package com.capacitacao.embedded.aula03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

/**
 * UserFormActivity.java
 *
 * É do tipo OnClickListener e OnDateSelectedListener para que utilize as funções callbacks de
 * cada evento disparado.
 *
 * Created by rogerio on 18/08/16.
 */
public class UserFormActivity extends AppCompatActivity
        implements DatePickerFragment.OnDateSelectedListener, UserFormFragment.OnUserSavedListener {

    private static final String FRAGMENT_USER_EDIT = "fragment_form";
    private static final String FRAGMENT_USER_DETAIL = "fragment_detail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_user);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_edit_user,
                UserFormFragment.newInstance(), FRAGMENT_USER_EDIT).commit();

        if(isTablet()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail_user,
                    UserDetailFragment.newInstance(), FRAGMENT_USER_DETAIL).commit();
        }

    }

    public boolean isTablet() {
        return getResources().getBoolean(R.bool.tablet);
    }

    /**
     * Método callback de seleção de datas.
     * Após o usuário selecionar uma data no DatePickerFragment, ele vai disparar uma chamada
     * a este método através da referência desta activity no Fragment.
     * */
    @Override
    public void onDateSelected(DatePicker datePicker) {
        DatePickerFragment.OnDateSelectedListener callback =
                (DatePickerFragment.OnDateSelectedListener)getSupportFragmentManager()
                .findFragmentByTag(FRAGMENT_USER_EDIT);

        callback.onDateSelected(datePicker);
    }

    /**
     * Método callback chamado para atualizar o UserDetailFragment que também implementa a mesma
     * interface.
     * */
    @Override
    public void onUserSaved(UserModel user) {
        if(isTablet()) {
            UserFormFragment.OnUserSavedListener callback =
                    (UserFormFragment.OnUserSavedListener)getSupportFragmentManager()
                            .findFragmentByTag(FRAGMENT_USER_DETAIL);

            callback.onUserSaved(user);
        }
    }
}
