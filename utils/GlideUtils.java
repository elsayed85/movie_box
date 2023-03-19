package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.GlideApp;
import com.movieboxpro.android.app.GlideRequest;
import java.io.File;
import java.math.BigDecimal;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
/* loaded from: classes3.dex */
public class GlideUtils {
    public static void load(Activity activity, String str, ImageView imageView) {
        if (TextUtils.isEmpty(str) || activity == null || isDestroy(activity) || imageView == null) {
            return;
        }
        GlideApp.with(activity).load(str).into(imageView);
    }

    public static void loadPost(final Context context, final String str, final ImageView imageView, int i) {
        final MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(DensityUtils.dp2px(App.getContext(), i), 0));
        if (context == null || imageView == null) {
            return;
        }
        imageView.post(new Runnable() { // from class: com.movieboxpro.android.utils.-$$Lambda$GlideUtils$oCXAflpUi3-aGDVjTMvmfviBqVY
            @Override // java.lang.Runnable
            public final void run() {
                GlideUtils.lambda$loadPost$0(context, imageView, str, multiTransformation);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$loadPost$0(Context context, ImageView imageView, String str, MultiTransformation multiTransformation) {
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        int width = imageView.getWidth();
        int height = imageView.getHeight();
        if (width > 0 && height > 0) {
            Glide.with(context).asDrawable().transition(DrawableTransitionOptions.withCrossFade()).load(str).format(DecodeFormat.PREFER_RGB_565).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).placeholder(R.drawable.ic_land_loading_holder).override(width / 2, height / 2).into(imageView);
        } else {
            Glide.with(context).asDrawable().transition(DrawableTransitionOptions.withCrossFade()).load(str).format(DecodeFormat.PREFER_RGB_565).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).placeholder(R.drawable.ic_land_loading_holder).into(imageView);
        }
    }

    public static void load(Activity activity, int i, ImageView imageView) {
        if (activity == null || isDestroy(activity) || imageView == null) {
            return;
        }
        GlideApp.with(activity).load(Integer.valueOf(i)).into(imageView);
    }

    public static void load(Activity activity, String str, ImageView imageView, int i) {
        if (TextUtils.isEmpty(str) || activity == null || isDestroy(activity) || imageView == null) {
            return;
        }
        GlideApp.with(activity).load(str).placeholder(i).into(imageView);
    }

    public static void load(Context context, String str, ImageView imageView, int i) {
        if (context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).placeholder(i).error(i).into(imageView);
    }

    public static void load(Context context, String str, ImageView imageView, Drawable drawable) {
        if (context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).placeholder(drawable).error(drawable).into(imageView);
    }

    public static void loadLandGifHolder(Context context, String str, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            GlideApp.with(context).load(Integer.valueOf((int) R.drawable.ic_empty_land_holder)).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_RGB_565).into(imageView);
        } else {
            GlideApp.with(context).load(str).format(DecodeFormat.PREFER_RGB_565).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder((int) R.drawable.ic_land_loading_holder).error((int) R.drawable.ic_land_img_load_failed).into(imageView);
        }
    }

    public static void loadCornerLandGifHolder(Context context, String str, ImageView imageView, int i) {
        MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(DensityUtils.dp2px(App.getContext(), i), 0));
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            GlideApp.with(context).load(Integer.valueOf((int) R.drawable.ic_empty_land_holder)).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).format(DecodeFormat.PREFER_RGB_565).into(imageView);
        } else {
            GlideApp.with(context).load(str).format(DecodeFormat.PREFER_RGB_565).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).placeholder((int) R.drawable.ic_land_loading_holder).error((int) R.drawable.ic_land_img_load_failed).into(imageView);
        }
    }

    public static void loadLandSkipMemCache(Context context, String str, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            GlideApp.with(context).load(Integer.valueOf((int) R.drawable.ic_empty_land_holder)).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
        } else {
            GlideApp.with(context).load(str).placeholder((int) R.drawable.ic_land_loading_holder).diskCacheStrategy(DiskCacheStrategy.RESOURCE).error((int) R.drawable.ic_land_img_load_failed).into(imageView);
        }
    }

    public static void loadPortraitGifHolder(Context context, String str, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            GlideApp.with(context).load(Integer.valueOf((int) R.drawable.ic_empty_portrait_holder)).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_RGB_565).into(imageView);
        } else {
            GlideApp.with(context).load(str).format(DecodeFormat.PREFER_RGB_565).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder((int) R.drawable.ic_portrait_loading_holder).error((int) R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    public static void loadPortraitSkipMemCache(Context context, String str, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            GlideApp.with(context).load(Integer.valueOf((int) R.drawable.ic_empty_portrait_holder)).diskCacheStrategy(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_RGB_565).into(imageView);
        } else {
            GlideApp.with(context).load(str).format(DecodeFormat.PREFER_RGB_565).diskCacheStrategy(DiskCacheStrategy.RESOURCE).placeholder((int) R.drawable.ic_portrait_loading_holder).skipMemoryCache(true).error((int) R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    public static void loadCornerPortraitGifHolder(Context context, String str, ImageView imageView, int i) {
        MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(DensityUtils.dp2px(App.getContext(), i), 0));
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            GlideApp.with(context).load(Integer.valueOf((int) R.drawable.ic_empty_portrait_holder)).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).format(DecodeFormat.PREFER_RGB_565).into(imageView);
        } else {
            GlideApp.with(context).load(str).format(DecodeFormat.PREFER_RGB_565).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).diskCacheStrategy(DiskCacheStrategy.RESOURCE).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).placeholder((int) R.drawable.ic_portrait_loading_holder).error((int) R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    public static void loadWithNoCache(Context context, String str, ImageView imageView, int i) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).placeholder(i).into(imageView);
    }

    public static void loadWithNoCache(Context context, String str, ImageView imageView) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    public static void loadSkipMemory(Activity activity, String str, ImageView imageView) {
        if (activity == null || imageView == null || isDestroy(activity)) {
            return;
        }
        GlideApp.with(activity).load(str).skipMemoryCache(true).into(imageView);
    }

    public static void loadWithBlur(final Context context, final String str, final View view) {
        if (context == null || view == null) {
            return;
        }
        view.post(new Runnable() { // from class: com.movieboxpro.android.utils.-$$Lambda$GlideUtils$RmNLGK09kTQil8zwYDU3q_XSlTA
            @Override // java.lang.Runnable
            public final void run() {
                GlideUtils.lambda$loadWithBlur$1(context, view, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$loadWithBlur$1(Context context, final View view, String str) {
        if (context != null) {
            if (view.getWidth() != 0 && view.getHeight() != 0) {
                GlideApp.with(context).load(str).priority(Priority.LOW).skipMemoryCache(true).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new BlurTransformation(3, 1))).into((GlideRequest<Drawable>) new SimpleTarget<Drawable>(view.getWidth() / 3, view.getHeight() / 3) { // from class: com.movieboxpro.android.utils.GlideUtils.1
                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                    }

                    public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                        view.setBackground(drawable);
                    }
                });
            } else {
                GlideApp.with(context).load(str).priority(Priority.LOW).skipMemoryCache(true).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new BlurTransformation(3, 1))).into((GlideRequest<Drawable>) new SimpleTarget<Drawable>() { // from class: com.movieboxpro.android.utils.GlideUtils.2
                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                    }

                    public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                        view.setBackground(drawable);
                    }
                });
            }
        }
    }

    public static void loadWithBlur(final Activity activity, final String str, final View view) {
        if (activity == null || view == null) {
            return;
        }
        view.post(new Runnable() { // from class: com.movieboxpro.android.utils.-$$Lambda$GlideUtils$mlAM_vdw-FWVx1wzkqW8v4g1GI8
            @Override // java.lang.Runnable
            public final void run() {
                GlideUtils.lambda$loadWithBlur$2(activity, view, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$loadWithBlur$2(Activity activity, final View view, String str) {
        if (isDestroy(activity)) {
            return;
        }
        if (view.getWidth() != 0 && view.getHeight() != 0) {
            GlideApp.with(activity).load(str).priority(Priority.LOW).skipMemoryCache(true).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new BlurTransformation(30, 3))).into((GlideRequest<Drawable>) new SimpleTarget<Drawable>(view.getWidth() / 3, view.getHeight() / 3) { // from class: com.movieboxpro.android.utils.GlideUtils.3
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    view.setBackground(drawable);
                }
            });
        } else {
            GlideApp.with(activity).load(str).priority(Priority.LOW).skipMemoryCache(true).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new BlurTransformation(30, 3))).into((GlideRequest<Drawable>) new SimpleTarget<Drawable>() { // from class: com.movieboxpro.android.utils.GlideUtils.4
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                }

                public void onResourceReady(Drawable drawable, Transition<? super Drawable> transition) {
                    view.setBackground(drawable);
                }
            });
        }
    }

    public static void loadSkipMemory(Context context, String str, ImageView imageView, int i) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).skipMemoryCache(true).placeholder(i).into(imageView);
    }

    public static void load(Context context, int i, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(Integer.valueOf(i)).into(imageView);
    }

    public static void load(Context context, Uri uri, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(uri).into(imageView);
    }

    public static void load(Context context, Bitmap bitmap, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(bitmap).into(imageView);
    }

    public static void load(Context context, File file, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(file).into(imageView);
    }

    public static void load(Context context, String str, ImageView imageView) {
        GlideApp.with(context).load(str).into(imageView);
    }

    public static void loads(Context context, String str, ImageView imageView) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).transform((Transformation<Bitmap>) new CropTransformation(imageView.getWidth(), imageView.getHeight(), CropTransformation.CropType.BOTTOM)).priority(Priority.LOW).skipMemoryCache(true).into(imageView);
    }

    public static void load(Activity activity, String str, ImageView imageView, int i, int i2) {
        if (TextUtils.isEmpty(str) || activity == null || isDestroy(activity) || imageView == null) {
            return;
        }
        GlideApp.with(activity).load(str).transform((Transformation<Bitmap>) new RoundedCornersTransformation(i, i2)).into(imageView);
    }

    public static void load(Activity activity, String str, ImageView imageView, int i, int i2, int i3) {
        if (TextUtils.isEmpty(str) || activity == null || isDestroy(activity) || imageView == null) {
            return;
        }
        GlideApp.with(activity).load(str).error(i).transform((Transformation<Bitmap>) new RoundedCornersTransformation(i2, i3)).into(imageView);
    }

    public static void load(Context context, String str, ImageView imageView, int i, int i2, int i3) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).error(i).transform((Transformation<Bitmap>) new RoundedCornersTransformation(i2, i3)).into(imageView);
    }

    public static void loadWithCircle(Context context, String str, ImageView imageView, int i, int i2) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        int dp2px = DensityUtils.dp2px(context, i);
        GlideApp.with(context).load(str).placeholder(i2).skipMemoryCache(true).priority(Priority.LOW).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).override(dp2px, dp2px).into(imageView);
    }

    public static void loadWithCircle(Context context, String str, ImageView imageView, int i) {
        if (TextUtils.isEmpty(str) || context == null || imageView == null) {
            return;
        }
        GlideApp.with(context).load(str).placeholder(i).skipMemoryCache(true).priority(Priority.LOW).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(imageView);
    }

    public static Bitmap loadWithCircleFile(Context context, String str) throws Exception {
        return GlideApp.with(context).asBitmap().load(str).apply((BaseRequestOptions<?>) RequestOptions.centerCropTransform()).override(200, 200).submit().get();
    }

    public static void loadWithCircle(Context context, int i, ImageView imageView, int i2, int i3) {
        if (context == null || imageView == null) {
            return;
        }
        int dp2px = DensityUtils.dp2px(context, i2);
        GlideApp.with(context).load(Integer.valueOf(i)).placeholder(i3).transition((TransitionOptions<?, ? super Drawable>) DrawableTransitionOptions.withCrossFade()).skipMemoryCache(true).priority(Priority.LOW).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).override(dp2px, dp2px).into(imageView);
    }

    public static void loadWithCornerThumb(Context context, String str, ImageView imageView, int i) {
        GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i, 0)))).thumbnail(0.1f).into(imageView);
    }

    public static void loadWithCorner(Context context, String str, ImageView imageView, int i) {
        GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i, 0)))).into(imageView);
    }

    public static void loadWithCorner(Context context, int i, ImageView imageView, int i2) {
        GlideApp.with(context).load(Integer.valueOf(i)).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i2, 0)))).into(imageView);
    }

    private static int[] getImageWidthHeight(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static void loadWithCornerOriginSize(Context context, String str, ImageView imageView, int i, int i2, int i3) {
        if (i2 != 400 && i3 != 400) {
            if (i2 != 0) {
                i3 = (i3 * 400) / i2;
                i2 = 400;
            } else {
                i2 = 400;
                i3 = 0;
            }
        }
        if (i2 != 0 && i3 != 0) {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i, 0)))).override(i2, i3).into(imageView);
        } else {
            GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i, 0)))).into(imageView);
        }
    }

    public static void loadWithCorner(Context context, String str, ImageView imageView, int i, int i2) {
        GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(i, 0)))).placeholder(i2).into(imageView);
    }

    public static void loadBlur(Context context, String str, SimpleTarget<Drawable> simpleTarget) {
        GlideApp.with(context).load(str).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new BlurTransformation(30, 3))).into((GlideRequest<Drawable>) simpleTarget);
    }

    public static void loadBlurWithNoCache(Context context, String str, SimpleTarget<Drawable> simpleTarget) {
        GlideApp.with(context).load(str).diskCacheStrategy(DiskCacheStrategy.NONE).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new BlurTransformation(30, 3))).into((GlideRequest<Drawable>) simpleTarget);
    }

    public static void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() { // from class: com.movieboxpro.android.utils.GlideUtils.5
                    @Override // java.lang.Runnable
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        deleteFolderFile(context.getExternalCacheDir() + DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, true);
    }

    public static String getCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/" + DiskCache.Factory.DEFAULT_DISK_CACHE_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getFolderSize(File file) throws Exception {
        File[] listFiles;
        long length;
        long j = 0;
        try {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    length = getFolderSize(file2);
                } else {
                    length = file2.length();
                }
                j += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static void deleteFolderFile(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            File file = new File(str);
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteFolderFile(file2.getAbsolutePath(), true);
                }
            }
            if (z) {
                if (!file.isDirectory()) {
                    file.delete();
                } else if (file.listFiles().length == 0) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isDestroy(Activity activity) {
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    public static String getFormatSize(double d) {
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return d + "Byte";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            BigDecimal bigDecimal = new BigDecimal(Double.toString(d2));
            return bigDecimal.setScale(2, 4).toPlainString() + "KB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            BigDecimal bigDecimal2 = new BigDecimal(Double.toString(d3));
            return bigDecimal2.setScale(2, 4).toPlainString() + "MB";
        }
        double d5 = d4 / 1024.0d;
        if (d5 < 1.0d) {
            BigDecimal bigDecimal3 = new BigDecimal(Double.toString(d4));
            return bigDecimal3.setScale(2, 4).toPlainString() + "GB";
        }
        BigDecimal bigDecimal4 = new BigDecimal(d5);
        return bigDecimal4.setScale(2, 4).toPlainString() + "TB";
    }
}
