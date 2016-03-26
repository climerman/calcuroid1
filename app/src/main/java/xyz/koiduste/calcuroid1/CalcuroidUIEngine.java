package xyz.koiduste.calcuroid1;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import org.parceler.Parcel;

/**
 * Created by marko on 3/26/16.
 */
@Parcel
public class CalcuroidUIEngine implements UIEngine {

    private final static String DIGITS = "0123456789.";
    private final static String OPERATORS = "+-*/";
    private final static String ADD = "+";
    private final static String SUBTRACT = "-";
    private final static String MULTIPLY = "*";
    private final static String DIVIDE = "/";
    private final static String EQUALS = "=";
    private final static String CLEAR = "C";
    private final static String BRACKETS ="( )";
    private final static String DELETE = "<-";

    public final ObservableField<String> operandText = new ObservableField<String>("");
    public final ObservableField<String> resultText = new ObservableField<String>("");
    public final ObservableBoolean commaEnabled = new ObservableBoolean(true);

    public String waitingOperator = "";

    @Override
    public void readInput(String key) {
        if (DIGITS.contains(key)) {
            digitAction(key);
        } else if (OPERATORS.contains(key)) {
            opAction(key);
        } else {
            throw new RuntimeException("Faulty input");
        }
    }

    @Override
    public void opAction(String key) {
        commaEnabled.set(true);
        switch(key) {
            case ADD:
                //resultText.set(String.valueOf(add(getResult(), getOperand())));
                operandText.set("");
                break;
            case SUBTRACT:
                //resultText.set(String.valueOf(subtract(getResult(), getOperand())));
                operandText.set("");
                break;
            case MULTIPLY:
                //resultText.set(String.valueOf(multiply(getResult(), getOperand())));
                operandText.set("");
                break;
            case DIVIDE:
                //resultText.set(String.valueOf(divide(getResult(), getOperand())));
                operandText.set("");
                break;
            case EQUALS:
                //Calc operation
                operandText.set("");
                waitingOperator = "";
                break;
            case CLEAR:
                operandText.set("");
                resultText.set("");
                break;
            case DELETE:
                operandText.set("");
                break;
            case BRACKETS:
                break;
            case "":
                if (!operandText.get().isEmpty()) {
                    resultText.set(operandText.get());
                }
                operandText.set("");
                break;
            default:
                throw new RuntimeException("Tundmatu operaator!");
        }
    }

    @Override
    public void digitAction(String key) {
        operandText.set(operandText.get()+key);
        if (key.equals(".")) {
            commaEnabled.set(false);
        }
    }
}
