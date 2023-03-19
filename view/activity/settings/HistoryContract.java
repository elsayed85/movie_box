package com.movieboxpro.android.view.activity.settings;

import com.movieboxpro.android.base.mvp.BaseContract;
/* loaded from: classes3.dex */
public interface HistoryContract {

    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void clearHistory(String str);

        void deleteHistory(String str, String str2, int i);
    }

    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void clearHistoryResult(boolean z, String str);

        void deleteHistoryResult(boolean z, String str);
    }
}
