package com.example.util;

import android.content.Context;
import android.content.SharedPreferences;


public class SharePreferenceUtil {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharePreferenceUtil(Context context, String file) {
        sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
        editor = sp.edit();
    }

//    public void setYear(String userid, int year) {
//        editor.putInt(userid+SharePreferenceConf.YEAR , year);
//        editor.commit();
//    }
//
//    public int getYear(String userid) {
//        return sp.getInt(userid+SharePreferenceConf.YEAR,0);
//    }
//    public void setMonth(String userid, int month) {
//        editor.putInt(userid+SharePreferenceConf.MONTH, month);
//        editor.commit();
//    }
//    public int getMonth(String userid) {
//        return sp.getInt(userid+SharePreferenceConf.MONTH,1);
//    }

    public String getPassword() {
        return sp.getString("height", "");
    }

    public void setPassword(String password) {
        editor.putString("password", password);
        editor.commit();
    }

    public String getUsername() {
        return sp.getString("username", "");
    }

    public void setUsername(String username) {
        editor.putString("userid", username);
        editor.commit();
    }


//    public String getsex()
//    {
//        return sp.getString(SharePreferenceConf.SEX,"");
//    }
//
//    public void setsex(String sex)
//    {
//        editor.putString(SharePreferenceConf.SEX, sex);
//        editor.commit();
//    }
//    public String getname()
//    {
//        return sp.getString(SharePreferenceConf.USERNAME,"");
//    }
//
//    public void setname(String name)
//    {
//        editor.putString(SharePreferenceConf.USERNAME, name);
//        editor.commit();
//    }
//    public String gettoken()
//    {
//        return sp.getString(SharePreferenceConf.TOKEN,"");
//    }
//
//    public void settoken(String token)
//    {
//        editor.putString(SharePreferenceConf.TOKEN, token);
//        editor.commit();
//    }
//    public String geturl()
//    {
//        return sp.getString(SharePreferenceConf.SYSTEMURL,"");
//    }
//
//    public void seturl(String url)
//    {
//        editor.putString(SharePreferenceConf.SYSTEMURL,url);
//        editor.commit();
//    }
}
