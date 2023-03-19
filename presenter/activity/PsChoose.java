package com.movieboxpro.android.presenter.activity;

import android.content.Context;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.model.detail.AbstractVideoBean;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.service.UploadErrorInfoService;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.choose.IChoose;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class PsChoose implements IPresenter {
    private static final String TAG = "PsChoose";
    private AbstractVideoBean abstractVideoBean;
    private Context mContext;
    private IChoose mViewController;

    @Override // com.movieboxpro.android.presenter.IPresenter
    public void detachView() {
    }

    @Override // com.movieboxpro.android.presenter.IPresenter
    public void loadData() {
    }

    public PsChoose() {
    }

    public PsChoose(Context context, IChoose iChoose) {
        this.mContext = context;
        this.mViewController = iChoose;
    }

    public void setFactory(AbstractVideoBean abstractVideoBean) {
        this.abstractVideoBean = abstractVideoBean;
    }

    public void getPath(final String str, final int i, final int i2) {
        if (this.mViewController == null) {
            return;
        }
        this.abstractVideoBean.getPath(str, i, i2).subscribeOn(Schedulers.io()).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).observeOn(AndroidSchedulers.mainThread()).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.presenter.activity.PsChoose.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                RxManager.addDisposable(PsChoose.TAG, disposable);
                if (PsChoose.this.mViewController != null) {
                    PsChoose.this.mViewController.showSwipeView();
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BaseMediaModel baseMediaModel) {
                if (PsChoose.this.mViewController == null) {
                    return;
                }
                PsChoose.this.mViewController.setPath(baseMediaModel);
                PsChoose.this.mViewController.hideSwipeView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onException(Throwable th) {
                if (i > 0 && i2 > 0) {
                    UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, API.Tv.TV_DOWNLAODURL, 2, DownloadInfo.DOWNLOAD, i, i2);
                } else {
                    UploadErrorInfoService.Companion.startUploadService(App.getContext(), th, str, API.Movie.MOVE_DOWNLAOD, 1, DownloadInfo.DOWNLOAD);
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                if (PsChoose.this.mViewController == null) {
                    return;
                }
                IChoose iChoose = PsChoose.this.mViewController;
                iChoose.showToast("Load failed:" + apiException.getMessage());
                PsChoose.this.mViewController.hideSwipeView();
            }
        });
    }

    public void get_DownaloadSrt(String str, int i, int i2, final String str2, final int i3) {
        Observable<String> Movie_srt_auto;
        if (i > 0 && i2 > 0) {
            Movie_srt_auto = Http.getService().Tv_srt_auto(API.BASE_URL, API.Tv.TV_SRT_AUTO_V2, str, i, i2, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "");
        } else {
            Movie_srt_auto = Http.getService().Movie_srt_auto(API.BASE_URL, API.Movie.MOVIE_SRT_AUTO_V2, str, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "");
        }
        Movie_srt_auto.compose(RxUtils.rxTranslate2List(Srt.class)).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<List<Srt>>() { // from class: com.movieboxpro.android.presenter.activity.PsChoose.2
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                RxManager.addDisposable(PsChoose.TAG, disposable);
                if (PsChoose.this.mViewController != null) {
                    PsChoose.this.mViewController.showLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onNext(List<Srt> list) {
                if (PsChoose.this.mViewController != null) {
                    PsChoose.this.mViewController.getSrt(list, str2, i3);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (PsChoose.this.mViewController != null) {
                    PsChoose.this.mViewController.hideLoading();
                }
                if (PsChoose.this.mViewController != null) {
                    PsChoose.this.mViewController.getSrt(new ArrayList(), str2, i3);
                }
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                if (PsChoose.this.mViewController != null) {
                    PsChoose.this.mViewController.hideLoading();
                }
            }
        });
    }
}
