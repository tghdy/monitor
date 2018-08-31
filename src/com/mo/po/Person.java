package com.mo.po;

public class Person {
    public Person() {
        super();
    }

    public Person(String name, int sex, String birthtime, String rfid) {
        this.name = name;
        this.sex = sex;
        this.birthtime = birthtime;
        this.rfid = rfid;
    }

    private int id;
    private String name;
    private int sex;
    private String birthtime;
    private String rfid;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public String getBirthtime() {
        return birthtime;
    }

    public String getRfid() {
        return rfid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setBirthtime(String birthtime) {
        this.birthtime = birthtime;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }
}
