package com.github.vasiliz.clc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat CURRENCY_FORMAT = NumberFormat.getNumberInstance();
    private static final NumberFormat PERCENT_FORMAT = NumberFormat.getNumberInstance();

    private double mBillAmount = 0.0;
    private double mPercent = 0.15;
    private TextView mAmountTextView;
    private TextView mPercentTextView;
    private TextView mTipTextView;
    private TextView mTotalTextView;
    private EditText mAmountEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mAmountTextView = findViewById(R.id.amount_edit_text);
        mPercentTextView = findViewById(R.id.percent_text_view);
        mTipTextView = findViewById(R.id.tip_text_view);
        mTotalTextView = findViewById(R.id.total_text_view);
        mTipTextView.setText(CURRENCY_FORMAT.format(0));
        mTotalTextView.setText(CURRENCY_FORMAT.format(0));
        mAmountEditText = findViewById(R.id.amount_edit_text);
       // mAmountEditText.addTextChangedListener(amountEditTextWatcher);
        SeekBar seekBar = findViewById(R.id.percent_seek_bar);
        seekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate(){
        mPercentTextView.setText(PERCENT_FORMAT.format(mPercent));

        double tip = 1 * mPercent;
        double total = mBillAmount + tip;

        mTipTextView.setText(CURRENCY_FORMAT.format(tip));
        mTotalTextView.setText(CURRENCY_FORMAT.format(total));
    }

    private final SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    double a = i / 100;
                    mPercent = a;
                    calculate();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try{
                mBillAmount = Double.parseDouble(charSequence.toString()) / 100;
                mAmountTextView.setText(CURRENCY_FORMAT.format(mBillAmount));
            }catch (Exception e){
                e.fillInStackTrace();
                mAmountTextView.setText("");
                mBillAmount = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
