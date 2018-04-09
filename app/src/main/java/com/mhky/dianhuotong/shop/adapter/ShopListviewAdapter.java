package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/9.
 */

public class ShopListviewAdapter extends BaseAdapter {
    private Context mContext;
    private int number;

    public ShopListviewAdapter(Context mContext, int numbers) {
        this.mContext = mContext;
        this.number = numbers;
    }

    @Override
    public int getCount() {
        return number;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = View.inflate(mContext, R.layout.item_goods1, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.shop_good1_imageview);
            viewHolder.textView1 = convertView.findViewById(R.id.shop_good1_title);
            viewHolder.textView2 = convertView.findViewById(R.id.shop_good1_title1);
            viewHolder.textView3 = convertView.findViewById(R.id.shop_good1_money);
            viewHolder.textView4 = convertView.findViewById(R.id.shop_good1_shangjia);
            viewHolder.textView5 = convertView.findViewById(R.id.shop_good1_number);
            convertView.setTag(viewHolder);
        } else {
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

    }
}
