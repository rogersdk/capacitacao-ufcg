package embedded.capacitacao.com.aula05;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import embedded.capacitacao.com.aula05.database.UserContract;
import embedded.capacitacao.com.aula05.database.Aula05DbHelper;
import embedded.capacitacao.com.aula05.database.UserDataSource;

/**
 * Created by rogerio on 16/09/16.
 */
public class AddUserActivity extends AppCompatActivity {

    private EditText mName, mEmail, mPassword;
    private Button mBtnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_user);

        mName = (EditText) findViewById(R.id.form_add_user_name);
        mEmail = (EditText) findViewById(R.id.form_add_user_email);
        mPassword = (EditText) findViewById(R.id.form_add_user_password);
        mBtnSave = (Button) findViewById(R.id.btn_save);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // instancia o datasoure de user
                UserDataSource dataSource = new UserDataSource(v.getContext());

                // chama a funcao de adicionar novo user do datasource
                long result = dataSource.addNewUser(mName.getText().toString(), mEmail.getText().toString(),
                        mPassword.getText().toString());

                // se @result for -1 quer dizer que aconteceu algum erro
                if(result > 0) {
                    finish();
                } else {
                    Log.e("Error", "Error 1: Erro ao inserir usuário.");
                }


            }
        });
    }
}
