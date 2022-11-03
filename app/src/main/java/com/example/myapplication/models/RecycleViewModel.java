package com.example.myapplication.models;

public class RecycleViewModel {

    private int photoId;
    private String mTitle;

    public RecycleViewModel(int photoId, String mTitle) {
        this.photoId = photoId;
        this.mTitle = mTitle;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getmTitle() {
        return mTitle;
    }
}
