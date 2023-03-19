package com.movieboxpro.android.config;

import android.content.Context;
import android.text.TextUtils;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.MLog;
/* loaded from: classes.dex */
public class ConfigUtils {
    private static final String TAG = "ConfigUtils";

    public static boolean containsConfig(String str) {
        String format = String.format("%s_%s_%s", str, App.channel, App.versionName);
        boolean containsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), format);
        MLog.d(TAG, format + ": " + containsKey);
        if (!containsKey) {
            format = String.format("%s_%s", str, App.channel);
            containsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), format);
        }
        MLog.d(TAG, format + ": " + containsKey);
        if (!containsKey) {
            format = String.format("%s_%s", str, App.versionName);
            containsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), format);
        }
        MLog.d(TAG, format + ": " + containsKey);
        if (!containsKey) {
            format = String.format("%s_%s", App.versionName, str);
            containsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), format);
        }
        MLog.d(TAG, format + ": " + containsKey);
        if (containsKey) {
            str = format;
        } else {
            containsKey = OnlineConfigAgent.getInstance().containsKey(App.getContext(), str);
        }
        MLog.d(TAG, str + ": " + containsKey);
        return containsKey;
    }

    public static String readStringConfig(String str) {
        String configParam = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), String.format("%s_%s_%s", str, App.channel, App.versionName));
        if (TextUtils.isEmpty(configParam)) {
            String format = String.format("%s_%s", str, App.channel);
            String configParam2 = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), format);
            MLog.d(TAG, "readOnline: " + format + ": " + configParam2);
            configParam = configParam2;
        }
        if (TextUtils.isEmpty(configParam)) {
            String format2 = String.format("%s_%s", str, App.versionName);
            String configParam3 = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), format2);
            MLog.d(TAG, "readOnline: " + format2 + ": " + configParam3);
            configParam = configParam3;
        }
        if (TextUtils.isEmpty(configParam)) {
            String format3 = String.format("%s_%s", App.versionName, str);
            String configParam4 = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), format3);
            MLog.d(TAG, "readOnline: " + format3 + ": " + configParam4);
            configParam = configParam4;
        }
        if (TextUtils.isEmpty(configParam)) {
            String configParam5 = OnlineConfigAgent.getInstance().getConfigParam(App.getContext(), str);
            MLog.d(TAG, "readOnline: " + str + ": " + configParam5);
            return configParam5;
        }
        return configParam;
    }

    public static int readIntBoolConfig(String str) {
        return readIntBoolConfig(str, 0);
    }

    public static int readIntBoolConfig(String str, int i) {
        String readStringConfig = readStringConfig(str);
        if (readStringConfig == null || !readStringConfig.matches("[01]")) {
            return i > 0 ? 1 : 0;
        }
        return Integer.parseInt(readStringConfig);
    }

    public static boolean readBooleanConfig(String str) {
        return readBooleanConfig(str, false);
    }

    public static boolean readBooleanConfig(String str, boolean z) {
        String readStringConfig = readStringConfig(str);
        return (readStringConfig == null || !readStringConfig.matches("[01]")) ? z : Integer.parseInt(readStringConfig) == 1;
    }

    public static int readIntegerConfig(String str) {
        return readIntegerConfig(str, -1);
    }

    public static int readIntegerConfig(String str, int i) {
        String readStringConfig = readStringConfig(str);
        return (readStringConfig == null || !readStringConfig.matches("\\d+")) ? i : Integer.parseInt(readStringConfig);
    }

    public static void writeIntgerConfig(String str, int i) {
        OnlineConfigAgent onlineConfigAgent = OnlineConfigAgent.getInstance();
        Context context = App.getContext();
        onlineConfigAgent.setConfigParam(context, str, i + "");
    }
}
