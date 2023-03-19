package com.movieboxpro.android.utils.unrar;
/* loaded from: classes3.dex */
public class MVTest {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void main(java.lang.String[] r4) {
        /*
            java.io.File r4 = new java.io.File
            java.lang.String r0 = "/home/Avenger/testdata/test2.part01.rar"
            r4.<init>(r0)
            com.movieboxpro.android.utils.unrar.Archive r0 = new com.movieboxpro.android.utils.unrar.Archive     // Catch: java.io.IOException -> Ld com.movieboxpro.android.utils.unrar.exception.RarException -> L12
            r0.<init>(r4)     // Catch: java.io.IOException -> Ld com.movieboxpro.android.utils.unrar.exception.RarException -> L12
            goto L17
        Ld:
            r4 = move-exception
            r4.printStackTrace()
            goto L16
        L12:
            r4 = move-exception
            r4.printStackTrace()
        L16:
            r0 = 0
        L17:
            if (r0 == 0) goto L6c
            com.movieboxpro.android.utils.unrar.rarfile.MainHeader r4 = r0.getMainHeader()
            r4.print()
            com.movieboxpro.android.utils.unrar.rarfile.FileHeader r4 = r0.nextFileHeader()
        L24:
            if (r4 == 0) goto L6c
            java.io.File r1 = new java.io.File     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r2.<init>()     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.lang.String r3 = "/home/Avenger/testdata/"
            r2.append(r3)     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.lang.String r3 = r4.getFileNameString()     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.lang.String r3 = r3.trim()     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r2.append(r3)     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.lang.String r2 = r2.toString()     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r1.<init>(r2)     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.io.PrintStream r2 = java.lang.System.out     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.lang.String r3 = r1.getAbsolutePath()     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r2.println(r3)     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r2.<init>(r1)     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r0.extractFile(r4, r2)     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            r2.close()     // Catch: java.io.IOException -> L59 com.movieboxpro.android.utils.unrar.exception.RarException -> L5e java.io.FileNotFoundException -> L63
            goto L67
        L59:
            r4 = move-exception
            r4.printStackTrace()
            goto L67
        L5e:
            r4 = move-exception
            r4.printStackTrace()
            goto L67
        L63:
            r4 = move-exception
            r4.printStackTrace()
        L67:
            com.movieboxpro.android.utils.unrar.rarfile.FileHeader r4 = r0.nextFileHeader()
            goto L24
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.unrar.MVTest.main(java.lang.String[]):void");
    }
}
