package com.xter.support.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by XTER on 2017/10/12.
 * 网络相关工具
 */
public class NetUtil {

	public static String getLocalMac(Context context) {
		if (Build.VERSION.SDK_INT >= 19) {
			return getLocalMacAddressFromIp(context);
		} else {
			return getLocalMacFromWiFiInfo(context);
		}
	}

	public static String getLocalMacFromWiFiInfo(Context context) {
		WifiManager wifi = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}

	public static String getLocalMacAddressFromIp(Context context) {
		String mac_s = "";
		try {
			byte[] mac;
			NetworkInterface ne = NetworkInterface.getByInetAddress(InetAddress.getByName(getLocalIpAddress()));
			mac = ne.getHardwareAddress();
			mac_s = TextUtil.byte2hex(mac);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mac_s;
	}

	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements(); ) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 网络可用？
	 *
	 * @return bool
	 */
	public static boolean isInternetConnection(Context context) {
		boolean isConnected;
		ConnectivityManager connectivityManager =
				(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

		return isConnected;
	}

	/**
	 * wifi获取ip
	 *
	 * @param context 上下文
	 * @return ip
	 */
	public static String getIpInWiFi(Context context) {
		try {
			//获取wifi服务
			WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
			//判断wifi是否开启
			if (!(wifiManager != null && wifiManager.isWifiEnabled())) {
				wifiManager.setWifiEnabled(true);
			}
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			String ip = intToIp(ipAddress);
			return ip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 3G/4g网络IP
	 */
	public static String getIpInMobile() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements(); ) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& inetAddress instanceof Inet4Address) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ip转为字符串
	 *
	 * @param i ip
	 * @return 字符
	 */
	private static String intToIp(int i) {
		return (i & 0xFF) + "." +
				((i >> 8) & 0xFF) + "." +
				((i >> 16) & 0xFF) + "." +
				(i >> 24 & 0xFF);
	}

	/**
	 * 获取本机的ip地址（3中方法都包括）
	 *
	 * @param context 上下文
	 * @return ip 本机ip
	 */
	public static String getIpAdress(Context context) {
		String ip = null;
		try {
			ip = getIpInWiFi(context);
			if (ip == null) {
				ip = getIpInMobile();
				if (ip == null) {
					ip = getLocalIpAddress();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ip;
	}
}
