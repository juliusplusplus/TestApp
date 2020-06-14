package com.example.db;

import android.content.Context;

import com.example.entity.UserService;
import com.example.util.SharePreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * @ProjectName: TestApp
 * @Package: com.example.asr
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: julius
 * @Email: julius_hy@qq.com
 * @Version: 1.0
 */
public class UserServiceImpl implements UserService {
    private static final MediaType MEDIA_TYPE = MediaType.parse("image/png");
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public int login(String userName, String password, Context context) {
        SharePreferenceUtil login = new SharePreferenceUtil(context, "personinfo");
        if (userName.equals("201518530505") && password.equals("123")) {
            login.setUsername("201518530505");
//            login.setYear(login.getUsername(),1993);
//            login.setMonth(login.getUsername(), 6);
//            login.setsex("男");
//            login.setname("张三");
            return 1;
        } else {
            return 2;
        }
//
//        RequestBody formBody = new FormBody.Builder()
//                .add("username", userName)
//                .add("password", password)
//                .build();
//        System.out.println("-------->"+"1234");
//        Request request = new Request.Builder()
//                .url(UrlConfig.url+UrlConfig.url_login)
//                .post(formBody)
//                .build();
//        try {
//            OkHttpClient.Builder builder=new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS);
//            OkHttpClient okHttpClient=builder.build();
//            Response response = okHttpClient.newCall(request).execute();
//            if (response.isSuccessful()) {
//                //System.out.println("-------->"+response.body().string());
//                JSONObject jsonObject=new JSONObject(response.body().string());
//                if(jsonObject.getString("Code").equals("0")) {
//                    String s[] = jsonObject.getString("StudentBirthday").toString().split("-");
//                    login.setUsername(jsonObject.getString("StudentId"));
////                    login.setYear(login.getUsername(), Integer.parseInt(s[0]));
////                    login.setMonth(login.getUsername(), Integer.parseInt(s[1]));
////                    login.setsex(jsonObject.getString("StudentSex"));
////                    login.setname(jsonObject.getString("StudentName"));
////                    login.settoken(jsonObject.getString("Token"));
//                    //login.setUsername(userName);
//                    //登入验证
//                    //存入sp
//                    return 1;
//                }
//                else
//                    return 2;
//            }
//            else
//            {
//                System.out.println("-------->+失败");
//                return  3;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("-------->+登录失败，请检查网络！");
//            return  0;
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return 3;
//        }
    }

    @Override
    public void logout() {

    }

    @Override
    public int changepassword(String userName, String oldpassword, String newpassword) {
        return 2;
    }

    @Override
    public int feedback(String userName, String feedback, String contactway) {
//        RequestBody formBody = new FormBody.Builder()
//                .add("userName", userName)
//                .add("feedback",feedback)
//                .add("contactway",contactway)
//                .build();
//        Request request = new Request.Builder()
//                .url(UrlConfig.url)
//                .post(formBody)
//                .build();
//        try {
//            Response response = client.newCall(request).execute();
//            JSONObject jsonObject=new JSONObject(response.body().string());
//            switch (jsonObject.getString("Code"))
//            {
//                case "1":
//                    return 1;
//                case "2":
//                    return 2;
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return 3;
    }

    @Override
    public void uploadimg(String userName, String url) {


    }
}
