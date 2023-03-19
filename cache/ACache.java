package com.movieboxpro.android.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import com.koushikdutta.async.http.cache.ResponseCacheMiddleware;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.MLog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ACache {
    private static final int MAX_COUNT = Integer.MAX_VALUE;
    private static final int MAX_SIZE = 104857600;
    private static final String TAG = "ACache";
    private static final int TIME_DAY = 86400;
    private static final int TIME_HOUR = 3600;
    private static Map<String, ACache> mInstanceMap = new HashMap();
    private ACacheManager mCache;

    public static ACache getOld(Context context) {
        return get(context, TAG);
    }

    public static void clearOldCache(Context context) {
        FileUtils.deleteFile(new File(context.getFilesDir(), TAG));
    }

    public static ACache get(Context context) {
        return get(context, ResponseCacheMiddleware.CACHE);
    }

    public static ACache get(Context context, String str) {
        return get(new File(context.getFilesDir(), str), 104857600L, Integer.MAX_VALUE);
    }

    public static ACache get(File file) {
        return get(file, 104857600L, Integer.MAX_VALUE);
    }

    public static ACache get(Context context, long j, int i) {
        return get(new File(context.getFilesDir(), ResponseCacheMiddleware.CACHE), j, i);
    }

    public static ACache get(File file, long j, int i) {
        Map<String, ACache> map = mInstanceMap;
        ACache aCache = map.get(file.getAbsoluteFile() + myPid());
        if (aCache == null) {
            ACache aCache2 = new ACache(file, j, i);
            Map<String, ACache> map2 = mInstanceMap;
            map2.put(file.getAbsolutePath() + myPid(), aCache2);
            return aCache2;
        }
        return aCache;
    }

    private static String myPid() {
        return "_" + Process.myPid();
    }

    private ACache(File file, long j, int i) {
        if (file.exists() || file.mkdirs()) {
            this.mCache = new ACacheManager(file, j, i);
        }
    }

    public void put(String str, String str2) {
        BufferedWriter bufferedWriter;
        File newFile = this.mCache.newFile(str);
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(newFile), 1024);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedWriter.write(str2);
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                this.mCache.put(newFile);
            }
        } catch (IOException e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    this.mCache.put(newFile);
                }
            }
            this.mCache.put(newFile);
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            this.mCache.put(newFile);
            throw th;
        }
        this.mCache.put(newFile);
    }

    public void put(String str, String str2, int i) {
        put(str, Utils.newStringWithDateInfo(i, str2));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    public String getAsString(String str) {
        BufferedReader bufferedReader;
        File file = this.mCache.get(str);
        ?? exists = file.exists();
        BufferedReader bufferedReader2 = null;
        try {
            if (exists == 0) {
                return null;
            }
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String str2 = "";
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str2 = str2 + readLine;
                    } catch (IOException e) {
                        e = e;
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        return null;
                    }
                }
                if (Utils.isDue(str2)) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                    remove(str);
                    return null;
                }
                String clearDateInfo = Utils.clearDateInfo(str2);
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return clearDateInfo;
            } catch (IOException e5) {
                e = e5;
                bufferedReader = null;
            } catch (Throwable th) {
                th = th;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = exists;
        }
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject.toString());
    }

    public void put(String str, JSONObject jSONObject, int i) {
        put(str, jSONObject.toString(), i);
    }

    public JSONObject getAsJSONObject(String str) {
        try {
            return new JSONObject(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray.toString());
    }

    public void put(String str, JSONArray jSONArray, int i) {
        put(str, jSONArray.toString(), i);
    }

    public JSONArray getAsJSONArray(String str) {
        try {
            return new JSONArray(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, byte[] bArr) {
        FileOutputStream fileOutputStream;
        File newFile = this.mCache.newFile(str);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(newFile);
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            fileOutputStream.write(bArr);
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                this.mCache.put(newFile);
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    this.mCache.put(newFile);
                }
            }
            this.mCache.put(newFile);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.flush();
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            this.mCache.put(newFile);
            throw th;
        }
        this.mCache.put(newFile);
    }

    public void put(String str, byte[] bArr, int i) {
        put(str, Utils.newByteArrayWithDateInfo(i, bArr));
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x007d: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:37:0x007d */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private byte[] getAsBinary(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getAsBinary: "
            r0.append(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ACache"
            com.movieboxpro.android.utils.MLog.d(r1, r0)
            r0 = 0
            com.movieboxpro.android.cache.ACache$ACacheManager r2 = r6.mCache     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            java.io.File r2 = com.movieboxpro.android.cache.ACache.ACacheManager.access$400(r2, r7)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            if (r3 != 0) goto L29
            java.lang.String r7 = "getAsBinary: not found"
            com.movieboxpro.android.utils.MLog.d(r1, r7)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            return r0
        L29:
            java.io.RandomAccessFile r3 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            java.lang.String r4 = "r"
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L6a java.lang.Exception -> L6c
            long r4 = r3.length()     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            int r2 = (int) r4     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            r3.read(r2)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            boolean r4 = com.movieboxpro.android.cache.ACache.Utils.access$800(r2)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            if (r4 != 0) goto L52
            java.lang.String r7 = "getAsBinary: isDue: false"
            com.movieboxpro.android.utils.MLog.d(r1, r7)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            byte[] r7 = com.movieboxpro.android.cache.ACache.Utils.access$900(r2)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            r3.close()     // Catch: java.io.IOException -> L4d
            goto L51
        L4d:
            r0 = move-exception
            r0.printStackTrace()
        L51:
            return r7
        L52:
            java.lang.String r2 = "getAsBinary: isDue: true"
            com.movieboxpro.android.utils.MLog.d(r1, r2)     // Catch: java.lang.Exception -> L68 java.lang.Throwable -> L7c
            r3.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r2 = move-exception
            r2.printStackTrace()
        L5f:
            java.lang.String r2 = "getAsBinary: removeFile: true"
            com.movieboxpro.android.utils.MLog.d(r1, r2)
            r6.remove(r7)
            return r0
        L68:
            r7 = move-exception
            goto L6e
        L6a:
            r7 = move-exception
            goto L7e
        L6c:
            r7 = move-exception
            r3 = r0
        L6e:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L7c
            if (r3 == 0) goto L7b
            r3.close()     // Catch: java.io.IOException -> L77
            goto L7b
        L77:
            r7 = move-exception
            r7.printStackTrace()
        L7b:
            return r0
        L7c:
            r7 = move-exception
            r0 = r3
        L7e:
            if (r0 == 0) goto L88
            r0.close()     // Catch: java.io.IOException -> L84
            goto L88
        L84:
            r0 = move-exception
            r0.printStackTrace()
        L88:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.cache.ACache.getAsBinary(java.lang.String):byte[]");
    }

    public void put(String str, Serializable serializable) {
        put(str, serializable, -1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.io.ObjectOutputStream] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0033 -> B:33:0x0036). Please submit an issue!!! */
    public void put(String str, Serializable serializable, int i) {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2 = 0;
        ObjectOutputStream objectOutputStream3 = null;
        objectOutputStream2 = 0;
        try {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Exception e) {
                e = e;
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            objectOutputStream2 = objectOutputStream2;
        }
        try {
            objectOutputStream.writeObject(serializable);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            objectOutputStream2 = -1;
            if (i != -1) {
                put(str, byteArray, i);
            } else {
                put(str, byteArray);
            }
            objectOutputStream.close();
        } catch (Exception e3) {
            e = e3;
            objectOutputStream3 = objectOutputStream;
            e.printStackTrace();
            if (objectOutputStream3 != null) {
                objectOutputStream3.close();
                objectOutputStream2 = objectOutputStream3;
            }
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != 0) {
                try {
                    objectOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getAsObject(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "getAsObject: "
            r0.append(r1)
            r0.append(r5)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "ACache"
            com.movieboxpro.android.utils.MLog.d(r1, r0)
            byte[] r5 = r4.getAsBinary(r5)
            r0 = 0
            if (r5 == 0) goto L7c
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L4b
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L46 java.lang.Exception -> L4b
            com.weiphone.reader.cache.MyObjectInputStream r5 = new com.weiphone.reader.cache.MyObjectInputStream     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            r5.<init>(r1)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L43
            java.lang.Object r0 = r5.readObject()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L66
            r1.close()     // Catch: java.io.IOException -> L2f
            goto L33
        L2f:
            r1 = move-exception
            r1.printStackTrace()
        L33:
            r5.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r5 = move-exception
            r5.printStackTrace()
        L3b:
            return r0
        L3c:
            r2 = move-exception
            goto L4e
        L3e:
            r5 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L67
        L43:
            r2 = move-exception
            r5 = r0
            goto L4e
        L46:
            r5 = move-exception
            r1 = r0
            r0 = r5
            r5 = r1
            goto L67
        L4b:
            r2 = move-exception
            r5 = r0
            r1 = r5
        L4e:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L66
            if (r1 == 0) goto L5b
            r1.close()     // Catch: java.io.IOException -> L57
            goto L5b
        L57:
            r1 = move-exception
            r1.printStackTrace()
        L5b:
            if (r5 == 0) goto L65
            r5.close()     // Catch: java.io.IOException -> L61
            goto L65
        L61:
            r5 = move-exception
            r5.printStackTrace()
        L65:
            return r0
        L66:
            r0 = move-exception
        L67:
            if (r1 == 0) goto L71
            r1.close()     // Catch: java.io.IOException -> L6d
            goto L71
        L6d:
            r1 = move-exception
            r1.printStackTrace()
        L71:
            if (r5 == 0) goto L7b
            r5.close()     // Catch: java.io.IOException -> L77
            goto L7b
        L77:
            r5 = move-exception
            r5.printStackTrace()
        L7b:
            throw r0
        L7c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.cache.ACache.getAsObject(java.lang.String):java.lang.Object");
    }

    public void put(String str, Bitmap bitmap) {
        put(str, Utils.Bitmap2Bytes(bitmap));
    }

    public void put(String str, Bitmap bitmap, int i) {
        put(str, Utils.Bitmap2Bytes(bitmap), i);
    }

    public Bitmap getAsBitmap(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return Utils.Bytes2Bimap(getAsBinary(str));
    }

    public void put(String str, Drawable drawable) {
        put(str, Utils.drawable2Bitmap(drawable));
    }

    public void put(String str, Drawable drawable, int i) {
        put(str, Utils.drawable2Bitmap(drawable), i);
    }

    public Drawable getAsDrawable(String str) {
        if (getAsBinary(str) == null) {
            return null;
        }
        return Utils.bitmap2Drawable(Utils.Bytes2Bimap(getAsBinary(str)));
    }

    public File file(String str) {
        File newFile = this.mCache.newFile(str);
        if (newFile.exists()) {
            return newFile;
        }
        return null;
    }

    public boolean remove(String str) {
        MLog.d(TAG, "remove: " + str);
        return this.mCache.remove(str);
    }

    public void clear() {
        MLog.d(TAG, "clear cache");
        this.mCache.clear();
    }

    /* loaded from: classes3.dex */
    public class ACacheManager {
        private final AtomicInteger cacheCount;
        File cacheDir;
        private final AtomicLong cacheSize;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates;
        private final long sizeLimit;

        private ACacheManager(File file, long j, int i) {
            this.lastUsageDates = Collections.synchronizedMap(new HashMap());
            this.cacheDir = file;
            this.sizeLimit = j;
            this.countLimit = i;
            this.cacheSize = new AtomicLong();
            this.cacheCount = new AtomicInteger();
            calculateCacheSizeAndCacheCount();
        }

        private void calculateCacheSizeAndCacheCount() {
            new Thread(new Runnable() { // from class: com.movieboxpro.android.cache.ACache.ACacheManager.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] listFiles = ACacheManager.this.cacheDir.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        int i2 = 0;
                        for (File file : listFiles) {
                            i = (int) (i + ACacheManager.this.calculateSize(file));
                            i2++;
                            ACacheManager.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                        }
                        ACacheManager.this.cacheSize.set(i);
                        ACacheManager.this.cacheCount.set(i2);
                    }
                }
            }).start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void put(File file) {
            int i = this.cacheCount.get();
            while (i + 1 > this.countLimit) {
                this.cacheSize.addAndGet(-removeNext());
                i = this.cacheCount.addAndGet(-1);
            }
            this.cacheCount.addAndGet(1);
            long calculateSize = calculateSize(file);
            long j = this.cacheSize.get();
            while (j + calculateSize > this.sizeLimit) {
                j = this.cacheSize.addAndGet(-removeNext());
            }
            this.cacheSize.addAndGet(calculateSize);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(file, valueOf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File get(String str) {
            File newFile = newFile(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            newFile.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(newFile, valueOf);
            return newFile;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File newFile(String str) {
            File file = this.cacheDir;
            return new File(file, str.hashCode() + "");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean remove(String str) {
            return get(str).delete();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clear() {
            this.lastUsageDates.clear();
            this.cacheSize.set(0L);
            File[] listFiles = this.cacheDir.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        private long removeNext() {
            File file;
            if (this.lastUsageDates.isEmpty()) {
                return 0L;
            }
            Set<Map.Entry<File, Long>> entrySet = this.lastUsageDates.entrySet();
            synchronized (this.lastUsageDates) {
                file = null;
                Long l = null;
                for (Map.Entry<File, Long> entry : entrySet) {
                    if (file == null) {
                        file = entry.getKey();
                        l = entry.getValue();
                    } else {
                        Long value = entry.getValue();
                        if (value.longValue() < l.longValue()) {
                            file = entry.getKey();
                            l = value;
                        }
                    }
                }
            }
            long calculateSize = calculateSize(file);
            if (file.delete()) {
                this.lastUsageDates.remove(file);
            }
            return calculateSize;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long calculateSize(File file) {
            return file.length();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Utils {
        private static final char mSeparator = ' ';

        private Utils() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(String str) {
            return isDue(str.getBytes());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(byte[] bArr) {
            MLog.d(ACache.TAG, "isDue");
            String[] dateInfoFromDate = getDateInfoFromDate(bArr);
            if (dateInfoFromDate != null && dateInfoFromDate.length == 2) {
                String str = dateInfoFromDate[0];
                while (str.startsWith("0")) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(dateInfoFromDate[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String newStringWithDateInfo(int i, String str) {
            return createDateInfo(i) + str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] newByteArrayWithDateInfo(int i, byte[] bArr) {
            byte[] bytes = createDateInfo(i).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String clearDateInfo(String str) {
            return (str == null || !hasDateInfo(str.getBytes())) ? str : str.substring(str.indexOf(32) + 1, str.length());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] clearDateInfo(byte[] bArr) {
            return hasDateInfo(bArr) ? copyOfRange(bArr, indexOf(bArr, mSeparator) + 1, bArr.length) : bArr;
        }

        private static boolean hasDateInfo(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && indexOf(bArr, mSeparator) > 14;
        }

        private static String[] getDateInfoFromDate(byte[] bArr) {
            if (hasDateInfo(bArr)) {
                return new String[]{new String(copyOfRange(bArr, 0, 13)), new String(copyOfRange(bArr, 14, indexOf(bArr, mSeparator)))};
            }
            return null;
        }

        private static int indexOf(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException(i + " > " + i2);
            }
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
            return bArr2;
        }

        private static String createDateInfo(int i) {
            String str = System.currentTimeMillis() + "";
            while (str.length() < 13) {
                str = "0" + str;
            }
            return str + "-" + i + mSeparator;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] Bitmap2Bytes(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bitmap Bytes2Bimap(byte[] bArr) {
            if (bArr.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Bitmap drawable2Bitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static Drawable bitmap2Drawable(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            return new BitmapDrawable(bitmap);
        }
    }
}
