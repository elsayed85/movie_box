package com.movieboxpro.android.utils;

import android.text.TextUtils;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.DownloadInfo;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.fourthline.cling.model.types.BytesRange;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class DownloadManager {
    private static final AtomicReference<DownloadManager> INSTANCE = new AtomicReference<>();
    private HashMap<String, Call> downCalls = new HashMap<>();
    private OkHttpClient mClient = new OkHttpClient.Builder().build();

    public static DownloadManager getInstance() {
        DownloadManager downloadManager;
        do {
            DownloadManager downloadManager2 = INSTANCE.get();
            if (downloadManager2 != null) {
                return downloadManager2;
            }
            downloadManager = new DownloadManager();
        } while (!INSTANCE.compareAndSet(null, downloadManager));
        return downloadManager;
    }

    private DownloadManager() {
    }

    public boolean getDownloadUrl(String str) {
        return this.downCalls.containsKey(str);
    }

    public void download(final String str, String str2, DownloadObserver downloadObserver) {
        Observable.just(str2).filter(new Predicate<String>() { // from class: com.movieboxpro.android.utils.DownloadManager.4
            @Override // io.reactivex.functions.Predicate
            public boolean test(String str3) {
                return !DownloadManager.this.downCalls.containsKey(str3);
            }
        }).flatMap(new Function<String, ObservableSource<?>>() { // from class: com.movieboxpro.android.utils.DownloadManager.3
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(String str3) {
                return Observable.just(DownloadManager.this.createDownInfo(str3));
            }
        }).map(new Function<Object, DownloadInfo>() { // from class: com.movieboxpro.android.utils.DownloadManager.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.functions.Function
            public DownloadInfo apply(Object obj) {
                if (TextUtils.isEmpty(str)) {
                    return DownloadManager.this.getRealFileName((DownloadInfo) obj);
                }
                DownloadInfo downloadInfo = (DownloadInfo) obj;
                downloadInfo.setFileName(str);
                return downloadInfo;
            }
        }).flatMap(new Function<DownloadInfo, ObservableSource<DownloadInfo>>() { // from class: com.movieboxpro.android.utils.DownloadManager.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<DownloadInfo> apply(DownloadInfo downloadInfo) {
                return Observable.create(new DownloadSubscribe(downloadInfo));
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(downloadObserver);
    }

    public void pauseDownload(String str) {
        Call call = this.downCalls.get(str);
        if (call != null) {
            call.cancel();
        }
        this.downCalls.remove(str);
    }

    public void cancelDownload(DownloadInfo downloadInfo) {
        pauseDownload(downloadInfo.getUrl());
        downloadInfo.setProgress(0L);
        downloadInfo.setDownloadStatus(DownloadInfo.DOWNLOAD_CANCEL);
        EventBus.getDefault().post(downloadInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadInfo createDownInfo(String str) {
        DownloadInfo downloadInfo = new DownloadInfo(str);
        downloadInfo.setTotal(getContentLength(str));
        downloadInfo.setFileName(str.substring(str.lastIndexOf("/")));
        return downloadInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadInfo getRealFileName(DownloadInfo downloadInfo) {
        int lastIndexOf;
        String str;
        String fileName = downloadInfo.getFileName();
        long total = downloadInfo.getTotal();
        File file = new File(Constant.DIR_DOWNLOAD);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(Constant.DIR_DOWNLOAD, fileName);
        long length = file2.exists() ? file2.length() : 0L;
        int i = 1;
        while (length >= total) {
            if (fileName.lastIndexOf(com.huantansheng.easyphotos.utils.file.FileUtils.HIDDEN_PREFIX) == -1) {
                str = fileName + "(" + i + ")";
            } else {
                str = fileName.substring(0, lastIndexOf) + "(" + i + ")" + fileName.substring(lastIndexOf);
            }
            File file3 = new File(Constant.DIR_DOWNLOAD, str);
            i++;
            file2 = file3;
            length = file3.length();
        }
        downloadInfo.setProgress(length);
        downloadInfo.setFileName(file2.getName());
        return downloadInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class DownloadSubscribe implements ObservableOnSubscribe<DownloadInfo> {
        private DownloadInfo downloadInfo;

        public DownloadSubscribe(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }

        @Override // io.reactivex.ObservableOnSubscribe
        public void subscribe(ObservableEmitter<DownloadInfo> observableEmitter) throws Exception {
            FileOutputStream fileOutputStream;
            String url = this.downloadInfo.getUrl();
            long progress = this.downloadInfo.getProgress();
            long total = this.downloadInfo.getTotal();
            observableEmitter.onNext(this.downloadInfo);
            Call newCall = DownloadManager.this.mClient.newCall(new Request.Builder().addHeader("RANGE", BytesRange.PREFIX + progress + "-" + total).url(url).build());
            DownloadManager.this.downCalls.put(url, newCall);
            Response execute = newCall.execute();
            File file = new File(Constant.DIR_DOWNLOAD, this.downloadInfo.getFileName());
            InputStream inputStream = null;
            try {
                if (execute.body() != null) {
                    InputStream byteStream = execute.body().byteStream();
                    try {
                        fileOutputStream = new FileOutputStream(file, true);
                        try {
                            byte[] bArr = new byte[2048];
                            while (true) {
                                int read = byteStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                                progress += read;
                                this.downloadInfo.setProgress(progress);
                                observableEmitter.onNext(this.downloadInfo);
                            }
                            fileOutputStream.flush();
                            DownloadManager.this.downCalls.remove(url);
                            inputStream = byteStream;
                        } catch (Throwable th) {
                            th = th;
                            inputStream = byteStream;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = null;
                    }
                } else {
                    fileOutputStream = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                observableEmitter.onComplete();
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        }
    }

    private long getContentLength(String str) {
        try {
            Response execute = this.mClient.newCall(new Request.Builder().url(str).build()).execute();
            if (execute != null && execute.isSuccessful()) {
                long contentLength = execute.body().contentLength();
                execute.close();
                if (contentLength == 0) {
                    return -1L;
                }
                return contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1L;
    }
}
