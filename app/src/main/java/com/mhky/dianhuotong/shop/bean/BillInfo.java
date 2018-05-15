package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class BillInfo implements Serializable {

    /**
     * accountNumber : string
     * address : string
     * bankName : string
     * compositeid : 9
     * id : 0
     * mobile : string
     * peopleName : string
     * taxNumber : string
     * ticketType : UNIVERSAL
     */

    private String accountNumber="";
    private String address="";
    private String bankName="";
    private int compositeid;
    private int id;
    private String mobile="";
    private String peopleName="";
    private String taxNumber="";
    private String ticketType="";

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getCompositeid() {
        return compositeid;
    }

    public void setCompositeid(int compositeid) {
        this.compositeid = compositeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
