package com.mhky.dianhuotong.shop.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.credential.adapter.CredentialBaseAdapter;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;
import com.mhky.dianhuotong.custom.AlertDialog.CredentialBaseDialog;
import com.mhky.dianhuotong.shop.adapter.CouponDialogAdapter;
import com.mhky.dianhuotong.shop.bean.CouponInfo;

import java.util.List;

public class CouponDialog extends Dialog implements View.OnClickListener{
    private TextView textViewTitle;
    private RecyclerView listViewBody;
    private TextView textViewLeft;
    private CouponDialog.CredentialBaseDialogListener baseDialogListener;
    private String tag;
    private  TextView textViewNull;
    private CouponDialogAdapter couponDialogAdapter;

   private CouponDialog(@NonNull Context context, CouponDialog.CredentialBaseDialogListener baseDialogListener1) {
        super(context, R.style.custom_dialog);
        this.baseDialogListener = baseDialogListener1;
        View view = View.inflate(context, R.layout.dialog_coupon, null);
        textViewTitle = view.findViewById(R.id.coupon_dialog_title);
        listViewBody = view.findViewById(R.id.coupon_dialog_body);
        textViewLeft = view.findViewById(R.id.coupon_dialog_button);
        textViewNull=view.findViewById(R.id.coupon_dialog_null);
        textViewNull.setOnClickListener(this);
        setContentView(view);
        textViewLeft.setOnClickListener(this);


    }

    public CouponDialog(@NonNull Context context, CouponDialog.CredentialBaseDialogListener baseDialogListener1, List<CouponInfo> couponInfoList, String title, String left, String mtag) {
        this(context, baseDialogListener1);
        //super(context,R.style.custom_dialog);
        couponDialogAdapter = new CouponDialogAdapter(couponInfoList);
        textViewTitle.setText(title);
        textViewLeft.setText(left);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listViewBody.setLayoutManager(linearLayoutManager);
        couponDialogAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                baseDialogListener.OnClickCredentialBaseDialogListviewItem((CouponInfo) adapter.getData().get(position),tag);
                dismiss();
            }
        });
        listViewBody.setAdapter(couponDialogAdapter);
        tag = mtag;

    }

    public CouponDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CouponDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coupon_dialog_button:
                dismiss();
                break;
            case R.id.coupon_dialog_null:
                baseDialogListener.OnClickCredentialBaseDialogListviewItem(null,tag);
                dismiss();
                break;
        }
    }


    public interface CredentialBaseDialogListener {

        void OnClickCredentialBaseDialogListviewItem(CouponInfo couponInfo,String tag);
    }
}
