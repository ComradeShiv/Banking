package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
        this.name = name;
        this.balance = balance;
        this.minBalance = minBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(sum < digits || sum > 9*digits) throw new AccountNumberCanNotBeGenerated("Account Number can not be generated");

        Random random = new Random();
        ArrayList<String> numberArray = new ArrayList<>();

        for(int i = 0; i < digits-1; i++) {
            int possibleDigit = random.nextInt(10);

            while(sum - possibleDigit < digits - i - 1 || sum - possibleDigit > 9 * (digits - i - 1))
                possibleDigit = random.nextInt(10);

            sum -= possibleDigit;
            numberArray.add(String.valueOf(possibleDigit));
        }

        numberArray.add(String.valueOf(sum));

        Collections.shuffle(numberArray);

        String accountNumber = "";
        for(String possibleDigit: numberArray)
            accountNumber = possibleDigit + accountNumber;

        return accountNumber;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance += amount;
        System.out.println("Amount Deposited");
    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        try{
            if(balance < minBalance+amount)
                throw new InsufficientBalance("Insufficient Balance");

            this.balance -= amount;
            System.out.println("Successful Withdrawal");
        } catch (InsufficientBalance e){
            System.out.println(e.getMessage());
        }
    }

}