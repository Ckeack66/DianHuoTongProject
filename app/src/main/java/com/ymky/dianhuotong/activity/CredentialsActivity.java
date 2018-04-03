package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.ymky.dianhuotong.invoice.VoiceGridviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        voiceGridviewAdapter = new VoiceGridviewAdapter(this);
        gridView.setAdapter(voiceGridviewAdapter);
        View view=voiceGridviewAdapter.getView(0,null,gridView);
        view.measure(0,0);
        view.getMeasuredHeight();
    }
}
