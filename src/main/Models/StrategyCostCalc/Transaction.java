package main.Models.StrategyCostCalc;


/**
 * Uses the TransactionCalculator to complete the cost calculation
 * Strategy Pattern Context Class
 *
 * @author Alanna Morris
 */

public class Transaction {
    private TransactionCalculator transactionCalculator;

    public Transaction(TransactionCalculator transactionCalculator) {
        this.transactionCalculator = transactionCalculator;
    }

    public int calculate() {
        return transactionCalculator.calculateCost();
    }

}
