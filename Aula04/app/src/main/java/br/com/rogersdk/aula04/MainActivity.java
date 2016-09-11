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

    private Button mBtnLaunchService;
    private Spinner mSpinnerServiceList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // obtendo a referência das views
        mBtnLaunchService = (Button) findViewById(R.id.btn_launch_service);
        mSpinnerServiceList = (Spinner) findViewById(R.id.spinner_service_list);

        // configurando os listeners
        mBtnLaunchService.setOnClickListener(onBtnLaunchServiceClicked());

    }

    private View.OnClickListener onBtnLaunchServiceClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent(ServiceEnum.fromOrdinal(mSpinnerServiceList.getSelectedItemPosition()));

                if(i != null) {
                    startService(i);
                }
            }
        };
    }

    public Intent getIntent(ServiceEnum service) {

        switch (service) {
            case LIFECYCLE:
                return new Intent(this, LifecycleService.class);
            default:
                break;
        }


        return new Intent();
    }

}
