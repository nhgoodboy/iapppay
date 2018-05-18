package com.benmu.wx.iapppay;

import android.content.Intent;
import android.widget.Toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

public class IapppayModule extends WXModule {

    //run ui thread
    @JSMethod(uiThread = true)
    public void printLog(String msg) {
        Toast.makeText(mWXSDKInstance.getContext(),msg,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();
        intent.putExtra("acid", "123456");
        intent.setClass(mWXSDKInstance.getContext(), IapppayActivity.class);
        mWXSDKInstance.getContext().startActivity(intent);
    }

}
