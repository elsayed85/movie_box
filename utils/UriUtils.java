package com.movieboxpro.android.utils;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import master.flame.danmaku.danmaku.parser.IDataSource;
/* loaded from: classes3.dex */
public class UriUtils {
    private static int sBufferSize = 524288;

    public static File uri2FileNormal(Uri uri) {
        return uri2FileReal(uri);
    }

    public static File uri2FileCopy(Uri uri) {
        return copyUri2Cache(uri);
    }

    public static Pair<String, Long> getNameAndSize(Uri uri) {
        Cursor query = Utils.getApp().getContentResolver().query(uri, null, null, null, null);
        try {
            if (query == null) {
                if (query != null) {
                    query.close();
                }
                return new Pair<>("" + System.currentTimeMillis(), 0L);
            }
            try {
                int columnIndex = query.getColumnIndex("_display_name");
                int columnIndex2 = query.getColumnIndex("_size");
                if (query.moveToFirst()) {
                    Pair<String, Long> pair = new Pair<>(query.getString(columnIndex), Long.valueOf(query.getLong(columnIndex2)));
                    if (query != null) {
                        query.close();
                    }
                    return pair;
                }
                Pair<String, Long> pair2 = new Pair<>("" + System.currentTimeMillis(), 0L);
                if (query != null) {
                    query.close();
                }
                return pair2;
            } catch (Exception unused) {
                Pair<String, Long> pair3 = new Pair<>("" + System.currentTimeMillis(), 0L);
                if (query != null) {
                    query.close();
                }
                return pair3;
            }
        } catch (Throwable th) {
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0056, code lost:
        if (r0 == null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0058, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0062, code lost:
        if (r0 != null) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
        r7 = com.movieboxpro.android.utils.Utils.getApp().getContentResolver().openInputStream(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0072, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0073, code lost:
        r7.printStackTrace();
        r7 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.io.File copyUri2Cache(android.net.Uri r7) {
        /*
            java.lang.String r0 = "UriUtils"
            java.lang.String r1 = "copyUri2Cache() called"
            android.util.Log.d(r0, r1)
            android.app.Application r0 = com.movieboxpro.android.utils.Utils.getApp()
            android.content.ContentResolver r1 = r0.getContentResolver()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r7
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            java.lang.String r1 = ""
            if (r0 == 0) goto L62
            java.lang.String r2 = "_display_name"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            boolean r3 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            if (r3 == 0) goto L2d
            java.lang.String r1 = r0.getString(r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L62
        L2d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.<init>()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.append(r1)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.append(r3)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L62
        L41:
            r7 = move-exception
            goto L5c
        L43:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L41
            r2.append(r1)     // Catch: java.lang.Throwable -> L41
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L41
            r2.append(r3)     // Catch: java.lang.Throwable -> L41
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto L65
        L58:
            r0.close()
            goto L65
        L5c:
            if (r0 == 0) goto L61
            r0.close()
        L61:
            throw r7
        L62:
            if (r0 == 0) goto L65
            goto L58
        L65:
            android.app.Application r0 = com.movieboxpro.android.utils.Utils.getApp()     // Catch: java.io.FileNotFoundException -> L72
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.io.FileNotFoundException -> L72
            java.io.InputStream r7 = r0.openInputStream(r7)     // Catch: java.io.FileNotFoundException -> L72
            goto L77
        L72:
            r7 = move-exception
            r7.printStackTrace()
            r7 = 0
        L77:
            java.io.File r0 = new java.io.File
            android.app.Application r2 = com.movieboxpro.android.utils.Utils.getApp()
            java.io.File r2 = r2.getCacheDir()
            r0.<init>(r2, r1)
            writeFileFromIS(r0, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.UriUtils.copyUri2Cache(android.net.Uri):java.io.File");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0056, code lost:
        if (r0 == null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0058, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0062, code lost:
        if (r0 != null) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0065, code lost:
        r7 = com.movieboxpro.android.utils.Utils.getApp().getContentResolver().openInputStream(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0072, code lost:
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0073, code lost:
        r7.printStackTrace();
        r7 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.File copyUri2Cache(android.net.Uri r7, java.io.File r8) {
        /*
            java.lang.String r0 = "UriUtils"
            java.lang.String r1 = "copyUri2Cache() called"
            android.util.Log.d(r0, r1)
            android.app.Application r0 = com.movieboxpro.android.utils.Utils.getApp()
            android.content.ContentResolver r1 = r0.getContentResolver()
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r7
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            java.lang.String r1 = ""
            if (r0 == 0) goto L62
            java.lang.String r2 = "_display_name"
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            boolean r3 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            if (r3 == 0) goto L2d
            java.lang.String r1 = r0.getString(r2)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L62
        L2d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.<init>()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.append(r1)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            r2.append(r3)     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L41 java.lang.Exception -> L43
            goto L62
        L41:
            r7 = move-exception
            goto L5c
        L43:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L41
            r2.append(r1)     // Catch: java.lang.Throwable -> L41
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L41
            r2.append(r3)     // Catch: java.lang.Throwable -> L41
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L41
            if (r0 == 0) goto L65
        L58:
            r0.close()
            goto L65
        L5c:
            if (r0 == 0) goto L61
            r0.close()
        L61:
            throw r7
        L62:
            if (r0 == 0) goto L65
            goto L58
        L65:
            android.app.Application r0 = com.movieboxpro.android.utils.Utils.getApp()     // Catch: java.io.FileNotFoundException -> L72
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.io.FileNotFoundException -> L72
            java.io.InputStream r7 = r0.openInputStream(r7)     // Catch: java.io.FileNotFoundException -> L72
            goto L77
        L72:
            r7 = move-exception
            r7.printStackTrace()
            r7 = 0
        L77:
            java.io.File r0 = new java.io.File
            r0.<init>(r8, r1)
            writeFileFromIS(r0, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.UriUtils.copyUri2Cache(android.net.Uri, java.io.File):java.io.File");
    }

    static boolean writeFileFromIS(File file, InputStream inputStream) {
        BufferedOutputStream bufferedOutputStream;
        if (!FileUtils.createOrExistsFile(file) || inputStream == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                bufferedOutputStream.write(bArr, 0, read);
            }
            try {
                inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            try {
                bufferedOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            return true;
        } catch (IOException e4) {
            e = e4;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            try {
                inputStream.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            try {
                inputStream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e8) {
                    e8.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static InputStream uri2InputStream(Uri uri) {
        new StringBuilder();
        try {
            InputStream openInputStream = Utils.getApp().getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                try {
                    openInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return openInputStream;
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static File uri2File(Uri uri) {
        if (uri == null) {
            return null;
        }
        File uri2FileReal = uri2FileReal(uri);
        return uri2FileReal != null ? uri2FileReal : copyUri2Cache(uri);
    }

    private static File uri2FileReal(Uri uri) {
        Uri uri2;
        File fileFromUri;
        boolean z;
        String str;
        Log.d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if (Build.VERSION.SDK_INT >= 24 && path != null) {
            String[] strArr = {"/external", "/external_path", "/beta_external_files_path"};
            for (int i = 0; i < 3; i++) {
                String str2 = strArr[i];
                if (path.startsWith(str2 + "/")) {
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace(str2, ""));
                    if (file.exists()) {
                        Log.d("UriUtils", uri.toString() + " -> " + str2);
                        return file;
                    }
                }
            }
        }
        if (IDataSource.SCHEME_FILE_TAG.equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            Log.d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        } else if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(Utils.getApp(), uri)) {
            if ("com.android.externalstorage.documents".equals(authority)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                String str3 = split[0];
                if ("primary".equalsIgnoreCase(str3)) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + split[1]);
                }
                StorageManager storageManager = (StorageManager) Utils.getApp().getSystemService("storage");
                try {
                    Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                    Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
                    Method method2 = cls.getMethod("getUuid", new Class[0]);
                    Method method3 = cls.getMethod("getState", new Class[0]);
                    Method method4 = cls.getMethod("getPath", new Class[0]);
                    Method method5 = cls.getMethod("isPrimary", new Class[0]);
                    Method method6 = cls.getMethod("isEmulated", new Class[0]);
                    Object invoke = method.invoke(storageManager, new Object[0]);
                    int length = Array.getLength(invoke);
                    int i2 = 0;
                    while (i2 < length) {
                        Object obj = Array.get(invoke, i2);
                        Object obj2 = invoke;
                        if (!"mounted".equals(method3.invoke(obj, new Object[0])) && !"mounted_ro".equals(method3.invoke(obj, new Object[0]))) {
                            z = false;
                            if (z && ((!((Boolean) method5.invoke(obj, new Object[0])).booleanValue() || !((Boolean) method6.invoke(obj, new Object[0])).booleanValue()) && (str = (String) method2.invoke(obj, new Object[0])) != null && str.equals(str3))) {
                                return new File(method4.invoke(obj, new Object[0]) + "/" + split[1]);
                            }
                            i2++;
                            invoke = obj2;
                        }
                        z = true;
                        if (z) {
                            return new File(method4.invoke(obj, new Object[0]) + "/" + split[1]);
                        }
                        i2++;
                        invoke = obj2;
                    }
                } catch (Exception e) {
                    Log.d("UriUtils", uri.toString() + " parse failed. " + e.toString() + " -> 1_0");
                }
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            } else if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (TextUtils.isEmpty(documentId)) {
                    Log.d("UriUtils", uri.toString() + " parse failed(id is null). -> 1_1");
                    return null;
                } else if (documentId.startsWith("raw:")) {
                    return new File(documentId.substring(4));
                } else {
                    String[] strArr2 = {"content://downloads/public_downloads", "content://downloads/all_downloads", "content://downloads/my_downloads"};
                    for (int i3 = 0; i3 < 3; i3++) {
                        try {
                            fileFromUri = getFileFromUri(ContentUris.withAppendedId(Uri.parse(strArr2[i3]), Long.parseLong(documentId)), "1_1");
                        } catch (Exception unused) {
                        }
                        if (fileFromUri != null) {
                            return fileFromUri;
                        }
                    }
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                    return null;
                }
            } else if ("com.android.providers.media.documents".equals(authority)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str4 = split2[0];
                if ("image".equals(str4)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str4)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (!"audio".equals(str4)) {
                    Log.d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                } else {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getFileFromUri(uri2, "_id=?", new String[]{split2[1]}, "1_2");
            } else if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
                return getFileFromUri(uri, "1_3");
            } else {
                Log.d("UriUtils", uri.toString() + " parse failed. -> 1_4");
                return null;
            }
        } else if (FirebaseAnalytics.Param.CONTENT.equals(scheme)) {
            return getFileFromUri(uri, ExifInterface.GPS_MEASUREMENT_2D);
        } else {
            Log.d("UriUtils", uri.toString() + " parse failed. -> 3");
            return null;
        }
    }

    private static File getFileFromUri(Uri uri, String str) {
        return getFileFromUri(uri, null, null, str);
    }

    private static File getFileFromUri(Uri uri, String str, String[] strArr, String str2) {
        if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
            if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
                return new File(uri.getLastPathSegment());
            }
        } else if ("com.tencent.mtt.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(Environment.getExternalStorageDirectory(), path.substring(10, path.length()));
            }
        } else if ("com.huawei.hidisk.fileprovider".equals(uri.getAuthority())) {
            String path2 = uri.getPath();
            if (!TextUtils.isEmpty(path2)) {
                return new File(path2.replace("/root", ""));
            }
        }
        Cursor query = Utils.getApp().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (query == null) {
                Log.d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            } else if (!query.moveToFirst()) {
                Log.d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                return null;
            } else {
                int columnIndex = query.getColumnIndex("_data");
                if (columnIndex > -1) {
                    return new File(query.getString(columnIndex));
                }
                Log.d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
                return null;
            }
        } catch (Exception unused) {
            Log.d("UriUtils", uri.toString() + " parse failed. -> " + str2);
            return null;
        } finally {
            query.close();
        }
    }
}
