package br.edu.ufcg.embedded.threadservice;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    https://journal.helabs.com/um-passeio-com-threads-no-android-parte-1-1fe57463a03#.4iy4yernn
    private Button mBtnServiceActivity;
    private OpenWifiReceiver openWifiReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // desabilitando a WIFI para começar o App
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(false);

        mBtnServiceActivity = (Button) findViewById(R.id.btn_service_activity);

        /**
         * Botão ativa o WIFI para que o OpenWifiReceiver seja lançado e assim por diante lance o
         * serviço.
         * */
        mBtnServiceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager)v.getContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // declarando o broadcast receiver
        openWifiReceiver = new OpenWifiReceiver();

        // registrando o receiver na Activity, para que ele so execute quando o APP estiver em
        // Foreground (executando)
        registerReceiver(openWifiReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));

    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(openWifiReceiver);
    }
}
