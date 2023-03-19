package com.movieboxpro.android.view.activity.managesubtitle;

import com.movieboxpro.android.model.DirectoryModel;
import io.reactivex.functions.Function;
import java.io.File;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitlePresenter$o24QZajD0MKldFcn9ihpnnRe1t4  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ManageSubtitlePresenter$o24QZajD0MKldFcn9ihpnnRe1t4 implements Function {
    public static final /* synthetic */ $$Lambda$ManageSubtitlePresenter$o24QZajD0MKldFcn9ihpnnRe1t4 INSTANCE = new $$Lambda$ManageSubtitlePresenter$o24QZajD0MKldFcn9ihpnnRe1t4();

    private /* synthetic */ $$Lambda$ManageSubtitlePresenter$o24QZajD0MKldFcn9ihpnnRe1t4() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        DirectoryModel m376loadAllSubtitleFiles$lambda8;
        m376loadAllSubtitleFiles$lambda8 = ManageSubtitlePresenter.m376loadAllSubtitleFiles$lambda8((File) obj);
        return m376loadAllSubtitleFiles$lambda8;
    }
}
