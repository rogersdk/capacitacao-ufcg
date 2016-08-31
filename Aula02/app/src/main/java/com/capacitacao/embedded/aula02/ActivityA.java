/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * ActivityA.java
 *
 * Activity criada para exemplificar a navegação entre telas e a troca de mensagens
 * as mesmas.
 *
 * Created by rogerio on 16/08/16.
 */
public class ActivityA extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.capacitacao.embedded.aula02.MESSAGE";

    private static final String TAG = "lifecycle";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);
        Log.d(TAG, String.format("%s.%s", getClassName(), "onCreate()"));
    }

    /**
     * Chamado quando o usuario clica na View
     *
     * @param view View associada ao evento de clique
     * */
    public void enviarMensagem(View view) {
        /**
         * Cria a intent para Activity B
         *
         * */
        Intent intent = new Intent(this, ActivityB.class);

        EditText editText = (EditText) findViewById(R.id.texto_mensagem);
        String message = editText.getText().toString();

        /**
         * Inserindo a mensagem dentro do intent
         * */
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * Chamado quando o usuario clica na View
     *
     * @param view View associada ao evento de clique
     * */
    public void intentImplicita(View view) {
        /**
         * Cria a intent IMPLICITA para Activity B
         *
         * */
        Intent intent = new Intent("com.capacitacao.embedded.aula02.implicit");

        startActivity(intent);
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
