package br.edu.ufcg.embedded.threadservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by rogerio on 24/09/16.
 */
public class OpenWifiReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("wifi", intent.getAction());

        if(intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
            WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
            Log.d("wifi", String.format("Wifi enabled = %b", wifiManager.isWifiEnabled()));

            if (wifiManager.isWifiEnabled()) {

                context.startService(new Intent("br.edu.ufcg.embedded.threadservice.OPEN_WIFI_CONNECTOR_SERVICE"));
            }
        }


    }
}
