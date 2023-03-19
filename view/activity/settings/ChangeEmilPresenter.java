package com.movieboxpro.android.view.activity.settings;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.settings.ChangeEmilContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChangeEmilPresenter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/ChangeEmilPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/settings/ChangeEmilContract$View;", "Lcom/movieboxpro/android/view/activity/settings/ChangeEmilContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "changeEmail", "", "email", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChangeEmilPresenter extends BasePresenter<ChangeEmilContract.View> implements ChangeEmilContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChangeEmilPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.settings.ChangeEmilContract.Presenter
    public void changeEmail(String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        Object as = Http.getService().userprofile(API.BASE_URL, API.USER.PROFILE, App.getUserData().uid_v2, "", "", "", "", "", email, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "getService().userprofile…leOwner(mLifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new ChangeEmilPresenter$changeEmail$1(this), null, new ChangeEmilPresenter$changeEmail$2(this), null, new ChangeEmilPresenter$changeEmail$3(this), 10, null);
    }
}
