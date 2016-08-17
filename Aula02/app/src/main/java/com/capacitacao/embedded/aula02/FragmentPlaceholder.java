/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * FragmentPlaceholder.java
 *
 * Exemplo de uma classe de fragment que pode ser utilizado para mais de uma finalidade
 * através de seus argumentos.
 *
 * Created by rogerio on 16/08/16.
 */
public class FragmentPlaceholder extends Fragment {

    /**
     * Variavel utilizada para definir a chave do argumento.
     * */
    private static final String TEXT = "text";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Argumentos estão disponíveis no onCreate()
         * */
        Log.d("args", getArguments().toString());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);

        Bundle args = getArguments();

        /**
         * Verificação da existência de argumentos para este fragment.
         * */
        if(args != null && !args.isEmpty()) {
            TextView text = (TextView) view.findViewById(R.id.text_fragment);
            text.setText(args.getString(TEXT));
        }

        return view;
    }

    /**
     * Pode ser que o Android decida recriar seu Fragment um tempo depois, chamando o construtor
     * vazio do seu fragment. Então sobrescrever o construtor não é uma solução, pois você perderá
     * seus argumentos.
     *
     * Utilizar o método newInstance() é uma boa prática de criação de fragments,
     * pois preserva o(s) argumento(s) caso o Android recrie o fragment.
     * */
    public static FragmentPlaceholder newInstance(String text) {
        FragmentPlaceholder fragment = new FragmentPlaceholder();
        Bundle args = new Bundle();
        args.putString(TEXT, text);
        fragment.setArguments(args);
        return fragment;
    }
}
