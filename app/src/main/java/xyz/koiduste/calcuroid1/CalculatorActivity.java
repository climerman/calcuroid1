package xyz.koiduste.calcuroid1;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels; //http://parceler.org/ - Helps me Parcel stuff.

import xyz.koiduste.calcuroid1.databinding.LayoutLandBinding;
import xyz.koiduste.calcuroid1.databinding.LayoutPortBinding;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mCalcInputDisplay;
    private TextView mCalcOutputDisplay;
    private CalcuroidUIEngine mUIEngine;
    private LayoutLandBinding landBinding;
    private LayoutPortBinding portBinding;

    private static final String SERVICE = "SERVICE";
    private static final String OPERAND = "OPERAND";
    private static final String RESULT = "RESULT";
    private static final String OPERATOR = "OPERATOR";
    private final static String OPERATORS = "+-*/=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            mUIEngine = new CalcuroidUIEngine();
        } else {
            mUIEngine = Parcels.unwrap(savedInstanceState.getParcelable(SERVICE));
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            portBinding = DataBindingUtil.setContentView(this, R.layout.layout_port);
            portBinding.setUIEngine((CalcuroidUIEngine)mUIEngine);
            mCalcInputDisplay = portBinding.textInput;
            mCalcOutputDisplay = portBinding.textOutput;
        } else {
            landBinding = DataBindingUtil.setContentView(this, R.layout.layout_land);
            landBinding.setUIEngine((CalcuroidUIEngine)mUIEngine);
            mCalcInputDisplay = landBinding.textInput;
            mCalcOutputDisplay = landBinding.textOutput;
        }

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
    } //onCreate

    @Override
    public void onClick(View v) {
        String key = ((Button) v).getText().toString();
        if (OPERATORS.contains(key)) {
            broadcastIntent(v);
        }
        mUIEngine.readInput(key);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SERVICE, Parcels.wrap(mUIEngine));
    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("xyz.koiduste.CALCULATE");
        intent.putExtra(RESULT, mUIEngine.resultText.get());
        intent.putExtra(OPERAND, mUIEngine.operandText.get());
        intent.putExtra(OPERATOR, mUIEngine.waitingOperator);
        //intent.setType("text/plain");
        sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mUIEngine.resultText.set(getResultData());
            }
        }, null, Activity.RESULT_OK, null ,null);
    }
}
