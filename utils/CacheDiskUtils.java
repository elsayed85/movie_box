package com.movieboxpro.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.movieboxpro.android.app.Constant;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes3.dex */
public class CacheDiskUtils implements CacheConstants {
    private static final Map<String, CacheDiskUtils> CACHE_MAP = new HashMap();
    private static final String CACHE_PREFIX = "cdu_";
    private static final int DEFAULT_MAX_COUNT = Integer.MAX_VALUE;
    private static final long DEFAULT_MAX_SIZE = Long.MAX_VALUE;
    private static final String TYPE_BITMAP = "bi_";
    private static final String TYPE_BYTE = "by_";
    private static final String TYPE_DRAWABLE = "dr_";
    private static final String TYPE_JSON_ARRAY = "ja_";
    private static final String TYPE_JSON_OBJECT = "jo_";
    private static final String TYPE_PARCELABLE = "pa_";
    private static final String TYPE_SERIALIZABLE = "se_";
    private static final String TYPE_STRING = "st_";
    private final File mCacheDir;
    private final String mCacheKey;
    private DiskCacheManager mDiskCacheManager;
    private final int mMaxCount;
    private final long mMaxSize;

    public static CacheDiskUtils getInstance() {
        return getInstance(new File(Constant.DIR_NET_DATA_CACHE), Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(String str) {
        return getInstance(str, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(long j, int i) {
        return getInstance("", j, i);
    }

    public static CacheDiskUtils getInstance(String str, long j, int i) {
        if (isSpace(str)) {
            str = "cacheUtils";
        }
        return getInstance(new File(Utils.getApp().getCacheDir(), str), j, i);
    }

    public static CacheDiskUtils getInstance(File file) {
        return getInstance(file, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheDiskUtils getInstance(File file, long j, int i) {
        String str = file.getAbsoluteFile() + "_" + j + "_" + i;
        CacheDiskUtils cacheDiskUtils = CACHE_MAP.get(str);
        if (cacheDiskUtils == null) {
            synchronized (CacheDiskUtils.class) {
                cacheDiskUtils = CACHE_MAP.get(str);
                if (cacheDiskUtils == null) {
                    cacheDiskUtils = new CacheDiskUtils(str, file, j, i);
                    CACHE_MAP.put(str, cacheDiskUtils);
                }
            }
        }
        return cacheDiskUtils;
    }

    private CacheDiskUtils(String str, File file, long j, int i) {
        this.mCacheKey = str;
        this.mCacheDir = file;
        this.mMaxSize = j;
        this.mMaxCount = i;
    }

    private DiskCacheManager getDiskCacheManager() {
        if (this.mCacheDir.exists()) {
            if (this.mDiskCacheManager == null) {
                this.mDiskCacheManager = new DiskCacheManager(this.mCacheDir, this.mMaxSize, this.mMaxCount);
            }
        } else if (this.mCacheDir.mkdirs()) {
            this.mDiskCacheManager = new DiskCacheManager(this.mCacheDir, this.mMaxSize, this.mMaxCount);
        } else {
            Log.e("CacheDiskUtils", "can't make dirs in " + this.mCacheDir.getAbsolutePath());
        }
        return this.mDiskCacheManager;
    }

    public String toString() {
        return this.mCacheKey + "@" + Integer.toHexString(hashCode());
    }

    public void put(String str, byte[] bArr) {
        put(str, bArr, -1);
    }

    public void put(String str, byte[] bArr, int i) {
        realPutBytes(TYPE_BYTE + str, bArr, i);
    }

    private void realPutBytes(String str, byte[] bArr, int i) {
        DiskCacheManager diskCacheManager;
        if (bArr == null || (diskCacheManager = getDiskCacheManager()) == null) {
            return;
        }
        if (i >= 0) {
            bArr = DiskCacheHelper.newByteArrayWithTime(i, bArr);
        }
        File fileBeforePut = diskCacheManager.getFileBeforePut(str);
        writeFileFromBytes(fileBeforePut, bArr);
        diskCacheManager.updateModify(fileBeforePut);
        diskCacheManager.put(fileBeforePut);
    }

    public byte[] getBytes(String str) {
        return getBytes(str, null);
    }

    public byte[] getBytes(String str, byte[] bArr) {
        return realGetBytes(TYPE_BYTE + str, bArr);
    }

    private byte[] realGetBytes(String str) {
        return realGetBytes(str, null);
    }

    private byte[] realGetBytes(String str, byte[] bArr) {
        File fileIfExists;
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null || (fileIfExists = diskCacheManager.getFileIfExists(str)) == null) {
            return bArr;
        }
        byte[] readFile2Bytes = readFile2Bytes(fileIfExists);
        if (DiskCacheHelper.isDue(readFile2Bytes)) {
            diskCacheManager.removeByKey(str);
            return bArr;
        }
        diskCacheManager.updateModify(fileIfExists);
        return DiskCacheHelper.getDataWithoutDueTime(readFile2Bytes);
    }

    public void put(String str, String str2) {
        put(str, str2, -1);
    }

    public void put(String str, String str2, int i) {
        realPutBytes(TYPE_STRING + str, string2Bytes(str2), i);
    }

    public String getString(String str) {
        return getString(str, null);
    }

    public String getString(String str, String str2) {
        byte[] realGetBytes = realGetBytes(TYPE_STRING + str);
        return realGetBytes == null ? str2 : bytes2String(realGetBytes);
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject, -1);
    }

    public void put(String str, JSONObject jSONObject, int i) {
        realPutBytes(TYPE_JSON_OBJECT + str, jsonObject2Bytes(jSONObject), i);
    }

    public JSONObject getJSONObject(String str) {
        return getJSONObject(str, null);
    }

    public JSONObject getJSONObject(String str, JSONObject jSONObject) {
        byte[] realGetBytes = realGetBytes(TYPE_JSON_OBJECT + str);
        return realGetBytes == null ? jSONObject : bytes2JSONObject(realGetBytes);
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray, -1);
    }

    public void put(String str, JSONArray jSONArray, int i) {
        realPutBytes(TYPE_JSON_ARRAY + str, jsonArray2Bytes(jSONArray), i);
    }

    public JSONArray getJSONArray(String str) {
        return getJSONArray(str, null);
    }

    public JSONArray getJSONArray(String str, JSONArray jSONArray) {
        byte[] realGetBytes = realGetBytes(TYPE_JSON_ARRAY + str);
        return realGetBytes == null ? jSONArray : bytes2JSONArray(realGetBytes);
    }

    public void put(String str, Bitmap bitmap) {
        put(str, bitmap, -1);
    }

    public void put(String str, Bitmap bitmap, int i) {
        realPutBytes(TYPE_BITMAP + str, bitmap2Bytes(bitmap), i);
    }

    public Bitmap getBitmap(String str) {
        return getBitmap(str, null);
    }

    public Bitmap getBitmap(String str, Bitmap bitmap) {
        byte[] realGetBytes = realGetBytes(TYPE_BITMAP + str);
        return realGetBytes == null ? bitmap : bytes2Bitmap(realGetBytes);
    }

    public void put(String str, Drawable drawable) {
        put(str, drawable, -1);
    }

    public void put(String str, Drawable drawable, int i) {
        realPutBytes(TYPE_DRAWABLE + str, drawable2Bytes(drawable), i);
    }

    public Drawable getDrawable(String str) {
        return getDrawable(str, null);
    }

    public Drawable getDrawable(String str, Drawable drawable) {
        byte[] realGetBytes = realGetBytes(TYPE_DRAWABLE + str);
        return realGetBytes == null ? drawable : bytes2Drawable(realGetBytes);
    }

    public void put(String str, Parcelable parcelable) {
        put(str, parcelable, -1);
    }

    public void put(String str, Parcelable parcelable, int i) {
        realPutBytes(TYPE_PARCELABLE + str, parcelable2Bytes(parcelable), i);
    }

    public <T> T getParcelable(String str, Parcelable.Creator<T> creator) {
        return (T) getParcelable(str, creator, null);
    }

    public <T> T getParcelable(String str, Parcelable.Creator<T> creator, T t) {
        byte[] realGetBytes = realGetBytes(TYPE_PARCELABLE + str);
        return realGetBytes == null ? t : (T) bytes2Parcelable(realGetBytes, creator);
    }

    public void put(String str, Serializable serializable) {
        put(str, serializable, -1);
    }

    public void put(String str, Serializable serializable, int i) {
        realPutBytes(TYPE_SERIALIZABLE + str, serializable2Bytes(serializable), i);
    }

    public Object getSerializable(String str) {
        return getSerializable(str, null);
    }

    public Object getSerializable(String str, Object obj) {
        byte[] realGetBytes = realGetBytes(TYPE_SERIALIZABLE + str);
        return realGetBytes == null ? obj : bytes2Object(realGetBytes);
    }

    public long getCacheSize() {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return 0L;
        }
        return diskCacheManager.getCacheSize();
    }

    public int getCacheCount() {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return 0;
        }
        return diskCacheManager.getCacheCount();
    }

    public boolean remove(String str) {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return true;
        }
        if (diskCacheManager.removeByKey(TYPE_BYTE + str)) {
            if (diskCacheManager.removeByKey(TYPE_STRING + str)) {
                if (diskCacheManager.removeByKey(TYPE_JSON_OBJECT + str)) {
                    if (diskCacheManager.removeByKey(TYPE_JSON_ARRAY + str)) {
                        if (diskCacheManager.removeByKey(TYPE_BITMAP + str)) {
                            if (diskCacheManager.removeByKey(TYPE_DRAWABLE + str)) {
                                if (diskCacheManager.removeByKey(TYPE_PARCELABLE + str)) {
                                    if (diskCacheManager.removeByKey(TYPE_SERIALIZABLE + str)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean clear() {
        DiskCacheManager diskCacheManager = getDiskCacheManager();
        if (diskCacheManager == null) {
            return true;
        }
        return diskCacheManager.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class DiskCacheManager {
        private final AtomicInteger cacheCount;
        private final File cacheDir;
        private final AtomicLong cacheSize;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates;
        private final Thread mThread;
        private final long sizeLimit;

        private DiskCacheManager(final File file, long j, int i) {
            this.lastUsageDates = Collections.synchronizedMap(new HashMap());
            this.cacheDir = file;
            this.sizeLimit = j;
            this.countLimit = i;
            this.cacheSize = new AtomicLong();
            this.cacheCount = new AtomicInteger();
            Thread thread = new Thread(new Runnable() { // from class: com.movieboxpro.android.utils.CacheDiskUtils.DiskCacheManager.1
                @Override // java.lang.Runnable
                public void run() {
                    File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.movieboxpro.android.utils.CacheDiskUtils.DiskCacheManager.1.1
                        @Override // java.io.FilenameFilter
                        public boolean accept(File file2, String str) {
                            return str.startsWith(CacheDiskUtils.CACHE_PREFIX);
                        }
                    });
                    if (listFiles != null) {
                        int i2 = 0;
                        int i3 = 0;
                        for (File file2 : listFiles) {
                            i2 = (int) (i2 + file2.length());
                            i3++;
                            DiskCacheManager.this.lastUsageDates.put(file2, Long.valueOf(file2.lastModified()));
                        }
                        DiskCacheManager.this.cacheSize.getAndAdd(i2);
                        DiskCacheManager.this.cacheCount.getAndAdd(i3);
                    }
                }
            });
            this.mThread = thread;
            thread.start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public long getCacheSize() {
            wait2InitOk();
            return this.cacheSize.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getCacheCount() {
            wait2InitOk();
            return this.cacheCount.get();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File getFileBeforePut(String str) {
            wait2InitOk();
            File file = new File(this.cacheDir, getCacheNameByKey(str));
            if (file.exists()) {
                this.cacheCount.addAndGet(-1);
                this.cacheSize.addAndGet(-file.length());
            }
            return file;
        }

        private void wait2InitOk() {
            try {
                this.mThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public File getFileIfExists(String str) {
            File file = new File(this.cacheDir, getCacheNameByKey(str));
            if (file.exists()) {
                return file;
            }
            return null;
        }

        private String getCacheNameByKey(String str) {
            return CacheDiskUtils.CACHE_PREFIX + str.substring(0, 3) + str.substring(3).hashCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void put(File file) {
            this.cacheCount.addAndGet(1);
            this.cacheSize.addAndGet(file.length());
            while (true) {
                if (this.cacheCount.get() <= this.countLimit && this.cacheSize.get() <= this.sizeLimit) {
                    return;
                }
                this.cacheSize.addAndGet(-removeOldest());
                this.cacheCount.addAndGet(-1);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateModify(File file) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(file, valueOf);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean removeByKey(String str) {
            File fileIfExists = getFileIfExists(str);
            if (fileIfExists == null) {
                return true;
            }
            if (fileIfExists.delete()) {
                this.cacheSize.addAndGet(-fileIfExists.length());
                this.cacheCount.addAndGet(-1);
                this.lastUsageDates.remove(fileIfExists);
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean clear() {
            File[] listFiles = this.cacheDir.listFiles(new FilenameFilter() { // from class: com.movieboxpro.android.utils.CacheDiskUtils.DiskCacheManager.2
                @Override // java.io.FilenameFilter
                public boolean accept(File file, String str) {
                    return str.startsWith(CacheDiskUtils.CACHE_PREFIX);
                }
            });
            boolean z = true;
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.delete()) {
                        this.cacheSize.addAndGet(-file.length());
                        this.cacheCount.addAndGet(-1);
                        this.lastUsageDates.remove(file);
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    this.lastUsageDates.clear();
                    this.cacheSize.set(0L);
                    this.cacheCount.set(0);
                }
            }
            return z;
        }

        private long removeOldest() {
            if (this.lastUsageDates.isEmpty()) {
                return 0L;
            }
            Long l = Long.MAX_VALUE;
            File file = null;
            Set<Map.Entry<File, Long>> entrySet = this.lastUsageDates.entrySet();
            synchronized (this.lastUsageDates) {
                for (Map.Entry<File, Long> entry : entrySet) {
                    Long value = entry.getValue();
                    if (value.longValue() < l.longValue()) {
                        file = entry.getKey();
                        l = value;
                    }
                }
            }
            if (file == null) {
                return 0L;
            }
            long length = file.length();
            if (file.delete()) {
                this.lastUsageDates.remove(file);
                return length;
            }
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class DiskCacheHelper {
        static final int TIME_INFO_LEN = 14;

        private DiskCacheHelper() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] newByteArrayWithTime(int i, byte[] bArr) {
            byte[] bytes = createDueTime(i).getBytes();
            byte[] bArr2 = new byte[bytes.length + bArr.length];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        private static String createDueTime(int i) {
            return String.format(Locale.getDefault(), "_$%010d$_", Long.valueOf((System.currentTimeMillis() / 1000) + i));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean isDue(byte[] bArr) {
            long dueTime = getDueTime(bArr);
            return dueTime != -1 && System.currentTimeMillis() > dueTime;
        }

        private static long getDueTime(byte[] bArr) {
            if (hasTimeInfo(bArr)) {
                try {
                    return Long.parseLong(new String(copyOfRange(bArr, 2, 12))) * 1000;
                } catch (NumberFormatException unused) {
                }
            }
            return -1L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static byte[] getDataWithoutDueTime(byte[] bArr) {
            return hasTimeInfo(bArr) ? copyOfRange(bArr, 14, bArr.length) : bArr;
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

        private static boolean hasTimeInfo(byte[] bArr) {
            return bArr != null && bArr.length >= 14 && bArr[0] == 95 && bArr[1] == 36 && bArr[12] == 36 && bArr[13] == 95;
        }
    }

    private static byte[] string2Bytes(String str) {
        if (str == null) {
            return null;
        }
        return str.getBytes();
    }

    private static String bytes2String(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new String(bArr);
    }

    private static byte[] jsonObject2Bytes(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return jSONObject.toString().getBytes();
    }

    private static JSONObject bytes2JSONObject(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONObject(new String(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] jsonArray2Bytes(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        return jSONArray.toString().getBytes();
    }

    private static JSONArray bytes2JSONArray(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new JSONArray(new String(bArr));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] parcelable2Bytes(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }

    private static <T> T bytes2Parcelable(byte[] bArr, Parcelable.Creator<T> creator) {
        if (bArr == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        T createFromParcel = creator.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    /* JADX WARN: Not initialized variable reg: 1, insn: 0x0033: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:26:0x0033 */
    private static byte[] serializable2Bytes(Serializable serializable) {
        ObjectOutputStream objectOutputStream;
        ObjectOutputStream objectOutputStream2;
        ObjectOutputStream objectOutputStream3 = null;
        try {
            if (serializable == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream2.writeObject(serializable);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        objectOutputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return byteArray;
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    if (objectOutputStream2 != null) {
                        try {
                            objectOutputStream2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (Exception e4) {
                e = e4;
                objectOutputStream2 = null;
            } catch (Throwable th) {
                th = th;
                if (objectOutputStream3 != null) {
                    try {
                        objectOutputStream3.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream3 = objectOutputStream;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0033 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.Object bytes2Object(byte[] r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L1d java.lang.Exception -> L1f
            java.lang.Object r3 = r1.readObject()     // Catch: java.lang.Exception -> L1b java.lang.Throwable -> L2f
            r1.close()     // Catch: java.io.IOException -> L16
            goto L1a
        L16:
            r0 = move-exception
            r0.printStackTrace()
        L1a:
            return r3
        L1b:
            r3 = move-exception
            goto L21
        L1d:
            r3 = move-exception
            goto L31
        L1f:
            r3 = move-exception
            r1 = r0
        L21:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L2f
            if (r1 == 0) goto L2e
            r1.close()     // Catch: java.io.IOException -> L2a
            goto L2e
        L2a:
            r3 = move-exception
            r3.printStackTrace()
        L2e:
            return r0
        L2f:
            r3 = move-exception
            r0 = r1
        L31:
            if (r0 == 0) goto L3b
            r0.close()     // Catch: java.io.IOException -> L37
            goto L3b
        L37:
            r0 = move-exception
            r0.printStackTrace()
        L3b:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.CacheDiskUtils.bytes2Object(byte[]):java.lang.Object");
    }

    private static byte[] bitmap2Bytes(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static Bitmap bytes2Bitmap(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    private static byte[] drawable2Bytes(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return bitmap2Bytes(drawable2Bitmap(drawable));
    }

    private static Drawable bytes2Drawable(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return bitmap2Drawable(bytes2Bitmap(bArr));
    }

    private static Bitmap drawable2Bitmap(Drawable drawable) {
        Bitmap.Config config;
        Bitmap createBitmap;
        Bitmap.Config config2;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            if (drawable.getOpacity() != -1) {
                config = Bitmap.Config.ARGB_8888;
            } else {
                config = Bitmap.Config.RGB_565;
            }
            createBitmap = Bitmap.createBitmap(1, 1, config);
        } else {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (drawable.getOpacity() != -1) {
                config2 = Bitmap.Config.ARGB_8888;
            } else {
                config2 = Bitmap.Config.RGB_565;
            }
            createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, config2);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    private static Drawable bitmap2Drawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(Utils.getApp().getResources(), bitmap);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0029 -> B:23:0x002c). Please submit an issue!!! */
    private static void writeFileFromBytes(File file, byte[] bArr) {
        FileChannel fileChannel = null;
        try {
            try {
                try {
                    fileChannel = new FileOutputStream(file, false).getChannel();
                    fileChannel.write(ByteBuffer.wrap(bArr));
                    fileChannel.force(true);
                    if (fileChannel != null) {
                        fileChannel.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                if (fileChannel == null) {
                    return;
                }
                fileChannel.close();
            }
        } catch (Throwable th) {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x004a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] readFile2Bytes(java.io.File r10) {
        /*
            r0 = 0
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L37
            java.lang.String r2 = "r"
            r1.<init>(r10, r2)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L37
            java.nio.channels.FileChannel r10 = r1.getChannel()     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L37
            long r1 = r10.size()     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            int r2 = (int) r1     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            r5 = 0
            long r7 = (long) r2     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            r3 = r10
            java.nio.MappedByteBuffer r1 = r3.map(r4, r5, r7)     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            java.nio.MappedByteBuffer r1 = r1.load()     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            byte[] r3 = new byte[r2]     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            r4 = 0
            r1.get(r3, r4, r2)     // Catch: java.io.IOException -> L30 java.lang.Throwable -> L47
            if (r10 == 0) goto L2f
            r10.close()     // Catch: java.io.IOException -> L2b
            goto L2f
        L2b:
            r10 = move-exception
            r10.printStackTrace()
        L2f:
            return r3
        L30:
            r1 = move-exception
            goto L39
        L32:
            r10 = move-exception
            r9 = r0
            r0 = r10
            r10 = r9
            goto L48
        L37:
            r1 = move-exception
            r10 = r0
        L39:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L47
            if (r10 == 0) goto L46
            r10.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r10 = move-exception
            r10.printStackTrace()
        L46:
            return r0
        L47:
            r0 = move-exception
        L48:
            if (r10 == 0) goto L52
            r10.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r10 = move-exception
            r10.printStackTrace()
        L52:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.CacheDiskUtils.readFile2Bytes(java.io.File):byte[]");
    }

    private static boolean isSpace(String str) {
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
}
