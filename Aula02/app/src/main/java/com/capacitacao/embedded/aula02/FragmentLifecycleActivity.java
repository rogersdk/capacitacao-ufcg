package com.capacitacao.embedded.aula02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by rogerio on 16/08/16.
 */
public class FragmentLifecycleActivity extends AppCompatActivity {

    private static final String TAG = "lifecycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment_lifecycle);
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

    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }
}
