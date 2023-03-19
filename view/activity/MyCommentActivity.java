package com.movieboxpro.android.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.tabs.TabLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.ImageViewerPopupView;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopup.interfaces.OnSrcViewUpdateListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.AtItem;
import com.movieboxpro.android.model.Comment;
import com.movieboxpro.android.model.ImageItem;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.model.ReviewResponse;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.XpopImageLoader;
import com.movieboxpro.android.view.activity.MyCommentActivity;
import com.movieboxpro.android.view.activity.actor.ActorDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.UserAvatarView;
import com.movieboxpro.android.view.widget.textview.QMUISpanTouchFixTextView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: MyCommentActivity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/MyCommentActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "getLayoutResId", "", "initData", "", "initListener", "initView", "MyCommentListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyCommentActivity extends BaseSimpleActivity {
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
        return R.layout.activity_my_comment;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) findViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyCommentActivity$UBriz-4tVvoVui3coLD9WtFWTF4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyCommentActivity.m175initListener$lambda0(MyCommentActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m175initListener$lambda0(MyCommentActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ArrayList arrayList = new ArrayList();
        arrayList.add(MyCommentListFragment.Companion.newInstance("movie"));
        arrayList.add(MyCommentListFragment.Companion.newInstance("playlist"));
        arrayList.add(MyCommentListFragment.Companion.newInstance("actor"));
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), arrayList, new String[]{"Movies&Tv Shows", "Movielists", "Actors"});
        viewPager.setOffscreenPageLimit(arrayList.size());
        viewPager.setAdapter(tabLayoutPagerAdapter);
        ((TabLayout) findViewById(R.id.tabLayout)).setupWithViewPager(viewPager);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("My Comments");
    }

    /* compiled from: MyCommentActivity.kt */
    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 *2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001*B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\nH\u0014J\"\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\u0012\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0003H\u0014J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u0002H\u0014J\b\u0010\u001c\u001a\u00020\u000eH\u0014J\"\u0010\u001d\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0014J\b\u0010!\u001a\u00020\"H\u0014J2\u0010#\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010$\u001a\u0004\u0018\u00010%2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/movieboxpro/android/view/activity/MyCommentActivity$MyCommentListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/ReviewItem;", "Lcom/movieboxpro/android/model/ReviewResponse;", "()V", IjkMediaMeta.IJKM_KEY_TYPE, "", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "deleteComment", "position", "", "commentId", "boxType", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "item", "initItemLayout", "likeReview", NotificationCompat.CATEGORY_STATUS, "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "toImageShow", "view", "Landroid/view/View;", "images", "", "imageView", "Landroid/widget/ImageView;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class MyCommentListFragment extends BaseListFragment<ReviewItem, ReviewResponse> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private String type = "";

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
            return R.layout.adapter_my_reviews_detail_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post(this, "User_comment_list").param(IjkMediaMeta.IJKM_KEY_TYPE, this.type).param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<ReviewItem> getData(ReviewResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            List<ReviewItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return new ArrayList(list);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            String string;
            this.mClass = ReviewItem.class;
            this.mPageClass = ReviewResponse.class;
            String str = "";
            if (bundle != null && (string = bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE)) != null) {
                str = string;
            }
            this.type = str;
        }

        private final void toImageShow(int i, View view, List<String> list, final ImageView imageView) {
            if (list != null && list.size() == 1) {
                new XPopup.Builder(getContext()).asImageViewer(view instanceof ImageView ? (ImageView) view : null, list.get(0), new XpopImageLoader()).show();
            } else if (view == null) {
            } else {
                XPopup.Builder builder = new XPopup.Builder(getContext());
                if (view == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
                }
                builder.asImageViewer((ImageView) view, i, list, new OnSrcViewUpdateListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyCommentActivity$MyCommentListFragment$Uuu36prorH1tDwfXLQgRQUz4kEs
                    @Override // com.lxj.xpopup.interfaces.OnSrcViewUpdateListener
                    public final void onSrcViewUpdate(ImageViewerPopupView imageViewerPopupView, int i2) {
                        MyCommentActivity.MyCommentListFragment.m183toImageShow$lambda1$lambda0(imageView, imageViewerPopupView, i2);
                    }
                }, new XpopImageLoader()).show();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: toImageShow$lambda-1$lambda-0  reason: not valid java name */
        public static final void m183toImageShow$lambda1$lambda0(ImageView imageView, ImageViewerPopupView popupView, int i) {
            Intrinsics.checkNotNullParameter(imageView, "$imageView");
            Intrinsics.checkNotNullParameter(popupView, "popupView");
            if (i == 0) {
                popupView.updateSrcView(imageView);
            } else if (i == 1) {
                popupView.updateSrcView(imageView);
            } else if (i != 2) {
            } else {
                popupView.updateSrcView(imageView);
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyCommentActivity$MyCommentListFragment$vYtny4-cIEkuNsBRTK6lAI10uPw
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MyCommentActivity.MyCommentListFragment.m180onItemChildClick$lambda3(MyCommentActivity.MyCommentListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-3  reason: not valid java name */
        public static final void m180onItemChildClick$lambda3(final MyCommentListFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            final ReviewItem reviewItem = (ReviewItem) this$0.mAdapter.getItem(i);
            final Ref.IntRef intRef = new Ref.IntRef();
            boolean z = true;
            intRef.element = 1;
            if (Intrinsics.areEqual(this$0.type, "playlist")) {
                intRef.element = 3;
            } else if (Intrinsics.areEqual(this$0.type, "actor")) {
                intRef.element = 4;
            } else if (Intrinsics.areEqual(this$0.type, "movie")) {
                intRef.element = reviewItem.getBox_type();
            }
            String str = "";
            switch (view.getId()) {
                case R.id.ivAction /* 2131296911 */:
                    new XPopup.Builder(this$0.getContext()).atView(view).asAttachList(new String[]{"Delete"}, null, new OnSelectListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyCommentActivity$MyCommentListFragment$lVEFIaGBu7JIqKciHQck4afatzo
                        @Override // com.lxj.xpopup.interfaces.OnSelectListener
                        public final void onSelect(int i2, String str2) {
                            MyCommentActivity.MyCommentListFragment.m181onItemChildClick$lambda3$lambda2(MyCommentActivity.MyCommentListFragment.this, i, reviewItem, intRef, i2, str2);
                        }
                    }).show();
                    return;
                case R.id.llLike /* 2131297190 */:
                    reviewItem.setSupport_status(1);
                    Integer support = reviewItem.getSupport();
                    reviewItem.setSupport(support != null ? Integer.valueOf(support.intValue() + 1) : null);
                    this$0.mAdapter.notifyItemChanged(i);
                    this$0.likeReview(reviewItem.getComment_id(), 1, intRef.element);
                    return;
                case R.id.llReply /* 2131297211 */:
                    String mid = reviewItem.getMid();
                    if (!(mid == null || StringsKt.isBlank(mid))) {
                        str = reviewItem.getMid();
                    } else {
                        String pid = reviewItem.getPid();
                        if (!(pid == null || StringsKt.isBlank(pid))) {
                            str = reviewItem.getPid();
                        } else {
                            String actor_id = reviewItem.getActor_id();
                            if (actor_id != null && !StringsKt.isBlank(actor_id)) {
                                z = false;
                            }
                            if (!z) {
                                str = reviewItem.getActor_id();
                            }
                        }
                    }
                    ReplyDetailActivity.Companion.start(this$0.getContext(), str, intRef.element, reviewItem.getComment_id(), null);
                    return;
                case R.id.tvName /* 2131298124 */:
                    if (reviewItem.getMovie() != null) {
                        if (reviewItem.getMovie().getBox_type() == 1) {
                            MovieDetailActivity.start(this$0.getContext(), String.valueOf(reviewItem.getMovie().getId()));
                            return;
                        } else {
                            TvDetailActivity.start(this$0.getContext(), String.valueOf(reviewItem.getMovie().getId()));
                            return;
                        }
                    } else if (reviewItem.getPlaylist() != null) {
                        MovieListDetailActivity.start(this$0.getContext(), String.valueOf(reviewItem.getPlaylist().getId()), "", "");
                        return;
                    } else if (reviewItem.getActor() != null) {
                        ActorDetailActivity.Companion.start(this$0.getContext(), String.valueOf(reviewItem.getActor().getId()));
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-3$lambda-2  reason: not valid java name */
        public static final void m181onItemChildClick$lambda3$lambda2(MyCommentListFragment this$0, int i, ReviewItem reviewItem, Ref.IntRef boxType, int i2, String str) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(boxType, "$boxType");
            this$0.deleteComment(i, reviewItem.getComment_id(), boxType.element);
        }

        private final void deleteComment(int i, String str, int i2) {
            MyCommentListFragment myCommentListFragment = this;
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post(myCommentListFragment, ApiUtils.INSTANCE.deleteComment(i2)).param("comment_id", str).asRequest(), myCommentListFragment), MyCommentActivity$MyCommentListFragment$deleteComment$1.INSTANCE, null, null, null, new MyCommentActivity$MyCommentListFragment$deleteComment$2(this, i), 14, null);
        }

        private final void likeReview(String str, int i, int i2) {
            MyCommentListFragment myCommentListFragment = this;
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post(myCommentListFragment, ApiUtils.INSTANCE.likeReview(i2)).param("comment_id", str).param("support", Integer.valueOf(i)).asRequest(), myCommentListFragment), MyCommentActivity$MyCommentListFragment$likeReview$1.INSTANCE, null, null, null, MyCommentActivity$MyCommentListFragment$likeReview$2.INSTANCE, 14, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyCommentActivity$MyCommentListFragment$azFaI3ozcy7rLz_X3vdWnFc3b_4
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MyCommentActivity.MyCommentListFragment.m182onItemClick$lambda4(MyCommentActivity.MyCommentListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-4  reason: not valid java name */
        public static final void m182onItemClick$lambda4(MyCommentListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            String actor_id;
            int box_type;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            ReviewItem reviewItem = (ReviewItem) this$0.mAdapter.getItem(i);
            String mid = reviewItem.getMid();
            boolean z = false;
            if (!(mid == null || StringsKt.isBlank(mid))) {
                actor_id = reviewItem.getMid();
            } else {
                String pid = reviewItem.getPid();
                if (!(pid == null || StringsKt.isBlank(pid))) {
                    actor_id = reviewItem.getPid();
                } else {
                    String actor_id2 = reviewItem.getActor_id();
                    actor_id = !((actor_id2 == null || StringsKt.isBlank(actor_id2)) ? true : true) ? reviewItem.getActor_id() : "";
                }
            }
            String str = actor_id;
            if (Intrinsics.areEqual(this$0.type, "playlist")) {
                box_type = 3;
            } else if (Intrinsics.areEqual(this$0.type, "actor")) {
                box_type = 4;
            } else {
                box_type = Intrinsics.areEqual(this$0.type, "movie") ? reviewItem.getBox_type() : 1;
            }
            ReplyDetailActivity.Companion.start(this$0.getContext(), str, box_type, reviewItem.getComment_id(), null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void addOnItemClickViews(BaseQuickAdapter<ReviewItem, BaseViewHolder> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.llLike, R.id.llReply, R.id.ivAction, R.id.tvName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, ReviewItem item) {
            Integer is_delete;
            Integer is_delete2;
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = (TextView) helper.getView(R.id.tvName);
            if (item.getMovie() != null) {
                textView.setText(item.getMovie().getTitle());
            } else if (item.getPlaylist() != null) {
                textView.setText(item.getPlaylist().getName());
            } else if (item.getActor() != null) {
                textView.setText(item.getActor().getName());
            } else {
                textView.setText("");
            }
            TextView textView2 = (TextView) helper.getView(R.id.tvReplyStack);
            Integer is_delete3 = item.is_delete();
            if (is_delete3 != null && is_delete3.intValue() == 0) {
                TextView textView3 = textView2;
                CommonExtKt.visible(textView3);
                List<AtItem> at = item.getAt();
                if (at == null || at.isEmpty()) {
                    CommonExtKt.gone(textView3);
                } else {
                    CommonExtKt.visible(textView3);
                    SpanUtils with = SpanUtils.with(textView2);
                    Intrinsics.checkNotNullExpressionValue(with, "with(reply)");
                    SpanUtils addText = CommonExtKt.addText(with, "Replying to ", 14, R.color.white_70alpha);
                    for (AtItem atItem : item.getAt()) {
                        CommonExtKt.addText(addText, Intrinsics.stringPlus("@", atItem.getUsername()), 14, R.color.color_main_blue).append(" ");
                    }
                    addText.create();
                }
            } else {
                CommonExtKt.gone(textView2);
            }
            ImageView imageView = (ImageView) helper.getView(R.id.ivAction);
            if (Intrinsics.areEqual(item.getUid(), App.getUserData().uid_v2) && (is_delete2 = item.is_delete()) != null && is_delete2.intValue() == 1) {
                CommonExtKt.gone(imageView);
            } else {
                CommonExtKt.visible(imageView);
            }
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView = (QMUISpanTouchFixTextView) helper.getView(R.id.tvContent);
            qMUISpanTouchFixTextView.setMovementMethodDefault();
            qMUISpanTouchFixTextView.setNeedForceEventToParent(true);
            QMUISpanTouchFixTextView qMUISpanTouchFixTextView2 = qMUISpanTouchFixTextView;
            SpanUtils span = SpanUtils.with(qMUISpanTouchFixTextView2);
            Integer is_delete4 = item.is_delete();
            if (is_delete4 != null && is_delete4.intValue() == 0) {
                List<Comment> comment = item.getComment();
                if (comment != null) {
                    for (Comment comment2 : comment) {
                        String uid = comment2.getUid();
                        if (uid == null || StringsKt.isBlank(uid)) {
                            Intrinsics.checkNotNullExpressionValue(span, "span");
                            CommonExtKt.addText(span, String.valueOf(comment2.getText()), 14, R.color.white30_transparent);
                        } else {
                            String stringPlus = Intrinsics.stringPlus("@", comment2.getUsername());
                            if (stringPlus == null) {
                                stringPlus = "";
                            }
                            SpanUtils append = span.append(stringPlus);
                            final int colorInt = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                            append.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.activity.MyCommentActivity$MyCommentListFragment$initHolder$2$1
                                @Override // android.text.style.ClickableSpan
                                public void onClick(View widget) {
                                    Intrinsics.checkNotNullParameter(widget, "widget");
                                    ToastUtils.showShort("点击", new Object[0]);
                                }
                            }).setForegroundColor(CommonExtKt.colorInt(this, (int) R.color.color_main_blue)).setFontSize(14, true);
                        }
                    }
                }
                span.create();
            } else {
                SpanUtils with2 = SpanUtils.with(qMUISpanTouchFixTextView2);
                Intrinsics.checkNotNullExpressionValue(with2, "with(content)");
                CommonExtKt.addText(with2, "This review is deleted by user.", 14, R.color.white30_transparent).setItalic().create();
            }
            LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llLike);
            Integer support_status = item.getSupport_status();
            linearLayout.setSelected(support_status != null && support_status.intValue() == 1);
            TextView textView4 = (TextView) helper.getView(R.id.tvLikeNum);
            Integer support = item.getSupport();
            if ((support == null ? 0 : support.intValue()) > 0) {
                CommonExtKt.visible(textView4);
                textView4.setText(String.valueOf(item.getSupport()));
            } else {
                CommonExtKt.gone(textView4);
            }
            TextView textView5 = (TextView) helper.getView(R.id.tvReplyNum);
            Integer reply = item.getReply();
            if ((reply == null ? 0 : reply.intValue()) > 0) {
                CommonExtKt.visible(textView5);
                textView5.setText(String.valueOf(item.getReply()));
            } else {
                CommonExtKt.gone(textView5);
            }
            helper.setText(R.id.tvUsername, item.getUsername());
            helper.setText(R.id.tvTime, TimeUtils.formatTime(item.getDateline() * 1000));
            ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
            RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
            recyclerView.setNestedScrollingEnabled(false);
            List<ImageItem> img_list = item.getImg_list();
            if ((img_list == null || img_list.isEmpty()) || ((is_delete = item.is_delete()) != null && is_delete.intValue() == 1)) {
                CommonExtKt.gone(recyclerView);
                return;
            }
            CommonExtKt.visible(recyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            if (recyclerView.getTag() == null) {
                recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
                recyclerView.setTag("added");
            }
            CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new MyCommentActivity$MyCommentListFragment$initHolder$imgAdapter$1(this), item.getImg_list());
            recyclerView.setAdapter(commBaseAdapter);
            final ArrayList arrayList = new ArrayList();
            for (ImageItem imageItem : item.getImg_list()) {
                String url = imageItem.getUrl();
                if (url == null) {
                    url = "";
                }
                arrayList.add(url);
            }
            commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyCommentActivity$MyCommentListFragment$gQcwieqTypLCuKsC-ZcVQIUwHtg
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MyCommentActivity.MyCommentListFragment.m177initHolder$lambda8(MyCommentActivity.MyCommentListFragment.this, arrayList, baseQuickAdapter, view, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initHolder$lambda-8  reason: not valid java name */
        public static final void m177initHolder$lambda8(MyCommentListFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(images, "$images");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
            this$0.toImageShow(i, imageView, images, imageView);
        }

        /* compiled from: MyCommentActivity.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/activity/MyCommentActivity$MyCommentListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/MyCommentActivity$MyCommentListFragment;", IjkMediaMeta.IJKM_KEY_TYPE, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final MyCommentListFragment newInstance(String type) {
                Intrinsics.checkNotNullParameter(type, "type");
                MyCommentListFragment myCommentListFragment = new MyCommentListFragment();
                Bundle bundle = new Bundle();
                bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, type);
                myCommentListFragment.setArguments(bundle);
                return myCommentListFragment;
            }
        }
    }
}
