package com.ajit123jain.recyclerview;

/**
 * Created by ajit on 24/10/17.
 */

public class Data {
    public int imageId;
    public String txt;

    Data( int imageId, String text) {

        this.imageId = imageId;
        this.txt=text;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}