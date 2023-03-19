package com.movieboxpro.android.view.activity.subtitlecheck;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckContract;
import com.movieboxpro.android.view.videocontroller.LrcCheckController;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: SubtitleCheckActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00192\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0019B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u0002H\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0016J\b\u0010\u000e\u001a\u00020\u0006H\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0014J\b\u0010\u0011\u001a\u00020\fH\u0014J\b\u0010\u0012\u001a\u00020\fH\u0014J$\u0010\u0013\u001a\u00020\f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckPresenter;", "Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckContract$View;", "()V", "count", "", "sid", "", "videoType", "bindPresenter", "feedbackSuccess", "", IjkMediaMeta.IJKM_KEY_TYPE, "getLayoutResId", "initData", "initListener", "initView", "requestData", "showFeedbackData", "subtitleFeedbackList", "", "Lcom/dueeeke/model/SubTitleFeedbackModel;", "subtitleList", "Lcom/dueeeke/model/SrtPraseModel;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleCheckActivity extends BaseMvpActivity<SubtitleCheckPresenter> implements SubtitleCheckContract.View {
    public static final Companion Companion = new Companion(null);
    private int count;
    private int videoType;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String sid = "";

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_subtitle_check;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        if (!getIntent().getBooleanExtra("IsDownload", false)) {
            ((LrcCheckController) _$_findCachedViewById(R.id.lrcView)).setConfirmText("Download");
        } else {
            ((LrcCheckController) _$_findCachedViewById(R.id.lrcView)).setConfirmText("");
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.count = getIntent().getIntExtra("Count", 0);
        this.videoType = getIntent().getIntExtra("Type", 0);
        String stringExtra = getIntent().getStringExtra("Sid");
        if (stringExtra == null) {
            stringExtra = "";
        }
        this.sid = stringExtra;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        SubtitleCheckPresenter subtitleCheckPresenter = (SubtitleCheckPresenter) this.mPresenter;
        int i = this.videoType;
        String str = this.sid;
        String stringExtra = getIntent().getStringExtra("Url");
        if (stringExtra == null) {
            stringExtra = "";
        }
        subtitleCheckPresenter.loadFeedbackData(i, str, stringExtra);
    }

    @Override // com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckContract.View
    public void showFeedbackData(List<? extends SubTitleFeedbackModel> subtitleFeedbackList, List<? extends SrtPraseModel> subtitleList) {
        Intrinsics.checkNotNullParameter(subtitleFeedbackList, "subtitleFeedbackList");
        Intrinsics.checkNotNullParameter(subtitleList, "subtitleList");
        ((LrcCheckController) _$_findCachedViewById(R.id.lrcView)).setDataList("", -1, getIntent().getIntExtra("Count", 0), subtitleFeedbackList, subtitleList, null, new LrcCheckController.SubtitleFeedbackCallback() { // from class: com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckActivity$showFeedbackData$1
            @Override // com.movieboxpro.android.view.videocontroller.LrcCheckController.SubtitleFeedbackCallback
            public void onCancel() {
                SubtitleCheckActivity.this.finish();
            }

            @Override // com.movieboxpro.android.view.videocontroller.LrcCheckController.SubtitleFeedbackCallback
            public void onSelected(SrtPraseModel srtPraseModel, boolean z, List<SrtPraseModel> list) {
                SubtitleCheckActivity.this.setResult(-1);
                SubtitleCheckActivity.this.finish();
            }

            @Override // com.movieboxpro.android.view.videocontroller.LrcCheckController.SubtitleFeedbackCallback
            public void onVoteClick(int i) {
                BaseContract.BasePresenter basePresenter;
                int i2;
                String str;
                basePresenter = SubtitleCheckActivity.this.mPresenter;
                i2 = SubtitleCheckActivity.this.videoType;
                str = SubtitleCheckActivity.this.sid;
                ((SubtitleCheckPresenter) basePresenter).voteFeedback(i2, i, str);
            }
        });
    }

    @Override // com.movieboxpro.android.view.activity.subtitlecheck.SubtitleCheckContract.View
    public void feedbackSuccess(int i) {
        ((LrcCheckController) _$_findCachedViewById(R.id.lrcView)).voteSuccess(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public SubtitleCheckPresenter bindPresenter() {
        return new SubtitleCheckPresenter(this);
    }

    /* compiled from: SubtitleCheckActivity.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/subtitlecheck/SubtitleCheckActivity$Companion;", "", "()V", "starter", "", "context", "Landroid/app/Activity;", "count", "", "sid", "", IjkMediaMeta.IJKM_KEY_TYPE, "url", ChooseActivity.ISDOWNLOAD, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void starter(Activity context, int i, String sid, int i2, String url, boolean z) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sid, "sid");
            Intrinsics.checkNotNullParameter(url, "url");
            Intent intent = new Intent(context, SubtitleCheckActivity.class);
            intent.putExtra("Count", i);
            intent.putExtra("Sid", sid);
            intent.putExtra("Type", i2);
            intent.putExtra("Url", url);
            intent.putExtra("IsDownload", z);
            context.startActivityForResult(intent, 1);
        }
    }
}
