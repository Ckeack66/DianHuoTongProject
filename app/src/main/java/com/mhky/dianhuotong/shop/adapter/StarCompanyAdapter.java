package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.StarCompanyInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StarCompanyAdapter extends BaseQuickAdapter<StarCompanyInfo,BaseViewHolder> {
    private Context mContext;
    public StarCompanyAdapter(@Nullable List<StarCompanyInfo> data, Context context) {
        super(R.layout.item_search_shop,data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, StarCompanyInfo item) {
        if (!TextUtils.isEmpty(item.getLogo())){
            Picasso.get().load(item.getLogo()).centerInside().resize(BaseTool.dip2px(mContext,60),BaseTool.dip2px(mContext,60)).into((ImageView) helper.getView(R.id.item_search_shop_logo));
        }
        helper.setText(R.id.item_search_shop_name,item.getName());
        if (item.getSendAccount()>0){
            helper.setText(R.id.item_search_shop_transfer,"满"+item.getSendAccount()/100+"元免邮");
        }else {
            helper.setText(R.id.item_search_shop_transfer,"全场免邮");
        }
        helper.addOnClickListener(R.id.go_company);
    }
}
