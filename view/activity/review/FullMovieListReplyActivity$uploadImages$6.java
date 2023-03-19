package com.movieboxpro.android.view.activity.review;

import android.content.Intent;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.ShellUtil;
import java.util.List;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FullMovieListReplyActivity.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FullMovieListReplyActivity$uploadImages$6 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Ref.ObjectRef<List<String>> $idList;
    final /* synthetic */ FullMovieListReplyActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullMovieListReplyActivity$uploadImages$6(FullMovieListReplyActivity fullMovieListReplyActivity, Ref.ObjectRef<List<String>> objectRef) {
        super(1);
        this.this$0 = fullMovieListReplyActivity;
        this.$idList = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String str) {
        this.this$0.hideLoadingView();
        CommonExtKt.logD(this.this$0, str);
        StringBuilder sb = new StringBuilder();
        List<String> list = this.$idList.element;
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str2 = (String) obj;
                if (i == list.size() - 1) {
                    sb.append(str2);
                } else {
                    sb.append(str2);
                    sb.append(",");
                }
                i = i2;
            }
        }
        String replaceAll = Pattern.compile(ShellUtil.COMMAND_LINE_END).matcher(str).replaceAll("");
        Intent intent = new Intent();
        intent.putExtra(FirebaseAnalytics.Param.CONTENT, replaceAll);
        intent.putExtra("userFile", sb.toString());
        intent.putExtra("htmlOn", 1);
        this.this$0.setResult(-1, intent);
        this.this$0.finish();
    }
}
