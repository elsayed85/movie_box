package com.movieboxpro.android.view.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.avery.subtitle.format.FormatSRT;
import com.avery.subtitle.model.Subtitle;
import com.dueeeke.model.SrtPraseModel;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.DialogPreviewLocalSubtitleBinding;
import com.movieboxpro.android.http.HttpUploadRequest;
import com.movieboxpro.android.model.TranscodeResponse;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.videocontroller.fragment.ChooseLanguageActivity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
/* compiled from: PreviewLocalSubtitleDialog.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 J2\u00020\u0001:\u0002JKB\u0005¢\u0006\u0002\u0010\u0002J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u000206H\u0016J\b\u00108\u001a\u000206H\u0016J\b\u00109\u001a\u00020:H\u0014J\"\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020\t2\u0006\u0010=\u001a\u00020\t2\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J$\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010E2\b\u0010F\u001a\u0004\u0018\u00010GH\u0016J\u0016\u0010H\u001a\u0002062\u0006\u0010,\u001a\u00020-2\u0006\u00102\u001a\u000203J\b\u0010I\u001a\u000206H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R+\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR+\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000eR+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR+\u0010\u001c\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0010\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR/\u0010 \u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b#\u0010\u0010\u001a\u0004\b!\u0010\u0018\"\u0004\b\"\u0010\u001aR/\u0010$\u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010\u0010\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR/\u0010(\u001a\u0004\u0018\u00010\u00152\b\u0010\b\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b+\u0010\u0010\u001a\u0004\b)\u0010\u0018\"\u0004\b*\u0010\u001aR\u0010\u0010,\u001a\u0004\u0018\u00010-X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010.\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010\u0010\u001a\u0004\b/\u0010\f\"\u0004\b0\u0010\u000eR\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PreviewLocalSubtitleDialog;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/avery/subtitle/model/Subtitle;", "binding", "Lcom/movieboxpro/android/databinding/DialogPreviewLocalSubtitleBinding;", "<set-?>", "", "boxType", "getBoxType", "()I", "setBoxType", "(I)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "episode", "getEpisode", "setEpisode", "episode$delegate", "", "fileName", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "fileName$delegate", "filePath", "getFilePath", "setFilePath", "filePath$delegate", "id", "getId", "setId", "id$delegate", "lang", "getLang", "setLang", "lang$delegate", "language", "getLanguage", "setLanguage", "language$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "season", "getSeason", "setSeason", "season$delegate", "selectListener", "Lcom/movieboxpro/android/view/dialog/PreviewLocalSubtitleDialog$OnSelectLocalSubtitleListener;", "transcodeFilePath", "initData", "", "initListener", "initView", "needFullscreen", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setListener", "transcodeSubtitle", "Companion", "OnSelectLocalSubtitleListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PreviewLocalSubtitleDialog extends BaseBindingBottomDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "filePath", "getFilePath()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "fileName", "getFileName()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "boxType", "getBoxType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "season", "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "episode", "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "lang", "getLang()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "language", "getLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(PreviewLocalSubtitleDialog.class, "id", "getId()Ljava/lang/String;", 0))};
    public static final Companion Companion = new Companion(null);
    private CommBaseAdapter<Subtitle> adapter;
    private DialogPreviewLocalSubtitleBinding binding;
    private final ReadWriteProperty boxType$delegate;
    private final ReadWriteProperty episode$delegate;
    private final ReadWriteProperty fileName$delegate;
    private final ReadWriteProperty filePath$delegate;
    private final ReadWriteProperty id$delegate;
    private final ReadWriteProperty lang$delegate;
    private final ReadWriteProperty language$delegate;
    private DialogAction.ActionListener listener;
    private final ReadWriteProperty season$delegate;
    private OnSelectLocalSubtitleListener selectListener;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String transcodeFilePath = "";

    /* compiled from: PreviewLocalSubtitleDialog.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001JB\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH&¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PreviewLocalSubtitleDialog$OnSelectLocalSubtitleListener;", "", "onSelectLocalSubtitle", "", "subtitleSrtModel", "Ljava/util/ArrayList;", "Lcom/dueeeke/model/SrtPraseModel;", "Lkotlin/collections/ArrayList;", "subtitles", "", "Lcom/avery/subtitle/model/Subtitle;", "lang", "", FileDownloadService.PARAMS_KEY_PATH, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnSelectLocalSubtitleListener {
        void onSelectLocalSubtitle(ArrayList<SrtPraseModel> arrayList, List<Subtitle> list, String str, String str2);
    }

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

    public PreviewLocalSubtitleDialog() {
        PreviewLocalSubtitleDialog previewLocalSubtitleDialog = this;
        this.filePath$delegate = FragmentArgsKt.arg(previewLocalSubtitleDialog);
        this.fileName$delegate = FragmentArgsKt.arg(previewLocalSubtitleDialog);
        this.boxType$delegate = FragmentArgsKt.arg(previewLocalSubtitleDialog);
        this.season$delegate = FragmentArgsKt.arg(previewLocalSubtitleDialog);
        this.episode$delegate = FragmentArgsKt.arg(previewLocalSubtitleDialog);
        this.lang$delegate = FragmentArgsKt.argOrNull(previewLocalSubtitleDialog);
        this.language$delegate = FragmentArgsKt.argOrNull(previewLocalSubtitleDialog);
        this.id$delegate = FragmentArgsKt.argOrNull(previewLocalSubtitleDialog);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFilePath() {
        return (String) this.filePath$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFilePath(String str) {
        this.filePath$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    private final String getFileName() {
        return (String) this.fileName$delegate.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFileName(String str) {
        this.fileName$delegate.setValue(this, $$delegatedProperties[1], str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getBoxType() {
        return ((Number) this.boxType$delegate.getValue(this, $$delegatedProperties[2])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setBoxType(int i) {
        this.boxType$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSeason() {
        return ((Number) this.season$delegate.getValue(this, $$delegatedProperties[3])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSeason(int i) {
        this.season$delegate.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getEpisode() {
        return ((Number) this.episode$delegate.getValue(this, $$delegatedProperties[4])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEpisode(int i) {
        this.episode$delegate.setValue(this, $$delegatedProperties[4], Integer.valueOf(i));
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
    public final String getId() {
        return (String) this.id$delegate.getValue(this, $$delegatedProperties[7]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id$delegate.setValue(this, $$delegatedProperties[7], str);
    }

    public final void setListener(DialogAction.ActionListener listener, OnSelectLocalSubtitleListener selectListener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(selectListener, "selectListener");
        this.listener = listener;
        this.selectListener = selectListener;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.dialog_preview_local_subtitle, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…btitle, container, false)");
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding = (DialogPreviewLocalSubtitleBinding) inflate;
        this.binding = dialogPreviewLocalSubtitleBinding;
        if (dialogPreviewLocalSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding = null;
        }
        View root = dialogPreviewLocalSubtitleBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding = this.binding;
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding2 = null;
        if (dialogPreviewLocalSubtitleBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding = null;
        }
        dialogPreviewLocalSubtitleBinding.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$PreviewLocalSubtitleDialog$FjxUdh45542ZGihJkWlD2_1Gxm0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewLocalSubtitleDialog.m1050initListener$lambda0(PreviewLocalSubtitleDialog.this, view);
            }
        });
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding3 = this.binding;
        if (dialogPreviewLocalSubtitleBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogPreviewLocalSubtitleBinding3 = null;
        }
        dialogPreviewLocalSubtitleBinding3.llChooseLanguage.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$PreviewLocalSubtitleDialog$oWWx_qP2MZcxdlt_Ku-m3NIYo-c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewLocalSubtitleDialog.m1051initListener$lambda1(PreviewLocalSubtitleDialog.this, view);
            }
        });
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding4 = this.binding;
        if (dialogPreviewLocalSubtitleBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            dialogPreviewLocalSubtitleBinding2 = dialogPreviewLocalSubtitleBinding4;
        }
        dialogPreviewLocalSubtitleBinding2.tvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$PreviewLocalSubtitleDialog$JRuqrZE7pmbH_vUQ5WL5a7uLiu4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PreviewLocalSubtitleDialog.m1052initListener$lambda3(PreviewLocalSubtitleDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1050initListener$lambda0(PreviewLocalSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1051initListener$lambda1(PreviewLocalSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChooseLanguageActivity.Companion companion = ChooseLanguageActivity.Companion;
        PreviewLocalSubtitleDialog previewLocalSubtitleDialog = this$0;
        String lang = this$0.getLang();
        if (lang == null) {
            lang = "";
        }
        companion.start(previewLocalSubtitleDialog, lang, 100);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m1052initListener$lambda3(PreviewLocalSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList<SrtPraseModel> arrayList = new ArrayList<>();
        CommBaseAdapter<Subtitle> commBaseAdapter = this$0.adapter;
        CommBaseAdapter<Subtitle> commBaseAdapter2 = null;
        if (commBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commBaseAdapter = null;
        }
        for (Subtitle subtitle : commBaseAdapter.getData()) {
            SrtPraseModel srtPraseModel = new SrtPraseModel();
            srtPraseModel.setSrtBody(subtitle.content);
            srtPraseModel.setBeginTime(subtitle.getStart().mseconds);
            srtPraseModel.setEndTime(subtitle.getEnd().mseconds);
            arrayList.add(srtPraseModel);
        }
        OnSelectLocalSubtitleListener onSelectLocalSubtitleListener = this$0.selectListener;
        if (onSelectLocalSubtitleListener != null) {
            CommBaseAdapter<Subtitle> commBaseAdapter3 = this$0.adapter;
            if (commBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commBaseAdapter2 = commBaseAdapter3;
            }
            onSelectLocalSubtitleListener.onSelectLocalSubtitle(arrayList, commBaseAdapter2.getData(), this$0.getLang(), this$0.transcodeFilePath);
        }
        this$0.dismissAllowingStateLoss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1) {
            DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding = null;
            String stringExtra = intent == null ? null : intent.getStringExtra("language");
            setLang(stringExtra);
            String lang = getLang();
            if (lang == null) {
                lang = "";
            }
            Locale locale = new Locale(lang);
            if (Intrinsics.areEqual(stringExtra, "zh-TW")) {
                DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding2 = this.binding;
                if (dialogPreviewLocalSubtitleBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogPreviewLocalSubtitleBinding = dialogPreviewLocalSubtitleBinding2;
                }
                dialogPreviewLocalSubtitleBinding.tvLanguage.setText("Chinese(Traditional)");
            } else if (Intrinsics.areEqual(stringExtra, "zh-CN")) {
                DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding3 = this.binding;
                if (dialogPreviewLocalSubtitleBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogPreviewLocalSubtitleBinding = dialogPreviewLocalSubtitleBinding3;
                }
                dialogPreviewLocalSubtitleBinding.tvLanguage.setText("Chinese(Simplified)");
            } else {
                DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding4 = this.binding;
                if (dialogPreviewLocalSubtitleBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    dialogPreviewLocalSubtitleBinding = dialogPreviewLocalSubtitleBinding4;
                }
                dialogPreviewLocalSubtitleBinding.tvLanguage.setText(locale.getDisplayLanguage(Locale.ENGLISH));
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
        transcodeSubtitle();
        setLang(App.deviceLang);
        DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding = null;
        if (Intrinsics.areEqual(App.deviceLang, "zh-CN")) {
            DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding2 = this.binding;
            if (dialogPreviewLocalSubtitleBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogPreviewLocalSubtitleBinding = dialogPreviewLocalSubtitleBinding2;
            }
            dialogPreviewLocalSubtitleBinding.tvLanguage.setText("Chinese(Simplified)");
        } else if (Intrinsics.areEqual(App.deviceLang, "zh-TW")) {
            DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding3 = this.binding;
            if (dialogPreviewLocalSubtitleBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogPreviewLocalSubtitleBinding = dialogPreviewLocalSubtitleBinding3;
            }
            dialogPreviewLocalSubtitleBinding.tvLanguage.setText("Chinese(Traditional)");
        } else {
            Locale locale = new Locale(App.deviceLang);
            DialogPreviewLocalSubtitleBinding dialogPreviewLocalSubtitleBinding4 = this.binding;
            if (dialogPreviewLocalSubtitleBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                dialogPreviewLocalSubtitleBinding = dialogPreviewLocalSubtitleBinding4;
            }
            dialogPreviewLocalSubtitleBinding.tvLanguage.setText(locale.getDisplayLanguage(Locale.ENGLISH));
        }
    }

    private final void transcodeSubtitle() {
        Object as = Observable.just(getFilePath()).flatMap(new Function() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$PreviewLocalSubtitleDialog$GeJkJvkXrsfA8cnGApPfOWENQKU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ObservableSource m1055transcodeSubtitle$lambda5;
                m1055transcodeSubtitle$lambda5 = PreviewLocalSubtitleDialog.m1055transcodeSubtitle$lambda5(PreviewLocalSubtitleDialog.this, (String) obj);
                return m1055transcodeSubtitle$lambda5;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(as, "just(filePath)\n         …bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new PreviewLocalSubtitleDialog$transcodeSubtitle$2(this), null, new PreviewLocalSubtitleDialog$transcodeSubtitle$3(this), null, new PreviewLocalSubtitleDialog$transcodeSubtitle$4(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: transcodeSubtitle$lambda-5  reason: not valid java name */
    public static final ObservableSource m1055transcodeSubtitle$lambda5(final PreviewLocalSubtitleDialog this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        final File file = new File(this$0.getFilePath());
        return new HttpUploadRequest(null, 1, null).addBaseParams("Srt_convert_encoding", "text/plain", file, "zip_file").addParam("encoding", "UTF-8").asRequest().compose(RxUtils.rxTranslate2Bean(TranscodeResponse.class)).map(new Function() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$PreviewLocalSubtitleDialog$FVZy-SdRJoh54uL2xJSi6ti-FMU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                ArrayList m1056transcodeSubtitle$lambda5$lambda4;
                m1056transcodeSubtitle$lambda5$lambda4 = PreviewLocalSubtitleDialog.m1056transcodeSubtitle$lambda5$lambda4(PreviewLocalSubtitleDialog.this, file, (TranscodeResponse) obj);
                return m1056transcodeSubtitle$lambda5$lambda4;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: transcodeSubtitle$lambda-5$lambda-4  reason: not valid java name */
    public static final ArrayList m1056transcodeSubtitle$lambda5$lambda4(PreviewLocalSubtitleDialog this$0, File file, TranscodeResponse it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(file, "$file");
        Intrinsics.checkNotNullParameter(it, "it");
        File file2 = new File(Constant.DIR_TRANS_CODE_SUBTITLE + '/' + ((Object) URLDecoder.decode(it.getSrt_name(), "utf-8")));
        String path = file2.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "file1.path");
        this$0.transcodeFilePath = path;
        if (file2.exists()) {
            file2.delete();
        }
        FileUtils.writeFileFromString(file2, it.getSrt_content(), false);
        return new ArrayList(new FormatSRT().parseFile(file.getName(), new FileInputStream(file2)).captions.values());
    }

    /* compiled from: PreviewLocalSubtitleDialog.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/PreviewLocalSubtitleDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/PreviewLocalSubtitleDialog;", "fileName", "", "filePath", "boxType", "", "id", "season", "episode", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PreviewLocalSubtitleDialog newInstance(String str, String str2, int i, String str3, int i2, int i3) {
            PreviewLocalSubtitleDialog previewLocalSubtitleDialog = new PreviewLocalSubtitleDialog();
            if (str2 == null) {
                str2 = "";
            }
            previewLocalSubtitleDialog.setFilePath(str2);
            if (str == null) {
                str = "";
            }
            previewLocalSubtitleDialog.setFileName(str);
            previewLocalSubtitleDialog.setBoxType(i);
            previewLocalSubtitleDialog.setId(str3);
            previewLocalSubtitleDialog.setSeason(i2);
            previewLocalSubtitleDialog.setEpisode(i3);
            return previewLocalSubtitleDialog;
        }
    }
}
