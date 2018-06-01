package com.mhky.dianhuotong.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.promote.PromoteIF;
import com.mhky.dianhuotong.promote.PromoteInfo;
import com.mhky.dianhuotong.promote.PromotePresenter;
import com.mhky.dianhuotong.shop.tool.QRCodeUtil;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mhky.dianhuotong.wxapi.Constants.APP_ID;

public class MyIntroduceActivity extends BaseActivity implements PromoteIF {
    @BindView(R.id.shop_code_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.shop_code_img)
    ImageView imageView;
    private IWXAPI api;
    private Bitmap bitmap;
    private Bitmap bitmap1;
    private PromotePresenter promotePresenter;
    public static final String CARD_TAG = "card100";
    private static final String TAG = "CardActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_introduce);
        ButterKnife.bind(this);
        init();
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
        promotePresenter=new PromotePresenter().setPromoteIF(this);
        promotePresenter.getPromoteInfo();
    }

    @OnClick(R.id.card_wx)
    void shareWx() {
        share(0);
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

    private void getCode(final String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bitmap = QRCodeUtil.createQRCodeBitmap("http://mp.mhkyjk.com/extension?code="+code, dip2px(200), Color.parseColor("#04c1ab"), Color.parseColor("#ffffff"));
                bitmap1 = QRCodeUtil.addText(bitmap, "推广码：" + code);
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

    @Override
    public void getPromoteInfoSuccess(int code, String result) {
        if (code == 200) {
            PromoteInfo promoteInfo= JSON.parseObject(result,PromoteInfo.class);
            getCode(promoteInfo.getCode());
        }
    }

    @Override
    public void getPromoteInfoFailed(int code, String result) {

    }
}