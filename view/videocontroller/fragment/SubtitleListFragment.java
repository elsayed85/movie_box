package com.movieboxpro.android.view.videocontroller.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.dueeeke.model.SRTModel;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.videocontroller.CcController;
import com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleFragment;
import com.movieboxpro.android.view.videocontroller.fragment.SubtitleListContract;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SubtitleListFragment.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001(B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0002H\u0014J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0014J\b\u0010\u0018\u001a\u00020\u0014H\u0014J\b\u0010\u0019\u001a\u00020\u0014H\u0014J\u0006\u0010\u001a\u001a\u00020\u001bJ\b\u0010\u001c\u001a\u00020\u0014H\u0014J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\"\u0010\u001e\u001a\u00020\u00142\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0018\u00010\fJ\u000e\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\bJ\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u0011J\u000e\u0010#\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u0006J\"\u0010$\u001a\u00020\u00142\u001a\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0018\u00010\fJ\b\u0010%\u001a\u00020\u0014H\u0016J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020'H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListPresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract$View;", "()V", "ccCallback", "Lcom/movieboxpro/android/view/videocontroller/CcController$CcCallBack;", "searchUploadSubtitleCallback", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment$SearchUploadSubtitleCallback;", "searchUploadSubtitleFragment", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleFragment;", "subtitleData", "Ljava/util/LinkedHashMap;", "", "", "Lcom/dueeeke/model/SRTModel$SubTitles;", "bindLayout", "", "bindPresenter", "clearData", "", "disSelect", "hideLoadingView", "initData", "initListener", "initView", "isFragmentVisible", "", "loadData", "loadFailed", "resetSubtitleData", "setSearchUploadCallback", "callback", "setSelectPosition", "position", "setSubtitleCallback", "setSubtitleData", "showLoadingView", "showSubtitleData", "Lcom/dueeeke/model/ResponseSubtitleRecord;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleListFragment extends BaseMvpFragment<SubtitleListPresenter> implements SubtitleListContract.View {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private CcController.CcCallBack ccCallback;
    private SearchUploadSubtitleFragment.SearchUploadSubtitleCallback searchUploadSubtitleCallback;
    private SearchUploadSubtitleFragment searchUploadSubtitleFragment;
    private LinkedHashMap<String, List<SRTModel.SubTitles>> subtitleData;

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_subtitle_list;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        ((CcController) _$_findCachedViewById(R.id.subtitleController)).setShowFragmentListener(new CcController.OnShowFragmentListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SubtitleListFragment$kz6dXvIN8acXyDjnyDGMsIZ5-Jk
            @Override // com.movieboxpro.android.view.videocontroller.CcController.OnShowFragmentListener
            public final void showFragment(int i) {
                SubtitleListFragment.m1380initListener$lambda0(SubtitleListFragment.this, i);
            }
        });
        ((CcController) _$_findCachedViewById(R.id.subtitleController)).setCallback(this.ccCallback);
        ((CcController) _$_findCachedViewById(R.id.subtitleController)).setOnLikeStatusListener(new CcController.LikeStatusListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SubtitleListFragment$JgftNN_lHg8CTnUZRkBy537B6A8
            @Override // com.movieboxpro.android.view.videocontroller.CcController.LikeStatusListener
            public final void onLikeStatus(int i, String str, int i2) {
                SubtitleListFragment.m1381initListener$lambda1(SubtitleListFragment.this, i, str, i2);
            }
        });
        if (this.subtitleData != null) {
            ((CcController) _$_findCachedViewById(R.id.subtitleController)).setVideoQualityList(this.subtitleData);
        }
        if (!Network.isConnected(getContext())) {
            ((CcController) _$_findCachedViewById(R.id.subtitleController)).setShowSubtitlePage();
        } else if (this.subtitleData == null) {
            SubtitleListPresenter subtitleListPresenter = (SubtitleListPresenter) this.mPresenter;
            Bundle arguments = getArguments();
            String string = arguments == null ? null : arguments.getString("fid");
            Bundle arguments2 = getArguments();
            String string2 = arguments2 == null ? null : arguments2.getString("id");
            Bundle arguments3 = getArguments();
            Integer valueOf = arguments3 == null ? null : Integer.valueOf(arguments3.getInt("boxType"));
            Bundle arguments4 = getArguments();
            Integer valueOf2 = arguments4 == null ? null : Integer.valueOf(arguments4.getInt("season"));
            Bundle arguments5 = getArguments();
            subtitleListPresenter.getSubtitleData(string, string2, valueOf, valueOf2, arguments5 != null ? Integer.valueOf(arguments5.getInt("episode")) : null);
        }
        ((TextView) _$_findCachedViewById(R.id.tvTryAgain)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SubtitleListFragment$8lrWlnbaLY7eEJrpU7_mweS-C6o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SubtitleListFragment.m1382initListener$lambda2(SubtitleListFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1380initListener$lambda0(SubtitleListFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SearchUploadSubtitleFragment searchUploadSubtitleFragment = this$0.searchUploadSubtitleFragment;
        if (searchUploadSubtitleFragment == null) {
            SearchUploadSubtitleFragment newInstance = SearchUploadSubtitleFragment.Companion.newInstance(this$0.getArguments());
            this$0.searchUploadSubtitleFragment = newInstance;
            Intrinsics.checkNotNull(newInstance);
            newInstance.setSearchUploadSubtitleCallback(this$0.searchUploadSubtitleCallback);
            FragmentManager childFragmentManager = this$0.getChildFragmentManager();
            SearchUploadSubtitleFragment searchUploadSubtitleFragment2 = this$0.searchUploadSubtitleFragment;
            Intrinsics.checkNotNull(searchUploadSubtitleFragment2);
            FragmentUtils.add(childFragmentManager, searchUploadSubtitleFragment2, i);
        } else {
            Intrinsics.checkNotNull(searchUploadSubtitleFragment);
            FragmentUtils.show(searchUploadSubtitleFragment);
        }
        Context context = this$0.getContext();
        if (context != null) {
            CommonExtKt.onMobEvent(context, "MoreSubtitles");
        }
        EventUtils.event("字幕进入更多");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1381initListener$lambda1(SubtitleListFragment this$0, int i, String str, int i2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HttpRequest.Companion companion = HttpRequest.Companion;
        Bundle arguments = this$0.getArguments();
        boolean z = false;
        if (arguments != null && arguments.getInt("boxType") == 1) {
            z = true;
        }
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(companion.post(z ? "Movie_srt_opposition" : "TV_srt_opposition").param("support", Integer.valueOf(i)).param("sid", str).asRequest(), this$0), new SubtitleListFragment$initListener$2$1(this$0), null, new SubtitleListFragment$initListener$2$2(this$0), null, new SubtitleListFragment$initListener$2$3(this$0, i2, i), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1382initListener$lambda2(SubtitleListFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SubtitleListPresenter subtitleListPresenter = (SubtitleListPresenter) this$0.mPresenter;
        Bundle arguments = this$0.getArguments();
        String string = arguments == null ? null : arguments.getString("fid");
        Bundle arguments2 = this$0.getArguments();
        String string2 = arguments2 == null ? null : arguments2.getString("id");
        Bundle arguments3 = this$0.getArguments();
        Integer valueOf = arguments3 == null ? null : Integer.valueOf(arguments3.getInt("boxType"));
        Bundle arguments4 = this$0.getArguments();
        Integer valueOf2 = arguments4 == null ? null : Integer.valueOf(arguments4.getInt("season"));
        Bundle arguments5 = this$0.getArguments();
        subtitleListPresenter.getSubtitleData(string, string2, valueOf, valueOf2, arguments5 == null ? null : Integer.valueOf(arguments5.getInt("episode")));
    }

    public final void setSearchUploadCallback(SearchUploadSubtitleFragment.SearchUploadSubtitleCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.searchUploadSubtitleCallback = callback;
    }

    public final void setSubtitleCallback(CcController.CcCallBack callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.ccCallback = callback;
    }

    public final void setSubtitleData(LinkedHashMap<String, List<SRTModel.SubTitles>> linkedHashMap) {
        if (this.subtitleData == null) {
            this.subtitleData = linkedHashMap;
        }
    }

    public final void resetSubtitleData(LinkedHashMap<String, List<SRTModel.SubTitles>> linkedHashMap) {
        ((CcController) _$_findCachedViewById(R.id.subtitleController)).setVideoQualityList(linkedHashMap);
    }

    public final void setSelectPosition(int i) {
        CcController ccController = (CcController) _$_findCachedViewById(R.id.subtitleController);
        if (ccController == null) {
            return;
        }
        ccController.setSelectPosition(i);
    }

    public final void clearData() {
        CcController ccController = (CcController) _$_findCachedViewById(R.id.subtitleController);
        if (ccController == null) {
            return;
        }
        ccController._clear();
    }

    public final void disSelect() {
        ((CcController) _$_findCachedViewById(R.id.subtitleController)).disSelect();
    }

    public final boolean isFragmentVisible() {
        return isVisible();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public SubtitleListPresenter bindPresenter() {
        return new SubtitleListPresenter(this);
    }

    /* compiled from: SubtitleListFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JG\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListFragment;", "title", "", "id", "fid", "season", "", "episode", "boxType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListFragment;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SubtitleListFragment newInstance(String str, String str2, String str3, Integer num, Integer num2, Integer num3) {
            SubtitleListFragment subtitleListFragment = new SubtitleListFragment();
            subtitleListFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("id", str2), TuplesKt.to("season", num), TuplesKt.to("episode", num2), TuplesKt.to("name", str), TuplesKt.to("fid", str3), TuplesKt.to("boxType", num3)));
            return subtitleListFragment;
        }
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.SubtitleListContract.View
    public void loadFailed() {
        FrameLayout flLoad = (FrameLayout) _$_findCachedViewById(R.id.flLoad);
        Intrinsics.checkNotNullExpressionValue(flLoad, "flLoad");
        CommonExtKt.visible(flLoad);
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        LinearLayout llFailed = (LinearLayout) _$_findCachedViewById(R.id.llFailed);
        Intrinsics.checkNotNullExpressionValue(llFailed, "llFailed");
        CommonExtKt.visible(llFailed);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
        FrameLayout flLoad = (FrameLayout) _$_findCachedViewById(R.id.flLoad);
        Intrinsics.checkNotNullExpressionValue(flLoad, "flLoad");
        CommonExtKt.visible(flLoad);
        ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
        LinearLayout llFailed = (LinearLayout) _$_findCachedViewById(R.id.llFailed);
        Intrinsics.checkNotNullExpressionValue(llFailed, "llFailed");
        CommonExtKt.gone(llFailed);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        FrameLayout flLoad = (FrameLayout) _$_findCachedViewById(R.id.flLoad);
        Intrinsics.checkNotNullExpressionValue(flLoad, "flLoad");
        CommonExtKt.gone(flLoad);
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.SubtitleListContract.View
    public void showSubtitleData(ResponseSubtitleRecord subtitleData) {
        Intrinsics.checkNotNullParameter(subtitleData, "subtitleData");
        if (this.subtitleData == null) {
            this.subtitleData = new LinkedHashMap<>();
            for (SRTModel sRTModel : subtitleData.getList()) {
                LinkedHashMap<String, List<SRTModel.SubTitles>> linkedHashMap = this.subtitleData;
                Intrinsics.checkNotNull(linkedHashMap);
                String str = sRTModel.language;
                Intrinsics.checkNotNullExpressionValue(str, "srtList.language");
                List<SRTModel.SubTitles> list = sRTModel.subtitles;
                Intrinsics.checkNotNullExpressionValue(list, "srtList.subtitles");
                linkedHashMap.put(str, list);
            }
            ((CcController) _$_findCachedViewById(R.id.subtitleController)).setVideoQualityList(this.subtitleData);
        }
    }
}
