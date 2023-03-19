package com.movieboxpro.android.view.activity.managesubtitle;

import com.movieboxpro.android.model.DirectoryModel;
import io.reactivex.functions.Function;
import java.io.File;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitlePresenter$Y1wgvsTxZHAstnNPtqwPNbRPM4c  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ManageSubtitlePresenter$Y1wgvsTxZHAstnNPtqwPNbRPM4c implements Function {
    public static final /* synthetic */ $$Lambda$ManageSubtitlePresenter$Y1wgvsTxZHAstnNPtqwPNbRPM4c INSTANCE = new $$Lambda$ManageSubtitlePresenter$Y1wgvsTxZHAstnNPtqwPNbRPM4c();

    private /* synthetic */ $$Lambda$ManageSubtitlePresenter$Y1wgvsTxZHAstnNPtqwPNbRPM4c() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        DirectoryModel m377loadAllSubtitleFiles$lambda9;
        m377loadAllSubtitleFiles$lambda9 = ManageSubtitlePresenter.m377loadAllSubtitleFiles$lambda9((File) obj);
        return m377loadAllSubtitleFiles$lambda9;
    }
}
