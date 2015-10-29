package com.bgreen.filips.bgreen.achievements.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bgreen.filips.bgreen.profile.utils.ErrorHandler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class created to download images from URL and convert to bitmap object
 *
 * Created by Albertsson on 15-10-26.
 */
public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private ImageView imageView;
    private ErrorHandler errorHandler;

    //Method to get image from url and place it in the specific imageView Object
    public ImageLoadTask(String url, ImageView imageView) {
        this.url = url;
        this.imageView = imageView;
        this.errorHandler = new ErrorHandler(imageView.getContext());
    }

    //Background thread that connects to the url site and converts the image to a bitmap Object
    @Override
    protected Bitmap doInBackground(Void... params) {
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (Exception e) {
            //Displays error if the images couldn't load
            errorHandler.displayError(e.getMessage());
        }
        return null;
    }

    //If the result passed the Imgae will be set
    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        imageView.setImageBitmap(result);
    }

}
