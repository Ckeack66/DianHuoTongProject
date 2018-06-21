package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.LastMinuteGoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2018/4/9.
 */

public class ShopMiaoShaAdapter extends BaseQuickAdapter<LastMinuteGoodsInfo.ContentBean, BaseViewHolder> {
    public ShopMiaoShaAdapter(@Nullable List<LastMinuteGoodsInfo.ContentBean> data) {
        super(R.layout.item_shop_miaosha, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LastMinuteGoodsInfo.ContentBean item) {
        helper.setText(R.id.shop_miaosha_item_title, item.getName());
        if (item.getPromotionInfos().size() > 0) {
            double money = item.getPromotionInfos().get(0).getCutPrice();
            helper.setText(R.id.shop_miaosha_item_money1, String.valueOf(money / 100));
        } else {
            double money = item.getPrice();
            helper.setText(R.id.shop_miaosha_item_money1, "￥"+String.valueOf(money / 100));
        }
        double money1 = item.getPrice();
        TextView textView=helper.getView(R.id.shop_miaosha_item_money2);
        textView.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);
        textView.setText("￥"+String.valueOf(money1/100));
        //helper.setText(R.id.shop_miaosha_item_money2, String.valueOf(money1/100));
        String pic = item.getPicture().split(",")[0];
        Picasso.get().load(pic).into((ImageView) helper.getView(R.id.shop_miaosha_item_image));
        helper.addOnClickListener(R.id.last_minute_goods);
    }
//    private int number;
//    private Context mContext;
//
//    public ShopMiaoShaAdapter(int number, Context mContext) {
//        this.number = number;
//        this.mContext = mContext;
//    }
//
//    @Override
//    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shop_miaosha, parent, false);
//        MyHolder myHolder = new MyHolder(view);
//        return myHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.textViewMoney2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);// 设置中划线并加清晰
//    }
//
//    @Override
//    public int getItemCount() {
//        return number;
//    }
//
//    static class MyHolder extends RecyclerView.ViewHolder {
//        ImageView imageView;
//        TextView textViewTitle;
//        TextView textViewMoney1;
//        TextView textViewMoney2;
//
//        public MyHolder(View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.shop_miaosha_item_image);
//            textViewTitle = itemView.findViewById(R.id.shop_miaosha_item_title);
//            textViewMoney1 = itemView.findViewById(R.id.shop_miaosha_item_money1);
//            textViewMoney2 = itemView.findViewById(R.id.shop_miaosha_item_money2);
//        }
//    }
}
