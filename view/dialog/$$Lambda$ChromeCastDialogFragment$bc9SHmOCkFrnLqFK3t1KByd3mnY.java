package com.movieboxpro.android.view.dialog;

import androidx.mediarouter.media.MediaRouter;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.dialog.-$$Lambda$ChromeCastDialogFragment$bc9SHmOCkFrnLqFK3t1KByd3mnY  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ChromeCastDialogFragment$bc9SHmOCkFrnLqFK3t1KByd3mnY implements Comparator {
    public static final /* synthetic */ $$Lambda$ChromeCastDialogFragment$bc9SHmOCkFrnLqFK3t1KByd3mnY INSTANCE = new $$Lambda$ChromeCastDialogFragment$bc9SHmOCkFrnLqFK3t1KByd3mnY();

    private /* synthetic */ $$Lambda$ChromeCastDialogFragment$bc9SHmOCkFrnLqFK3t1KByd3mnY() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int m965refreshRoutes$lambda1;
        m965refreshRoutes$lambda1 = ChromeCastDialogFragment.m965refreshRoutes$lambda1((MediaRouter.RouteInfo) obj, (MediaRouter.RouteInfo) obj2);
        return m965refreshRoutes$lambda1;
    }
}
