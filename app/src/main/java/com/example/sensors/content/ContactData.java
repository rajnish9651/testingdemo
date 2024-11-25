package com.example.sensors.content;

public class ContactData {
    String name;
    String number;
    String img;


    public ContactData(String img, String name, String number) {
        this.img = img;
        this.name = name;
        this.number = number;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
