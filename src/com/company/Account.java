package com.company;

/**
 * Created by andy on 13.12.2017.
 */
public class Account {

    private static int currentID;
    private static boolean isFirst;
    private int balance;
    private int userID;
    private int id;


    public Account(int balance, int userID) {
        if (!isFirst) {
            currentID = 0;
        }
        this.isFirst = true;
        this.balance = balance;
        this.userID = userID;
        increment();
        this.id = currentID;
    }

    private void increment() {
        currentID++;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountId(){
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public void balanceTake(int amount){
        balance -= amount;
    }

    public void balancePut(int amount){
        balance += amount;
    }

}
