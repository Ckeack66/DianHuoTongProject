package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.bean.SearchCompanyInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchCompanyAdapter extends BaseQuickAdapter<SearchCompanyInfo.ContentBean,BaseViewHolder> {
    private Context mC;
    public SearchCompanyAdapter(@Nullable List<SearchCompanyInfo.ContentBean> data,Context qContext) {
        super(R.layout.item_search_shop,data);
        mC=qContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchCompanyInfo.ContentBean item) {
        if (!TextUtils.isEmpty(item.getLogo())){
            Picasso.get().load(item.getLogo()).centerInside().resize(BaseTool.dip2px(mC,60),BaseTool.dip2px(mC,60)).into((ImageView) helper.getView(R.id.item_search_shop_logo));
        }
        helper.setText(R.id.item_search_shop_name,item.getName());
        if (item.getSendAccount()>0){
            helper.setText(R.id.item_search_shop_transfer,"满"+item.getSendAccount()/100+"免邮");
        }else {
            helper.setText(R.id.item_search_shop_transfer,"全场免邮");
        }

    }
}
