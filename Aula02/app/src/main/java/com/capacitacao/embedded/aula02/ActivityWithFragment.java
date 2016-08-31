/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * ActivityWithFragment.java
 *
 * Activity que adiciona fragments programaticamente.
 *
 * Created by rogerio on 16/08/16.
 */
public class ActivityWithFragment extends AppCompatActivity {

    private static final String TAG = "lifecycle";

    private FrameLayout mFragmentA;
    private FrameLayout mFragmentB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_with_fragment);
        Log.d(TAG, String.format("%s.%s", getClassName(), "onCreate()"));

        mFragmentA = (FrameLayout) findViewById(R.id.fragment_a);
        mFragmentB = (FrameLayout) findViewById(R.id.fragment_b);

        FragmentPlaceholder fragmentA = FragmentPlaceholder.newInstance("Fragment A");
        FragmentPlaceholder fragmentB = FragmentPlaceholder.newInstance("Fragment B");

        /**
         * Adiciona uma instancia de fragment ao respectivo layout.
         * */
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_a, fragmentA).commit();

        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_b, fragmentB).commit();

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
