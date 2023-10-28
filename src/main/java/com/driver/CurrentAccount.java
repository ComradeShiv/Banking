package com.driver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

        try {
            if(balance < getMinBalance()) {
                throw new InsufficientBalance("Insufficient Balance");
            }
        } catch(InsufficientBalance e){
            System.out.println(e.getMessage());
        }

    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        HashMap<Character, Integer> freqs = new HashMap<>();

        ArrayList<Character> charList = new ArrayList<>();
        for(char ch: tradeLicenseId.toCharArray()){
            charList.add(ch);
            freqs.put(ch, freqs.getOrDefault(ch, 0) + 1);
        }

        for(Map.Entry<Character, Integer> freq: freqs.entrySet()) {
            try {
                if(freq.getValue()%2 == 0 && freq.getValue() > charList.size()/2)
                    throw new ValidLicenseCanNotBeGenerated("Valid License can not be generated");
                else if(freq.getValue()%2 != 0 && freq.getValue() > (charList.size()/2) + 1)
                    throw new ValidLicenseCanNotBeGenerated("Valid License can not be generated");
            } catch(ValidLicenseCanNotBeGenerated e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        for(int i = 0; i < charList.size()-1; i++) {
            if(charList.get(i) == charList.get(i+1)) {
                Collections.shuffle(charList);
                i = 0;
            }
        }

        String validId = "";
        for(char ch: charList)
            validId += ch;

        System.out.println("Given LicenseId is valid: " + validId);
    }
}
