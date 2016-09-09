/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package com.capacitacao.embedded.aula03;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * UserFormFragment.java
 * <p/>
 * É do tipo OnDateSelectedListener para que utilize a função callback do DatePickerFragment, para
 * o evento de seleção de datas.
 * <p/>
 * Created by rogerio on 05/09/16.
 */
public class UserFormFragment extends Fragment implements DatePickerFragment.OnDateSelectedListener {

    private EditText mBirthDate, mName, mEmail, mPassword;
    private Spinner mGender;
    private FragmentManager supportFragmentManager;
    private AppCompatActivity mActivity;
    private Button mBtnSaveUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form_user, container, false);

        // pegando as referencias das views
        mName = (EditText) view.findViewById(R.id.name);
        mEmail = (EditText) view.findViewById(R.id.email);
        mPassword = (EditText) view.findViewById(R.id.password);
        mBirthDate = (EditText) view.findViewById(R.id.datepicker_birthdate);

        mGender = (Spinner) view.findViewById(R.id.gender);

        mBtnSaveUser = (Button) view.findViewById(R.id.btn_save_user);

        /**
         * Atribuição do evento de click para o EditText de data de nascimento
         * */
        mBirthDate.setOnClickListener(onEditTextBirthdateClicked());

        mBtnSaveUser.setOnClickListener(onBtnSaveClickedListener());

        return view;
    }

    public static UserFormFragment newInstance() {
        UserFormFragment fragment = new UserFormFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Click listener para o botão de salvar
     */
    public View.OnClickListener onBtnSaveClickedListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String name, String email, String gender, String birthDate, String password
                UserModel user = new UserModel(
                        mName.getText().toString(),
                        mEmail.getText().toString(),
                        mGender.getSelectedItem().toString(),
                        mBirthDate.getText().toString(),
                        mPassword.getText().toString()
                );

                // This makes sure that the container activity has implemented
                // the callback interface. If not, it throws an exception
                try {
                    ((OnUserSavedListener) mActivity).onUserSaved(user);

                } catch (ClassCastException e) {
                    throw new ClassCastException(mActivity.toString()
                            + " must implement OnUserSavedListener");
                }
            }
        };
    }

    /**
     * Callback do evento de click no EditText que dispara um Dialog para a seleção da data.
     */
    public View.OnClickListener onEditTextBirthdateClicked() {

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Lembrar que nesse caso estamos trabalhando com getSupportFragmentManager, então a API
                 * de fragment utilizada deve ser a v4 (android.support.v4.app.DialogFragment)
                 * */
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(mActivity.getSupportFragmentManager(), "datePicker");
            }
        };
    }

    /**
     * Atribui a referência da activity no onAttach
     * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (AppCompatActivity) context;
    }


    /**
     * Método callback de seleção de datas.
     * Após o usuário selecionar uma data no DatePickerFragment, ele vai disparar uma chamada
     * a este método através da referência desta activity no Fragment.
     */
    @Override
    public void onDateSelected(DatePicker datePicker) {
        String birthdate = String.format("%d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth(),
                datePicker.getYear());

        mBirthDate.setText(birthdate);
    }

    /**
     * Definição da Interface OnUserSavedListener para disparar o evento de quando salvar o
     * usuário.
     * */
    public interface OnUserSavedListener {
        void onUserSaved(UserModel user);
    }

}
