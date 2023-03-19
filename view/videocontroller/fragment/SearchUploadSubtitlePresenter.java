package com.movieboxpro.android.view.videocontroller.fragment;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.model.WifiUploadSubtitleModel;
import com.movieboxpro.android.utils.FileUtil;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
/* compiled from: SearchUploadSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitlePresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract$View;", "Lcom/movieboxpro/android/view/videocontroller/fragment/SearchUploadSubtitleContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "loadSubtitle", "", "dir", "", "lastSelectedPath", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SearchUploadSubtitlePresenter extends BasePresenter<SearchUploadSubtitleContract.View> implements SearchUploadSubtitleContract.Presenter {
    public SearchUploadSubtitlePresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.SearchUploadSubtitleContract.Presenter
    public void loadSubtitle(String dir, final String lastSelectedPath) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(lastSelectedPath, "lastSelectedPath");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        Object as = Observable.just(new File(dir)).map(new Function() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$SearchUploadSubtitlePresenter$Me5WG8BYepfVLsDv5IH1p5XrV_o
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m1379loadSubtitle$lambda2;
                m1379loadSubtitle$lambda2 = SearchUploadSubtitlePresenter.m1379loadSubtitle$lambda2(lastSelectedPath, intRef, (File) obj);
                return m1379loadSubtitle$lambda2;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "just(File(dir))\n        …leOwner(mLifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, null, null, null, new SearchUploadSubtitlePresenter$loadSubtitle$2(this, intRef), 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadSubtitle$lambda-2  reason: not valid java name */
    public static final List m1379loadSubtitle$lambda2(String lastSelectedPath, Ref.IntRef lastSelectedPosition, File it) {
        File[] fileArr;
        String name;
        String name2;
        Intrinsics.checkNotNullParameter(lastSelectedPath, "$lastSelectedPath");
        Intrinsics.checkNotNullParameter(lastSelectedPosition, "$lastSelectedPosition");
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        if (it.exists() && it.isDirectory()) {
            File[] listFiles = it.listFiles();
            Intrinsics.checkNotNullExpressionValue(listFiles, "it.listFiles()");
            File[] fileArr2 = listFiles;
            int length = fileArr2.length;
            int i = 0;
            while (i < length) {
                File file = fileArr2[i];
                i++;
                File file2 = file;
                int i2 = 2;
                if (file2.isDirectory()) {
                    File[] listFiles2 = file2.listFiles();
                    Intrinsics.checkNotNullExpressionValue(listFiles2, "file.listFiles()");
                    File[] fileArr3 = listFiles2;
                    int length2 = fileArr3.length;
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < length2) {
                        File file3 = fileArr3[i3];
                        i3++;
                        int i5 = i4 + 1;
                        File file4 = file3;
                        String name3 = file4.getName();
                        Intrinsics.checkNotNullExpressionValue(name3, "file2.name");
                        List split$default = StringsKt.split$default((CharSequence) name3, new String[]{"_"}, false, 0, 6, (Object) null);
                        if (split$default.size() >= i2) {
                            name2 = (String) split$default.get(1);
                        } else {
                            name2 = file4.getName();
                        }
                        Intrinsics.checkNotNullExpressionValue(name2, "name");
                        String formatFileSizeToString = FileUtil.formatFileSizeToString(file4.length());
                        File[] fileArr4 = fileArr2;
                        Intrinsics.checkNotNullExpressionValue(formatFileSizeToString, "formatFileSizeToString(file2.length())");
                        WifiUploadSubtitleModel wifiUploadSubtitleModel = new WifiUploadSubtitleModel(name2, file4, formatFileSizeToString, TimeUtils.formatTime3(file4.lastModified()));
                        if (Intrinsics.areEqual(lastSelectedPath, file4.getPath())) {
                            wifiUploadSubtitleModel.setChecked(true);
                            lastSelectedPosition.element = i4;
                        }
                        arrayList.add(wifiUploadSubtitleModel);
                        i4 = i5;
                        fileArr2 = fileArr4;
                        i2 = 2;
                    }
                    fileArr = fileArr2;
                } else {
                    fileArr = fileArr2;
                    String name4 = file2.getName();
                    Intrinsics.checkNotNullExpressionValue(name4, "file.name");
                    List split$default2 = StringsKt.split$default((CharSequence) name4, new String[]{"_"}, false, 0, 6, (Object) null);
                    if (split$default2.size() >= 2) {
                        name = (String) split$default2.get(1);
                    } else {
                        name = file2.getName();
                    }
                    Intrinsics.checkNotNullExpressionValue(name, "name");
                    String formatFileSizeToString2 = FileUtil.formatFileSizeToString(file2.length());
                    Intrinsics.checkNotNullExpressionValue(formatFileSizeToString2, "formatFileSizeToString(file.length())");
                    arrayList.add(new WifiUploadSubtitleModel(name, file2, formatFileSizeToString2, TimeUtils.formatTime3(file2.lastModified())));
                }
                fileArr2 = fileArr;
            }
        }
        return arrayList;
    }
}
