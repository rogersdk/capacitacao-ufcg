package br.edu.ufcg.embedded.aula10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText mEmail;

    @BindView(R.id.login_password)
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked(View v) {
        
    }
}
