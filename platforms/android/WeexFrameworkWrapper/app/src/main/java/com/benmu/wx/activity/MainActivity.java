package com.benmu.wx.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.benmu.framework.activity.AbstractWeexActivity;
import com.benmu.wx.R;
import com.benmu.wx.iapppay.IAppPaySDKConfig;
import com.iapppay.sdk.main.IAppPay;

public class MainActivity extends AbstractWeexActivity {

    private String acid; // 需要渠道分包功能，请传入对应渠道标识ACID, 可以为空

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        renderPage();

        if (getIntent() != null) {
            acid = getIntent().getStringExtra("acid");
        }
        /**
         * SDK初始化 ，请放在游戏启动界面
         */
        if (TextUtils.isEmpty(acid)) {
            IAppPay.init(this, IAppPay.PORTRAIT, IAppPaySDKConfig.APP_ID);
        } else {
            IAppPay.init(this, IAppPay.PORTRAIT, IAppPaySDKConfig.APP_ID, acid); //需要渠道分包功能，请传入对应渠道标识ACID, 可以为空
        }
    }

    private void initView() {
        mContainer = (ViewGroup) findViewById(R.id.layout_container);
    }
}
