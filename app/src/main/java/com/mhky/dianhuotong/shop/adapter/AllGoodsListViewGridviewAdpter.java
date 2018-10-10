package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsCategories;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListViewGridviewAdpter extends BaseAdapter {

    private Context context;
    private List<GoodsCategories.ItemsBean.SubitemDataBean> subitemDataBeanList;
    private int a;

    public AllGoodsListViewGridviewAdpter(Context context, List<GoodsCategories.ItemsBean.SubitemDataBean> subitemDataBeanList, int aa) {
        this.context = context;
        this.subitemDataBeanList = subitemDataBeanList;
        a = aa;
    }

    public int getA() {
        return a;
    }

    @Override
    public int getCount() {
        if (subitemDataBeanList != null) {
            return subitemDataBeanList.size();
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
        convertView = View.inflate(context, R.layout.item_all_goods_liatview2_gridview, null);
        ImageView imageView = convertView.findViewById(R.id.all_goods_listview2_gridview_image);
        TextView textView = convertView.findViewById(R.id.all_goods_listview2_gridview_text);
        textView.setText(subitemDataBeanList.get(position).getTitle());
        if (!BaseTool.isEmpty(subitemDataBeanList.get(position).getImage())) {
            Picasso.get().load(subitemDataBeanList.get(position).getImage()).into(imageView);
        }
        return convertView;
    }

}
