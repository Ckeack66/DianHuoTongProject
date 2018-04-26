package com.mhky.dianhuotong.base.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;


/**
 * Created by Administrator on 2018/3/26.
 */

public class BaseActivity extends FragmentActivity {
    /**
     * 该方法是在Activity被创建时回调，它是生命周期第一个调用的方法，
     * 我们在创建Activity时一般都需要重写该方法，然后在该方法中做一些初始化的操作，如
     * 通过setContentView设置界面布局的资源，初始化所需要的组件信息等。
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Sofia.with(this).statusBarBackground(getResources().getColor(R.color.color04c1ab));
//        Sofia.with(this).statusBarBackgroundAlpha(100);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * onRestart
     * 表示Activity正在重新启动，当Activity由不可见变为可见状态时，该方法被回调。
     * 这种情况一般是用户打开了一个新的Activity时，当前的Activity就会被暂停（onPause和onStop被执行了），
     * 接着又回到当前Activity页面时，onRestart方法就会被回调。
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    /**
     * onStop()
     * 当一个Activity被另一个Activity完全覆盖时，被覆盖的Activity就会进入Stopped状态，
     * 此时它不再可见，但是跟Paused状态一样保持着其所有状态信息及其成员变量。
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * onPause()
     * 当Activity失去焦点时，或被一个新的非全屏的Activity，或被一个透明的Activity放置在栈顶时，Activity就转化为Paused状态。
     * 但我们需要明白，此时Activity只是失去了与用户交互的能力，其所有的状态信息及其成员变量都还存在，
     * 只有在系统内存紧张的情况下，才有可能被系统回收掉。
     */
    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }

    /**
     * onResume
     * 当此方法回调时，则说明Activity已在前台可见，可与用户交互了（处于前面所说的Active/Running形态），
     * onResume方法与onStart的相同点是两者都表示Activity可见，只不过onStart回调时Activity还是后台无法与用户交互，
     * 而onResume则已显示在前台，可与用户交互。当然从流程图，我们也可以看出当Activity停止后（onPause方法和onStop方法被调用），
     * 重新回到前台时也会调用onResume方法，因此我们也可以在onResume方法中初始化一些资源，比如重新初始化在onPause或者onStop方法中释放的资源。
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }
}
