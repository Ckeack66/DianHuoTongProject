package com.mhky.dianhuotong.credential.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class CredentialBaseAdapter extends BaseAdapter {
    private Context context;
    private List<CredentialBaseTypeInfo> arrayList;

    public CredentialBaseAdapter(Context context, List<CredentialBaseTypeInfo> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public void updateDate(List<CredentialBaseTypeInfo> arrayList1) {
        arrayList = arrayList1;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
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
            convertView = View.inflate(context, R.layout.item_credentail_listview, null);
            viewHolder.textView = convertView.findViewById(R.id.credential_listview_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(arrayList.get(position).getName());
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
    }
}
