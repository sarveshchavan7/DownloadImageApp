package com.sarvesh.downloadimageapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MyAsyncTask extends AsyncTask <String,Integer,Boolean>{
    private int totalBytesRead = -1;
    private  int lengthOfFile = 0;
    int progressValue=0;
    Activity activity;

    MyAsyncTask(Activity activity){
        this.activity=activity;
    }

    public void onAttach(Context context){
        this.activity = (Activity) context;
    }

    public void onDetach(){
        activity=null;
    }

    @Override
    protected Boolean doInBackground(String... objects) {
        URL url = null;
        int read=-1;

        try {
            url=new URL(objects[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            lengthOfFile = httpURLConnection.getContentLength();
            InputStream inputStream = httpURLConnection.getInputStream();
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath()+"/"+ Uri.parse((String) objects[0]).getLastPathSegment());
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            byte [] mybytes=new byte[1024];

            while((read = inputStream.read(mybytes))!=-1){
                totalBytesRead+=read;
                publishProgress(totalBytesRead);
                fileOutputStream.write(mybytes,0,read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        if(activity!=null){
            progressValue = (int) (((double)values[0]/lengthOfFile)*100);
            Log.d("Progressfuck", String.valueOf((double) totalBytesRead));
            ((MainActivity)activity).updateProgress(progressValue);
        }
    }
}
