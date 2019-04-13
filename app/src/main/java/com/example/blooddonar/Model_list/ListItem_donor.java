package com.example.blooddonar.Model_list;

public class ListItem_donor {

    private String email,name,mobile,blood_group,postal_address,distance;


    public ListItem_donor(String email, String name, String mobile, String blood_group, String postal_address, String distance) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.blood_group = blood_group;
        this.postal_address = postal_address;
        this.distance = distance;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}