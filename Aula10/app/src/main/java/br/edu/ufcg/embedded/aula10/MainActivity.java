package br.edu.ufcg.embedded.aula10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import br.edu.ufcg.embedded.aula10.adapter.ContactAdapter;
import br.edu.ufcg.embedded.aula10.api.ApiManager;
import br.edu.ufcg.embedded.aula10.api.GsonRequest;
import br.edu.ufcg.embedded.aula10.model.Contact;
import br.edu.ufcg.embedded.aula10.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements RequestQueue.RequestFinishedListener {

    public static final String USER_BUNDLE_KEY = "user";

    @BindView(R.id.contacts_list)
    RecyclerView mContactRecyclerView;

    @BindView(R.id.login_progress)
    ProgressBar mProgress;

    @BindView(R.id.no_contact_text)
    TextView mNoContactText;

    RequestQueue requestQueue;
    private LinearLayoutManager mLayoutManager;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mContactRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mContactRecyclerView.setLayoutManager(mLayoutManager);

        requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();

        if(extras != null && !extras.isEmpty()) {

            user = (User) extras.getSerializable(USER_BUNDLE_KEY);

            searchContacts(user);
        } else {
            user = SessionManager.getInstance(this).getUserSession();
        }
    }


    private void searchContacts(User user) {

        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
        GsonRequest<ArrayList<Contact>> getContactsRequest = new GsonRequest<ArrayList<Contact>>(
                ApiManager.getInstance().getGetContacts(user.getId()),
                type,
                null,
                new Response.Listener<ArrayList<Contact>>() {
                    @Override
                    public void onResponse(ArrayList<Contact> response) {
                        Log.d("json", response.toString());

                        if(response.size() > 0) {
                            updateContactList(response);
                            mNoContactText.setVisibility(View.GONE);
                        } else {
                            mNoContactText.setVisibility(View.VISIBLE);
                        }

                        hideProgress();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("json", "Deu aguia " + error.getLocalizedMessage());
                    }
                }
        );

        showProgress();
        requestQueue.add(getContactsRequest).setTag("contacts");
    }

    @Override
    protected void onResume() {
        super.onResume();

        user = SessionManager.getInstance(this).getUserSession();

        searchContacts(user);
    }

    private void updateContactList(ArrayList<Contact> response) {

        ContactAdapter contactAdapter = new ContactAdapter(this, response);
        mContactRecyclerView.setAdapter(contactAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sair:

                SessionManager.getInstance(this).stopUserSession();

                startActivity(new Intent(this, LoginActivity.class));
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @OnClick(R.id.btn_add_contact)
    public void addContact(View view) {
        startActivity(new Intent(this, ContactsActivity.class));
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(requestQueue != null) {
            requestQueue.cancelAll("contacts");
        }
    }

    private void hideProgress() {
        mProgress.setVisibility(View.GONE);
        mContactRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestFinished(Request request) {
        hideProgress();
    }


}
