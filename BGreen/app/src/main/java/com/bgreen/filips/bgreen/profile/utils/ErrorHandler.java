package com.bgreen.filips.bgreen.profile.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 *
 * Created by medioloco on 2015-10-27.
 */
public class ErrorHandler {

    private Context context;

    public ErrorHandler(Context context){
        this.context = context;
    }

    public void displayError(String errorMsg){
        new AlertDialog.Builder(context)
                .setTitle("Nätverksproblem")
                .setMessage("Orsakat av: " + errorMsg + ". Se till att du har inernetåtkomst " +
                        "och försök igen.")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
