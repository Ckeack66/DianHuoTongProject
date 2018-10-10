package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsCategories;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListview2Adapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private List<GoodsCategories.ItemsBean> itemsBeanList;
    private Context context;
    private OnItemGridviewClickListener onItemGridviewClickListener;
    int positionParent;

    public AllGoodsListview2Adapter(List<GoodsCategories.ItemsBean> itemsBeanList, Context context) {
        this.itemsBeanList = itemsBeanList;
        this.context = context;
    }

    public void setOnItemGridviewClickListener(OnItemGridviewClickListener onItemGridviewClickListener1) {
        onItemGridviewClickListener = onItemGridviewClickListener1;
    }

    @Override
    public int getCount() {
        if (itemsBeanList != null) {
            return itemsBeanList.size();
        }
        return 0;
    }

    public void updateData(List<GoodsCategories.ItemsBean> itemsBeanList1) {
        itemsBeanList = itemsBeanList1;
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
        positionParent = position;
        convertView = View.inflate(context, R.layout.item_all_goods_listview2, null);
        TextView textView = convertView.findViewById(R.id.all_goods_listview2_text);
        TextView tv_item_more = convertView.findViewById(R.id.tv_item_more);
        GridView gridView = convertView.findViewById(R.id.all_goods_listview2_gridview);
        RelativeLayout relativeLayout = convertView.findViewById(R.id.all_goods_listview2_more);
        AllGoodsListViewGridviewAdpter allGoodsListViewGridviewAdpter = new AllGoodsListViewGridviewAdpter(context, itemsBeanList.get(position).getSubitemData(), position);
        gridView.setAdapter(allGoodsListViewGridviewAdpter);
        gridView.setOnItemClickListener(this);
        textView.setText(itemsBeanList.get(position).getName());
        tv_item_more.setText(itemsBeanList.get(position).getItemMore().getName());
        if (itemsBeanList.get(position).getSubitemData().size() != 0) {
            BaseTool.setListViewHeightBasedOnChildren(gridView, 3);
        }
        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AllGoodsListViewGridviewAdpter allGoodsListViewGridviewAdpter = (AllGoodsListViewGridviewAdpter) parent.getAdapter();
        onItemGridviewClickListener.onclickItem(allGoodsListViewGridviewAdpter.getA(), position);
    }

    public interface OnItemGridviewClickListener {
        void onclickItem(int positionParent, int positionChild);
    }
}
