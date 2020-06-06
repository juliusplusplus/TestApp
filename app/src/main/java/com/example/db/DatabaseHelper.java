package com.example.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

//import com.huichenghe.domain.HeartInfo;
//import com.huichenghe.domain.StepInfo;
//import com.huichenghe.domain.run_record;
//import com.huichenghe.domain.weight_record;
//import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
//import com.j256.ormlite.dao.Dao;
//import com.j256.ormlite.support.ConnectionSource;
//import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.sql.SQLException;

/**
 * TODO: 数据库应该是建两张表，分别存放学生信息和测试成绩，最后联合查询，但目前先直接用一张表处理。
 * 不对，还应该有一张表用来以blob格式存储原始音频信息。
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "HealthExam.db";
//    private DatabaseHelper(Context context)
//    {
//        super(context, TABLE_NAME, null, 1);
//
//    }
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, TABLE_NAME, null, 1);
        mContext = context;
    }

    private Context mContext;
    //数据库第一次创建时被调用
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),class VARCHAR(20),project VARCHAR(20),score VARCHAR(20))");

//        db.execSQL("CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),class VARCHAR(20))");
//        db.execSQL("CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),project VARCHAR(20),score VARCHAR(20))");
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    //软件版本号发生改变时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("ALTER TABLE student ADD phone VARCHAR(12) NULL");
        db.execSQL("drop table if exists student");
        Log.d("sqlllllllllll","upgrade" );
        Toast.makeText(mContext,"upgrade",Toast.LENGTH_SHORT).show();
        onCreate(db);
    }


//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
//        try {
//            TableUtils.createTable(connectionSource, weight_record.class);
//            TableUtils.createTable(connectionSource, run_record.class);
//            TableUtils.createTable(connectionSource, HeartInfo.class);
//            TableUtils.createTable(connectionSource, StepInfo.class);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
//
//        try {
//            TableUtils.dropTable(connectionSource, weight_record.class, true);
//            TableUtils.dropTable(connectionSource, run_record.class, true);
//            TableUtils.dropTable(connectionSource, HeartInfo.class, true);
//            TableUtils.dropTable(connectionSource, StepInfo.class, true);
//            onCreate(sqLiteDatabase, connectionSource);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private static DatabaseHelper instance;
//
//    public static synchronized DatabaseHelper getHelper(Context context)
//    {
//        if (instance == null)
//        {
//            synchronized (DatabaseHelper.class)
//            {
//                if (instance == null)
//                    instance = new DatabaseHelper(context);
//            }
//        }
//
//        return instance;
//    }
//    public Dao<weight_record, Integer> getWeightrDao() throws SQLException
//    {
//        if (weightrDao == null)
//        {
//            weightrDao = getDao(weight_record.class);
//        }
//        return weightrDao;
//    }
//    public Dao<run_record, Integer> getRunDao() throws SQLException
//    {
//        if (RunDao == null)
//        {
//            RunDao = getDao(run_record.class);
//        }
//        return RunDao;
//    }
//    public Dao<HeartInfo, Integer> getHeartInfoDao() throws SQLException
//    {
//        if (heartInfo == null)
//        {
//            heartInfo=getDao(HeartInfo.class);
//        }
//        return heartInfo;
//    }
//    public Dao<StepInfo, Integer> getStepInfoDao() throws SQLException
//    {
//        if (StepInfoDao == null)
//        {
//            StepInfoDao=getDao(StepInfo.class);
//        }
//        return StepInfoDao;
//    }

}
