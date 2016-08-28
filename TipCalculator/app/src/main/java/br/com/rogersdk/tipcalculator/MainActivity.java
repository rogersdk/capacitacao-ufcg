package br.com.rogersdk.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText mBillEditText;
    private SeekBar mTipPercentageSeekBar;
    private TextView mTipPercentageText;
    private Button mTotalButton;
    private Button mTipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBillEditText = (EditText) findViewById(R.id.bill);
        mTipPercentageSeekBar = (SeekBar) findViewById(R.id.tip_percentage);
        mTipPercentageText = (TextView) findViewById(R.id.tip_text);
        mTipButton = (Button) findViewById(R.id.tip_bill);
        mTotalButton = (Button) findViewById(R.id.total_bill);



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
