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
public class BankManager extends Banker implements Serializable{
    
     
    Transaction trans;
    int sn = MFB.journalRegister.size()+1;
    public BankManager(String uId, String password, String firstname, String lastname, String gender, String address, String UserType) {
        super(uId, password, firstname, lastname, gender, address, UserType);
    }
    
   

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }
    
    public ArrayList<Banker> viewOfficersList(){
        ArrayList<Banker> users = new ArrayList<>();
        for(String id: MFB.bankerRegister.keySet()){

            if(MFB.bankerRegister.get(id).getClass() == DeskOfficer.class){
                //continue;
            users.add(MFB.bankerRegister.get(id));
            }
        }
        
       
        return users;
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
    
    public String createDeskOfficer(String uId, String password, String firstname, String lastname, String gender, String address){
        Banker deskOfficer1 = new DeskOfficer("admin","admin", "admin", "admin", "male","new Nyanya","");
        
        deskOfficer1.setPassword(String.valueOf(password));
        MFB.bankerRegister.put(uId, deskOfficer1);
        
        trans = new Transaction(sn,Now(), null, "new Desk Officer Created", "Create Desk Officer", null, null);
        MFB.journalRegister.put(String.valueOf(sn),trans);
        
        return String.valueOf(password);
    }
    
    public String Now(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
    
}
