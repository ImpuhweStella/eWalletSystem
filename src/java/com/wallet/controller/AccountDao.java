package com.wallet.controller;

import com.wallet.domain.Account;
import com.wallet.util.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author stella
 */
public class AccountDao {
    Session session = null;
    Transaction transaction = null;
    
    public void createAccount(Account account){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(account);
        transaction.commit();
        session.close();
    }  
    
    public void updateAccount(Account account){
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.update(account);
        transaction.commit();
        session.close();
    }  
    
    public List<Account> listAccounts() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Account> listOfAccounts = session.createCriteria(Account.class).list();
        transaction.commit();
        session.close();
        return listOfAccounts;
    }
    
    public Account getAccount(long accountNumber) {
        Account foundAccount = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            foundAccount = (Account)session.get(Account.class, accountNumber);
            session.close();
            if (foundAccount == null) {
                return null;
            } else {
                return foundAccount;
            }
        } catch (HibernateException e) {
            return null;
        }
    }
}
