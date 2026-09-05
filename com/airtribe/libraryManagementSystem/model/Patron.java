package com.airtribe.libraryManagementSystem.model;

import com.airtribe.libraryManagementSystem.util.IdGenerator;

public class Patron {
    private int id;
    private String name;
    private String phone;
    private String address;

    public Patron(String name, String phone, String address) {
        this.id = IdGenerator.getNextPatronId();
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
