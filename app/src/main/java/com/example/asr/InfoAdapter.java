package com.example.asr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import com.example.entity.Record;
import com.example.testapp.R;

public class InfoAdapter extends BaseAdapter {

    private Context mContext;
    private List<?> listItems;
    private LayoutInflater inflater;

    public InfoAdapter(List<?>listItems, Context  mContext) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return this.listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * 使用循环迭代遍历listItems中的每一个选项对象，并通过转换视图对象ConverView动态加载当前选项布局资源
     * 同时根据ListItem中的选项对象参数，动态设置其组件显示和内容
     * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /* 步骤1：创建一个空的InfoViewHolder对象 */
        InfoViewHolder infoViewHolder = null;
        /* 步骤2：判断转换视图参数convertView是否为空，若为空则实例化viewHolder对象中的组件 */
        if(convertView == null){
            infoViewHolder = new InfoViewHolder();
            /* 步骤3：转换视图绑定列表选项布局文件 */
            convertView = this.inflater.inflate(R.layout.display_item, null);
            /* 步骤4：获取列表布局中的所有组件对象 */
            infoViewHolder.txtUserName = (TextView) convertView.findViewById(R.id.txtUserName);
            infoViewHolder.txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);

            /* 步骤5：转换视图设置ViewHolder对象*/
            convertView.setTag(infoViewHolder);
        }else{
            infoViewHolder = (InfoViewHolder) convertView.getTag();
        }

        /* 步骤6：动态为每一个选项对象赋值 */
        Record record = (Record) this.listItems.get(position);
        infoViewHolder.txtUserName.setText(record.getName());
        infoViewHolder.txtInfo.setText(record.getScore());

        /* 步骤7：动态设置适配器中的组件显示 */
//        if(this.listItems.get(position).get("layoutImages").toString().equals("0")){
//            infoViewHolder.layoutImages.setVisibility(View.GONE);
//        }
//
//        if(this.listItems.get(position).get("layoutReplys").toString().equals("0")){
//            infoViewHolder.layoutReplys.setVisibility(View.GONE);
//        }
//
//        /* 步骤8：监听器绑定 */
//        infoViewHolder.btnPin.setOnClickListener(new ViewOcl(position));
//
        return convertView;
    }

    /* 自定义一个单击监听器 */
//    private class ViewOcl implements View.OnClickListener{
//        private int position;
//
//        public ViewOcl(int position) {
//            this.position = position;
//        }
//
//        @Override
//        public void onClick(View v) {
//            switch (v.getId()){
//                case R.id.btnPin:
//                    Toast.makeText(mContext, "你要回复 ["+ listItems.get(position).get("txtUserName") +"]", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    }

}