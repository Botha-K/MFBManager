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
import java.util.logging.Logger;

/**
 *
 * @author warl0ck
 */
public class Transaction implements Serializable {
   int sn;
   String now;
   String amount;
   String accNo; String description, operationType;
   String withdrawAmt, depositAmt, balance;

    public Transaction(int sn, String now, String accNo, String description, String operationType, String amount, String balance) {
        this.sn = sn;
        this.now = now;
        this.accNo = accNo;
        this.description = description;
        this.operationType = operationType;
        this.amount = amount;
        this.balance = balance;
        MFB.journalRegister.put(String.valueOf(sn), this);
        //MFB.save(); //this is so that the Size of the hashmap which is used as the transaction Id can be acurate at all times
    }
    
    

    public int getSn() {
        return sn;
    }

    public String getNow() {
        return now;
    }

    public String getAccNo() {
        return accNo;
    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getWithdrawAmt() {
        return withdrawAmt;
    }

    public String getDepositAmt() {
        return depositAmt;
    }

    public String getBalance() {
        return balance;
    }
    
    
    public String Now(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }

    @Override
    public String toString() {
        return "Transaction{" + "sn=" + sn + ", Date=" + now + ","
                + " amount=" + amount + ", accNo=" + accNo + ", description=" 
                + description + ", operationType=" + operationType + ", withdrawAmt="
                + withdrawAmt + ", depositAmt=" + depositAmt + ", balance=" + balance + '}';
    }
  
    
}