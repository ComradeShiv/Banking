package com.driver;

public class WithdrawalLimitException extends Exception{

    public WithdrawalLimitException(String message) {
        super(message);
    }
}
