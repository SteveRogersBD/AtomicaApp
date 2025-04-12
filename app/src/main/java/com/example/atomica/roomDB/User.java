package com.example.atomica.roomDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public long id;
    public String fullName;
    public String username;
    public String email;
    public String password;
    public String role;
    public String dp;
    public String about;
    public String createdAt;
    public User(){}
}
