package com.example.asr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.entity.ServiceFactory;
import com.example.testapp.R;

import com.example.util.NetWorkUtil;
import com.example.util.SharePreferenceUtil;

/**
 * @ProjectName: TestApp
 * @Package: com.example.asr
 * @ClassName: LoginActivity
 * @Description: 这里暂时舍弃了对网络的判断，调用本地数据。
 * @Author: julius
 * @Email: julius_hy@qq.com
 * @Version: 1.0
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private ImageButton delete_username;
    private ImageButton delete_password;
    private Button Login_button;
    private Button Edit_button;
    private SharePreferenceUtil sp;
    private String TAG = "LoginActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = new SharePreferenceUtil(getApplicationContext(), "systeminfo");
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);


        delete_username = (ImageButton) findViewById(R.id.imageButton_del_username);
        delete_password = (ImageButton) findViewById(R.id.imageButton_del_password);
        Login_button = (Button) findViewById(R.id.imageButton_login);
        delete_username.setOnClickListener(this);
        delete_password.setOnClickListener(this);
        Login_button.setOnClickListener(this);
        password.addTextChangedListener(new MyTextWatcher());
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b)
                    delete_username.setVisibility(View.VISIBLE);
                else
                    delete_username.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton_del_username:
                username.setText("");
                break;
            case R.id.imageButton_del_password:
                password.setText("");
                break;
            case R.id.imageButton_login:
//                if( !NetWorkUtil.isNetworkConnected(getApplicationContext()))
//                    Toast.makeText(getApplicationContext(),"网络不可用", Toast.LENGTH_SHORT).show();
//                else {
                login();
//                }
                break;
        }
    }

    private void login() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        if (username.getText().toString().equals("")) {
            builder.setMessage("请输入用户名");
            builder.show();
        } else if (password.getText().toString().equals("")) {
            builder.setMessage("请输入密码");
            builder.show();
        } else {
            LoadingDialog.show(this);
            new LoginTask().execute(username.getText().toString(), password.getText().toString());
        }
    }

    private void afterLogin() {
        LoadingDialog.dismiss();
        Intent intent = new Intent(this, LaunchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
        finish();
    }

    private void loginFailed(String reason) {
        LoadingDialog.dismiss();
        Toast.makeText(getApplicationContext(), reason, Toast.LENGTH_SHORT).show();
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            delete_password.setVisibility(password.isFocused()
                    && password.getText().length() > 0 ? View.VISIBLE
                    : View.INVISIBLE);
        }

    }

    private class LoginTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {

            return ServiceFactory.getUserService(getApplicationContext()).login(
                    params[0], params[1], getApplicationContext());

        }

        @Override
        protected void onPostExecute(Integer result) {
            System.out.println("------->result" + result);
            switch (result) {
                case 0:
                    loginFailed("登录失败，请检查网络");
                    break;
                case 1:
                    afterLogin();
                    break;
                case 2:
                    loginFailed("用户名/密码错误");
                    break;
                case 3:
                    loginFailed("登录失败，请稍后重试");
                    break;
            }
        }

    }
}
