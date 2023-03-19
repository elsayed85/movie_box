package com.movieboxpro.android.view.activity.review;

import com.movieboxpro.android.utils.CommonExtKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FullReplyActivity.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FullReplyActivity$uploadImages$6 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Ref.ObjectRef<List<String>> $idList;
    final /* synthetic */ FullReplyActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FullReplyActivity$uploadImages$6(FullReplyActivity fullReplyActivity, Ref.ObjectRef<List<String>> objectRef) {
        super(1);
        this.this$0 = fullReplyActivity;
        this.$idList = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        this.this$0.hideLoadingView();
        CommonExtKt.logD(this.this$0, it);
        StringBuilder sb = new StringBuilder();
        List<String> list = this.$idList.element;
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str = (String) obj;
                if (i == list.size() - 1) {
                    sb.append(str);
                } else {
                    sb.append(str);
                    sb.append(",");
                }
                i = i2;
            }
        }
        FullReplyActivity fullReplyActivity = this.this$0;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "userFile.toString()");
        fullReplyActivity.addReply(it, 1, sb2);
    }
}
