package br.com.rogersdk.aula04;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by rogerio on 10/09/16.
 */
public class MainActivity extends AppCompatActivity {

    private Button mBtnLaunchService, mBtnLaunchBoundActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // obtendo a referência das views
        mBtnLaunchService = (Button) findViewById(R.id.btn_launch_lifecycle_service);
        mBtnLaunchBoundActivity = (Button) findViewById(R.id.btn_launch_bound_activity);

        // configurando os listeners
        mBtnLaunchService.setOnClickListener(onBtnLaunchServiceClicked());
        mBtnLaunchBoundActivity.setOnClickListener(onBtnLaunchBoundServiceActivity());

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
}
