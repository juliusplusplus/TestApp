package com.example.asr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.db.DbStudent;
import com.example.db.DbTeacher;
import com.example.testapp.R;
import com.example.util.AndroidFileUtil;
import com.google.android.material.tabs.TabLayout;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_my extends Fragment implements View.OnClickListener {
    TextView textView;
    private CircleImageView imageView;
    private String teachName="丁非真";
    private Context context;
    private Bitmap head;// 头像Bitmap
    private static String path;// sd路径
    private RelativeLayout classses;
    private View v;
    private Button uploadButton;
    private Button addButton;
    private Button showButton;
    private LayoutInflater inflater_pop;
    private TextView tv_classNum;
    private TextView tv_id;
    private TextView tv_name;
    private TextView tv_sex;
    private List<String> a = new ArrayList<String>();
    private List<String> year = new ArrayList<String>();
    private List<String> month = new ArrayList<String>();
    List<DbStudent> studentList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        path = context.getFilesDir().getPath();
        v = inflater.inflate(R.layout.fragment_my, container, false);
        initView(v);

        List<DbTeacher> teachers = LitePal.where("name=?",teachName).find(DbTeacher.class);
        tv_classNum.setText(String.valueOf(teachers.size()));

        initEvent();

        Bitmap bt = BitmapFactory.decodeFile(path + "/" + "head.jpg");// 从SD卡中找头像，转换成Bitmap

        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            imageView.setImageDrawable(drawable);
        }
    return v;
    }

    private void initEvent() {
//        weight.setOnClickListener(this);
        classses.setOnClickListener(this);
        imageView.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        tv_classNum.setOnClickListener(this);
    }

//    private void init() {
//        if (sp.getHeight(sp.getUsername()) == 0)
//            tv_height.setText("--");
//        else
//            tv_height.setText(sp.getHeight(sp.getUsername()) + "厘米");
//        if (sp.getYear(sp.getUsername()) == 0)
//            tv_birthday.setText("--");
//        else
//            tv_birthday.setText(sp.getYear(sp.getUsername()) + "年" + sp.getMonth(sp.getUsername()) + "月");
//        if (sp.getWeight1(sp.getUsername()) == 0)
//            tv_weight.setText("--");
//        else
//            tv_weight.setText(sp.getWeight1(sp.getUsername()) + "." + sp.getWeight2(sp.getUsername()) + "公斤");
//        tv_id.setText("学号："+sp.getUsername());
//        tv_name.setText(sp.getname());
//        tv_sex.setText(sp.getsex());
//    }

    private void initView(View v) {
        imageView = (CircleImageView) v.findViewById(R.id.profile_image);
        classses = (RelativeLayout) v.findViewById(R.id.classes);
        tv_id= (TextView) v.findViewById(R.id.userid);
        tv_name= (TextView) v.findViewById(R.id.username);
        tv_sex= (TextView) v.findViewById(R.id.usersex);
        uploadButton=v.findViewById(R.id.btn_upload);
        addButton=v.findViewById(R.id.btn_add);
        showButton=v.findViewById(R.id.btn_show);
        tv_classNum=v.findViewById(R.id.tv_classNum);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
//        EventBus.getDefault().unregister(this);//反注册EventBus
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:// 更换头像
                showTypeDialog();
                break;
            case R.id.classes:
//                slect_height(view);
                break;
            case R.id.btn_upload:
//                LitePal.deleteDatabase("asrDb");
//                LitePal.getDatabase();
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);// 只有设置了这个，返回的uri才能使用 getContentResolver().openInputStream(uri) 打开。
                startActivityForResult(intent, 4);
                break;
            case R.id.btn_add:
//                DbTeacher teacher = new DbTeacher();
//                DbTeacher teacher1 = new DbTeacher();
//                teacher.setName("丁非真");
//                teacher.setClasses("202001");
//                teacher.save();
//                teacher1.setName("丁非真");
//                teacher1.setClasses("202002");
//                teacher1.save();
                break;
            case R.id.btn_show:
//                List<DbTeacher> teachers = LitePal.findAll(DbTeacher.class);
//                for(DbTeacher a: teachers){
//                    Log.d("showDbTeacher","teacher name is "+a.getName());
//                    Log.d("showDbTeacher","teacher classes is "+a.getClasses());
//                    Log.d("showDbTeacher","teacher id is "+a.getId());
//                }
                break;
        }
    }

    /**
     * android 打开本地文件
     *
     * @param path    本地文件路径
     * @param context 上下文
     */
    public void openFile(String path, Context context) {
        try {
            File file = new File(path);
            Uri uri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//24 android7
                uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider", file);
            } else {
                uri = Uri.fromFile(file);
            }
            context.grantUriPermission(context.getPackageName(), uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.addCategory("android.intent.category.DEFAULT");
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent2.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Log.d("sss", "opneFile: uri " + uri.toString());
            String type = AndroidFileUtil.getMIMEtype(path);
            intent2.setDataAndType(uri, type);
            context.startActivity(intent2);
        } catch (Exception e) {
            Log.d("sss", "loadAccessorySuccess: error " + e.toString());
        }
    }

    private void showTypeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        View view = View.inflate(context, R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialog.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
        dialog.setView(view);
        dialog.show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_CANCELED) {
            switch (requestCode) {

                case 1:
                    if (resultCode == Activity.RESULT_OK) {
                        cropPhoto(data.getData());// 裁剪图片
                    }

                    break;
                case 2:
                    if (resultCode == Activity.RESULT_OK) {
                        File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                        cropPhoto(Uri.fromFile(temp));// 裁剪图片
                    }

                    break;
                case 3:
                    if (data != null) {
                        Bundle extras = data.getExtras();
                        head = extras.getParcelable("data");
                        if (head != null) {
                            setPicToView(head);// 保存在SD卡中
                            imageView.setImageBitmap(head);// 用ImageView显示出来
                        }
                    }
                    break;
                case 4:
                    Log.d("openfile", "选择的文件Uri = " + data.toString());
                    //通过Uri获取真实路径
                    final String excelPath = getRealFilePath(getActivity(), data.getData());
                    Log.d("openfile", "excelPath = " + excelPath);//    /storage/emulated/0/test.xls
                    if (excelPath.contains(".xls") || excelPath.contains(".xlsx")) {
                        Toast.makeText(getActivity(),("正在加载Excel中..."), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(),(excelPath + "上传成功"), Toast.LENGTH_SHORT).show();
                        //载入excel
//                        readExcel(excelPath);
                    } else {
                        Toast.makeText(getActivity(),("此文件不是excel格式"), Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //读取Excel表 TODO: 这里的POIFSFileSystem这种始终找不到，可能是 导入的包有问题，换了几个没搞定，先放一下
//    private void readExcel(String excelPath) {
//        try {
//            InputStream input = new FileInputStream(new File(excelPath));
//            POIFSFileSystem fs = new POIFSFileSystem(input);
//            HSSFWorkbook wb = new HSSFWorkbook(fs);
//            HSSFSheet sheet = wb.getSheetAt(0);
//            // Iterate over each row in the sheet
//            Iterator<Keyboard.Row> rows = sheet.rowIterator();
//            while (rows.hasNext()) {
//                HSSFRow row = (HSSFRow) rows.next();
//                System.out.println("Row #" + row.getRowNum());
//                //每一行 = 新建一个学生
//                DbStudent stu = new DbStudent();
//                // Iterate over each cell in the row and print out the cell"s
//                // content
//                Iterator<Cell> cells = row.cellIterator();
//                while (cells.hasNext()) {
//                    HSSFCell cell = (HSSFCell) cells.next();
//                    switch (cell.getCellType()) {
////                        case HSSFCell.CELL_TYPE_NUMERIC:
////                            System.out.println("number= " + (int) (cell.getNumericCellValue()));
////                            //自定操作,我这里写入学号
////                            stu.setSno((int) (cell.getNumericCellValue()) + "");
////                            break;
//                        case HSSFCell.CELL_TYPE_STRING:
//                            System.out.println("string= " + cell.getStringCellValue());
//                            //自定操作,我这里写入姓名
//                            stu.setName(cell.getStringCellValue());
//                            break;
//                        case HSSFCell.CELL_TYPE_BOOLEAN:
//                            System.out.println("boolean= " + cell.getBooleanCellValue());
//                            break;
//                        case HSSFCell.CELL_TYPE_FORMULA:
//                            System.out.println("formula= " + cell.getCellFormula());
//                            break;
//                        default:
//                            System.out.println("unsuported sell type");
//                            break;
//                    }
//                }
//                stu.save();
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        //刷新列表
//        getAllStudent();
//    }

    //查询所有学生
    private void getAllStudent() {
        studentList = LitePal.findAll(DbStudent.class);
    }

    /**
     * 根据Uri获取真实图片路径
     * <p/>
     * 一个android文件的Uri地址一般如下：
     * content://media/external/images/media/62026
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
//        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
//            return;
//        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "/" + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
