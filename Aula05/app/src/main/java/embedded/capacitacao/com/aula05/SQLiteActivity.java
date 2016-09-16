package embedded.capacitacao.com.aula05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import embedded.capacitacao.com.aula05.database.UserDataSource;

/**
 * Created by rogerio on 16/09/16.
 */
public class SQLiteActivity extends AppCompatActivity {

    private Button mBtnAddNewUser;
    private TextView mUserList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sqlite);

        mUserList = (TextView) findViewById(R.id.user_list);

        mBtnAddNewUser = (Button) findViewById(R.id.btn_add_new_user);

        mBtnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), AddUserActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // instancia o datasource
        UserDataSource dataSource = new UserDataSource(this);

        // obtem a lista de User
        List<User> users = dataSource.getAll();

        if(users.size() > 0) {
            StringBuffer userList = new StringBuffer();
            userList.append("Lista de User\n");
            for(User u: users) {
                userList.append(String.format("->%s\n", u.getName()));
            }

            mUserList.setText(userList);
        }
    }
}
