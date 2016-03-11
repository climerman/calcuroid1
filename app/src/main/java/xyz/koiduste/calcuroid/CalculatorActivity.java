package xyz.koiduste.calcuroid;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.koiduste.calcuroid.databinding.ActivityCalculatorBinding;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCalcInputDisplay;
    private TextView mCalcOutputDisplay;
    private CalculatorService mCalcService;
    private ActivityCalculatorBinding binding;

    private final static String DIGITS = "0123456789.";
    private final static String OPERATORS = "+-*/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calculator);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);


        //Service & Display
        mCalcService = new CalculatorServiceImpl();
        binding.setCalcService((CalculatorServiceImpl) mCalcService);
        mCalcInputDisplay = binding.textInput;
        mCalcOutputDisplay = binding.textOutput;
       // mCalcInputDisplay = (TextView) findViewById(R.id.textInput);
        //mCalcOutputDisplay = (TextView) findViewById(R.id.textOutput);

        //Buttons
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonDelete).setOnClickListener(this);
        findViewById(R.id.buttonBrackets).setOnClickListener(this);
        findViewById(R.id.buttonComma).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String key = ((Button) v).getText().toString();
        if (DIGITS.contains(key)) {
            binding.getCalcService().operandText.set(mCalcInputDisplay.getText()+key);
            if (key.equals(".")) ((Button)findViewById(R.id.buttonComma)).setEnabled(false);
        } else {
            mCalcService.opAction(key);
            ((Button)findViewById(R.id.buttonComma)).setEnabled(true); //TODO Replace with boolean property, if possible
        }
    }
}
