package com.mhky.dianhuotong.shop.activity;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.tool.QRCodeUtil;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.mhky.dianhuotong.wxapi.Constants.APP_ID;
@PermissionsRequestSync(permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, value = {101, 102})
public class CardActivity extends BaseActivity {
    @BindView(R.id.shop_code_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.shop_code_img)
    ImageView imageView;
    private IWXAPI api;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private boolean a;
    private boolean b;
    public static final String CARD_TAG = "card101";
    private static final String TAG = "CardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        ButterKnife.bind(this);
        Permissions4M.get(this).requestSync();
    }

    private void init() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("店铺名称");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            String shopId=bundle.getString("shopid");
            if (!TextUtils.isEmpty(shopId)){
                getCode(shopId);
            }
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @OnClick(R.id.card_wx)
    void shareWx() {
        share(0);
//        share1();
    }

    @OnClick(R.id.card_py)
    void sharePy() {
        share(1);
    }

    private void share(int type) {
        WXImageObject imageObject = new WXImageObject(bitmap1);
        WXMediaMessage msg = new WXMediaMessage();  //这个对象是用来包裹发送信息的对象
        msg.mediaObject = imageObject;
        Bitmap thumbBitmap = Bitmap.createScaledBitmap(bitmap1, 150, 150, true);
        msg.thumbData = bitmap2ByteArray(thumbBitmap);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = msg;
        if (type == 0) {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        } else if (type == 1) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        }
        req.transaction = CARD_TAG;
        boolean b = api.sendReq(req);
        BaseTool.logPrint(TAG, "share: ---" + b);
    }

    private void share1() {
        WXTextObject wxTextObject = new WXTextObject();
        wxTextObject.text = "这是一个测试的文本对象";
        WXMediaMessage wxMediaMessage = new WXMediaMessage();
        wxMediaMessage.mediaObject = wxTextObject;
        wxMediaMessage.description = "看到测试的";
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "1002";
        req.message = wxMediaMessage;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        api.sendReq(req);
    }

    private void getCode(final String shopId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bitmap = QRCodeUtil.createQRCodeBitmap("MHKYDP"+shopId, dip2px(200), Color.parseColor("#04c1ab"), Color.parseColor("#ffffff"));
                bitmap1 = QRCodeUtil.addText(bitmap, "店铺号："+"MHKYDP00"+shopId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageBitmap(bitmap1);
                        //bitmap.recycle();
                    }
                });
            }
        }).start();
    }

    private int dip2px(float dpValue) {
        float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private byte[] bitmap2ByteArray(Bitmap bitmap1) {
        BaseTool.logPrint(TAG, "bitmap2ByteArray: " + bitmap1.getRowBytes() * bitmap1.getHeight());
//        int bytes = bitmap1.getByteCount();
//        ByteBuffer buf = ByteBuffer.allocate(bytes);
//        bitmap1.copyPixelsToBuffer(buf);
//        byte[] byteArray = buf.array();
//        return byteArray;
        int i;
        int j;
        if (bitmap1.getHeight() > bitmap1.getWidth()) {
            i = bitmap1.getWidth();
            j = bitmap1.getWidth();
        } else {
            i = bitmap1.getHeight();
            j = bitmap1.getHeight();
        }
        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
        Canvas localCanvas = new Canvas(localBitmap);

        while (true) {
            localCanvas.drawBitmap(bitmap1, new Rect(0, 0, i, j), new Rect(0, 0, i, j), null);
//            if (needRecycle)
            bitmap1.recycle();
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    localByteArrayOutputStream);
            localBitmap.recycle();
            byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
            try {
                localByteArrayOutputStream.close();
                return arrayOfByte;
            } catch (Exception e) {
                // F.out(e);
            }
            i = bitmap1.getHeight();
            j = bitmap1.getHeight();
        }
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
}
