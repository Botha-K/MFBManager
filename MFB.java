/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfbmanager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;    
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author warl0ck
 */
public class MFB {
    public static HashMap<String, Account> accountRegister;
    public static HashMap<String, Banker> bankerRegister;
    public static HashMap<String, Transaction> journalRegister;
    
    
    static File accountRecords ;
    static File bankerRecords ;
    static File journal;
    static boolean isSaved = false;
    
    
    public static Account account;
    public static Account account2;
    public static Account account3;
    public static Account account4;
    public static Account account5;
    public static Banker banker;
    public static Banker banker2;
    public static Banker banker3;
    public static Banker banker4;
    public static Banker banker5;
    public static Transaction transaction;
    
    static File directory;
    
    
    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
       
        save();
        System.out.println("after Save mrthod comes...\n");
        
        load();
        
    }
      
    public static boolean save(){
        FileOutputStream fosA = null;
        try {
            //directory = new File();
            accountRegister = new HashMap<>();
            bankerRegister = new HashMap<>();
            journalRegister = new HashMap<>();
            accountRecords = new File( "accountrecords.ser");
            bankerRecords = new File("bankerRecords.ser");
            journal = new File( "journal.ser");
            
            fosA = new FileOutputStream(accountRecords);
            FileOutputStream fosB = new FileOutputStream(bankerRecords);
            FileOutputStream fosJ = new FileOutputStream(journal);
            ObjectOutputStream oosA = new ObjectOutputStream(fosA);
            ObjectOutputStream oosB = new ObjectOutputStream(fosB);
            ObjectOutputStream oosJ = new ObjectOutputStream(fosJ);
            
            banker = new DeskOfficer("D01","mohammad","bashir","okala","male","new Nyanya","DeskOfficer");
            banker2 = new DeskOfficer("D02","mohammad","ibrahim","bitrus","male","gwarimpa","DeskOfficer");
            banker3 = new DeskOfficer("D03","mohammad","Rahmatu","John","male","Karu","DeskOfficer");
            banker4 = new BankManager("D04","mohammad","George","David","male","new Karu","BankManager");
            banker5 = new BankManager("D05","mohammad","Kane","Shedrach","male","Jabi","BankManager");
            
            
            account = new Account("warl0ck", "mohammad", "Bashir", "Mohammad", 54800000.00, "0198579725", "male", "Chanchaga");
            account2 = new Account("grace", "mohammad", "grace", "Musa", 50.00, "0198579725", "female", "Abuja");
            account3 = new Account("Simon", "mohammad", "simon", "Oguntuase", 5990.00, "0198579725", "male", "Lagos");
            account4 = new Account("Utibe", "mohammad", "Utibe", "David", 7600.00, "0198756445", "male", "Zaria");
            account5 = new Account("Saidu", "mohammad", "saidu", "Aliyu", 6734.00, "09897845", "male", "Kaduna");
            
            System.out.println("suspended");
            account3.setS(Account.Status.SUSPENDED);
            System.out.println("deposit");
            account3.deposit(1050,account3.accNo );
            System.out.println("withdraw");
            account3.withdraw(1111);
            System.out.println("transfer");
            account3.transferFund(1000, account4.accNo);
            System.out.println("getStatement");
            account3.getStatement(account3.getAccNo());
            System.out.println("check balance");
            account3.checkBalance();
            System.out.println("change password to admin");
            account3.changePassword(account3.getPassword(), "admin", "admin");
            
            
            
            
            accountRegister.put(account.getAccNo(), account);
            accountRegister.put(account2.getAccNo(), account2);
            accountRegister.put(account3.getAccNo(), account3);
            accountRegister.put(account4.getAccNo(), account4);
            accountRegister.put(account5.getAccNo(), account4);
            
            
            
            bankerRegister.put(banker.getuId(), banker);
            bankerRegister.put(banker2.getuId(), banker2);
            bankerRegister.put(banker3.getuId(), banker3);
            bankerRegister.put(banker4.getuId(), banker4);
            bankerRegister.put(banker5.getuId(), banker5);
            //journalRegister.put(transaction.getAccName(),transaction);
            
            System.out.println("acc size:" + accountRegister.size());
            System.out.println("baker size:" + bankerRegister.size());
            System.out.println("Journal size:" + journalRegister.size());
            
            System.out.println("Acc values" + accountRegister.get("0198579725").toString());
            
            oosA.writeObject(accountRegister);
            oosB.writeObject(bankerRegister);
            oosJ.writeObject(journalRegister);
            oosA.close();
            oosB.close();
            oosJ.close();
            fosA.close();
            fosB.close();
            fosJ.close();
            isSaved = true;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MFB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MFB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fosA.close();
            } catch (IOException ex) {
                Logger.getLogger(MFB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isSaved;
    }
    
    
     public static void load(){
          if (accountRecords.exists() && bankerRecords.exists() && journal.exists()) {
            
           
        try {
            FileInputStream fisA = new FileInputStream("accountrecords.ser");
            FileInputStream fisB = new FileInputStream("bankerRecords.ser");
            FileInputStream fisJ = new FileInputStream("journal.ser");
            ObjectInputStream oosA = new ObjectInputStream(new FileInputStream(accountRecords));
            ObjectInputStream oosB = new ObjectInputStream(fisB);
            ObjectInputStream oosJ = new ObjectInputStream(fisJ);
            ///Account user = (Account)oosA.readObject();
            accountRegister = (HashMap) oosA.readObject();
            bankerRegister = (HashMap) oosB.readObject();
            journalRegister = (HashMap)oosJ.readObject();
            //Banker banker2 = (Banker)oosB.readObject();
            //Transaction transaction2 = (Transaction)oosJ.readObject();
            System.out.println(accountRegister.get(account.getAccNo()));
            System.out.println(bankerRegister.get(banker.getuId()));
//            System.out.println(journalRegister.get(String.valueOf(transaction.getSn())));
            account = accountRegister.get(account.getuId());
  //          System.out.printf("Account number:\t%d\n Account Name\t%s\n Account"
//                    + " Balance:\t%f\n",account.getAccNo(),account.getAccName(),account.getBalance());
            
            ArrayList accs = new ArrayList();
            accs.addAll(accountRegister.values());
            for (int i = 0; i < accs.size(); i++) {
                System.out.println("accountRegister values are: " + accs.get(i));
            }
            ArrayList bankers = new ArrayList();
            bankers.addAll(bankerRegister.values());
            for (int i = 0; i < bankers.size(); i++) {
                System.out.println("accountRegister values are: " + bankers.get(i));
            }
            ArrayList trans = new ArrayList();
            trans.addAll(journalRegister.values());
            for (int i = 0; i < trans.size(); i++) {
                System.out.println("JoournalRegister values are: " + trans.get(i));
            }
            //System.out.println("the hashmap info is: " +  );
            oosA.close();
            oosB.close();
            oosJ.close();
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MFB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MFB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MFB.class.getName()).log(Level.SEVERE, null, ex);
        }  
        }
     }
}

