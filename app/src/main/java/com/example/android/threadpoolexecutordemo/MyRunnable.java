package com.example.android.threadpoolexecutordemo;


import android.util.Log;

public class MyRunnable implements Runnable {
    public static final String LOG_TAG=MyRunnable.class.getSimpleName();

    public static final int DELAY_MILLIS=500;
    public static final int ITERATIONS_COUNT=10;

    private String name;

    public MyRunnable(String name){
        this.name=name;
    }

    @Override
    public void run() {
        for(int i =0;i<ITERATIONS_COUNT;i++){
            Log.d(LOG_TAG,name+", i="+(i+1));
            try {
                Thread.sleep(DELAY_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
