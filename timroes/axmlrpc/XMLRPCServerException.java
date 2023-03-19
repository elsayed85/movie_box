package com.movieboxpro.android.timroes.axmlrpc;
/* loaded from: classes3.dex */
public class XMLRPCServerException extends XMLRPCException {
    private final int errornr;

    public XMLRPCServerException(String str, int i) {
        super(str);
        this.errornr = i;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage() + " [" + this.errornr + "]";
    }

    public int getErrorNr() {
        return this.errornr;
    }
}
