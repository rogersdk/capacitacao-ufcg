package br.com.rogersdk.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OldMainActivity extends AppCompatActivity {

    @BindView(R.id.bill)
    EditText mBillEditText;

    @BindView(R.id.tip_percentage)
    SeekBar mTipPercentageSeekBar;

    @BindView(R.id.tip_text)
    TextView mTipPercentageText;

    @BindView(R.id.total_bill)
    Button mTotalButton;

    @BindView(R.id.tip_bill)
    Button mTipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

/*
        R1 Calcular o valor total da conta, incluindo gorjeta.
                R2 Calcular valor da gorjeta baseado em uma porcentagem pré-definida, variando de 10% à 30%;
        R3 Dividir o valor total de acordo com o número de integrantes;
        R5 O sistema deverá mostrar o valor da conta, mais o valor da gorjeta, e o valor total será o somoatório da gorjeta mais o da conta.
        R7 Havera um botao para resetar os valores inseridos.
*/


                mTipPercentageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d("seek", ""+i);
                mTipPercentageText.setText(String.format("%d%%", i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        mTipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBillEditText.getText().toString().equals("")) {
                    Toast.makeText(view.getContext(), "Nenhum valor inserido", Toast.LENGTH_SHORT).show();
                    return;
                }

                double value = Double.parseDouble(mBillEditText.getText().toString());
                int tipPercentage = mTipPercentageSeekBar.getProgress();
                mTipButton.setText(String.format("R$ %.2f", tipPercentage * 0.01 * value ));
            }
        });

        mTotalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mBillEditText.getText().toString().equals("")) {
                    Toast.makeText(view.getContext(), "Nenhum valor inserido", Toast.LENGTH_SHORT).show();
                    return;
                }

                double  total = 0.0,
                        value = Double.parseDouble(mBillEditText.getText().toString());
                int tipPercentage = mTipPercentageSeekBar.getProgress();



                total+= value + (tipPercentage * 0.01 * value);

                mTotalButton.setText(String.format("R$ %.2f", total));
                mTipButton.setText(String.format("R$ %.2f",tipPercentage * 0.01 * value));
            }
        });
    }
}
