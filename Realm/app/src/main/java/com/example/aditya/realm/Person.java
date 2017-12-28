package com.example.aditya.realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Aditya on 12/27/2017.
 */


public class Person extends RealmObject {


    private RealmList<Name> name;
    private Integer age;
    private Email email;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public RealmList<Name> getName() {
        return name;
    }

    public void setName(RealmList<Name> name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
