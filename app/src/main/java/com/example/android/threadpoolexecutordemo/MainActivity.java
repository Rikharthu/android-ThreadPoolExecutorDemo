package com.example.android.threadpoolexecutordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // executor with an expandable thread pool. This executor is suitable for applications that launch many short-lived tasks.
        // allocates a new Thread or reuses existing one for each runnable (unused thread is cached for 60 seconds)
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        // executor that uses a fixed thread pool (always maintains them)
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
//        for (int i = 0; i <= 5; i++)
//        {
//            String taskName="Task " + i;
//            MyRunnable task = new MyRunnable("Task " + i);
//            Log.d(LOG_TAG,("A new task has been added : " +taskName));
//            executor.execute(task);
//        }
//        executor.shutdown();

        // Fork/Join demo
        MyAction action = new MyAction(0,3000);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(action);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // executes Runnable's synchronously (one after another)
//        Executor executor = new Executor() {
//            @Override
//            public void execute(Runnable runnable) {
//                // by default runs in caller's thread
//                Thread thread = new Thread(runnable);
//                thread.start();
//
//                // or
//
////                runnable.run();
//            }
//        };
//
//        executor.execute(new MyRunnable("A"));
//        executor.execute(new MyRunnable("B"));
//        executor.execute(new MyRunnable("C"));
    }
}
