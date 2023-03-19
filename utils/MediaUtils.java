package com.movieboxpro.android.utils;

import kotlin.Metadata;
/* compiled from: MediaUtils.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000bJ6\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u000b¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/utils/MediaUtils;", "", "()V", "saveImageWithAndroidQ", "Lkotlin/Pair;", "", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "sourceFile", "saveFileName", "", "saveDirName", "Ljava/io/File;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MediaUtils {
    public static final MediaUtils INSTANCE = new MediaUtils();

    private MediaUtils() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0094, code lost:
        if (r1 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.Pair<java.lang.Boolean, android.net.Uri> saveImageWithAndroidQ(android.content.Context r4, java.io.File r5, java.lang.String r6, java.lang.String r7) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "sourceFile"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "saveDirName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "_display_name"
            r0.put(r1, r6)
            java.lang.String r6 = "mime_type"
            java.lang.String r1 = "image/jpg"
            r0.put(r6, r1)
            java.lang.String r6 = "title"
            java.lang.String r1 = "Image.jpg"
            r0.put(r6, r1)
            java.lang.String r6 = "Pictures/"
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r7)
            java.lang.String r7 = "relative_path"
            r0.put(r7, r6)
            android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            java.lang.String r7 = "EXTERNAL_CONTENT_URI"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            android.content.ContentResolver r4 = r4.getContentResolver()
            java.lang.String r7 = "context.getContentResolver()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r7)
            android.net.Uri r6 = r4.insert(r6, r0)
            r7 = 0
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L7e java.io.IOException -> L8d
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L7e java.io.IOException -> L8d
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L7e java.io.IOException -> L8d
            java.io.InputStream r2 = (java.io.InputStream) r2     // Catch: java.lang.Throwable -> L7e java.io.IOException -> L8d
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L7e java.io.IOException -> L8d
            if (r6 == 0) goto L5f
            java.io.OutputStream r0 = r4.openOutputStream(r6)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            goto L5f
        L5b:
            r4 = move-exception
            goto L80
        L5d:
            goto L8e
        L5f:
            if (r0 == 0) goto L73
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
        L65:
            int r5 = r1.read(r4)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            r2 = -1
            if (r5 == r2) goto L70
            r0.write(r4, r7, r5)     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
            goto L65
        L70:
            r0.flush()     // Catch: java.lang.Throwable -> L5b java.io.IOException -> L5d
        L73:
            r7 = 1
            if (r0 != 0) goto L77
            goto L7a
        L77:
            r0.close()
        L7a:
            r1.close()
            goto L96
        L7e:
            r4 = move-exception
            r1 = r0
        L80:
            if (r0 != 0) goto L83
            goto L86
        L83:
            r0.close()
        L86:
            if (r1 != 0) goto L89
            goto L8c
        L89:
            r1.close()
        L8c:
            throw r4
        L8d:
            r1 = r0
        L8e:
            if (r0 != 0) goto L91
            goto L94
        L91:
            r0.close()
        L94:
            if (r1 != 0) goto L7a
        L96:
            kotlin.Pair r4 = new kotlin.Pair
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r7)
            r4.<init>(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.MediaUtils.saveImageWithAndroidQ(android.content.Context, java.io.File, java.lang.String, java.lang.String):kotlin.Pair");
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x0091, code lost:
        if (r1 == null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.Pair<java.lang.Boolean, android.net.Uri> saveImageWithAndroidQ(android.content.Context r4, android.net.Uri r5, java.lang.String r6, java.lang.String r7) {
        /*
            r3 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "sourceFile"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "saveDirName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            android.content.ContentValues r0 = new android.content.ContentValues
            r0.<init>()
            java.lang.String r1 = "_display_name"
            r0.put(r1, r6)
            java.lang.String r6 = "mime_type"
            java.lang.String r1 = "image/jpg"
            r0.put(r6, r1)
            java.lang.String r6 = "title"
            java.lang.String r1 = "Image.jpg"
            r0.put(r6, r1)
            java.lang.String r6 = "Pictures/"
            java.lang.String r6 = kotlin.jvm.internal.Intrinsics.stringPlus(r6, r7)
            java.lang.String r7 = "relative_path"
            r0.put(r7, r6)
            android.net.Uri r6 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            java.lang.String r7 = "EXTERNAL_CONTENT_URI"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            android.content.ContentResolver r4 = r4.getContentResolver()
            java.lang.String r7 = "context.getContentResolver()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r7)
            android.net.Uri r6 = r4.insert(r6, r0)
            r7 = 0
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L8a
            java.io.InputStream r5 = r4.openInputStream(r5)     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L8a
            r1.<init>(r5)     // Catch: java.lang.Throwable -> L7b java.io.IOException -> L8a
            if (r6 == 0) goto L5c
            java.io.OutputStream r0 = r4.openOutputStream(r6)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            goto L5c
        L58:
            r4 = move-exception
            goto L7d
        L5a:
            goto L8b
        L5c:
            if (r0 == 0) goto L70
            r4 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r4]     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
        L62:
            int r5 = r1.read(r4)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            r2 = -1
            if (r5 == r2) goto L6d
            r0.write(r4, r7, r5)     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
            goto L62
        L6d:
            r0.flush()     // Catch: java.lang.Throwable -> L58 java.io.IOException -> L5a
        L70:
            r7 = 1
            if (r0 != 0) goto L74
            goto L77
        L74:
            r0.close()
        L77:
            r1.close()
            goto L93
        L7b:
            r4 = move-exception
            r1 = r0
        L7d:
            if (r0 != 0) goto L80
            goto L83
        L80:
            r0.close()
        L83:
            if (r1 != 0) goto L86
            goto L89
        L86:
            r1.close()
        L89:
            throw r4
        L8a:
            r1 = r0
        L8b:
            if (r0 != 0) goto L8e
            goto L91
        L8e:
            r0.close()
        L91:
            if (r1 != 0) goto L77
        L93:
            kotlin.Pair r4 = new kotlin.Pair
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r7)
            r4.<init>(r5, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.MediaUtils.saveImageWithAndroidQ(android.content.Context, android.net.Uri, java.lang.String, java.lang.String):kotlin.Pair");
    }
}
