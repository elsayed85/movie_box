package com.movieboxpro.android.utils;

import android.content.Context;
import android.util.Log;
import com.movieboxpro.android.app.App;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.ILibVLCFactory;
import org.videolan.libvlc.util.VLCUtil;
/* compiled from: VLCInstance.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0003J\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\u00028\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/utils/VLCInstance;", "Lcom/movieboxpro/android/utils/SingletonHolder;", "Lorg/videolan/libvlc/interfaces/ILibVLC;", "Landroid/content/Context;", "()V", "TAG", "", "libVLCFactory", "Lorg/videolan/libvlc/interfaces/ILibVLCFactory;", "sLibVLC", "init", "ctx", "restart", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VLCInstance extends SingletonHolder<ILibVLC, Context> {
    public static final VLCInstance INSTANCE = new VLCInstance();
    public static final String TAG = "VLC/UiTools/VLCInstance";
    private static final ILibVLCFactory libVLCFactory;
    private static ILibVLC sLibVLC;

    /* compiled from: VLCInstance.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/videolan/libvlc/interfaces/ILibVLC;", "it", "Landroid/content/Context;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.utils.VLCInstance$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends Lambda implements Function1<Context, ILibVLC> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final ILibVLC invoke(Context it) {
            Intrinsics.checkNotNullParameter(it, "it");
            VLCInstance vLCInstance = VLCInstance.INSTANCE;
            Context applicationContext = it.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "it.applicationContext");
            return vLCInstance.init(applicationContext);
        }
    }

    private VLCInstance() {
        super(AnonymousClass1.INSTANCE);
    }

    static {
        IComponentFactory factory = FactoryManager.getFactory(ILibVLCFactory.factoryId);
        if (factory == null) {
            throw new NullPointerException("null cannot be cast to non-null type org.videolan.libvlc.interfaces.ILibVLCFactory");
        }
        libVLCFactory = (ILibVLCFactory) factory;
    }

    public final ILibVLC init(Context ctx) throws IllegalStateException {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        if (!VLCUtil.hasCompatibleCPU(ctx)) {
            Log.e(TAG, VLCUtil.getErrorMsg());
            throw new IllegalStateException(Intrinsics.stringPlus("LibVLC initialisation failed: ", VLCUtil.getErrorMsg()));
        }
        ILibVLC fromOptions = libVLCFactory.getFromOptions(ctx, VLCOptions.INSTANCE.getLibOptions());
        Intrinsics.checkNotNullExpressionValue(fromOptions, "libVLCFactory.getFromOpt…x, VLCOptions.libOptions)");
        sLibVLC = fromOptions;
        if (fromOptions == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
            return null;
        }
        return fromOptions;
    }

    public final void restart() throws IllegalStateException {
        ILibVLC iLibVLC = sLibVLC;
        if (iLibVLC == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sLibVLC");
            iLibVLC = null;
        }
        iLibVLC.release();
        ILibVLC fromOptions = libVLCFactory.getFromOptions(App.getContext(), VLCOptions.INSTANCE.getLibOptions());
        Intrinsics.checkNotNullExpressionValue(fromOptions, "libVLCFactory.getFromOpt…), VLCOptions.libOptions)");
        sLibVLC = fromOptions;
    }
}
