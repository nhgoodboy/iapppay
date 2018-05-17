package com.benmu.wx;

import android.app.Application;

import com.benmu.framework.BMWXApplication;
import com.benmu.wx.iapppay.IapppayModule;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * Created by Carry on 2017/8/23.
 */

public class App extends BMWXApplication {
    public Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        try {
            WXSDKEngine.registerModule("IapppayModule", IapppayModule.class);
        } catch (WXException e) {
            e.printStackTrace();
        }


    }

}
