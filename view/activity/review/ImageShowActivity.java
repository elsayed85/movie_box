package com.movieboxpro.android.view.activity.review;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.review.ImageShowActivity;
import com.movieboxpro.android.view.widget.LayoutScrollListener;
import com.movieboxpro.android.view.widget.SlideCloseLayout;
import com.shizhefei.view.largeimage.LargeImageView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
/* compiled from: ImageShowActivity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\bH\u0003J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\nH\u0002J \u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ImageShowActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "imageUrls", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "downloadImage", "", "getLayoutResId", "", "getStatusColor", "initData", "initListener", "initView", "requestWritePermission", "setIndicatorNum", "position", "writeInputStreamToAlbum", "inputStream", "Ljava/io/InputStream;", "displayName", "mimeType", "Companion", "MyPagerAdapter", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ImageShowActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ArrayList<String> imageUrls = new ArrayList<>();

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_image_show;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.black;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    /* compiled from: ImageShowActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ImageShowActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "position", "", "urls", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, int i, ArrayList<String> urls) {
            Intrinsics.checkNotNullParameter(urls, "urls");
            Intent intent = new Intent(context, ImageShowActivity.class);
            intent.putExtra("position", i);
            intent.putExtra("urls", urls);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$initListener$1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ImageShowActivity.this.setIndicatorNum(i);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.ivDownload)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ImageShowActivity$edMv2n4b1UHpaa6n4TpfM0d5X_M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageShowActivity.m552initListener$lambda0(ImageShowActivity.this, view);
            }
        });
        ((SlideCloseLayout) _$_findCachedViewById(R.id.slideCloseLayout)).setLayoutScrollListener(new LayoutScrollListener() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$initListener$3
            @Override // com.movieboxpro.android.view.widget.LayoutScrollListener
            public void onLayoutClosed() {
                ImageShowActivity.this.finish();
                ImageShowActivity.this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m552initListener$lambda0(ImageShowActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requestWritePermission();
    }

    private final void requestWritePermission() {
        downloadImage();
    }

    private final void writeInputStreamToAlbum(InputStream inputStream, String str, String str2) {
        OutputStream openOutputStream;
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", str);
        contentValues.put("mime_type", str2);
        if (Build.VERSION.SDK_INT >= 29) {
            contentValues.put("relative_path", Environment.DIRECTORY_DCIM);
        } else {
            contentValues.put("_data", ((Object) Environment.getExternalStorageDirectory().getPath()) + '/' + ((Object) Environment.DIRECTORY_DCIM) + '/' + str);
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        Uri insert = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null && (openOutputStream = getContentResolver().openOutputStream(insert)) != null) {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openOutputStream);
            byte[] bArr = new byte[1024];
            for (int read = bufferedInputStream.read(bArr); read >= 0; read = bufferedInputStream.read(bArr)) {
                bufferedOutputStream.write(bArr, 0, read);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
        }
        bufferedInputStream.close();
    }

    private final void downloadImage() {
        Observable map = Observable.just(this.imageUrls.get(((ViewPager) _$_findCachedViewById(R.id.viewPager)).getCurrentItem())).map(new Function() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ImageShowActivity$oUan83CEQOsw4IJy7FAHUUOPepQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Boolean m551downloadImage$lambda1;
                m551downloadImage$lambda1 = ImageShowActivity.m551downloadImage$lambda1(ImageShowActivity.this, (String) obj);
                return m551downloadImage$lambda1;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "just(imageUrls[viewPager…   true\n                }");
        RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$downloadImage$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ImageShowActivity.this.hideLoadingView();
                if (((Boolean) it).booleanValue()) {
                    ImageView ivDownload = (ImageView) ImageShowActivity.this._$_findCachedViewById(R.id.ivDownload);
                    Intrinsics.checkNotNullExpressionValue(ivDownload, "ivDownload");
                    CommonExtKt.gone(ivDownload);
                    return;
                }
                ToastUtils.showShort("Download failed", new Object[0]);
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$downloadImage$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ImageShowActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Download failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$downloadImage$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$downloadImage$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ImageShowActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadImage$lambda-1  reason: not valid java name */
    public static final Boolean m551downloadImage$lambda1(ImageShowActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        String str = Constant.DIR_CACHE;
        File file = new File(str, System.currentTimeMillis() + ".jpg");
        new Pair(Boolean.valueOf(FileUtils.copyFile(Glide.with((FragmentActivity) this$0).asFile().load(it).submit().get(), file)), file.getPath());
        String name = file.getName();
        Intrinsics.checkNotNullExpressionValue(name, "targetFile.name");
        this$0.writeInputStreamToAlbum(new FileInputStream(file), name, "image/jpg");
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("urls");
        if (stringArrayListExtra == null) {
            stringArrayListExtra = new ArrayList<>();
        }
        this.imageUrls = stringArrayListExtra;
        int intExtra = getIntent().getIntExtra("position", -1);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this, this, this.imageUrls);
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setPageMargin(CommonExtKt.dp2Px(15));
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setAdapter(myPagerAdapter);
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setCurrentItem(intExtra);
        if (this.imageUrls.size() > 1) {
            setIndicatorNum(intExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setIndicatorNum(int i) {
        TextView tvIndicator = (TextView) _$_findCachedViewById(R.id.tvIndicator);
        Intrinsics.checkNotNullExpressionValue(tvIndicator, "tvIndicator");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s/%s", Arrays.copyOf(new Object[]{Integer.valueOf(i + 1), Integer.valueOf(this.imageUrls.size())}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        CommonExtKt.textShadow(tvIndicator, format, 14, R.color.white_30alpha);
    }

    /* compiled from: ImageShowActivity.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/ImageShowActivity$MyPagerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "context", "Landroid/content/Context;", "mImagesPath", "", "", "(Lcom/movieboxpro/android/view/activity/review/ImageShowActivity;Landroid/content/Context;Ljava/util/List;)V", "destroyItem", "", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "position", "", "object", "", "getCount", "instantiateItem", "isViewFromObject", "", "view", "Landroid/view/View;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public final class MyPagerAdapter extends PagerAdapter {
        private final Context context;
        private final List<String> mImagesPath;
        final /* synthetic */ ImageShowActivity this$0;

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object object) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(object, "object");
            return view == object;
        }

        public MyPagerAdapter(ImageShowActivity this$0, Context context, List<String> mImagesPath) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(mImagesPath, "mImagesPath");
            this.this$0 = this$0;
            this.context = context;
            this.mImagesPath = mImagesPath;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup container, int i) {
            Intrinsics.checkNotNullParameter(container, "container");
            final String str = this.mImagesPath.get(i);
            View view = LayoutInflater.from(this.context).inflate(R.layout.image_show_item_layout, container, false);
            final LargeImageView largeImageView = (LargeImageView) view.findViewById(R.id.largeImageView);
            largeImageView.setEnabled(true);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.loading);
            largeImageView.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ImageShowActivity$MyPagerAdapter$hUc7HAY6nA9AF73b6b9Za4lKnBg
                @Override // java.lang.Runnable
                public final void run() {
                    ImageShowActivity.MyPagerAdapter.m553instantiateItem$lambda0(ImageShowActivity.MyPagerAdapter.this, str, progressBar, largeImageView);
                }
            });
            final ImageShowActivity imageShowActivity = this.this$0;
            largeImageView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$ImageShowActivity$MyPagerAdapter$fxxOu006om3u8j3mQpJ8kBF2zVA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ImageShowActivity.MyPagerAdapter.m554instantiateItem$lambda1(ImageShowActivity.this, view2);
                }
            });
            container.addView(view);
            Intrinsics.checkNotNullExpressionValue(view, "view");
            return view;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: instantiateItem$lambda-0  reason: not valid java name */
        public static final void m553instantiateItem$lambda0(MyPagerAdapter this$0, String url, final ProgressBar progressBar, final LargeImageView largeImageView) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(url, "$url");
            Glide.with(this$0.context).asBitmap().load(url).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.movieboxpro.android.view.activity.review.ImageShowActivity$MyPagerAdapter$instantiateItem$1$1
                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable drawable) {
                    super.onLoadFailed(drawable);
                    ProgressBar loading = progressBar;
                    Intrinsics.checkNotNullExpressionValue(loading, "loading");
                    CommonExtKt.gone(loading);
                    ToastUtils.showShort("Load failed", new Object[0]);
                }

                public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                    largeImageView.setImage(bitmap);
                    ProgressBar loading = progressBar;
                    Intrinsics.checkNotNullExpressionValue(loading, "loading");
                    CommonExtKt.gone(loading);
                }

                @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                public void onLoadStarted(Drawable drawable) {
                    ProgressBar loading = progressBar;
                    Intrinsics.checkNotNullExpressionValue(loading, "loading");
                    CommonExtKt.visible(loading);
                    super.onLoadStarted(drawable);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: instantiateItem$lambda-1  reason: not valid java name */
        public static final void m554instantiateItem$lambda1(ImageShowActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.finish();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.mImagesPath.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup container, int i, Object object) {
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(object, "object");
            container.removeView((View) object);
        }
    }
}
