package com.movieboxpro.android.view.activity;

import com.movieboxpro.android.utils.LogUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: MyCommentActivity.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class MyCommentActivity$MyCommentListFragment$likeReview$2 extends Lambda implements Function1<String, Unit> {
    public static final MyCommentActivity$MyCommentListFragment$likeReview$2 INSTANCE = new MyCommentActivity$MyCommentListFragment$likeReview$2();

    MyCommentActivity$MyCommentListFragment$likeReview$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LogUtils.INSTANCE.logD("");
    }
}
