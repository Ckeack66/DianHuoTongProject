package com.mhky.dianhuotong.shop.custom;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.adapter.BaseDialogAdapter;
import com.mhky.dianhuotong.shop.bean.CouponInfo;

import java.util.List;

public class BaseListDialog extends Dialog implements View.OnClickListener {
    private TextView textViewTitle;
    private RecyclerView listViewBody;
    private TextView textViewLeft;
    private View viewText;
    private BaseListDialog.CredentialBaseDialogListener baseDialogListener;
    private String tag;
    private TextView textViewNull;
    private BaseDialogAdapter baseDialogAdapter;

    private BaseListDialog(@NonNull Context context, BaseListDialog.CredentialBaseDialogListener baseDialogListener1) {
        super(context, R.style.custom_dialog);
        this.baseDialogListener = baseDialogListener1;
        View view = View.inflate(context, R.layout.dialog_coupon, null);
        textViewTitle = view.findViewById(R.id.coupon_dialog_title);
        listViewBody = view.findViewById(R.id.coupon_dialog_body);
        textViewLeft = view.findViewById(R.id.coupon_dialog_button);
        textViewNull = view.findViewById(R.id.coupon_dialog_null);
        viewText=view.findViewById(R.id.coupon_dialog_line);
        textViewNull.setVisibility(View.GONE);
        viewText.setVisibility(View.GONE);
        setContentView(view);
        textViewLeft.setOnClickListener(this);


    }

    public BaseListDialog(@NonNull Context context, BaseListDialog.CredentialBaseDialogListener baseDialogListener1, List<String> couponInfoList, String title, String left, String mtag) {
        this(context, baseDialogListener1);
        //super(context,R.style.custom_dialog);
        baseDialogAdapter = new BaseDialogAdapter(couponInfoList, context);
        textViewTitle.setText(title);
        textViewLeft.setText(left);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        listViewBody.setLayoutManager(linearLayoutManager);
        baseDialogAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                baseDialogListener.OnClickCredentialBaseDialogListviewItem(position);
                dismiss();
            }
        });
        listViewBody.setAdapter(baseDialogAdapter);
        tag = mtag;

    }

    public BaseListDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseListDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coupon_dialog_button:
                dismiss();
                break;
        }
    }


    public interface CredentialBaseDialogListener {

        void OnClickCredentialBaseDialogListviewItem(int position);
    }
}
