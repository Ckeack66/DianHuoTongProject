package com.mhky.dianhuotong.shop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhky.dianhuotong.R;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsInfoInstructionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsInfoInstructionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder unbinder;
    @BindView(R.id.goods_info_instruction)
    WebView webView;


    public GoodsInfoInstructionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoodsInfoInstructionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsInfoInstructionFragment newInstance(String param1, String param2) {
        GoodsInfoInstructionFragment fragment = new GoodsInfoInstructionFragment();
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
        View view = inflater.inflate(R.layout.fragment_goods_info_instruction, container, false);
        unbinder = ButterKnife.bind(this, view);
        if (mParam1 != null && !mParam1.equals("")) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setLoadWithOverviewMode(true);
            webView.loadDataWithBaseURL(null, mParam1, "text/html", "utf-8", null);


        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
