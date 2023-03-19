package com.movieboxpro.android.listener;
/* loaded from: classes.dex */
public interface OnFavoriteStatusChangedListener {
    void hideLoadLoading();

    void onFavoriteStatusChanged(boolean z, int i);

    void showLoadLoading();

    void showViewToast(String str);
}
