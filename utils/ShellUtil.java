package com.movieboxpro.android.utils;

import java.util.List;
/* loaded from: classes3.dex */
public class ShellUtil {
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_SU = "su";

    public static boolean hasRootPermission() {
        return execCommand("echo root", true, false).result == 0;
    }

    public static CommandResult execCommand(String str, boolean z) {
        return execCommand(new String[]{str}, z, true);
    }

    public static CommandResult execCommand(String str, boolean z, boolean z2) {
        return execCommand(new String[]{str}, z, z2);
    }

    public static CommandResult execCommand(List<String> list, boolean z, boolean z2) {
        return execCommand(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x0159, code lost:
        if (r10 == null) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0162, code lost:
        if (r10 != null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0188, code lost:
        if (r10 == null) goto L90;
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0191, code lost:
        if (r10 != null) goto L91;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0193, code lost:
        r10.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x0196, code lost:
        r8 = r9;
        r10 = r1;
        r1 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x019b, code lost:
        if (r8 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x019d, code lost:
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x019f, code lost:
        r8 = r8.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x01a3, code lost:
        if (r10 != 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x01a6, code lost:
        r0 = r10.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x01ad, code lost:
        return new com.movieboxpro.android.utils.ShellUtil.CommandResult(r1, r8, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x01cb, code lost:
        if (r9 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x01d4, code lost:
        if (r9 == null) goto L139;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x01d6, code lost:
        r9.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x01d9, code lost:
        throw r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00fb, code lost:
        if (r9 != null) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00fd, code lost:
        r9.destroy();
        r10 = r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0105, code lost:
        if (r9 == null) goto L33;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x01b0: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:155:0x01af */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x01b1: MOVE  (r3 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:155:0x01af */
    /* JADX WARN: Not initialized variable reg: 5, insn: 0x01b2: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r5 I:??[OBJECT, ARRAY]), block:B:155:0x01af */
    /* JADX WARN: Removed duplicated region for block: B:108:0x014d A[Catch: all -> 0x0147, IOException -> 0x0149, TryCatch #8 {IOException -> 0x0149, blocks: (B:102:0x0143, B:108:0x014d, B:110:0x0152), top: B:184:0x0143, outer: #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0152 A[Catch: all -> 0x0147, IOException -> 0x0149, TRY_LEAVE, TryCatch #8 {IOException -> 0x0149, blocks: (B:102:0x0143, B:108:0x014d, B:110:0x0152), top: B:184:0x0143, outer: #18 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017c A[Catch: all -> 0x0176, IOException -> 0x0178, TryCatch #0 {IOException -> 0x0178, blocks: (B:126:0x0172, B:132:0x017c, B:134:0x0181), top: B:178:0x0172, outer: #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0181 A[Catch: all -> 0x0176, IOException -> 0x0178, TRY_LEAVE, TryCatch #0 {IOException -> 0x0178, blocks: (B:126:0x0172, B:132:0x017c, B:134:0x0181), top: B:178:0x0172, outer: #11 }] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01bf A[Catch: all -> 0x01b9, IOException -> 0x01bb, TryCatch #15 {IOException -> 0x01bb, blocks: (B:157:0x01b5, B:163:0x01bf, B:165:0x01c4), top: B:188:0x01b5, outer: #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01c4 A[Catch: all -> 0x01b9, IOException -> 0x01bb, TRY_LEAVE, TryCatch #15 {IOException -> 0x01bb, blocks: (B:157:0x01b5, B:163:0x01bf, B:165:0x01c4), top: B:188:0x01b5, outer: #12 }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0172 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0143 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x01b5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v24 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.movieboxpro.android.utils.ShellUtil.CommandResult execCommand(java.lang.String[] r8, boolean r9, boolean r10) {
        /*
            Method dump skipped, instructions count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.utils.ShellUtil.execCommand(java.lang.String[], boolean, boolean):com.movieboxpro.android.utils.ShellUtil$CommandResult");
    }

    /* loaded from: classes3.dex */
    public static class CommandResult {
        public String errorMsg;
        public String responseMsg;
        public int result;

        public CommandResult(int i) {
            this.result = i;
        }

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.responseMsg = str;
            this.errorMsg = str2;
        }
    }
}
