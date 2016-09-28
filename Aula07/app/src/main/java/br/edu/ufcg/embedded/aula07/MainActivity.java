package br.edu.ufcg.embedded.aula07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mLocationAndMap, mGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationAndMap = (Button) findViewById(R.id.btn_location_and_map);
        mGoogleSignIn = (Button) findViewById(R.id.btn_google_sign_in);

        mLocationAndMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LocationManagerActivity.class));
            }
        });

        mGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), GoogleSignInActivity.class));
            }
        });

    }
}
