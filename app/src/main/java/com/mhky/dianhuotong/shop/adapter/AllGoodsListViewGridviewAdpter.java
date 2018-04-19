package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListViewGridviewAdpter extends BaseAdapter {
    private Context context;
    private List<GoodsBaseInfo.ChildrenBeanX.ChildrenBean> childrenBean;
    private int a;

    public AllGoodsListViewGridviewAdpter(Context context, List<GoodsBaseInfo.ChildrenBeanX.ChildrenBean> childrenBean, int aa) {
        this.context = context;
        this.childrenBean = childrenBean;
        a = aa;
    }

    public int getA() {
        return a;
    }

    @Override
    public int getCount() {
        if (childrenBean != null) {
            return childrenBean.size();
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
        textView.setText(childrenBean.get(position).getName());
        if (childrenBean.get(position).getPicture() != null) {
            Picasso.with(context).load(childrenBean.get(position).getPicture().toString()).into(imageView);
        }
        return convertView;
    }


}
