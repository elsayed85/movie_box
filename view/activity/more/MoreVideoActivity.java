package com.movieboxpro.android.view.activity.more;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: MoreVideoActivity.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 &2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001&B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0014J\b\u0010\u0015\u001a\u00020\u000bH\u0002J\u0018\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u0002H\u0014J\b\u0010\u0019\u001a\u00020\u000eH\u0014J\u0012\u0010\u001a\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u001eH\u0014J\u0010\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020$H\u0014J\b\u0010%\u001a\u00020\u000bH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/view/activity/more/MoreVideoActivity;", "Lcom/movieboxpro/android/base/BaseListActivity;", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "", "()V", "itemDecoration", "Lcom/movieboxpro/android/view/widget/GridSpacingItemDecoration;", "kProgressHUD", "Lcom/kaopiz/kprogresshud/KProgressHUD;", IjkMediaMeta.IJKM_KEY_TYPE, "dislikeMovie", "", "item", "position", "", "getIntentData", "intent", "Landroid/content/Intent;", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "hideLoadingView", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isOpenLoadMore", "", "isVerticalLayout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "showLoadingView", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreVideoActivity extends BaseListActivity<Homelist.Typelist, String> {
    public static final Companion Companion = new Companion(null);
    private GridSpacingItemDecoration itemDecoration;
    private KProgressHUD kProgressHUD;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String type = "";

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

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

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int initItemLayout() {
        return R.layout.adapter_more_video_item;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isVerticalLayout() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected Observable<String> getServiceData() {
        return Http.getService().Home_list(API.BASE_URL, API.Common.HOME_LIST_V2, App.getUserData().uid_v2, this.type, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), 0, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.more.-$$Lambda$MoreVideoActivity$9jkMQ5lJWR4UXi4YkTnzyXLOqms
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MoreVideoActivity.m384onItemClick$lambda0(MoreVideoActivity.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-0  reason: not valid java name */
    public static final void m384onItemClick$lambda0(MoreVideoActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        Homelist.Typelist typelist = (Homelist.Typelist) this$0.mAdapter.getItem(i);
        boolean z = false;
        if (typelist != null && typelist.box_type == 1) {
            z = true;
        }
        if (z) {
            MovieDetailActivity.start(this$0, typelist.id, typelist.poster);
        } else {
            TvDetailActivity.start(this$0, typelist == null ? null : typelist.id, typelist == null ? null : typelist.banner_mini, typelist != null ? typelist.poster : null);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        String stringExtra;
        this.mClass = Homelist.Typelist.class;
        this.mPageSize = 15;
        String str = "";
        if (intent != null && (stringExtra = intent.getStringExtra(IjkMediaMeta.IJKM_KEY_TYPE)) != null) {
            str = stringExtra;
        }
        this.type = str;
        this.mTvTitle.setText(intent == null ? null : intent.getStringExtra("title"));
        CommonExtKt.onMobEvent(this, "EnterHomeMore");
        EventUtils.event("首页进入更多");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initRecyclerView(RecyclerView recyclerView) {
        this.mAdapter.addChildClickViewIds(R.id.ivDislike);
        this.mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.more.-$$Lambda$MoreVideoActivity$NtMOtFZp3FK5cpQGnktu6J5PsuU
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MoreVideoActivity.m381initRecyclerView$lambda2(MoreVideoActivity.this, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initRecyclerView$lambda-2  reason: not valid java name */
    public static final void m381initRecyclerView$lambda2(final MoreVideoActivity this$0, BaseQuickAdapter noName_0, View noName_1, final int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        new MsgHintDialog.Builder(this$0).setContent("Dislike this movie?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.more.-$$Lambda$MoreVideoActivity$Ll2OodPWaSG-3Wstj1HSQBK8v0A
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                MoreVideoActivity.m382initRecyclerView$lambda2$lambda1(MoreVideoActivity.this, i);
            }
        }).setPositiveText("Dislike").create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initRecyclerView$lambda-2$lambda-1  reason: not valid java name */
    public static final void m382initRecyclerView$lambda2$lambda1(MoreVideoActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Homelist.Typelist item = (Homelist.Typelist) this$0.mAdapter.getItem(i);
        Intrinsics.checkNotNullExpressionValue(item, "item");
        this$0.dislikeMovie(item, i);
    }

    private final void dislikeMovie(Homelist.Typelist typelist, int i) {
        RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this, "Movie_dislike").param("mid", typelist.id).param("box_type", (Object) 1).asMsg(), new MoreVideoActivity$dislikeMovie$1(this), null, new MoreVideoActivity$dislikeMovie$2(this), null, new MoreVideoActivity$dislikeMovie$3(this, i), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int gridLayoutSpan() {
        if (CommonUtils.isTablet()) {
            GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(5, DensityUtils.dp2px(App.getContext(), 10.0f), true);
            this.itemDecoration = gridSpacingItemDecoration;
            if (gridSpacingItemDecoration == null) {
                return 5;
            }
            this.mRecyclerView.addItemDecoration(gridSpacingItemDecoration);
            return 5;
        }
        GridSpacingItemDecoration gridSpacingItemDecoration2 = new GridSpacingItemDecoration(3, DensityUtils.dp2px(App.getContext(), 10.0f), true);
        this.itemDecoration = gridSpacingItemDecoration2;
        if (gridSpacingItemDecoration2 != null) {
            this.mRecyclerView.addItemDecoration(gridSpacingItemDecoration2);
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoadingView() {
        if (isFinishing()) {
            return;
        }
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoadingView() {
        if (isFinishing()) {
            return;
        }
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder helper, Homelist.Typelist item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
        SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.tvTimeUpdate);
        TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
        GlideUtils.loadPortraitGifHolder(this, item.poster, (ImageView) helper.getView(R.id.ivPoster));
        ImageView imageView2 = (ImageView) helper.getView(R.id.ivDislike);
        if (Intrinsics.areEqual(this.type, "maybe_like")) {
            CommonExtKt.visible(imageView2);
        } else {
            CommonExtKt.gone(imageView2);
        }
        boolean z = false;
        if (item.box_type == 1) {
            CommonExtKt.gone(textView);
            CommonExtKt.gone(slantedTextView);
            String str = item.quality_tag_new;
            if (str == null || str.length() == 0) {
                CommonExtKt.gone(imageView);
            } else {
                CommonExtKt.visible(imageView);
                imageView.setImageResource(CommonUtils.getMovieTag(item.quality_tag_new));
            }
        } else {
            CommonExtKt.gone(imageView);
            String str2 = item.season_episode;
            if (str2 == null || str2.length() == 0) {
                CommonExtKt.gone(textView);
            } else {
                CommonExtKt.visible(textView);
                textView.setText(item.season_episode);
            }
            String str3 = item.update_title;
            if (str3 == null || str3.length() == 0) {
                CommonExtKt.gone(slantedTextView);
            } else {
                CommonExtKt.visible(slantedTextView);
                slantedTextView.setText(item.update_title);
            }
        }
        ImageView imageView3 = (ImageView) helper.getView(R.id.ivTomato);
        TextView textView2 = (TextView) helper.getView(R.id.tvTomatoMeter);
        imageView3.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
        if (item.getTomato_meter() == 0) {
            CommonExtKt.gone(textView2);
            CommonExtKt.gone(imageView3);
        } else {
            CommonExtKt.visible(textView2);
            CommonExtKt.visible(imageView3);
            StringBuilder sb = new StringBuilder();
            sb.append(item.getTomato_meter());
            sb.append('%');
            CommonExtKt.textShadow$default(textView2, sb.toString(), 0, 0, 6, null);
        }
        TextView textView3 = (TextView) helper.getView(R.id.tvImdbRating);
        String imdb_rating = item.getImdb_rating();
        if ((imdb_rating == null || imdb_rating.length() == 0) ? true : true) {
            CommonExtKt.textShadow$default(textView3, "-.-", 0, 0, 6, null);
            return;
        }
        String imdb_rating2 = item.getImdb_rating();
        if (imdb_rating2 == null) {
            imdb_rating2 = "";
        }
        CommonExtKt.textShadow$default(textView3, imdb_rating2, 0, 0, 6, null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == 2) {
            RecyclerView mRecyclerView = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView, this.mAdapter, 5);
        } else if (getResources().getConfiguration().orientation == 1) {
            RecyclerView mRecyclerView2 = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView2, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView2, this.mAdapter, 3);
        }
    }

    /* compiled from: MoreVideoActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/more/MoreVideoActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", IjkMediaMeta.IJKM_KEY_TYPE, "", "title", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String type, String title) {
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(title, "title");
            Intent intent = new Intent(context, MoreVideoActivity.class);
            intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, type);
            intent.putExtra("title", title);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
