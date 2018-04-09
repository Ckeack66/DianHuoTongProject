package com.mhky.dianhuotong.addshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/2.
 */

public class AddShopAdapter extends BaseAdapter {
    private Context context;
    private int numberl;

    public AddShopAdapter(Context context) {
        this.context = context;
        numberl = 5;
    }

    public AddShopAdapter(Context context, int numberl) {
        this.context = context;
        this.numberl = numberl;
    }

    @Override
    public int getCount() {
        return numberl;
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
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_addshop_listview, null);
            viewHolder.textView = convertView.findViewById(R.id.item_addshop_txt);
            viewHolder.textView1 = convertView.findViewById(R.id.item_addshop_txt1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        TextView textView1;
    }
}
