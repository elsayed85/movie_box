package com.movieboxpro.android.utils;

import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.format.Formatter;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class SDCardUtils {
    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isSDCardEnableByEnvironment() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String getSDCardPathByEnvironment() {
        return isSDCardEnableByEnvironment() ? Environment.getExternalStorageDirectory().getAbsolutePath() : "";
    }

    public static List<SDCardInfo> getSDCardInfo() {
        ArrayList arrayList = new ArrayList();
        StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
        if (storageManager == null) {
            return arrayList;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            List<StorageVolume> storageVolumes = storageManager.getStorageVolumes();
            try {
                if (Build.VERSION.SDK_INT > 29) {
                    for (StorageVolume storageVolume : storageVolumes) {
                        boolean isRemovable = storageVolume.isRemovable();
                        String state = storageVolume.getState();
                        File directory = storageVolume.getDirectory();
                        if (directory != null) {
                            arrayList.add(new SDCardInfo(directory.getPath(), state, isRemovable));
                        }
                    }
                } else {
                    Method method = StorageVolume.class.getMethod("getPath", new Class[0]);
                    for (StorageVolume storageVolume2 : storageVolumes) {
                        arrayList.add(new SDCardInfo((String) method.invoke(storageVolume2, new Object[0]), storageVolume2.getState(), storageVolume2.isRemovable()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        } else {
            try {
                Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                Method method2 = cls.getMethod("getPath", new Class[0]);
                Method method3 = cls.getMethod("isRemovable", new Class[0]);
                Method method4 = StorageManager.class.getMethod("getVolumeState", String.class);
                Object invoke = StorageManager.class.getMethod("getVolumeList", new Class[0]).invoke(storageManager, new Object[0]);
                int length = Array.getLength(invoke);
                for (int i = 0; i < length; i++) {
                    Object obj = Array.get(invoke, i);
                    String str = (String) method2.invoke(obj, new Object[0]);
                    arrayList.add(new SDCardInfo(str, (String) method4.invoke(storageManager, str), ((Boolean) method3.invoke(obj, new Object[0])).booleanValue()));
                }
            } catch (ClassNotFoundException e4) {
                e4.printStackTrace();
            } catch (IllegalAccessException e5) {
                e5.printStackTrace();
            } catch (NoSuchMethodException e6) {
                e6.printStackTrace();
            } catch (InvocationTargetException e7) {
                e7.printStackTrace();
            }
        }
        return arrayList;
    }

    public static List<String> getMountedSDCardPath() {
        ArrayList arrayList = new ArrayList();
        List<SDCardInfo> sDCardInfo = getSDCardInfo();
        if (sDCardInfo != null && !sDCardInfo.isEmpty()) {
            for (SDCardInfo sDCardInfo2 : sDCardInfo) {
                String str = sDCardInfo2.state;
                if (str != null && "mounted".equals(str.toLowerCase())) {
                    arrayList.add(sDCardInfo2.path);
                }
            }
        }
        return arrayList;
    }

    public static long getExternalTotalSize() {
        return FileUtils.getFsTotalSize(getSDCardPathByEnvironment());
    }

    public static long getExternalAvailableSize() {
        return FileUtils.getFsAvailableSize(getSDCardPathByEnvironment());
    }

    public static long getInternalTotalSize() {
        return FileUtils.getFsTotalSize(Environment.getDataDirectory().getAbsolutePath());
    }

    public static long getInternalAvailableSize() {
        return FileUtils.getFsAvailableSize(Environment.getDataDirectory().getAbsolutePath());
    }

    /* loaded from: classes3.dex */
    public static class SDCardInfo {
        private long availableSize;
        private boolean isRemovable;
        private String path;
        private String state;
        private long totalSize;

        SDCardInfo(String str, String str2, boolean z) {
            this.path = str;
            this.state = str2;
            this.isRemovable = z;
            this.totalSize = FileUtils.getFsTotalSize(str);
            this.availableSize = FileUtils.getFsAvailableSize(str);
        }

        public String getPath() {
            return this.path;
        }

        public String getState() {
            return this.state;
        }

        public boolean isRemovable() {
            return this.isRemovable;
        }

        public long getTotalSize() {
            return this.totalSize;
        }

        public long getAvailableSize() {
            return this.availableSize;
        }

        public String toString() {
            return "SDCardInfo {path = " + this.path + ", state = " + this.state + ", isRemovable = " + this.isRemovable + ", totalSize = " + Formatter.formatFileSize(Utils.getApp(), this.totalSize) + ", availableSize = " + Formatter.formatFileSize(Utils.getApp(), this.availableSize) + '}';
        }
    }
}
