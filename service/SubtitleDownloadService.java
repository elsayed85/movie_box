package com.movieboxpro.android.service;

import android.app.IntentService;
import android.content.Intent;
import com.google.android.gms.cast.MediaTrack;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.SubtitleDownloadEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
/* compiled from: SubtitleDownloadService.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002JJ\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\\\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/service/SubtitleDownloadService;", "Landroid/app/IntentService;", "()V", "downloadSubtitleObservable", "Lio/reactivex/Observable;", "", "sid", "url", "dir", "id", "name", "season", "", "episode", "downloadSubtitles", "", "urls", "sidList", "downloadDir", "onDestroy", "", "onHandleIntent", "intent", "Landroid/content/Intent;", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SubtitleDownloadService extends IntentService {
    public static final Companion Companion = new Companion(null);
    public static final String EPISODE = "episode";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SEASON = "season";

    public SubtitleDownloadService() {
        super("SubtitleDownloadService");
    }

    /* compiled from: SubtitleDownloadService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/service/SubtitleDownloadService$Companion;", "", "()V", UploadErrorInfoService.EPISODE, "", UploadErrorInfoService.ID, "NAME", UploadErrorInfoService.SEASON, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        final String stringExtra = intent.getStringExtra("id");
        final int intExtra = intent.getIntExtra("season", 0);
        final int intExtra2 = intent.getIntExtra("episode", 0);
        final String stringExtra2 = intent.getStringExtra("name");
        if (intExtra == 0) {
            final String str = Constant.DIR_UPLOAD_MOVIE_SUBTITLE + ((Object) File.separator) + ((Object) stringExtra) + ((Object) File.separator) + ((Object) stringExtra2) + ((Object) File.separator) + ((Object) App.deviceLang);
            Http.getService().Movie_srt_auto(API.BASE_URL, API.Movie.MOVIE_SRT_AUTO_V2, stringExtra, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "").compose(RxUtils.rxTranslate2List(Srt.class)).flatMap(new Function() { // from class: com.movieboxpro.android.service.-$$Lambda$SubtitleDownloadService$7NJBI13dLSNJkE-wKsxf4tnrKYM
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m70onHandleIntent$lambda4$lambda1;
                    m70onHandleIntent$lambda4$lambda1 = SubtitleDownloadService.m70onHandleIntent$lambda4$lambda1(SubtitleDownloadService.this, str, stringExtra, stringExtra2, intExtra, intExtra2, (List) obj);
                    return m70onHandleIntent$lambda4$lambda1;
                }
            }).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<List<? extends String>>() { // from class: com.movieboxpro.android.service.SubtitleDownloadService$onHandleIntent$1$2
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                }

                /* renamed from: onSuccess  reason: avoid collision after fix types in other method */
                public void onSuccess2(List<String> model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public /* bridge */ /* synthetic */ void onSuccess(List<? extends String> list) {
                    onSuccess2((List<String>) list);
                }
            });
            return;
        }
        final String str2 = Constant.DIR_UPLOAD_TV_SUBTITLE + ((Object) File.separator) + ((Object) stringExtra) + ((Object) File.separator) + ((Object) stringExtra2) + ((Object) File.separator) + "Season " + intExtra + ((Object) File.separator) + "Episode " + intExtra2 + ((Object) File.separator) + ((Object) App.deviceLang);
        Http.getService().Tv_srt_auto(API.BASE_URL, API.Tv.TV_SRT_AUTO_V2, stringExtra, intExtra, intExtra2, App.deviceLang, App.isLogin() ? App.getUserData().uid_v2 : "").compose(RxUtils.rxTranslate2List(Srt.class)).flatMap(new Function() { // from class: com.movieboxpro.android.service.-$$Lambda$SubtitleDownloadService$6N6WuJMi4b7cdgB4FbYuxLyKAKk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m71onHandleIntent$lambda4$lambda3;
                m71onHandleIntent$lambda4$lambda3 = SubtitleDownloadService.m71onHandleIntent$lambda4$lambda3(SubtitleDownloadService.this, str2, stringExtra, stringExtra2, intExtra, intExtra2, (List) obj);
                return m71onHandleIntent$lambda4$lambda3;
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<List<? extends String>>() { // from class: com.movieboxpro.android.service.SubtitleDownloadService$onHandleIntent$1$4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(List<? extends String> list) {
                onSuccess2((List<String>) list);
            }

            /* renamed from: onSuccess  reason: avoid collision after fix types in other method */
            public void onSuccess2(List<String> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                EventBus.getDefault().post(new SubtitleDownloadEvent());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onHandleIntent$lambda-4$lambda-1  reason: not valid java name */
    public static final ObservableSource m70onHandleIntent$lambda4$lambda1(SubtitleDownloadService this$0, String downloadDir, String str, String str2, int i, int i2, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadDir, "$downloadDir");
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Srt srt = (Srt) it.next();
            arrayList.add(srt.file_path);
            arrayList2.add(srt.sid);
        }
        return this$0.downloadSubtitles(arrayList, arrayList2, downloadDir, str, str2, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onHandleIntent$lambda-4$lambda-3  reason: not valid java name */
    public static final ObservableSource m71onHandleIntent$lambda4$lambda3(SubtitleDownloadService this$0, String downloadDir, String str, String str2, int i, int i2, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadDir, "$downloadDir");
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Srt srt = (Srt) it.next();
            arrayList.add(srt.file_path);
            arrayList2.add(srt.sid);
        }
        return this$0.downloadSubtitles(arrayList, arrayList2, downloadDir, str, str2, i, i2);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        CommonExtKt.logD(this, "onDestroy");
        super.onDestroy();
    }

    private final Observable<List<String>> downloadSubtitles(List<String> list, List<String> list2, final String str, final String str2, final String str3, final int i, final int i2) {
        Observable<List<String>> observable = Observable.zip(Observable.fromIterable(list), Observable.fromIterable(list2), $$Lambda$SubtitleDownloadService$d78vfz8cMvFdt55QKCWQR92t9mc.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.service.-$$Lambda$SubtitleDownloadService$JtiDMRAS611xx_5lca1rVDwbGYg
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m67downloadSubtitles$lambda6;
                m67downloadSubtitles$lambda6 = SubtitleDownloadService.m67downloadSubtitles$lambda6(SubtitleDownloadService.this, str, str2, str3, i, i2, (Pair) obj);
                return m67downloadSubtitles$lambda6;
            }
        }).toList().toObservable();
        Intrinsics.checkNotNullExpressionValue(observable, "zip(Observable.fromItera…          .toObservable()");
        return observable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadSubtitles$lambda-5  reason: not valid java name */
    public static final Pair m66downloadSubtitles$lambda5(String url, String sid) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new Pair(url, sid);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadSubtitles$lambda-6  reason: not valid java name */
    public static final ObservableSource m67downloadSubtitles$lambda6(SubtitleDownloadService this$0, String downloadDir, String str, String str2, int i, int i2, Pair it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(downloadDir, "$downloadDir");
        Intrinsics.checkNotNullParameter(it, "it");
        return this$0.downloadSubtitleObservable((String) it.getSecond(), (String) it.getFirst(), downloadDir, str, str2, i, i2);
    }

    private final Observable<String> downloadSubtitleObservable(final String str, final String str2, final String str3, String str4, final String str5, final int i, final int i2) {
        Observable map = Http.getService().strlist(str2).map(new Function() { // from class: com.movieboxpro.android.service.-$$Lambda$SubtitleDownloadService$vMRjQupNTWEW-s4GIWmsNe_PcGY
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                String m65downloadSubtitleObservable$lambda7;
                m65downloadSubtitleObservable$lambda7 = SubtitleDownloadService.m65downloadSubtitleObservable$lambda7(str3, str, str2, i, str5, i2, (ResponseBody) obj);
                return m65downloadSubtitleObservable$lambda7;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "getService().strlist(url…    sid\n                }");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: downloadSubtitleObservable$lambda-7  reason: not valid java name */
    public static final String m65downloadSubtitleObservable$lambda7(String dir, String sid, String url, int i, String str, int i2, ResponseBody it) {
        String str2;
        File file;
        String str3;
        Intrinsics.checkNotNullParameter(dir, "$dir");
        Intrinsics.checkNotNullParameter(sid, "$sid");
        Intrinsics.checkNotNullParameter(url, "$url");
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        sb.append(dir);
        sb.append('/');
        sb.append(sid);
        sb.append('_');
        String str4 = url;
        String substring = url.substring(StringsKt.lastIndexOf$default((CharSequence) str4, "/", 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        sb.append(substring);
        String sb2 = sb.toString();
        CommonExtKt.logD(Companion, Intrinsics.stringPlus("Download subtitle path:", sb2));
        File file2 = ReaderUtils.getChapterFile(sb2);
        FileUtils.writeFileFromBytesByStream(file2, it.bytes());
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.INTERNAL_STORAGE, true)) {
            if (i == 0) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(Constant.DIR_DOWNLOAD);
                sb3.append((Object) File.separator);
                sb3.append(MediaTrack.ROLE_SUBTITLE);
                sb3.append((Object) File.separator);
                sb3.append("movie");
                sb3.append((Object) File.separator);
                sb3.append((Object) (str != null ? StringsKt.replace$default(str, ":", "_", false, 4, (Object) null) : null));
                sb3.append((Object) File.separator);
                sb3.append('/');
                sb3.append(sid);
                sb3.append('_');
                String substring2 = url.substring(StringsKt.lastIndexOf$default((CharSequence) str4, "/", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
                sb3.append(substring2);
                File chapterFile = ReaderUtils.getChapterFile(sb3.toString());
                Intrinsics.checkNotNullExpressionValue(file2, "file");
                FileUtils.writeFileFromBytesByStream(chapterFile, FilesKt.readBytes(file2));
            } else {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(Constant.DIR_DOWNLOAD);
                sb4.append((Object) File.separator);
                sb4.append(MediaTrack.ROLE_SUBTITLE);
                sb4.append((Object) File.separator);
                sb4.append("tv");
                sb4.append((Object) File.separator);
                if (str == null) {
                    str2 = "this as java.lang.String).substring(startIndex)";
                    file = file2;
                    str3 = IDataSource.SCHEME_FILE_TAG;
                } else {
                    str2 = "this as java.lang.String).substring(startIndex)";
                    file = file2;
                    str3 = IDataSource.SCHEME_FILE_TAG;
                    String replace$default = StringsKt.replace$default(str, " ", "_", false, 4, (Object) null);
                    if (replace$default != null) {
                        r11 = StringsKt.replace$default(replace$default, ":", "_", false, 4, (Object) null);
                    }
                }
                sb4.append((Object) r11);
                sb4.append((Object) File.separator);
                sb4.append(i);
                sb4.append((Object) File.separator);
                sb4.append(i2);
                sb4.append('/');
                sb4.append(sid);
                sb4.append('_');
                String substring3 = url.substring(StringsKt.lastIndexOf$default((CharSequence) str4, "/", 0, false, 6, (Object) null) + 1);
                Intrinsics.checkNotNullExpressionValue(substring3, str2);
                sb4.append(substring3);
                File chapterFile2 = ReaderUtils.getChapterFile(sb4.toString());
                File file3 = file;
                Intrinsics.checkNotNullExpressionValue(file3, str3);
                FileUtils.writeFileFromBytesByStream(chapterFile2, FilesKt.readBytes(file3));
            }
        }
        return sid;
    }
}
