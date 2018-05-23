package com.event.hostevent.vo;

public class Invitee {
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email_address;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String rsvp;
    //private int num_of_guests;

    public Invitee(){}
    public  Invitee(String first_name, String last_name, String phone_number,
                        String email_address, String address, String city , String state,
                        String zip){
        this.first_name  = first_name;
        this.last_name = last_name;
        this.email_address = email_address;
        this.phone_number = phone_number;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;


    }

    public String getRsvp() {
        return rsvp;
    }

    public void setRsvp(String rsvp) {
        this.rsvp = rsvp;
    }

    /*public int getNum_of_guests() {
        return num_of_guests;
    }

    public void setNum_of_guests(int num_of_guests) {
        this.num_of_guests = num_of_guests;
    }*/

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
