package com.movieboxpro.android.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import com.google.common.base.Ascii;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.listener.WriteFileToUriListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class FileUtils {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* loaded from: classes3.dex */
    public interface OnReplaceListener {
        boolean onReplace();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$copyFile$0() {
        return true;
    }

    public static boolean viewToFile(View view, File file) {
        return writeFileFromBytesByStream(file, bitmap2Bytes(view2Bitmap(view), Bitmap.CompressFormat.PNG));
    }

    public static boolean bitmapToFile(Bitmap bitmap, File file) {
        return writeFileFromBytesByStream(file, bitmap2Bytes(bitmap, Bitmap.CompressFormat.PNG));
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr) {
        return writeFileFromIS(file, new ByteArrayInputStream(bArr));
    }

    public static Bitmap view2Bitmap(View view) {
        if (view == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static long getFileSize(File file) {
        long j;
        long length;
        int i = 0;
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                int length2 = listFiles.length;
                int i2 = 0;
                while (i < length2) {
                    File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        j = i2;
                        length = getFileSize(file2);
                    } else {
                        j = i2;
                        length = file2.length();
                    }
                    i2 = (int) (j + length);
                    i++;
                }
                i = i2;
            } else {
                i = (int) (0 + file.length());
            }
        }
        return i;
    }

    public static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file2 : listFiles) {
                    if (file2.isDirectory()) {
                        deleteFile(file2);
                    } else {
                        file2.delete();
                    }
                }
                return;
            }
            file.delete();
            return;
        }
        file.delete();
    }

    public static void write(File file, String str, boolean z) {
        FileWriter fileWriter;
        if (file != null) {
            FileWriter fileWriter2 = null;
            try {
                try {
                    try {
                        fileWriter = new FileWriter(file, z);
                    } catch (IOException e) {
                        e = e;
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    fileWriter.write(str);
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e2) {
                    e = e2;
                    fileWriter2 = fileWriter;
                    e.printStackTrace();
                    if (fileWriter2 != null) {
                        fileWriter2.flush();
                        fileWriter2.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileWriter2 = fileWriter;
                    if (fileWriter2 != null) {
                        try {
                            fileWriter2.flush();
                            fileWriter2.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (file2.isFile()) {
                            if (!file2.delete()) {
                                return false;
                            }
                        } else if (file2.isDirectory() && !deleteDir(file2)) {
                            return false;
                        }
                    }
                }
                return file.delete();
            }
            return false;
        }
        return true;
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return !file.exists() || (file.isFile() && file.delete());
    }

    public static boolean deleteAllInDir(File file) {
        return deleteFilesInDirWithFilter(file, new FileFilter() { // from class: com.movieboxpro.android.utils.FileUtils.1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(File file, FileFilter fileFilter) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null && listFiles.length != 0) {
                    for (File file2 : listFiles) {
                        if (fileFilter.accept(file2)) {
                            if (file2.isFile()) {
                                if (!file2.delete()) {
                                    return false;
                                }
                            } else if (file2.isDirectory() && !deleteDir(file2)) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
            return false;
        }
        return true;
    }

    public static String inputStream2String(InputStream inputStream, String str) {
        if (inputStream != null && !isSpace(str)) {
            try {
                ByteArrayOutputStream input2OutputStream = CommonUtils.input2OutputStream(inputStream);
                return input2OutputStream == null ? "" : input2OutputStream.toString(str);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
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

    public static String getCharset(String str) throws FileNotFoundException {
        return getCharset(new FileInputStream(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x0081, code lost:
        r8 = r4.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0085, code lost:
        if (128 > r8) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0087, code lost:
        if (r8 > 191) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0089, code lost:
        r8 = r4.read();
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x008d, code lost:
        if (128 > r8) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x008f, code lost:
        if (r8 > 191) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCharset(java.io.InputStream r8) {
        /*
            Method dump skipped, instructions count: 193
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.FileUtils.getCharset(java.io.InputStream):java.lang.String");
    }

    public static String getExtensionName(String str) {
        int lastIndexOf;
        if (str.length() > 0 && (lastIndexOf = str.lastIndexOf(46)) > -1 && lastIndexOf < str.length() - 1) {
            return str.substring(lastIndexOf + 1);
        }
        return str.toLowerCase();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x009c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.movieboxpro.android.utils.unrar.Archive] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.movieboxpro.android.utils.unrar.Archive] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void unrar(java.lang.String r6, java.lang.String r7) throws java.lang.Exception {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r6)
            java.io.File r6 = new java.io.File
            r6.<init>(r7)
            r7 = 0
            com.movieboxpro.android.utils.unrar.Archive r1 = new com.movieboxpro.android.utils.unrar.Archive     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L94
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L94
            com.movieboxpro.android.utils.unrar.rarfile.FileHeader r0 = r1.nextFileHeader()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
        L14:
            if (r0 == 0) goto L87
            java.lang.String r2 = r0.getFileNameW()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            if (r2 != 0) goto L29
            java.lang.String r2 = r0.getFileNameW()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            goto L31
        L29:
            java.lang.String r2 = r0.getFileNameString()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            java.lang.String r2 = r2.trim()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
        L31:
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r4.<init>()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            java.lang.String r5 = r6.getAbsolutePath()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r4.append(r5)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            java.lang.String r5 = "/"
            r4.append(r5)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r4.append(r2)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            boolean r2 = r0.isDirectory()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            if (r2 == 0) goto L62
            boolean r0 = r3.exists()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            if (r0 != 0) goto L5d
            r3.mkdirs()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
        L5d:
            com.movieboxpro.android.utils.unrar.rarfile.FileHeader r0 = r1.nextFileHeader()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            goto L14
        L62:
            java.io.File r2 = r3.getParentFile()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            boolean r2 = r2.exists()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            if (r2 != 0) goto L73
            java.io.File r2 = r3.getParentFile()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r2.mkdirs()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
        L73:
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            r1.extractFile(r0, r2)     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            r2.close()     // Catch: java.lang.Throwable -> L83 java.lang.Exception -> L85
            com.movieboxpro.android.utils.unrar.rarfile.FileHeader r0 = r1.nextFileHeader()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            goto L14
        L83:
            r6 = move-exception
            goto L99
        L85:
            r6 = move-exception
            goto L8f
        L87:
            r1.close()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8d
            return
        L8b:
            r6 = move-exception
            goto L9a
        L8d:
            r6 = move-exception
            r2 = r7
        L8f:
            r7 = r1
            goto L96
        L91:
            r6 = move-exception
            r1 = r7
            goto L9a
        L94:
            r6 = move-exception
            r2 = r7
        L96:
            throw r6     // Catch: java.lang.Throwable -> L97
        L97:
            r6 = move-exception
            r1 = r7
        L99:
            r7 = r2
        L9a:
            if (r7 == 0) goto La1
            r7.close()     // Catch: java.lang.Exception -> La0
            goto La1
        La0:
        La1:
            if (r1 == 0) goto La6
            r1.close()     // Catch: java.lang.Exception -> La6
        La6:
            goto La8
        La7:
            throw r6
        La8:
            goto La7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.FileUtils.unrar(java.lang.String, java.lang.String):void");
    }

    public static boolean copyFile(File file, File file2) {
        return copyOrMoveFile(file, file2, $$Lambda$FileUtils$0mS0X3AT2TpOw9SXEeOnWzDEZn0.INSTANCE, false);
    }

    public static boolean move(File file, File file2, OnReplaceListener onReplaceListener) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return moveDir(file, file2, onReplaceListener);
        }
        return moveFile(file, file2, onReplaceListener);
    }

    public static boolean moveDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, true);
    }

    public static boolean moveFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, true);
    }

    private static boolean copyOrMoveDir(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        File[] listFiles;
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (!str.contains(file.getPath() + File.separator) && file.exists() && file.isDirectory() && createOrExistsDir(file2)) {
            for (File file3 : file.listFiles()) {
                File file4 = new File(str + file3.getName());
                if (file3.isFile()) {
                    if (!copyOrMoveFile(file3, file4, onReplaceListener, z)) {
                        return false;
                    }
                } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, onReplaceListener, z)) {
                    return false;
                }
            }
            return !z || deleteDir(file);
        }
        return false;
    }

    public static boolean copyOrMoveFile(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file != null && file2 != null && !file.equals(file2) && file.exists() && file.isFile()) {
            if (file2.exists()) {
                if (onReplaceListener != null && !onReplaceListener.onReplace()) {
                    return true;
                }
                if (!file2.delete()) {
                    return false;
                }
            }
            if (!createOrExistsDir(file2.getParentFile())) {
                return false;
            }
            try {
                if (writeFileFromIS(file2, new FileInputStream(file))) {
                    if (z) {
                        if (!file.exists()) {
                            return false;
                        }
                        if (file.isFile()) {
                            if (file.delete()) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                return false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream) {
        BufferedOutputStream bufferedOutputStream;
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
                int read = inputStream.read(bArr, 0, 8192);
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

    public static boolean writeFileFromString(File file, String str, boolean z) {
        BufferedWriter bufferedWriter;
        if (file == null || str == null || !createOrExistsFile(file)) {
            return false;
        }
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new FileWriter(file, z));
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            bufferedWriter.write(str);
            try {
                bufferedWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return true;
        } catch (IOException e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            e.printStackTrace();
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (createOrExistsDir(file.getParentFile())) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    public static boolean isFileExists(String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        return file.exists() && file.isFile();
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static void forceMkdir(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return;
            }
            throw new IOException("File " + file + " exists and is not a directory. Unable to create directory.");
        } else if (file.mkdirs() || file.isDirectory()) {
        } else {
            throw new IOException("Unable to create directory " + file);
        }
    }

    public static String fileExt(String str) {
        int lastIndexOf = str.lastIndexOf(com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX);
        if (lastIndexOf == -1) {
            return null;
        }
        return str.substring(lastIndexOf + 1).toLowerCase();
    }

    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static List<File> listFilesInDir(File file) {
        return listFilesInDirWithFilter(file, new FileFilter() { // from class: com.movieboxpro.android.utils.FileUtils.2
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                return true;
            }
        }, false);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, boolean z) {
        if (isDir(file)) {
            ArrayList arrayList = new ArrayList();
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File file2 : listFiles) {
                    if (fileFilter.accept(file2)) {
                        arrayList.add(file2);
                    }
                    if (z && file2.isDirectory()) {
                        arrayList.addAll(listFilesInDirWithFilter(file2, fileFilter, true));
                    }
                }
            }
            return arrayList;
        }
        return null;
    }

    public static String getAvailMemory(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return Formatter.formatFileSize(context, memoryInfo.availMem);
    }

    public static String getRemoteFileName(String str) {
        int lastIndexOf = str.lastIndexOf("/");
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            return str.substring(lastIndexOf, indexOf);
        }
        return str.substring(lastIndexOf, str.length());
    }

    public static void create(File file, long j) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            try {
                randomAccessFile2.setLength(j);
                try {
                    randomAccessFile2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getRemoteFileName2(String str) {
        int lastIndexOf = str.lastIndexOf("/") + 1;
        int indexOf = str.indexOf("?");
        if (indexOf >= 0) {
            return str.substring(lastIndexOf, indexOf);
        }
        return str.substring(lastIndexOf, str.length());
    }

    public static String getFileMD5ToString(File file) {
        return bytes2HexString(getFileMD5(file));
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0048: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:34:0x0048 */
    /* JADX WARN: Removed duplicated region for block: B:46:0x003e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] getFileMD5(java.io.File r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L4
            return r0
        L4:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.security.NoSuchAlgorithmException -> L37
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.security.NoSuchAlgorithmException -> L37
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.security.NoSuchAlgorithmException -> L37
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.security.NoSuchAlgorithmException -> L37
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L35 java.security.NoSuchAlgorithmException -> L37
            r3 = 262144(0x40000, float:3.67342E-40)
            byte[] r3 = new byte[r3]     // Catch: java.io.IOException -> L2f java.security.NoSuchAlgorithmException -> L31 java.lang.Throwable -> L47
        L18:
            int r1 = r2.read(r3)     // Catch: java.io.IOException -> L2f java.security.NoSuchAlgorithmException -> L31 java.lang.Throwable -> L47
            if (r1 > 0) goto L18
            java.security.MessageDigest r3 = r2.getMessageDigest()     // Catch: java.io.IOException -> L2f java.security.NoSuchAlgorithmException -> L31 java.lang.Throwable -> L47
            byte[] r3 = r3.digest()     // Catch: java.io.IOException -> L2f java.security.NoSuchAlgorithmException -> L31 java.lang.Throwable -> L47
            r2.close()     // Catch: java.io.IOException -> L2a
            goto L2e
        L2a:
            r0 = move-exception
            r0.printStackTrace()
        L2e:
            return r3
        L2f:
            r3 = move-exception
            goto L39
        L31:
            r3 = move-exception
            goto L39
        L33:
            r3 = move-exception
            goto L49
        L35:
            r3 = move-exception
            goto L38
        L37:
            r3 = move-exception
        L38:
            r2 = r0
        L39:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L47
            if (r2 == 0) goto L46
            r2.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r3 = move-exception
            r3.printStackTrace()
        L46:
            return r0
        L47:
            r3 = move-exception
            r0 = r2
        L49:
            if (r0 == 0) goto L53
            r0.close()     // Catch: java.io.IOException -> L4f
            goto L53
        L4f:
            r0 = move-exception
            r0.printStackTrace()
        L53:
            goto L55
        L54:
            throw r3
        L55:
            goto L54
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.FileUtils.getFileMD5(java.io.File):byte[]");
    }

    public static long getFsTotalSize(String str) {
        long blockSize;
        long blockCount;
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(str);
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                blockCount = statFs.getBlockCountLong();
            } else {
                blockSize = statFs.getBlockSize();
                blockCount = statFs.getBlockCount();
            }
            return blockSize * blockCount;
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static long getFsAvailableSize(String str) {
        long blockSize;
        long availableBlocks;
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(str);
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                availableBlocks = statFs.getAvailableBlocksLong();
            } else {
                blockSize = statFs.getBlockSize();
                availableBlocks = statFs.getAvailableBlocks();
            }
            return blockSize * availableBlocks;
        } catch (Exception unused) {
            return 0L;
        }
    }

    private static String bytes2HexString(byte[] bArr) {
        int length;
        if (bArr != null && (length = bArr.length) > 0) {
            char[] cArr = new char[length << 1];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i + 1;
                char[] cArr2 = HEX_DIGITS;
                cArr[i] = cArr2[(bArr[i2] >> 4) & 15];
                i = i3 + 1;
                cArr[i3] = cArr2[bArr[i2] & Ascii.SI];
            }
            return new String(cArr);
        }
        return "";
    }

    public static String byte2FitMemorySize(long j) {
        if (j < 0) {
            return "shouldn't be less than zero!";
        }
        if (j < 1024) {
            return String.format(Locale.getDefault(), "%.2fB", Double.valueOf(j));
        }
        if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            Locale locale = Locale.getDefault();
            double d = j;
            Double.isNaN(d);
            return String.format(locale, "%.2fKB", Double.valueOf(d / 1024.0d));
        } else if (j < IjkMediaMeta.AV_CH_STEREO_RIGHT) {
            Locale locale2 = Locale.getDefault();
            double d2 = j;
            Double.isNaN(d2);
            return String.format(locale2, "%.2fMB", Double.valueOf(d2 / 1048576.0d));
        } else {
            Locale locale3 = Locale.getDefault();
            double d3 = j;
            Double.isNaN(d3);
            return String.format(locale3, "%.2fGB", Double.valueOf(d3 / 1.073741824E9d));
        }
    }

    public static String readFile2String(File file) {
        byte[] readFile2BytesByStream = readFile2BytesByStream(file);
        if (readFile2BytesByStream == null) {
            return null;
        }
        return new String(readFile2BytesByStream);
    }

    public static String readFile2String(File file, String str) throws UnsupportedEncodingException {
        byte[] readFile2BytesByStream = readFile2BytesByStream(file);
        if (readFile2BytesByStream == null) {
            return null;
        }
        return new String(readFile2BytesByStream, str);
    }

    public static void writeFileToUri(File file, Uri uri, WriteFileToUriListener writeFileToUriListener) {
        if (uri == null || file == null || !file.exists()) {
            return;
        }
        OutputStream outputStream = null;
        try {
            try {
                try {
                    OutputStream openOutputStream = App.getContext().getContentResolver().openOutputStream(uri);
                    if (openOutputStream == null) {
                        if (openOutputStream != null) {
                            try {
                                openOutputStream.close();
                                return;
                            } catch (IOException e) {
                                e.printStackTrace();
                                return;
                            }
                        }
                        return;
                    }
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 524288);
                    byte[] bArr = new byte[524288];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 524288);
                        if (read == -1) {
                            break;
                        }
                        openOutputStream.write(bArr, 0, read);
                        writeFileToUriListener.onProgress(read);
                    }
                    if (openOutputStream != null) {
                        openOutputStream.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (0 != 0) {
                        outputStream.close();
                    }
                }
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    outputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r7v4 */
    public static byte[] readFile2BytesByStream(File file) {
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        if (!isFileExists((File) file)) {
            return null;
        }
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream((File) file), 524288);
            } catch (Throwable th) {
                th = th;
            }
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[524288];
                    while (true) {
                        int read = bufferedInputStream.read(bArr, 0, 524288);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return byteArray;
                } catch (IOException e3) {
                    e = e3;
                    e.printStackTrace();
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    return null;
                }
            } catch (IOException e6) {
                e = e6;
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                file = 0;
                try {
                    bufferedInputStream.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
                if (file != 0) {
                    try {
                        file.close();
                    } catch (IOException e8) {
                        e8.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException e9) {
            e9.printStackTrace();
            return null;
        }
    }
}
