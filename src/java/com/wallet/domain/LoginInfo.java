package com.wallet.domain;

/**
 *
 * @author stella
 */
public class LoginInfo {
    private long insertedAccountNumber;
    private String insertedPassword;

    public LoginInfo() {
    }

    public LoginInfo(long insertedAccountNumber, String insertedPassword) {
        this.insertedAccountNumber = insertedAccountNumber;
        this.insertedPassword = insertedPassword;
    }

    public long getInsertedAccountNumber() {
        return insertedAccountNumber;
    }

    public void setInsertedAccountNumber(long insertedAccountNumber) {
        this.insertedAccountNumber = insertedAccountNumber;
    }

    public String getInsertedPassword() {
        return insertedPassword;
    }

    public void setInsertedPassword(String insertedPassword) {
        this.insertedPassword = insertedPassword;
    }    
}
