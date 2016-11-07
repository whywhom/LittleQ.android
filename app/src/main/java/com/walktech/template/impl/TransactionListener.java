package com.walktech.template.impl;

import java.util.HashMap;

public interface TransactionListener {
	//	正在绑定、连接蓝牙回调
	public void onBluetoothBounding();

	//	蓝牙连接成功回调
	public void onBluetoothConnected();

	//	蓝牙连接失败回调
	public void onBluetoothConnectFail();

	//	等待刷卡、插卡、放卡回调
	public void onWaitingForCardSwipe();

	// 	检卡成功回调
//	参数：
//		cardType：当前检测到的卡类型，CARDTYPE_MAG、CARDTYPE_IC或CARDTYPE_NFC
//		status：输入当前检测卡的状态，0表示成功，其他值表示检卡错误
//		PAN：若成功检测到磁卡，输入磁卡账户信息
//		track2：若成功检测到磁卡，输入二磁道数据
//		additonal：若检卡成功，额外的信息将在additonal中输入，
//			其中，一磁道数据的key为“TRACK1”，value为String类型；
//			三磁道数据的key为“TRACK3”，value为String类型；
//			卡片有效期的key为“EXPIRE”，value为String类型；
//			磁片是否带IC的key为“isICCARD”，value为boolean类型，带IC为true、不带ic为false；
//			卡片国家代码的key为“COUNTRYCODE”，value为String类型；
//			持卡人姓名的key为“CARDHOLDER”，value为String类型；
//			IC卡的ATR数据的key为“ATR”，value为String类型；
//			NFC卡UUID的key为“UUID”，value为String类型。
	void onCheckCardCompleted(int cardType,
							  int status,
							  String PAN,
							  String track2,
							  HashMap additonal
	);

	//	刷卡、插卡、放卡，超时回调
	public void onTimeout();

	//	固件更新进度回调
//	参数:
//		percent：输入当前进度的百分数值，0~100，100表示更新结束；小于0表示更新错误。
	public void onUpdateProcess(int percent);

	//	获取用户PIN输入回调
//	参数：
//		pin：输出PIN数据，ASCII码格式；
//	返回：
//		大于0：PIN的长度；
//		等于0：用户没有输入PIN，直接按确定键返回；
//		小于0：用户按取消键返回；
//	说明：只适用于刷卡器设备。
	public int onInputPIN(byte[] pin);

	//	获取AID参数回调
//	参数：
//		aid：输出所有AID和ASI，单个AID构成：1字节AID长度 + n字节AID数据 + 1字节ASI数据；
//	返回：
//		大于0：所有AID数据的长度；
//		其他：获取AID参数失败；
//	说明：只适用于刷卡器设备。
	public int onGetAllAID(byte[] aid);

	//	获取RID参数回调
//	参数：
//		rid：输入RID数据；
//		ridLen：输入RID数据的长度；
//		pki：输入RID的PKI；
//		Mod：输出该RID的module数据，格式为：2字节module长度（高字节在前，低字节在后）+ n字节module数据；
//		Exp：输出该RID的exponent数据，格式为：1字节exponent长度 + n字节exponent数据；
//	返回：
//		大于0：获取RID参数成功；
//		其他：获取RID参数失败；
//	说明：只适用于刷卡器设备
	public int onGetRID (byte[] rid, int ridLen, int pki, byte[] Mod, byte[] Exp);

	//	获取终端参数回调
//	参数：
//		reserve：保留；
//		tag：输出所有终端参数，包括终端类型、终端性能、附加终端性能、终端国家代码、接口设备序列号；
//	返回：
//		大于0：所有Terminal Tag数据的长度；
//		其他：获取Terminal Tag参数失败；
//	说明：只适用于刷卡器设备。
	public int onGetTerminalTag(String reserve, byte[] tag);

	//	获取脱机交易金额回调
//	返回：
//		脱机交易总金额，单位为分，如“1234”，表示12.34元。
	public int onGetOfflineTotalAmount();

	//	获取卡片应用选择回调
//	参数：
//		total：输入卡片上的总应用数；
//		appName：输入卡片上各应用的名称，从0到total-1；
//	返回：
//		用户选择的应用号，0~total-1。
	public int onAppSelect(int total, String[] appName);

	//	与后台通讯回调
//	参数：
//		reserve：保留；
//		req：输入往后台发送的数据；
//		len：往后台发送的数据长度：
//		resp：输出后台返回的数据：
//	返回：
//		大于0：后台返回的数据长度；
//		其他：与后台通讯错误。
//	说明：应用程序里直接把往后台发送的数据按各通道格式进行打包发送，并将后台返回的数据进行输出。
	public int HostSendRecv(byte[] req, int len, byte[] resp, String reserve);

	//	交易结果回调
//	参数：
//		res：输出交易结果，0表示交易正常完成，其他值表示交易出错；
//		status：在交易正常完成的情况下，输出交易完成状态，0表示TERMINATE；
//				1表示OFFLINEAPPROVED；2表示 OFFLINEDECLINED；3表示ONLINEAPPROVED；4表示ONLINEDECLINED；
//				5表示UNABLEONLINE_OFFLINEAPPROVED；6表示UNABLEONINE_OFFLINEDECLINED；
//				0x1001表示需要fallback交易。在交易出错的情况下为0；
//		TVR：在交易正常完成的情况下，输出TVR值；在交易出错的情况下为null；
//		TSI：在交易正常完成的情况下，输出TSI值；在交易出错的情况下为null；
//		CVM：在交易正常完成的情况下，输出CVM值；在交易出错的情况下为null；
//		Script：在交易正常完成的情况下，输出Script值；在交易出错的情况下为null；
	public void onTransactResult(int res, int status, String TVR, String TSI, String CVM, String[] Script);

	//	调试信息输出
//	参数：
//		info：SDK输出的调试信息；
//	说明：该接口主要用于开发过程中的调试，应用程序不用实现。
	public void onDebug(String info);

	//	检查CRL
//	参数：
//		RID：CRL中的RID；
//		Index：CRL中的Index；
//		SN：CRL中的SN；
//	返回：
//		等于0表示不存在；非0不是存在；
//	说明：该接口主要用于检查CRL，对于没有CRL的应用，可以不用实现。
	public int onCheckCRL(String RID, String Index, String SN);
}
