package com.movieboxpro.android.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.ares.downloader.jarvis.Jarvis;
import com.ares.downloader.jarvis.core.DownloadListener;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.huantansheng.easyphotos.EasyPhotos;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.event.ChangeToPauseEvent;
import com.movieboxpro.android.event.DownloadChangedEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RomUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.StringUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class DownloadService extends Service {
    public static final String DOWNLOAD_NOTIFICATION = "download_notification";
    public static final int DOWNLOAD_NOTIFICATION_ID = 1000;
    private static final String TAG = "MovieDownLoaderService";
    private static volatile ConcurrentHashMap<String, Long> downloadedMap;
    private static volatile ConcurrentHashMap<String, Long> speedMap;
    private static volatile ConcurrentHashMap<String, Long> totalFileLengthMap;
    private NotificationCompat.Builder builder;
    private NotificationManager manager;
    private static volatile LinkedHashMap<String, DownloadModel> list = new LinkedHashMap<>();
    private static int downloadingNum = 0;

    private void startDownloadNotifyService() {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    static /* synthetic */ int access$908() {
        int i = downloadingNum;
        downloadingNum = i + 1;
        return i;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (speedMap == null) {
            speedMap = new ConcurrentHashMap<>();
        }
        if (downloadedMap == null) {
            downloadedMap = new ConcurrentHashMap<>();
        }
        if (totalFileLengthMap == null) {
            totalFileLengthMap = new ConcurrentHashMap<>();
        }
        initNotification();
    }

    private void initNotification() {
        this.manager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            this.manager.createNotificationChannel(new NotificationChannel(DOWNLOAD_NOTIFICATION, "Downloading", 3));
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DOWNLOAD_NOTIFICATION);
        this.builder = builder;
        builder.setDefaults(4).setOngoing(true).setOnlyAlertOnce(true).setPriority(-2).setContentTitle("Start Download").setContentText("");
        if (RomUtils.isGoogle() || RomUtils.isOneplus()) {
            this.builder.setSmallIcon(R.mipmap.ic_white_logo);
            this.builder.setColor(Color.parseColor("#2D2D30"));
            this.builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo));
            return;
        }
        this.builder.setSmallIcon(R.mipmap.ic_logo);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        List<Download> all;
        if (intent != null) {
            String action = intent.getAction();
            if (Constant.ACTION.MOVIE_DOWNLOAD.equals(action)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    String string = extras.getString(Constant.Download.PARAMS_KEY_MOVIE, "");
                    int i3 = extras.getInt(Constant.Download.PARAMS_KEY_TYPE, 1);
                    MLog.d(TAG, "开始下载" + string + ":" + i3);
                    Download findByType = App.getDB().downloadDao().findByType(i3, string);
                    if (findByType != null) {
                        if (canDownload(findByType.getPrivateid(), findByType.getBox_type())) {
                            doDownload(findByType, 1);
                        } else {
                            onDownloadReady(findByType);
                        }
                    }
                }
            } else if (Constant.ACTION.MOVIE_ALLDOWNLOAD.equals(action)) {
                List<Download> all2 = App.getDB().downloadDao().getAll();
                if (all2 != null && all2.size() > 0) {
                    for (Download download : all2) {
                        if (download.getStatue() != 2) {
                            if (canDownload(download.getPrivateid(), download.getBox_type())) {
                                doDownload(download, download.getStatue());
                            } else {
                                onDownloadReady(download);
                            }
                        }
                    }
                }
            } else if (Constant.ACTION.MOVIE_RESET_MAX_COUNT.equals(action)) {
                for (Download download2 : App.getDB().downloadDao().findByStatue(0)) {
                    if (canDownload(download2.getPrivateid(), download2.getBox_type())) {
                        doDownload(download2, 1);
                    }
                }
            } else if (Constant.ACTION.MOVIE_PAUSED.equals(action)) {
                Bundle extras2 = intent.getExtras();
                String string2 = extras2.getString(Constant.Download.PARAMS_KEY_MOVIE, "");
                Download findByType2 = App.getDB().downloadDao().findByType(extras2.getInt(Constant.Download.PARAMS_KEY_TYPE, 1), string2);
                MLog.d(TAG, "下载进度 ： " + list.containsKey(string2) + "," + list.size());
                if (findByType2 != null) {
                    doDownload(findByType2, 3);
                }
            } else if (Constant.ACTION.MOVIE_ERROR.equals(action)) {
                Bundle extras3 = intent.getExtras();
                String string3 = extras3.getString(Constant.Download.PARAMS_KEY_MOVIE, "");
                int i4 = extras3.getInt(Constant.Download.PARAMS_KEY_TYPE, 1);
                MLog.d(TAG, "下载错误 ： " + list.containsKey(string3) + "," + list.size());
                if (list.containsKey(string3)) {
                    list.get(string3).downloader.pause();
                    Download findByType3 = App.getDB().downloadDao().findByType(i4, string3);
                    if (findByType3 != null) {
                        doDownload(findByType3, 1);
                    }
                }
            } else if (Constant.ACTION.MOVIE_DELETE.equals(action)) {
                getAllDownloadModel();
                Bundle extras4 = intent.getExtras();
                String string4 = extras4.getString(Constant.Download.PARAMS_KEY_MOVIE, "");
                int i5 = extras4.getInt(Constant.Download.PARAMS_KEY_TYPE, 1);
                MLog.d(TAG, "下载删除 ： " + list.containsKey(string4) + "," + list.size());
                if (i5 == 1) {
                    FileUtils.deleteDir(new File(Constant.DIR_UPLOAD_MOVIE_SUBTITLE + File.separator + string4));
                    deleteDownloadTask(string4);
                } else if (i5 == 2) {
                    if (App.getDB().downloadDao().findByType(i5, string4) != null) {
                        if (list.get(string4) != null) {
                            deleteDownloadTask(string4);
                        }
                    } else {
                        for (Download download3 : App.getDB().downloadDao().selectByTvId(string4, i5)) {
                            FileUtils.deleteDir(new File(Constant.DIR_UPLOAD_TV_SUBTITLE + File.separator + download3.getMovieId() + File.separator + download3.getTitle() + File.separator + "Season " + download3.getSeason() + File.separator + "Episode " + download3.getEpisode()));
                            deleteDownloadTask(download3.getPrivateid());
                        }
                    }
                }
            } else if (Constant.ACTION.DELETE_DOWNLOADED_TV.equals(action)) {
                getAllDownloadModel();
                for (Download download4 : App.getDB().downloadDao().selectByTvId(intent.getExtras().getString(Constant.Download.PARAMS_KEY_MOVIE, ""), 2, 2)) {
                    FileUtils.deleteDir(new File(Constant.DIR_UPLOAD_TV_SUBTITLE + File.separator + download4.getMovieId() + File.separator + download4.getTitle() + File.separator + "Season " + download4.getSeason() + File.separator + "Episode " + download4.getEpisode()));
                    deleteDownloadTask(download4.getPrivateid());
                }
            } else if (Constant.ACTION.MOVIE_TV_DELETE.equals(action)) {
                Bundle extras5 = intent.getExtras();
                for (Download download5 : App.getDB().downloadDao().selectByTvId(extras5.getString(Constant.Download.PARAMS_KEY_MOVIE, ""), extras5.getInt(Constant.Download.PARAMS_KEY_TYPE, 1))) {
                    deleteDownloadTask(download5.getPrivateid());
                }
            } else if (Constant.ACTION.MOVIE_STARTALL.equals(action)) {
                List<Download> all3 = App.getDB().downloadDao().getAll();
                if (all3 != null && all3.size() > 0) {
                    for (Download download6 : all3) {
                        if (download6.getStatue() != 2) {
                            if (canDownload(download6.getPrivateid(), download6.getBox_type())) {
                                doDownload(download6, 1);
                            } else {
                                onDownloadReady(download6);
                            }
                        }
                    }
                }
            } else if (Constant.ACTION.MOVIE_PAUSEDALL.equals(action) && (all = App.getDB().downloadDao().getAll()) != null && all.size() > 0) {
                for (Download download7 : all) {
                    if (download7.getStatue() != 2) {
                        if (download7.getStatue() == 0) {
                            EventBus.getDefault().post(new ChangeToPauseEvent(download7.getBox_type(), download7.getPrivateid()));
                        } else {
                            doDownload(download7, 3);
                        }
                    }
                }
            }
        }
        return 1;
    }

    private void deleteDownloadTask(String str) {
        String str2;
        DownloadModel downloadModel = list.get(str);
        if (downloadModel != null) {
            if (downloadModel.box_type == 1) {
                str2 = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + File.separator + str + File.separator + downloadModel.title + File.separator + App.deviceLang;
            } else {
                str2 = Constant.DIR_UPLOAD_TV_SUBTITLE + File.separator + downloadModel.movieId + File.separator + downloadModel.title + File.separator + "Season " + downloadModel.season + File.separator + "Episode " + downloadModel.episode + downloadModel.episode + File.separator + App.deviceLang;
            }
            new File(str2).delete();
            downloadModel.downloader.pauseAndDelete();
            list.remove(str);
            App.getDB().downloadDao().deleteByTid(str);
        }
    }

    private boolean canDownload(String str, int i) {
        Download findByType = App.getDB().downloadDao().findByType(i, str);
        if (findByType == null || findByType.getStatue() != 1) {
            return App.getDB().downloadDao().findByStatue(1).size() < PrefsUtils.getInstance().getInt(Constant.MAX_DOWNLOAD_COUNT, 1);
        }
        return true;
    }

    private void getAllDownloadModel() {
        List<Download> all = App.getDB().downloadDao().getAll();
        if (all == null || all.size() <= 0) {
            return;
        }
        for (Download download : all) {
            doDownload(download, download.getStatue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeOneTask(Download download) {
        downloadingNum--;
        downloadedMap.remove(download.getPrivateid());
        speedMap.remove(download.getPrivateid());
        totalFileLengthMap.remove(download.getPrivateid());
        if (downloadingNum < 0) {
            downloadingNum = 0;
        }
        if (downloadingNum == 0) {
            this.manager.cancel(1000);
            stopForeground(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDownload(final Download download, int i) {
        File externalFilesDir;
        MLog.d(TAG, "onDownloaddownloading" + list.containsKey(download.getPrivateid()));
        if (!list.containsKey(download.getPrivateid())) {
            if (TextUtils.isEmpty(download.getPath())) {
                App.getDB().downloadDao().deleteByTid(download.getPrivateid());
                return;
            }
            if (Constant.DIR_DOWNLOAD == null && (externalFilesDir = getExternalFilesDir("")) != null) {
                Constant.DIR = externalFilesDir.getParent();
                Constant.DIR_DOWNLOAD = Constant.DIR + File.separator + DownloadInfo.DOWNLOAD;
            }
            Jarvis.Downloader downloadListener = Jarvis.with(App.getContext()).withUrl(download.getPath()).fileName(RemoteFileUtil.getFileName(download.getPath(), download.getTitle(), Constant.DIR_DOWNLOAD)).filePath(Constant.DIR_DOWNLOAD).allowBackgroundDownload(true).threadCount(1).refreshTime(1000L).setDownloadListener(new DownloadListener() { // from class: com.movieboxpro.android.service.DownloadService.1
                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onSuccess(File file) {
                    MLog.d(DownloadService.TAG, "下载 ：success");
                    DownloadService.this.onDownloadSuccess(download);
                    DownloadService.this.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_SUCCESS, download, "", 0);
                    DownloadService.this.downloadNextVideo();
                    EasyPhotos.notifyMedia(App.getContext(), file);
                    DownloadService.this.removeOneTask(download);
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onProgress(long j, float f, long j2) {
                    MLog.d(DownloadService.TAG, "下载 ：process");
                    int i2 = (int) (f * 100.0f);
                    DownloadService.this.onDownloadProgress(download, i2, j);
                    DownloadService downloadService = DownloadService.this;
                    Download download2 = download;
                    downloadService.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_PROGRESS, download2, StringUtils.getPrintSize(j - download2.getFileLength()), i2);
                    DownloadService.downloadedMap.put(download.getPrivateid(), Long.valueOf(j));
                    DownloadService.totalFileLengthMap.put(download.getPrivateid(), Long.valueOf(j2));
                    DownloadService.speedMap.put(download.getPrivateid(), Long.valueOf(j - download.getFileLength()));
                    download.setFileLength(j);
                    long j3 = 0;
                    long j4 = 0;
                    for (String str : DownloadService.speedMap.keySet()) {
                        Long l = (Long) DownloadService.speedMap.get(str);
                        if (l != null) {
                            j4 += l.longValue();
                        }
                    }
                    long j5 = 0;
                    for (String str2 : DownloadService.downloadedMap.keySet()) {
                        Long l2 = (Long) DownloadService.downloadedMap.get(str2);
                        if (l2 != null) {
                            j5 += l2.longValue();
                        }
                    }
                    for (String str3 : DownloadService.totalFileLengthMap.keySet()) {
                        Long l3 = (Long) DownloadService.totalFileLengthMap.get(str3);
                        if (l3 != null) {
                            j3 += l3.longValue();
                        }
                    }
                    DownloadService.this.builder.setContentText(String.format("Download speed:%s/s", FileUtils.byte2FitMemorySize(j4)));
                    if (DownloadService.downloadingNum == 1) {
                        DownloadService.this.builder.setContentTitle(String.format("1 video left(%s)", DownloadService.this.calcPercent(j5, j3)));
                    } else {
                        DownloadService.this.builder.setContentTitle(String.format("%s videos left(%s)", Integer.valueOf(DownloadService.downloadingNum), DownloadService.this.calcPercent(j5, j3)));
                    }
                    DownloadService.this.manager.notify(1000, DownloadService.this.builder.build());
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onStart() {
                    MLog.d(DownloadService.TAG, "下载 ：start");
                    DownloadService.this.onDownloadStarted(download);
                    DownloadService.this.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_START, download, "", 0);
                    DownloadService.access$908();
                    DownloadService downloadService = DownloadService.this;
                    downloadService.startForeground(1000, downloadService.builder.build());
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onPause() {
                    MLog.d(DownloadService.TAG, "下载 ：pause");
                    DownloadService.this.onDownloadPaused(download);
                    DownloadService.this.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_PAUSE, download, "", 0);
                    DownloadService.this.downloadNextVideo();
                    DownloadService.this.removeOneTask(download);
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onFail(String str, int i2) {
                    MLog.d(DownloadService.TAG, "下载 ：fail");
                    if (i2 == 410) {
                        DownloadService.this.getDownloadUrl(download);
                        return;
                    }
                    DownloadService.this.onDownloadFailure(download, str);
                    DownloadService.this.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_FAIL, download, "", 0);
                    DownloadService.this.removeOneTask(download);
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onDelete(boolean z) {
                    MLog.d(DownloadService.TAG, "下载 ：delete" + z);
                    DownloadService.this.onDownloadDelete(download);
                    DownloadService.this.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_DELETE, download, "", 0);
                    DownloadService.this.removeOneTask(download);
                }
            });
            DownloadModel downloadModel = new DownloadModel(download);
            downloadModel.downloader = downloadListener;
            list.put(download.getPrivateid(), downloadModel);
            excute(downloadModel, i);
            return;
        }
        excute(list.get(download.getPrivateid()), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String calcPercent(long j, long j2) {
        if (j2 == 0) {
            return "";
        }
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
        StringBuilder sb = new StringBuilder();
        double d = j;
        Double.isNaN(d);
        double d2 = j2;
        Double.isNaN(d2);
        sb.append(decimalFormat.format((d * 100.0d) / d2));
        sb.append("%");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDownloadUrl(final Download download) {
        Observable compose;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        boolean isEmpty = TextUtils.isEmpty(string);
        String str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        if (!isEmpty && "0".equalsIgnoreCase(string)) {
            string = "";
            str = string;
        }
        if (download.getBox_type() == 2) {
            APIService service = Http.getService();
            String str2 = API.BASE_URL;
            String str3 = App.isLogin() ? App.getUserData().uid_v2 : "";
            compose = service.TV_downloadurl(str2, API.Tv.TV_DOWNLAODURL, str3, download.getMovieId(), download.getSeason() + "", download.getEpisode() + "", str, string).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
        } else {
            compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", download.getMovieId(), "", str, string).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
        }
        compose.compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.service.DownloadService.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BaseMediaModel baseMediaModel) {
                if (baseMediaModel.list != null) {
                    for (BaseMediaModel.DownloadFile downloadFile : baseMediaModel.list) {
                        if (downloadFile.quality != null && downloadFile.quality.equals(download.getQuality())) {
                            download.setPath(downloadFile.path);
                            App.getDB().downloadDao().update(download);
                            DownloadModel downloadModel = (DownloadModel) DownloadService.list.get(download.getPrivateid());
                            if (downloadModel != null && downloadModel.downloader != null) {
                                downloadModel.downloader.changeUrl(downloadFile.path);
                            }
                            DownloadService.this.doDownload(download, 1);
                            return;
                        }
                    }
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                DownloadService.this.onDownloadFailure(download, apiException.getMessage());
                DownloadService.this.sendDownloadBroadcast(Constant.ACTION.DOWNLOAD_NOTIFY_FAIL, download, "", 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendDownloadBroadcast(String str, Download download, String str2, int i) {
        Intent intent = new Intent(str);
        intent.putExtra("speedText", str2);
        intent.putExtra("id", download.getId());
        intent.putExtra("title", download.getTitle());
        intent.putExtra("season", download.getSeason());
        intent.putExtra("episode", download.getEpisode());
        intent.putExtra("boxType", download.getBox_type());
        intent.putExtra("progress", i);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadNextVideo() {
        for (Download download : App.getDB().downloadDao().findByStatue(0)) {
            if (canDownload(download.getPrivateid(), download.getBox_type())) {
                doDownload(download, 1);
            }
        }
    }

    private void excute(DownloadModel downloadModel, int i) {
        if (i == 1) {
            downloadModel.downloader.download();
            startDownloadNotifyService();
        } else if (i == 3) {
            downloadModel.downloader.pause();
            startDownloadNotifyService();
        } else if (i == 4) {
            downloadModel.downloader.recovery();
            startDownloadNotifyService();
        } else if (i != 5) {
        } else {
            downloadModel.downloader.deleteCacheFile();
            startDownloadNotifyService();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    private void onDownloadReady(Download download) {
        if (download.getStatue() == 2) {
            return;
        }
        MLog.d(TAG, "onDownloadReady");
        download.setStatue(0);
        download.setSpeed(0L);
        App.getDB().downloadDao().update(download);
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_READY);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        EventBus.getDefault().post(new DownloadChangedEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadStarted(Download download) {
        MLog.d(TAG, "onDownloadStarted");
        download.setStatue(1);
        download.setProgress(0);
        App.getDB().downloadDao().update(download);
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_STARTED);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        EventBus.getDefault().post(new DownloadChangedEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadPaused(Download download) {
        MLog.d(TAG, "onDownloadPaused");
        download.setStatue(3);
        download.setSpeed(0L);
        App.getDB().downloadDao().update(download);
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_PAUSED);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadProgress(Download download, int i, long j) {
        MLog.d(TAG, "onDownloadProgress: " + i);
        if (i == 100) {
            onDownloadSuccess(download);
            return;
        }
        download.setStatue(1);
        download.setProgress(i);
        App.getDB().downloadDao().update(download);
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_PROGRESS);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        intent.putExtra(Constant.Download.PARAMS_KEY_PROGRESS, i);
        intent.putExtra(Constant.Download.PARAMS_KEY_SIZE, j);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadFailure(Download download, String str) {
        MLog.d(TAG, "onDownloadFailure: " + str);
        download.setFailReason(str);
        download.setStatue(4);
        App.getDB().downloadDao().update(download);
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_FAILURE);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        intent.putExtra(Constant.Download.PARAMS_KEY_REASON, str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadSuccess(Download download) {
        MLog.d(TAG, "onDownloadSuccess: ");
        download.setStatue(2);
        download.setProgress(100);
        App.getDB().downloadDao().update(download);
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_COMPLETE);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        list.remove(download.getPrivateid());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDownloadDelete(Download download) {
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_MOVIE_DELETE);
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_ID, download.getPrivateid());
        intent.putExtra(Constant.Download.PARAMS_KEY_MOVIE_TYPE, download.getBox_type());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
