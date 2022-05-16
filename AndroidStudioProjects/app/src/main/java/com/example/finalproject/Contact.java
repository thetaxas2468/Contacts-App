package com.example.finalproject;

public class Contact {
    private String Name;
    private String Phone;
    private String Location;
    private int Postcode;
    public Contact(String name, String Phone, String Location, int Postcode){
        this.Name=name;
        this.Phone=Phone;
        this.Location=Location;
        this.Postcode=Postcode;
    }
    public String getName() {
        return Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getLocation() {
        return Location;
    }

    public int getPostcode() {
        return Postcode;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public void setLocation(String location) {
        this.Location = location;
    }

    public void setPostcode(int postcode) {
        this.Postcode = postcode;
    }
}
