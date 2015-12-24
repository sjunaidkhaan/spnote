package com.ingentive.shopnote;

/**
 * Created by PC on 12/22/2015.
 */
public class Contact {

    int sectionId;
    String sectionName;

    public Contact() {

    }

    public Contact(int id, String name) {
        this.sectionId = id;
        this.sectionName = name;
    }

    public Contact(String name) {
        this.sectionName = name;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }







    /*int id;
    String name;
    String phoneNo;

    public Contact() {

    }

    public Contact(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNo = phoneNumber;
    }

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNo = phoneNumber;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNo;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNo = phoneNumber;
    }*/
}
