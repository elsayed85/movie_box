package com.movieboxpro.android.view.fragment.movielist;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListActivity;
import com.movieboxpro.android.event.MovieListSelectedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.GridInsetDecoration;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
/* compiled from: CompilationsListActivity.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001b2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u001a\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0014J\u0012\u0010\u0015\u001a\u00020\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0006H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/fragment/movielist/CompilationsListActivity;", "Lcom/movieboxpro/android/base/BaseListActivity;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "", "()V", "choose", "", "id", "getIntentData", "", "intent", "Landroid/content/Intent;", "getServiceData", "Lio/reactivex/Observable;", "gridLayoutSpan", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isVerticalLayout", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CompilationsListActivity extends BaseListActivity<MovieListModel.MovieListItem, String> {
    public static final Companion Companion = new Companion(null);
    private boolean choose;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String id = "";

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
        return R.layout.adapter_compilations_list_item;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected boolean isVerticalLayout() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected Observable<String> getServiceData() {
        return Http.getService().Playlists_compilations_detail(API.BASE_URL, "Playlists_compilations_detail", this.id, this.mCurrentPage, this.mPageSize);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initRecyclerView(RecyclerView recyclerView) {
        if (CommonUtils.isTablet()) {
            if (recyclerView == null) {
                return;
            }
            recyclerView.addItemDecoration(new GridInsetDecoration(16, 0, true));
        } else if (recyclerView == null) {
        } else {
            recyclerView.addItemDecoration(new GridInsetDecoration(16, 0, true));
        }
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$CompilationsListActivity$phzB1O3L2a66klmUktvs6hbXYiw
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CompilationsListActivity.m1258onItemClick$lambda1(CompilationsListActivity.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m1258onItemClick$lambda1(CompilationsListActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this$0.mAdapter.getItem(i);
        if (movieListItem == null) {
            return;
        }
        List<String> imgArr = movieListItem.getImgArr();
        Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
        if (!imgArr.isEmpty()) {
            String str2 = movieListItem.getImgArr().get(0);
            Intrinsics.checkNotNullExpressionValue(str2, "item.imgArr[0]");
            str = str2;
        } else {
            str = "";
        }
        if (this$0.choose) {
            EventBus.getDefault().post(new MovieListSelectedEvent(movieListItem));
            this$0.finish();
            return;
        }
        MovieListDetailActivity.start(this$0, movieListItem.getLid(), movieListItem.getUsername(), str, this$0.getIntent().getBooleanExtra("fromAdd", false));
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected int gridLayoutSpan() {
        return CommonUtils.isTablet() ? 2 : 1;
    }

    @Override // com.movieboxpro.android.base.BaseListActivity
    protected void getIntentData(Intent intent) {
        String stringExtra;
        this.mClass = MovieListModel.MovieListItem.class;
        String str = "";
        if (intent != null && (stringExtra = intent.getStringExtra("id")) != null) {
            str = stringExtra;
        }
        this.id = str;
        this.mTvTitle.setText(intent == null ? null : intent.getStringExtra("title"));
        this.choose = intent != null ? intent.getBooleanExtra("choose", false) : false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListActivity
    public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem movieListItem) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        if (movieListItem == null) {
            return;
        }
        ImageView imageView = (ImageView) helper.getView(R.id.imageView);
        TextView textView = (TextView) helper.getView(R.id.tvName);
        TextView textView2 = (TextView) helper.getView(R.id.tvNum);
        if (movieListItem.getImgArr() != null) {
            List<String> imgArr = movieListItem.getImgArr();
            Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
            if (!imgArr.isEmpty()) {
                GlideUtils.loadWithCorner(this, movieListItem.getImgArr().get(0), imageView, DensityUtils.dp2px(App.getContext(), 4.0f));
            }
        }
        String name = movieListItem.getName();
        Intrinsics.checkNotNullExpressionValue(name, "item.name");
        CommonExtKt.textShadow$default(textView, name, 10, 0, 4, null);
        CommonExtKt.textShadow$default(textView2, String.valueOf(movieListItem.getCount()), 10, 0, 4, null);
        List<String> movieArr = movieListItem.getMovieArr();
        int size = movieArr == null ? 0 : movieArr.size();
        if (size == 1) {
            helper.setText(R.id.tvOne, Intrinsics.stringPlus("1.", movieArr.get(0)));
        } else if (size == 2) {
            helper.setText(R.id.tvOne, Intrinsics.stringPlus("1.", movieArr.get(0)));
            helper.setText(R.id.tvTwo, Intrinsics.stringPlus("2.", movieArr.get(1)));
        } else if (size == 3) {
            helper.setText(R.id.tvOne, Intrinsics.stringPlus("1.", movieArr.get(0)));
            helper.setText(R.id.tvTwo, Intrinsics.stringPlus("2.", movieArr.get(1)));
            helper.setText(R.id.tvThree, Intrinsics.stringPlus("3.", movieArr.get(2)));
        } else {
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: CompilationsListActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ0\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/fragment/movielist/CompilationsListActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "id", "", "title", "fromAdd", "", "choose", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String id, String title) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intent intent = new Intent(context, CompilationsListActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }

        public final void start(Context context, String id, String title, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(title, "title");
            Intent intent = new Intent(context, CompilationsListActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("title", title);
            intent.putExtra("choose", z2);
            intent.putExtra("fromAdd", z);
            if (context == null) {
                return;
            }
            context.startActivity(intent);
        }
    }
}
