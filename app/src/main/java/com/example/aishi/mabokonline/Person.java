package com.example.aishi.mabokonline;

/**
 * Created by Aishi on 1/16/2018.
 */

public class Person {
    String name;

    public Person(String name, String info, String imangeUri) {
        this.name = name;
        this.info = info;
        this.imangeUri = imangeUri;
    }

    String info;
    String imangeUri;


    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImangeUri() {
        return imangeUri;
    }

    public void setImangeUri(String imangeUri) {
        this.imangeUri = imangeUri;
    }
}
