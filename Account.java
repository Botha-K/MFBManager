/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfbmanager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static mfbmanager.MFB.journalRegister;

/**
 *
 * @author warl0ck
 */
public  class Account implements Serializable{

    
    public enum Status{CLEARED,SUSPENDED};
    public String accName;
    public String accNo;
    private String address;
    private double balance;
    private String password;
    public double amount;
    String firstname, lastname, gender;
    private String uId;
    Status s;
    public Transaction trans;
    int sn= MFB.journalRegister.size()+1;

    

    public Account(String uId, String password, String firstname, String lastname,double amount, String accNo, String gender, String address) {
        setAccName(firstname + " " + lastname);        
        this.accNo = accNo;
        this.address = address;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        setBalance(amount) ;
        this.uId = uId;
        this.password = password;
        s = Status.CLEARED;
        trans = new Transaction(sn,Now(),accNo,"Acount has been Created", "Acc Created", String.valueOf(amount), String.valueOf(getBalance()));
        
     }

 

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
    
    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccNo() {
        return accNo;
    }
    public Status getS() {
        return s;
    }

    public void setS(Status s) {
        this.s = s;
    }

    public void setAccNo(String accNo) {
        if (accNo != null ) {
            this.accNo = accNo;
        }
        else{
            System.out.println("input a valid Account number");
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null) {
            this.address = address;
        }
        else{
            System.out.println("Address Space can not be Empty");
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        
        this.balance = balance;
    }
    
    public void debit(double amount){
        setBalance(getBalance() - amount);
        
    }

   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean deposit(double amount, String accNo){
        boolean result;
        if(MFB.accountRegister.containsKey(accNo) && amount > 0.00){
            Account customer = MFB.accountRegister.get(accNo);
            
            customer.setBalance(customer.getBalance() + amount);
            trans = new Transaction(sn,Now(),this.accNo,"deposited: "+amount+" to "+MFB.accountRegister.get(accNo).getAccName(),"deposit",
            String.valueOf(amount),String.valueOf(getBalance()));
            
            result = true;
        }
        else{
                System.out.println("check if amount to be deposited is more than 0.00 \n"
                        + "check if acoount details is correct \n"
                       );
            result = false;
        }
        
        return result;
    }
    
    public boolean transferFund(double amount, String accNo){
        boolean result;
        if(getBalance() > amount && amount > 0.00 && MFB.accountRegister.containsKey(accNo)){

            result = deposit(amount, accNo);
            if(result == true){
                setBalance(getBalance() - amount);
                result = true;
                
                trans = new Transaction(sn,Now(),
                        this.accNo,"transferd: "+amount+" to "+MFB.accountRegister.get(accNo).getAccName(),
                        "transfer",
                        String.valueOf(amount),String.valueOf(getBalance()));
            
            }
            else{
                System.out.println("Error Depositing");
                result = false;
            }
         
        }
        else{
            if(amount > getBalance())
                System.out.println("Insuficient Funds");
            if(amount <= 0.00)
                System.out.println("Invalid Transfer Amount");
            if(MFB.accountRegister.containsKey(accNo) == false)
                System.out.println("Account Not Found in Record");
            
            result = false;
        }
        return result;
     }
    
    public boolean withdraw(double amount){
        boolean result;
        if(getBalance() > amount && amount> 0.00){
            debit(amount);
            result = true;
            trans = new Transaction(sn,Now(),this.accNo,"withdraw: " + amount, "withdraw",
            String.valueOf(amount),String.valueOf(getBalance()));
            
        }
        else{
            if(getBalance() < amount)
                System.out.println("Insuficient Funds");
            if(amount < 0.00)
                System.out.println("Amount must be more than 0.00 Naira");
            result = false;
        }
        return result;
    }
    
     public ArrayList<String> getStatement(String accNo){
        ArrayList<String> statements = new ArrayList();
        for(Map.Entry<String, Transaction> entry : MFB.journalRegister.entrySet() ){
            if(entry.getKey() == accNo){
                
                statements.add(entry.getValue().toString());
            }
        }
        return statements;

    }
    
    public double checkBalance(){
        trans = new Transaction(sn,Now(),this.accNo,"current balance", "check Balance", null, String.valueOf(getBalance()));
        return getBalance();
            
    }
    
    public boolean changePassword(String oldPwd, String newPwd, String confPwd){
        boolean result;
        if(oldPwd.equals(getPassword()) && newPwd.equals(confPwd) ){
            setPassword(newPwd);
            result = true;
            
            trans = new Transaction(sn,Now(),this.accNo,"password has been Changed to "+ newPwd, "changed password", null, null);
        }
        else{
            if (!oldPwd.equals(getPassword())) {
                System.out.println("incorect password");
                
            }
            if (!newPwd.equals(confPwd)) {
                System.out.println("new Passwords do not match");
            }
            result = false;
        }
        return result;
    }
    
   
    
    public String viewPwd(){
        
        return getPassword();
    }
    
   
     public String Now(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    @Override
    public String toString() {
        return "Account{" + "accName=" + accName + ", accNo=" + accNo + ", address=" + address + ", balance=" + balance + ", password=" + password + ", amount=" + amount + ", firstname=" + firstname + ", lastname=" + lastname + ", gender=" + gender + ", uId=" + uId + ", s=" + s + ", trans=" + trans + ", sn=" + sn + '}';
    }
    
}
