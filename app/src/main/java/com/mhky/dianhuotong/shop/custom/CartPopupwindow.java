package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.Popupwindow3Adapter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/25.
 */

public class CartPopupwindow extends PopupWindow implements View.OnClickListener {
    private ImageView imageViewDismiss;
    private Popupwindow3Adapter popupwindow3Adapter;
    private static final String TAG = "CompanyPopupwindow";
    private CompanyPopupwindow.OnClickPopupwindow3ItemListener onClickPopupwindow3ItemListener;
    private TagFlowLayout tagFlowLayout;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private int selectNUmber = -1;
    private int goodsNumber = 0;
    private ImageView imageViewReduce;
    private ImageView imageViewPlus;
    private TextView textViewNumber;

    public CartPopupwindow(final Context context) {
        super(context);
        mContext = context;
        View view = View.inflate(context, R.layout.popupwindow_cart, null);
        setContentView(view);
        layoutInflater = LayoutInflater.from(context);
        imageViewDismiss = view.findViewById(R.id.cart_popup_dismiss);
        tagFlowLayout = view.findViewById(R.id.cart_popup_type_layout);
        imageViewReduce = view.findViewById(R.id.cart_popup_reduce);
        imageViewPlus = view.findViewById(R.id.cart_popup_plus);
        textViewNumber = view.findViewById(R.id.cart_popup_numbers);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(false);
        setTouchable(true);
        setClippingEnabled(false);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(0));
        imageViewReduce.setOnClickListener(this);
        imageViewPlus.setOnClickListener(this);
        imageViewDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setTagFlowLayout(new String[]{"到鞥", "aaaa", "bbbbbb", "ssddccfvg", "hisiksjihjsihai", "ssskdjdjiiooo", "到鞥", "aaaa", "bbbbbb", "ssddccfvg", "hisiksjihjsihai", "ssskdjdjiiooo", "到鞥", "aaaa", "bbbbbb", "ssddccfvg", "hisiksjihjsihai", "ssskdjdjiiooo"});

    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    public void setOnClickPopupwindowItemListener(CompanyPopupwindow.OnClickPopupwindow3ItemListener onClickPopupwindow3ItemListener1) {
        onClickPopupwindow3ItemListener = onClickPopupwindow3ItemListener1;
    }

    private void setTagFlowLayout(String[] strings) {
        tagFlowLayout.setAdapter(new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String o) {
                TextView tv = (TextView) layoutInflater.inflate(R.layout.view_textview,
                        tagFlowLayout, false);
                tv.setText(o);
                return tv;
            }
        });
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (position == selectNUmber) {
                    selectNUmber = -1;
                } else {
                    selectNUmber = position;
                }
                ToastUtil.makeText(mContext, "选择了" + position + "-----" + selectNUmber, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_popup_reduce:
                if (goodsNumber > 0) {
                    goodsNumber--;
                }
                break;
            case R.id.cart_popup_plus:
                goodsNumber++;
                break;
        }
        textViewNumber.setText(goodsNumber + "");
    }

    public interface OnClickPopupwindowCartListener {
        void onclick(AllCompanyInfo.ContentBean contentBean);
    }
}
