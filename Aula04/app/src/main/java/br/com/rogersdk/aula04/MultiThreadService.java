/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package br.com.rogersdk.aula04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by rogerio on 12/09/16.
 */
public class MultiThreadService extends Service {

    private ArrayList<WorkerThread> threads = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("lifecycle", String.format("Multi-Thread.%s", "onCreate()"));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        WorkerThread worker = new WorkerThread(startId);
        worker.start();
        threads.add(worker);

        Log.d("lifecycle", String.format("Multi-Thread.%s", "onStartCommand()"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class WorkerThread extends Thread {

        public int startId, count;

        public boolean active = true;

        public WorkerThread(int startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            while (active && count < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d("lifecycle", String.format("-> ThreadId = %d / count = %d", startId, count++));
            }

            Log.d("lifecycle", String.format("-> ThreadId = %d preparing to destroy", startId));
            stopSelf(startId);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for(WorkerThread w: threads) {
            w.active = false;
        }

        Log.d("lifecycle", String.format("Multi-Thread.%s", "onDestroy()"));
    }


}
