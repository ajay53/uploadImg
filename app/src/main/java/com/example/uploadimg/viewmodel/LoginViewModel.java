package com.example.uploadimg.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.uploadimg.database.repository.UserRepository;
import com.example.uploadimg.model.User;
import com.example.uploadimg.utility.AsyncResponse;

public class LoginViewModel extends AndroidViewModel {

    private final UserRepository repository;
    public String password;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void getUser(String username, AsyncResponse asyncResponse) {
        repository.getUser(username, asyncResponse);
    }

    public void getDuplicate(String emailID, String phoneNo, AsyncResponse asyncResponse) {
        repository.getDuplicate(emailID, phoneNo, asyncResponse);
    }

    public void insert(User user) {
        repository.insert(user);
    }
}
