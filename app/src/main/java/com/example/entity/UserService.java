package com.example.entity;

import android.content.Context;


public interface UserService {


    public int login(String userName, String password, Context context);

    public void logout();

    public int changepassword(String userName, String oldpassword, String newpassword);

    public int feedback(String userName, String feedback, String contactway);

    public void uploadimg(String userName, String url);
}
