package com.movieboxpro.android.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.huantansheng.easyphotos.engine.ImageEngine;
/* loaded from: classes3.dex */
public class ChooseImageGlideEngine implements ImageEngine {
    private static ChooseImageGlideEngine instance;

    private ChooseImageGlideEngine() {
    }

    public static ChooseImageGlideEngine getInstance() {
        if (instance == null) {
            synchronized (ChooseImageGlideEngine.class) {
                if (instance == null) {
                    instance = new ChooseImageGlideEngine();
                }
            }
        }
        return instance;
    }

    @Override // com.huantansheng.easyphotos.engine.ImageEngine
    public void loadPhoto(Context context, Uri uri, ImageView imageView) {
        Glide.with(context).load(uri).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    @Override // com.huantansheng.easyphotos.engine.ImageEngine
    public void loadGifAsBitmap(Context context, Uri uri, ImageView imageView) {
        Glide.with(context).asBitmap().load(uri).into(imageView);
    }

    @Override // com.huantansheng.easyphotos.engine.ImageEngine
    public void loadGif(Context context, Uri uri, ImageView imageView) {
        Glide.with(context).asGif().load(uri).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    @Override // com.huantansheng.easyphotos.engine.ImageEngine
    public Bitmap getCacheBitmap(Context context, Uri uri, int i, int i2) throws Exception {
        return Glide.with(context).asBitmap().load(uri).submit(i, i2).get();
    }
}
