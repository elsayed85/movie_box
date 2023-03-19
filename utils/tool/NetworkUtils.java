package com.movieboxpro.android.utils.tool;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.dl7.player.utils.NetWorkUtils;
import com.movieboxpro.android.utils.tool.Utils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
/* loaded from: classes3.dex */
public class NetworkUtils {

    /* loaded from: classes3.dex */
    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    /* loaded from: classes3.dex */
    public interface OnNetworkStatusChangedListener {
        void onConnected(NetworkType networkType);

        void onDisconnected();
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Utils.Task<Boolean> isAvailableAsync(Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.1
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailable());
            }
        });
    }

    public static boolean isAvailable() {
        return isAvailableByDns() || isAvailableByPing(null);
    }

    public static void isAvailableByPingAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByPingAsync("", consumer);
    }

    public static Utils.Task<Boolean> isAvailableByPingAsync(final String str, Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.2
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailableByPing(str));
            }
        });
    }

    public static boolean isAvailableByPing() {
        return isAvailableByPing("");
    }

    public static boolean isAvailableByPing(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "223.5.5.5";
        }
        return ShellUtils.execCmd(String.format("ping -c 1 %s", str), false).result == 0;
    }

    public static void isAvailableByDnsAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByDnsAsync("", consumer);
    }

    public static Utils.Task isAvailableByDnsAsync(final String str, Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.3
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isAvailableByDns(str));
            }
        });
    }

    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    public static boolean isAvailableByDns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            return InetAddress.getByName(str) != null;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getMobileDataEnabled() {
        TelephonyManager telephonyManager;
        try {
            telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        } catch (Exception e) {
            Log.e("NetworkUtils", "getMobileDataEnabled: ", e);
        }
        if (telephonyManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            return telephonyManager.isDataEnabled();
        }
        Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
        if (declaredMethod != null) {
            return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
        }
        return false;
    }

    public static boolean isMobileData() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        if (wifiManager == null || z == wifiManager.isWifiEnabled()) {
            return;
        }
        wifiManager.setWifiEnabled(z);
    }

    public static boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        return (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) ? false : true;
    }

    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailable();
    }

    public static Utils.Task<Boolean> isWifiAvailableAsync(Utils.Consumer<Boolean> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.4
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public Boolean doInBackground() {
                return Boolean.valueOf(NetworkUtils.isWifiAvailable());
            }
        });
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        return telephonyManager == null ? "" : telephonyManager.getNetworkOperatorName();
    }

    public static NetworkType getNetworkType() {
        if (isEthernet()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            if (activeNetworkInfo.getType() == 1) {
                return NetworkType.NETWORK_WIFI;
            }
            if (activeNetworkInfo.getType() == 0) {
                switch (activeNetworkInfo.getSubtype()) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                    case 16:
                        return NetworkType.NETWORK_2G;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 17:
                        return NetworkType.NETWORK_3G;
                    case 13:
                    case 18:
                        return NetworkType.NETWORK_4G;
                    default:
                        String subtypeName = activeNetworkInfo.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            return NetworkType.NETWORK_3G;
                        }
                        return NetworkType.NETWORK_UNKNOWN;
                }
            }
            return NetworkType.NETWORK_UNKNOWN;
        }
        return NetworkType.NETWORK_NO;
    }

    private static boolean isEthernet() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        return state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING;
    }

    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static Utils.Task<String> getIPAddressAsync(final boolean z, Utils.Consumer<String> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<String>(consumer) { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.5
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public String doInBackground() {
                return NetworkUtils.getIPAddress(z);
            }
        });
    }

    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        linkedList.addFirst(inetAddresses.nextElement());
                    }
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                InetAddress inetAddress = (InetAddress) it.next();
                if (!inetAddress.isLoopbackAddress()) {
                    String hostAddress = inetAddress.getHostAddress();
                    boolean z2 = hostAddress.indexOf(58) < 0;
                    if (z) {
                        if (z2) {
                            return hostAddress;
                        }
                    } else if (!z2) {
                        int indexOf = hostAddress.indexOf(37);
                        if (indexOf < 0) {
                            return hostAddress.toUpperCase();
                        }
                        return hostAddress.substring(0, indexOf).toUpperCase();
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp() && !nextElement.isLoopback()) {
                    List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                    int size = interfaceAddresses.size();
                    for (int i = 0; i < size; i++) {
                        InetAddress broadcast = interfaceAddresses.get(i).getBroadcast();
                        if (broadcast != null) {
                            return broadcast.getHostAddress();
                        }
                    }
                    continue;
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static Utils.Task<String> getDomainAddressAsync(final String str, Utils.Consumer<String> consumer) {
        return UtilsBridge.doAsync(new Utils.Task<String>(consumer) { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.6
            @Override // com.movieboxpro.android.utils.tool.ThreadUtils.Task
            public String doInBackground() {
                return NetworkUtils.getDomainAddress(str);
            }
        });
    }

    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    public static String getGatewayByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    public static String getNetMaskByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }

    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.getInstance().registerListener(onNetworkStatusChangedListener);
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.getInstance().unregisterListener(onNetworkStatusChangedListener);
    }

    /* loaded from: classes3.dex */
    public static final class NetworkChangedReceiver extends BroadcastReceiver {
        private Set<OnNetworkStatusChangedListener> mListeners = new HashSet();
        private NetworkType mType;

        /* JADX INFO: Access modifiers changed from: private */
        public static NetworkChangedReceiver getInstance() {
            return LazyHolder.INSTANCE;
        }

        void registerListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            UtilsBridge.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.NetworkChangedReceiver.1
                @Override // java.lang.Runnable
                public void run() {
                    int size = NetworkChangedReceiver.this.mListeners.size();
                    NetworkChangedReceiver.this.mListeners.add(onNetworkStatusChangedListener);
                    if (size == 0 && NetworkChangedReceiver.this.mListeners.size() == 1) {
                        NetworkChangedReceiver.this.mType = NetworkUtils.getNetworkType();
                        Utils.getApp().registerReceiver(NetworkChangedReceiver.getInstance(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                    }
                }
            });
        }

        void unregisterListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return;
            }
            UtilsBridge.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.NetworkChangedReceiver.2
                @Override // java.lang.Runnable
                public void run() {
                    int size = NetworkChangedReceiver.this.mListeners.size();
                    NetworkChangedReceiver.this.mListeners.remove(onNetworkStatusChangedListener);
                    if (size == 1 && NetworkChangedReceiver.this.mListeners.size() == 0) {
                        Utils.getApp().unregisterReceiver(NetworkChangedReceiver.getInstance());
                    }
                }
            });
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                UtilsBridge.runOnUiThreadDelayed(new Runnable() { // from class: com.movieboxpro.android.utils.tool.NetworkUtils.NetworkChangedReceiver.3
                    @Override // java.lang.Runnable
                    public void run() {
                        NetworkType networkType = NetworkUtils.getNetworkType();
                        if (NetworkChangedReceiver.this.mType == networkType) {
                            return;
                        }
                        NetworkChangedReceiver.this.mType = networkType;
                        if (networkType == NetworkType.NETWORK_NO) {
                            for (OnNetworkStatusChangedListener onNetworkStatusChangedListener : NetworkChangedReceiver.this.mListeners) {
                                onNetworkStatusChangedListener.onDisconnected();
                            }
                            return;
                        }
                        for (OnNetworkStatusChangedListener onNetworkStatusChangedListener2 : NetworkChangedReceiver.this.mListeners) {
                            onNetworkStatusChangedListener2.onConnected(networkType);
                        }
                    }
                }, 1000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class LazyHolder {
            private static final NetworkChangedReceiver INSTANCE = new NetworkChangedReceiver();

            private LazyHolder() {
            }
        }
    }
}
