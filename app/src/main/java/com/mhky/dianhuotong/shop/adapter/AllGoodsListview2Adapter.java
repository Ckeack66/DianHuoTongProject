package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;

import java.util.Random;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListview2Adapter extends BaseAdapter {
    private int number1;
    private int number2;
    private Context context;

    public AllGoodsListview2Adapter(int number1, int number2, Context context) {
        this.number1 = number1;
        this.number2 = number2;
        this.context = context;
    }

    @Override
    public int getCount() {
        return number1;
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
            convertView = View.inflate(context, R.layout.item_all_goods_listview2, null);
            viewHolder.textView = convertView.findViewById(R.id.all_goods_listview2_text);
            viewHolder.gridView = convertView.findViewById(R.id.all_goods_listview2_gridview);
            viewHolder.number=new Random().nextInt(10);
            viewHolder.allGoodsListViewGridviewAdpter = new AllGoodsListViewGridviewAdpter(viewHolder.number, context);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.gridView.setAdapter(viewHolder.allGoodsListViewGridviewAdpter);
        if (viewHolder.number!=0){
            BaseTool.setListViewHeightBasedOnChildren(viewHolder.gridView,3);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        GridView gridView;
        AllGoodsListViewGridviewAdpter allGoodsListViewGridviewAdpter;
        int number;
    }
}
