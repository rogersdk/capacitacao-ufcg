package br.edu.ufcg.embedded.threadservice;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//    https://journal.helabs.com/um-passeio-com-threads-no-android-parte-1-1fe57463a03#.4iy4yernn
    private Button mBtnThreadActivity, mBtnServiceActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnThreadActivity = (Button) findViewById(R.id.btn_thread_activity);
        mBtnServiceActivity = (Button) findViewById(R.id.btn_service_activity);



        mBtnThreadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ThreadActivity.class));
            }
        });


        mBtnServiceActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager)v.getContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
            }
        });
    }
}
