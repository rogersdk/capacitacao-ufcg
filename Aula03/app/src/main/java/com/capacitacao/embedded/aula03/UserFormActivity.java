package com.capacitacao.embedded.aula03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

/**
 * UserFormActivity.java
 *
 * É do tipo OnClickListener e OnDateSelectedListener para que utilize as funções callbacks de
 * cada evento disparado.
 *
 * Created by rogerio on 18/08/16.
 */
public class UserFormActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerFragment.OnDateSelectedListener {

    private EditText mBirthDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.form_user);


        mBirthDate = (EditText) findViewById(R.id.birthdate);

        /**
         * Atribuição do evento de click para o EditText.
         * */
        mBirthDate.setOnClickListener(this);

    }

    /**
     * Callback do evento de click no EditText que dispara um Dialog para a seleção da data.
     * */
    @Override
    public void onClick(View view) {
        /**
         * Lembrar que nesse caso estamos trabalhando com getSupportFragmentManager, então a API
         * de fragment utilizada deve ser a v4 (android.support.v4.app.DialogFragment)
         * */
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * Método callback de seleção de datas.
     * Após o usuário selecionar uma data no DatePickerFragment, ele vai disparar uma chamada
     * a este método através da referência desta activity no Fragment.
     * */
    @Override
    public void onDateSelected(DatePicker datePicker) {
        String birthdate = String.format("%d/%d/%d", datePicker.getDayOfMonth(), datePicker.getMonth(),
                datePicker.getYear());

        mBirthDate.setText(birthdate);
    }

}
