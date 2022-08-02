package com.wallet.domain;

/**
 *
 * @author stella
 */
public class MoneyTransfer {
    private long accountOfReciever;
    private Double amount;

    public MoneyTransfer() {
    }

    public MoneyTransfer(long accountOfReciever, Double amount) {
        this.accountOfReciever = accountOfReciever;
        this.amount = amount;
    }

    public long getAccountOfReciever() {
        return accountOfReciever;
    }

    public void setAccountOfReciever(long accountOfReciever) {
        this.accountOfReciever = accountOfReciever;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    
}
