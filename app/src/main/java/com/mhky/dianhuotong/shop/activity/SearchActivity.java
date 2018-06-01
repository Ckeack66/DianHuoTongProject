package com.mhky.dianhuotong.shop.activity;

import android.Manifest;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.CredentialBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.database.SearchHistoryTable;
import com.mhky.dianhuotong.shop.adapter.SearchHistoryAdapter;
import com.mhky.dianhuotong.shop.bean.CouponInfo;
import com.mhky.dianhuotong.shop.custom.BaseListDialog;
import com.mhky.dianhuotong.shop.custom.BasePopupwindow;
import com.mhky.dianhuotong.shop.custom.CouponDialog;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.pgyersdk.crash.PgyCrashManager;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@PermissionsRequestSync(permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, value = {101, 102})
public class SearchActivity extends BaseActivity implements BaseListDialog.CredentialBaseDialogListener {
    @BindView(R.id.search_view)
    SearchView searchView;
    @BindView(R.id.search_spinner_text)
    TextView textViewSearchSp;
    @BindView(R.id.search_spinner)
    LinearLayout linearLayout;
    @BindView(R.id.search_history)
    RecyclerView recyclerViewHistory;
    @BindView(R.id.base_body)
    RelativeLayout relativeLayoutBody;
    @BindView(R.id.base_tips)
    RelativeLayout relativeLayoutTips;
    private String[] stringList;
    private BaseListDialog baseListDialog;
    int type = 0;
    private Context context;
    private List<SearchHistoryTable> searchHistoryTableList;
    private SearchHistoryAdapter searchHistoryAdapter;
    private SQLiteDatabase sqLiteDatabase;
    private boolean a;
    private boolean b;
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        context = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    private void init() {
        stringList = getResources().getStringArray(R.array.search_arr);
        baseListDialog = new BaseListDialog(this, this, Arrays.asList(stringList), "搜索类型", "取消", "1");
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(false);
        Permissions4M.get(this).requestSync();
    }

    private void initDatabase() {
        sqLiteDatabase = LitePal.getDatabase();
        BaseTool.logPrint(TAG, sqLiteDatabase.getPath());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    BaseTool.logPrint(TAG, "已提交");
                    List<SearchHistoryTable> list = DataSupport.where("body=?", query).find(SearchHistoryTable.class);
                    BaseTool.logPrint("T----", list.size() + "");
                    SearchHistoryTable searchHistoryTable = new SearchHistoryTable();
                    searchHistoryTable.setBody(query);
                    if (type == 0) {
                        searchHistoryTable.setType(0);
                        if (list.size() == 0) {
                            searchHistoryTable.save();
                            BaseTool.logPrint("存商品搜索数据", String.valueOf(searchHistoryTable.isSaved()));
                        } else {
                            if (list.get(0).getType() != 0) {
                                searchHistoryTable.save();
                                BaseTool.logPrint("存商品搜索数据", String.valueOf(searchHistoryTable.isSaved()));
                            }

                        }
                        goSearch(0, query);
                    } else if (type == 1) {
                        searchHistoryTable.setType(1);
                        if (list.size() > 0) {
                            if (list.get(0).getType() != 1) {
                                searchHistoryTable.save();
                                BaseTool.logPrint("存店铺搜索数据", String.valueOf(searchHistoryTable.isSaved()));
                            }
                        }
                        goSearch(1, query);
                    }
                }catch (Exception e){

                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                BaseTool.logPrint(TAG, "正在输入");
                return true;
            }
        });
        searchHistoryTableList = DataSupport.findAll(SearchHistoryTable.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewHistory.setLayoutManager(linearLayoutManager);
        if (searchHistoryTableList != null) {
            relativeLayoutTips.setVisibility(View.GONE);
            relativeLayoutBody.setVisibility(View.VISIBLE);
        } else {
            relativeLayoutTips.setVisibility(View.VISIBLE);
            relativeLayoutBody.setVisibility(View.GONE);
            searchHistoryTableList = new ArrayList<>();

        }
        searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryTableList, context);
        recyclerViewHistory.setAdapter(searchHistoryAdapter);
    }

    private void goSearch(int type, String body) {
        String type1 = "";
        Class cls = null;
        if (type == 0) {
            type1 = "104";
            cls = SearchGoodsActivity.class;
        } else if (type == 1) {
            type1 = "100";
            cls = SearchCompanyActivity.class;
        }
        if (cls != null) {
            Bundle bundle = new Bundle();
            bundle.putString("type", type1);
            bundle.putString("shopnm", body);
            BaseTool.goActivityWithData(context, cls, bundle);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionsGranted({101, 102})
    void getLocationGrantsucess(int code) {
        switch (code) {
            case 101:
                a = true;
                if (b) {
                    initDatabase();
                }
                break;
            case 102:
                b = true;
                if (a) {
                    initDatabase();
                }
                break;
        }
    }

    @PermissionsDenied({101, 102})
    void getLocationGrantFaile(int code) {
        switch (code) {
            case 101:
                ToastUtil.makeText(this, "请打开定位权限", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                break;
        }
    }

    @OnClick(R.id.search_spinner)
    void selectType() {

//        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        Log.d(TAG, "selectType: "+inputMethodManager);
//        if (inputMethodManager!=null){
//            inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(),0);
//        }
//        //PopupWindowCompat.showAsDropDown(basePopupwindow,linearLayout,0,0, Gravity.LEFT);
//        basePopupwindow.showAsDropDown(linearLayout);
//        basePopupwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        basePopupwindow.update();
        baseListDialog.show();
    }


    @Override
    public void OnClickCredentialBaseDialogListviewItem(int position) {
        if (position == 0) {
            textViewSearchSp.setText("商品");
            type = 0;
        } else {
            textViewSearchSp.setText("店铺");
            type = 1;
        }
    }
}
