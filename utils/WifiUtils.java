package com.movieboxpro.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.dl7.player.utils.NetWorkUtils;
import com.movieboxpro.android.app.App;
/* loaded from: classes3.dex */
public class WifiUtils {
    public static String getWifiIp() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) App.getContext().getApplicationContext().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return null;
        }
        return intToIp(connectionInfo.getIpAddress());
    }

    private static String intToIp(int i) {
        return (i & 255) + com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX + ((i >> 8) & 255) + com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX + ((i >> 16) & 255) + com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX + ((i >> 24) & 255);
    }

    public static int getWifiState(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        return 1;
    }

    public static NetworkInfo.State getWifiConnectState(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getNetworkInfo(1);
        if (networkInfo != null) {
            return networkInfo.getState();
        }
        return NetworkInfo.State.DISCONNECTED;
    }
}
