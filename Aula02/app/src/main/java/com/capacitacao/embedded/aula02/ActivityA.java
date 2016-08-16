/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula02;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);
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

}
