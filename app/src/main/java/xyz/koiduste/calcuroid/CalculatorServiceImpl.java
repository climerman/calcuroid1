package xyz.koiduste.calcuroid;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;


/**
 * Created by marko on 3/10/16.
 */
public class CalculatorServiceImpl extends MathServiceImpl implements CalculatorService {

    private final static String ADD = "+";
    private final static String SUBTRACT = "-";
    private final static String MULTIPLY = "*";
    private final static String DIVIDE = "/";
    private final static String EQUALS = "=";
    private final static String CLEAR = "C";
    private final static String BRACKETS ="( )";
    private final static String DELETE = "<-";

    private String waitingOperator = "";

    public final ObservableField<String> operandText = new ObservableField<>("");
    public final ObservableField<String> resultText = new ObservableField<>("");
    public final ObservableBoolean commaEnabled = new ObservableBoolean(true);

    //region Getters and Setters


    //endregion

    @Override
    public double getOperand() {
        if (this.operandText.get().equals("")) {
            return 0;
        } else return Double.parseDouble(this.operandText.get());
        //TODO! Error handling

    }

    @Override
    public double getResult() {
        if (this.resultText.get().equals("")) {
            return 0;
        } else return Double.parseDouble(this.resultText.get());
       // TODO! Error handling

    }

    @Override
    public void calcOperation(String operator) {
        switch(operator) {
            case ADD:
                resultText.set(String.valueOf(add(getResult(), getOperand())));
                break;
            case SUBTRACT:
                resultText.set(String.valueOf(subtract(getResult(), getOperand())));
                break;
            case MULTIPLY:
                resultText.set(String.valueOf(multiply(getResult(), getOperand())));
                break;
            case DIVIDE:
                resultText.set(String.valueOf(divide(getResult(), getOperand())));
                break;
            case EQUALS:
                break;
            case CLEAR:
                //TODO Clear logic
                break;
            case BRACKETS:
                //TODO Brackets logic
                break;
            case DELETE:
                //TODO Delete logic
                break;
            case "":
                resultText.set(operandText.get());
                break;
            default:
                //TODO Error handling
                break;
        }
    }

    @Override
    public void opAction(String key) {
        calcOperation(waitingOperator);
        operandText.set("");
        waitingOperator=key;
    }
}