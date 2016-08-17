/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

/**
 * ActivityWithFragment.java
 *
 * Activity que adiciona fragments programaticamente.
 *
 * Created by rogerio on 16/08/16.
 */
public class ActivityWithFragment extends AppCompatActivity {

    private FrameLayout mFragmentA;
    private FrameLayout mFragmentB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_with_fragment);

        mFragmentA = (FrameLayout) findViewById(R.id.fragment_a);
        mFragmentB = (FrameLayout) findViewById(R.id.fragment_b);

        FragmentPlaceholder fragmentA = FragmentPlaceholder.newInstance("Fragment A");
        FragmentPlaceholder fragmentB = FragmentPlaceholder.newInstance("Fragment B");

        /**
         * Adiciona uma instancia de fragment ao respectivo layout.
         * */
        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_a, fragmentA).commit();

        getSupportFragmentManager().beginTransaction().add(
                R.id.fragment_b, fragmentB).commit();

    }


}
