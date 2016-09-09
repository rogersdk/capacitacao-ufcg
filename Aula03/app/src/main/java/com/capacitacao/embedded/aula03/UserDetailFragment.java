/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;

/**
 * UserDetailFragment.java
 *
 * Fragment responsável por exibir as informações inseridas pelo formulário.
 *
 * Implementa a interface OnUserSavedListener para ser notificado quando o Usuário clicar no botão
 * salvar do Formulário.
 *
 * Created by rogerio on 05/09/16.
 */
public class UserDetailFragment extends Fragment implements UserFormFragment.OnUserSavedListener {

    private static final String USER_PARAM = "user_param";
    // Declaração dos objetos presentes na view
    private TextView mBirthDate, mName, mEmail, mPassword, mGender;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_user, container, false);

        // Atribuição das referências presentes na view do fragment
        mName = (TextView) view.findViewById(R.id.full_name);
        mBirthDate = (TextView) view.findViewById(R.id.birthdate);
        mPassword = (TextView) view.findViewById(R.id.password);
        mGender = (TextView) view.findViewById(R.id.gender);
        mEmail = (TextView) view.findViewById(R.id.email);


        /**
         * Verifica se exite argumentos passados para o fragment através do Bundle, e se também
         * não está vazio.
         * */
        if(getArguments() != null && !getArguments().isEmpty()) {
            Bundle args = getArguments();

            // Pega o usuário passado no argumento
            Serializable user = args.getSerializable(USER_PARAM);

            // Dá um typecast de UserModel e passa como parâmetro
            setViewValues((UserModel) user);
        }



        return view;
    }

    /**
     * Utilizando quando se precisa de uma instância do fragment comum, sem valores
     * */
    public static UserDetailFragment newInstance() {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    /**
     * Sobrecarga de método
     *
     * Utilizado quando se quer passar algum parâmetro para este fragment.
     * */
    public static UserDetailFragment newInstance(UserModel user) {
        UserDetailFragment fragment = new UserDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(USER_PARAM, user);
        fragment.setArguments(args);

        return fragment;
    }


    @Override
    public void onUserSaved(UserModel user) {
        Log.d("user", user.toString());

        setViewValues(user);

    }

    /**
     * Atribui os valores para as views do layout
     * */
    public void setViewValues(UserModel user) {
        if(user != null) {
            mName.setText(user.getName());
            mEmail.setText(user.getEmail());
            mPassword.setText(user.getPassword());
            mBirthDate.setText(user.getBirthdate());
            mGender.setText(user.getGender());
        }
    }

}
