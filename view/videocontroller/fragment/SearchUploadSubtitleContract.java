package com.movieboxpro.android.view.videocontroller.fragment;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.WifiUploadSubtitleModel;
import java.util.List;
import kotlin.Metadata;
/* compiled from: SearchUploadSubtitleContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface SearchUploadSubtitleContract {

    /* compiled from: SearchUploadSubtitleContract.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract$View;", "loadSubtitle", "", "dir", "", "lastSelectedPath", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void loadSubtitle(String str, String str2);
    }

    /* compiled from: SearchUploadSubtitleContract.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showSubtitles", "", "models", "", "Lcom/movieboxpro/android/model/WifiUploadSubtitleModel;", "lastSelectedPosition", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void showSubtitles(List<WifiUploadSubtitleModel> list, int i);
    }
}
