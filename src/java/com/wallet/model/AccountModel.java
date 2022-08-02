package com.wallet.model;

import com.wallet.controller.AccountDao;
import com.wallet.domain.Account;
import com.wallet.domain.LoginInfo;
import com.wallet.domain.MoneyTransfer;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author stella
 */
@SessionScoped
@ManagedBean(name = "acc")
public class AccountModel {
    private Account account = new Account();
    private AccountDao accountDao = new AccountDao();
    private String loggedInUser;
    private LoginInfo loginInfo = new LoginInfo();
    private String user;
    private MoneyTransfer moneyTransfer = new MoneyTransfer();
    private Double yourBalance;
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public MoneyTransfer getMoneyTransfer() {
        return moneyTransfer;
    }

    public void setMoneyTransfer(MoneyTransfer moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }

    public Double getYourBalance() {
        return yourBalance;
    }

    public void setYourBalance(Double yourBalance) {
        this.yourBalance = yourBalance;
    }
    
    
    
    
    //NAVIGATION
    public String loginPage(){
        return "login";
    }
    
    public String homePage(){
        return "index";
    }
    
    public String userAccountPage(){
        return "user-account";
    }
    
    public String balancePage(){
        return "balance";
    }

    public String createAccountPage(){
        return "create-account";
    }

    public String transferPage(){
        user = fetchedUser.getFirstName()+" "+fetchedUser.getLastName();
        return "transfer";
    }
    

    
    
    //GENERATE BANK ACCOUNT
    public long generateBankAccount(){
        long min = 1000000;
        long max = 1000000000;
        return (long)Math.floor(Math.random()*(max-min+1)+min);
    }
    
    //CREATING AN ACCOUNT
    public String createAccount(){
        FacesMessage saveMessage;
        try {
            account.setAccountNumber(generateBankAccount());
            account.setAmount(1000.0);
            
            System.out.println("Generated Bank account: "+generateBankAccount());
            
            accountDao.createAccount(account);
            
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Account successfuly created! \nYour account number is "+account.getAccountNumber(),"Make sure all required Infomation is provided.");
            FacesContext.getCurrentInstance().addMessage("success-message", saveMessage);
            return "login";
           
        } catch (Exception e) {
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Failed to create Account! | "+e.getMessage()+"","Make sure all required Infomation is provided.");
            FacesContext.getCurrentInstance().addMessage("error-message", saveMessage);
            return "create-account";        
        }
    }
    
    long loginAccountNumber;
    Account fetchedUser;
    
    //LOGIN
    public String login(){
        FacesMessage saveMessage;
        try {            
            loginAccountNumber = loginInfo.getInsertedAccountNumber();
            String loginPassword = loginInfo.getInsertedPassword();
            fetchedUser = accountDao.getAccount(loginAccountNumber);
            String fetchedPassword = fetchedUser.getPassword1();
            
            if(fetchedPassword.equals(loginPassword)) {
                user = fetchedUser.getFirstName()+" "+fetchedUser.getLastName();
                return "user-account";
            } else {
                saveMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Incorrect username or password!!","Make sure all required Infomation is provided.");
                FacesContext.getCurrentInstance().addMessage("login-message", saveMessage);
                return "login";
            } 
        } catch (Exception e) {
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Login Failed!! | "+e.getMessage()+"","Make sure all required Infomation is provided.");
            FacesContext.getCurrentInstance().addMessage("login-message", saveMessage);
            return "login";        
        }
    }
    
    //FETCHING BALANCE
    public Double myBalance(){
        fetchedUser = accountDao.getAccount(loginAccountNumber);
        return fetchedUser.getAmount();
    }
    
    //CHECK BALANCE
    public String checkBalance(){
        FacesMessage saveMessage;
        try {            
            
            user = fetchedUser.getFirstName()+" "+fetchedUser.getLastName();
                
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Account successfuly created!","Make sure all required Infomation is provided.");
            FacesContext.getCurrentInstance().addMessage("success-message", saveMessage);
            return "login";
           
        } catch (Exception e) {
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Failed to create Account! | "+e.getMessage()+"","Make sure all required Infomation is provided.");
            FacesContext.getCurrentInstance().addMessage("error-message", saveMessage);
            return "create-account";        
        }
    }
    
    //TRANSFER
    public String send(){
        FacesMessage saveMessage;
        try {            
            //Fetching form data
            long accountNumberOfReciever = moneyTransfer.getAccountOfReciever();
            Double amountToSend = moneyTransfer.getAmount();

            //Fetching the account of the reciever
            Account recieverAccount = accountDao.getAccount(accountNumberOfReciever);
            
            //Updating the reciever data.
            recieverAccount.setAmount(recieverAccount.getAmount() + amountToSend);
            recieverAccount.setAccountNumber(accountNumberOfReciever);
            recieverAccount.setFirstName(recieverAccount.getFirstName());
            recieverAccount.setLastName(recieverAccount.getLastName());
            recieverAccount.setLocation(recieverAccount.getLocation());
            recieverAccount.setPassword1(recieverAccount.getPassword1());
            recieverAccount.setPassword2(recieverAccount.getPassword2());

            //Updating the current user's account.
            fetchedUser.setAccountNumber(loginAccountNumber);
            if (amountToSend <= 10000.0) {
                fetchedUser.setAmount(fetchedUser.getAmount() - amountToSend);
            } else if(amountToSend < 100000.0 && amountToSend > 10000.0) {
                fetchedUser.setAmount(fetchedUser.getAmount() - amountToSend - 200.0);
            } else if(amountToSend >= 100000.0) {
                fetchedUser.setAmount(fetchedUser.getAmount() - amountToSend - 1000.0);
            }
            
            fetchedUser.setFirstName(fetchedUser.getFirstName());
            fetchedUser.setLastName(fetchedUser.getLastName());
            fetchedUser.setLocation(fetchedUser.getLocation());
            fetchedUser.setPassword1(fetchedUser.getPassword1());
            fetchedUser.setPassword2(fetchedUser.getPassword2());
            
            //Updating the two accounts.
            accountDao.updateAccount(recieverAccount);
            accountDao.updateAccount(fetchedUser);
            
            //The name of the currently logged in user.
            fetchedUser = accountDao.getAccount(loginAccountNumber);
            
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_INFO,"Transfer successfuly! Your new balance is "+fetchedUser.getAmount(),"");
            FacesContext.getCurrentInstance().addMessage("success-message", saveMessage);
            return "user-account";
           
        } catch (Exception e) {
            saveMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL,"Failed to transfer! | "+e.getMessage()+"","null");
            FacesContext.getCurrentInstance().addMessage("error-message", saveMessage);
            return "transfer";        
        }
    }
    
}
