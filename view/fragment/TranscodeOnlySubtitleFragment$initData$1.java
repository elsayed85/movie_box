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
import com.movieboxpro.android.view.fragment.TranscodeOnlySubtitleFragment;
import com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: TranscodeOnlySubtitleFragment.kt */
@Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J8\u0010\u0004\u001a\u00020\u00032\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\u0011"}, d2 = {"com/movieboxpro/android/view/fragment/TranscodeOnlySubtitleFragment$initData$1", "Lcom/movieboxpro/android/view/videocontroller/TransCodingSubtitleController$TransCodingSubtitleCallback;", "onClose", "", "onDone", "srtPraseModels", "", "Lcom/dueeeke/model/SrtPraseModel;", "localSubtitlePosition", "", "isLocalFile", "", "subtitles", "Lcom/avery/subtitle/model/Subtitle;", "onEncodeSelected", "code", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranscodeOnlySubtitleFragment$initData$1 implements TransCodingSubtitleController.TransCodingSubtitleCallback {
    final /* synthetic */ TranscodeOnlySubtitleFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TranscodeOnlySubtitleFragment$initData$1(TranscodeOnlySubtitleFragment transcodeOnlySubtitleFragment) {
        this.this$0 = transcodeOnlySubtitleFragment;
    }

    @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
    public void onEncodeSelected(String code, boolean z) {
        String filePath;
        Intrinsics.checkNotNullParameter(code, "code");
        HttpUploadRequest httpUploadRequest = new HttpUploadRequest(null, 1, null);
        filePath = this.this$0.getFilePath();
        Observable<R> compose = httpUploadRequest.addBaseParams("Srt_convert_encoding", "text/plain", new File(filePath), "zip_file").addParam("encoding", code).asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class));
        final TranscodeOnlySubtitleFragment transcodeOnlySubtitleFragment = this.this$0;
        Observable map = compose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeOnlySubtitleFragment$initData$1$39RtST1dSqbUSFpY_cYyeOXQ9IQ
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m1225onEncodeSelected$lambda0;
                m1225onEncodeSelected$lambda0 = TranscodeOnlySubtitleFragment$initData$1.m1225onEncodeSelected$lambda0(TranscodeOnlySubtitleFragment.this, (TranscodeResponse) obj);
                return m1225onEncodeSelected$lambda0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "HttpUploadRequest()\n    …els\n                    }");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(map, this.this$0), new TranscodeOnlySubtitleFragment$initData$1$onEncodeSelected$2(this.this$0), null, new TranscodeOnlySubtitleFragment$initData$1$onEncodeSelected$3(this.this$0), null, new TranscodeOnlySubtitleFragment$initData$1$onEncodeSelected$4(this.this$0), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onEncodeSelected$lambda-0  reason: not valid java name */
    public static final List m1225onEncodeSelected$lambda0(TranscodeOnlySubtitleFragment this$0, TranscodeResponse it) {
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
        TranscodeOnlySubtitleFragment.OnSelectSubtitleListener onSelectSubtitleListener;
        onSelectSubtitleListener = this.this$0.listener;
        if (onSelectSubtitleListener != null) {
            if (list2 == null) {
                list2 = new ArrayList();
            }
            onSelectSubtitleListener.onSelectSubtitle(list2);
        }
        this.this$0.dismissAllowingStateLoss();
    }

    @Override // com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.TransCodingSubtitleCallback
    public void onClose() {
        this.this$0.dismissAllowingStateLoss();
    }
}
