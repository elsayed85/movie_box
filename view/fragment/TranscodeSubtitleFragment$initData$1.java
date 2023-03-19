package com.movieboxpro.android.view.fragment;

import com.avery.subtitle.model.Subtitle;
import com.dueeeke.model.SrtPraseModel;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.TranscodeResponse;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: TranscodeSubtitleFragment.kt */
@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J8\u0010\u0004\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0011"}, d2 = {"com/movieboxpro/android/view/fragment/TranscodeSubtitleFragment$initData$1", "Lcom/movieboxpro/android/view/videocontroller/TransCodingSubtitleController$TransCodingSubtitleCallback;", "onClose", "", "onDone", "srtPraseModels", "", "Lcom/dueeeke/model/SrtPraseModel;", "localSubtitlePosition", "", "isLocalFile", "", "subtitles", "Lcom/avery/subtitle/model/Subtitle;", "onEncodeSelected", "code", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranscodeSubtitleFragment$initData$1 implements TransCodingSubtitleController.TransCodingSubtitleCallback {
    final /* synthetic */ TranscodeSubtitleFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TranscodeSubtitleFragment$initData$1(TranscodeSubtitleFragment transcodeSubtitleFragment) {
        this.this$0 = transcodeSubtitleFragment;
    }

    @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
    public void onEncodeSelected(String code, boolean z) {
        String str;
        Intrinsics.checkNotNullParameter(code, "code");
        HttpUploadRequest httpUploadRequest = new HttpUploadRequest(null, 1, null);
        str = this.this$0.filePath;
        Observable<R> compose = httpUploadRequest.addBaseParams("Srt_convert_encoding", "text/plain", new File(str), "zip_file").addParam("encoding", code).asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class));
        final TranscodeSubtitleFragment transcodeSubtitleFragment = this.this$0;
        Observable map = compose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeSubtitleFragment$initData$1$P9dbIFWiy2HY5L1s3EVyXFH3_2s
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m1230onEncodeSelected$lambda0;
                m1230onEncodeSelected$lambda0 = TranscodeSubtitleFragment$initData$1.m1230onEncodeSelected$lambda0(TranscodeSubtitleFragment.this, (TranscodeResponse) obj);
                return m1230onEncodeSelected$lambda0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "HttpUploadRequest()\n    …els\n                    }");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(map, this.this$0), new TranscodeSubtitleFragment$initData$1$onEncodeSelected$2(this.this$0), null, new TranscodeSubtitleFragment$initData$1$onEncodeSelected$3(this.this$0), null, new TranscodeSubtitleFragment$initData$1$onEncodeSelected$4(this.this$0), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onEncodeSelected$lambda-0  reason: not valid java name */
    public static final List m1230onEncodeSelected$lambda0(TranscodeSubtitleFragment this$0, TranscodeResponse it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        SrtParser.parseContentSrt(it.getSrt_content(), arrayList);
        File file = new File(Constant.DIR_TRANS_CODE_SUBTITLE + '/' + ((Object) URLDecoder.decode(it.getSrt_name(), "utf-8")));
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "file1.path");
        this$0.transcodeFilePath = path;
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeFileFromString(file, it.getSrt_content(), false);
        return arrayList;
    }

    @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
    public void onDone(List<SrtPraseModel> list, int i, boolean z, List<Subtitle> list2) {
        int boxType;
        String str;
        String sid;
        String id;
        String id2;
        int season;
        int episode;
        String language;
        String lang;
        HttpUploadRequest httpUploadRequest = new HttpUploadRequest(null, 1, null);
        boxType = this.this$0.getBoxType();
        String str2 = boxType == 1 ? "Upload_movie_srt_user" : "Upload_tv_srt_user";
        str = this.this$0.transcodeFilePath;
        HttpUploadRequest addBaseParams = httpUploadRequest.addBaseParams(str2, "text/plain", new File(str), IDataSource.SCHEME_FILE_TAG);
        sid = this.this$0.getSid();
        if (sid == null) {
            sid = "";
        }
        HttpUploadRequest addParam = addBaseParams.addParam("sid", sid);
        id = this.this$0.getId();
        if (id == null) {
            id = "";
        }
        HttpUploadRequest addParam2 = addParam.addParam("tid", id);
        id2 = this.this$0.getId();
        if (id2 == null) {
            id2 = "";
        }
        HttpUploadRequest addParam3 = addParam2.addParam("mid", id2);
        season = this.this$0.getSeason();
        HttpUploadRequest addParam4 = addParam3.addParam("season", Integer.valueOf(season));
        episode = this.this$0.getEpisode();
        HttpUploadRequest addParam5 = addParam4.addParam("episode", Integer.valueOf(episode));
        language = this.this$0.getLanguage();
        if (language == null) {
            language = "";
        }
        HttpUploadRequest addParam6 = addParam5.addParam("language", language).addParam(IjkMediaMeta.IJKM_KEY_FORMAT, "srt");
        lang = this.this$0.getLang();
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(addParam6.addParam("lang", lang != null ? lang : "").asRequest(), this.this$0), new TranscodeSubtitleFragment$initData$1$onDone$1(this.this$0), null, new TranscodeSubtitleFragment$initData$1$onDone$2(this.this$0), null, new TranscodeSubtitleFragment$initData$1$onDone$3(this.this$0, list2), 10, null);
    }

    @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
    public void onClose() {
        this.this$0.dismissAllowingStateLoss();
    }
}
