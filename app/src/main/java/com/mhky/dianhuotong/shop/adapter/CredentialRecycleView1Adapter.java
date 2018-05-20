package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.ShopCredentialBaseInfo;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2.
 */

public class CredentialRecycleView1Adapter extends BaseMultiItemQuickAdapter<ShopCredentialBaseInfo, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private Context mContext;
    private int withResult;
    private int heightResult;

    public CredentialRecycleView1Adapter(List<ShopCredentialBaseInfo> data, Context context) {
        super(data);
        addItemType(ShopCredentialBaseInfo.CREDETIAL, R.layout.item_invoice_fragment1_gridview);
        addItemType(ShopCredentialBaseInfo.CREDENTIAL_ADD, R.layout.item_uploade_invoice_2);
        mContext = context;
        float width = mContext.getResources().getDimension(R.dimen.x122);
        withResult = BaseTool.dip2px(mContext, width);
        float height = mContext.getResources().getDimension(R.dimen.x86);
        heightResult = BaseTool.dip2px(mContext, height);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCredentialBaseInfo item) {
        switch (item.getItemType()) {
            case ShopCredentialBaseInfo.CREDETIAL:
                helper.setText(R.id.voice_gridview_item_txt1, item.getName());
                if (item.getUrl() != null && !item.getUrl().equals("")) {
                    Picasso.with(mContext).load(item.getUrl()).resize(withResult, heightResult).into((ImageView) helper.getView(R.id.voice_gridview_item_image));
                }
                helper.addOnClickListener(R.id.voice_gridview_item_image);
                break;
            case ShopCredentialBaseInfo.CREDENTIAL_ADD:
                helper.addOnClickListener(R.id.add_credential);
                break;
        }
    }
}
