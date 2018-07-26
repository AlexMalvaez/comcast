package com.codingtest.comcast.comcastct.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexlm on 7/23/18.
 */

public class Character implements Parcelable{

    private String title;
    private String description;
    private String ulr;

    public Character(){

    }

    public Character(String title, String description, String ulr) {
        this.title = title;
        this.description = description;
        this.ulr = ulr;
    }

    public Character(Parcel in){
        this.title = in.readString();
        this.description = in.readString();
        this.ulr = in.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.ulr);
    }

    @Override
    public String toString() {
        return "[title : " + title +
                ", description : " + description +
                ", url : " + ulr +"]";
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {

        public Character createFromParcel(Parcel in){
            return new Character(in);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }

    };
}
