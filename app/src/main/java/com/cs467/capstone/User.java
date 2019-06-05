package com.cs467.capstone;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String id;
    private String email;
    private String username;
    private String profileImageUrl;

    User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String id, String email, String username, String profileImageUrl) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public User(Parcel in) {
        String[] data = new String[4];

        in.readStringArray(data);

        this.id = data[0];
        this.email = data[1];
        this.username = data[2];
        this.profileImageUrl = data[3];


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.id,
                this.email,
                this.username,
                this.profileImageUrl
        });
    }

     public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

         @Override
         public Object createFromParcel(Parcel in) {
             return new User(in);
         }

         @Override
         public Object[] newArray(int size) {
             return new User[size];
         }


     };
}
