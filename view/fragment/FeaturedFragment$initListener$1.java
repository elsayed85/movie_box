package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.lxj.xpopup.XPopup;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.FeaturedAdapter;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.livedata.SwitchRecommendLiveData;
import com.movieboxpro.android.livedata.SwitchWaitingLiveData;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.activity.actor.ActorDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.more.MoreVideoActivity;
import com.movieboxpro.android.view.activity.movie.ActMovieActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.activity.review.ReviewDetailActivity;
import com.movieboxpro.android.view.activity.web.WebViewActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.dialog.WebViewPopView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FeaturedFragment.kt */
@Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J4\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016J4\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\fH\u0016¨\u0006\u000f"}, d2 = {"com/movieboxpro/android/view/fragment/FeaturedFragment$initListener$1", "Lcom/movieboxpro/android/adapter/FeaturedAdapter$OnFeaturedItemClickListener;", "onItemClicked", "", "itemType", "", "item", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "view", "Landroid/view/View;", "position", "featuredAdapter", "Lcom/movieboxpro/android/adapter/FeaturedAdapter$FeaturedListAdapter;", "onItemLongClicked", "adapter", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeaturedFragment$initListener$1 implements FeaturedAdapter.OnFeaturedItemClickListener {
    final /* synthetic */ FeaturedFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FeaturedFragment$initListener$1(FeaturedFragment featuredFragment) {
        this.this$0 = featuredFragment;
    }

    @Override // com.movieboxpro.android.adapter.FeaturedAdapter.OnFeaturedItemClickListener
    public void onItemClicked(int i, final Homelist.Typelist typelist, View view, final int i2, final FeaturedAdapter.FeaturedListAdapter featuredAdapter) {
        String str;
        boolean checkChildMode;
        BaseContract.BasePresenter basePresenter;
        BaseContract.BasePresenter basePresenter2;
        Intrinsics.checkNotNullParameter(featuredAdapter, "featuredAdapter");
        if (typelist == null) {
            return;
        }
        final FeaturedFragment featuredFragment = this.this$0;
        if (i != -100) {
            str = "";
            switch (i) {
                case 1:
                    String url = typelist.getUrl();
                    if (((url == null || url.length() == 0) ? 1 : 1) == 0) {
                        int type = typelist.getType();
                        if (type != 1) {
                            if (type != 2) {
                                return;
                            }
                            SystemUtils.startBrowser((Activity) featuredFragment.getActivity(), typelist.getUrl());
                            return;
                        }
                        WebViewActivity.Companion companion = WebViewActivity.Companion;
                        Context context = featuredFragment.getContext();
                        String url2 = typelist.getUrl();
                        Intrinsics.checkNotNullExpressionValue(url2, "item.url");
                        companion.starter(context, url2, "");
                        return;
                    } else if (featuredFragment.getContext() == null) {
                        return;
                    } else {
                        TvDetailActivity.start(featuredFragment.getContext(), typelist.id, typelist.banner_mini, typelist.poster);
                        return;
                    }
                case 2:
                    checkChildMode = featuredFragment.checkChildMode(typelist.getContent_rating());
                    if (checkChildMode) {
                        return;
                    }
                    String str2 = typelist.id;
                    if (!(str2 == null || str2.length() == 0)) {
                        basePresenter = featuredFragment.mPresenter;
                        FeaturedPresenter featuredPresenter = (FeaturedPresenter) basePresenter;
                        String str3 = typelist.id;
                        Intrinsics.checkNotNullExpressionValue(str3, "item.id");
                        int i3 = typelist.box_type;
                        Homelist.History history = typelist.getHistory();
                        int season = history == null ? 0 : history.getSeason();
                        Homelist.History history2 = typelist.getHistory();
                        featuredPresenter.getPlayPath(str3, i3, season, history2 != null ? history2.getEpisode() : 0);
                        return;
                    }
                    SwitchWaitingLiveData.Companion.get().setValue(true);
                    return;
                case 3:
                case 9:
                    if (Intrinsics.areEqual(typelist.getFeaturedType(), "maybe_like")) {
                        String str4 = typelist.id;
                        if (((str4 == null || str4.length() == 0) ? 1 : 1) != 0) {
                            SwitchRecommendLiveData.Companion.get().setValue(true);
                            return;
                        } else {
                            MovieDetailActivity.start(featuredFragment.getContext(), typelist.id, typelist.poster);
                            return;
                        }
                    }
                    String str5 = typelist.id;
                    if (((str5 == null || str5.length() == 0) ? 1 : 1) != 0) {
                        MoreVideoActivity.Companion companion2 = MoreVideoActivity.Companion;
                        Context context2 = featuredFragment.getContext();
                        String featuredType = typelist.getFeaturedType();
                        if (featuredType == null) {
                            featuredType = "like";
                        }
                        String name = typelist.getName();
                        companion2.start(context2, featuredType, name != null ? name : "");
                        return;
                    }
                    MovieDetailActivity.start(featuredFragment.getContext(), typelist.id, typelist.poster);
                    return;
                case 4:
                    String str6 = typelist.id;
                    if (((str6 == null || str6.length() == 0) ? 1 : 1) != 0) {
                        if (featuredFragment.getContext() == null) {
                            return;
                        }
                        MoreVideoActivity.Companion companion3 = MoreVideoActivity.Companion;
                        Context context3 = featuredFragment.getContext();
                        String featuredType2 = typelist.getFeaturedType();
                        if (featuredType2 == null) {
                            featuredType2 = "dayhottv";
                        }
                        String name2 = typelist.getName();
                        companion3.start(context3, featuredType2, name2 != null ? name2 : "");
                        return;
                    } else if (featuredFragment.getContext() == null) {
                        return;
                    } else {
                        TvDetailActivity.start(featuredFragment.getContext(), typelist.id, typelist.banner_mini, typelist.poster);
                        return;
                    }
                case 5:
                    if (featuredFragment.getContext() == null) {
                        return;
                    }
                    ActorDetailActivity.Companion companion4 = ActorDetailActivity.Companion;
                    Context context4 = featuredFragment.getContext();
                    String actor_id = typelist.getActor_id();
                    companion4.start(context4, actor_id != null ? actor_id : "");
                    return;
                case 6:
                    if (featuredFragment.getContext() == null) {
                        return;
                    }
                    ActMovieActivity.Companion companion5 = ActMovieActivity.Companion;
                    Context context5 = featuredFragment.getContext();
                    String id = typelist.getId();
                    if (id == null) {
                        id = "";
                    }
                    String name3 = typelist.getName();
                    companion5.start(context5, id, name3 != null ? name3 : "");
                    return;
                case 7:
                    if (featuredFragment.getContext() == null) {
                        return;
                    }
                    List<String> imgArr = typelist.getImgArr();
                    Intrinsics.checkNotNullExpressionValue(imgArr, "item.imgArr");
                    if (!imgArr.isEmpty()) {
                        String str7 = typelist.getImgArr().get(0);
                        Intrinsics.checkNotNullExpressionValue(str7, "item.imgArr[0]");
                        str = str7;
                    }
                    MovieListDetailActivity.start(featuredFragment.getContext(), typelist.getLid(), typelist.getUsername(), str);
                    return;
                case 8:
                    basePresenter2 = featuredFragment.mPresenter;
                    ((FeaturedPresenter) basePresenter2).advNotify(typelist.id);
                    int type2 = typelist.getType();
                    if (type2 == 1) {
                        if (featuredFragment.getContext() == null) {
                            return;
                        }
                        XPopup.Builder moveUpToKeyboard = new XPopup.Builder(featuredFragment.getContext()).hasShadowBg(false).moveUpToKeyboard(false);
                        Context context6 = featuredFragment.getContext();
                        Intrinsics.checkNotNull(context6);
                        Intrinsics.checkNotNullExpressionValue(context6, "context!!");
                        String url3 = typelist.getUrl();
                        Intrinsics.checkNotNullExpressionValue(url3, "item.url");
                        moveUpToKeyboard.asCustom(new WebViewPopView(context6, url3)).show();
                        return;
                    } else if (type2 == 2) {
                        SystemUtils.startBrowser((Activity) featuredFragment.getActivity(), typelist.getUrl());
                        return;
                    } else if (type2 == 5) {
                        MovieDetailActivity.start(featuredFragment.getContext(), typelist.getUrl());
                        return;
                    } else if (type2 == 6) {
                        TvDetailActivity.start(featuredFragment.getContext(), typelist.getUrl());
                        return;
                    } else if (type2 == 7) {
                        MovieListDetailActivity.start(featuredFragment.getContext(), typelist.getUrl(), "", "");
                        return;
                    } else if (type2 != 8) {
                        return;
                    } else {
                        ReviewDetailActivity.Companion.start(featuredFragment.getContext(), "", typelist.getTid(), null, false);
                        return;
                    }
                default:
                    return;
            }
        }
        if (view != null && view.getId() == R.id.ivDislike) {
            r2 = 1;
        }
        if (r2 != 0) {
            new MsgHintDialog.Builder(featuredFragment.getContext()).setContent("Dislike this movie?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$initListener$1$RIHcM0YSspM1L8CIEcqZveIncIw
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    FeaturedFragment$initListener$1.m1190onItemClicked$lambda10$lambda9(FeaturedFragment.this, typelist, i2, featuredAdapter);
                }
            }).setPositiveText("Dislike").create().show();
        } else if (typelist.box_type == 1) {
            MovieDetailActivity.start(featuredFragment.getContext(), typelist.id, typelist.poster);
        } else {
            TvDetailActivity.start(featuredFragment.getContext(), typelist.id, typelist.banner_mini, typelist.poster);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClicked$lambda-10$lambda-9  reason: not valid java name */
    public static final void m1190onItemClicked$lambda10$lambda9(final FeaturedFragment this$0, final Homelist.Typelist typelist, final int i, final FeaturedAdapter.FeaturedListAdapter featuredAdapter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(featuredAdapter, "$featuredAdapter");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$initListener$1$OYqQ9iNviyWb1O-wV-76fiLaPzc
            @Override // java.lang.Runnable
            public final void run() {
                FeaturedFragment$initListener$1.m1191onItemClicked$lambda10$lambda9$lambda8(FeaturedFragment.this, typelist, i, featuredAdapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemClicked$lambda-10$lambda-9$lambda-8  reason: not valid java name */
    public static final void m1191onItemClicked$lambda10$lambda9$lambda8(FeaturedFragment this$0, Homelist.Typelist typelist, int i, FeaturedAdapter.FeaturedListAdapter featuredAdapter) {
        BaseContract.BasePresenter basePresenter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(featuredAdapter, "$featuredAdapter");
        basePresenter = this$0.mPresenter;
        ((FeaturedPresenter) basePresenter).dislikeMovie(typelist.id, i, featuredAdapter);
    }

    @Override // com.movieboxpro.android.adapter.FeaturedAdapter.OnFeaturedItemClickListener
    public void onItemLongClicked(int i, final Homelist.Typelist typelist, View view, int i2, FeaturedAdapter.FeaturedListAdapter adapter) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        if (i == 2) {
            ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(this.this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true);
            ActionSheetDialog.SheetItemColor sheetItemColor = ActionSheetDialog.SheetItemColor.White;
            final FeaturedFragment featuredFragment = this.this$0;
            canceledOnTouchOutside.addSheetItem("Delete", sheetItemColor, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$FeaturedFragment$initListener$1$DesiVnVorqrwUixezSin5ePlpdk
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public final void onClick(int i3) {
                    FeaturedFragment$initListener$1.m1192onItemLongClicked$lambda11(FeaturedFragment.this, typelist, i3);
                }
            }).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onItemLongClicked$lambda-11  reason: not valid java name */
    public static final void m1192onItemLongClicked$lambda11(FeaturedFragment this$0, Homelist.Typelist typelist, int i) {
        BaseContract.BasePresenter basePresenter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        basePresenter = this$0.mPresenter;
        ((FeaturedPresenter) basePresenter).deleteHistory(typelist == null ? null : typelist.id, typelist == null ? 1 : typelist.box_type);
    }
}
