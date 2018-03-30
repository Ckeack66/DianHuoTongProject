package com.ymky.dianhuotong.dingdan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ymky.dianhuotong.R;

/**
 * Created by Administrator on 2018/3/30.
 */

public class MyselectFragmentAdapter extends BaseAdapter {
    private int number = 0;
    private Context mContext;

    public MyselectFragmentAdapter(int number, Context mContext) {
        this.number = number;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if (number != 0) {
            return number;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView=View.inflate(mContext, R.layout.item_myselect_fragment_list,null);
            viewHolder=new ViewHolder();
            viewHolder.imageView=convertView.findViewById(R.id.myselelct_fragment_listview_item_imageview);
            viewHolder.textView1=convertView.findViewById(R.id.myselelct_fragment_listview_item_layout_title);
            viewHolder.textView2=convertView.findViewById(R.id.myselelct_fragment_listview_item_layout_title1);
            viewHolder.textView3=convertView.findViewById(R.id.myselelct_fragment_listview_item_rltext2);
            viewHolder.textView4=convertView.findViewById(R.id.myselelct_fragment_listview_item_rltext3);
            viewHolder.textView5=convertView.findViewById(R.id.myselelct_fragment_listview_item_rltext11);
            viewHolder.textView6=convertView.findViewById(R.id.myselelct_fragment_listview_item_rltext21);
            viewHolder.textButton=convertView.findViewById(R.id.myselelct_fragment_listview_item_button);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    private static class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textButton;

    }
}
