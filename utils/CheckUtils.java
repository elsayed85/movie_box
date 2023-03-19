package com.movieboxpro.android.utils;

import android.text.TextUtils;
import android.util.Patterns;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;
/* loaded from: classes3.dex */
public class CheckUtils {
    public static final String PATTENR_INVITE_CODE = "(<stoneread>)(\\d{1,})(</stoneread>)";
    private static final String PATTERN_PHONE = "[1][3578]\\d{9}";
    public static final String PATTERN_URL = "(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    public static final String PATTERN_URL_FULL = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";
    private static final String PATTERN_USERNAME = "[a-zA-Z0-9_-]{6,16}";

    public static boolean checkPhone(String str) {
        return !TextUtils.isEmpty(str) && str.matches(PATTERN_PHONE);
    }

    public static boolean checkUsername(String str) {
        return !TextUtils.isEmpty(str) && str.matches(PATTERN_USERNAME);
    }

    public static boolean checkEmail(String str) {
        return !TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static boolean hasUrl(String str) {
        return Pattern.compile(PATTERN_URL_FULL).matcher(str).find();
    }

    public static boolean checkFileExists(String str, String str2) {
        if (str != null) {
            File file = new File(str);
            if (file.exists() && file.isFile()) {
                try {
                    return HexDump.toHexString(MD5Util.md5(new FileInputStream(file))).equalsIgnoreCase(str2);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean checkInviteCode(String str) {
        return str != null && str.matches(PATTENR_INVITE_CODE);
    }

    public static boolean checkFileMd5(File file, String str) {
        FileInputStream fileInputStream;
        boolean z = false;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    String hexString = HexDump.toHexString(MD5Util.md5(fileInputStream));
                    if (str != null) {
                        if (str.equalsIgnoreCase(hexString)) {
                            z = true;
                        }
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return z;
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return false;
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    fileInputStream2 = fileInputStream;
                    e.printStackTrace();
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream2 = fileInputStream;
                    if (fileInputStream2 != null) {
                        try {
                            fileInputStream2.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e = e5;
            } catch (NoSuchAlgorithmException e6) {
                e = e6;
            }
        } catch (IOException e7) {
            e7.printStackTrace();
        }
    }
}
