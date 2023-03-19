package com.movieboxpro.android.base.mvp;
/* loaded from: classes3.dex */
public class BaseContract {

    /* loaded from: classes3.dex */
    public interface BasePresenter<T extends BaseView> {
        void attachView(T t);

        void detachView();

        T getView();

        boolean isViewAttached();
    }

    /* loaded from: classes.dex */
    public interface BaseView {
        void hideLoading();

        void hideLoadingView();

        void showEmptyState();

        void showError(String str);

        void showErrorPage();

        void showFinish(String str);

        void showLoading();

        void showLoadingView();
    }
}
