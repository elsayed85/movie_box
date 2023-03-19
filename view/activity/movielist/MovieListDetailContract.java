package com.movieboxpro.android.view.activity.movielist;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.tv.TvDetail;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public interface MovieListDetailContract {

    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void collect(String str, String str2, String str3, String str4, String str5);

        void requestFilterList(String str, String str2, String str3, int i, int i2);

        void requestList(String str, String str2, int i, String str3);

        void requestListToPlay(String str, String str2, String str3);
    }

    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void doCollectComplete(boolean z);

        void goMoviePlayer(MovieDetail movieDetail);

        void goTvPlayer(TvDetail tvDetail);

        void movieListDeleted(String str);

        void randomPlay(MovieListDetailModel movieListDetailModel);

        void removeCollectComplete();

        void showData(MovieListDetailModel movieListDetailModel);

        void showFilterData(MovieListDetailModel movieListDetailModel);

        void showReviewCount(String str);

        void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str, String str2, int i, int i2, int i3);
    }
}
