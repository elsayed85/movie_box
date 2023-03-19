package com.movieboxpro.android.view.listener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public interface IViewController {
    void hideLoading();

    void hideLoading(boolean z);

    void initData();

    void initPresenter();

    void initView();

    View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    void showAlert(String str);

    void showLoading();

    void showNoInternetDialog();

    void showToast(String str);

    <T> void updateView(T t);
}
