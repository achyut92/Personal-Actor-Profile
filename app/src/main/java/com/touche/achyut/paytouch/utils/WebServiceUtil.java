package com.touche.achyut.paytouch.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebServiceUtil {

    public static String getJson(String urlStr)
    {
        URL url;
        HttpURLConnection httpConn = null;
        String inStream = null;



        try {
            url = new URL(urlStr);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Content-Type", "application/json");

            inStream = WebServiceUtil.readStream(httpConn.getInputStream());


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }

            return inStream;
        }
    }

    public static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));


            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static InputStream getStreamFromUrl(String url) {
        InputStream inStream=null;
        try {
            inStream= (InputStream)new URL(url).getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inStream;
    }

}
