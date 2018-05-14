package com.mhky.dianhuotong.addshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class AddShopAdapter extends BaseAdapter {
    private Context context;
    private List<ShopBaseInfo> shopBaseInfoList;
    private int type;

    public AddShopAdapter(Context context, List<ShopBaseInfo> shopBaseInfoList, int type1) {
        this.context = context;
        this.shopBaseInfoList = shopBaseInfoList;
        type = type1;
    }


    @Override
    public int getCount() {
        if (shopBaseInfoList != null) {
            if (type==0){
                if (shopBaseInfoList.size() > 6) {
                    return 6;
                } else {
                    return shopBaseInfoList.size();
                }
            }else {
                return shopBaseInfoList.size();
            }

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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_addshop_listview, null);
            viewHolder.textView = convertView.findViewById(R.id.item_addshop_txt);
            viewHolder.textView1 = convertView.findViewById(R.id.item_addshop_txt1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(shopBaseInfoList.get(position).getShopname());
        viewHolder.textView1.setText(shopBaseInfoList.get(position).getAddress().getProvince() + shopBaseInfoList.get(position).getAddress().getCity() + shopBaseInfoList.get(position).getAddress().getDistrict() + shopBaseInfoList.get(position).getAddress().getTown() + shopBaseInfoList.get(position).getAddress().getRoad());
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        TextView textView1;
    }
}
