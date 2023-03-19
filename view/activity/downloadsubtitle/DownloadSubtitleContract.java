package com.movieboxpro.android.view.activity.downloadsubtitle;

import com.dueeeke.model.ResponseSubtitleRecord;
import com.movieboxpro.android.base.mvp.BaseContract;
import java.util.List;
import kotlin.Metadata;
/* compiled from: DownloadSubtitleContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface DownloadSubtitleContract {

    /* compiled from: DownloadSubtitleContract.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J,\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\u0007H&J(\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH&¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract$View;", "downloadSubtitles", "", "urls", "", "", "sidList", "downloadDir", "loadSubtitle", "id", "fid", "season", "", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void downloadSubtitles(List<String> list, List<String> list2, String str);

        void loadSubtitle(String str, String str2, int i, int i2);
    }

    /* compiled from: DownloadSubtitleContract.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/downloadsubtitle/DownloadSubtitleContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "downloadSubtitlesComplete", "", "sidList", "", "", "showSubtitle", "model", "Lcom/dueeeke/model/ResponseSubtitleRecord;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void downloadSubtitlesComplete(List<String> list);

        void showSubtitle(ResponseSubtitleRecord responseSubtitleRecord);
    }
}
