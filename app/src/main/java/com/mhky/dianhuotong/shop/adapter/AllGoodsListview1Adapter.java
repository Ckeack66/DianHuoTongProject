package com.mhky.dianhuotong.shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListview1Adapter extends BaseAdapter {
    private int number;
    private Context mContext;

    public int getIndexColor() {
        return indexColor;
    }

    public void setIndexColor(int indexColor) {
        this.indexColor = indexColor;
    }

    private int indexColor = 0;

    public AllGoodsListview1Adapter(int number, Context mContext) {
        this.number = number;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return number;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_allgoods_listview1, null);
            viewHolder.textView = convertView.findViewById(R.id.item_all_goods_listview11_txt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (position == indexColor) {
            viewHolder.textView.setTextColor(R.color.color04c1ab);
            viewHolder.textView.setBackgroundColor(R.color.colorF7F7F7);
        } else {
            viewHolder.textView.setTextColor(R.color.color333333);
            viewHolder.textView.setBackgroundColor(R.color.colorWhite);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
    }
}
