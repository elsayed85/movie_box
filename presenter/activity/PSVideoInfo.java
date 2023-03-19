package com.movieboxpro.android.presenter.activity;

import com.movieboxpro.android.model.detail.AbstractVideoBean;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.view.activity.detail.IMovieDetail;
/* loaded from: classes3.dex */
public abstract class PSVideoInfo implements IPresenter {
    AbstractVideoBean abstractVideoBean;
    public IMovieDetail mViewController;

    public abstract void getInfo(String str, String str2);

    @Override // com.movieboxpro.android.presenter.IPresenter
    public void loadData() {
    }

    private PSVideoInfo() {
    }

    public PSVideoInfo(IMovieDetail iMovieDetail) {
        this.mViewController = iMovieDetail;
    }

    @Override // com.movieboxpro.android.presenter.IPresenter
    public void detachView() {
        this.mViewController = null;
    }
}
