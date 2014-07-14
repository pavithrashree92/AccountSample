package com.account.selectors;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by Pavithra on 11/7/14.
 */
public abstract class  Account {

    public String ac_no;
    public  String ac_name;
    public String ac_type;
    public String contact_no;
    public String email;
    public Double balance;


    public String getAc_no() {
        return ac_no;
    }

    public void setAc_no(String ac_no) {
        this.ac_no = ac_no;
    }

    public String getAc_name() {
        return ac_name;
    }

    public void setAc_name(String ac_name) {
        this.ac_name = ac_name;
    }

    public String getAc_type() {
        return ac_type;
    }

    public void setAc_type(String ac_type) {
        this.ac_type = ac_type;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBalance(){
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    public boolean create() throws IOException {
return Boolean.parseBoolean(null);
    }

    public Double addFunds(Double Deposit) throws IOException {
return null;
    }
    public Double withdrawFunds(Double Deposit) throws IOException {
return null;
    }

    public Double displayBalance() throws IOException {

        return null;
    }


}
