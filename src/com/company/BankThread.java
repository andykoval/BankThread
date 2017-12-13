package com.company;

/**
 * Created by andy on 13.12.2017.
 */
class BankThread implements Runnable {
    private Account src;
    private Account dest;
    private int amount;

    public BankThread(Account src, Account dest, int amount) {
        this.src = src;
        this.dest = dest;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            Bank.transferMoney(src, dest, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
