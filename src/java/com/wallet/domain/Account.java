package com.wallet.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author stella
 */
@Entity
public class Account implements Serializable {
    @Id
    private long accountNumber;
    private String firstName;
    private String lastName;
    private String location;
    private Double amount;
    private String password1;
    private String password2;

    public Account() {
    }

    public Account(String firstName, String lastName, String location, long accountNumber, Double amount, String password1, String password2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.password1 = password1;
        this.password2 = password2;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }    

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }
}
