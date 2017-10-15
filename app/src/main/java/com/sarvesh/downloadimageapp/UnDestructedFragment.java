package com.sarvesh.downloadimageapp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class UnDestructedFragment extends Fragment{
    Activity activity;
    MyAsyncTask myAsyncTask;


    public void beginTask(String value){
        myAsyncTask =new MyAsyncTask(activity);
        myAsyncTask.execute(value);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity= (Activity) context;
        if(myAsyncTask!=null){
            myAsyncTask.onAttach(context);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(myAsyncTask!=null){
            myAsyncTask.onDetach();
        }

    }
}
