package com.cs467.capstone;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String email;
    private String username;
    private String profileImageUrl;

    User() {}

    public User(String email, String username, String profileImageUrl) {
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

    public void setUsername(String useername) {
        this.username = useername;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public User(Parcel in) {
        String[] data = new String[3];

        in.readStringArray(data);

        this.email = data[0];
        this.username = data[1];
        this.profileImageUrl = data[2];


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                this.email,
                this.username,
                this.profileImageUrl
        });
    }

     public static final Parcelable.Creator Creator = new Parcelable.Creator() {

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
