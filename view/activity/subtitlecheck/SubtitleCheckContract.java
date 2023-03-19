package com.movieboxpro.android.view.activity.subtitlecheck;

import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.movieboxpro.android.base.mvp.BaseContract;
import java.util.List;
import kotlin.Metadata;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: SubtitleCheckContract.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract;", "", "Presenter", "View", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface SubtitleCheckContract {

    /* compiled from: SubtitleCheckContract.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&J \u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract$View;", "loadFeedbackData", "", IjkMediaMeta.IJKM_KEY_TYPE, "", "sid", "", "url", "voteFeedback", "videoType", "feedbackType", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void loadFeedbackData(int i, String str, String str2);

        void voteFeedback(int i, int i2, String str);
    }

    /* compiled from: SubtitleCheckContract.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J$\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bH&¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "feedbackSuccess", "", IjkMediaMeta.IJKM_KEY_TYPE, "", "showFeedbackData", "subtitleFeedbackList", "", "Lcom/dueeeke/model/SubTitleFeedbackModel;", "subtitleList", "Lcom/dueeeke/model/SrtPraseModel;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface View extends BaseContract.BaseView {
        void feedbackSuccess(int i);

        void showFeedbackData(List<? extends SubTitleFeedbackModel> list, List<? extends SrtPraseModel> list2);
    }
}
