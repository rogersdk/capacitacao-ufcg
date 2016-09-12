package br.com.rogersdk.aula04;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by rogerio on 10/09/16.
 */
public class MainActivity extends AppCompatActivity {

    private Button  mBtnLaunchService, mBtnLaunchBoundActivity, mBtnLaunchIntentService,
                    mBtnLaunchForegroundService;
    private int extraInt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // obtendo a referência das views
        mBtnLaunchService = (Button) findViewById(R.id.btn_launch_lifecycle_service);
        mBtnLaunchBoundActivity = (Button) findViewById(R.id.btn_launch_bound_activity);
        mBtnLaunchIntentService = (Button) findViewById(R.id.btn_launch_intent_service);
        mBtnLaunchForegroundService = (Button) findViewById(R.id.btn_launch_foreground_service);

        // configurando os listeners
        mBtnLaunchService.setOnClickListener(onBtnLaunchServiceClicked());
        mBtnLaunchBoundActivity.setOnClickListener(onBtnLaunchBoundServiceActivity());
        mBtnLaunchIntentService.setOnClickListener(onBtnLaunchIntentServiceClicked());
        mBtnLaunchForegroundService.setOnClickListener(onBtnLaunchForegroundServiceClicked());

    }

    private View.OnClickListener onBtnLaunchIntentServiceClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(v.getContext(), ExampleIntentService.class);
                serviceIntent.putExtra(ExampleIntentService.EXTRA, extraInt++);
                startService(serviceIntent);
            }
        };
    }

    private View.OnClickListener onBtnLaunchServiceClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(v.getContext(), LifecycleService.class);
                serviceIntent.putExtra(LifecycleService.EXTRA, "The extra value.");
                startService(serviceIntent);
            }
        };
    }

    private View.OnClickListener onBtnLaunchBoundServiceActivity() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), BoundServiceActivity.class ));
            }
        };
    }

    private View.OnClickListener onBtnLaunchForegroundServiceClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent serviceIntent = new Intent(v.getContext(), ForegroundService.class);
                serviceIntent.putExtra(ForegroundService.EXTRA, "Passando valor");
                startService(serviceIntent);
            }
        };
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("receiver", intent.getAction());
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        // register no onResume()
        registerReceiver(receiver, new IntentFilter(ServiceBroadcastReceiver.SERVICE_ACTION));

    }

    @Override
    protected void onPause() {
        super.onPause();
        // unregister no onPause()
        unregisterReceiver(receiver);
    }
}
