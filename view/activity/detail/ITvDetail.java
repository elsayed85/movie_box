package com.movieboxpro.android.view.activity.detail;

import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.view.listener.IViewController;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface ITvDetail extends IViewController {
    void ChoosePlayer(TvDetail tvDetail, int i, int i2, boolean z);

    void addVideoCallback();

    void getFeedBack(List<Feedback> list);

    void getProblem(List<TvDetail.SeasonDetail> list);

    void getVideoInfo(TvDetail tvDetail);

    void goTvPlayer(TvDetail tvDetail, int i, boolean z);

    void markWatchedComplete();

    void onWatchedChanged(int i);

    void setDateList(List<TvDetail.SeasonDetail> list);

    void setFavorite(boolean z);

    void showActors(List<ActorModel> list);

    void showBottomMovieListDialog(List<MovieListModel.MovieListItem> list);

    void showPoster(String str);

    void showReviewCount(String str);

    void showScreenManage(String str, ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str2, int i, int i2, int i3, boolean z);

    void switchPlayButtonStage(int i);
}
