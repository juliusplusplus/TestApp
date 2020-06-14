package com.example.entity;

import android.content.Context;
import android.util.Log;

import com.example.db.UserServiceImpl;


/**
 * Created by X45VD on 2016/7/26 0026.
 */
public class ServiceFactory {
    public static UserService getUserService(Context context) {
        return new UserServiceImpl();
    }
//    public static NetworkService getNetworkService(Context context)
//    {
//        return new NetworkServiceImpl(context );
//    }
//
//    public static SystemService getSystemService(Context context)
//    {
//        return new SystemServiceImpl(context);
//    }
}
