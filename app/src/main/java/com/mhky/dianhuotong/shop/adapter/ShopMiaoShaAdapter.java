package com.mhky.dianhuotong.shop.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/9.
 */

public class ShopMiaoShaAdapter extends RecyclerView.Adapter<ShopMiaoShaAdapter.MyHolder> {

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewMoney1;
        TextView textViewMoney2;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.shop_miaosha_item_image);
            textViewTitle = itemView.findViewById(R.id.shop_miaosha_item_title);
            textViewMoney1 = itemView.findViewById(R.id.shop_miaosha_item_money1);
            textViewMoney2 = itemView.findViewById(R.id.shop_miaosha_item_money2);
        }
    }
}
