package com.mhky.dianhuotong.main.adpter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/29.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> textArrayList;
    private int[] imageArray;

    public GridViewAdapter(Context mContext, ArrayList<String> textArrayList, int[] imageArray) {
        this.mContext = mContext;
        this.textArrayList = textArrayList;
        this.imageArray = imageArray;
    }

    @Override
    public int getCount() {
        if (textArrayList != null && imageArray != null) {
            return textArrayList.size();
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
            convertView = View.inflate(mContext, R.layout.item_main_gridview, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.item_image);
            viewHolder.textView = convertView.findViewById(R.id.item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setBackgroundResource(imageArray[position]);
        viewHolder.textView.setText(textArrayList.get(position));
        return convertView;
    }


    private static class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
