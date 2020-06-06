package com.example.asr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.app.Activity ;
import android.util.Log ;
import android.view.LayoutInflater;
import android.view.View ;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button ;
import android.widget.EditText ;
import android.widget.Toast ;

import androidx.fragment.app.Fragment;

import com.example.db.DatabaseHelper;
import com.example.testapp.R;
import com.example.util.ChinaStringUtil;
import com.example.util.ReadFileUtil;
import com.iflytek.cloud.ErrorCode ;
import com.iflytek.cloud.InitListener ;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.RecognizerListener ;
import com.iflytek.cloud.RecognizerResult ;
import com.iflytek.cloud.SpeechConstant ;
import com.iflytek.cloud.SpeechError ;
import com.iflytek.cloud.SpeechRecognizer ;
import com.iflytek.cloud.SpeechSynthesizer ;
import com.iflytek.cloud.SpeechUtility ;
import com.iflytek.cloud.SynthesizerListener ;
import com.iflytek.cloud.ui.RecognizerDialog ;
import com.iflytek.cloud.ui.RecognizerDialogListener ;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;

import org.json.JSONException ;
import org.json.JSONObject ;

import java.util.HashMap ;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap ;
import java.util.Map;
import java.util.Set;



public class Fragmnet_exam extends Fragment implements View.OnClickListener {

//    QMUITabSegment mTabSegment = new QMUITabSegment(context());
    private static final String TAG =Fragmnet_exam.class.getSimpleName();
    private EditText et_input;
    private Button btn_startspeech, btn_startspeektext ;
    private Context mContext;
    private String SpeechText;
    private DatabaseHelper dbHelper;
    private View v;
    private int i = 1;
    private int ret = 0;
    private Set a = new HashSet();
    //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
    SpeechRecognizer mIat = SpeechRecognizer.createRecognizer( getActivity(), null); //语音识别器

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String , String>();
    private HashMap<String, String> b = new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        ReadFileUtil.verifyStoragePermissions(getActivity().this);
        super .onCreate(savedInstanceState) ;
        mContext = getActivity();
        dbHelper = new DatabaseHelper(mContext,"",null,2);
        initView() ;
        initSpeech() ;
        a.add("1");
        a.add("2");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        path = context.getFilesDir().getPath();
        v = inflater.inflate(R.layout.fragment_my, container, false);
        initView(v);
        initEvent();
    }
    private void initView() {
        v = inflater.inflate(R.layout.fragment_my, container, false);
        setContentView(R.layout.activity_main) ;
        et_input = (EditText) findViewById(R.id.et_input );
        btn_startspeech = (Button) findViewById(R.id.btn_startspeech );
        btn_startspeektext = (Button) findViewById(R.id.btn_startspeektext );
        btn_startspeech .setOnClickListener(this) ;
        btn_startspeektext .setOnClickListener(this) ;

        Button add_data = (Button) findViewById(R.id.add_data);
        add_data.setOnClickListener(this);
        Button select_data = (Button) findViewById(R.id.select_data);
        select_data.setOnClickListener(this);
        Button update_dic = (Button) findViewById(R.id.update_dic);
        update_dic.setOnClickListener(this);


    }

    private void initSpeech() {
        // 将“12345678”替换成您申请的 APPID，申请地址： http://www.xfyun.cn
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility. createUtility( this, SpeechConstant. APPID + "=5e92eb93" );
    }

    private void updateData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("score",15);
        Log.d(TAG,"updateData "+SpeechText);
        db.update("student",values,"name=?",new String[]{SpeechText});
        Iterator it = mIatResults.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object key = entry.getKey();
            String value = entry.getValue().toString();
            decode(value);
            System.out.println("key=" + key + " value=" + value);
        }
        showTip(mIatResults.toString());
    }

    private void decode(String str){
        int index=0,i = 0;
        int len = str.length();
        while(i<len){
            String c=String.valueOf(str.charAt(i));
            if (a.contains(c)) {
                index = i;
                break;
            }
            i += 1;
        }

        b.put(str.substring(0, index), str.substring(index));
        // b.put(ChinaStringUtil.subChString(str,0, index), ChinaStringUtil.subChString(str,index+1, len-index+1));
    }
    private void strTomap(String str,Map<String,String> results){

    }
    private void addData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        //第一条
        values.put("name","姚瑶");
        values.put("class","202001");
        values.put("project","引体向上");
        values.put("score","");
        db.insert("student",null,values);
        values.clear();

        //第二条
        values.put("name","张三");
        values.put("class","202001");
        values.put("project","引体向上");
        values.put("score","");
        db.insert("student",null,values);
        Toast.makeText(mContext, "addData succeeded", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"adddd data");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startspeech: //语音识别（把声音转文字）
                startSpeechDialog();
                break;
            case R.id. btn_startspeektext:// 语音合成（把文字转声音）
                dbHelper.getWritableDatabase();
//                Intent i = new Intent(MainActivity.this , Display.class);
//                startActivity(i);
                break;
            case R.id.add_data:
                addData();
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values1 = new ContentValues();
//                values1.put("name", "呵呵~" + i);
//                i++;
//                //参数依次是：表名，强行插入null值得数据列的列名，一行记录的数据
//                db.insert("student", null, values1);
//                Toast.makeText(mContext, "插入完毕~", Toast.LENGTH_SHORT).show();
                break;
            case R.id.select_data:
                getCount();
                break;
            case R.id.update_dic:
                /*
                String contents = FucUtil.readFile(mContext, "userwords","utf-8");
//                showContacts.setText(contents);

                // 指定引擎类型
                mIat.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
                mIat.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
                ret = mIat.updateLexicon("userword", contents, mLexiconListener);
                if (ret != ErrorCode.SUCCESS)
                    showTip("上传热词失败,错误码：" + ret+",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
                 */
                updateData();
//                String test = db.execSQL("select * from student");
                break;
        }

    }
//    private void qmuiTabSegment()
//    {
//        new QMUITabSegment(this)
//                .setupWithViewPager();
//
//    }
public long getCount()
{
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor =  db.rawQuery("SELECT COUNT (*) FROM student",null);
    cursor.moveToFirst();
    long result = cursor.getLong(0);
    cursor.close();
    Log.d(TAG,"select data " + result);
    showTip(String.valueOf(result));
    return result;
}
    private void qmuiTest(){
        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("QMUI对话框标题")
                .setMessage("这是QMUI框架对话框的内容")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();

                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    private void speekText() {
        //1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer( this, null);
//2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
//设置发音人（更多在线发音人，用户可参见 附录 13.2
        mTts.setParameter(SpeechConstant. VOICE_NAME, "vixyun" ); // 设置发音人
        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
        mTts.setParameter(SpeechConstant. VOLUME, "80" );// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant. TYPE_CLOUD); //设置云端
//设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
//保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
//仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
//3.开始合成
        mTts.startSpeaking( et_input.getText().toString(), new MySynthesizerListener()) ;

    }

    class MySynthesizerListener implements SynthesizerListener {

        @Override
        public void onSpeakBegin() {
            showTip(" 开始播放 ");
        }

        @Override
        public void onSpeakPaused() {
            showTip(" 暂停播放 ");
        }

        @Override
        public void onSpeakResumed() {
            showTip(" 继续播放 ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos ,
                                     String info) {
            // 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                showTip("播放完成 ");
            } else if (error != null ) {
                showTip(error.getPlainDescription( true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话 id为null
            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //     Log.d(TAG, "session id =" + sid);
            //}
        }
    }

    private void startSpeechDialog() {
        //1. 创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener()) ;
        //2. 设置accent、 language等参数
        mDialog.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mDialog.setParameter(SpeechConstant. ACCENT, "mandarin" );
        mDialog.setParameter(SpeechConstant.ASR_PTT, "0");
        // 若要将UI控件用于语义理解，必须添加以下参数设置，设置之后 onResult回调返回将是语义理解
        // 结果
        // mDialog.setParameter("asr_sch", "1");
        // mDialog.setParameter("nlp_version", "2.0");
        //3.设置回调接口
        mDialog.setListener( new MyRecognizerDialogListener()) ;
        //4. 显示dialog，接收语音输入
        mDialog.show() ;
    }

    /**
     * 上传联系人/词表监听器。
     */
    private LexiconListener mLexiconListener = new LexiconListener() {

        @Override
        public void onLexiconUpdated(String lexiconId, SpeechError error) {
            if (error != null) {
                showTip(error.toString());
            } else {
                showTip(getString(R.string.text_upload_success));
            }
        }
    };

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
            Log. d(TAG, "结束录音");
        }
        /**
         * @param results
         * @param isLast  是否说完了
         */
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String result = results.getResultString(); //为解析的
//            showTip(result) ;
            System. out.println(" 没有解析的 :" + result);
//            Log.d (TAG, "没有解析的 " + results.getResultString());

            SpeechText = JsonParser.parseIatResult(result) ;//解析过后的
            System. out.println(" 解析后的 :" + SpeechText);
//            Log.d (TAG, "解析后的 " + text);

//            String filePath = "/sdcard/tmp/";
//            String fileName = "data.txt";
//            ReadFileUtil.writeTxtToFile("Wx:lcti1314", filePath, fileName);
            showTip(SpeechText);
            String sn = null;
            // 读取json结果中的 sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString()) ;
                sn = resultJson.optString("sn" );
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults .put(sn, SpeechText) ;//没有得到一句，添加到

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults .get(key));
            }

            et_input.setText(resultBuffer.toString());// 设置输入框的文本
            et_input .setSelection(et_input.length()) ;//把光标定位末尾
        }



        @Override
        public void onError(SpeechError speechError) {

        }
    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                showTip("初始化失败 ");
            }

        }
    }

    /**
     * 语音识别
     */
    private void startSpeech() {
        //1. 创建SpeechRecognizer对象，第二个参数： 本地识别时传 InitListener
//        SpeechRecognizer mIat = SpeechRecognizer.createRecognizer( this, null); //语音识别器
        //2. 设置听写参数，详见《 MSC Reference Manual》 SpeechConstant类
        mIat.setParameter(SpeechConstant. DOMAIN, "iat" );// 短信和日常用语： iat (默认)
        mIat.setParameter(SpeechConstant. LANGUAGE, "zh_cn" );// 设置中文
        mIat.setParameter(SpeechConstant. ACCENT, "mandarin" );// 设置普通话
        mIat.setParameter(SpeechConstant.ASR_PTT, "0");
        mIat.setParameter("dwa","wpgs");
        //3. 开始听写
        mIat.startListening( mRecoListener);
    }


    // 听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener() {
        // 听写结果回调接口 (返回Json 格式结果，用户可参见附录 13.1)；
//一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
//关于解析Json的代码可参见 Demo中JsonParser 类；
//isLast等于true 时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d (TAG, "听写监听器"+results.getResultString());
            System.out.println(results.getResultString()) ;
            showTip(results.getResultString()) ;
        }

        // 会话发生错误回调接口
        public void onError(SpeechError error) {
            showTip(error.getPlainDescription(true)) ;
            // 获取错误码描述
            Log. e(TAG, "error.getPlainDescription(true)==" + error.getPlainDescription(true ));
        }

        // 开始录音
        public void onBeginOfSpeech() {
            showTip(" 开始录音 ");
        }

        //volume 音量值0~30， data音频数据
        public void onVolumeChanged(int volume, byte[] data) {
            showTip(" 声音改变了 ");
        }

        // 结束录音
        public void onEndOfSpeech() {
            showTip(" 结束录音 ");
            Log. d(TAG, "结束录音");
        }

        // 扩展用接口
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
        }
    };

    private void showTip (String data) {
        Toast.makeText( this, data, Toast.LENGTH_SHORT).show() ;
    }
}
