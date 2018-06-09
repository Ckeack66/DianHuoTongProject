package com.mhky.dianhuotong.shop.activity;

import android.Manifest;
import android.app.Application;
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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.CredentialBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.database.SearchBaseInfo;
import com.mhky.dianhuotong.database.SearchHistoryTable;
import com.mhky.dianhuotong.greendao.DaoMaster;
import com.mhky.dianhuotong.greendao.SearchBaseInfoDao;
import com.mhky.dianhuotong.shop.adapter.SearchHistoryAdapter;
import com.mhky.dianhuotong.shop.bean.CouponInfo;
import com.mhky.dianhuotong.shop.custom.BaseListDialog;
import com.mhky.dianhuotong.shop.custom.BasePopupwindow;
import com.mhky.dianhuotong.shop.custom.CouponDialog;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.pgyersdk.crash.PgyCrashManager;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;
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
    @BindView(R.id.search_history_delete)
    FrameLayout frameLayoutDelete;
    private String[] stringList;
    private BaseListDialog baseListDialog;
    int type = 0;
    private Context context;
    private List<SearchBaseInfo> searchBaseInfoList;
    private SearchHistoryAdapter searchHistoryAdapter;
    private boolean a;
    private boolean b;
    private SearchBaseInfoDao searchBaseInfoDao;
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        context = this;
        try {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "search-data", null);
            Database database = devOpenHelper.getWritableDb();
            DaoMaster daoMaster = new DaoMaster(database);
            searchBaseInfoDao = daoMaster.newSession().getSearchBaseInfoDao();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
        Permissions4M.get(this).requestSync();

    }

    private void init() {
        stringList = getResources().getStringArray(R.array.search_arr);
        baseListDialog = new BaseListDialog(this, this, Arrays.asList(stringList), "搜索类型", "取消", "1");
        searchView.setIconified(false);
        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(false);
        initDatabase();
    }

    private void initDatabase() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    BaseTool.logPrint(TAG, "已提交");
                    List<SearchBaseInfo> list = searchBaseInfoDao.queryBuilder().where(SearchBaseInfoDao.Properties.Body.eq(query)).list();
                    BaseTool.logPrint("T----", list.size() + "");
                    SearchBaseInfo searchBaseInfo = new SearchBaseInfo();
                    searchBaseInfo.setBody(query);
                    if (type == 0) {
                        searchBaseInfo.setType(0);
                        if (list.size() == 0) {
                            searchBaseInfoDao.insert(searchBaseInfo);
                            updateData();
                            BaseTool.logPrint("存商品搜索数据", "数据库无相同数据");
                        } else {
                            for (int a = 0; a < list.size(); a++) {
                                if (list.get(a).getType() == 0) {
                                    break;
                                } else {
                                    if (a == list.size() - 1) {
                                        if (list.size() >= 20) {
                                            searchBaseInfoDao.delete(list.get(0));
                                        }
                                        searchBaseInfoDao.insert(searchBaseInfo);
                                        updateData();
                                        BaseTool.logPrint("存商品搜索数据", "数据库有相同数据");
                                    }
                                }
                            }
                        }
                        goSearch(0, query);
                    } else if (type == 1) {
                        searchBaseInfo.setType(1);
                        if (list.size() == 0) {
                            searchBaseInfoDao.insert(searchBaseInfo);
                            updateData();
                            BaseTool.logPrint("存店铺搜索数据", "数据库无相同数据");
                        } else {
                            for (int a = 0; a < list.size(); a++) {
                                if (list.get(a).getType() == 1) {
                                    break;
                                } else {
                                    if (a == list.size() - 1) {
                                        if (list.size() >= 20) {
                                            searchBaseInfoDao.delete(list.get(0));
                                        }
                                        searchBaseInfoDao.insert(searchBaseInfo);
                                        updateData();
                                        BaseTool.logPrint("存店铺搜索数据", "数据库有相同数据");
                                    }
                                }
                            }
                        }
                        goSearch(1, query);
                    }
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(context,e);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                BaseTool.logPrint(TAG, "正在输入");
                return true;
            }
        });
        searchBaseInfoList = searchBaseInfoDao.queryBuilder().list();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewHistory.setLayoutManager(linearLayoutManager);
        if (searchBaseInfoList != null&&searchBaseInfoList.size()>0) {
            relativeLayoutTips.setVisibility(View.GONE);
            relativeLayoutBody.setVisibility(View.VISIBLE);
        } else {
            relativeLayoutTips.setVisibility(View.VISIBLE);
            relativeLayoutBody.setVisibility(View.GONE);
            searchBaseInfoList = new ArrayList<>();

        }
        searchHistoryAdapter = new SearchHistoryAdapter(searchBaseInfoList, context);
        searchHistoryAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.item_search_delete:
                        searchBaseInfoDao.delete(searchBaseInfoList.get(position));
                        updateData();
                        break;
                    case R.id.item_click:
                        int tp = searchBaseInfoList.get(position).getType();
                        if (tp == 0) {
                            goSearch(0, searchBaseInfoList.get(position).getBody());
                        } else if (tp == 1) {
                            goSearch(1, searchBaseInfoList.get(position).getBody());
                        }
                        break;
                }
            }
        });
        recyclerViewHistory.setAdapter(searchHistoryAdapter);
    }

    private void updateData() {
        searchBaseInfoList = searchBaseInfoDao.queryBuilder().list();
        if (searchBaseInfoList != null && searchBaseInfoList.size() > 0) {
            relativeLayoutTips.setVisibility(View.GONE);
            relativeLayoutBody.setVisibility(View.VISIBLE);
        } else {
            relativeLayoutTips.setVisibility(View.VISIBLE);
            relativeLayoutBody.setVisibility(View.GONE);
        }
        searchHistoryAdapter.setNewData(searchBaseInfoList);
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

    @OnClick(R.id.search_history_delete)
    void deleteHistory() {
        if (searchBaseInfoList != null && searchBaseInfoList.size() > 0) {
            searchBaseInfoDao.deleteAll();
            updateData();
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
                    try {
                        init();
                    } catch (Exception e) {
                        PgyCrashManager.reportCaughtException(this, e);
                    }
                }
                break;
            case 102:
                b = true;
                if (a) {
                    try {
                        init();
                    } catch (Exception e) {
                        PgyCrashManager.reportCaughtException(this, e);
                    }
                }
                break;
        }
    }

    @PermissionsDenied({101, 102})
    void getLocationGrantFaile(int code) {
        switch (code) {
            case 101:
                ToastUtil.makeText(this, "请打开存储权限", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                ToastUtil.makeText(this, "请打开存储权限", Toast.LENGTH_SHORT).show();
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
