package com.mhky.dianhuotong.addshop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;

import java.util.List;

public class AddShopNewAdapter extends BaseQuickAdapter<ShopBaseInfo,BaseViewHolder> {
    public AddShopNewAdapter(@Nullable List<ShopBaseInfo> data) {
        super(R.layout.item_addshop_listview,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopBaseInfo item) {
        helper.setText(R.id.item_addshop_txt,item.getShopname());
        helper.setText(R.id.item_addshop_txt1,item.getAddress().getProvince()+item.getAddress().getCity()+item.getAddress().getDistrict()+item.getAddress().getTown()+item.getAddress().getRoad());
    }
}
