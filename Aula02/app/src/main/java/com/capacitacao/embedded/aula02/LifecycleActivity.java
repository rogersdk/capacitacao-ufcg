package com.capacitacao.embedded.aula02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * LifecycleActivity.java
 *
 * Classe criada para demonstrar o ciclo de vida de uma activity, assim como todos os seus
 * principais callbacks.
 *
 * Created by rogerio on 16/08/16.
 */
public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = "lifecycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecycle);
        Log.d(TAG, String.format("%s passou por %s", TAG, "onCreate()"));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, String.format("%s passou por %s", TAG, "onStart()"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, String.format("%s passou por %s", TAG, "onResume()"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, String.format("%s passou por %s", TAG, "onPause()"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, String.format("%s passou por %s", TAG, "onRestart()"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, String.format("%s passou por %s", TAG, "onStop()"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, String.format("%s passou por %s", TAG, "onDestroy()"));
    }
}
