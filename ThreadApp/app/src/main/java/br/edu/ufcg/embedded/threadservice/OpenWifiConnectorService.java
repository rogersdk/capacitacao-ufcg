package br.edu.ufcg.embedded.threadservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rogerio on 24/09/16.
 */
public class OpenWifiConnectorService extends Service {

    private Thread t;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("wifi", String.format("OpenWifiConnectorService"));

        try {
            t = new Thread(new SearchOpenWifiThread(getApplicationContext()));
            t.start();
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        t = null;
        Log.d("wifi", String.format("OpenWifiConnectorService.onDestroy()"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public class SearchOpenWifiThread implements Runnable {

        private final WifiManager wifiManager;

        public SearchOpenWifiThread(Context context) {
            wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        }

        @Override
        public void run() {
            int cont = 0;
            List<ScanResult> resultList = new ArrayList<>();
            while(!Thread.currentThread().isInterrupted()) {
                resultList = wifiManager.getScanResults();

                if(resultList != null && resultList.size() > 0) {
                    for(ScanResult result: resultList) {
                        if(result.SSID.equals("yournetworkname")) {
                            Log.d("wifi", String.format("Found yournetworkname = %d", ++cont));
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }

            wifiManager.setWifiEnabled(false);
            stopSelf();
        }

    }

}
