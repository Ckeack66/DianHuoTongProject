package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.ShopCredentialInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class CredentialRecycleViewAdapter extends BaseSectionQuickAdapter<ShopCredentialInfo, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    private Context mContext;

    public CredentialRecycleViewAdapter(int layoutResId, int sectionHeadResId, List<ShopCredentialInfo> data, Context context) {
        super(layoutResId, sectionHeadResId, data);
        mContext = context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, ShopCredentialInfo item) {
        helper.setText(R.id.voice_gridview_item_txt1, item.getShopCredentialBaseInfo().getName());
        if (item.getShopCredentialBaseInfo().getUrl() != null && !item.getShopCredentialBaseInfo().getUrl().equals("")) {
            Picasso.with(mContext).load(item.getShopCredentialBaseInfo().getUrl()).into((ImageView) helper.getView(R.id.voice_gridview_item_image));
        }

    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCredentialInfo item) {

    }
}
