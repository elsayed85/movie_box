package com.movieboxpro.android.view.activity.review;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.OnSetResultListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.view.fragment.movielist.AddFavoriteFragment;
import com.movieboxpro.android.view.fragment.movielist.AddHistoryFragment;
import com.movieboxpro.android.view.fragment.movielist.AddSearchFragment;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: AltVideosActivity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u00012\u00020\u0002:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\b\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0005H\u0016J\u0012\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/AltVideosActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "Landroid/content/DialogInterface$OnDismissListener;", "()V", "finish", "", "getLayoutResId", "", "initData", "initListener", "initView", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AltVideosActivity extends BaseSimpleActivity implements DialogInterface.OnDismissListener {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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
        return R.layout.activity_alt_video;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    /* compiled from: AltVideosActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/review/AltVideosActivity$Companion;", "", "()V", TtmlNode.START, "", "activity", "Landroid/app/Activity;", IjkMediaMeta.IJKM_KEY_TYPE, "", "requestCode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Activity activity, int i, int i2) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intent intent = new Intent(activity, AltVideosActivity.class);
            intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, i);
            activity.startActivityForResult(intent, i2);
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        int intExtra = getIntent().getIntExtra(IjkMediaMeta.IJKM_KEY_TYPE, 1);
        if (intExtra == 1) {
            AddSearchFragment newInstance = AddSearchFragment.newInstance(new ArrayList());
            newInstance.setResultListener(new OnSetResultListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltVideosActivity$G10YdypgrNWeeVY5EMYtmc6eAe4
                @Override // com.movieboxpro.android.OnSetResultListener
                public final void onSetResult(List list) {
                    AltVideosActivity.m428initData$lambda0(AltVideosActivity.this, list);
                }
            });
            newInstance.show(getSupportFragmentManager(), "AddSearchFragment");
        } else if (intExtra == 2) {
            AddHistoryFragment addHistoryFragment = new AddHistoryFragment();
            addHistoryFragment.setNormalFilmModels(new ArrayList());
            addHistoryFragment.setListener(new OnSetResultListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltVideosActivity$KF5M-8Zc8zcZ28F3fNH6l2ZZVW8
                @Override // com.movieboxpro.android.OnSetResultListener
                public final void onSetResult(List list) {
                    AltVideosActivity.m429initData$lambda1(AltVideosActivity.this, list);
                }
            });
            addHistoryFragment.show(getSupportFragmentManager(), "AddHistoryFragment");
        } else if (intExtra != 3) {
        } else {
            AddFavoriteFragment addFavoriteFragment = new AddFavoriteFragment();
            addFavoriteFragment.setNormalFilmModels(new ArrayList());
            addFavoriteFragment.setListener(new OnSetResultListener() { // from class: com.movieboxpro.android.view.activity.review.-$$Lambda$AltVideosActivity$SyY2xFTIfMsHBB2ensCAF3SPYDA
                @Override // com.movieboxpro.android.OnSetResultListener
                public final void onSetResult(List list) {
                    AltVideosActivity.m430initData$lambda2(AltVideosActivity.this, list);
                }
            });
            addFavoriteFragment.show(getSupportFragmentManager(), "AddFavoriteFragment");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0  reason: not valid java name */
    public static final void m428initData$lambda0(AltVideosActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("data", new ArrayList<>(list));
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m429initData$lambda1(AltVideosActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("data", new ArrayList<>(list));
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-2  reason: not valid java name */
    public static final void m430initData$lambda2(AltVideosActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("data", new ArrayList<>(list));
        this$0.setResult(-1, intent);
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity, android.app.Activity
    public void finish() {
        super.finish();
    }
}
