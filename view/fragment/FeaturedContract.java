package com.movieboxpro.android.view.fragment;

import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.adapter.FeaturedAdapter;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FeaturedDataModel;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
/* compiled from: FeaturedContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FeaturedContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface FeaturedContract {

    /* compiled from: FeaturedContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\u0004H&J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nH&J\"\u0010\u000b\u001a\u00020\u00042\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0004H&J(\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH&J\u0012\u0010\u0014\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H&¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FeaturedContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/fragment/FeaturedContract$View;", "advNotify", "", "id", "", "cancelRequest", "deleteHistory", "boxType", "", "dislikeMovie", "mid", "position", "adapter", "Lcom/movieboxpro/android/adapter/FeaturedAdapter$FeaturedListAdapter;", "getContinueList", "getPlayPath", "currSeason", "currEpisode", "requestFeaturedData", "uid", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void advNotify(String str);

        void cancelRequest();

        void deleteHistory(String str, int i);

        void dislikeMovie(String str, int i, FeaturedAdapter.FeaturedListAdapter featuredListAdapter);

        void getContinueList();

        void getPlayPath(String str, int i, int i2, int i3);

        void requestFeaturedData(String str);
    }

    /* compiled from: FeaturedContract.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\u0003H&J\b\u0010\n\u001a\u00020\u0003H&J\u0016\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&JL\u0010\u0015\u001a\u00020\u00032\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0016j\b\u0012\u0004\u0012\u00020\u0017`\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH&¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/fragment/FeaturedContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "goMoviePlayer", "", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "hideErrorView", "hideLoadView", "showContinueList", "list", "", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "showErrorView", "text", "", "showFeaturedData", "model", "Lcom/movieboxpro/android/model/FeaturedDataModel;", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "id", "boxType", "", "currSeason", "currEpisode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes.dex */
    public interface View extends BaseContract.BaseView {
        void goMoviePlayer(MovieDetail movieDetail);

        void goTvPlayer(TvDetail tvDetail);

        void hideErrorView();

        void hideLoadView();

        void showContinueList(List<? extends Homelist.Typelist> list);

        void showErrorView(String str);

        void showFeaturedData(FeaturedDataModel featuredDataModel);

        void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str, String str2, int i, int i2, int i3);
    }
}
