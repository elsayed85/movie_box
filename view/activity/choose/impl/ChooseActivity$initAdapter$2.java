package com.movieboxpro.android.view.activity.choose.impl;

import com.movieboxpro.android.model.BaseMediaModel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* compiled from: ChooseActivity.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "t", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "invoke", "(Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;)Ljava/lang/Integer;"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ChooseActivity$initAdapter$2 extends Lambda implements Function1<BaseMediaModel.DownloadFile, Integer> {
    public static final ChooseActivity$initAdapter$2 INSTANCE = new ChooseActivity$initAdapter$2();

    ChooseActivity$initAdapter$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Integer invoke(BaseMediaModel.DownloadFile t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return Integer.valueOf(t.viewType);
    }
}
