package com.mhky.dianhuotong.addshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.AdressBaseInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class AdressExpandbleListviewAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<AdressBaseInfo> list;

    public AdressExpandbleListviewAdapter(Context mContext, List<AdressBaseInfo> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (list.get(groupPosition) != null && list.get(groupPosition).getRegion() != null) {
            return list.get(groupPosition).getRegion().size();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (list != null) {
            return list.get(groupPosition);
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (list.get(groupPosition) != null && list.get(groupPosition).getRegion() != null) {
            return list.get(groupPosition).getRegion();
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        ParentViewHolder parentViewHolder = null;
//        if (convertView == null) {
//            parentViewHolder = new ParentViewHolder();
//            convertView = View.inflate(mContext, R.layout.item_adress_expandablelisteview_parent, null);
//            parentViewHolder.imageView = convertView.findViewById(R.id.adress_parent_image);
//            parentViewHolder.textView = convertView.findViewById(R.id.adress_parent_title);
//            convertView.setTag(parentViewHolder);
//        } else {
//            parentViewHolder = (ParentViewHolder) convertView.getTag();
//        }
//        if (groupPosition == 0) {
//            parentViewHolder.imageView.setVisibility(View.VISIBLE);
//        }
//        parentViewHolder.textView.setText(list.get(groupPosition).getFirstName());

        convertView = View.inflate(mContext, R.layout.item_adress_expandablelisteview_parent, null);
        ImageView imageView = convertView.findViewById(R.id.adress_parent_image);
        TextView textView = convertView.findViewById(R.id.adress_parent_title);
        RelativeLayout relativeLayout = convertView.findViewById(R.id.adress1_parent_backgroud);
        if (groupPosition == 0) {
            imageView.setVisibility(View.VISIBLE);
            relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        textView.setText(list.get(groupPosition).getFirstName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = View.inflate(mContext, R.layout.item_adress_expandablelisteview_child, null);
//            viewHolder.imageView = convertView.findViewById(R.id.adress_child_image);
//            viewHolder.textView = convertView.findViewById(R.id.adress_child_title);
//            viewHolder.view = convertView.findViewById(R.id.adress_child_line);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        if (childPosition == 0) {
//            viewHolder.view.setVisibility(View.GONE);
//        }
//        viewHolder.textView.setText(list.get(groupPosition).getRegion().get(childPosition).getName());

        convertView = View.inflate(mContext, R.layout.item_adress_expandablelisteview_child, null);
        ImageView imageView = convertView.findViewById(R.id.adress_child_image);
        TextView textView = convertView.findViewById(R.id.adress_child_title);
        View view = convertView.findViewById(R.id.adress_child_line);

        if (childPosition == 0) {
            view.setVisibility(View.GONE);
        }
        textView.setText(list.get(groupPosition).getRegion().get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private View view;
    }

    private static class ParentViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
