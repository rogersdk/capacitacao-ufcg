/*******************************************************************
 * Copyright (C) 2014 DL.                                           *
 * All rights, including trade secret rights, reserved.             *
 *******************************************************************/

package embedded.capacitacao.com.aula05;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by rogerio on 14/09/16.
 */
public class MainActivity extends AppCompatActivity {

    private Button mSharedPrefBtn, mStorageBtn, mSQLiteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mSharedPrefBtn = (Button) findViewById(R.id.btn_shared_preference);
        mStorageBtn = (Button) findViewById(R.id.btn_storage);
        mSQLiteBtn = (Button) findViewById(R.id.btn_sqlite);


        mSharedPrefBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SharedPreferencesActivity.class));
            }
        });

        mStorageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), StorageActivity.class));
            }
        });

        mSQLiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SQLiteActivity.class));
            }
        });

    }
}
