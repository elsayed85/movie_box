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
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.TranscodeResponse;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SrtParser;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.net.URLDecoder;
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
/* compiled from: TranscodeOnlySubtitleFragment.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 B2\u00020\u0001:\u0002BCB\u0005¢\u0006\u0002\u0010\u0002J\b\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u000205H\u0016J\b\u00107\u001a\u000205H\u0016J\b\u00108\u001a\u000205H\u0016J$\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\u000e\u0010A\u001a\u0002052\u0006\u0010%\u001a\u00020&R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR/\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\r\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R/\u0010\u0019\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001a\u0010\u0015\"\u0004\b\u001b\u0010\u0017R/\u0010\u001d\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b \u0010\r\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R/\u0010!\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\r\u001a\u0004\b\"\u0010\u0015\"\u0004\b#\u0010\u0017R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010'\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\r\u001a\u0004\b(\u0010\t\"\u0004\b)\u0010\u000bR/\u0010+\u001a\u0004\u0018\u00010\u00122\b\u0010\u0005\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b.\u0010\r\u001a\u0004\b,\u0010\u0015\"\u0004\b-\u0010\u0017R\u000e\u0010/\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00100\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b3\u0010\r\u001a\u0004\b1\u0010\u0015\"\u0004\b2\u0010\u0017¨\u0006D"}, d2 = {"Lcom/movieboxpro/android/view/fragment/TranscodeOnlySubtitleFragment;", "Lcom/movieboxpro/android/base/BaseBindingFullScreenDialogFragment;", "()V", "binding", "Lcom/movieboxpro/android/databinding/FragmentTranscodeSubtitleBinding;", "<set-?>", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "episode", "getEpisode", "setEpisode", "episode$delegate", "", "filePath", "getFilePath", "()Ljava/lang/String;", "setFilePath", "(Ljava/lang/String;)V", "filePath$delegate", "id", "getId", "setId", "id$delegate", "lang", "getLang", "setLang", "lang$delegate", "language", "getLanguage", "setLanguage", "language$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/fragment/TranscodeOnlySubtitleFragment$OnSelectSubtitleListener;", "season", "getSeason", "setSeason", "season$delegate", "sid", "getSid", "setSid", "sid$delegate", "transcodeFilePath", "url", "getUrl", "setUrl", "url$delegate", "getTranscodeData", "", "initData", "initListener", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "Companion", "OnSelectSubtitleListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TranscodeOnlySubtitleFragment extends BaseBindingFullScreenDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "url", "getUrl()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "season", "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "episode", "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "sid", "getSid()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "lang", "getLang()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "language", "getLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "filePath", "getFilePath()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(TranscodeOnlySubtitleFragment.class, "id", "getId()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    private FragmentTranscodeSubtitleBinding binding;
    private final ReadWriteProperty boxType$delegate;
    private final ReadWriteProperty episode$delegate;
    private final ReadWriteProperty filePath$delegate;
    private final ReadWriteProperty id$delegate;
    private final ReadWriteProperty lang$delegate;
    private final ReadWriteProperty language$delegate;
    private OnSelectSubtitleListener listener;
    private final ReadWriteProperty season$delegate;
    private final ReadWriteProperty sid$delegate;
    private final ReadWriteProperty url$delegate;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String transcodeFilePath = "";

    /* compiled from: TranscodeOnlySubtitleFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/fragment/TranscodeOnlySubtitleFragment$OnSelectSubtitleListener;", "", "onSelectSubtitle", "", "subtitles", "", "Lcom/avery/subtitle/model/Subtitle;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    public TranscodeOnlySubtitleFragment() {
        TranscodeOnlySubtitleFragment transcodeOnlySubtitleFragment = this;
        this.url$delegate = FragmentArgsKt.arg(transcodeOnlySubtitleFragment);
        this.boxType$delegate = FragmentArgsKt.arg(transcodeOnlySubtitleFragment);
        this.season$delegate = FragmentArgsKt.arg(transcodeOnlySubtitleFragment);
        this.episode$delegate = FragmentArgsKt.arg(transcodeOnlySubtitleFragment);
        this.sid$delegate = FragmentArgsKt.argOrNull(transcodeOnlySubtitleFragment);
        this.lang$delegate = FragmentArgsKt.argOrNull(transcodeOnlySubtitleFragment);
        this.language$delegate = FragmentArgsKt.argOrNull(transcodeOnlySubtitleFragment);
        this.filePath$delegate = FragmentArgsKt.argOrNull(transcodeOnlySubtitleFragment);
        this.id$delegate = FragmentArgsKt.argOrNull(transcodeOnlySubtitleFragment);
    }

    private final String getUrl() {
        return (String) this.url$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final void setUrl(String str) {
        this.url$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    private final int getBoxType() {
        return ((Number) this.boxType$delegate.getValue(this, $$delegatedProperties[1])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType$delegate.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    private final int getSeason() {
        return ((Number) this.season$delegate.getValue(this, $$delegatedProperties[2])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeason(int i) {
        this.season$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    private final int getEpisode() {
        return ((Number) this.episode$delegate.getValue(this, $$delegatedProperties[3])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEpisode(int i) {
        this.episode$delegate.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    private final String getSid() {
        return (String) this.sid$delegate.getValue(this, $$delegatedProperties[4]);
    }

    private final void setSid(String str) {
        this.sid$delegate.setValue(this, $$delegatedProperties[4], str);
    }

    private final String getLang() {
        return (String) this.lang$delegate.getValue(this, $$delegatedProperties[5]);
    }

    private final void setLang(String str) {
        this.lang$delegate.setValue(this, $$delegatedProperties[5], str);
    }

    private final String getLanguage() {
        return (String) this.language$delegate.getValue(this, $$delegatedProperties[6]);
    }

    private final void setLanguage(String str) {
        this.language$delegate.setValue(this, $$delegatedProperties[6], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFilePath() {
        return (String) this.filePath$delegate.getValue(this, $$delegatedProperties[7]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFilePath(String str) {
        this.filePath$delegate.setValue(this, $$delegatedProperties[7], str);
    }

    private final String getId() {
        return (String) this.id$delegate.getValue(this, $$delegatedProperties[8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id$delegate.setValue(this, $$delegatedProperties[8], str);
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
        fragmentTranscodeSubtitleBinding.controller.setTransCodingSubtitleCallback(new TranscodeOnlySubtitleFragment$initData$1(this));
    }

    private final void getTranscodeData() {
        final ArrayList arrayList = new ArrayList();
        Object as = Http.getService().Encoding_list(API.BASE_URL, "Encoding_list").compose(RxUtils.rxTranslate2Bean(HashMap.class)).map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeOnlySubtitleFragment$DYJEoTNNy_pVRuQ1EOqX75bfPzI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                List m1221getTranscodeData$lambda0;
                m1221getTranscodeData$lambda0 = TranscodeOnlySubtitleFragment.m1221getTranscodeData$lambda0(arrayList, (HashMap) obj);
                return m1221getTranscodeData$lambda0;
            }
        }).flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeOnlySubtitleFragment$i00GsL4yRMYtUVUi3YirhSHjl30
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1222getTranscodeData$lambda2;
                m1222getTranscodeData$lambda2 = TranscodeOnlySubtitleFragment.m1222getTranscodeData$lambda2(TranscodeOnlySubtitleFragment.this, (List) obj);
                return m1222getTranscodeData$lambda2;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "getService().Encoding_li…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new TranscodeOnlySubtitleFragment$getTranscodeData$3(this, arrayList), null, new TranscodeOnlySubtitleFragment$getTranscodeData$4(this), null, new TranscodeOnlySubtitleFragment$getTranscodeData$5(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getTranscodeData$lambda-0  reason: not valid java name */
    public static final List m1221getTranscodeData$lambda0(ArrayList codes, HashMap hashMap) {
        Intrinsics.checkNotNullParameter(codes, "$codes");
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
        codes.addAll(arrayList);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getTranscodeData$lambda-2  reason: not valid java name */
    public static final ObservableSource m1222getTranscodeData$lambda2(final TranscodeOnlySubtitleFragment this$0, final List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "list");
        return new HttpUploadRequest(null, 1, null).addBaseParams("Srt_convert_encoding", "text/plain", new File(this$0.getFilePath()), "zip_file").addParam("encoding", SettingManager.INSTANCE.getTranscodeCode()).asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class)).map(new Function() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$TranscodeOnlySubtitleFragment$syyYbWCMuGPTLMQ6ca-LHOeEs6A
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Pair m1223getTranscodeData$lambda2$lambda1;
                m1223getTranscodeData$lambda2$lambda1 = TranscodeOnlySubtitleFragment.m1223getTranscodeData$lambda2$lambda1(TranscodeOnlySubtitleFragment.this, list, (TranscodeResponse) obj);
                return m1223getTranscodeData$lambda2$lambda1;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getTranscodeData$lambda-2$lambda-1  reason: not valid java name */
    public static final Pair m1223getTranscodeData$lambda2$lambda1(TranscodeOnlySubtitleFragment this$0, List list, TranscodeResponse it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(list, "$list");
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
        return new Pair(list, arrayList);
    }

    /* compiled from: TranscodeOnlySubtitleFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/fragment/TranscodeOnlySubtitleFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/TranscodeOnlySubtitleFragment;", "filePath", "", "boxType", "", "id", "season", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TranscodeOnlySubtitleFragment newInstance(String str, int i, String str2, int i2, int i3) {
            TranscodeOnlySubtitleFragment transcodeOnlySubtitleFragment = new TranscodeOnlySubtitleFragment();
            transcodeOnlySubtitleFragment.setBoxType(i);
            transcodeOnlySubtitleFragment.setId(str2);
            transcodeOnlySubtitleFragment.setSeason(i2);
            transcodeOnlySubtitleFragment.setEpisode(i3);
            transcodeOnlySubtitleFragment.setFilePath(str);
            return transcodeOnlySubtitleFragment;
        }
    }
}
