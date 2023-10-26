package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super(name, balance, 0);
        this.maxWithdrawalLimit = maxWithdrawalLimit;
        this.rate = rate;
    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        try {
            if(amount > getBalance())
                throw new InsufficientBalance("Insufficient Balance");

            if(amount > maxWithdrawalLimit)
                throw new WithdrawalLimitException("Maximum Withdraw Limit Exceed");

            double currentBalance = getBalance();
            currentBalance -= amount;
        } catch(InsufficientBalance e){
            System.out.println();
        } catch(WithdrawalLimitException e) {
            System.out.println();
        }
    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double simpleInterest = (getBalance() * rate * years)/100;
        return getBalance() + simpleInterest;
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        return getBalance()*(Math.pow(1+(rate/times), times*years));
    }

}
