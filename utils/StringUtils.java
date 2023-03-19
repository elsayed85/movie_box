package com.movieboxpro.android.utils;

import java.util.Formatter;
import java.util.Locale;
import java.util.Random;
/* loaded from: classes3.dex */
public class StringUtils {
    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getTwoSpaces() {
        return "\u3000\u3000";
    }

    public static String formatContent(String str) {
        String replace = str.replaceAll("[ ]*", "").replaceAll("[ ]*", "").replace("<br/>", ShellUtil.COMMAND_LINE_END).replace("</p>", ShellUtil.COMMAND_LINE_END).replace("\n\n", ShellUtil.COMMAND_LINE_END);
        String replace2 = replace.replace(ShellUtil.COMMAND_LINE_END, ShellUtil.COMMAND_LINE_END + getTwoSpaces());
        return getTwoSpaces() + replace2;
    }

    public static String formatContent2(String str) {
        return str.replaceAll("[ ]*", "").replaceAll("[ ]*", "").replace("<br/>", ShellUtil.COMMAND_LINE_END).replace("</p>", ShellUtil.COMMAND_LINE_END).replace("\n\n", ShellUtil.COMMAND_LINE_END).replace(ShellUtil.COMMAND_LINE_END, ShellUtil.COMMAND_LINE_END);
    }

    public static String getRandomString(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(36)));
        }
        return sb.toString();
    }

    public static String getPrintSize(long j) {
        if (j < 1024) {
            return j + "B";
        }
        long j2 = j / 1024;
        if (j2 < 1024) {
            return j2 + "KB";
        }
        long j3 = j2 / 1024;
        if (j3 < 1024) {
            long j4 = j3 * 100;
            return (j4 / 100) + com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX + (j4 % 100) + "MB";
        }
        long j5 = (j3 * 100) / 1024;
        return (j5 / 100) + com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX + (j5 % 100) + "GB";
    }

    public static String stringForTime(int i) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb, Locale.getDefault());
        int i2 = i / 1000;
        int i3 = i2 % 60;
        int i4 = (i2 / 60) % 60;
        int i5 = i2 / 3600;
        sb.setLength(0);
        return i5 > 0 ? formatter.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString() : formatter.format("%d:%02d:%02d", Integer.valueOf(i5), Integer.valueOf(i4), Integer.valueOf(i3)).toString();
    }
}
