/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package aula05.embedded.capacitacao.com.aula05;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by rogerio on 14/09/16.
 */
public class SharedPreferencesActivity extends AppCompatActivity {

    private static final String SHARED_PREF = "embedded.capacitacao.com.aula05.PREFS";

    private CheckBox mCheckbox;
    private TextView mValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shared_preferences);

        mCheckbox = (CheckBox) findViewById(R.id.checkbox);
        mValue = (TextView) findViewById(R.id.value);

        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                saveToSharedPreference(isChecked);

                mValue.setText(String.format("O valor é: %b", isChecked));
            }
        });
    }

    public void saveToSharedPreference(boolean isChecked) {

        // recebe a referencia de uma shared preference, baseada com o nome "SHARED_PREF"
        // Context.MODE_PRIVADE - modo de criação do arquivo, apenas App com o mesmo user ID
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        sharedPref
                .edit()
                .putBoolean("checkbox", isChecked)
                .commit();

        updateValue();

    }

    public void updateValue() {
        SharedPreferences sharedPref = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        // retorna o valor, se nao existir retorna "false" por padrão
        boolean newValue = sharedPref.getBoolean("checkbox", false);

        mValue.setText(String.format("O valor é: %b", newValue));
    }

}
