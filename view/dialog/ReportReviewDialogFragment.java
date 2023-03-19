package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseFullScreenDialogFragment;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: ReportReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReportReviewDialogFragment;", "Lcom/movieboxpro/android/base/BaseFullScreenDialogFragment;", "()V", "id", "", "bindLayout", "", "initData", "", "initListener", "initView", "sendReport", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ReportReviewDialogFragment extends BaseFullScreenDialogFragment {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String id;

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    protected int bindLayout() {
        return R.layout.dialog_report_review;
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void initData() {
        Bundle arguments = getArguments();
        this.id = arguments == null ? null : arguments.getString("id");
    }

    @Override // com.movieboxpro.android.base.BaseFullScreenDialogFragment
    public void initListener() {
        TextView textView;
        TextView textView2;
        View view = getView();
        if (view != null && (textView2 = (TextView) view.findViewById(R.id.tvCancel)) != null) {
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReportReviewDialogFragment$EHgq1ksmJf69K4QLBFwF7fWUuR0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ReportReviewDialogFragment.m1057initListener$lambda0(ReportReviewDialogFragment.this, view2);
                }
            });
        }
        View view2 = getView();
        if (view2 == null || (textView = (TextView) view2.findViewById(R.id.tvSend)) == null) {
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReportReviewDialogFragment$5Hys65t35WsMKMeOr5jcU4hDraI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view3) {
                ReportReviewDialogFragment.m1058initListener$lambda1(ReportReviewDialogFragment.this, view3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1057initListener$lambda0(ReportReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1058initListener$lambda1(ReportReviewDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendReport();
    }

    private final void sendReport() {
        EditText editText;
        Bundle arguments = getArguments();
        Integer valueOf = arguments == null ? null : Integer.valueOf(arguments.getInt(IjkMediaMeta.IJKM_KEY_TYPE));
        boolean z = true;
        if ((valueOf == null || valueOf.intValue() != 1) && (valueOf == null || valueOf.intValue() != 2)) {
            z = false;
        }
        String str = "movie";
        if (!z) {
            if (valueOf != null && valueOf.intValue() == 3) {
                str = "playlist";
            } else if (valueOf != null && valueOf.intValue() == 4) {
                str = "actor";
            }
        }
        View view = getView();
        String valueOf2 = String.valueOf((view == null || (editText = (EditText) view.findViewById(R.id.editText)) == null) ? null : editText.getText());
        HttpRequest param = HttpRequest.Companion.post("Comment_report").param(IjkMediaMeta.IJKM_KEY_TYPE, str);
        Bundle arguments2 = getArguments();
        HttpRequest param2 = param.param("comment_id", arguments2 == null ? null : arguments2.getString("commentId"));
        Bundle arguments3 = getArguments();
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(param2.param("reason_id", arguments3 != null ? arguments3.getString("reasonId") : null).param("reason", valueOf2).asRequest(), this), new ReportReviewDialogFragment$sendReport$1(this), null, new ReportReviewDialogFragment$sendReport$2(this), null, new ReportReviewDialogFragment$sendReport$3(this), 10, null);
    }

    /* compiled from: ReportReviewDialogFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ReportReviewDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ReportReviewDialogFragment;", "commentId", "", "reasonId", IjkMediaMeta.IJKM_KEY_TYPE, "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ReportReviewDialogFragment newInstance(String commentId, String reasonId, int i) {
            Intrinsics.checkNotNullParameter(commentId, "commentId");
            Intrinsics.checkNotNullParameter(reasonId, "reasonId");
            ReportReviewDialogFragment reportReviewDialogFragment = new ReportReviewDialogFragment();
            reportReviewDialogFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("commentId", commentId), TuplesKt.to("reasonId", reasonId), TuplesKt.to(IjkMediaMeta.IJKM_KEY_TYPE, Integer.valueOf(i))));
            return reportReviewDialogFragment;
        }
    }
}
