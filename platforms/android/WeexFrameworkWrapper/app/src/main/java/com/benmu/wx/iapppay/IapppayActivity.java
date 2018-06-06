package com.benmu.wx.iapppay;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.benmu.wx.R;
import com.iapppay.interfaces.callback.IPayResultCallback;
import com.iapppay.sdk.main.IAppPay;
import com.iapppay.sdk.main.IAppPayOrderUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

public class IapppayActivity extends Activity implements View.OnClickListener {

    private String acid; // 需要渠道分包功能，请传入对应渠道标识ACID, 可以为空
    private String serverOrderaddr = "http://192.168.102.13:8081/iapppay/server_order";
    private String clientOrderaddr = "http://192.168.102.13:8081/iapppay/client_order";
    private final int GETVALUE = 0, POSTVALUE = 1;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iapppay);
        mHandler = new MyHandler(this);

        findViewById(R.id.btn_client_order_one).setOnClickListener(this);
        findViewById(R.id.btn_client_order_two).setOnClickListener(this);
        findViewById(R.id.btn_server_order).setOnClickListener(this);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

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

    /**
     * 支付结果回调
     */
    IPayResultCallback iPayResultCallback = new IPayResultCallback() {

        @Override
        public void onPayResult(int resultCode, String signValue, String resultInfo) {
            // TODO Auto-generated method stub
            switch (resultCode) {
                case IAppPay.PAY_SUCCESS:
                    //调用 IAppPayOrderUtils 的验签方法进行支付结果验证
                    //请尽量采用服务端验签方式  ，以下验签方法不建议
                    boolean payState = IAppPayOrderUtils.checkPayResult(signValue, IAppPaySDKConfig.PLATP_KEY);
                    if (payState) {
                        Toast.makeText(IapppayActivity.this, "支付成功", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(IapppayActivity.this, "支付成功但验签失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    Toast.makeText(IapppayActivity.this, resultInfo, Toast.LENGTH_LONG).show();
                    break;
            }
            Log.d("IapppayActivity", "requestCode:" + resultCode + ",signvalue:" + signValue + ",resultInfo:" + resultInfo);
        }
    };


    public boolean serverOrder(int waresId, String type) throws IOException {
        String params = "waresId=" + waresId;
        URL url = null;
        if("serverOrder".equals(type)){
            url = new URL(serverOrderaddr + "?" + params);
        }else if("clientOrder".equals(type)){
            url = new URL(clientOrderaddr + "?" + params);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200) {
            System.out.println(conn.getResponseMessage());

            InputStream in = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder s = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                s.append(line);
            }
            reader.close();
            Message message = new Message();
            message.what = GETVALUE;
            message.obj = s.toString();
            mHandler.sendMessage(message);
        }
        return true;
    }


    class MyHandler extends Handler {
        WeakReference<IapppayActivity> iapppayActivityWeakReference;

        public MyHandler(IapppayActivity activity) {
            iapppayActivityWeakReference = new WeakReference<IapppayActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
//            IapppayActivity iapppayActivity = iapppayActivityWeakReference.get();
            switch (msg.what) {
                case GETVALUE:
                    try {
                        msg.obj = URLDecoder.decode(msg.obj + "", "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String params = msg.obj.toString();
                    System.out.println(params);
                    IAppPay.startPay(IapppayActivity.this, params, iPayResultCallback);
                    break;
                case POSTVALUE:
                    System.out.println(msg.obj + "2");
                    break;
                default:
                    break;
            }

        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_client_order_two) {
            String cporderid = System.currentTimeMillis() + "";
            String param = getTransdata("userid001", "cpprivateinfo123456", 1, 0.01, cporderid);
            System.out.println(param);
            IAppPay.startPay(IapppayActivity.this, param, iPayResultCallback);
        } else if (view.getId() == R.id.btn_server_order) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Boolean respCode = serverOrder(1, "serverOrder");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else if(view.getId() == R.id.btn_client_order_one) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Boolean respCode = serverOrder(1, "clientOrder");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
