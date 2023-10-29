package com.driver;

public class Main {
    public static void main(String[] args) throws Exception {

        CurrentAccount ca = new CurrentAccount("Shiv" , 10000, "a");
        ca.validateLicenseId();
        System.out.println(ca.generateAccountNumber(5, 25));

        SavingsAccount sa = new SavingsAccount("Shiv" , 10000, 1000, 2);
        sa.withdraw(2000);
        sa.getSimpleInterest(5);
        sa.getCompoundInterest(5, 5);

    }
}