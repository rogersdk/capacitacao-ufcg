package com.capacitacao.embedded.aula02;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * FragmentLifecycle.java
 *
 * Fragment que demonstra o ciclo de vida de um fragment, assim como todos os seus
 * principais callbacks.
 *
 * Created by rogerio on 16/08/16.
 */
public class FragmentLifecycle extends Fragment {

    private static final String TAG = "lifecycle";

    private static final String SAVED_INSTANCES = "savedInstances";

    private int savedInstances;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onCreate()"));
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onStart()"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lifecycle, container, false);

        /**
         * Verifica se há algum estado salvo a ser restaurado devido a alguma mudança de configuração
         * do dispositivo.
         *
         * Ex: Orientação, Idioma, etc...
         *
         * Não utilizar este método se o setRetainInstance for TRUE.
         * Utilizar apenas se chamar o onSavedInstanceState
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

        Log.d(TAG, String.format(" -> %s.%s | savedInstances = %d",
                getClassName(), "onCreateView()", savedInstances));

        /**
         * Salva o estado do fragment sem a utilização do método onSavedInstanceState()
         * */
//        setRetainInstance(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        /**
         * Usar quando o setRetainInstance é TRUE
         * */
        //savedInstances++;

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onResume()"));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onAttach()"));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onDetach()"));
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onPause()"));
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onStop()"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onDestroy()"));
    }

    /**
     * Utilizado para salvar a instancia de algum(ns) objeto(s) necessários durante a execução
     * do fluxo de atividades do aplicativo.
     *
     * Callback chamado antes que a activity seja
     * */
    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        savedInstances++;
        outState.putInt(SAVED_INSTANCES, savedInstances);
        Log.d(TAG, String.format(" -> %s.%s", getClassName(), "onSaveInstanceState()"));
    }*/

    public String getClassName() {
        String className = getClass().getName();
        return (className.substring(className.lastIndexOf(".") + 1));
    }
}
