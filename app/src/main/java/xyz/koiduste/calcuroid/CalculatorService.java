package xyz.koiduste.calcuroid;

/**
 * Created by marko on 3/10/16.
 */
public interface CalculatorService {

    double getOperand();
    double getResult();
    void calcOperation(String waitingOperator);
    void opAction(String key);
    void digitAction(String key);
}
