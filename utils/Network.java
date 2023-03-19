package com.movieboxpro.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import com.dl7.player.utils.NetWorkUtils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes3.dex */
public class Network {
    private static final String TAG = Network.class.getSimpleName();

    /* loaded from: classes3.dex */
    public enum NetType {
        None(1),
        Mobile(2),
        Wifi(4),
        Other(8);
        
        public int value;

        NetType(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes3.dex */
    public enum NetWorkType {
        UnKnown(-1),
        Wifi(1),
        Net2G(2),
        Net3G(3),
        Net4G(4);
        
        public int value;

        NetWorkType(int i) {
            this.value = i;
        }
    }

    public static ConnectivityManager getConnectivityManager(Context context) {
        return (ConnectivityManager) context.getSystemService("connectivity");
    }

    public static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo;
        return (context == null || (activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo()) == null || !activeNetworkInfo.isConnected()) ? false : true;
    }

    public static boolean isConnectedOrConnecting(Context context) {
        NetworkInfo[] allNetworkInfo = getConnectivityManager(context).getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.isConnectedOrConnecting()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static NetType getConnectedType(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            if (type != 0) {
                if (type == 1) {
                    return NetType.Wifi;
                }
                return NetType.Other;
            }
            return NetType.Mobile;
        }
        return NetType.None;
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1 && activeNetworkInfo.isConnected();
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0 && activeNetworkInfo.isConnected();
    }

    public static boolean isAvailable(Context context) {
        return isWifiAvailable(context) || (isMobileAvailable(context) && isMobileEnabled(context));
    }

    public static boolean isWifiAvailable(Context context) {
        NetworkInfo[] allNetworkInfo = getConnectivityManager(context).getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getType() == 1) {
                    return networkInfo.isAvailable();
                }
            }
        }
        return false;
    }

    public static boolean isMobileAvailable(Context context) {
        NetworkInfo[] allNetworkInfo = getConnectivityManager(context).getAllNetworkInfo();
        if (allNetworkInfo != null) {
            for (NetworkInfo networkInfo : allNetworkInfo) {
                if (networkInfo.getType() == 0) {
                    return networkInfo.isAvailable();
                }
            }
        }
        return false;
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

    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService(NetWorkUtils.NETWORK_TYPE_WIFI);
        return wifiManager == null ? "" : Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }

    public static boolean isMobileEnabled(Context context) {
        try {
            Method declaredMethod = ConnectivityManager.class.getDeclaredMethod("getMobileDataEnabled", new Class[0]);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(getConnectivityManager(context), new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static String getDomainAddress(String str) {
        try {
            String[] split = str.split(":");
            return InetAddress.getByName(split.length > 0 ? split[0] : "").getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getConnectedTypeINT(Context context) {
        NetworkInfo activeNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            return activeNetworkInfo.getType();
        }
        return -1;
    }

    public static int getTelNetworkTypeINT(Context context) {
        return getTelephonyManager(context).getNetworkType();
    }

    public static NetWorkType getNetworkType(Context context) {
        int connectedTypeINT = getConnectedTypeINT(context);
        if (connectedTypeINT != 0) {
            if (connectedTypeINT == 1) {
                return NetWorkType.Wifi;
            }
            if (connectedTypeINT != 2 && connectedTypeINT != 3 && connectedTypeINT != 4 && connectedTypeINT != 5) {
                return NetWorkType.UnKnown;
            }
        }
        switch (getTelephonyManager(context).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return NetWorkType.Net2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return NetWorkType.Net3G;
            case 13:
                return NetWorkType.Net4G;
            default:
                return NetWorkType.UnKnown;
        }
    }
}
