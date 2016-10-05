package br.edu.ufcg.embedded.aula10;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.api.ApiManager;
import br.edu.ufcg.embedded.aula10.api.GsonPostRequest;
import br.edu.ufcg.embedded.aula10.model.Contact;
import br.edu.ufcg.embedded.aula10.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsActivity extends AppCompatActivity {

    @BindView(R.id.contact_name_layout)
    TextInputLayout mName;

    @BindView(R.id.contact_last_name_layout)
    TextInputLayout mLastName;

    @BindView(R.id.contact_phone_layout)
    TextInputLayout mPhone;

    @BindView(R.id.contact_email_layout)
    TextInputLayout mEmail;

    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);

    }

    @OnClick(R.id.btn_submit_contact)
    public void submitContact(View view) {
        String name = mName.getEditText().getText().toString();
        String lastName = mLastName.getEditText().getText().toString();
        String email = mEmail.getEditText().getText().toString();
        String phone = mPhone.getEditText().getText().toString();

        if(email.trim().isEmpty()) {
            mName.setErrorEnabled(false);
            mName.setError("Nome obrigatório");

            return;
        }

        if(email.trim().isEmpty()) {
            mEmail.setErrorEnabled(false);
            mEmail.setError("Email obrigatório");

            return;
        }

        if(phone.trim().isEmpty()) {
            mPhone.setErrorEnabled(false);
            mPhone.setError("Email obrigatório");

            return;
        }


        User user = SessionManager.getInstance(this).getUserSession();



        Map<String, String> params = new HashMap<>();

        params.put("email", email);
        params.put("phone", phone);
        params.put("userId", user.getId());
        params.put("name", name);
        params.put("lastName", lastName);

        GsonPostRequest<Contact> post = new GsonPostRequest<>(
                ApiManager.getInstance().getAddContactResource(),
                Contact.class,
                params,
                new Response.Listener<Contact>() {
                    @Override
                    public void onResponse(Contact response) {
                        if(response != null) {
                            Log.d("json", response.toString());
//                            redirectToMain(response);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactsActivity.this,
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("json", error.getMessage());
//                        hideProgress();
                    }
                }
        );

        post.setTag("addContact");
        requestQueue.add(post);


    }

    @Override
    protected void onStop() {
        super.onStop();

        if(requestQueue != null) {
            requestQueue.cancelAll("addContact");
        }
    }
}
