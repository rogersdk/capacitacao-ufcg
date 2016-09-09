package com.capacitacao.embedded.aula03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;

/**
 * MainActivity.java
 *
 * É do tipo OnDateSelectedListener (interface criada dentro do DatePickerFragment) para notificar
 * a esta classe quando uma data for selecionada e assim passar para o UserFormFragment o valor se-
 * lecionado, tendo em vista que ele é o responsável pela sua view.
 *
 * É do tipo OnUserSavedListener, pois ao clicar no botão salvar que está presente no UserFormFragment,
 * ele irá disparar um evento que notificará essa activity através do seu método callback
 * onUserSaved(UserModel user).
 *
 * Created by rogerio on 18/08/16.
 */
public class MainActivity extends AppCompatActivity
        implements DatePickerFragment.OnDateSelectedListener, UserFormFragment.OnUserSavedListener {

    private static final String FRAGMENT_USER_EDIT = "fragment_form";
    private static final String FRAGMENT_USER_DETAIL = "fragment_detail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /**
         * Adicionando o fragment do formulário programaticamente
         *
         * */
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_edit_user,
                UserFormFragment.newInstance(), FRAGMENT_USER_EDIT).commit();

        /**
         * Se o dispositivo for um tablet (layout-large-land), adicionar o fragment do
         * */
        if(isTablet()) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail_user,
                    UserDetailFragment.newInstance(), FRAGMENT_USER_DETAIL).commit();
        }

    }

    /**
     * Este método testa se o dispositivo é um Tablet na orientação landscape (layout-large-land)
     *
     * Método que retorna o valor inserido no item do arquivo res/values/bool.xml. Lembrando que
     * o valor muda de acordo com as configurações do dispositivo.
     * */
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
     * Método callback da interface OnUserSavedListener, chamado para atualizar o UserDetailFragment
     * que também implementa a mesma interface e assim disparar o evento para a modificação dos dados
     * de acordo com o UserModel do parâmetro.
     * */
    @Override
    public void onUserSaved(UserModel user) {

        /**
         * Testa se é um Tablet (layout-large-land), pega a instância do Fragmente de Detalhe, que é
         * um Objeto do tipo OnUserSavedListener e chama seu método passando como referência um
         * UserModel
         * */
        if(isTablet()) {
            UserFormFragment.OnUserSavedListener callback =
                    (UserFormFragment.OnUserSavedListener)getSupportFragmentManager()
                            .findFragmentByTag(FRAGMENT_USER_DETAIL);

            // dispara o evento para o UserDetailFragment
            callback.onUserSaved(user);
        } else {
            /**
             * Se não for Tablet na orientação landscape (layout-large-land) e for Smartphone
             * ou tablet na orientação Portrait mostra o fragment de detalhe
             * */

            UserDetailFragment fragment = UserDetailFragment.newInstance(user);
            /**
             * Adiciona o Fragment de Detalhe e exibe as informaçoes do usuario, adicionando
             * o Fragment do Formulario ao Back Stack.
             * */
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_edit_user, fragment)
                    .addToBackStack(null)
                    .commit();



        }




    }
}
