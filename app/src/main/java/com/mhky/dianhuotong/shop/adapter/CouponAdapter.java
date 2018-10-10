package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.opengl.Visibility;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.BaseCouponInfo;
import com.mhky.dianhuotong.shop.bean.CouponInfo;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 * 优惠券列表适配器
 */

public class CouponAdapter extends BaseQuickAdapter<ShopCouponInfo, BaseViewHolder> {

    private Context mContext;
    private List<CouponInfo> couponInfoList = new ArrayList<>();

    public CouponAdapter(@Nullable List<ShopCouponInfo> data, Context context, List<CouponInfo> couponInfoList) {
        super(R.layout.item_coupon, data);
        mContext = context;
        this.couponInfoList = couponInfoList;
    }


    @Override
    protected void convert(BaseViewHolder helper, ShopCouponInfo item) {
        try {
            helper.setText(R.id.coupon_cut_money, item.getGradientFullCut().getSignal().getCutPrice() / 100 + "");
            helper.setText(R.id.name_1,item.getName());
            helper.setText(R.id.name_2, "滿" + item.getGradientFullCut().getSignal().getFullAmount() / 100 + "減" + item.getGradientFullCut().getSignal().getCutPrice() / 100);
            helper.setText(R.id.coupon_time, "使用期限：" + item.getStartDate() + "  ~  " + item.getEndDate());
            helper.addOnClickListener(R.id.coupon_get);
            if (couponInfoList != null && couponInfoList.size() > 0){
                for (int i = 0; i < couponInfoList.size(); i++){
                    BaseTool.logPrint("sign",item.getId() + "----" + couponInfoList.get(i).getPromotionItem().getId());
                    if (item.getId().equals(couponInfoList.get(i).getPromotionItem().getId())){
                        helper.setVisible(R.id.iv_haved_tag, true);
                        item.setHaved(true);
                        break;
                    }else {
                        item.setHaved(false);
                    }
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        }

    }
}
