package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.DiaHuiTongBaseTitleBar;
import com.ymky.dianhuotong.main.MainIF;
import com.ymky.dianhuotong.main.adpter.DrawerLayoutAdapter;
import com.ymky.dianhuotong.main.presenter.MainActivityPrecenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainIF{
    @BindView(R.id.drawer_listview)
    ListView listView;
    @BindView(R.id.mian_titlebar)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
    private MainActivityPrecenter mainActivityPrecenter;
    private DrawerLayoutAdapter drawerLayoutAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt(){
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_go_personal);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.app_name));
        mainActivityPrecenter=new MainActivityPrecenter(this,this);
        mainActivityPrecenter.getlistData();
    }

    @Override
    public void updateListview(int[] array, ArrayList<String> list) {
        drawerLayoutAdapter=new DrawerLayoutAdapter(list,array,this);
        listView.setAdapter(drawerLayoutAdapter);
    }
}
