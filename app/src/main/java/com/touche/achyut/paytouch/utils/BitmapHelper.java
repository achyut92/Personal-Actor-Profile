package com.touche.achyut.paytouch.utils;


import android.graphics.Bitmap;

import com.touche.achyut.paytouch.presentation.AsyncTask.ImageStreamDownloadTask;

import java.util.concurrent.ExecutionException;

public class BitmapHelper {



    public static Bitmap getBitmap(String url){

        Bitmap bitmap = null;

        try {
            bitmap = new ImageStreamDownloadTask(url).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
