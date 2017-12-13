package com.company;

/**
 * Created by andy on 13.12.2017.
 */

class MailerThread implements Runnable {
    private Account src;
    private Account dest;
    private int amount;
    private TxResult result;

    public MailerThread(Account src, Account dest, int amount, TxResult result) {
        this.src = src;
        this.dest = dest;
        this.amount = amount;
        this.result = result;
    }

    @Override
    public void run() {
        System.out.print("Operation: ");
        switch (result) {
            case WRONG_CONDITIONS:
                System.out.println("Uncorect conditions");
                break;
            case NOT_ENOUGH:
                System.out.println("Not enough money on account(" + src.getAccountId() +"("+Bank.userMap.get(src.getUserID()).getName()+")"+")"+ ". Balannce is: " + src.getBalance()+ ". Amount is: " + amount);
                break;
            case SUCCESS:
                System.out.println("Success operation from account(" + src.getAccountId()+"("+Bank.userMap.get(src.getUserID()).getName()+")"+")"+
                        " to account(" + dest.getAccountId() +"("+Bank.userMap.get(dest.getUserID()).getName()+")"+")"+ ". Amount is: " + amount);
        }
    }
}

