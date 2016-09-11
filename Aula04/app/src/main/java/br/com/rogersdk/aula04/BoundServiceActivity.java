package br.com.rogersdk.aula04;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * BoundServiceActivity.java
 *
 * Activity responsável por disponibilizar a funcionalidade de criação de Bound Service.
 *
 * Possui botões para bind, unbind e interação com o Bound Service
 *
 * Created by rogerio on 11/09/16.
 */
public class BoundServiceActivity extends AppCompatActivity {

    // Referência para o Bound service
    BoundService mService;

    // flag para indicar se o serviço já está Bound
    boolean mBound = false;

    private TextView mTextView;
    private EditText mEditText;
    private Button mBtnBindService, mBtnGenerate, mBtnUnbindService;

    /**
     * Define os callbacks para o bind de serviço, passado pelo bindService()
     * */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {

            // Como está bound to BoundService, typecast the IBinder e obtenha a instância de
            // BoundService
            BoundService.MyBinder binder = (BoundService.MyBinder) service;


            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bound_service);

        mTextView = (TextView) findViewById(R.id.random_number);
        mEditText = (EditText) findViewById(R.id.random_number_interval);

        mBtnBindService = (Button) findViewById(R.id.btn_launch_service);
        mBtnGenerate = (Button) findViewById(R.id.btn_generate);
        mBtnUnbindService = (Button) findViewById(R.id.btn_unbind_service);


        mBtnBindService.setOnClickListener(onBtnBindBoundServiceListener());
        mBtnUnbindService.setOnClickListener(onBtnUnbindBoundServiceListener());
        mBtnGenerate.setOnClickListener(onBtnGenerateClickListener());
    }

    public View.OnClickListener onBtnGenerateClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // verifica a vinculação ao Serviço
                if(mBound) {
                    // obtém o valor do intervalo no edittext
                    int interval = Integer.parseInt(mEditText.getText().toString());

                    /**
                     * Acessa o serviço que está vinculado e obtém o valor
                     * */
                    int randomNumberGenerated = mService.generateRandomNumber(interval);

                    // modifica o valor no TextView
                    mTextView.setText(String.format("Service Running, number: %d",
                            randomNumberGenerated));

                } else {
                    Toast.makeText(v.getContext(), "Service not Bound", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    public View.OnClickListener onBtnBindBoundServiceListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tenta dar o bind no serviço
                bindService();
            }
        };
    }

    public View.OnClickListener onBtnUnbindBoundServiceListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // tenta dar unbind no serviço
                unBindService();
            }
        };
    }



    @Override
    protected void onStop() {
        super.onStop();

        unBindService();
    }

    /**
     * Executa o unBind (desvincular-se) no serviço, verificando se o mesmo encontra-se bind
     * (vinculado)
     * */
    private void unBindService() {
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
            Toast.makeText(this, "Service Unbound", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Service not Bound", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Executa o bind (vincular-se) no serviço, verificando se o mesmo encontra-se
     * unBind (desvinculado)
     * */
    private void bindService() {
        // Bind to Service
        if (!mBound) {
            // Bind to LocalService
            Intent intent = new Intent(this, BoundService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            Toast.makeText(this, "Service Bound", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Service already Bound", Toast.LENGTH_SHORT).show();
        }
    }
}
