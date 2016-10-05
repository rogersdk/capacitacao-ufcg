package br.edu.ufcg.embedded.aula10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.api.ApiManager;
import br.edu.ufcg.embedded.aula10.api.GsonPostRequest;
import br.edu.ufcg.embedded.aula10.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements RequestQueue.RequestFinishedListener {

    @BindView(R.id.login_email_layout)
    TextInputLayout mEmail;

    @BindView(R.id.login_password_layout)
    TextInputLayout mPassword;

    @BindView(R.id.login_progress)
    ProgressBar mProgress;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
        ButterKnife.bind(this);

        if(SessionManager.getInstance(this).isUserLogged()) {
            redirectToMain(SessionManager.getInstance(this).getUserSession());
        }

        requestQueue = Volley.newRequestQueue(this);
    }

    @OnClick(R.id.btn_login)
    public void onBtnLoginClicked(final View v) {

        String email = mEmail.getEditText().getText().toString();
        String password = mPassword.getEditText().getText().toString();

        if(email.trim().isEmpty()) {
            mEmail.setErrorEnabled(false);
            mEmail.getEditText().setError("Insira um email válido");

            return;
        }

        if(password.trim().isEmpty()) {
            mPassword.setErrorEnabled(false);
            mPassword.getEditText().setError("Insira um password válido");

            return;
        }

        showProgress();

        User user = new User(email, password);

        Map<String, String> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());

        GsonPostRequest<User> post = new GsonPostRequest<>(
                ApiManager.getInstance().getLoginResource(),
                User.class,
                params,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        if(response != null) {
                            redirectToMain(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        hideProgress();
                    }
                }
        );

        post.setTag("login");
        requestQueue.add(post);

        requestQueue.addRequestFinishedListener(this);
    }

    public void redirectToMain(User user) {
        Bundle args = new Bundle();
        args.putSerializable(MainActivity.USER_BUNDLE_KEY, user);

        Intent i = new Intent(this, MainActivity.class);
        i.putExtras(args);

        SessionManager.getInstance(this).startUserSession(user);

        startActivity(i);
        finish();
    }

    public void saveUserSession(User user) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("session", true);
        editor.commit();

    }

    private void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(requestQueue != null) {
            requestQueue.cancelAll("login");
        }
    }

    @Override
    public void onRequestFinished(Request request) {
        hideProgress();
    }
}
