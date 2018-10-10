package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsCategories;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 * 一级类目  适配器
 */

public class AllGoodsListview1Adapter extends BaseAdapter {

    private List<GoodsCategories> goodsCategoriesList;
    private Context mContext;
    private int indexColor = 0;   //通过index底标来渲染被选中的标签的颜色


    public AllGoodsListview1Adapter(List<GoodsCategories> goodsCategoriesList1, Context mContext) {
        this.goodsCategoriesList = goodsCategoriesList1;
        this.mContext = mContext;
    }

    public int getIndexColor() {
        return indexColor;
    }

    public void setIndexColor(int indexColor) {
        this.indexColor = indexColor;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (goodsCategoriesList != null) {
            return goodsCategoriesList.size();
        }
        return 0;
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_allgoods_listview1, null);
            viewHolder.textView = convertView.findViewById(R.id.item_all_goods_listview11_txt);
            viewHolder.linearLayout = convertView.findViewById(R.id.item_all_goods_listview11_background);
            viewHolder.view = convertView.findViewById(R.id.item_all_goods_listview11_line);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(goodsCategoriesList.get(position).getName());
        if (position == indexColor) {
            viewHolder.textView.setTextColor(Color.parseColor("#04c1ab"));
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#eeeeee"));
            viewHolder.view.setVisibility(View.GONE);
        } else {
            viewHolder.textView.setTextColor(Color.parseColor("#333333"));
            viewHolder.linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            viewHolder.view.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        LinearLayout linearLayout;
        View view;
    }
}
