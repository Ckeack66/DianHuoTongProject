package com.ymky.dianhuotong.invoice;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ymky.dianhuotong.R;

/**
 * Created by Administrator on 2018/3/31.
 */

public class VoiceGridviewAdapter extends BaseAdapter {
    private Context context;

    public VoiceGridviewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
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
            viewHolder=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_invoice_fragment1_gridview,null);
            viewHolder.imageView=convertView.findViewById(R.id.voice_gridview_item_image);
            viewHolder.textView1=convertView.findViewById(R.id.voice_gridview_item_txt1);
            viewHolder.textView2=convertView.findViewById(R.id.voice_gridview_item_txt2);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    private static class ViewHolder {
        ImageView imageView;
        TextView textView1;
        TextView textView2;
    }
}
