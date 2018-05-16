package com.benmu.wx.iapppay;

/**
 *应用接入iAppPay云支付平台sdk集成信息
 */
public class IAppPaySDKConfig{

	/**
	 * 应用名称：
	 * 应用在iAppPay云支付平台注册的名称
	 */
	public final static String APP_NAME = "test1";

	/**
	 * 应用编号：
	 * 应用在iAppPay云支付平台的编号，此编号用于应用与iAppPay云支付平台的sdk集成
	 */
	public final static String APP_ID = "3019332996";

	/**
	 * 商品编号：
	 * 应用的商品在iAppPay云支付平台的编号，此编号用于iAppPay云支付平台的sdk到iAppPay云支付平台查找商品详细信息（商品名称、商品销售方式、商品价格）
	 * 编号对应商品名称为：测试银币
	 */
	public final static  int WARES_ID_1=1;

	/**
	 * 应用私钥：
	 * 用于对商户应用发送到平台的数据进行加密
	 */
	public final static String APPV_KEY = "MIICWwIBAAKBgQCImvU8W78kDS5Y/2Oxq/jgBETqVqRV5OMBkD2VUDGI9SF+SpYBGrakbYFHJLpo0G7LIJ7xd8isyulY7pxtYJIjZfnCnjIS3/5lg35sUVZgrvFWhwb40SO8RNqSRyjg2YTkkF5mMQUNpeqcSv1V3MuIbZeg1cn3FV9snkpPWzb6KwIDAQABAoGAOE7XY0fWJAAi6KBs8aGXdo+L8KRCHP257L0OBWwG3xNb+Pi5mD7wphpVPYQQKoAwCx6pJZNgbok9YmEIrbUuxziqLNXol4gy/NuoSBe49ak355zh0HdCvD4mv74qtk9/UDjkoGairgOzHuxBwYkDk8mBfbbbjH7ebKAB6S4XYUECQQDZVO/O89tbQPmsDcp6TXd35lKnm93SB/5HZ91fcJBAqgT356dhbC0pjyMw/gq4ThIuLQ4yBhQi8eNPFoCL45wbAkEAoOkUFVZPxWJXZ2qIGgFV6Ni0pCNuTed0hO9Ehflu640SzhMAbwVNN2BH//fYz1eRJqHnhwoJxysI3CH/5gHbMQJAVeKt/j/6QN61jBvLF3gDrVtU8K21BGpqP3e5UR0ftJ475mgiimqAknrhclwCioE3yA3AHV6vmHu0061V0XJ6XwJAZ4sUfVZ5Jo7Np6KJyhIp8hfgxe52wHK8K/67nWqQ0cqJ20mm8cn7kjLGohQe0+2JXc65adAv0pYCsYS/Yoa3QQJAO6koiWi1YYmJIbTHA2QH6OaRByXEhfuAI5T1pryn7v4kx21iOn6ywDpx2J5L6XGaqzz2HB21/HpeZiV/AYyznw==";

	/**
	 * 平台公钥：
	 * 用于商户应用对接收平台的数据进行解密

	 */
	public final static String PLATP_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrVLFWvWFU2VKWBafNM9U1iA+i7B0IR2s/zEGbh6a9FNuFppbPDbe+CF9OfI2euIHcRpLAwYieHH4CJFMznCTgpTn5RFQ+Osnw65E1byh3UjEfndTsjEvotOnNIO6F3dGdImZvRuJXlK6R9lYhlEOo0Gb3VqmpmWRFjG5FJDOR5wIDAQAB";

}
