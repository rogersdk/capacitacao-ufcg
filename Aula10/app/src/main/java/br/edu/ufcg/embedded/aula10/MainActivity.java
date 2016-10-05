package br.edu.ufcg.embedded.aula10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String USER_BUNDLE_KEY = "user";

    @BindView(R.id.contacts_list)
    RecyclerView mContactRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null && !extras.isEmpty()) {
            Log.d(USER_BUNDLE_KEY, extras.getSerializable("user").toString());
        }

    }

    @OnClick(R.id.btn_add_contact)
    public void addContact(View view) {
        startActivity(new Intent(this, ContactsActivity.class));
    }

}
