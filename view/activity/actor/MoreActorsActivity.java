package com.movieboxpro.android.view.activity.actor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: MoreActorsActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u001c\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u0017\u001a\u00020\u0016H\u0014J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0014R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/MoreActorsActivity;", "Lcom/movieboxpro/android/base/BaseListActivity;", "Lcom/movieboxpro/android/model/ActorModel;", "", "()V", "id", "keyword", IjkMediaMeta.IJKM_KEY_TYPE, "getIntentData", "", "intent", "Landroid/content/Intent;", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "isOpenLoadMore", "", "isVerticalLayout", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreActorsActivity extends BaseListActivity<ActorModel, String> {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String type = "";
    private String id = "";
    private String keyword = "";

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
        return R.layout.adapter_actor_list_item;
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
        String str = this.type;
        int hashCode = str.hashCode();
        if (hashCode != -906336856) {
            if (hashCode != 3714) {
                if (hashCode == 104087344 && str.equals("movie")) {
                    return Http.getService().MovieActors(API.BASE_URL, API.Movie.MOVIE_ACTORS, this.id, this.mCurrentPage, this.mPageSize);
                }
            } else if (str.equals("tv")) {
                return Http.getService().TvActors(API.BASE_URL, API.Tv.TV_ACTORS, this.id, this.mCurrentPage, this.mPageSize);
            }
        } else if (str.equals(FirebaseAnalytics.Event.SEARCH)) {
            return Http.getService().Search(API.BASE_URL, API.Search.SEARCH4, "actor", this.keyword, App.isLogin() ? App.getUserData().uid_v2 : "", String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), Integer.valueOf(PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0));
        }
        return Http.getService().MovieActors(API.BASE_URL, API.Movie.MOVIE_ACTORS, this.id, this.mCurrentPage, this.mPageSize);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$MoreActorsActivity$B4dJfaoEJhCAbcQ5XAjQpzj2dLQ
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MoreActorsActivity.m319onItemClick$lambda1(MoreActorsActivity.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m319onItemClick$lambda1(MoreActorsActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String actor_id;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        ActorModel actorModel = (ActorModel) this$0.mAdapter.getItem(i);
        if (actorModel == null || (actor_id = actorModel.getActor_id()) == null) {
            return;
        }
        ActorDetailActivity.Companion.start(this$0, actor_id);
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        String stringExtra;
        String stringExtra2;
        String stringExtra3;
        if (CommonUtils.isTablet()) {
            this.mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(6, DensityUtils.dp2px(this, 10.0f), true));
        } else {
            this.mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, DensityUtils.dp2px(this, 10.0f), true));
        }
        this.mClass = ActorModel.class;
        String str = "";
        if (intent == null || (stringExtra = intent.getStringExtra("id")) == null) {
            stringExtra = "";
        }
        this.id = stringExtra;
        if (intent == null || (stringExtra2 = intent.getStringExtra(IjkMediaMeta.IJKM_KEY_TYPE)) == null) {
            stringExtra2 = "";
        }
        this.type = stringExtra2;
        if (intent != null && (stringExtra3 = intent.getStringExtra("keyword")) != null) {
            str = stringExtra3;
        }
        this.keyword = str;
        this.mTvTitle.setText(intent == null ? null : intent.getStringExtra("title"));
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 6 : 3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder baseViewHolder, ActorModel actorModel) {
        if (baseViewHolder != null) {
            baseViewHolder.setText(R.id.tvName, actorModel == null ? null : actorModel.getName());
        }
        if (this.keyword.length() > 0) {
            if (baseViewHolder != null) {
                baseViewHolder.setText(R.id.tvJob, actorModel == null ? null : actorModel.getJob());
            }
        } else if (baseViewHolder != null) {
            baseViewHolder.setText(R.id.tvJob, actorModel == null ? null : actorModel.getType());
        }
        AppCompatTextView appCompatTextView = baseViewHolder == null ? null : (AppCompatTextView) baseViewHolder.getView(R.id.tvNameFirst);
        ImageView imageView = baseViewHolder == null ? null : (ImageView) baseViewHolder.getView(R.id.ivAvatar);
        if (actorModel != null && actorModel.isMore()) {
            GlideUtils.load((Activity) this, (int) R.mipmap.ic_actors_more, imageView);
            return;
        }
        if (!TextUtils.isEmpty(actorModel == null ? null : actorModel.getAvatar())) {
            if (appCompatTextView != null) {
                CommonExtKt.gone(appCompatTextView);
            }
            if (imageView != null) {
                CommonExtKt.visible(imageView);
            }
            GlideUtils.loadWithCircle(this, actorModel != null ? actorModel.getAvatar() : null, imageView, 88, (int) R.drawable.image_loading_placeholer);
            return;
        }
        if (appCompatTextView != null) {
            CommonExtKt.visible(appCompatTextView);
        }
        if (appCompatTextView != null) {
            appCompatTextView.setText(CommonUtils.getNameFirstLetter(actorModel != null ? actorModel.getName() : null));
        }
        if (imageView == null) {
            return;
        }
        CommonExtKt.gone(imageView);
    }

    /* compiled from: MoreActorsActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000b\u001a\u00020\bH\u0007¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/MoreActorsActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", IjkMediaMeta.IJKM_KEY_TYPE, "title", "keyword", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void start(Context context, String id, String type, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(type, "type");
            start$default(this, context, id, type, str, null, 16, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void start$default(Companion companion, Context context, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 16) != 0) {
                str4 = "";
            }
            companion.start(context, str, str2, str3, str4);
        }

        public final void start(Context context, String id, String type, String str, String keyword) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(keyword, "keyword");
            Intent intent = new Intent(context, MoreActorsActivity.class);
            intent.putExtra("id", id);
            intent.putExtra(IjkMediaMeta.IJKM_KEY_TYPE, type);
            intent.putExtra("title", str);
            intent.putExtra("keyword", keyword);
            context.startActivity(intent);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == 2) {
            RecyclerView mRecyclerView = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView, this.mAdapter, 6);
        } else if (getResources().getConfiguration().orientation == 1) {
            RecyclerView mRecyclerView2 = this.mRecyclerView;
            Intrinsics.checkNotNullExpressionValue(mRecyclerView2, "mRecyclerView");
            CommonExtKt.resetSpanCount(mRecyclerView2, this.mAdapter, 3);
        }
    }
}
