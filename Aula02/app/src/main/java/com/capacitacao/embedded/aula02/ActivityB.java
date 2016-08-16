/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by rogerio on 16/08/16.
 */
public class ActivityB extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_b);

        TextView textView = (TextView) findViewById(R.id.mensagem);

        /**
         * Intent criada a partir da chamada da Activity A
         * */
        Intent intent = getIntent();
        String mensagem = intent.getStringExtra(ActivityA.EXTRA_MESSAGE);

        textView.setText(mensagem);
    }


}
