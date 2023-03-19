package com.movieboxpro.android.utils;

import android.util.Log;
import com.movieboxpro.android.model.DownloadInfo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class DownloadObserver implements Observer<DownloadInfo> {
    public Disposable d;
    public DownloadInfo downloadInfo;

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        this.d = disposable;
    }

    @Override // io.reactivex.Observer
    public void onNext(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
        downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD);
        EventBus.getDefault().post(this.downloadInfo);
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        Log.d("My_Log", "onError");
        if (DownloadManager.getInstance().getDownloadUrl(this.downloadInfo.getUrl())) {
            DownloadManager.getInstance().pauseDownload(this.downloadInfo.getUrl());
            this.downloadInfo.setDownloadStatus("error");
            EventBus.getDefault().post(this.downloadInfo);
            return;
        }
        this.downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_PAUSE);
        EventBus.getDefault().post(this.downloadInfo);
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        Log.d("My_Log", "onComplete");
        DownloadInfo downloadInfo = this.downloadInfo;
        if (downloadInfo != null) {
            downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_OVER);
            EventBus.getDefault().post(this.downloadInfo);
        }
    }
}
