package br.com.rogersdk.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.consumido)
    EditText mConsumido;

    @BindView(R.id.porcentagem)
    AppCompatSeekBar mPorcentagem;

    @BindView(R.id.pessoas)
    EditText mPessoas;

    @BindView(R.id.total_por_pessoa)
    TextView mTotalPorPessoa;

    @BindView(R.id.texto_porcentagem)
    TextView mTextoPorcentagem;

    @BindView(R.id.total)
    TextView mTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        ButterKnife.bind(this);

        mPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               setSeekBarTextValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });


        // TODO Adicionar um TextWatcher e formatar o campo do valor consumido "mConsumido" para Ex: 100,00
        // TODO O Campo "mConsumido" deve permitir a inserção de apenas 10 carecteres
        // TODO O Campo de "pessoas" deve possuir no maximo 3 caracteres
        // TODO O Campo de "pessoas" deve possuir um valor padrão de 1
        // TODO Adicionar um TextWatcher e formatar o campo do valor consumido "mTotalPorPessoa" para Ex: 100,00
        // TODO Adicionar um TextWatcher e formatar o campo do valor consumido "mTotal" para Ex: 100,00
    }

    private void setSeekBarTextValue(int progress) {
        mTextoPorcentagem.setText(String.format("%d%%", progress));
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
                resetarCampos();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Reseta os valores dos campos do formulário
     * */
    public void resetarCampos() {
        mConsumido.setText("");
        mPessoas.setText("");
        mPorcentagem.setProgress(10);
        mTotal.setText("");
        mTotalPorPessoa.setText("");
    }

    @OnClick(R.id.calcular_conta)
    public void calcular(View view) {
        Log.d("calcular", "Calculando valores...");

        try {
            Conta conta = new Conta(
                    mConsumido.getText().toString(),
                    mPorcentagem.getProgress(),
                    mPessoas.getText().toString()
            );

            mTotalPorPessoa.setText(String.valueOf(conta.calcularValorPorPessoa()));
            mTotal.setText(String.valueOf(conta.calcularValorTotal()));
        } catch (IllegalArgumentException e ) {
            Log.e("error", e.getMessage());
        }




    }

}
