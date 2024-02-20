package by.bsu.threadbank;

import java.util.concurrent.ThreadLocalRandom;

public class ThreadTest {

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;

    public static void main(String[] args) {
        var bank = new Bank(4, 100000);
        Runnable task1 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * ThreadLocalRandom.current().nextDouble();
                    
                    bank.transfer(0, 1, amount);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(DELAY));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable task2 = () -> {
            try {
                for (int i = 0; i < STEPS; i++) {
                    double amount = MAX_AMOUNT * ThreadLocalRandom.current().nextDouble();
                    bank.transfer(2, 3, amount);
                    Thread.sleep(ThreadLocalRandom.current().nextInt(DELAY));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
