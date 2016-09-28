package br.edu.ufcg.embedded.aula08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionName = (TextView) findViewById(R.id.version_name);

        versionName.setText(BuildConfig.VERSION_NAME);


    }
}
