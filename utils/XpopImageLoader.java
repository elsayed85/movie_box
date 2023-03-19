package com.movieboxpro.android.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lxj.xpopup.interfaces.OnImageLoadCompleteListener;
import com.lxj.xpopup.interfaces.XPopupImageLoader;
import java.io.File;
/* loaded from: classes3.dex */
public class XpopImageLoader implements XPopupImageLoader {
    @Override // com.lxj.xpopup.interfaces.XPopupImageLoader
    public void loadImage(final Context context, int i, final Object obj, final ImageView imageView, ProgressBar progressBar, final OnImageLoadCompleteListener onImageLoadCompleteListener) {
        imageView.post(new Runnable() { // from class: com.movieboxpro.android.utils.XpopImageLoader.1
            @Override // java.lang.Runnable
            public void run() {
                Glide.with(context).load(obj).listener(new RequestListener<Drawable>() { // from class: com.movieboxpro.android.utils.XpopImageLoader.1.1
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(GlideException glideException, Object obj2, Target<Drawable> target, boolean z) {
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable drawable, Object obj2, Target<Drawable> target, DataSource dataSource, boolean z) {
                        imageView.setImageDrawable(drawable);
                        onImageLoadCompleteListener.onImageLoadComplete();
                        return false;
                    }
                }).into(imageView);
            }
        });
    }

    @Override // com.lxj.xpopup.interfaces.XPopupImageLoader
    public void loadImage(int i, Object obj, ImageView imageView) {
        Glide.with(imageView).load(obj).apply((BaseRequestOptions<?>) new RequestOptions().override(imageView.getWidth(), imageView.getHeight())).into(imageView);
    }

    @Override // com.lxj.xpopup.interfaces.XPopupImageLoader
    public File getImageFile(Context context, Object obj) {
        try {
            return Glide.with(context).downloadOnly().load(obj).submit().get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
