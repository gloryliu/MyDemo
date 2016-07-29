package com.liu.glory.rollview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liu.zhenrong on 2016/7/29.
 */
public class MyMessageAdapter extends BaseAdapter {
    private List<String> message;
    private Context context;

    public MyMessageAdapter(Context context, List<String> message) {
        this.context = context;
        this.message = message;
    }


    @Override
    public int getCount() {
        return message!=null?message.size():0;
    }

    @Override
    public Object getItem(int position) {
        return message!=null?message.get(position):null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = View.inflate(context,R.layout.item_message,null);
            viewHolder.tv_message = (TextView) convertView.findViewById(R.id.tv_message);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String menuName = message.get(position);
        viewHolder.tv_message.setText(menuName);
        return convertView;
    }

    private class ViewHolder{
        public TextView tv_message;
    }
}
