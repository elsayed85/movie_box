package com.movieboxpro.android.timroes.axmlrpc;
/* loaded from: classes3.dex */
public interface XMLRPCCallback {
    void onError(long j, XMLRPCException xMLRPCException);

    void onResponse(long j, Object obj);

    void onServerError(long j, XMLRPCServerException xMLRPCServerException);
}
