package com.example.aditya.realm;

import io.realm.RealmObject;

/**
 * Created by Aditya on 12/27/2017.
 */

public class Email  extends RealmObject {
    private String address;
    private boolean active;

    public Email() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
