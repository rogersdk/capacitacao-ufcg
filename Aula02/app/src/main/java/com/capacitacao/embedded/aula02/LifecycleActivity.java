package com.capacitacao.embedded.aula02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * LifecycleActivity.java
 *
 * Activity que demonstra o ciclo de vida de uma activity, assim como todos os seus
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
        Log.d(TAG, String.format("%s.%s", getClassName(), "onCreate()"));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onStart()"));
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onResume()"));
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onPause()"));
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onRestart()"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onStop()"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, String.format("%s.%s", getClassName(), "onDestroy()"));
    }

    public void abrirComunicacaoEntreActivities(View view) {
        startActivity(new Intent(this, ActivityA.class));

    }

    public void abrirCicloDeVidaFragment(View view) {
        startActivity(new Intent(this, FragmentLifecycleActivity.class));
    }

    public void abrirActivityComFragmentAdicionadoProgramaticamente(View view) {
        startActivity(new Intent(this, ActivityWithFragment.class));
    }

    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }

}
