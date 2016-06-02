package com.touche.achyut.paytouch.presentation.AsyncTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;


import com.touche.achyut.paytouch.utils.WebServiceUtil;

import java.io.InputStream;

public class ImageStreamDownloadTask extends AsyncTask<Void, Void, Bitmap> {

    private String url;
    private Bitmap bitmap;

    public ImageStreamDownloadTask(String adUrl)
    {
        this.url=adUrl;
        this.bitmap=null;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        InputStream inStream= WebServiceUtil.getStreamFromUrl(url);
        bitmap = BitmapFactory.decodeStream(inStream);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }

}