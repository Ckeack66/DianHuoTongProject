package com.mhky.dianhuotong.wxapi;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler{

	private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_about_our);
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		BaseTool.logPrint(TAG, "BaseReq, errCode = " + req.openId+"------"+req.transaction);
	}


	@Override
	public void onResp(BaseResp resp) {
		BaseTool.logPrint(TAG, "onPayFinish, errCode = " + resp.errCode);
		BaseTool.logPrint(TAG, "onPayFinish, errCode = " + resp.errCode+"------"+resp.errStr+"-----"+resp.transaction);
		BaseTool.logPrint(TAG,"type--"+resp.getType());
		Intent intent=new Intent();
		intent.putExtra("result",resp.errCode);
		intent.setAction(BaseApplication.wxAction);
		sendBroadcast(intent);
		finish();
//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			//builder.setTitle(R.string.app_tip);
//			//builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//			builder.show();
//		}
	}


}