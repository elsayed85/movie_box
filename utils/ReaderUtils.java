package com.movieboxpro.android.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v4.media.session.PlaybackStateCompat;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class ReaderUtils {
    private static String getChapterPath(String str, String str2, int i) {
        return Constant.DIR_NOVEL + File.separator + str + File.separator + str2 + File.separator + i + ".dat";
    }

    public static File getChapterFile(String str) {
        File file = new File(str);
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static File createWifiTempFile() {
        File file = new File(Constant.DIR_CACHE + File.separator + System.currentTimeMillis());
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static File createWifiTranfesFile(String str) {
        File file = new File(Constant.DIR_CACHE + File.separator + str + File.separator + "1.txt");
        if (!file.exists()) {
            createFile(file);
        }
        return file;
    }

    public static String getPathOPF(String str) {
        String str2 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str + "/META-INF/container.xml"), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (readLine.contains("full-path")) {
                    int indexOf = readLine.indexOf(34, readLine.indexOf("full-path"));
                    int i = indexOf + 1;
                    int indexOf2 = readLine.indexOf(34, i);
                    if (indexOf > -1 && indexOf2 > indexOf) {
                        str2 = readLine.substring(i, indexOf2).trim();
                        break;
                    }
                }
            }
            bufferedReader.close();
            if (str2.contains("/")) {
                int lastIndexOf = str2.lastIndexOf(47);
                return lastIndexOf > -1 ? str2.substring(0, lastIndexOf) : str2;
            }
            return null;
        } catch (IOException | NullPointerException unused) {
            return "";
        }
    }

    public static boolean checkOPFInRootDirectory(String str) {
        String str2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str + "/META-INF/container.xml"), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    str2 = "";
                    break;
                } else if (readLine.contains("full-path")) {
                    int indexOf = readLine.indexOf(34, readLine.indexOf("full-path"));
                    int i = indexOf + 1;
                    int indexOf2 = readLine.indexOf(34, i);
                    if (indexOf > -1 && indexOf2 > indexOf) {
                        str2 = readLine.substring(i, indexOf2).trim();
                        break;
                    }
                }
            }
            bufferedReader.close();
            return !str2.contains("/");
        } catch (IOException | NullPointerException unused) {
            return false;
        }
    }

    public static void unzipFile(String str, String str2) throws IOException {
        ArrayList<String> arrayList = new ArrayList();
        File file = new File(str);
        File file2 = new File(str2);
        createDir(file2.getAbsolutePath());
        ZipFile zipFile = new ZipFile(file, 1);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            File file3 = new File(file2, name);
            if (name.endsWith(Constant.SUFFIX_ZIP)) {
                arrayList.add(file3.getAbsolutePath());
            }
            createDir(file3.getParentFile().getAbsolutePath());
            if (!nextElement.isDirectory() && !file3.exists()) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(nextElement));
                byte[] bArr = new byte[2048];
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file3), 2048);
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, 2048);
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, read);
                }
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
            }
        }
        zipFile.close();
        for (String str3 : arrayList) {
            unzipFile(str3, str2 + File.separatorChar + str3.substring(0, str3.lastIndexOf(Constant.SUFFIX_ZIP)));
        }
    }

    public static byte[] readAssets(String str) {
        byte[] bArr = null;
        if (str != null && str.length() > 0) {
            try {
                try {
                    AssetManager assets = App.getContext().getAssets();
                    InputStream open = assets.open("uploader" + str);
                    bArr = new byte[open.available()];
                    open.read(bArr);
                    open.close();
                    return bArr;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Throwable unused) {
            }
        }
        return bArr;
    }

    public static String createRootPath(Context context) {
        if (isSdCardAvailable()) {
            return context.getExternalCacheDir().getPath();
        }
        return context.getCacheDir().getPath();
    }

    public static boolean isSdCardAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static String createDir(String str) {
        File file;
        try {
            file = new File(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file.getParentFile().exists()) {
            file.mkdir();
            return file.getAbsolutePath();
        }
        createDir(file.getParentFile().getAbsolutePath());
        file.mkdir();
        return str;
    }

    public static String createFile(File file) {
        try {
            if (file.getParentFile().exists()) {
                file.createNewFile();
                return file.getAbsolutePath();
            }
            createDir(file.getParentFile().getAbsolutePath());
            file.createNewFile();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void writeFile(String str, String str2, boolean z) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str, z);
            fileOutputStream.write(str2.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String str, String str2) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(str));
            outputStreamWriter.write(str2);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileFromRaw(Context context, int i) {
        if (context == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(i)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] getBytesFromFile(File file) {
        if (file == null) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
            byte[] bArr = new byte[1000];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static void fileChannelCopy(File file, File file2) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        createFile(file2);
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
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
                e = e4;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public static String formatFileSizeToString(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
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

    public static boolean deleteFile(File file) {
        return deleteFileOrDirectory(file);
    }

    private static boolean deleteFileOrDirectory(File file) {
        if (file != null && file.isFile()) {
            return file.delete();
        }
        if (file == null || !file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return file.delete();
        }
        for (File file2 : listFiles) {
            deleteFileOrDirectory(file2);
        }
        return file.delete();
    }

    public static long getFileSize(String str) throws Exception {
        long length;
        long j = 0;
        try {
            File[] listFiles = new File(str).listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    length = getFileSize(listFiles[i].getAbsolutePath());
                } else {
                    length = listFiles[i].length();
                }
                j += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static String getExtensionName(String str) {
        int lastIndexOf;
        return (str == null || str.length() <= 0 || (lastIndexOf = str.lastIndexOf(46)) <= -1 || lastIndexOf >= str.length() + (-1)) ? str : str.substring(lastIndexOf + 1);
    }

    public static String getFileOutputString(String str, String str2) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str)), str2), 8192);
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

    private synchronized void getAllFiles(File file, String str) {
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    getAllFiles(file2, str);
                } else if (file2.getName().endsWith(str) && file2.length() > 50) {
                    arrayList.add(file2);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0086, code lost:
        r8 = r4.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x008a, code lost:
        if (128 > r8) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x008c, code lost:
        if (r8 > 191) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x008e, code lost:
        r8 = r4.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0092, code lost:
        if (128 > r8) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0094, code lost:
        if (r8 > 191) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCharset(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 198
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.ReaderUtils.getCharset(java.lang.String):java.lang.String");
    }

    public static String getCharset1(String str) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(str));
        int read = (bufferedInputStream.read() << 8) + bufferedInputStream.read();
        return read != 61371 ? read != 65279 ? read != 65534 ? "GBK" : "Unicode" : "UTF-16BE" : "UTF-8";
    }

    public static void saveWifiTxt(String str, String str2) {
        byte[] bytes = ShellUtil.COMMAND_LINE_END.getBytes();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(str), getCharset(str)));
            FileOutputStream fileOutputStream = new FileOutputStream(str2, true);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    fileOutputStream.write(readLine.getBytes());
                    fileOutputStream.write(bytes);
                } else {
                    bufferedReader.close();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
