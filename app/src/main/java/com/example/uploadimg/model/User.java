package com.example.uploadimg.model;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.uploadimg.BR;

@Entity(tableName = "user")
public class User extends BaseObservable {

    private String name;
    @PrimaryKey
    @NonNull
    private String emailId;
    private String password;
    private String phoneNo;
    private String state;
    private String city;

    public User(String name, @NonNull String emailId, String password, String phoneNo, String state, String city) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.phoneNo = phoneNo;
        this.state = state;
        this.city = city;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
        notifyPropertyChanged(BR.emailId);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        notifyPropertyChanged(BR.phoneNo);
    }

    @Bindable
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        notifyPropertyChanged(BR.state);
    }

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }
}
