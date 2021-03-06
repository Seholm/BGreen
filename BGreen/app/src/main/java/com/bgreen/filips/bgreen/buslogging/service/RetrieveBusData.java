package com.bgreen.filips.bgreen.buslogging.service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Filips on 9/23/2015.
 */
public class RetrieveBusData extends AsyncTask <String,Void,String> {
    //class to recieve data from electricity API
    @Override
    protected String doInBackground(String... params) {
        try {

            StringBuffer response = new StringBuffer();
            String key = "Basic Z3JwMjA6eXhZN09ENGFRMg=="; //API-key
            String url = getURL(params[0],params[1]); //gets info for what to ask the api for, aparam[0] is what buss,
                                                    // param[1] is the value.
            URL requestURL = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) requestURL
                    .openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", key);
            //Creates a URL connection towards the Electrycity API

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                //adds all the resonpses to one string
            }
            in.close(); //closes the inputReader
            return parseRespnos(response.toString());
        }catch(IOException e ){
            return null; //null return is handled in class using this class
        }
    }

    private String getURL(String vin, String sensorSpec){
        //builds the url with different vin(to identify which buss) and sensorspec (what value to ask for)
        long t2 = System.currentTimeMillis();
        long t1 = t2 - (1000 * 30);
        String url = "https://ece01.ericsson.net:4443/ecity?dgw=Ericsson$"+vin+"&sensorSpec=Ericsson$"+sensorSpec+"&t1="
                + t1 + "&t2=" + t2;
        return url;

    }

    private String parseRespnos(String respons) {
        //loops through the String and searches for "value" in the string
        if (respons != null) {
            for (int i = respons.length() - 5; i > 0; i--) { //loops from the end
                if (respons.substring(i, i + 5).equals("value")) {
                    int startIndex = 0;
                    int endIndex = 0;
                    int count = 0;
                    for (int j = i + 6; j < respons.length(); j++)
                        //if a value string is found, search for the next string between the "" symbols
                        if (respons.charAt(j) == '"') {
                            count++;
                            if (count == 2) {
                                endIndex = j;
                                if (!Character.isDigit(respons.charAt(startIndex + 1))) {
                                    //checks if the value is a alphabetic String to get the correct string
                                    return respons.substring(startIndex + 1, endIndex);
                                }
                            } else {
                                startIndex = j;
                            }
                        }
                }
            }

        }
        //returns null if not found
        return null;
    }
}
