package com.movieboxpro.android.view.activity.managesubtitle;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.model.DirectoryModel;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
/* compiled from: ManageSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\n2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016Jx\u0010\u0013\u001aj\u0012.\u0012,\u0012\u0004\u0012\u00020\u000b \u0017*\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\u00160\u0015j\b\u0012\u0004\u0012\u00020\u000b`\u0016 \u0017*4\u0012.\u0012,\u0012\u0004\u0012\u00020\u000b \u0017*\u0016\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u000b\u0018\u0001`\u00160\u0015j\b\u0012\u0004\u0012\u00020\u000b`\u0016\u0018\u00010\u00140\u00142\u0006\u0010\u0011\u001a\u00020\u0012H\u0002¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitlePresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract$View;", "Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "deleteFile", "", "list", "", "Lcom/movieboxpro/android/model/DirectoryModel;", "listSubtitleFiles", "Ljava/io/File;", IDataSource.SCHEME_FILE_TAG, "loadAllSubtitleFiles", "loadSubtitleFiles", FileDownloadService.PARAMS_KEY_PATH, "", "loadSubtitleFilesObservable", "Lio/reactivex/Observable;", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ManageSubtitlePresenter extends BasePresenter<ManageSubtitleContract.View> implements ManageSubtitleContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManageSubtitlePresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract.Presenter
    public void deleteFile(List<DirectoryModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ((ObservableSubscribeProxy) Observable.just(list).map($$Lambda$ManageSubtitlePresenter$Wxp_ONsGFkQMnqksdgCZ0_EKXXE.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitlePresenter$deleteFile$2
            @Override // io.reactivex.Observer
            public void onComplete() {
                ManageSubtitlePresenter.this.getView().hideLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ManageSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onNext(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                if (name.length() == 0) {
                    ManageSubtitlePresenter.this.getView().deleteFileComplete();
                } else {
                    ToastUtils.showShort(Intrinsics.stringPlus("Delete failed name = ", name), new Object[0]);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: deleteFile$lambda-1  reason: not valid java name */
    public static final String m373deleteFile$lambda1(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            DirectoryModel directoryModel = (DirectoryModel) it2.next();
            if (directoryModel.getChecked() && !FileUtils.delete(new File(directoryModel.getPath()))) {
                directoryModel.getName();
            }
        }
        return "";
    }

    @Override // com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract.Presenter
    public void loadAllSubtitleFiles() {
        File file = new File(Constant.DIR_UPLOAD_MOVIE_SUBTITLE);
        File file2 = new File(Constant.DIR_UPLOAD_TV_SUBTITLE);
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!file2.exists()) {
            file2.mkdirs();
        }
        ArrayList arrayList = new ArrayList();
        List<File> listSubtitleFiles = listSubtitleFiles(new File(Constant.DIR_UPLOAD_MOVIE_SUBTITLE));
        if (listSubtitleFiles != null) {
            for (File file3 : listSubtitleFiles) {
                List<File> listSubtitleFiles2 = listSubtitleFiles(file3);
                if (listSubtitleFiles2 != null) {
                    arrayList.addAll(listSubtitleFiles2);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        List<File> listSubtitleFiles3 = listSubtitleFiles(new File(Constant.DIR_UPLOAD_TV_SUBTITLE));
        if (listSubtitleFiles3 != null) {
            for (File file4 : listSubtitleFiles3) {
                List<File> listSubtitleFiles4 = listSubtitleFiles(file4);
                if (listSubtitleFiles4 != null) {
                    arrayList2.addAll(listSubtitleFiles4);
                }
            }
        }
        ((ObservableSubscribeProxy) Observable.zip(Observable.fromIterable(arrayList).map($$Lambda$ManageSubtitlePresenter$o24QZajD0MKldFcn9ihpnnRe1t4.INSTANCE).toList().toObservable(), Observable.fromIterable(arrayList2).map($$Lambda$ManageSubtitlePresenter$Y1wgvsTxZHAstnNPtqwPNbRPM4c.INSTANCE).toList().toObservable(), $$Lambda$ManageSubtitlePresenter$YxqFQlyz7puR6rYRjtYM0VIbSc.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<List<? extends DirectoryModel>>() { // from class: com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitlePresenter$loadAllSubtitleFiles$2
            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(List<? extends DirectoryModel> list) {
                onNext2((List<DirectoryModel>) list);
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                ManageSubtitlePresenter.this.getView().hideLoadingView();
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ManageSubtitlePresenter.this.getView().showLoadingView();
            }

            /* renamed from: onNext  reason: avoid collision after fix types in other method */
            public void onNext2(List<DirectoryModel> list) {
                Intrinsics.checkNotNullParameter(list, "list");
                ManageSubtitlePresenter.this.getView().showFiles(list);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ManageSubtitlePresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("load file error", e.getMessage()), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadAllSubtitleFiles$lambda-8  reason: not valid java name */
    public static final DirectoryModel m376loadAllSubtitleFiles$lambda8(File it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String name = it.getName();
        Intrinsics.checkNotNullExpressionValue(name, "it.name");
        String path = it.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "it.path");
        return new DirectoryModel(name, false, path, it.isDirectory(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadAllSubtitleFiles$lambda-9  reason: not valid java name */
    public static final DirectoryModel m377loadAllSubtitleFiles$lambda9(File it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String name = it.getName();
        Intrinsics.checkNotNullExpressionValue(name, "it.name");
        String path = it.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "it.path");
        return new DirectoryModel(name, false, path, it.isDirectory(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadAllSubtitleFiles$lambda-10  reason: not valid java name */
    public static final List m375loadAllSubtitleFiles$lambda10(List t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(t1);
        arrayList.addAll(t2);
        return arrayList;
    }

    private final List<File> listSubtitleFiles(File file) {
        return FileUtils.listFilesInDirWithFilter(file, new FileFilter() { // from class: com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitlePresenter$listSubtitleFiles$1
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                String name;
                if (!(file2 != null && file2.isFile()) || !Intrinsics.areEqual(FileUtils.getExtensionName(file2.getName()), "srt")) {
                    String str = "";
                    if (file2 != null && (name = file2.getName()) != null) {
                        str = name;
                    }
                    if (!Intrinsics.areEqual(FileUtils.getExtensionName(str), "ass")) {
                        return file2 != null && file2.isDirectory();
                    }
                }
                return true;
            }
        }, false);
    }

    private final Observable<ArrayList<DirectoryModel>> loadSubtitleFilesObservable(String str) {
        return Observable.just(new File(str)).map(new Function() { // from class: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitlePresenter$lHtflsEYfCIrqtAvwiRReDtIgAk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ArrayList m378loadSubtitleFilesObservable$lambda12;
                m378loadSubtitleFilesObservable$lambda12 = ManageSubtitlePresenter.m378loadSubtitleFilesObservable$lambda12(ManageSubtitlePresenter.this, (File) obj);
                return m378loadSubtitleFilesObservable$lambda12;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadSubtitleFilesObservable$lambda-12  reason: not valid java name */
    public static final ArrayList m378loadSubtitleFilesObservable$lambda12(ManageSubtitlePresenter this$0, File it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        List<File> listSubtitleFiles = this$0.listSubtitleFiles(it);
        if (listSubtitleFiles != null) {
            for (File file : listSubtitleFiles) {
                String name = file.getName();
                Intrinsics.checkNotNullExpressionValue(name, "eachFile.name");
                String path = file.getPath();
                Intrinsics.checkNotNullExpressionValue(path, "eachFile.path");
                arrayList.add(new DirectoryModel(name, false, path, file.isDirectory(), false));
            }
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract.Presenter
    public void loadSubtitleFiles(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        ((ObservableSubscribeProxy) loadSubtitleFilesObservable(path).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new Observer<List<? extends DirectoryModel>>() { // from class: com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitlePresenter$loadSubtitleFiles$1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public /* bridge */ /* synthetic */ void onNext(List<? extends DirectoryModel> list) {
                onNext2((List<DirectoryModel>) list);
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                ManageSubtitlePresenter.this.getView().showLoadingView();
            }

            /* renamed from: onNext  reason: avoid collision after fix types in other method */
            public void onNext2(List<DirectoryModel> list) {
                Intrinsics.checkNotNullParameter(list, "list");
                ManageSubtitlePresenter.this.getView().hideLoadingView();
                ManageSubtitlePresenter.this.getView().showFiles(list);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ManageSubtitlePresenter.this.getView().hideLoadingView();
                ToastUtils.showShort(Intrinsics.stringPlus("load file error", e.getMessage()), new Object[0]);
            }
        });
    }
}
