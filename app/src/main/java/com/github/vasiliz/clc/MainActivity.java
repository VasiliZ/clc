package com.github.vasiliz.clc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private double mBillAmount;
    private double mPercent = 15;
    private TextView mAmountTextView;
    private TextView mPercentTextView;
    private TextView mTipTextView;
    private TextView mTotalTextView;
    private EditText mAmountEditText;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mAmountTextView = findViewById(R.id.amount_edit_text);
        mPercentTextView = findViewById(R.id.percent_text_view);
        mTipTextView = findViewById(R.id.tip_text_view);
        mTotalTextView = findViewById(R.id.total_text_view);
        mTipTextView.setText("0");
        mTotalTextView.setText("0");
        mAmountEditText = findViewById(R.id.amount_edit_text);
        mAmountEditText.addTextChangedListener(amountEditTextWatcher);
        final SeekBar seekBar = findViewById(R.id.percent_seek_bar);
        seekBar.setProgress((int) mPercent);
        seekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate() {
        mPercentTextView.setText(mPercent + "%");

        final double tip = mBillAmount * mPercent / 100;
        final double total = mBillAmount + tip;


        mTipTextView.setText(String.valueOf(tip));
        mTotalTextView.setText(String.valueOf(total));
    }

    private String TAG = "log";
    private final SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {

                int progress;

                @Override
                public void onProgressChanged(final SeekBar seekBar, final int i, final boolean b) {
                    progress = i;
                }

                @Override
                public void onStartTrackingTouch(final SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(final SeekBar seekBar) {
                    mPercent = (double) progress;
                    calculate();

                }
            };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {

        }

        @Override
        public void onTextChanged(final CharSequence charSequence, final int i, final int i1, final int i2) {
            try {
                mBillAmount = Double.parseDouble(charSequence.toString());
            } catch (final Exception e) {
                e.fillInStackTrace();
                mAmountTextView.setText("");
                mBillAmount = 0.0;
            }

            calculate();
        }

        @Override
        public void afterTextChanged(final Editable editable) {

        }
    };
}
