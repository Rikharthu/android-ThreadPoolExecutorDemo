package com.example.android.threadpoolexecutordemo;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.RecursiveAction;


public class MyAction extends RecursiveAction {
    public static final String LOG_TAG=MyAction.class.getSimpleName();

    private static boolean firstLaunch=true;
    int start;
    int stop;

    Random random = new Random();

    public MyAction(int start, int stop) {
        this.start = start;
        this.stop = stop;
    }

    private void computeDirectly(){
        String name = "["+start+", "+stop+"]";
        for(int i=start;i<=stop;i++){
            Log.d(LOG_TAG,name+", i="+i);
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        Log.e(LOG_TAG,name+" finished "+dateStr);

    }

    @Override
    protected void compute() {
        DateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);
        if((stop-start)<1000){
            Log.e(LOG_TAG,"computing directly ["+start+", "+stop+"] "+dateStr);
            computeDirectly();
        }else{
            int split = (stop-start)/2;

            Log.e(LOG_TAG,"splitting ["+start+", "+stop+"] into ["+start+", "+(start+split)+"] " +
                    "and ["+(start+split)+","+stop+"] "+dateStr);
            invokeAll(new MyAction(start,start+split),
                    new MyAction(start+split+1,stop));
        }
    }
}
