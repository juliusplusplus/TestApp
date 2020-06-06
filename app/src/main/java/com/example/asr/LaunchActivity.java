package com.example.asr;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.testapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView_run;
    private ImageView imageView_set;
    private ImageView imageView_add;
    private ImageView imageView_contact;
    private TextView toolbartitle;
    private LinearLayout buttom_1;
    private LinearLayout buttom_2;
    private LinearLayout buttom_3;
    private LinearLayout buttom_4;

    private ImageView iv_1;
    private ImageView iv_2;
    private ImageView iv_3;
    private ImageView iv_4;

    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;
    private TextView tv_4;

    private Fragment CurrentFragment;
    private int currentid;
    private Fragment home;
    private Fragment find;
    private Fragment my;
    private Fragment statistic;

    private String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_launch);
        initView();
        initEvent();
        restartBotton();
        iv_1.setImageResource(R.drawable.home);
        tv_1.setTextColor(0xff56abe4);
        setSelect(0);
    }

    @Override
    protected void onResume() {
        switch (currentid)
        {
            case 0:
                tv_1.setTextColor(0xff56abe4);
                iv_1.setImageResource(R.drawable.home);
                break;
            case 1:
                tv_2.setTextColor(0xff56abe4);
                iv_2.setImageResource(R.drawable.find);
                break;
            case 2:
                iv_3.setImageResource(R.drawable.statitas);
                tv_3.setTextColor(0xff56abe4);
                break;
            case 3:
                iv_4.setImageResource(R.drawable.my);
                tv_4.setTextColor(0xff56abe4);
                break;
        }
        super.onResume();
    }

    private void initEvent() {
        // 设置按钮监听
        buttom_1.setOnClickListener(this);
        buttom_2.setOnClickListener(this);
        buttom_3.setOnClickListener(this);
        buttom_4.setOnClickListener(this);
        imageView_run.setOnClickListener(this);
        imageView_set.setOnClickListener(this);
        imageView_add.setOnClickListener(this);
        imageView_contact.setOnClickListener(this);
    }

    private void initView() {
        // initActionBar();
        // 底部菜单4个Linearlayout
        imageView_run= (ImageView) findViewById(R.id.run);
        toolbartitle=(TextView)findViewById(R.id.toolbar_title);
        imageView_set= (ImageView) findViewById(R.id.set);
        imageView_add= (ImageView) findViewById(R.id.add);
        imageView_contact= (ImageView) findViewById(R.id.friend);
        this.buttom_1 = (LinearLayout) findViewById(R.id.bottom_1);
        this.buttom_2 = (LinearLayout) findViewById(R.id.bottom_2);
        this.buttom_3 = (LinearLayout) findViewById(R.id.bottom_3);
        this.buttom_4 = (LinearLayout) findViewById(R.id.bottom_4);

        // 底部菜单4个ImageView
        this.iv_1 = (ImageView) findViewById(R.id.iv_buttom1);
        this.iv_2 = (ImageView) findViewById(R.id.iv_buttom2);
        this.iv_3 = (ImageView) findViewById(R.id.iv_buttom3);
        this.iv_4 = (ImageView) findViewById(R.id.iv_buttom4);

        // 底部菜单4个菜单标题
        this.tv_1 = (TextView) findViewById(R.id.tv_buttom1);
        this.tv_2 = (TextView) findViewById(R.id.tv_buttom2);
        this.tv_3 = (TextView) findViewById(R.id.tv_buttom3);
        this.tv_4 = (TextView) findViewById(R.id.tv_buttom4);




    }


    @Override
    public void onClick(View view) {
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (view.getId()) {
            case R.id.bottom_1:
                setSelect(0);
                iv_1.setImageResource(R.drawable.home);
                tv_1.setTextColor(0xff56abe4);
                break;
            case R.id.bottom_2:
                iv_2.setImageResource(R.drawable.find);
                tv_2.setTextColor(0xff56abe4);
                setSelect(1);
                break;
            case R.id.bottom_3:
                iv_3.setImageResource(R.drawable.statitas);
                tv_3.setTextColor(0xff56abe4);
                setSelect(2);
                break;
            case R.id.bottom_4:
                iv_4.setImageResource(R.drawable.my);
                tv_4.setTextColor(0xff56abe4);
                setSelect(3);
                break;
            case R.id.run:
                break;
            case R.id.set:
//                Intent intent1=new Intent();
//                intent1.setClass(this,SetActivity.class);
//                startActivity(intent1);
                break;
            case R.id.friend:
                break;
            case R.id.add:
                break;
            default:
                break;
        }
    }

    private void restartBotton() {
        // ImageView置为灰色
        iv_1.setImageResource(R.drawable.home1 );
        iv_2.setImageResource(R.drawable.find1);
        iv_3.setImageResource(R.drawable.static1);
        iv_4.setImageResource(R.drawable.use1);

        tv_1.setTextColor(0xffa9b7b7);
        tv_2.setTextColor(0xffa9b7b7);
        tv_3.setTextColor(0xffa9b7b7);
        tv_4.setTextColor(0xffa9b7b7);
    }
    public void initActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//创建一个事务
        hideFragment(transaction);//我们先把所有的Fragment隐藏了，然后下面再开始处理具体要显示的Fragment
        switch (i) {
            case 0:
                if (home == null) {
//                    home = new Fragment_home();
//                    transaction.add(R.id.id_content, home);//将微信聊天界面的Fragment添加到Activity中
                }else {
                    transaction.show(home);
                }
                CurrentFragment=home;
                currentid=0;
                toolbartitle.setText("运动");
                imageView_run.setVisibility(View.INVISIBLE);
                imageView_set.setVisibility(View.INVISIBLE);
                imageView_contact.setVisibility(View.GONE);
                imageView_add.setVisibility(View.GONE);
                break;
            case 1:
                if (find == null) {
//                    find = new Fragment_find();
//                    transaction.add(R.id.id_content, find);
                }else {
                    transaction.show(find);
                }
                CurrentFragment=find;
                currentid=1;
                toolbartitle.setText("手环");
                imageView_run.setVisibility(View.INVISIBLE);
                imageView_set.setVisibility(View.INVISIBLE);
                imageView_add.setVisibility(View.GONE);
                imageView_contact.setVisibility(View.GONE);
                break;
            case 2:
                if (statistic == null) {
//                    statistic = new Fragment_statistic();
//                    transaction.add(R.id.id_content, statistic);
                }else {
                    transaction.show(statistic);
                }
                CurrentFragment=statistic;
                currentid=2;
                toolbartitle.setText("跑步");
                imageView_run.setVisibility(View.GONE);
                imageView_set.setVisibility(View.GONE);
                imageView_add.setVisibility(View.GONE);
                imageView_contact.setVisibility(View.GONE);
                break;
            case 3:
                if (my == null) {
                    my = new Fragment_my();
                    transaction.add(R.id.id_content, my);
                }else {
                    transaction.show(my);
                }
                CurrentFragment=my;
                currentid=3;
                toolbartitle.setText("我的");
                imageView_run.setVisibility(View.INVISIBLE);
                imageView_set.setVisibility(View.VISIBLE);
                imageView_add.setVisibility(View.GONE);
                imageView_contact.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        transaction.commit();//提交事务
    }

    private void hideFragment(FragmentTransaction transaction) {

        if (home != null) {
            transaction.hide(home);
        }
        if (find != null) {
            transaction.hide(find);
        }
        if (my != null) {
            transaction.hide(my);
        }
        if (statistic != null) {
            transaction.hide(statistic);
        }

    }



//    @Override
//    public void onSyncTimeSuccess() {
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
            exitBy2Click();
        return false;
    }

    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

}
