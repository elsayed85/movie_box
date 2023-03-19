package com.movieboxpro.android.utils;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class FileUtil {
    private static final String TAG = FileUtil.class.getSimpleName();

    public static void fileChannelCopy(File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file2);
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    channel.transferTo(0L, channel.size(), fileOutputStream.getChannel());
                    fileOutputStream.close();
                    fileInputStream.close();
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream2 = fileOutputStream;
                    e.printStackTrace();
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream2 = fileOutputStream;
                    if (fileOutputStream2 != null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        } catch (IOException e5) {
            e = e5;
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public static String formatFileSizeToString(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j < 1024) {
            return decimalFormat.format(j) + "B";
        } else if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            StringBuilder sb = new StringBuilder();
            double d = j;
            Double.isNaN(d);
            sb.append(decimalFormat.format(d / 1024.0d));
            sb.append("K");
            return sb.toString();
        } else if (j < IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            StringBuilder sb2 = new StringBuilder();
            double d2 = j;
            Double.isNaN(d2);
            sb2.append(decimalFormat.format(d2 / 1048576.0d));
            sb2.append("M");
            return sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            double d3 = j;
            Double.isNaN(d3);
            sb3.append(decimalFormat.format(d3 / 1.073741824E9d));
            sb3.append("G");
            return sb3.toString();
        }
    }

    public static boolean deleteFile(File file) throws IOException {
        return file != null && file.delete();
    }

    public static String getFileOutputString(String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str), 8192);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(ShellUtil.COMMAND_LINE_END);
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
