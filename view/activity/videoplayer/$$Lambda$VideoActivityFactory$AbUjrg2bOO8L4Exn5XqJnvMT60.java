package com.movieboxpro.android.view.activity.videoplayer;

import android.content.DialogInterface;
import com.movieboxpro.android.utils.PrefsUtils;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$VideoActivityFactory$AbUjrg2bOO8L4Ex-n5XqJnvMT60  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$VideoActivityFactory$AbUjrg2bOO8L4Exn5XqJnvMT60 implements DialogInterface.OnDismissListener {
    public static final /* synthetic */ $$Lambda$VideoActivityFactory$AbUjrg2bOO8L4Exn5XqJnvMT60 INSTANCE = new $$Lambda$VideoActivityFactory$AbUjrg2bOO8L4Exn5XqJnvMT60();

    private /* synthetic */ $$Lambda$VideoActivityFactory$AbUjrg2bOO8L4Exn5XqJnvMT60() {
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        PrefsUtils.getInstance().putInt("DialogCount", PrefsUtils.getInstance().getInt("DialogCount", 0) + 1);
    }
}
