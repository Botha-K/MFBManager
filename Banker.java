/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfbmanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author warl0ck
 */
public  class Banker  implements  Serializable{


    public  String uId;
    public String password;
    public  String firstname;
    public  String lastname;
    public String gender;
    public String address;
    public String UserType;

    public Banker(String uId, String password, String firstname, String lastname, String gender, String address,  String UserType) {
        this.uId = uId;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.address = address;
        this.UserType = UserType;
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

   
    @Override
    public String toString() {
        return "Banker{" + "uId=" + uId + ", password=" + password + ", firstname=" 
                + firstname + ", lastname=" + lastname + ", gender=" + gender 
                + ", address=" + address + ", registrationDate="  + ", UserType=" + UserType + '}';
    }
    
    
    
}
