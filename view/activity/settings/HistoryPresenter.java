package com.movieboxpro.android.view.activity.settings;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.settings.HistoryContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
/* loaded from: classes3.dex */
public class HistoryPresenter extends BasePresenter<HistoryContract.View> implements HistoryContract.Presenter {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HistoryPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override // com.movieboxpro.android.view.activity.settings.HistoryContract.Presenter
    public void clearHistory(String str) {
        ((ObservableSubscribeProxy) Http.getService().MovieRecord(API.BASE_URL, API.SETTING.MOVIERECORD, str, "delall", "", "", "").compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.HistoryPresenter.1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                HistoryPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass1) str2);
                HistoryPresenter.this.getView().clearHistoryResult(true, str2);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                HistoryPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                HistoryPresenter.this.getView().hideLoadingView();
                HistoryPresenter.this.getView().clearHistoryResult(false, apiException.getMessage());
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.settings.HistoryContract.Presenter
    public void deleteHistory(String str, String str2, int i) {
        ((ObservableSubscribeProxy) Http.getService().deleteRecord(API.BASE_URL, API.SETTING.MOVIERECORD, str, "del", str2, "", "", i).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.activity.settings.HistoryPresenter.2
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                HistoryPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str3) {
                super.onNext((AnonymousClass2) str3);
                HistoryPresenter.this.getView().deleteHistoryResult(true, str3);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                HistoryPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                HistoryPresenter.this.getView().hideLoadingView();
                HistoryPresenter.this.getView().deleteHistoryResult(false, apiException.getMessage());
            }
        });
    }
}
