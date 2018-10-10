package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.QualicationInfo;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;
import com.mhky.dianhuotong.credential.bean.QulationBaseInfo;
import com.mhky.dianhuotong.credential.credentialif.UploadCredentialIF;
import com.mhky.dianhuotong.credential.precenter.UploadCredentialPrecenter;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBottomMenuDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.bean.CredentialUpdateInfo;
import com.mhky.dianhuotong.shop.bean.ShopCredentialBaseInfo;
import com.mhky.dianhuotong.shop.precenter.ShopCredentialPresenter;
import com.mhky.dianhuotong.shop.shopif.ShopCredentialIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 资质上传 Activity
 */

public class CredentialUploadActivity extends TakePhotoActivity implements DianHuoTongBottomMenuDialog.DianHuoTongBottomMenuDialogListener,
        UploadCredentialIF, ShopCredentialIF {

    @BindView(R.id.credential_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.credential_image_group)
    RelativeLayout relativeLayout;
    @BindView(R.id.credential_image)
    ImageView imageViewCredentail;
    @BindView(R.id.upload_credrntial_data1)
    TextView textViewData1;
    @BindView(R.id.upload_credrntial_data2)
    TextView textViewData2;
    @BindView(R.id.upload_credrntial_card_number)
    EditText editTextCardNumber;
    @BindView(R.id.upload_credrntial_body)
    EditText editTextBody;
    @BindView(R.id.upload_credrntial_name)
    EditText editTextNume;
    @BindView(R.id.upload_credrntial_ok)
    TextView textViewOk;
    @BindView(R.id.credential_file_name)
    TextView textViewFile;
    @BindView(R.id.upload_credential_img_start)
    ImageView imageViewStart;
    @BindView(R.id.upload_credential_img_stop)
    ImageView imageViewStop;

    private DianHuoTongBottomMenuDialog dianHuoTongBottomMenuDialog;
    private Uri uri = null;
    private Context mContext;
    private UploadCredentialPrecenter uploadCredentialPrecenter;
    private QualicationInfo.QualificationListBean qulationBaseInfo;
    private String imageUrl;
    private CredentialBaseTypeInfo credentialBaseTypeInfo;                                          //传过来的资质类型实体类
    private TimePickerView pvCustomTime1;
    private TimePickerView pvCustomTime2;
    private ShopCredentialBaseInfo shopCredentialBaseInfo;
    private String state = "";                                                  //0：修改已上传的资质    1：上传新的资质
    private ShopCredentialPresenter shopCredentialPresenter;
    private int withResult;
    private int heightResult;
    private static final String TAG = "CredentialUploadActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_upload);
        ButterKnife.bind(this);
        mContext = this;
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void inIt() {
        initW2H();
        shopCredentialPresenter = new ShopCredentialPresenter(this);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("资质上传");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qulationBaseInfo = new QualicationInfo.QualificationListBean();
        state = getIntent().getExtras().getString("state");
        if (state != null && !state.equals("") && state.equals("0")) {  //state = 0 的时候是去修改
            shopCredentialBaseInfo = (ShopCredentialBaseInfo) getIntent().getExtras().getSerializable("credentialinfo");
            textViewFile.setText("*请上传更改的" + shopCredentialBaseInfo.getName());
            textViewData1.setText(shopCredentialBaseInfo.getStartTime());
            textViewData2.setText(shopCredentialBaseInfo.getEndTime());
            if(!BaseTool.isEmpty(shopCredentialBaseInfo.getStartTime())){
                imageViewStart.setVisibility(View.VISIBLE);
            }
            if(!BaseTool.isEmpty(shopCredentialBaseInfo.getEndTime())){
                imageViewStop.setVisibility(View.VISIBLE);
            }
            if (!BaseTool.isEmpty(shopCredentialBaseInfo.getUrl())) {
                BaseTool.logPrint(TAG, "inIt: ------------" + shopCredentialBaseInfo.getUrl());
                Picasso.get().load(shopCredentialBaseInfo.getUrl()).resize(withResult, heightResult).into(imageViewCredentail);
                imageUrl = shopCredentialBaseInfo.getUrl();
            }
            editTextBody.setText(shopCredentialBaseInfo.getScope());
            editTextCardNumber.setText(shopCredentialBaseInfo.getNumber());
            editTextNume.setText(shopCredentialBaseInfo.getCorporation());
        } else if((state != null && !state.equals("") && state.equals("-1"))) {//此处也是修改，但是传过来的activity不同
            qulationBaseInfo = (QualicationInfo.QualificationListBean) getIntent().getExtras().getSerializable("credentialinfo");
            textViewFile.setText("*请上传更改的" + qulationBaseInfo.getName());
            textViewData1.setText(qulationBaseInfo.getStartTime());
            textViewData2.setText(qulationBaseInfo.getEndTime());
            if(!BaseTool.isEmpty(qulationBaseInfo.getStartTime())){
                imageViewStart.setVisibility(View.VISIBLE);
            }
            if(!BaseTool.isEmpty(qulationBaseInfo.getEndTime())){
                imageViewStop.setVisibility(View.VISIBLE);
            }
            if (!BaseTool.isEmpty(qulationBaseInfo.getUrl())) {
                BaseTool.logPrint(TAG, "inIt: ------------" + qulationBaseInfo.getUrl());
                Picasso.get().load(qulationBaseInfo.getUrl()).resize(withResult, heightResult).into(imageViewCredentail);
                imageUrl = qulationBaseInfo.getUrl();
            }
            editTextBody.setText(qulationBaseInfo.getScope());
            editTextCardNumber.setText(qulationBaseInfo.getNumber());
            editTextNume.setText(qulationBaseInfo.getCorporation());
        }else{//state = 1 的时候是去上传新的资质
            credentialBaseTypeInfo = (CredentialBaseTypeInfo) getIntent().getExtras().getSerializable("credentialtype");
            if (credentialBaseTypeInfo != null) {
                textViewFile.setText("*请上传" + credentialBaseTypeInfo.getName());
            }
        }
        dianHuoTongBottomMenuDialog = new DianHuoTongBottomMenuDialog(this, this);
        uploadCredentialPrecenter = new UploadCredentialPrecenter(this);
        initCustomTimePicker1(textViewData1);
        initCustomTimePicker2(textViewData2);
    }

    @OnClick({R.id.upload_credential_img_start})
    void resetStart() {
        textViewData1.setText("");
        imageViewStart.setVisibility(View.GONE);
    }

    @OnClick({R.id.upload_credential_img_stop})
    void resetStop() {
        textViewData2.setText("");
        imageViewStop.setVisibility(View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initW2H() {
        float width = getResources().getDimension(R.dimen.x210);
        withResult = BaseTool.dip2px(this, width);
        float height = getResources().getDimension(R.dimen.x150);
        heightResult = BaseTool.dip2px(this, height);
    }

    @OnClick(R.id.credential_image_group)
    void goSelectPhoto() {
        //getTakePhoto().onPickFromGallery();
        Permissions4M.get(this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                // .requestUnderM(false)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 权限码
                .requestCodes(1001)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();
    }

    @PermissionsGranted(1001)
    void granSuccess() {
        dianHuoTongBottomMenuDialog.show();
    }

    /**
     * 拍照或者获取相册图片成功
     * @param result
     */
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        try {
            //ToastUtil.makeText(this, "选取成功", Toast.LENGTH_SHORT).show();
            if (BaseApplication.getInstansApp().getPersonInfo() != null) {
                BaseTool.logPrint(TAG, "takeSuccess: " + result.getImages().size());
                BaseTool.logPrint(TAG, "takeSuccess: " + result.getImages().get(0).getOriginalPath());
                BaseTool.logPrint(TAG, "takeSuccess: " + result.getImages().get(0).getCompressPath());
                HttpParams httpParams = new HttpParams();
                httpParams.put("userName", BaseApplication.getInstansApp().getPersonInfo().getUsername());
                httpParams.put("userId", BaseApplication.getInstansApp().getPersonInfo().getId());
                httpParams.put("type", "USER");
                httpParams.put("file", new File(result.getImages().get(0).getCompressPath()));
                uploadCredentialPrecenter.getImageUplaodUrl(httpParams);
            } else {
                ToastUtil.makeText(this, "登陆异常", Toast.LENGTH_SHORT).show();
                BaseTool.goActivityNoData(this, LoginActivity.class);
            }

        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        } finally {
            dianHuoTongBottomMenuDialog.dismiss();
        }


        // Picasso.get().load("file://" + result.getImages().get(0).getOriginalPath()).into(imageView);
//        if (uri != null) {
//             Picasso.get().load("file://" + result.getImages().get(0).getOriginalPath()).into(imageView);
//        } else {
//            Picasso.get().load(uri).into(imageView);
//        }

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        ToastUtil.makeText(this, "无法选取图片" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
//        ToastUtil.makeText(this, "取消", Toast.LENGTH_SHORT).show();
    }

    /**
     * 拍照
     */
    @Override
    public void getCamera() {
        uri = BaseTool.createImagePathUri(mContext);
        TakePhoto takePhoto = getTakePhoto();
        CompressConfig compressConfig = CompressConfig.ofDefaultConfig();       //压缩参数
        compressConfig.setMaxSize(1000 * 1024);
        takePhoto.onEnableCompress(compressConfig, true);   //设置为需要压缩
        takePhoto.onPickFromCapture(uri);
    }

    /**
     * 相册选取
     */
    @Override
    public void getPhotos() {
        TakePhoto takePhoto = getTakePhoto();
        CompressConfig compressConfig = CompressConfig.ofDefaultConfig();
        compressConfig.setMaxSize(1000 * 1024);
        takePhoto.onEnableCompress(compressConfig, true);
        takePhoto.onPickFromGallery();
    }

    /**
     * 上传图片成功
     * @param code
     * @param result
     */
    @Override
    public void updataCredentialImageSucess(int code, String result) {
        try {
            if (code == 201) {
                if (result != null && !"".equals(result)) {
                    ToastUtil.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
                    imageUrl = result;
                    Picasso.get().load(imageUrl).resize(withResult, heightResult).into(imageViewCredentail);
                    BaseTool.logPrint(TAG, "updataCredentialImageSucess: W---" + withResult);
                    BaseTool.logPrint(TAG, "updataCredentialImageSucess: H---" + heightResult);
                    BaseTool.logPrint(TAG, "updataCredentialImageSucess: ------" + result);
                }
            } else {
                ToastUtil.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void updataCredentialImageFailed(int code, String result) {
        ToastUtil.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.upload_credrntial_ok)
    void finishCreateCredential() {
        try {
            if (imageUrl == null) {
                ToastUtil.makeText(this, "请上传证件", Toast.LENGTH_SHORT).show();
                return;
            }
            qulationBaseInfo.setUrl(imageUrl);
            if (TextUtils.isEmpty(textViewData1.getText())) {
//                if (!TextUtils.isEmpty(textViewData2.getText())) {
                    ToastUtil.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
//                    return;
//                }
            } else {
                qulationBaseInfo.setStartTime(textViewData1.getText().toString());
                if (!TextUtils.isEmpty(textViewData2.getText())) {
                    qulationBaseInfo.setEndTime(textViewData2.getText().toString());
                } else {
                    qulationBaseInfo.setEndTime("");
                }
            }
            if (!TextUtils.isEmpty(editTextCardNumber.getText())) {
                qulationBaseInfo.setNumber(editTextCardNumber.getText().toString());
            }else {
                ToastUtil.makeText(this, "请输入证件编号", Toast.LENGTH_SHORT).show();
            }
            if (!TextUtils.isEmpty(editTextBody.getText())) {
                qulationBaseInfo.setScope(editTextBody.getText().toString());
            }else {
                ToastUtil.makeText(this, "请输入经营范围", Toast.LENGTH_SHORT).show();
            }
            if (!TextUtils.isEmpty(editTextNume.getText())) {
                qulationBaseInfo.setCorporation(editTextNume.getText().toString());
            }else {
                ToastUtil.makeText(this, "请输入法人姓名", Toast.LENGTH_SHORT).show();
            }
            if (credentialBaseTypeInfo != null) {
                qulationBaseInfo.setId(credentialBaseTypeInfo.getId());
                qulationBaseInfo.setName(credentialBaseTypeInfo.getName());
            }
            if ("0".equals(state)) {
                CredentialUpdateInfo credentialUpdateInfo = new CredentialUpdateInfo();
                credentialUpdateInfo.setUrl(imageUrl);
                credentialUpdateInfo.setStartTime(textViewData1.getText().toString());
                credentialUpdateInfo.setEndTime(textViewData2.getText().toString());
                credentialUpdateInfo.setCorporation(editTextNume.getText().toString());
                credentialUpdateInfo.setScope(editTextBody.getText().toString());
                credentialUpdateInfo.setNumber(editTextCardNumber.getText().toString());
                credentialUpdateInfo.setName(shopCredentialBaseInfo.getName());
                credentialUpdateInfo.setCompositeid(BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString());
                shopCredentialPresenter.updateCredential(shopCredentialBaseInfo.getId(), JSON.toJSONString(credentialUpdateInfo));
            } else if ("1".equals(state)) {
                CredentialUpdateInfo credentialUpdateInfo = new CredentialUpdateInfo();
                credentialUpdateInfo.setUrl(imageUrl);
                credentialUpdateInfo.setName(qulationBaseInfo.getName());
                credentialUpdateInfo.setStartTime(textViewData1.getText().toString());
                credentialUpdateInfo.setEndTime(textViewData2.getText().toString());
                credentialUpdateInfo.setCorporation(editTextNume.getText().toString());
                credentialUpdateInfo.setScope(editTextBody.getText().toString());
                credentialUpdateInfo.setNumber(editTextCardNumber.getText().toString());
                credentialUpdateInfo.setCompositeid(BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString());
                shopCredentialPresenter.uploadNewCredential(JSON.toJSONString(credentialUpdateInfo));
            } else {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("qulation", qulationBaseInfo);
                bundle.putString("state", state);
                intent.putExtras(bundle);
                setResult(1002, intent);
                finish();
                BaseTool.logPrint(TAG,JSON.toJSONString(qulationBaseInfo));
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    @OnClick(R.id.time_1)
    void selecteDate1() {
        pvCustomTime1.show();
    }

    @OnClick(R.id.time_2)
    void selecteDate2() {
        pvCustomTime2.show();
    }


    private void initCustomTimePicker1(final TextView textView) {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(1990, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2070, 11, 30);
        //时间选择器 ，自定义布局
        pvCustomTime1 = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                if (!TextUtils.isEmpty(textViewData2.getText())) {
                    if (BaseTool.isDateOneBigger(getTime(date), textViewData2.getText().toString())) {
                        ToastUtil.makeText(mContext, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                    } else {
                        textView.setText(getTime(date));
                        imageViewStart.setVisibility(View.VISIBLE);
                    }
                } else {
                    textView.setText(getTime(date));
                    imageViewStart.setVisibility(View.VISIBLE);
                }
            }
        }).setDividerColor(R.color.color04c1ab)
                .setLineSpacingMultiplier(1.6f)
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.custom_view_pickerview, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = v.findViewById(R.id.dialog_date_right);
                        final TextView ivCancel = v.findViewById(R.id.dialog_date_left);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime1.returnData();
                                pvCustomTime1.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime1.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-40, 0, 40, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)
                .build();

    }

    private void initCustomTimePicker2(final TextView textView) {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        final Calendar startDate = Calendar.getInstance();
        startDate.set(1990, 0, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2070, 11, 30);
        //时间选择器 ，自定义布局
        pvCustomTime2 = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                //选中事件回调
                if (!TextUtils.isEmpty(textViewData1.getText())) {
                    if (BaseTool.isDateOneBigger(textViewData1.getText().toString(), getTime(date))) {
                        ToastUtil.makeText(mContext, "开始时间不能大于结束时间", Toast.LENGTH_SHORT).show();
                    } else {
                        textView.setText(getTime(date));
                        imageViewStop.setVisibility(View.VISIBLE);
                    }
                } else {
                    textView.setText(getTime(date));
                    imageViewStop.setVisibility(View.VISIBLE);
                }


            }
        }).setDividerColor(R.color.color04c1ab)
                .setLineSpacingMultiplier(1.6f)
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(R.layout.custom_view_pickerview, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = v.findViewById(R.id.dialog_date_right);
                        final TextView ivCancel = v.findViewById(R.id.dialog_date_left);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime2.returnData();
                                pvCustomTime2.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime2.dismiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(-40, 0, 40, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)
                .build();

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        BaseTool.logPrint("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void getShopCredentialSucess(int code, String result) {

    }

    @Override
    public void getShopCredentialFaild(int code, String result) {

    }

    /**
     * 修改已有资质成功
     * @param code
     * @param result
     */
    @Override
    public void updateShopCredentialSucess(int code, String result) {
        try {
            if (code == 200) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("qulation", qulationBaseInfo);
                bundle.putString("state", state);
                bundle.putString("result", "ok");
                intent.putExtras(bundle);
                setResult(1002, intent);
                finish();
            } else {
                ToastUtil.makeText(mContext, "上传失败" + code, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    @Override
    public void updateShopCredentialFaild(int code, String result) {
        ToastUtil.makeText(mContext, "更新异常" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadShopCredentialSucess(int code, String result) {
        try {
            if (code == 200) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("qulation", qulationBaseInfo);
                bundle.putString("state", state);
                bundle.putString("result", "ok");
                intent.putExtras(bundle);
                setResult(1002, intent);
                finish();
            } else {
                ToastUtil.makeText(mContext, "上传失败" + code, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    @Override
    public void uploadShopCredentialFaild(int code, String result) {
        ToastUtil.makeText(mContext, "上传异常" + code, Toast.LENGTH_SHORT).show();
    }
}
