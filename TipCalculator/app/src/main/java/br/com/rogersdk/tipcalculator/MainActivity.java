package br.com.rogersdk.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.consumido)
    EditText mConsumido;

    @BindView(R.id.porcentagem)
    AppCompatSeekBar mPorcentagem;

    @BindView(R.id.pessoas)
    EditText mPessoas;

    @BindView(R.id.total_por_pessoa)
    TextView mTotalPorPessoa;

    @BindView(R.id.total)
    TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        ButterKnife.bind(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.resetar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.resetar:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
