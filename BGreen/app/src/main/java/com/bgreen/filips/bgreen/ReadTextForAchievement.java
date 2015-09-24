package com.bgreen.filips.bgreen;

import android.content.res.Resources;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by paki on 24/09/15.
 */
public class ReadTextForAchievement {

    public String parseHeadline(String s) {
        String headLine = "";
        if (s.contains(";")) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ';') {
                    headLine = s.substring(0, i);
                }
            }
        }  return headLine;
    }

    public String parseDescText (String s) {
        String descText = "";
        if (s.contains(";") && s.contains("*")) {
            for (int i = 0; i< s.length(); i++) {
                for (int y = 0; y<s.length(); y++) {
                    if (s.charAt(i) == ';') {
                        if (s.charAt(y) == '*') {
                            descText = s.substring(i+1, y);
                        }
                    }
                }
            }
        } return descText;
    }


}
