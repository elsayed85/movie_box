package com.movieboxpro.android.view.activity.settings;

import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.view.activity.settings.TestSpeedActivity;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* compiled from: TestSpeedActivity.kt */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/movieboxpro/android/view/activity/settings/TestSpeedActivity$singleTest$1$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TestSpeedActivity$singleTest$1$1 implements Callback {
    final /* synthetic */ File $file;
    final /* synthetic */ TestSpeedActivity.OnTestSpeedListener $listener;
    final /* synthetic */ NetTestModel $model;
    final /* synthetic */ int $position;
    final /* synthetic */ Ref.BooleanRef $success;
    final /* synthetic */ TestSpeedActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TestSpeedActivity$singleTest$1$1(TestSpeedActivity testSpeedActivity, NetTestModel netTestModel, int i, TestSpeedActivity.OnTestSpeedListener onTestSpeedListener, Ref.BooleanRef booleanRef, File file) {
        this.this$0 = testSpeedActivity;
        this.$model = netTestModel;
        this.$position = i;
        this.$listener = onTestSpeedListener;
        this.$success = booleanRef;
        this.$file = file;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        RecyclerView recyclerView;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        if (this.this$0.isDestroyed() || (recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.recyclerView)) == null) {
            return;
        }
        final TestSpeedActivity testSpeedActivity = this.this$0;
        final NetTestModel netTestModel = this.$model;
        final int i = this.$position;
        final TestSpeedActivity.OnTestSpeedListener onTestSpeedListener = this.$listener;
        recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$singleTest$1$1$pWLB8RL6hw_EWR3hnquzvzhpvW0
            @Override // java.lang.Runnable
            public final void run() {
                TestSpeedActivity$singleTest$1$1.m831onFailure$lambda0(TestSpeedActivity.this, netTestModel, i, onTestSpeedListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onFailure$lambda-0  reason: not valid java name */
    public static final void m831onFailure$lambda0(TestSpeedActivity this$0, NetTestModel model, int i, TestSpeedActivity.OnTestSpeedListener onTestSpeedListener) {
        HashMap hashMap;
        CommMultiBaseAdapter commMultiBaseAdapter;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(model, "$model");
        hashMap = this$0.disposeMap;
        Disposable disposable = (Disposable) hashMap.get(model.getNewUrl());
        if (disposable != null) {
            disposable.dispose();
        }
        model.setStatus(2);
        commMultiBaseAdapter = this$0.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyItemChanged(i);
        this$0.deleteCacheFile(model.id);
        if (onTestSpeedListener == null) {
            return;
        }
        onTestSpeedListener.onFailed(i);
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        if (this.this$0.isDestroyed()) {
            return;
        }
        if (response.isSuccessful()) {
            this.$success.element = true;
            NetTestModel netTestModel = this.$model;
            ResponseBody body = response.body();
            netTestModel.setContentLength(body == null ? 0L : body.contentLength());
            this.$model.startTime = Long.valueOf(System.currentTimeMillis());
            FileUtils.createOrExistsDir(new File(Constant.DIR_DOWNLOAD_TEST_SPEED));
            File file = this.$file;
            ResponseBody body2 = response.body();
            FileUtils.writeFileFromIS(file, body2 == null ? null : body2.byteStream());
            return;
        }
        RecyclerView recyclerView = (RecyclerView) this.this$0._$_findCachedViewById(R.id.recyclerView);
        if (recyclerView == null) {
            return;
        }
        final NetTestModel netTestModel2 = this.$model;
        final TestSpeedActivity testSpeedActivity = this.this$0;
        final int i = this.$position;
        final TestSpeedActivity.OnTestSpeedListener onTestSpeedListener = this.$listener;
        recyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$TestSpeedActivity$singleTest$1$1$aajf_UMoMitx-l2Nub8EPefzZ9U
            @Override // java.lang.Runnable
            public final void run() {
                TestSpeedActivity$singleTest$1$1.m832onResponse$lambda1(NetTestModel.this, testSpeedActivity, i, onTestSpeedListener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onResponse$lambda-1  reason: not valid java name */
    public static final void m832onResponse$lambda1(NetTestModel model, TestSpeedActivity this$0, int i, TestSpeedActivity.OnTestSpeedListener onTestSpeedListener) {
        CommMultiBaseAdapter commMultiBaseAdapter;
        HashMap hashMap;
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        model.setStatus(2);
        commMultiBaseAdapter = this$0.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyItemChanged(i);
        hashMap = this$0.disposeMap;
        Disposable disposable = (Disposable) hashMap.get(model.getNewUrl());
        if (disposable != null) {
            disposable.dispose();
        }
        this$0.deleteCacheFile(model.id);
        if (onTestSpeedListener == null) {
            return;
        }
        onTestSpeedListener.onFailed(i);
    }
}
