package com.capacitacao.embedded.aula03;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * UserListActivity.java
 *
 * Activity criada para implementar um listview com o recyclerview
 *
 * Created by rogerio on 20/08/16.
 */
public class UserListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private UserListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_user);

        mRecyclerView = (RecyclerView) findViewById(R.id.list_item_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // the dataset
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel("Derp", "derp@embedded.ufcg.edu.br"));
        users.add(new UserModel("Herp", "herp@embedded.ufcg.edu.br"));
        users.add(new UserModel("Derpina", "derpina@embedded.ufcg.edu.br"));
        users.add(new UserModel("Herpina", "herpina@embedded.ufcg.edu.br"));


        // specify an adapter (see also next example)
        mAdapter = new UserListAdapter(users);
        mRecyclerView.setAdapter(mAdapter);

    }

}
