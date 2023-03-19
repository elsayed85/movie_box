package com.movieboxpro.android.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.avery.subtitle.format.FormatSRT;
import com.avery.subtitle.model.Subtitle;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.DialogPreviewSubtitleBinding;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.TranscodeResponse;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.fragment.TranscodeSubtitleFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.ResponseBody;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: PreviewSubtitleDialog.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 I2\u00020\u0001:\u0001IB\u0005¢\u0006\u0002\u0010\u0002J\b\u00109\u001a\u00020:H\u0016J\b\u0010;\u001a\u00020:H\u0016J\b\u0010<\u001a\u00020:H\u0016J\b\u0010=\u001a\u00020>H\u0014J$\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010FH\u0016J\u0016\u0010G\u001a\u00020:2\u0006\u0010(\u001a\u00020)2\u0006\u0010.\u001a\u00020/J\b\u0010H\u001a\u00020:H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR/\u0010\u001c\u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR/\u0010 \u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0010\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR/\u0010$\u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0010\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010*\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u0010\u001a\u0004\b+\u0010\f\"\u0004\b,\u0010\u000eR\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R/\u00100\u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b3\u0010\u0010\u001a\u0004\b1\u0010\u0018\"\u0004\b2\u0010\u001aR\u000e\u00104\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R+\u00105\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b8\u0010\u0010\u001a\u0004\b6\u0010\u0018\"\u0004\b7\u0010\u001a¨\u0006J"}, d2 = {"Lcom/movieboxpro/android/view/activity/PreviewSubtitleDialog;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/avery/subtitle/model/Subtitle;", "binding", "Lcom/movieboxpro/android/databinding/DialogPreviewSubtitleBinding;", "<set-?>", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "episode", "getEpisode", "setEpisode", "episode$delegate", "", "fileName", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "fileName$delegate", "id", "getId", "setId", "id$delegate", "lang", "getLang", "setLang", "lang$delegate", "language", "getLanguage", "setLanguage", "language$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "season", "getSeason", "setSeason", "season$delegate", "selectListener", "Lcom/movieboxpro/android/view/fragment/TranscodeSubtitleFragment$OnSelectSubtitleListener;", "sid", "getSid", "setSid", "sid$delegate", "transcodeFilePath", "url", "getUrl", "setUrl", "url$delegate", "initData", "", "initListener", "initView", "needFullscreen", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "transcodeSubtitle", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreviewSubtitleDialog extends BaseBindingBottomDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "url", "getUrl()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "fileName", "getFileName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "season", "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "episode", "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "sid", "getSid()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "lang", "getLang()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "language", "getLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewSubtitleDialog.class, "id", "getId()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    private CommBaseAdapter<Subtitle> adapter;
    private DialogPreviewSubtitleBinding binding;
    private final ReadWriteProperty boxType$delegate;
    private final ReadWriteProperty episode$delegate;
    private final ReadWriteProperty fileName$delegate;
    private final ReadWriteProperty id$delegate;
    private final ReadWriteProperty lang$delegate;
    private final ReadWriteProperty language$delegate;
    private DialogAction.ActionListener listener;
    private final ReadWriteProperty season$delegate;
    private TranscodeSubtitleFragment.OnSelectSubtitleListener selectListener;
    private final ReadWriteProperty sid$delegate;
    private final ReadWriteProperty url$delegate;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String transcodeFilePath = "";

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
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

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    protected boolean needFullscreen() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public PreviewSubtitleDialog() {
        PreviewSubtitleDialog previewSubtitleDialog = this;
        this.url$delegate = FragmentArgsKt.arg(previewSubtitleDialog);
        this.fileName$delegate = FragmentArgsKt.arg(previewSubtitleDialog);
        this.boxType$delegate = FragmentArgsKt.arg(previewSubtitleDialog);
        this.season$delegate = FragmentArgsKt.arg(previewSubtitleDialog);
        this.episode$delegate = FragmentArgsKt.arg(previewSubtitleDialog);
        this.sid$delegate = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.lang$delegate = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.language$delegate = FragmentArgsKt.argOrNull(previewSubtitleDialog);
        this.id$delegate = FragmentArgsKt.argOrNull(previewSubtitleDialog);
    }

    private final String getUrl() {
        return (String) this.url$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUrl(String str) {
        this.url$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    private final String getFileName() {
        return (String) this.fileName$delegate.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFileName(String str) {
        this.fileName$delegate.setValue(this, $$delegatedProperties[1], str);
    }

    private final int getBoxType() {
        return ((Number) this.boxType$delegate.getValue(this, $$delegatedProperties[2])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    private final int getSeason() {
        return ((Number) this.season$delegate.getValue(this, $$delegatedProperties[3])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeason(int i) {
        this.season$delegate.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    private final int getEpisode() {
        return ((Number) this.episode$delegate.getValue(this, $$delegatedProperties[4])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEpisode(int i) {
        this.episode$delegate.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSid() {
        return (String) this.sid$delegate.getValue(this, $$delegatedProperties[5]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSid(String str) {
        this.sid$delegate.setValue(this, $$delegatedProperties[5], str);
    }

    private final String getLang() {
        return (String) this.lang$delegate.getValue(this, $$delegatedProperties[6]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLang(String str) {
        this.lang$delegate.setValue(this, $$delegatedProperties[6], str);
    }

    private final String getLanguage() {
        return (String) this.language$delegate.getValue(this, $$delegatedProperties[7]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLanguage(String str) {
        this.language$delegate.setValue(this, $$delegatedProperties[7], str);
    }

    private final String getId() {
        return (String) this.id$delegate.getValue(this, $$delegatedProperties[8]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id$delegate.setValue(this, $$delegatedProperties[8], str);
    }

    public final void setListener(DialogAction.ActionListener listener, TranscodeSubtitleFragment.OnSelectSubtitleListener selectListener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(selectListener, "selectListener");
        this.listener = listener;
        this.selectListener = selectListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_preview_subtitle, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…btitle, container, false)");
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding = (DialogPreviewSubtitleBinding) inflate;
        this.binding = dialogPreviewSubtitleBinding;
        if (dialogPreviewSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewSubtitleBinding = null;
        }
        View root = dialogPreviewSubtitleBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding = this.binding;
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding2 = null;
        if (dialogPreviewSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewSubtitleBinding = null;
        }
        dialogPreviewSubtitleBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PreviewSubtitleDialog$Mx9mCW_gs7mCCIpKeseBAR90S5Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewSubtitleDialog.m193initListener$lambda0(PreviewSubtitleDialog.this, view);
            }
        });
        DialogPreviewSubtitleBinding dialogPreviewSubtitleBinding3 = this.binding;
        if (dialogPreviewSubtitleBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogPreviewSubtitleBinding2 = dialogPreviewSubtitleBinding3;
        }
        dialogPreviewSubtitleBinding2.tvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PreviewSubtitleDialog$8OFci6S5i5dkQ5pcL-hA0vjWJkw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewSubtitleDialog.m194initListener$lambda1(PreviewSubtitleDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m193initListener$lambda0(PreviewSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m194initListener$lambda1(PreviewSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        HttpUploadRequest addBaseParams = new HttpUploadRequest(null, 1, null).addBaseParams(this$0.getBoxType() == 1 ? "Upload_movie_srt_user" : "Upload_tv_srt_user", "text/plain", new File(this$0.transcodeFilePath), IDataSource.SCHEME_FILE_TAG);
        String sid = this$0.getSid();
        if (sid == null) {
            sid = "";
        }
        HttpUploadRequest addParam = addBaseParams.addParam("sid", sid);
        String id = this$0.getId();
        if (id == null) {
            id = "";
        }
        HttpUploadRequest addParam2 = addParam.addParam("tid", id);
        String id2 = this$0.getId();
        if (id2 == null) {
            id2 = "";
        }
        HttpUploadRequest addParam3 = addParam2.addParam("mid", id2).addParam("season", Integer.valueOf(this$0.getSeason())).addParam("episode", Integer.valueOf(this$0.getEpisode()));
        String language = this$0.getLanguage();
        if (language == null) {
            language = "";
        }
        HttpUploadRequest addParam4 = addParam3.addParam("language", language).addParam(IjkMediaMeta.IJKM_KEY_FORMAT, "srt");
        String lang = this$0.getLang();
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(addParam4.addParam("lang", lang != null ? lang : "").asRequest(), this$0), new PreviewSubtitleDialog$initListener$2$1(this$0), null, new PreviewSubtitleDialog$initListener$2$2(this$0), null, new PreviewSubtitleDialog$initListener$2$3(this$0), 10, null);
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
        transcodeSubtitle();
    }

    private final void transcodeSubtitle() {
        Object as = Http.getService2().download(getUrl()).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PreviewSubtitleDialog$7EsBFkZkRiFZe9SD4qZRe058g_k
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m197transcodeSubtitle$lambda3;
                m197transcodeSubtitle$lambda3 = PreviewSubtitleDialog.m197transcodeSubtitle$lambda3(PreviewSubtitleDialog.this, (ResponseBody) obj);
                return m197transcodeSubtitle$lambda3;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "getService2().download(u…bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new PreviewSubtitleDialog$transcodeSubtitle$2(this), null, new PreviewSubtitleDialog$transcodeSubtitle$3(this), null, new PreviewSubtitleDialog$transcodeSubtitle$4(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: transcodeSubtitle$lambda-3  reason: not valid java name */
    public static final ObservableSource m197transcodeSubtitle$lambda3(final PreviewSubtitleDialog this$0, ResponseBody it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(Constant.DIR_DOWNLOAD, Intrinsics.stringPlus(this$0.getFileName(), Constant.SUFFIX_ZIP));
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeFileFromIS(file, it.byteStream());
        return new HttpUploadRequest(null, 1, null).addBaseParams("Srt_convert_encoding", "text/plain", file, "zip_file").addParam("encoding", SettingManager.INSTANCE.getTranscodeCode()).asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class)).map(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$PreviewSubtitleDialog$1jrHROgM-Awi4tyZLg6bCxnCtz4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ArrayList m198transcodeSubtitle$lambda3$lambda2;
                m198transcodeSubtitle$lambda3$lambda2 = PreviewSubtitleDialog.m198transcodeSubtitle$lambda3$lambda2(PreviewSubtitleDialog.this, (TranscodeResponse) obj);
                return m198transcodeSubtitle$lambda3$lambda2;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: transcodeSubtitle$lambda-3$lambda-2  reason: not valid java name */
    public static final ArrayList m198transcodeSubtitle$lambda3$lambda2(PreviewSubtitleDialog this$0, TranscodeResponse it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(Constant.DIR_TRANS_CODE_SUBTITLE + '/' + ((Object) URLDecoder.decode(it.getSrt_name(), "utf-8")));
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "file1.path");
        this$0.transcodeFilePath = path;
        if (file.exists()) {
            file.delete();
        }
        FileUtils.writeFileFromString(file, it.getSrt_content(), false);
        return new ArrayList(new FormatSRT().parseFile(this$0.getFileName(), new FileInputStream(file)).captions.values());
    }

    /* compiled from: PreviewSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JP\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/PreviewSubtitleDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/PreviewSubtitleDialog;", "fileName", "", "url", "lang", "language", "boxType", "", "id", "season", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PreviewSubtitleDialog newInstance(String str, String str2, String str3, String str4, int i, String str5, int i2, int i3) {
            PreviewSubtitleDialog previewSubtitleDialog = new PreviewSubtitleDialog();
            if (str2 == null) {
                str2 = "";
            }
            previewSubtitleDialog.setUrl(str2);
            if (str == null) {
                str = "";
            }
            previewSubtitleDialog.setFileName(str);
            previewSubtitleDialog.setSid(previewSubtitleDialog.getSid());
            previewSubtitleDialog.setLang(str3);
            previewSubtitleDialog.setBoxType(i);
            previewSubtitleDialog.setId(str5);
            previewSubtitleDialog.setSeason(i2);
            previewSubtitleDialog.setEpisode(i3);
            previewSubtitleDialog.setLanguage(str4);
            return previewSubtitleDialog;
        }
    }
}
