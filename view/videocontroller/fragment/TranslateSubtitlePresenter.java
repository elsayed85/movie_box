package com.movieboxpro.android.view.videocontroller.fragment;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.avery.subtitle.format.FormatSRT;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.TimedTextObject;
import com.dueeeke.model.SrtPraseModel;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
/* compiled from: TranslateSubtitlePresenter.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J.\u0010\u000b\u001a\u00020\b2\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000f2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\nJ \u0010\u0012\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\nH\u0016J(\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitlePresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract$View;", "Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "parseContent", "", FirebaseAnalytics.Param.CONTENT, "", "startTranslate", MediaTrack.ROLE_SUBTITLE, "Ljava/util/ArrayList;", "Lcom/avery/subtitle/model/Subtitle;", "Lkotlin/collections/ArrayList;", "fromLanguage", "toLanguage", "translate", "sid", "boxType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranslateSubtitlePresenter extends BasePresenter<TranslateSubtitleContract.View> implements TranslateSubtitleContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranslateSubtitlePresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.Presenter
    public void parseContent(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        ((ObservableSubscribeProxy) Observable.just(content).map($$Lambda$TranslateSubtitlePresenter$9IWNWLrbcVBNI22TyXgxgWP0ti4.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<SrtPraseModel>>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitlePresenter$parseContent$2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TranslateSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<SrtPraseModel> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TranslateSubtitlePresenter.this.getView().hideLoadingView();
                TranslateSubtitlePresenter.this.getView().showContent(model);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TranslateSubtitlePresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: parseContent$lambda-0  reason: not valid java name */
    public static final ArrayList m1397parseContent$lambda0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        SrtParser.parseContentSrt(it, arrayList);
        return arrayList;
    }

    public final void startTranslate(ArrayList<Subtitle> subtitle, final String fromLanguage, final String toLanguage) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        CommonExtKt.onMobEvent(context, "Translate_start");
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Ref.IntRef intRef = new Ref.IntRef();
        Object as = Observable.just(subtitle).map(new Function() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitlePresenter$Rvu-CFaeK476evmGtMY1UrEy2Ow
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ArrayList m1398startTranslate$lambda2;
                m1398startTranslate$lambda2 = TranslateSubtitlePresenter.m1398startTranslate$lambda2(arrayList2, (ArrayList) obj);
                return m1398startTranslate$lambda2;
            }
        }).subscribeOn(Schedulers.io()).flatMap(new Function() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitlePresenter$VPEHcwoZ73ue5rViXKZIfNHjmUw
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1399startTranslate$lambda6;
                m1399startTranslate$lambda6 = TranslateSubtitlePresenter.m1399startTranslate$lambda6(fromLanguage, toLanguage, arrayList2, (ArrayList) obj);
                return m1399startTranslate$lambda6;
            }
        }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(as, "just(subtitle)\n         …leOwner(mLifecycleOwner))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new TranslateSubtitlePresenter$startTranslate$3(this), new TranslateSubtitlePresenter$startTranslate$4(arrayList2, this, arrayList), new TranslateSubtitlePresenter$startTranslate$5(this), null, new TranslateSubtitlePresenter$startTranslate$6(intRef, this, arrayList2), 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTranslate$lambda-2  reason: not valid java name */
    public static final ArrayList m1398startTranslate$lambda2(ArrayList subtitleList, ArrayList it) {
        Intrinsics.checkNotNullParameter(subtitleList, "$subtitleList");
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            Subtitle subtitle = (Subtitle) it2.next();
            if (sb.length() < 4500) {
                sb.append(subtitle.content);
                arrayList.add(subtitle);
            } else {
                subtitleList.add(new ArrayList(arrayList));
                StringsKt.clear(sb);
                sb.append(subtitle.content);
                arrayList.clear();
                arrayList.add(subtitle);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (!arrayList2.isEmpty()) {
            subtitleList.add(new ArrayList(arrayList2));
        }
        return subtitleList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTranslate$lambda-6  reason: not valid java name */
    public static final ObservableSource m1399startTranslate$lambda6(String fromLanguage, String toLanguage, final ArrayList subtitleList, ArrayList it) {
        Intrinsics.checkNotNullParameter(fromLanguage, "$fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "$toLanguage");
        Intrinsics.checkNotNullParameter(subtitleList, "$subtitleList");
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        final int i = 0;
        for (Object obj : it) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            StringBuilder sb = new StringBuilder();
            int i3 = 0;
            for (Object obj2 : (ArrayList) obj) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Subtitle subtitle = (Subtitle) obj2;
                sb.append(i4);
                sb.append(ShellUtil.COMMAND_LINE_END);
                sb.append(subtitle.start.getTime("hh:mm:ss,ms"));
                sb.append(" --> ");
                sb.append(subtitle.end.getTime("hh:mm:ss,ms"));
                sb.append(ShellUtil.COMMAND_LINE_END);
                sb.append(subtitle.content);
                sb.append(ShellUtil.COMMAND_LINE_END);
                sb.append(ShellUtil.COMMAND_LINE_END);
                i3 = i4;
            }
            arrayList.add(Http.getService().translate(API.TRANSLATE_URL, fromLanguage, toLanguage, sb.toString()).map(new Function() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitlePresenter$9dJFJxDJL1Imafa9vFjrvQgcj-Y
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj3) {
                    Unit m1400startTranslate$lambda6$lambda5$lambda4;
                    m1400startTranslate$lambda6$lambda5$lambda4 = TranslateSubtitlePresenter.m1400startTranslate$lambda6$lambda5$lambda4(subtitleList, i, (String) obj3);
                    return m1400startTranslate$lambda6$lambda5$lambda4;
                }
            }).subscribeOn(Schedulers.io()));
            i = i2;
        }
        return Observable.merge(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: startTranslate$lambda-6$lambda-5$lambda-4  reason: not valid java name */
    public static final Unit m1400startTranslate$lambda6$lambda5$lambda4(ArrayList subtitleList, int i, String it) {
        Intrinsics.checkNotNullParameter(subtitleList, "$subtitleList");
        Intrinsics.checkNotNullParameter(it, "it");
        FormatSRT formatSRT = new FormatSRT();
        byte[] bytes = it.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        subtitleList.set(i, new ArrayList(formatSRT.parseFile("", new ByteArrayInputStream(bytes)).captions.values()));
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.Presenter
    public void translate(String fromLanguage, String toLanguage, String content) {
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        Intrinsics.checkNotNullParameter(content, "content");
        ((ObservableSubscribeProxy) Http.getService().translate(API.TRANSLATE_URL, fromLanguage, toLanguage, content).map($$Lambda$TranslateSubtitlePresenter$Da6CTWhGw9yuOUfdMP4I3EctN4k.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<SrtPraseModel>>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitlePresenter$translate$2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TranslateSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<SrtPraseModel> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TranslateSubtitlePresenter.this.getView().showSubtitles(model);
                TranslateSubtitlePresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                ToastUtils.showShort(Intrinsics.stringPlus("Translate failed:", e.getMessage()), new Object[0]);
                TranslateSubtitlePresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: translate$lambda-8  reason: not valid java name */
    public static final ArrayList m1402translate$lambda8(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FormatSRT formatSRT = new FormatSRT();
        byte[] bytes = it.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        TimedTextObject parseFile = formatSRT.parseFile("", new ByteArrayInputStream(bytes));
        ArrayList arrayList = new ArrayList();
        Collection<Subtitle> values = parseFile.captions.values();
        Intrinsics.checkNotNullExpressionValue(values, "subtitles.captions.values");
        for (Subtitle subtitle : values) {
            SrtPraseModel srtPraseModel = new SrtPraseModel();
            srtPraseModel.setBeginTime(subtitle.start.mseconds);
            srtPraseModel.setEndTime(subtitle.end.mseconds);
            srtPraseModel.setSrtBody(subtitle.content);
            arrayList.add(srtPraseModel);
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.Presenter
    public void translate(String fromLanguage, String toLanguage, String sid, int i) {
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        Intrinsics.checkNotNullParameter(sid, "sid");
        if (i == 1) {
            ((ObservableSubscribeProxy) Http.getService().translateMovie(API.TRANSLATE_URL, fromLanguage, toLanguage, sid).map($$Lambda$TranslateSubtitlePresenter$3Tc5u9s0a2R_UVzu7CbSgSzUzU.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<SrtPraseModel>>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitlePresenter$translate$4
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    TranslateSubtitlePresenter.this.getView().showLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(List<SrtPraseModel> model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    TranslateSubtitlePresenter.this.getView().showSubtitles(model);
                    TranslateSubtitlePresenter.this.getView().hideLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    ToastUtils.showShort(Intrinsics.stringPlus("Translate failed:", e.getMessage()), new Object[0]);
                    TranslateSubtitlePresenter.this.getView().hideLoadingView();
                }
            });
        } else {
            ((ObservableSubscribeProxy) Http.getService().translateTv(API.TRANSLATE_URL, fromLanguage, toLanguage, sid).map($$Lambda$TranslateSubtitlePresenter$s4OboLB4qTpAE5vI74kyYoJDZ7U.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<SrtPraseModel>>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitlePresenter$translate$6
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                    TranslateSubtitlePresenter.this.getView().showLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(List<SrtPraseModel> model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    TranslateSubtitlePresenter.this.getView().showSubtitles(model);
                    TranslateSubtitlePresenter.this.getView().hideLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    ToastUtils.showShort(Intrinsics.stringPlus("Translate failed:", e.getMessage()), new Object[0]);
                    TranslateSubtitlePresenter.this.getView().hideLoadingView();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: translate$lambda-9  reason: not valid java name */
    public static final ArrayList m1403translate$lambda9(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        SrtParser.parseContentSrt(it, arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: translate$lambda-10  reason: not valid java name */
    public static final ArrayList m1401translate$lambda10(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        SrtParser.parseContentSrt(it, arrayList);
        return arrayList;
    }
}
