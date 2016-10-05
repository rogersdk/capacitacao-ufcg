package br.edu.ufcg.embedded.aula10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import br.edu.ufcg.embedded.aula10.models.Contact;
import br.edu.ufcg.embedded.aula10.models.User;
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

        // Get user
        Type type = new TypeToken<User>() {}.getType();
        GsonRequest<User> user = new GsonRequest<User>(
                ApiManager.getInstance().getUser("57f25bb45d453b257b2d0f9e"),
                type,
                null,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User response) {
                        Log.d("json", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("json", "Deu aguia " + error.getLocalizedMessage());
                    }
                }
        );


        // Return contacts
        /*Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
        GsonRequest<ArrayList<Contact>> user = new GsonRequest<ArrayList<Contact>>(
                ApiManager.getInstance().getGetContacts("57f25bb45d453b257b2d0f9e"),
                type,
                null,
                new Response.Listener<ArrayList<Contact>>() {
                    @Override
                    public void onResponse(ArrayList<Contact> response) {
                        Log.d("json", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("json", "Deu aguia " + error.getLocalizedMessage());
                    }
                }
        );*/

        Volley.newRequestQueue(this).add(user);

        /*startActivity(new Intent(this, MainActivity.class));
        finish();*/


        


    }
}
