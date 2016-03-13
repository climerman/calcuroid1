package xyz.koiduste.calcuroid;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels; //http://parceler.org/ - Helps me Parcel stuff.

import xyz.koiduste.calcuroid.databinding.LayoutLandBinding;
import xyz.koiduste.calcuroid.databinding.LayoutPortBinding;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCalcInputDisplay;
    private TextView mCalcOutputDisplay;
    private CalculatorService mCalcService;
    private LayoutLandBinding landBinding;
    private LayoutPortBinding portBinding;
    private DataBindingUtil binding;
    private final static String DIGITS = "0123456789.";
    private final static String OPERATORS = "+-*/";
    private final static String SERVICE = "SERVICE";
    private final static String INPUT = "INPUT";
    private final static String OUTPUT = "OUTPUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout_port.activity_calculator);

        if (savedInstanceState == null) {
            mCalcService = new CalculatorServiceImpl();
        } else {
            mCalcService = Parcels.unwrap(savedInstanceState.getParcelable(SERVICE));
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
        //    setContentView(R.layout.layout_port);
            portBinding = DataBindingUtil.setContentView(this, R.layout.layout_port);
            portBinding.setCalcService((CalculatorServiceImpl)mCalcService);
            mCalcInputDisplay = portBinding.textInput;
            mCalcOutputDisplay = portBinding.textOutput;
        } else {
        //    setContentView(R.layout.layout_land);
            landBinding = DataBindingUtil.setContentView(this, R.layout.layout_land);
            landBinding.setCalcService((CalculatorServiceImpl) mCalcService);
            mCalcInputDisplay = landBinding.textInput;
            mCalcOutputDisplay = landBinding.textOutput;
        }

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_calculator);

        //Service & Display
        //mCalcService = new CalculatorServiceImpl();
        //binding.setCalcService((CalculatorServiceImpl) mCalcService);
        //mCalcInputDisplay = binding.textInput;
        //mCalcOutputDisplay = binding.textOutput;
        //mCalcInputDisplay = (TextView) findViewById(R.id.textInput);
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
        //TODO! all key action logic to service
        if (DIGITS.contains(key)) {
            mCalcService.digitAction(key);
            //if (key.equals(".")) ((Button)findViewById(R.id.buttonComma)).setEnabled(false);
        } else {
            mCalcService.opAction(key);
            //((Button)findViewById(R.id.buttonComma)).setEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SERVICE, Parcels.wrap(mCalcService));
    }
}
