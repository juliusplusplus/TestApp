package com.example.asr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import com.example.testapp.R;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.model.TableColumnWeightModel;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment_display extends Fragment implements View.OnClickListener {

    private static final String[][] DATA_TO_SHOW = { { "This", "is", "a", "test" },
            { "and", "a", "second", "test" } };
    private View v;
    private Context context;
    private TableView<String[]> tableView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.display);
//        TableView tableView = (TableView) findViewById(R.id.tableView);
//        tableView.setColumnCount(4);
//        SortableTableView sortableTableView = null;
//        sortableTableView.setColumnComparator(0, new CarProducerComparator());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context = getContext();
        v = inflater.inflate(R.layout.fragment_display, container, false);
        initView(v);
        initEvent();
//        tableView = v.findViewById(R.id.tableView);
//        tableView.setDataAdapter(new SimpleTableDataAdapter(getActivity(), DATA_TO_SHOW));
//        init();
        return v;
    }

    private void initView(View v) {
//        imageView = (CircleImageView) v.findViewById(R.id.profile_image);
//        classses = (RelativeLayout) v.findViewById(R.id.classes);
//        tv_id= (TextView) v.findViewById(R.id.userid);
//        tv_name= (TextView) v.findViewById(R.id.username);
//        tv_sex= (TextView) v.findViewById(R.id.usersex);
        TableColumnWeightModel columnModel = new TableColumnWeightModel(4);
        columnModel.setColumnWeight(1, 2);
        columnModel.setColumnWeight(2, 2);
//        tableView.setColumnModel(columnModel);
    }

    private void initEvent() {
//        weight.setOnClickListener(this);
//        classses.setOnClickListener(this);
//        imageView.setOnClickListener(this);
    }
//
//    private static class CarProducerComparator implements Comparator<Car> {
//        @Override
//        public int compare(Car car1, Car car2) {
//            return car1.getProducer().getName().compareTo(car2.getProducer().getName());
//        }
//    }
//放置数据的容器，每一个对象其实就是等于一行
//private static List<Car> CAR_LIST = new ArrayList<>();


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_display);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
        //自定义View绑定Id
//        SortableCarTableView carTableView = (SortableCarTableView) findViewById(R.id.tableView);
        //填充数据
//        carTableView.setDataAdapter(new CarTableDataAdapter(this, CAR_LIST));
        //添加监听事件
//        carTableView.addDataClickListener(new CarClickListener());

    @Override
    public void onClick(View v) {

    }

    //表单内的点触时间，以行为单位
//    private class CarClickListener implements TableDataClickListener<Car> {
//
//        @Override
//        public void onDataClicked(int rowIndex, Car clickedData) {
//            String carString = clickedData.getProducer().getName() + " " + clickedData.getName();
//            Toast.makeText(MainActivity.this, carString, Toast.LENGTH_SHORT).show();
//        }
//    }

    //塞数据！！！
//    static {
//        CarProducer audi = new CarProducer(R.mipmap.audi, "Audi");
//        Car audiA1 = new Car(audi, "A1", 150, 25000);
//        Car audiA3 = new Car(audi, "A3", 120, 35000);
//        Car audiA4 = new Car(audi, "A4", 210, 42000);
//        Car audiA5 = new Car(audi, "S5", 333, 60000);
//        Car audiA6 = new Car(audi, "A6", 250, 55000);
//        Car audiA7 = new Car(audi, "A7", 420, 87000);
//        Car audiA8 = new Car(audi, "A8", 320, 110000);
//
//        CarProducer bmw = new CarProducer(R.mipmap.bmw, "BMW");
//        Car bmw1 = new Car(bmw, "1er", 170, 25000);
//        Car bmw3 = new Car(bmw, "3er", 230, 42000);
//        Car bmwX3 = new Car(bmw, "X3", 230, 45000);
//        Car bmw4 = new Car(bmw, "4er", 250, 39000);
//        Car bmwM4 = new Car(bmw, "M4", 350, 60000);
//        Car bmw5 = new Car(bmw, "5er", 230, 46000);
//
//        CarProducer porsche = new CarProducer(R.mipmap.porsche, "Porsche");
//        Car porsche911 = new Car(porsche, "911", 280, 45000);
//        Car porscheCayman = new Car(porsche, "Cayman", 330, 52000);
//        Car porscheCaymanGT4 = new Car(porsche, "Cayman GT4", 385, 86000);
//
//        CAR_LIST.add(audiA3);
//        CAR_LIST.add(audiA1);
//        CAR_LIST.add(porscheCayman);
//        CAR_LIST.add(audiA7);
//        CAR_LIST.add(audiA8);
//        CAR_LIST.add(audiA4);
//        CAR_LIST.add(bmwX3);
//        CAR_LIST.add(porsche911);
//        CAR_LIST.add(bmw1);
//        CAR_LIST.add(audiA6);
//        CAR_LIST.add(audiA5);
//        CAR_LIST.add(bmwM4);
//        CAR_LIST.add(bmw5);
//        CAR_LIST.add(porscheCaymanGT4);
//        CAR_LIST.add(bmw3);
//        CAR_LIST.add(bmw4);
//    }

}