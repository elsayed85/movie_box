package com.movieboxpro.android.view.activity.movielist;

import com.movieboxpro.android.adapter.AddMovieListDragAdapter;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.CreateMovieListModel;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import java.io.File;
import java.util.List;
/* loaded from: classes3.dex */
public interface CreateMovieListContract {

    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void doCreateList(String str, String str2, int i, String str3, List<CreateMovieListModel> list, File file);

        void doDeleteList(String str, String str2);

        void doEditList(String str, String str2, int i, String str3, List<CreateMovieListModel> list, File file, List<CreateMovieListModel> list2);

        void loadList(String str, String str2, int i, int i2, AddMovieListDragAdapter addMovieListDragAdapter);

        void uploadCover(String str, String str2, int i, String str3, List<CreateMovieListModel> list, File file, List<CreateMovieListModel> list2);
    }

    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void doCreateResult(boolean z, String str);

        void doDeleteListResult(boolean z, String str);

        void loadDataComplete(MovieListDetailModel movieListDetailModel);

        void loadMoreDataComplete(MovieListDetailModel movieListDetailModel);
    }
}
