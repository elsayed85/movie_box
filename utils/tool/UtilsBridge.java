package com.movieboxpro.android.utils.tool;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lxj.xpopup.util.PermissionConstants;
import com.movieboxpro.android.utils.KeyboardUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.tool.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public class UtilsBridge {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void preLoad() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void init(Application application) {
        UtilsActivityLifecycleImpl.INSTANCE.init(application);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unInit(Application application) {
        UtilsActivityLifecycleImpl.INSTANCE.unInit(application);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Activity getTopActivity() {
        return UtilsActivityLifecycleImpl.INSTANCE.getTopActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        UtilsActivityLifecycleImpl.INSTANCE.addOnAppStatusChangedListener(onAppStatusChangedListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeOnAppStatusChangedListener(Utils.OnAppStatusChangedListener onAppStatusChangedListener) {
        UtilsActivityLifecycleImpl.INSTANCE.removeOnAppStatusChangedListener(onAppStatusChangedListener);
    }

    public static void addActivityLifecycleCallbacks(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.addActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static void removeActivityLifecycleCallbacks(Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addActivityLifecycleCallbacks(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.addActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeActivityLifecycleCallbacks(Activity activity) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void removeActivityLifecycleCallbacks(Activity activity, Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        UtilsActivityLifecycleImpl.INSTANCE.removeActivityLifecycleCallbacks(activity, activityLifecycleCallbacks);
    }

    public static List<Activity> getActivityList() {
        return UtilsActivityLifecycleImpl.INSTANCE.getActivityList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Application getApplicationByReflect() {
        return UtilsActivityLifecycleImpl.INSTANCE.getApplicationByReflect();
    }

    public static boolean isAppForeground() {
        return UtilsActivityLifecycleImpl.INSTANCE.isAppForeground();
    }

    public static boolean isActivityAlive(Activity activity) {
        return ActivityUtils.isActivityAlive(activity);
    }

    static String getLauncherActivity(String str) {
        return ActivityUtils.getLauncherActivity(str);
    }

    static Activity getActivityByContext(Context context) {
        return ActivityUtils.getActivityByContext(context);
    }

    static void startHomeActivity() {
        ActivityUtils.startHomeActivity();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void finishAllActivities() {
        ActivityUtils.finishAllActivities();
    }

    static boolean isAppRunning(String str) {
        return !isSpace(str) && str.equals(getForegroundProcessName());
    }

    public static boolean isGranted(String... strArr) {
        Pair<List<String>, List<String>> requestAndDeniedPermissions = getRequestAndDeniedPermissions(strArr);
        if (((List) requestAndDeniedPermissions.second).isEmpty()) {
            Iterator it = ((List) requestAndDeniedPermissions.first).iterator();
            while (it.hasNext()) {
                if (!isGranted((String) it.next())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    public static List<String> getPermissions(String str) {
        try {
            String[] strArr = Utils.getApp().getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
            if (strArr == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(strArr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static Pair<List<String>, List<String>> getRequestAndDeniedPermissions(String... strArr) {
        String[] permissions;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List<String> permissions2 = getPermissions();
        for (String str : strArr) {
            boolean z = false;
            for (String str2 : PermissionConstants.getPermissions(str)) {
                if (permissions2.contains(str2)) {
                    arrayList.add(str2);
                    z = true;
                }
            }
            if (!z) {
                arrayList2.add(str);
                Log.e("PermissionUtils", "U should add the permission of " + str + " in manifest.");
            }
        }
        return Pair.create(arrayList, arrayList2);
    }

    public static String getForegroundProcessName() {
        List<UsageStats> list;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null && runningAppProcesses.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        if (Build.VERSION.SDK_INT > 21) {
            PackageManager packageManager = Utils.getApp().getPackageManager();
            Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            Log.i("ProcessUtils", queryIntentActivities.toString());
            if (queryIntentActivities.size() <= 0) {
                Log.i("ProcessUtils", "getForegroundProcessName: noun of access to usage information.");
                return "";
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(Utils.getApp().getPackageName(), 0);
                AppOpsManager appOpsManager = (AppOpsManager) Utils.getApp().getSystemService("appops");
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    intent.addFlags(268435456);
                    Utils.getApp().startActivity(intent);
                }
                if (appOpsManager.checkOpNoThrow("android:get_usage_stats", applicationInfo.uid, applicationInfo.packageName) != 0) {
                    Log.i("ProcessUtils", "getForegroundProcessName: refuse to device usage stats.");
                    return "";
                }
                UsageStatsManager usageStatsManager = (UsageStatsManager) Utils.getApp().getSystemService("usagestats");
                if (usageStatsManager != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    list = usageStatsManager.queryUsageStats(4, currentTimeMillis - 604800000, currentTimeMillis);
                } else {
                    list = null;
                }
                if (list != null && !list.isEmpty()) {
                    UsageStats usageStats = null;
                    for (UsageStats usageStats2 : list) {
                        if (usageStats == null || usageStats2.getLastTimeUsed() > usageStats.getLastTimeUsed()) {
                            usageStats = usageStats2;
                        }
                    }
                    if (usageStats == null) {
                        return null;
                    }
                    return usageStats.getPackageName();
                }
                return "";
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    static boolean isAppInstalled(String str) {
        if (isSpace(str)) {
            return false;
        }
        try {
            return Utils.getApp().getPackageManager().getApplicationInfo(str, 0).enabled;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    static void relaunchApp() {
        AppUtils.relaunchApp();
    }

    public static int getNavBarHeight() {
        Resources resources = Utils.getApp().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getStatusBarHeight() {
        Resources resources = Utils.getApp().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static Bitmap view2Bitmap(View view) {
        return ViewUtils.INSTANCE.view2Bitmap(view);
    }

    public static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static Intent getLaunchAppDetailsSettingsIntent(String str, boolean z) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + str));
        return getIntent(intent, z);
    }

    private static Intent getIntent(Intent intent, boolean z) {
        return z ? intent.addFlags(268435456) : intent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fixSoftInputLeaks(Activity activity) {
        KeyboardUtils.fixSoftInputLeaks(activity);
    }

    public static boolean isGrantedDrawOverlays() {
        return Settings.canDrawOverlays(Utils.getApp());
    }

    static SPUtils getSpUtils4Utils() {
        return SPUtils.getInstance("Utils");
    }

    public static String getString(int i) {
        return StringUtils.getString(i);
    }

    public static String getString(int i, Object... objArr) {
        return StringUtils.getString(i, objArr);
    }

    public static String format(String str, Object... objArr) {
        return StringUtils.format(str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> Utils.Task<T> doAsync(Utils.Task<T> task) {
        ThreadUtils.getCachedPool().execute(task);
        return task;
    }

    public static void runOnUiThread(Runnable runnable) {
        ThreadUtils.runOnUiThread(runnable);
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        ThreadUtils.runOnUiThreadDelayed(runnable, j);
    }

    public static View layoutId2View(int i) {
        return ((LayoutInflater) Utils.getApp().getSystemService("layout_inflater")).inflate(i, (ViewGroup) null);
    }

    /* loaded from: classes3.dex */
    static final class FileHead {
        private LinkedHashMap<String, String> mFirst = new LinkedHashMap<>();
        private LinkedHashMap<String, String> mLast = new LinkedHashMap<>();
        private String mName;

        FileHead(String str) {
            this.mName = str;
        }

        void addFirst(String str, String str2) {
            append2Host(this.mFirst, str, str2);
        }

        void append(Map<String, String> map) {
            append2Host(this.mLast, map);
        }

        void append(String str, String str2) {
            append2Host(this.mLast, str, str2);
        }

        private void append2Host(Map<String, String> map, Map<String, String> map2) {
            if (map2 == null || map2.isEmpty()) {
                return;
            }
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                append2Host(map, entry.getKey(), entry.getValue());
            }
        }

        private void append2Host(Map<String, String> map, String str, String str2) {
            int length;
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                return;
            }
            if (19 - str.length() > 0) {
                str = str + "                   ".substring(0, length);
            }
            map.put(str, str2);
        }

        public String getAppended() {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : this.mLast.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append(ShellUtil.COMMAND_LINE_END);
            }
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            String str = "************* " + this.mName + " Head ****************\n";
            sb.append(str);
            for (Map.Entry<String, String> entry : this.mFirst.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                sb.append(entry.getValue());
                sb.append(ShellUtil.COMMAND_LINE_END);
            }
            sb.append("Device Manufacturer: ");
            sb.append(Build.MANUFACTURER);
            sb.append(ShellUtil.COMMAND_LINE_END);
            sb.append("Device Model       : ");
            sb.append(Build.MODEL);
            sb.append(ShellUtil.COMMAND_LINE_END);
            sb.append("Android Version    : ");
            sb.append(Build.VERSION.RELEASE);
            sb.append(ShellUtil.COMMAND_LINE_END);
            sb.append("Android SDK        : ");
            sb.append(Build.VERSION.SDK_INT);
            sb.append(ShellUtil.COMMAND_LINE_END);
            sb.append(getAppended());
            sb.append(str);
            sb.append(ShellUtil.COMMAND_LINE_END);
            return sb.toString();
        }
    }
}
