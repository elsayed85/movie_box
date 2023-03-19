package com.movieboxpro.android.view.activity.detail;

import com.movieboxpro.android.model.ActorModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.view.listener.IViewController;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public interface IMovieDetail extends IViewController {
    void ChoosePlayer(MovieDetail movieDetail);

    void addVideoCallback();

    void getFeedBack(List<Feedback> list);

    void getVideoInfo(MovieDetail movieDetail);

    void onWatchedChanged(int i);

    void setFavorite(boolean z);

    void showActors(List<ActorModel> list);

    void showBottomMovieListDialog(List<MovieListModel.MovieListItem> list);

    void showPoster(String str);

    void showReviewCount(String str);

    void showScreenManage(String str, ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str2);

    void switchPlayButtonStage(int i);
}
