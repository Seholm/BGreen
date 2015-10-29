package com.bgreen.filips.bgreen.profile.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * this class is intended to save a users ID as a string to a .txt file.
 * The ID is a unique String given by our DB when we save an Object.
 * By having the ID of a user already on startup we can easily fetch it from our DB.
 * Created by medioloco on 2015-09-22.
 */
public class UserHandler implements IUserHandler {

    private Context context;

    public UserHandler(Context context){
        this.context = context;
    }

    @Override
    public void writeToFile(String userID) {
        try {
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(context.openFileOutput("userData.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(userID);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    @Override
    public String getUserID() {

        String ret = null;

        try {
            InputStream inputStream = context.openFileInput("userData.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
