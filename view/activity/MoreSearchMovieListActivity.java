package com.movieboxpro.android.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.view.activity.MoreSearchMovieListActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
/* compiled from: MoreSearchMovieListActivity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \t2\u00020\u0001:\u0002\t\nB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreSearchMovieListActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "getLayoutResId", "", "initData", "", "initListener", "initView", "Companion", "MoreSearchMovieListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreSearchMovieListActivity extends BaseSimpleActivity {
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
        return R.layout.activity_more_search_movie_list;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) findViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreSearchMovieListActivity$kA7JuF-tnjY2_p4RGcDX0qZ9zgs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreSearchMovieListActivity.m157initListener$lambda0(MoreSearchMovieListActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m157initListener$lambda0(MoreSearchMovieListActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        FragmentUtils.add(getSupportFragmentManager(), MoreSearchMovieListFragment.Companion.newInstance(getIntent().getStringExtra("keyword")), (int) R.id.fragmentContainer);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText(getIntent().getStringExtra("keyword"));
    }

    /* compiled from: MoreSearchMovieListActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreSearchMovieListActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "keyword", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            int i = 0;
            Pair[] pairArr = {TuplesKt.to("keyword", str)};
            Intent intent = new Intent(context, MoreSearchMovieListActivity.class);
            Bundle bundle = new Bundle(1);
            while (i < 1) {
                Pair pair = pairArr[i];
                i++;
                String str2 = (String) pair.component1();
                Object component2 = pair.component2();
                if (component2 == null) {
                    bundle.putString(str2, null);
                } else if (component2 instanceof Boolean) {
                    bundle.putBoolean(str2, ((Boolean) component2).booleanValue());
                } else if (component2 instanceof Byte) {
                    bundle.putByte(str2, ((Number) component2).byteValue());
                } else if (component2 instanceof Character) {
                    bundle.putChar(str2, ((Character) component2).charValue());
                } else if (component2 instanceof Double) {
                    bundle.putDouble(str2, ((Number) component2).doubleValue());
                } else if (component2 instanceof Float) {
                    bundle.putFloat(str2, ((Number) component2).floatValue());
                } else if (component2 instanceof Integer) {
                    bundle.putInt(str2, ((Number) component2).intValue());
                } else if (component2 instanceof Long) {
                    bundle.putLong(str2, ((Number) component2).longValue());
                } else if (component2 instanceof Short) {
                    bundle.putShort(str2, ((Number) component2).shortValue());
                } else if (component2 instanceof Bundle) {
                    bundle.putBundle(str2, (Bundle) component2);
                } else if (component2 instanceof CharSequence) {
                    bundle.putCharSequence(str2, (CharSequence) component2);
                } else if (component2 instanceof Parcelable) {
                    bundle.putParcelable(str2, (Parcelable) component2);
                } else if (component2 instanceof boolean[]) {
                    bundle.putBooleanArray(str2, (boolean[]) component2);
                } else if (component2 instanceof byte[]) {
                    bundle.putByteArray(str2, (byte[]) component2);
                } else if (component2 instanceof char[]) {
                    bundle.putCharArray(str2, (char[]) component2);
                } else if (component2 instanceof double[]) {
                    bundle.putDoubleArray(str2, (double[]) component2);
                } else if (component2 instanceof float[]) {
                    bundle.putFloatArray(str2, (float[]) component2);
                } else if (component2 instanceof int[]) {
                    bundle.putIntArray(str2, (int[]) component2);
                } else if (component2 instanceof long[]) {
                    bundle.putLongArray(str2, (long[]) component2);
                } else if (component2 instanceof short[]) {
                    bundle.putShortArray(str2, (short[]) component2);
                } else if (component2 instanceof Object[]) {
                    Class<?> componentType = component2.getClass().getComponentType();
                    Intrinsics.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                        }
                        bundle.putParcelableArray(str2, (Parcelable[]) component2);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                        }
                        bundle.putStringArray(str2, (String[]) component2);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                        }
                        bundle.putCharSequenceArray(str2, (CharSequence[]) component2);
                    } else if (Serializable.class.isAssignableFrom(componentType)) {
                        bundle.putSerializable(str2, (Serializable) component2);
                    } else {
                        String canonicalName = componentType.getCanonicalName();
                        throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str2 + Typography.quote);
                    }
                } else if (component2 instanceof Serializable) {
                    bundle.putSerializable(str2, (Serializable) component2);
                } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                    bundle.putBinder(str2, (IBinder) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                    bundle.putSize(str2, (Size) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                    bundle.putSizeF(str2, (SizeF) component2);
                } else {
                    String canonicalName2 = component2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str2 + Typography.quote);
                }
            }
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    /* compiled from: MoreSearchMovieListActivity.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0014J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0002H\u0014J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreSearchMovieListActivity$MoreSearchMovieListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "", "()V", "keyword", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class MoreSearchMovieListFragment extends BaseListFragment<MovieListModel.MovieListItem, String> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private String keyword;

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
            return R.layout.adapter_more_movie_list_image_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return Http.getService().Search(API.BASE_URL, API.Search.SEARCH4, "playlists", this.keyword, App.getUserData().uid_v2, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), Integer.valueOf(PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            recyclerView.addItemDecoration(new LinearItemDecoration(16, true));
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreSearchMovieListActivity$MoreSearchMovieListFragment$2eqr_gwdJjLuxfGPKQjYO9s5qGc
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MoreSearchMovieListActivity.MoreSearchMovieListFragment.m159onItemClick$lambda2(MoreSearchMovieListActivity.MoreSearchMovieListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-2  reason: not valid java name */
        public static final void m159onItemClick$lambda2(MoreSearchMovieListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            String str;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) this$0.mAdapter.getItem(i);
            Context context = this$0.getContext();
            if (context == null) {
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
            String lid = movieListItem.getLid();
            MovieListDetailActivity.start(context, lid != null ? lid : "", movieListItem.getUsername(), str);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = MovieListModel.MovieListItem.class;
            this.keyword = bundle == null ? null : bundle.getString("keyword");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, MovieListModel.MovieListItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = (TextView) helper.getView(R.id.tv_num);
            TextView textView2 = (TextView) helper.getView(R.id.tv_name);
            if (!item.isMore()) {
                textView2.setVisibility(0);
                textView.setVisibility(0);
                SpanUtils.with(textView).append(String.valueOf(item.getCount())).setFontSize(12, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
                List<String> imgArr = item.getImgArr();
                if (imgArr != null && imgArr.size() > 0) {
                    GlideUtils.loadPost(getContext(), imgArr.get(0), (ImageView) helper.getView(R.id.imageView), 4);
                }
                SpanUtils.with(textView2).append(item.getName().toString()).setFontSize(14, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
                return;
            }
            GlideUtils.load(getContext(), (int) R.mipmap.ic_movielist_more, (ImageView) helper.getView(R.id.imageView));
            textView2.setVisibility(8);
            textView.setVisibility(8);
        }

        /* compiled from: MoreSearchMovieListActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreSearchMovieListActivity$MoreSearchMovieListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/MoreSearchMovieListActivity$MoreSearchMovieListFragment;", "keyword", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final MoreSearchMovieListFragment newInstance(String str) {
                MoreSearchMovieListFragment moreSearchMovieListFragment = new MoreSearchMovieListFragment();
                moreSearchMovieListFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("keyword", str)));
                return moreSearchMovieListFragment;
            }
        }
    }
}
