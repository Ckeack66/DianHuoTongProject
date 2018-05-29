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

public class DrawerLayoutAdapter extends BaseAdapter {
    private ArrayList<String> nameArraylist;
    private int[] imageArray;
    private Context context;

    public DrawerLayoutAdapter(ArrayList<String> nameArraylist, int[] imageArray,Context context) {
        this.nameArraylist = nameArraylist;
        this.imageArray = imageArray;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (nameArraylist != null && imageArray != null) {
            return nameArraylist.size();
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
            convertView = View.inflate(context, R.layout.item_drawerlayout_list, null);
            viewHolder = new ViewHolder();
            viewHolder.leftImage = convertView.findViewById(R.id.drawer_item_image);
            viewHolder.rightText = convertView.findViewById(R.id.drawer_item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.leftImage.setBackgroundResource(imageArray[position]);
        viewHolder.rightText.setText(nameArraylist.get(position));

        return convertView;
    }

    private static class ViewHolder {
        ImageView leftImage;
        TextView rightText;
    }
}
