package com.ymky.dianhuotong.dingdan.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ymky.dianhuotong.R;

/**
 * Created by Administrator on 2018/3/30.
 */

public class BillActivityListViewAdapter extends BaseAdapter {
    private Context mContext;
    private int number;

    public BillActivityListViewAdapter(Context mContext, int number) {
        this.mContext = mContext;
        this.number = number;
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
            convertView = View.inflate(mContext, R.layout.item_bill_listvew, null);
            viewHolder = new ViewHolder();
            viewHolder.dateTxt = convertView.findViewById(R.id.bill_listview_item_date);
            viewHolder.textViewMoney1 = convertView.findViewById(R.id.bill_listview_item_money1);
            viewHolder.textViewMoney2 = convertView.findViewById(R.id.bill_listview_item_money2);
            viewHolder.textViewMoney3 = convertView.findViewById(R.id.bill_listview_item_money3);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView dateTxt;
        TextView textViewMoney1;
        TextView textViewMoney2;
        TextView textViewMoney3;
    }
}
