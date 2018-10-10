package com.mhky.dianhuotong.invoice;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.QualicationInfo;
import com.mhky.dianhuotong.base.BaseTool;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/31.
 */

public class VoiceGridviewAdapter extends BaseAdapter {
    private Context context;
    private int number = 1;
    private ArrayList<QualicationInfo.QualificationListBean> qualicationInfoArrayList;
    private static final String TAG = "VoiceGridviewAdapter";

    public VoiceGridviewAdapter(Context context, ArrayList<QualicationInfo.QualificationListBean> qualicationInfoArrayList) {
        this.context = context;
        this.qualicationInfoArrayList = qualicationInfoArrayList;
        if (qualicationInfoArrayList != null) {
            number = qualicationInfoArrayList.size() + 1;
        }
    }

    public void updateData(ArrayList<QualicationInfo.QualificationListBean> qualicationInfoArrayListNew) {
        qualicationInfoArrayList = qualicationInfoArrayListNew;
        if (qualicationInfoArrayList != null) {
            number = qualicationInfoArrayList.size() + 1;
        }
        //为了设置GridView的高度，此行代码移动到Activity出
//        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return number;
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
        if (position != number - 1) {
            convertView = View.inflate(context, R.layout.item_invoice_fragment1_gridview, null);
            ImageView imageView = convertView.findViewById(R.id.voice_gridview_item_image);
            TextView textView1 = convertView.findViewById(R.id.voice_gridview_item_txt1);
            if(!BaseTool.isEmpty(qualicationInfoArrayList.get(position).getUrl())){
                Picasso.get().load(qualicationInfoArrayList.get(position).getUrl()).into(imageView);
            }
            textView1.setText(qualicationInfoArrayList.get(position).getName());
        } else {
            convertView = View.inflate(context, R.layout.item_uploade_invoice_2, null);
        }
        return convertView;
    }

}
