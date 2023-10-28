package com.driver;

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
        if(sum > 9*digits) throw new AccountNumberCanNotBeGenerated("Account Number can not be generated");

        Random random = new Random();
        int AccountNumber = 0;

        while(true) {
            AccountNumber = random.nextInt((int) Math.pow(10, digits) - 1);
            int digit = AccountNumber;
            int checkSum = 0;
            for(int i = 0; i < digits; i++) {
                int extractDigit = digit%10;
                digit /= 10;

                checkSum += extractDigit;
            }

            if(checkSum == sum) break;
        }
        return String.valueOf(AccountNumber);
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