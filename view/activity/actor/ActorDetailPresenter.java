package com.movieboxpro.android.view.activity.actor;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.ActorDetailModel;
import com.movieboxpro.android.utils.ApiUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.actor.ActorDetailContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ActorDetailPresenter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/actor/ActorDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/actor/ActorDetailContract$View;", "Lcom/movieboxpro/android/view/activity/actor/ActorDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "getReviewNum", "", "id", "", "requestDetail", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ActorDetailPresenter extends BasePresenter<ActorDetailContract.View> implements ActorDetailContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActorDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.actor.ActorDetailContract.Presenter
    public void requestDetail(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        ((ObservableSubscribeProxy) Http.getService().ActorDetail(API.BASE_URL, "Actor", id).compose(RxUtils.rxTranslate2Bean(ActorDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<ActorDetailModel>() { // from class: com.movieboxpro.android.view.activity.actor.ActorDetailPresenter$requestDetail$1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ActorDetailPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(ActorDetailModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                ActorDetailPresenter.this.getView().hideLoading();
                ActorDetailPresenter.this.getView().showInfo(model);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ActorDetailPresenter.this.getView().hideLoading();
                ToastUtils.showShort(Intrinsics.stringPlus("load failed:", e.getMessage()), new Object[0]);
            }
        });
    }

    public final void getReviewNum(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<String> asRequest = HttpRequest.Companion.post(ApiUtils.INSTANCE.reviewNum(4)).param("box_type", (Object) 4).param("mid", id).param("pid", id).param("actor_id", id).asRequest();
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> compose = asRequest.compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(compose, mLifecycleOwner), null, null, null, null, new ActorDetailPresenter$getReviewNum$1(this), 15, null);
    }
}
