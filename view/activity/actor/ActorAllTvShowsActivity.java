package com.movieboxpro.android.view.activity.actor;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.model.ActorDetailModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActorAllTvShowsActivity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u001c\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/ActorAllTvShowsActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/ActorDetailModel$ActorTvShow;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getLayoutResId", "", "initData", "", "initListener", "initView", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ActorAllTvShowsActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> adapter;

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
        return R.layout.activity_actor_all_tv_shows;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorAllTvShowsActivity$Ucbtqh4eWrhFtIKzWIakyhZbSLE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActorAllTvShowsActivity.m310initListener$lambda0(ActorAllTvShowsActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m310initListener$lambda0(ActorAllTvShowsActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        final ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra("data");
        if (parcelableArrayListExtra == null) {
            parcelableArrayListExtra = new ArrayList();
        }
        this.adapter = new BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder>(parcelableArrayListExtra) { // from class: com.movieboxpro.android.view.activity.actor.ActorAllTvShowsActivity$initData$1
            final /* synthetic */ ArrayList<ActorDetailModel.ActorTvShow> $list;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.simple_tv_item, parcelableArrayListExtra);
                this.$list = parcelableArrayListExtra;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, ActorDetailModel.ActorTvShow item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.loadPortraitGifHolder(getContext(), item.getPoster(), (ImageView) holder.getView(R.id.tv_item_poster));
                TextView textView = (TextView) holder.getView(R.id.tv_item_season);
                if (!TextUtils.isEmpty(item.getSeason_episode())) {
                    textView.setText(item.getSeason_episode());
                    CommonExtKt.visible(textView);
                } else {
                    CommonExtKt.gone(textView);
                }
                TextView textView2 = (TextView) holder.getView(R.id.tvImdbRating);
                String imdb_rating = item.getImdb_rating();
                if (imdb_rating == null || imdb_rating.length() == 0) {
                    CommonExtKt.textShadow$default(textView2, "-.-", 0, 0, 6, null);
                    return;
                }
                String imdb_rating2 = item.getImdb_rating();
                if (imdb_rating2 == null) {
                    imdb_rating2 = "";
                }
                CommonExtKt.textShadow$default(textView2, imdb_rating2, 0, 0, 6, null);
            }
        };
        if (CommonUtils.isTablet()) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).setLayoutManager(new GridLayoutManager(this, 5));
        } else {
            ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).setLayoutManager(new GridLayoutManager(this, 3));
        }
        ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).addItemDecoration(new GridSpacingItemDecoration(10, true));
        ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).setAdapter(this.adapter);
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText(getIntent().getStringExtra("title"));
        BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            return;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.actor.-$$Lambda$ActorAllTvShowsActivity$BPdPGuc_vZVc2B1NmPnvuY0onvA
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                ActorAllTvShowsActivity.m309initData$lambda1(ActorAllTvShowsActivity.this, parcelableArrayListExtra, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1  reason: not valid java name */
    public static final void m309initData$lambda1(ActorAllTvShowsActivity this$0, ArrayList list, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "$list");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        TvDetailActivity.start(this$0, ((ActorDetailModel.ActorTvShow) list.get(i)).getId(), ((ActorDetailModel.ActorTvShow) list.get(i)).getBanner_mini(), ((ActorDetailModel.ActorTvShow) list.get(i)).getPoster());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == 2) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).setLayoutManager(new GridLayoutManager(this, 5));
            BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter = this.adapter;
            if (baseQuickAdapter == null) {
                return;
            }
            baseQuickAdapter.notifyDataSetChanged();
        } else if (getResources().getConfiguration().orientation == 1) {
            ((RecyclerView) _$_findCachedViewById(R.id.rvTvShows)).setLayoutManager(new GridLayoutManager(this, 3));
            BaseQuickAdapter<ActorDetailModel.ActorTvShow, BaseViewHolder> baseQuickAdapter2 = this.adapter;
            if (baseQuickAdapter2 == null) {
                return;
            }
            baseQuickAdapter2.notifyDataSetChanged();
        }
    }

    /* compiled from: ActorAllTvShowsActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/ActorAllTvShowsActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "title", "", "list", "", "Lcom/movieboxpro/android/model/ActorDetailModel$ActorTvShow;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String title, List<? extends ActorDetailModel.ActorTvShow> list) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(list, "list");
            Intent intent = new Intent(context, ActorAllTvShowsActivity.class);
            intent.putExtra("title", title);
            intent.putParcelableArrayListExtra("data", new ArrayList<>(list));
            context.startActivity(intent);
        }
    }
}
