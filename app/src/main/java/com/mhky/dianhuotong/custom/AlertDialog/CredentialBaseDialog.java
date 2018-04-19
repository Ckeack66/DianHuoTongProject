package com.mhky.dianhuotong.custom.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.credential.adapter.CredentialBaseAdapter;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class CredentialBaseDialog extends Dialog implements View.OnClickListener, AdapterView.OnItemClickListener {
    private TextView textViewTitle;
    private ListView listViewBody;
    private TextView textViewLeft;
    private CredentialBaseDialogListener baseDialogListener;
    private String tag;
    private CredentialBaseAdapter credentialBaseAdapter;

    public CredentialBaseDialog(@NonNull Context context, CredentialBaseDialogListener baseDialogListener1) {
        super(context, R.style.custom_dialog);
        this.baseDialogListener = baseDialogListener1;
        View view = View.inflate(context, R.layout.dialog_choose_credential, null);
        textViewTitle = view.findViewById(R.id.base_dialog_title);
        listViewBody = view.findViewById(R.id.base_dialog_body);
        textViewLeft = view.findViewById(R.id.base_dialog_button_left);
        setContentView(view);
        textViewLeft.setOnClickListener(this);
        listViewBody.setOnItemClickListener(this);


    }

    public CredentialBaseDialog(@NonNull Context context, CredentialBaseDialogListener baseDialogListener1, CredentialBaseAdapter credentialBaseAdapter1, String title, String left, String mtag) {
        this(context, baseDialogListener1);
        //super(context,R.style.custom_dialog);
        credentialBaseAdapter = credentialBaseAdapter1;
        textViewTitle.setText(title);
        listViewBody.setAdapter(credentialBaseAdapter);
        textViewLeft.setText(left);
        tag = mtag;

    }

    public CredentialBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CredentialBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_dialog_button_left:
                if (baseDialogListener != null) {
                    baseDialogListener.onClickCredentialBaseDialogLeft(tag);
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        baseDialogListener.OnClickCredentialBaseDialogListviewItem(position);
    }

    public void updateAdapter(List<CredentialBaseTypeInfo> list) {
        credentialBaseAdapter.updateDate(list);
    }

    public interface CredentialBaseDialogListener {
        void onClickCredentialBaseDialogLeft(String iTag);

        void OnClickCredentialBaseDialogListviewItem(int position);
    }
}
