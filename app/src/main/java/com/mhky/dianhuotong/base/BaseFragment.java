package com.mhky.dianhuotong.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator  on  2018/9/27
 * Describe：
 */
public abstract class BaseFragment extends Fragment implements BaseView{

    /**
     * 该方法是在Activity被创建时回调，它是生命周期第一个调用的方法，
     * 我们在创建Activity时一般都需要重写该方法，然后在该方法中做一些初始化的操作，如
     * 通过setContentView设置界面布局的资源，初始化所需要的组件信息等。
     *
     * @param savedInstanceState
     */
//    private DianHuoTongBaseDialog dianHuoTongBaseDialog;
    private List<BasePresenter> list = new ArrayList<>();
    private LoadingDialog loadingDialog;

    /**
     * 获取Presenter实例，子类实现
     */
    public abstract List<BasePresenter> getPresenter();
//    public abstract BasePresenter getPresenter();

    /**
     * 初始化Presenter的实例，子类实现
     */
    public abstract void initPresenter();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingDialog = new LoadingDialog(getContext());
        initPresenter();
        //对应每个activity多个presenter时
        if (getPresenter() != null && getPresenter().size() > 0){
            for (int i = 0; i < getPresenter().size(); i++){
                if(getPresenter().get(i) != null){
                    getPresenter().get(i).attachView(this);
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //对应每个activity多个presenter时
        if (getPresenter() != null && getPresenter().size() > 0){
            for (int i = 0; i < getPresenter().size(); i++){
                if(getPresenter().get(i) != null){
                    getPresenter().get(i).detachView();
                }
            }
        }
    }

    @Override
    public void showLoading() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msd) {

    }

    @Override
    public void showErr() {

    }
}
