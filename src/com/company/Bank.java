package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Bank {

    public static Map<Integer, User> userMap;
    private List<Account> accountList;
    private Thread thread;

    public Bank() {
        userMap = new HashMap<>();
        accountList = new ArrayList<>();
    }

    //Creating method transferMoney
    static public TxResult transferMoney(Account src, Account dest, int amount) {
        Thread thread;
        TxResult result = TxResult.WRONG_CONDITIONS;

        if (src == dest || amount < 0) {
            thread = new Thread(new MailerThread(src, dest, amount, result));
            thread.start();
            return result;
        }
        if (src.getBalance() >= amount) {
            synchronized (src) {
                synchronized (dest) {
                    result = TxResult.SUCCESS;
                    src.balanceTake(amount);
                    dest.balancePut(amount);
                    thread = new Thread(new MailerThread(src, dest, amount, result));
                    thread.start();
                    return result;
                }
            }
        }
        if (src.getBalance() < amount) {
            result = TxResult.NOT_ENOUGH;
            thread = new Thread(new MailerThread(src, dest, amount, result));
            thread.start();
            return result;
        }
        return result;
    }
    //Creating Main method
    public static void main(String[] args) {

        Bank bank = new Bank();
        //Creating Users
        User user1 = new User("Fedor");
        User user2 = new User("Konstantin");
        User user3 = new User("Egor");

        //Puting Users to the HashMap
        bank.userMap.put(user1.getId(), user1);
        bank.userMap.put(user2.getId(), user2);
        bank.userMap.put(user3.getId(), user3);

        //Puting Accounts to our List
        bank.accountList.add(new Account(7000, user1.getId()));
        bank.accountList.add(new Account(8000, user2.getId()));
        bank.accountList.add(new Account(9000, user3.getId()));

        //Printing Accounts before the transactins
        System.out.println("==============");
        for (Account account : bank.accountList) {
            System.out.println(bank.userMap.get(account.getUserID()).getName() + ": " + account.getBalance());
        }
        System.out.println("==============");

        try {
            Thread thread1 = new Thread(new BankThread(bank.accountList.get(0), bank.accountList.get(1), 40000));
            thread1.start();
//            bank.thread.join();
            Thread thread2 = new Thread(new BankThread(bank.accountList.get(1), bank.accountList.get(0), 2000));
            thread2.start();
//            bank.thread.join();
            Thread thread3 = new Thread(new BankThread(bank.accountList.get(0), bank.accountList.get(0), 888));
            thread3.start();
//            bank.thread.join();
            Thread thread4 = new Thread(new BankThread(bank.accountList.get(0), bank.accountList.get(2), -10));
            thread4.start();
//            bank.thread.join();
            Thread thread5 = new Thread(new BankThread(bank.accountList.get(2), bank.accountList.get(1), 3111));
            thread5.start();
//            bank.thread.join();
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Printing Accounts after the transactions
        try {
            Thread.currentThread().join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==============");

        for (Account account : bank.accountList) {
            System.out.println(bank.userMap.get(account.getUserID()).getName() + ": " + account.getBalance());
        }
        System.out.println("==============");
    }
}

