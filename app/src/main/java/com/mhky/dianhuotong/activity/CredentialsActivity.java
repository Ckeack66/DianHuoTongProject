package com.mhky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.invoice.VoiceGridviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CredentialsActivity extends AppCompatActivity {
    @BindView(R.id.credentials_title)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;

    @BindView(R.id.voice_upload_gridview)
    GridView gridView;
    private VoiceGridviewAdapter voiceGridviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.credentials_title));
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        voiceGridviewAdapter = new VoiceGridviewAdapter(this);
        gridView.setAdapter(voiceGridviewAdapter);
        BaseTool.setListViewHeightBasedOnChildren(gridView);
        gridView.deferNotifyDataSetChanged();
    }

    @OnClick(R.id.credentials_next)
    void goAddShopFinishActivity() {
        BaseTool.goActivityNoData(this, AddShop3Activity.class);
    }
}
