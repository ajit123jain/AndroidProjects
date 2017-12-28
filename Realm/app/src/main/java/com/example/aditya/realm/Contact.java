package com.example.aditya.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Aditya on 12/27/2017.
 */
public class Contact extends RealmObject{
    private Email email;
    private int age;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
