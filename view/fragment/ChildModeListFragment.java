package com.movieboxpro.android.view.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.RefreshChildModeListLiveData;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChildModeListFragment.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\nH\u0014J\u0012\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003H\u0014J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0014J\u0018\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0002H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u001eH\u0014J\b\u0010\u001f\u001a\u00020 H\u0014J\u001a\u0010!\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010\u00142\u0006\u0010#\u001a\u00020\u0019H\u0002J\u000e\u0010$\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/view/fragment/ChildModeListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "Lcom/movieboxpro/android/model/movie/MovieListModel;", "()V", "edit", "", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "", "initHolder", "helper", "item", "initItemLayout", "", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "remove", "lid", "position", "setEdit", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChildModeListFragment extends BaseListFragment<MovieListModel.MovieListItem, MovieListModel> {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean edit;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_movie_list_image_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        return HttpRequest.Companion.post("Playlists_super_child_list").param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
    }

    public final void setEdit(boolean z) {
        this.edit = z;
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public List<MovieListModel.MovieListItem> getData(MovieListModel model) {
        Intrinsics.checkNotNullParameter(model, "model");
        List<MovieListModel.MovieListItem> list = model.getList();
        Intrinsics.checkNotNullExpressionValue(list, "model.list");
        return list;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        recyclerView.addItemDecoration(new LinearItemDecoration(10, true));
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        this.mClass = MovieListModel.MovieListItem.class;
        this.mPageClass = MovieListModel.class;
        RefreshChildModeListLiveData.Companion.get().observe(this, new Observer() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$ChildModeListFragment$JXDDodxxGEFeOpfUH_4lZNcAOxI
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ChildModeListFragment.m1152getBundle$lambda0(ChildModeListFragment.this, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getBundle$lambda-0  reason: not valid java name */
    public static final void m1152getBundle$lambda0(ChildModeListFragment this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$ChildModeListFragment$ueDh78UoDQMZ9H-QNJhjsaJ7QEU
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChildModeListFragment.m1155onItemClick$lambda1(ChildModeListFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClick$lambda-1  reason: not valid java name */
    public static final void m1155onItemClick$lambda1(ChildModeListFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this$0.mAdapter.getItem(i);
        if (movieListItem != null) {
            MovieListDetailActivity.start(this$0.getContext(), movieListItem.getLid(), movieListItem.getUsername(), (movieListItem.getImgArr() == null || movieListItem.getImgArr().size() < 1) ? "" : movieListItem.getImgArr().get(0), true);
        }
    }

    private final void remove(String str, int i) {
        Observable compose = HttpRequest.Companion.post("Playlists_delete_super_child").param("lid", str).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(compose, "post(\"Playlists_delete_s…tils.rxSchedulerHelper())");
        RxSubscribersKt.subscribeTo$default(compose, new ChildModeListFragment$remove$1(this), null, new ChildModeListFragment$remove$2(this), new ChildModeListFragment$remove$3(this, i), 2, null);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemChildClickListener onItemChildClick() {
        return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$ChildModeListFragment$EOoUSQvI8jEUxjgZXVYIcRjE808
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ChildModeListFragment.m1154onItemChildClick$lambda2(ChildModeListFragment.this, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemChildClick$lambda-2  reason: not valid java name */
    public static final void m1154onItemChildClick$lambda2(ChildModeListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        this$0.remove(((MovieListModel.MovieListItem) this$0.mAdapter.getItem(i)).getLid(), i);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void addOnItemClickViews(BaseQuickAdapter<MovieListModel.MovieListItem, BaseViewHolder> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        adapter.addChildClickViewIds(R.id.flRemove);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        SpanUtils.with((TextView) helper.getView(R.id.tv_name)).append(item.getName().toString()).setFontSize(14, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
        SpanUtils.with((TextView) helper.getView(R.id.tv_num)).append(String.valueOf(item.getCount())).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
        List<String> imgArr = item.getImgArr();
        if (imgArr != null && imgArr.size() > 0) {
            GlideUtils.loadPost(getContext(), imgArr.get(0), (ImageView) helper.getView(R.id.imageView), 4);
        }
        FrameLayout frameLayout = (FrameLayout) helper.getView(R.id.flRemove);
        if (this.edit) {
            CommonExtKt.visible(frameLayout);
        } else {
            CommonExtKt.gone(frameLayout);
        }
    }
}
