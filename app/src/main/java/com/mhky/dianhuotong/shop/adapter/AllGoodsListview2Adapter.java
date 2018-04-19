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

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class AllGoodsListview2Adapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private List<GoodsBaseInfo.ChildrenBeanX> allGoodsBaseInfos;
    private Context context;
    private OnItemGridviewClickListener onItemGridviewClickListener;
    int positionParent;

    public AllGoodsListview2Adapter(List<GoodsBaseInfo.ChildrenBeanX> allGoodsBaseInfos, Context context) {
        this.allGoodsBaseInfos = allGoodsBaseInfos;
        this.context = context;
    }

    public void setOnItemGridviewClickListener(OnItemGridviewClickListener onItemGridviewClickListener1) {
        onItemGridviewClickListener = onItemGridviewClickListener1;
    }

    @Override
    public int getCount() {
        if (allGoodsBaseInfos != null) {
            return allGoodsBaseInfos.size();
        }
        return 0;
    }

    public void updateData(List<GoodsBaseInfo.ChildrenBeanX> allGoodsBaseInfos1) {
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
        positionParent = position;
        convertView = View.inflate(context, R.layout.item_all_goods_listview2, null);
        TextView textView = convertView.findViewById(R.id.all_goods_listview2_text);
        GridView gridView = convertView.findViewById(R.id.all_goods_listview2_gridview);
        RelativeLayout relativeLayout = convertView.findViewById(R.id.all_goods_listview2_more);
        AllGoodsListViewGridviewAdpter allGoodsListViewGridviewAdpter = new AllGoodsListViewGridviewAdpter(context, allGoodsBaseInfos.get(position).getChildren(), position);
        gridView.setAdapter(allGoodsListViewGridviewAdpter);
        gridView.setOnItemClickListener(this);
        textView.setText(allGoodsBaseInfos.get(position).getName());
        if (allGoodsBaseInfos.get(position).getChildren().size() != 0) {
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
