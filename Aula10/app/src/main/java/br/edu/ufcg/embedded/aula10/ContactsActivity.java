package br.edu.ufcg.embedded.aula10;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import br.edu.ufcg.embedded.aula10.api.ApiManager;
import br.edu.ufcg.embedded.aula10.api.GsonPostRequest;
import br.edu.ufcg.embedded.aula10.api.StringApiResponse;
import br.edu.ufcg.embedded.aula10.model.Contact;
import br.edu.ufcg.embedded.aula10.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ContactsActivity.java
 *
 * Activity que serve tanto para adicionar um novo contato quanto para editar um já existente.
 *
 * */
public class ContactsActivity extends AppCompatActivity {

    public static final String CONTACT_BUNDLE_KEY = "contact";
    @BindView(R.id.contact_name_layout)
    TextInputLayout mName;

    @BindView(R.id.contact_last_name_layout)
    TextInputLayout mLastName;

    @BindView(R.id.contact_phone_layout)
    TextInputLayout mPhone;

    @BindView(R.id.contact_email_layout)
    TextInputLayout mEmail;

    @BindView(R.id.btn_submit_contact)
    Button mSubmit;

    @BindView(R.id.btn_submit_edit_contact)
    Button mSubmitEdit;

    RequestQueue requestQueue;
    private Contact selectedContact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);

        Bundle args = getIntent().getExtras();

        /**
         * Fluxo que vem do clique em um item na Main Activity
         * */
        if(args != null && !args.isEmpty()) {
            selectedContact = (Contact) args.getSerializable(CONTACT_BUNDLE_KEY);

            mName.getEditText().setText(selectedContact.getName());
            mLastName.getEditText().setText(selectedContact.getLastName());
            mEmail.getEditText().setText(selectedContact.getEmail());
            mPhone.getEditText().setText(selectedContact.getPhone());

            disableFields();

            mSubmit.setVisibility(View.GONE);
        }


    }

    /**
     * Desabilita os campos do contato
     * */
    private void disableFields() {
        mName.getEditText().setFocusableInTouchMode(false);
        mName.getEditText().setFocusable(false);
        mLastName.getEditText().setFocusableInTouchMode(false);
        mLastName.getEditText().setFocusable(false);
        mEmail.getEditText().setFocusableInTouchMode(false);
        mEmail.getEditText().setFocusable(false);
        mPhone.getEditText().setFocusableInTouchMode(false);
        mPhone.getEditText().setFocusable(false);

        mSubmitEdit.setVisibility(View.GONE);
    }

    /**
     * Habilita os campos do contato
     * */
    private void enableFields() {
        mName.getEditText().setFocusableInTouchMode(true);
        mLastName.getEditText().setFocusableInTouchMode(true);
        mEmail.getEditText().setFocusableInTouchMode(true);
        mPhone.getEditText().setFocusableInTouchMode(true);
        mName.getEditText().requestFocus();

        mSubmitEdit.setVisibility(View.VISIBLE);
    }

    /**
     * Função responsável pelo POST de um novo Contato
     *
     * Verifica se os campos estão preenchidos e então executa a requisicao para o serviço
     * */
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

    /**
     * Função responsável pelo POST de alteração de um Contato
     *
     * Verifica se os campos estão preenchidos e então executa a requisicao para o serviço
     *
     * OBS: É necessário inserir um novo parâmetro na requisição, _id responsável pela referência
     * do objeto no servidor.
     * */
    @OnClick(R.id.btn_submit_edit_contact)
    public void submitEditContact(final View view) {
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

        if(selectedContact != null) {
            params.put("_id", selectedContact.getId());
            params.put("email", email);
            params.put("phone", phone);
            params.put("userId", user.getId());
            params.put("name", name);
            params.put("lastName", lastName);

            GsonPostRequest<StringApiResponse> post = new GsonPostRequest<>(
                    ApiManager.getInstance().getEditContactResource(),
                    StringApiResponse.class,
                    params,
                    new Response.Listener<StringApiResponse>() {
                        @Override
                        public void onResponse(StringApiResponse response) {
                            if(response != null) {
                                Toast.makeText(view.getContext(),
                                        response.toString(), Toast.LENGTH_SHORT).show();
                                disableFields();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ContactsActivity.this,
                                    error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            );

            post.setTag("addContact");
            requestQueue.add(post);

        }
    }

    /**
     * Ativa o menu na ActionBar da Activity
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(selectedContact != null) {
            getMenuInflater().inflate(R.menu.contact_menu, menu);
        }

        return true;
    }

    /**
     * Callback do evento de em um item do Menu
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_editar:
                //TODO Request que atualiza contato.
                    enableFields();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(requestQueue != null) {
            requestQueue.cancelAll("addContact");
        }
    }
}
