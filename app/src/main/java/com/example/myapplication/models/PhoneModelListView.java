package com.example.myapplication.models;

public class PhoneModelListView {

    private int mPhotos;
    private String mTitle;

    public PhoneModelListView(int mPhotos, String mTitle) {
        this.mPhotos = mPhotos;
        this.mTitle = mTitle;
    }

    public int getmPhotos() {
        return mPhotos;
    }


    public String getmTitle() {
        return mTitle;
    }


}
