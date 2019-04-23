package com.example.blooddonar.Model_list;

public class ListItem_donor {

    private String email,name,mobile,blood_group,postal_address,distance,latitude,longitude,sex,age;


    public ListItem_donor(String email, String name, String mobile, String blood_group, String postal_address, String distance,String latitude,String longitude,String sex,String age) {
        this.email = email;
        this.name = name;
        this.mobile = mobile;
        this.blood_group = blood_group;
        this.postal_address = postal_address;
        this.distance = distance;
        this.latitude=latitude;
        this.longitude=longitude;
        this.sex = sex;
        this.age = age;

    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistance() {

        double d=Double.parseDouble(distance);
        d = d *1.60934 *1.45;
        String Km_distance = String.format("%.2f", d);
        return Km_distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}