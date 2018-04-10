package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListViewGridviewAdpter extends BaseAdapter {
    private int number;
    private Context context;

    public AllGoodsListViewGridviewAdpter(int number, Context context) {
        this.number = number;
        this.context = context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_all_goods_liatview2_gridview, null);
            viewHolder.imageView = convertView.findViewById(R.id.all_goods_listview2_gridview_image);
            viewHolder.textView = convertView.findViewById(R.id.all_goods_listview2_gridview_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
