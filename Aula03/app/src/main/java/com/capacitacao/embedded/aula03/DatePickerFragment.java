package com.capacitacao.embedded.aula03;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * DatePickerFragment.java
 *
 * DialogFragment que lança um DIALOG com um DatePicker.
 *
 * Classe utilizada para dar a funcionalidade de seleção de datas através de um dialog.
 *
 * Created by rogerio on 04/09/16.
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    /**
     * Um listener que será chamado após o evento de mudança de data do datepicker.
     * */
    private OnDateSelectedListener mActivityListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        /**
         * Chamada callback para que a VIEW decida o que vai fazer com o dado, após um seleção de
         * nova data.
         * */
        mActivityListener.onDateSelected(datePicker);
    }

    /**
     * Lifecycle Callback do fragment, utilizado para a atribuição do valor de referência da View
     * através do parâmetro context.
     *
     * É necessária a checagem de tipo utilizando o <b>instanceof</b> na hora da atribuição, pois
     * assim garante que realmente a referência através do parâmetro é do tipo do
     * OnDateSelectedListener.
     * */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        /**
         * Checagem de tipo
         * */
        if(context instanceof OnDateSelectedListener) {

            /**
             * Atribuição de typecast do listener.
             * */
            mActivityListener = (OnDateSelectedListener) context;
        } else {

            Log.e("Erro:", String.format("Class %s does not implements OnDateSelectedListener",
                    context.getClass().getName()));

            throw new IllegalArgumentException(
                    String.format("Class %s does not implements OnDateSelectedListener",
                            context.getClass().getName())
            );
        }
    }

    /**
     * OnDateSelectedListener.java
     *
     * Inner Interface que define um listener para quando o usuário selecionar uma data.
     *
     * Toda View que precisar de um valor de DatePickerFragment, necessita implementar
     * esta interface.
     * */
    public interface OnDateSelectedListener {

        /**
         * Método callback chamado quando o usuário seleciona uma data.
         * */
        void onDateSelected(DatePicker datePicker);
    }
}
