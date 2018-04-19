package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.AllGoodsBaseInfo;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListview2Adapter extends BaseAdapter {
    private List<AllGoodsBaseInfo.ChildrenBeanX> allGoodsBaseInfos;
    private Context context;

    public AllGoodsListview2Adapter(List<AllGoodsBaseInfo.ChildrenBeanX> allGoodsBaseInfos, Context context) {
        this.allGoodsBaseInfos = allGoodsBaseInfos;
        this.context = context;
    }


    @Override
    public int getCount() {
        if (allGoodsBaseInfos != null) {
            return allGoodsBaseInfos.size();
        }
        return 0;
    }

    public void updateData(List<AllGoodsBaseInfo.ChildrenBeanX> allGoodsBaseInfos1) {
        allGoodsBaseInfos = allGoodsBaseInfos1;
        notifyDataSetChanged();
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
        convertView = View.inflate(context, R.layout.item_all_goods_listview2, null);
        TextView textView = convertView.findViewById(R.id.all_goods_listview2_text);
        GridView gridView = convertView.findViewById(R.id.all_goods_listview2_gridview);
        AllGoodsListViewGridviewAdpter allGoodsListViewGridviewAdpter = new AllGoodsListViewGridviewAdpter(context, allGoodsBaseInfos.get(position).getChildren());

        gridView.setAdapter(allGoodsListViewGridviewAdpter);
        textView.setText(allGoodsBaseInfos.get(position).getName());
        if (allGoodsBaseInfos.get(position).getChildren().size() != 0) {
            BaseTool.setListViewHeightBasedOnChildren(gridView, 3);
        }
        return convertView;
    }
}
