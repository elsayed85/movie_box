package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseActivity;
import com.shizhefei.view.largeimage.LargeImageView;
/* loaded from: classes3.dex */
public class PhotoActivity extends BaseActivity {
    public static final String PHTOTCHANGE = "photochange";
    @BindView(R.id.activity_photo_image)
    ImageView mPhoto;
    @BindView(R.id.imageView)
    LargeImageView mPhotos;

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isFullScreen() {
        return true;
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_photo, viewGroup, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        String stringParam = getStringParam(PHTOTCHANGE, "");
        if (this.activity != null && !this.activity.isFinishing()) {
            Glide.with(this.activity).asBitmap().load(stringParam).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.movieboxpro.android.view.activity.settings.PhotoActivity.1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    PhotoActivity.this.mPhotos.setImage(bitmap);
                }
            });
        }
        this.mPhotos.setEnabled(true);
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, PhotoActivity.class);
        intent.putExtra(PHTOTCHANGE, str);
        context.startActivity(intent);
    }

    @OnClick({R.id.imageView})
    public void onViewClicked() {
        this.activity.finish();
    }
}
