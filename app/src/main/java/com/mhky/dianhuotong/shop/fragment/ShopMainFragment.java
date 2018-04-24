package com.mhky.dianhuotong.shop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopMainFragment extends Fragment {
    @BindView(R.id.shop_main_tab_txt1)
    TextView textViewTab1;
    @BindView(R.id.shop_main_tab_txt2)
    TextView textViewTab2;
    @BindView(R.id.shop_main_tab_img1)
    ImageView imageViewTab1;
    @BindView(R.id.shop_main_tab_img2)
    ImageView imageViewTab2;
    @BindView(R.id.shop_main_rv)
    RecyclerView recyclerView;
    private int chooseOldNumber = -1;
    private boolean tabIsOpen = false;
    private Unbinder unbinder;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ShopMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopMainFragment newInstance(String param1, String param2) {
        ShopMainFragment fragment = new ShopMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.shop_main_child_tab1)
    void selectTab1() {
        setTabStateTrue(1);
    }

    @OnClick(R.id.shop_main_child_tab2)
    void selectTab2() {
        setTabStateTrue(2);
    }


    private void hideWindow() {

    }

    private void setTabStateTrue(int newNumber) {

        if (newNumber == chooseOldNumber && tabIsOpen) {
            setTabStateFalse(newNumber);
            hideWindow();
            return;
        } else if (newNumber == chooseOldNumber && !tabIsOpen) {
            setTabOpen(newNumber);
            return;
        } else if (newNumber != chooseOldNumber && !tabIsOpen) {
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        } else if (newNumber != chooseOldNumber && tabIsOpen) {
            hideWindow();
            setTabStateFalse(chooseOldNumber);
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        }

    }

    private void setTabOpen(int number) {
        switch (number) {
            case 1:
                textViewTab1.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewTab1.setImageResource(R.drawable.icon_choose_selecte);
//                PopupWindowCompat.showAsDropDown(goodsTypePopupwindow, tabI, 0, 0, Gravity.LEFT);
                tabIsOpen = true;
                break;
            case 2:
                textViewTab2.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewTab2.setImageResource(R.drawable.icon_choose_selecte);
//                PopupWindowCompat.showAsDropDown(sortPopupwindow, tabI, 0, 0, Gravity.LEFT);
                tabIsOpen = true;
                break;
        }
    }

    private void setTabStateFalse(int number) {
        switch (number) {
            case 1:
                textViewTab1.setTextColor(getResources().getColor(R.color.color333333));
                imageViewTab1.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
            case 2:
                textViewTab2.setTextColor(getResources().getColor(R.color.color333333));
                imageViewTab2.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
        }

    }

}
