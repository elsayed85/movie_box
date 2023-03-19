package com.movieboxpro.android.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/* loaded from: classes3.dex */
public class MemoryUtils {
    public static final String MEMFREE = "MemFree";
    public static final String MEMTOTAL = "MemTotal";
    private static final String MEM_INFO_PATH = "/proc/meminfo";
    private static final String TAG = "MemoryUtils";

    public static String getTotalMemory(Context context, String str) {
        return getMemInfoIype(context, MEMTOTAL);
    }

    public static String getMemoryFree(Context context, String str) {
        return getMemInfoIype(context, MEMFREE);
    }

    public static String getMemInfoIype(Context context, String str) {
        String readLine;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(MEM_INFO_PATH), 4096);
            do {
                readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
            } while (!readLine.contains(str));
            bufferedReader.close();
            return Formatter.formatFileSize(context, Integer.valueOf(readLine.split("\\s+")[1]).intValue() * 1024);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getInternalToatalSpace(Context context) {
        String path = Environment.getDataDirectory().getPath();
        Log.d(TAG, "root path is " + path);
        StatFs statFs = new StatFs(path);
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        statFs.getAvailableBlocksLong();
        return Formatter.formatFileSize(context, blockCountLong * blockSizeLong);
    }

    public static long getInternalToatalSize(Context context) {
        String path = Environment.getDataDirectory().getPath();
        Log.d(TAG, "root path is " + path);
        StatFs statFs = new StatFs(path);
        long blockSizeLong = statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        statFs.getAvailableBlocksLong();
        return blockCountLong * blockSizeLong;
    }

    public static long getAvailSize(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        return (statFs.getAvailableBlocks() * statFs.getBlockSize()) / 1024;
    }

    public static String getAvailSpace(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        return Formatter.formatFileSize(context, statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
    }

    public static long getAvailSpaceSize(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getAbsolutePath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }
}
