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

    private int savedInstances;
    private static final String SAVED_INSTANCES = "savedInstances" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lifecycle);

        /**
         * Verifica se há algum estado salvo a ser restaurado devido a alguma mudança de configuração
         * do dispositivo.
         *
         * No onCreate() necessita-se verificar a existência de um estado salvo.
         *
         * Ex: Orientação, Idioma, etc...
         * */
        if(savedInstanceState != null) {

            /**
             * Caso haja alguma instância salva deve-se obter o valor, com uma atribuição default
             * caso não exista essa variável salva.
             *
             * */
            savedInstances = savedInstanceState.getInt(SAVED_INSTANCES, 0);


            /**
             * Verifica se o valor salvo foi diferente do default e executa a ação.
             * */
            if(savedInstances > 0) {
                //TODO Executar todo o trabalho necessário que dependa do estado salvo
            }

        }

        Log.d(TAG, String.format("%s.%s | savedInstances = %d", getClassName(), "onCreate()", savedInstances));
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

    /**
     * Utilizado para salvar a instancia de algum(ns) objeto(s) necessários durante a execução
     * do fluxo de atividades do aplicativo.
     *
     * Callback chamado antes que a activity seja
     * */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        savedInstances++;
        outState.putInt(SAVED_INSTANCES, savedInstances);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        /**
         * Não necessita verificar se existe um estado salvo para esta activity, pois o sistema
         * só chama o onRestoreInstanceState() se existir um estado a ser restaurado.
         * */
        savedInstances = savedInstanceState.getInt(SAVED_INSTANCES);

        Log.d(TAG, String.format("%s.%s | savedInstances = %d", getClassName(), "onRestoreInstanceState()", savedInstances));
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
