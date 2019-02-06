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

/**
 *
 * @author warl0ck
 */

public class DeskOfficer extends Banker implements Serializable{
    Transaction trans;
    int sn = MFB.journalRegister.size()+1;
    
   

    public DeskOfficer(String uId, String password, String firstname, String lastname, String gender, String address,String UserType) {
        super(uId, password, firstname, lastname, gender, address, "DeskOfficer");
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public String createAccount(String uId, String password, String firstname, String lastname,double amount, String accNo, String gender, String address){
        Account customer = new Account("warl0ck","mohammad","bashir","Okala",55500.00,"3077918281","male","New Nyanya");
        int sn = MFB.journalRegister.size() + 1;
        customer.setPassword(String.valueOf(password));
        MFB.accountRegister.put(accNo, customer);
        trans = new Transaction(sn, Now(),"3077918281" , "new Account Created "+ accNo,"Create Account", String.valueOf(amount), null);
        
        MFB.journalRegister.put(String.valueOf(sn), trans);
        
        return String.valueOf(password);
    }
    
    public boolean closeAccount(String accNo){
        boolean result;
        if (MFB.accountRegister.containsKey(accNo)) {
            MFB.accountRegister.remove(accNo);
            result = true;
        }
        else{
            System.out.println("Invalid Account No or Account doesn't Exists");
            result = false;
        }
                return result;
    }
    
    public void suspendUser(String accNo){
        if(MFB.accountRegister.containsKey(accNo)){
            MFB.accountRegister.get(accNo).setS(Account.Status.CLEARED);
            System.out.println("User has been Suspended");
            trans = new Transaction(sn,Now(),accNo,"Acount has been Suspended", "Acc Suspended", null, null);
        }
        else 
            System.out.println("invalid account");
            
    }
    
    public ArrayList<Transaction> viewJournal(){
        ArrayList<Transaction> journal = new ArrayList<>();
        journal.addAll(MFB.journalRegister.values());
        return journal;
    }
    
    public ArrayList<Account> viewAccounts(){
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.addAll(MFB.accountRegister.values());
        return accounts;
    }
         
    public void clearUser(String accNo){
        
        if(MFB.accountRegister.containsKey(accNo)){
            
            MFB.accountRegister.get(accNo).setS(Account.Status.CLEARED);
            System.out.println("User has been Cleared");
            trans = new Transaction(sn,Now(),accNo,"Acount has been Cleared", "Acc Cleared", null, null);
        }
        else 
            System.out.println("invalid account");
    }
    
    public String Now(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
}
