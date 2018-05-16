package com.benmu.wx.iapppay;

import com.benmu.wx.activity.MainActivity;
import com.iapppay.sdk.main.IAppPay;
import com.iapppay.sdk.main.IAppPayOrderUtils;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

public class IAppPayUtil extends WXModule {

    @JSMethod
    public void startPay(final JSCallback checkCallBack){
        String cporderid = System.currentTimeMillis() + "";
        String param = getTransdata("userid001", "cpprivateinfo123456", 1, 0.01, cporderid);
        IAppPay.startPay(MainActivity.this, param, checkCallBack);
    }

    /**
     * 获取收银台参数
     * 请尽量采用服务端下单模式  ，  公私钥不要放在客户端
     * 以下方法不推荐使用
     */
    private String getTransdata(String appuserid, String cpprivateinfo, int waresid, double price, String cporderid) {
        //调用 IAppPayOrderUtils getTransdata() 获取支付参数
        IAppPayOrderUtils orderUtils = new IAppPayOrderUtils();
        orderUtils.setAppid(IAppPaySDKConfig.APP_ID);
        orderUtils.setWaresid(waresid);//传入您商户后台创建的商品编号
        orderUtils.setCporderid(cporderid);
        orderUtils.setAppuserid(appuserid);
        orderUtils.setPrice(price);//单位 元
        orderUtils.setWaresname("自定义名称");//开放价格名称(用户可自定义，如果不传以后台配置为准)
        orderUtils.setCpprivateinfo(cpprivateinfo);
        return orderUtils.getTransdata(IAppPaySDKConfig.APPV_KEY);
    }
}
