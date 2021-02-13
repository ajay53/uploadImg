package com.example.uploadimg.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.uploadimg.model.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user where emailId = :username or phoneNo = :username")
    User get(String username);

    @Query("SELECT * FROM user where emailId = :emailId or phoneNo = :phoneNo")
    User getDuplicate(String emailId, String phoneNo);
}
