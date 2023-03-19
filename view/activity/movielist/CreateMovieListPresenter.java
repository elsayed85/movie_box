package com.movieboxpro.android.view.activity.movielist;

import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.adapter.AddMovieListDragAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.CreateMovieListModel;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
/* loaded from: classes3.dex */
public class CreateMovieListPresenter extends BasePresenter<CreateMovieListContract.View> implements CreateMovieListContract.Presenter {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$uploadCover$0(String str, String str2) throws Exception {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CreateMovieListPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    private String buildData(String str, String str2, int i, String str3, List<CreateMovieListModel> list) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) "Playlists");
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("name", (Object) str);
        jSONObject.put("desc", (Object) str2);
        jSONObject.put("isshare", (Object) String.valueOf(i));
        jSONObject.put("lid", (Object) str3);
        jSONObject.put("videos", (Object) list);
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.Presenter
    public void doCreateList(String str, String str2, int i, String str3, List<CreateMovieListModel> list, File file) {
        MultipartBody.Part part;
        if (file != null) {
            part = MultipartBody.Part.createFormData(IDataSource.SCHEME_FILE_TAG, file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
        } else {
            part = null;
        }
        ((ObservableSubscribeProxy) Http.getService().uploadAvatar2(API.BASE_URL, buildData(str, str2, i, str3, list), BuildConfig.APPLICATION_ID, App.versionCode, part).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListPresenter.1
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                CreateMovieListPresenter.this.getView().showLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
                CreateMovieListPresenter.this.getView().doCreateResult(true, str4);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                CreateMovieListPresenter.this.getView().hideLoadingView();
                CreateMovieListPresenter.this.getView().doCreateResult(false, th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                CreateMovieListPresenter.this.getView().hideLoadingView();
            }
        });
    }

    private String buildEditData(String str, String str2, int i, String str3, List<CreateMovieListModel> list, List<CreateMovieListModel> list2) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("module", (Object) "Playlists_video_sort_v2");
        jSONObject.put("uid", (Object) App.getUserData().uid_v2);
        jSONObject.put("open_udid", (Object) SystemUtils.getUniqueId(App.getContext()));
        jSONObject.put("name", (Object) str);
        jSONObject.put("desc", (Object) str2);
        jSONObject.put("is_share", (Object) String.valueOf(i));
        jSONObject.put("lid", (Object) str3);
        jSONObject.put("sort_list", (Object) list);
        jSONObject.put("delete_list", (Object) list2);
        jSONObject.put("app_version", (Object) App.versionName);
        jSONObject.put("expired_date", (Object) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.Presenter
    public void doEditList(String str, String str2, int i, String str3, List<CreateMovieListModel> list, File file, List<CreateMovieListModel> list2) {
        MultipartBody.Part part;
        if (file != null) {
            part = MultipartBody.Part.createFormData(IDataSource.SCHEME_FILE_TAG, file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
        } else {
            part = null;
        }
        ((ObservableSubscribeProxy) Http.getService().uploadAvatar2(API.BASE_URL, buildEditData(str, str2, i, str3, list, list2), BuildConfig.APPLICATION_ID, App.versionCode, part).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListPresenter.2
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                CreateMovieListPresenter.this.getView().showLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str4) {
                CreateMovieListPresenter.this.getView().doCreateResult(true, str4);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                CreateMovieListPresenter.this.getView().hideLoadingView();
                CreateMovieListPresenter.this.getView().doCreateResult(false, th.getMessage());
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                CreateMovieListPresenter.this.getView().hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.Presenter
    public void doDeleteList(String str, String str2) {
        ((ObservableSubscribeProxy) Http.getService().Playlists_delete(API.BASE_URL, "Playlists_update", "del", str, str2, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListPresenter.3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                CreateMovieListPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str3) {
                super.onNext((AnonymousClass3) str3);
                CreateMovieListPresenter.this.getView().doDeleteListResult(true, str3);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                CreateMovieListPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                CreateMovieListPresenter.this.getView().hideLoadingView();
                CreateMovieListPresenter.this.getView().doDeleteListResult(false, apiException.getMessage());
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.Presenter
    public void loadList(String str, String str2, final int i, int i2, final AddMovieListDragAdapter addMovieListDragAdapter) {
        ((ObservableSubscribeProxy) Http.getService().Playlists_get(API.BASE_URL, "Playlists_get", str, str2, i, i2, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(MovieListDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<MovieListDetailModel>() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListPresenter.4
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (i == 1) {
                    CreateMovieListPresenter.this.getView().showLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(MovieListDetailModel movieListDetailModel) {
                super.onNext((AnonymousClass4) movieListDetailModel);
                if (i == 1) {
                    CreateMovieListPresenter.this.getView().loadDataComplete(movieListDetailModel);
                } else if (movieListDetailModel.getList() == null || movieListDetailModel.getList().isEmpty()) {
                    addMovieListDragAdapter.getLoadMore().loadMoreEnd();
                } else {
                    addMovieListDragAdapter.getLoadMore().loadMoreComplete();
                    CreateMovieListPresenter.this.getView().loadMoreDataComplete(movieListDetailModel);
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (i == 1) {
                    CreateMovieListPresenter.this.getView().hideLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (i == 1) {
                    addMovieListDragAdapter.getLoadMore().loadMoreFail();
                } else {
                    CreateMovieListPresenter.this.getView().hideLoading();
                }
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.Presenter
    public void uploadCover(String str, String str2, int i, String str3, List<CreateMovieListModel> list, File file, List<CreateMovieListModel> list2) {
        HashMap hashMap = new HashMap();
        hashMap.put("module", "Playlists_video_sort_v2");
        hashMap.put("uid", App.getUserData().uid_v2);
        hashMap.put("open_udid", SystemUtils.getUniqueId(App.getContext()));
        hashMap.put("name", str);
        hashMap.put("desc", str2);
        hashMap.put("is_share", String.valueOf(i));
        hashMap.put("lid", str3);
        hashMap.put("sort_list", list);
        hashMap.put("delete_list", list2);
        ObservableSource compose = Http.getService().Playlists_create(API.BASE_URL, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(hashMap))).compose(RxUtils.rxTranslateMsg());
        Observable<String> executeUploadObservable = new HttpUploadRequest().addBaseParams("Playlists_upload_cover", "image/jpg", file).addParam("pid", str3).executeUploadObservable();
        if (executeUploadObservable != null) {
            ((ObservableSubscribeProxy) Observable.zip(compose, executeUploadObservable, $$Lambda$CreateMovieListPresenter$l_mZ97xB7cJ_jtIOYaHH73feSDA.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListPresenter.5
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                    CreateMovieListPresenter.this.getView().showLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(String str4) {
                    CreateMovieListPresenter.this.getView().hideLoadingView();
                    CreateMovieListPresenter.this.getView().doCreateResult(true, str4);
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException apiException) {
                    CreateMovieListPresenter.this.getView().hideLoadingView();
                    CreateMovieListPresenter.this.getView().doCreateResult(false, apiException.getMessage());
                }
            });
        }
    }
}
