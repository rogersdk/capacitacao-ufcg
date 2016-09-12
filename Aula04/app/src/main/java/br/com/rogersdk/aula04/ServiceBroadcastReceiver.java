package br.com.rogersdk.aula04;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rogerio on 11/09/16.
 */
public class ServiceBroadcastReceiver extends BroadcastReceiver {
    public static final String SERVICE_ACTION = "br.com.rogersdk.aula04.SERVICES";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(SERVICE_ACTION)) {
            Toast.makeText(context, "Broadcast do Serviço", Toast.LENGTH_SHORT).show();

            Log.d("lifecycle", String.format("%s.%s",getClassName(), "onReceive()"));
        }
    }

    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }
}
