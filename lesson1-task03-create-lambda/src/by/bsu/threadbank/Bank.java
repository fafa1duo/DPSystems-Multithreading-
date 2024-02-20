package by.bsu.threadbank;

import java.util.Arrays;
import java.util.logging.Logger;

public class Bank {

    private final double[] accounts;
    private final Logger logger = Logger.getLogger(Bank.class.getName());

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {
        if (accounts[from] < amount) return;

        logger.info(Thread.currentThread().toString());
        accounts[from] -= amount;
        logger.info(String.format(" %10.2f from %d to %d", amount, from, to));
        accounts[to] += amount;
        logger.info(String.format(" Total Balance: %10.2f%n", getTotalBalance()));
    }

    public double getTotalBalance() {
        double sum = 0;

        for (double a : accounts) {
            sum += a;
        }

        return sum;
    }

    public int size() {
        return accounts.length;
    }
}
