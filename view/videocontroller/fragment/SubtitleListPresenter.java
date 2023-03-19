package com.movieboxpro.android.view.videocontroller.fragment;

import androidx.lifecycle.LifecycleOwner;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.dueeeke.model.SRTModel;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.MySubtitleResponse;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.videocontroller.fragment.SubtitleListContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: SubtitleListPresenter.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J?\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract$View;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SubtitleListContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getSubtitleData", "", "fid", "", "id", "boxType", "", "season", "episode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleListPresenter extends BasePresenter<SubtitleListContract.View> implements SubtitleListContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SubtitleListPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.SubtitleListContract.Presenter
    public void getSubtitleData(String str, String str2, Integer num, Integer num2, Integer num3) {
        Object as = Observable.zip(HttpRequest.Companion.post((num != null && num.intValue() == 1) ? API.Movie.MOVIE_SRTLIST_V2 : API.Tv.TV_SRTLIST_V2).param("mid", str2).param("tid", str2).param("fid", str).param("season", num2).param("episode", num3).asRequest().compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class)).subscribeOn(Schedulers.io()), HttpRequest.Companion.post("My_srt").param("mid", str2).param("box_type", num).param("season", num2).param("episode", num3).asRequest().compose(RxUtils.rxTranslate2Bean(MySubtitleResponse.class)).subscribeOn(Schedulers.io()), $$Lambda$SubtitleListPresenter$cZ__Mrw4XUk4yRdostKQkg__w.INSTANCE).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "zip<ResponseSubtitleReco…leOwner(mLifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new SubtitleListPresenter$getSubtitleData$2(this), null, new SubtitleListPresenter$getSubtitleData$3(this), null, new SubtitleListPresenter$getSubtitleData$4(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getSubtitleData$lambda-0  reason: not valid java name */
    public static final ResponseSubtitleRecord m1385getSubtitleData$lambda0(ResponseSubtitleRecord subtitleRecord, MySubtitleResponse dstr$srt_list) {
        Intrinsics.checkNotNullParameter(subtitleRecord, "subtitleRecord");
        Intrinsics.checkNotNullParameter(dstr$srt_list, "$dstr$srt_list");
        ArrayList<SRTModel.SubTitles> component1 = dstr$srt_list.component1();
        if (component1 != null && !component1.isEmpty()) {
            SRTModel sRTModel = new SRTModel();
            sRTModel.language = "My Subtitles";
            sRTModel.subtitles = component1;
            subtitleRecord.getList().add(0, sRTModel);
        }
        return subtitleRecord;
    }
}
