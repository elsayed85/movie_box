package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.shizhefei.view.largeimage.LargeImageView;
/* loaded from: classes3.dex */
public class PhotoActivity_ViewBinding implements Unbinder {
    private PhotoActivity target;
    private View view7f090252;

    public PhotoActivity_ViewBinding(PhotoActivity photoActivity) {
        this(photoActivity, photoActivity.getWindow().getDecorView());
    }

    public PhotoActivity_ViewBinding(final PhotoActivity photoActivity, View view) {
        this.target = photoActivity;
        photoActivity.mPhoto = (ImageView) Utils.findRequiredViewAsType(view, R.id.activity_photo_image, "field 'mPhoto'", ImageView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.imageView, "field 'mPhotos' and method 'onViewClicked'");
        photoActivity.mPhotos = (LargeImageView) Utils.castView(findRequiredView, R.id.imageView, "field 'mPhotos'", LargeImageView.class);
        this.view7f090252 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.PhotoActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                photoActivity.onViewClicked();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PhotoActivity photoActivity = this.target;
        if (photoActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        photoActivity.mPhoto = null;
        photoActivity.mPhotos = null;
        this.view7f090252.setOnClickListener(null);
        this.view7f090252 = null;
    }
}
