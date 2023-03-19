package com.movieboxpro.android.utils;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: BindingAdapters.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001a\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0007J\u001a\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0010\u001a\u00020\fH\u0007J\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J\"\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0001\u0010\u0013\u001a\u00020\fH\u0007¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/utils/BindingAdapters;", "", "()V", "goneUnless", "", "view", "Landroid/view/View;", "visible", "", "invisibleUnless", "loadBackground", "background", "", "loadImage", "imageView", "Landroid/widget/ImageView;", "imageRes", "url", "", "holder", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BindingAdapters {
    public static final BindingAdapters INSTANCE = new BindingAdapters();

    private BindingAdapters() {
    }

    @BindingAdapter({"invisibleUnless"})
    @JvmStatic
    public static final void invisibleUnless(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setVisibility(z ? 0 : 4);
    }

    @BindingAdapter({"goneUnless"})
    @JvmStatic
    public static final void goneUnless(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setVisibility(z ? 0 : 8);
    }

    @BindingAdapter({"imageUrl", "placeHolder"})
    @JvmStatic
    public static final void loadImage(ImageView imageView, String url, int i) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(url, "url");
        GlideUtils.load(imageView.getContext(), url, imageView, i);
    }

    @BindingAdapter({"imageUrl"})
    @JvmStatic
    public static final void loadImage(ImageView imageView, String url) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(url, "url");
        GlideUtils.load(imageView.getContext(), url, imageView);
    }

    @BindingAdapter({"imageUrl"})
    @JvmStatic
    public static final void loadImage(ImageView imageView, int i) {
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        GlideUtils.load(imageView.getContext(), i, imageView);
    }

    @BindingAdapter({"loadBackground"})
    @JvmStatic
    public static final void loadBackground(final View view, final int i) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.post(new Runnable() { // from class: com.movieboxpro.android.utils.-$$Lambda$BindingAdapters$elPEotZ_fZF5LZdSziTJapP_Bu8
            @Override // java.lang.Runnable
            public final void run() {
                BindingAdapters.m96loadBackground$lambda0(view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadBackground$lambda-0  reason: not valid java name */
    public static final void m96loadBackground$lambda0(final View view, int i) {
        Intrinsics.checkNotNullParameter(view, "$view");
        final int width = view.getWidth();
        final int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        Glide.with(view.getContext()).asDrawable().load(Integer.valueOf(i)).dontAnimate().into((RequestBuilder) new SimpleTarget<Drawable>(width, height, view) { // from class: com.movieboxpro.android.utils.BindingAdapters$loadBackground$1$1
            final /* synthetic */ int $height;
            final /* synthetic */ View $view;
            final /* synthetic */ int $width;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(width, height);
                this.$width = width;
                this.$height = height;
                this.$view = view;
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
            }

            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                this.$view.setBackground(resource);
            }
        });
    }
}
