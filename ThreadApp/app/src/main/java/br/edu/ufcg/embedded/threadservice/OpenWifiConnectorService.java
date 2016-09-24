package br.edu.ufcg.embedded.threadservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rogerio on 24/09/16.
 */
public class OpenWifiConnectorService extends Service {

    private Thread wifiThread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("wifi", String.format("OpenWifiConnectorService"));

        try {
            /**
             * Deve-se lançar uma thread para a realização de trabalho intensivo, para assim evitar
             * erros de Application Not Responding
             * */
            wifiThread = new Thread(new SearchOpenWifiThread(getApplicationContext()));
            wifiThread.start();
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wifiThread = null;
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

                        /**
                         * O teste aqui foi apenas pelo SSID da rede, para facilitar o entendimento.
                         *
                         * Porém poderia ser um teste de Aberta, e daí só executaria esse trecho de
                         * código quando satisfizesse a operação, o que pode demorar um tempo
                         * indeterminado.
                         * */
                        if(result.SSID.equals("rcnobrega")) {
                            Log.d("wifi", String.format("Found rcnobrega = %d", ++cont));
                            Thread.currentThread().interrupt();
                        } else {
                            cont++;
                        }
                    }
                }
            }

            wifiManager.setWifiEnabled(false);
            stopSelf();
        }

    }

}
