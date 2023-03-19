package com.movieboxpro.android.view.activity.movielist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.PermissionRequestActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yalantis.ucrop.UCrop;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChooseMovieListCoverActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\nH\u0002J\u001a\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0002J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0002J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0014J\b\u0010\u0019\u001a\u00020\fH\u0016J\b\u0010\u001a\u001a\u00020\fH\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\"\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\fH\u0014J\u0010\u0010\"\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0003R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/ChooseMovieListCoverActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "", "imageFileMap", "Landroid/util/SparseArray;", "Ljava/io/File;", "outUri", "Landroid/net/Uri;", "chooseImg", "", "position", "", "cropImage", "uri", "downloadPoster", "url", "getImageContentUri", "context", "Landroid/content/Context;", "imageFile", "getLayoutResId", "getStatusColor", "initData", "initListener", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "requestPermission", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChooseMovieListCoverActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public static final int REQUEST_CHOOSE_IMAGE = 1;
    public static final int REQUEST_CROP = 2;
    private CommBaseAdapter<String> adapter;
    private Uri outUri;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final SparseArray<File> imageFileMap = new SparseArray<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestPermission$lambda-3  reason: not valid java name */
    public static final void m395requestPermission$lambda3(Boolean bool) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: requestPermission$lambda-4  reason: not valid java name */
    public static final void m396requestPermission$lambda4(Throwable th) {
    }

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
        return R.layout.activity_choose_movie_list_cover;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    protected int getStatusColor() {
        return R.color.color_main;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$ChooseMovieListCoverActivity$7JBlZMCk0i-IwxwdBEcUM9184y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseMovieListCoverActivity.m389initListener$lambda0(ChooseMovieListCoverActivity.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llChooseImage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$ChooseMovieListCoverActivity$VAFOaIcmbIkwcdXcgLxfx1KQCug
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChooseMovieListCoverActivity.m390initListener$lambda1(ChooseMovieListCoverActivity.this, view);
            }
        });
        CommBaseAdapter<String> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$ChooseMovieListCoverActivity$mC3L_5Ehhq-hPwnGG5iNoFJOk3s
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChooseMovieListCoverActivity.m391initListener$lambda2(ChooseMovieListCoverActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m389initListener$lambda0(ChooseMovieListCoverActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m390initListener$lambda1(ChooseMovieListCoverActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), "Choose"), 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m391initListener$lambda2(ChooseMovieListCoverActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (CommonUtils.havePermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"})) {
            this$0.chooseImg(i);
        } else {
            PermissionRequestActivity.Companion.start(this$0, 4, 100);
        }
    }

    private final void chooseImg(int i) {
        File file = this.imageFileMap.get(i);
        if (file != null) {
            this.outUri = Uri.parse("file:///" + ((Object) Constant.DIR_CROP) + ((Object) File.separator) + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
            cropImage(Uri.fromFile(file));
            return;
        }
        CommBaseAdapter<String> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        downloadPoster(i, commBaseAdapter.getItem(i));
    }

    private final void requestPermission(int i) {
        new RxPermissions(this).request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").subscribe($$Lambda$ChooseMovieListCoverActivity$AWgxFbTzLKm4i0oFVqDaOaAUHw0.INSTANCE, $$Lambda$ChooseMovieListCoverActivity$fFxbfDvfWtijpMbgu0FjLk778Q.INSTANCE);
    }

    private final void downloadPoster(final int i, String str) {
        Observable map = Observable.just(str).map(new Function() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$ChooseMovieListCoverActivity$rGfbjZEtkPCiE4RhGZpD6ebxHdo
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                File m388downloadPoster$lambda5;
                m388downloadPoster$lambda5 = ChooseMovieListCoverActivity.m388downloadPoster$lambda5(ChooseMovieListCoverActivity.this, i, (String) obj);
                return m388downloadPoster$lambda5;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "just(url)\n              …   file\n                }");
        RxSubscribersKt.transform(map, this).subscribe(new Consumer() { // from class: com.movieboxpro.android.view.activity.movielist.ChooseMovieListCoverActivity$downloadPoster$$inlined$transformSubscribe$default$1
            @Override // io.reactivex.functions.Consumer
            public final void accept(T it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ChooseMovieListCoverActivity.this.outUri = Uri.parse("file:///" + ((Object) Constant.DIR_CROP) + ((Object) File.separator) + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
                ChooseMovieListCoverActivity.this.cropImage(Uri.fromFile((File) it));
                ChooseMovieListCoverActivity.this.hideLoadingView();
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.movielist.ChooseMovieListCoverActivity$downloadPoster$$inlined$transformSubscribe$default$2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ApiException handleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(handleException, "handleException(it)");
                ChooseMovieListCoverActivity.this.hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("Load image failed:", handleException.getMessage()), new Object[0]);
                if (th instanceof ServerException) {
                    ServerException serverException = (ServerException) th;
                }
            }
        }, new Action() { // from class: com.movieboxpro.android.view.activity.movielist.ChooseMovieListCoverActivity$downloadPoster$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new Consumer() { // from class: com.movieboxpro.android.view.activity.movielist.ChooseMovieListCoverActivity$downloadPoster$$inlined$transformSubscribe$default$4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Disposable it) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                ChooseMovieListCoverActivity.this.showLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadPoster$lambda-5  reason: not valid java name */
    public static final File m388downloadPoster$lambda5(ChooseMovieListCoverActivity this$0, int i, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        File file = Glide.with((FragmentActivity) this$0).asFile().load(it).submit().get();
        String str = Constant.DIR_CROP;
        this$0.imageFileMap.put(i, new File(str, System.currentTimeMillis() + ".jpg"));
        return file;
    }

    private final Uri getImageContentUri(Context context, File file) {
        String absolutePath = file.getAbsolutePath();
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{absolutePath}, null);
        if (query != null && query.moveToFirst()) {
            int i = query.getInt(query.getColumnIndex("_id"));
            Uri parse = Uri.parse("content://media/external/images/media");
            query.close();
            return Uri.withAppendedPath(parse, Intrinsics.stringPlus("", Integer.valueOf(i)));
        } else if (file.exists()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", absolutePath);
            return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            return null;
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        this.adapter = new CommBaseAdapter<>(R.layout.adapter_poster_item, new ChooseMovieListCoverActivity$initData$1(this), getIntent().getStringArrayListExtra("data"));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
        CommonExtKt.initGridAndMargin(recyclerView, this, 3, 30, true);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        CommBaseAdapter<String> commBaseAdapter = this.adapter;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        recyclerView2.setAdapter(commBaseAdapter);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Choose a cover");
        ((FrameLayout) _$_findCachedViewById(R.id.frameLayout)).setBackgroundColor(CommonExtKt.colorInt((Context) this, (int) R.color.color_main));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 1) {
                Uri data = intent == null ? null : intent.getData();
                this.outUri = Uri.parse("file:///" + ((Object) Constant.DIR_CROP) + ((Object) File.separator) + TimeUtils.getFormatedTime("yyyyMMddHHmmss") + ".jpg");
                cropImage(data);
            } else if (i == 69) {
                int size = this.imageFileMap.size();
                for (int i3 = 0; i3 < size; i3++) {
                    this.imageFileMap.valueAt(i3).delete();
                }
                Intent intent2 = new Intent();
                intent2.putExtra("uri", this.outUri);
                setResult(-1, intent2);
                finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseSimpleActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        int size = this.imageFileMap.size();
        for (int i = 0; i < size; i++) {
            this.imageFileMap.valueAt(i).delete();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cropImage(Uri uri) {
        Intrinsics.checkNotNull(uri);
        Uri uri2 = this.outUri;
        Intrinsics.checkNotNull(uri2);
        UCrop.of(uri, uri2).withAspectRatio(375.0f, 150.0f).withMaxResultSize(1000, 1000).start(this);
    }

    /* compiled from: ChooseMovieListCoverActivity.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/movielist/ChooseMovieListCoverActivity$Companion;", "", "()V", "REQUEST_CHOOSE_IMAGE", "", "REQUEST_CROP", TtmlNode.START, "", "context", "Landroid/app/Activity;", "urls", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "requestCode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity context, ArrayList<String> urls, int i) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(urls, "urls");
            Intent intent = new Intent(context, ChooseMovieListCoverActivity.class);
            intent.putStringArrayListExtra("data", urls);
            context.startActivityForResult(intent, i);
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
