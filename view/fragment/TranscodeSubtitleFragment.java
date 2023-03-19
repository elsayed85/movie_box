package com.movieboxpro.android.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.alibaba.fastjson.JSON;
import com.avery.subtitle.model.Subtitle;
import com.dueeeke.model.EncodeModel;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment;
import com.movieboxpro.android.databinding.FragmentTranscodeSubtitleBinding;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SrtParser;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
/* compiled from: TranscodeSubtitleFragment.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 ?2\u00020\u0001:\u0002?@B\u0005¢\u0006\u0002\u0010\u0002J\b\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u000202H\u0016J\b\u00104\u001a\u000202H\u0016J\b\u00105\u001a\u000202H\u0016J$\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u000e\u0010>\u001a\u0002022\u0006\u0010\"\u001a\u00020#R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\r\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R/\u0010\u001a\u001a\u0004\u0018\u00010\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\r\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R/\u0010\u001e\u001a\u0004\u0018\u00010\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\r\u001a\u0004\b\u001f\u0010\u0016\"\u0004\b \u0010\u0018R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010$\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\r\u001a\u0004\b%\u0010\t\"\u0004\b&\u0010\u000bR/\u0010(\u001a\u0004\u0018\u00010\u00132\b\u0010\u0005\u001a\u0004\u0018\u00010\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\r\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\u000e\u0010,\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010-\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00138B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b0\u0010\r\u001a\u0004\b.\u0010\u0016\"\u0004\b/\u0010\u0018¨\u0006A"}, d2 = {"Lcom/movieboxpro/android/view/fragment/TranscodeSubtitleFragment;", "Lcom/movieboxpro/android/base/BaseBindingFullScreenDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/FragmentTranscodeSubtitleBinding;", "<set-?>", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "episode", "getEpisode", "setEpisode", "episode$delegate", "filePath", "", "id", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "id$delegate", "lang", "getLang", "setLang", "lang$delegate", "language", "getLanguage", "setLanguage", "language$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/TranscodeSubtitleFragment$OnSelectSubtitleListener;", "season", "getSeason", "setSeason", "season$delegate", "sid", "getSid", "setSid", "sid$delegate", "transcodeFilePath", "url", "getUrl", "setUrl", "url$delegate", "getTranscodeData", "", "initData", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "Companion", "OnSelectSubtitleListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranscodeSubtitleFragment extends BaseBindingFullScreenDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "url", "getUrl()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "season", "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "episode", "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "sid", "getSid()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "lang", "getLang()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "language", "getLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeSubtitleFragment.class, "id", "getId()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    private FragmentTranscodeSubtitleBinding binding;
    private final ReadWriteProperty boxType$delegate;
    private final ReadWriteProperty episode$delegate;
    private final ReadWriteProperty id$delegate;
    private final ReadWriteProperty lang$delegate;
    private final ReadWriteProperty language$delegate;
    private OnSelectSubtitleListener listener;
    private final ReadWriteProperty season$delegate;
    private final ReadWriteProperty sid$delegate;
    private final ReadWriteProperty url$delegate;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String filePath = "";
    private String transcodeFilePath = "";

    /* compiled from: TranscodeSubtitleFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/TranscodeSubtitleFragment$OnSelectSubtitleListener;", "", "onSelectSubtitle", "", "subtitles", "", "Lcom/avery/subtitle/model/Subtitle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnSelectSubtitleListener {
        void onSelectSubtitle(List<Subtitle> list);
    }

    @Override // com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public TranscodeSubtitleFragment() {
        TranscodeSubtitleFragment transcodeSubtitleFragment = this;
        this.url$delegate = FragmentArgsKt.arg(transcodeSubtitleFragment);
        this.boxType$delegate = FragmentArgsKt.arg(transcodeSubtitleFragment);
        this.season$delegate = FragmentArgsKt.arg(transcodeSubtitleFragment);
        this.episode$delegate = FragmentArgsKt.arg(transcodeSubtitleFragment);
        this.sid$delegate = FragmentArgsKt.argOrNull(transcodeSubtitleFragment);
        this.lang$delegate = FragmentArgsKt.argOrNull(transcodeSubtitleFragment);
        this.language$delegate = FragmentArgsKt.argOrNull(transcodeSubtitleFragment);
        this.id$delegate = FragmentArgsKt.argOrNull(transcodeSubtitleFragment);
    }

    private final String getUrl() {
        return (String) this.url$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUrl(String str) {
        this.url$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getBoxType() {
        return ((Number) this.boxType$delegate.getValue(this, $$delegatedProperties[1])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType$delegate.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSeason() {
        return ((Number) this.season$delegate.getValue(this, $$delegatedProperties[2])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeason(int i) {
        this.season$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getEpisode() {
        return ((Number) this.episode$delegate.getValue(this, $$delegatedProperties[3])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEpisode(int i) {
        this.episode$delegate.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSid() {
        return (String) this.sid$delegate.getValue(this, $$delegatedProperties[4]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSid(String str) {
        this.sid$delegate.setValue(this, $$delegatedProperties[4], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getLang() {
        return (String) this.lang$delegate.getValue(this, $$delegatedProperties[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLang(String str) {
        this.lang$delegate.setValue(this, $$delegatedProperties[5], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getLanguage() {
        return (String) this.language$delegate.getValue(this, $$delegatedProperties[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLanguage(String str) {
        this.language$delegate.setValue(this, $$delegatedProperties[6], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getId() {
        return (String) this.id$delegate.getValue(this, $$delegatedProperties[7]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id$delegate.setValue(this, $$delegatedProperties[7], str);
    }

    public final void setListener(OnSelectSubtitleListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_transcode_subtitle, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            inf…          false\n        )");
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding = (FragmentTranscodeSubtitleBinding) inflate;
        this.binding = fragmentTranscodeSubtitleBinding;
        if (fragmentTranscodeSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentTranscodeSubtitleBinding = null;
        }
        View root = fragmentTranscodeSubtitleBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.base.BaseBindingFullScreenDialogFragment
    public void initData() {
        getTranscodeData();
        FragmentTranscodeSubtitleBinding fragmentTranscodeSubtitleBinding = this.binding;
        if (fragmentTranscodeSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentTranscodeSubtitleBinding = null;
        }
        fragmentTranscodeSubtitleBinding.controller.setTransCodingSubtitleCallback(new TranscodeSubtitleFragment$initData$1(this));
    }

    private final void getTranscodeData() {
        Object as = Http.getService().Encoding_list(API.BASE_URL, "Encoding_list").compose(RxUtils.rxTranslate2Bean(HashMap.class)).map($$Lambda$TranscodeSubtitleFragment$BAGK7NJTaXFQwwyDo7zQ_jn3eo.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeSubtitleFragment$qH8YJvEti_biz0h97fUIKoYoU3E
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1227getTranscodeData$lambda2;
                m1227getTranscodeData$lambda2 = TranscodeSubtitleFragment.m1227getTranscodeData$lambda2(TranscodeSubtitleFragment.this, (List) obj);
                return m1227getTranscodeData$lambda2;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "getService().Encoding_li…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new TranscodeSubtitleFragment$getTranscodeData$3(this), null, new TranscodeSubtitleFragment$getTranscodeData$4(this), null, new TranscodeSubtitleFragment$getTranscodeData$5(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getTranscodeData$lambda-0  reason: not valid java name */
    public static final List m1226getTranscodeData$lambda0(HashMap hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "hashMap");
        ArrayList arrayList = new ArrayList();
        for (Object obj : ((HashMap) JSON.parseObject(JSON.toJSONString(hashMap.get("list")), HashMap.class)).entrySet()) {
            if (obj == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map.Entry<*, *>");
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            EncodeModel encodeModel = new EncodeModel();
            encodeModel.setLanguage((String) key);
            encodeModel.setCode(JSON.parseArray(String.valueOf(value), String.class));
            arrayList.add(encodeModel);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getTranscodeData$lambda-2  reason: not valid java name */
    public static final ObservableSource m1227getTranscodeData$lambda2(final TranscodeSubtitleFragment this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "list");
        return Http.getService2().download(this$0.getUrl()).map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeSubtitleFragment$HTMbIM0qgoag17tmcmYP6Lx4Big
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Pair m1228getTranscodeData$lambda2$lambda1;
                m1228getTranscodeData$lambda2$lambda1 = TranscodeSubtitleFragment.m1228getTranscodeData$lambda2$lambda1(TranscodeSubtitleFragment.this, list, (ResponseBody) obj);
                return m1228getTranscodeData$lambda2$lambda1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getTranscodeData$lambda-2$lambda-1  reason: not valid java name */
    public static final Pair m1228getTranscodeData$lambda2$lambda1(TranscodeSubtitleFragment this$0, List list, ResponseBody it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "$list");
        Intrinsics.checkNotNullParameter(it, "it");
        String str = Constant.DIR_DOWNLOAD;
        String substring = this$0.getUrl().substring(StringsKt.lastIndexOf$default((CharSequence) this$0.getUrl(), "/", 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        File file = new File(str, substring);
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "file.path");
        this$0.filePath = path;
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeFileFromIS(file, it.byteStream());
        ArrayList arrayList = new ArrayList();
        SrtParser.parseContentSrt(FileUtils.readFile2String(file), arrayList);
        return new Pair(list, arrayList);
    }

    /* compiled from: TranscodeSubtitleFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JP\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/fragment/TranscodeSubtitleFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/TranscodeSubtitleFragment;", "url", "", "sid", "lang", "language", "boxType", "", "id", "season", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TranscodeSubtitleFragment newInstance(String str, String str2, String str3, String str4, int i, String str5, int i2, int i3) {
            TranscodeSubtitleFragment transcodeSubtitleFragment = new TranscodeSubtitleFragment();
            if (str == null) {
                str = "";
            }
            transcodeSubtitleFragment.setUrl(str);
            transcodeSubtitleFragment.setSid(str2);
            transcodeSubtitleFragment.setLang(str3);
            transcodeSubtitleFragment.setBoxType(i);
            transcodeSubtitleFragment.setId(str5);
            transcodeSubtitleFragment.setSeason(i2);
            transcodeSubtitleFragment.setEpisode(i3);
            transcodeSubtitleFragment.setLanguage(str4);
            return transcodeSubtitleFragment;
        }
    }
}
