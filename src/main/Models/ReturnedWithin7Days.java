package main.Models;


/**
 * Calculates a transaction cost of 0$
 * Strategy Pattern Concrete Operation
 *
 * @author Alanna Morris
 */

public class ReturnedWithin7Days implements TransactionCalculator{
    @Override
    public int calculateCost() {
        return 0;
    }
}
