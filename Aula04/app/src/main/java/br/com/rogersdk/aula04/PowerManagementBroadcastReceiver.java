package br.com.rogersdk.aula04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.PowerManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * PowerManagementBroadcastReceiver.java
 *
 * Broadcast receiver que monitora a Energia do dispositivo, Conectado ou Desconectado.
 *
 * Created by rogerio on 11/09/16.
 */
public class PowerManagementBroadcastReceiver extends BroadcastReceiver {
    private final String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";
    private final String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Charging", Toast.LENGTH_SHORT).show();
        } else if(intent.getAction().equals(ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Not Charging", Toast.LENGTH_SHORT).show();
        }


    }
}
