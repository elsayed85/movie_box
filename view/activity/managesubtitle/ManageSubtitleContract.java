package com.movieboxpro.android.view.activity.managesubtitle;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.DirectoryModel;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.List;
import kotlin.Metadata;
/* compiled from: ManageSubtitleContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ManageSubtitleContract {

    /* compiled from: ManageSubtitleContract.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0016\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract$View;", "deleteFile", "", "list", "", "Lcom/movieboxpro/android/model/DirectoryModel;", "loadAllSubtitleFiles", "loadSubtitleFiles", FileDownloadService.PARAMS_KEY_PATH, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void deleteFile(List<DirectoryModel> list);

        void loadAllSubtitleFiles();

        void loadSubtitleFiles(String str);
    }

    /* compiled from: ManageSubtitleContract.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\u00020\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "deleteFileComplete", "", "showFiles", "list", "", "Lcom/movieboxpro/android/model/DirectoryModel;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void deleteFileComplete();

        void showFiles(List<DirectoryModel> list);
    }
}
