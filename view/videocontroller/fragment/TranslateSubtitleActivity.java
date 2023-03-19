package com.movieboxpro.android.view.videocontroller.fragment;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.avery.subtitle.model.Subtitle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.SrtPraseModel;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.TransformSubtitleDataEvent;
import com.movieboxpro.android.event.TranslateSubtitleEvent;
import com.movieboxpro.android.utils.LinearItemDecoration;
import com.movieboxpro.android.view.dialog.TranslateProgressDialog;
import com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* compiled from: TranslateSubtitleActivity.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0006\u0018\u0000 22\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00012B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0018\u001a\u00020\u0002H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\fH\u0014J\u0018\u0010\u001c\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0002J\b\u0010\u001d\u001a\u00020\u000eH\u0014J\b\u0010\u001e\u001a\u00020\u001aH\u0014J\b\u0010\u001f\u001a\u00020\u001aH\u0014J\b\u0010 \u001a\u00020\u001aH\u0014J\b\u0010!\u001a\u00020\fH\u0014J\"\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020)H\u0007J\b\u0010*\u001a\u00020\u001aH\u0014J\u0016\u0010+\u001a\u00020\u001a2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00070-H\u0017J\u0018\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u000eH\u0016J\u0016\u00101\u001a\u00020\u001a2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00070-H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitlePresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleContract$View;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/dueeeke/model/SrtPraseModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "fromLanguage", "", "haveTanslate", "", "languageFromRequestCode", "", "languageRequestCode", "progressDialog", "Lcom/movieboxpro/android/view/dialog/TranslateProgressDialog;", MediaTrack.ROLE_SUBTITLE, "subtitles", "Ljava/util/ArrayList;", "Lcom/avery/subtitle/model/Subtitle;", "Lkotlin/collections/ArrayList;", "toLanguage", "bindPresenter", "dismissProgress", "", "enableEventBus", "equalsLanguages", "getLayoutResId", "initData", "initListener", "initView", "isNeedLoadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onSubtitleData", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/TransformSubtitleDataEvent;", "requestData", "showContent", "list", "", "showProgress", "progress", "max", "showSubtitles", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TranslateSubtitleActivity extends BaseMvpActivity<TranslateSubtitlePresenter> implements TranslateSubtitleContract.View {
    public static final Companion Companion = new Companion(null);
    private BaseQuickAdapter<SrtPraseModel, BaseViewHolder> adapter;
    private boolean haveTanslate;
    private TranslateProgressDialog progressDialog;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final int languageRequestCode = 1;
    private final int languageFromRequestCode = 2;
    private String toLanguage = "";
    private String subtitle = "";
    private ArrayList<Subtitle> subtitles = new ArrayList<>();
    private String fromLanguage = "";

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.fragment_translate_subtitle;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitleActivity$oMO4pIpsfUZnESLhASn65tksRUA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.m1387initListener$lambda0(TranslateSubtitleActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Translate");
        ((Button) _$_findCachedViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitleActivity$ybNRyTwQjyH45LLtg1O5i7knTbs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.m1388initListener$lambda1(TranslateSubtitleActivity.this, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitleActivity$iO4Ev2TtTfvd7v2apB1m-ZfTw5U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.m1389initListener$lambda2(TranslateSubtitleActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvToLanguage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitleActivity$pD9UmVqnsjFbA8VROJAPlvXLURk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.m1390initListener$lambda3(TranslateSubtitleActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$TranslateSubtitleActivity$1jsERJRJNn-0rTZ-a9O8oWMh3Wg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TranslateSubtitleActivity.m1391initListener$lambda4(TranslateSubtitleActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1387initListener$lambda0(TranslateSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1388initListener$lambda1(TranslateSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.haveTanslate) {
            EventBus eventBus = EventBus.getDefault();
            BaseQuickAdapter<SrtPraseModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            eventBus.post(new TranslateSubtitleEvent(baseQuickAdapter.getData()));
            this$0.finish();
            return;
        }
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1389initListener$lambda2(TranslateSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m1390initListener$lambda3(TranslateSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChooseLanguageActivity.Companion.start(this$0, ((TextView) this$0._$_findCachedViewById(R.id.tvFromLanguage)).getText().toString(), this$0.languageRequestCode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-4  reason: not valid java name */
    public static final void m1391initListener$lambda4(TranslateSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChooseLanguageActivity.Companion.start(this$0, ((TextView) this$0._$_findCachedViewById(R.id.tvToLanguage)).getText().toString(), this$0.languageFromRequestCode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        boolean z = false;
        if (i != this.languageRequestCode) {
            if (i == this.languageFromRequestCode && i2 == -1) {
                String stringExtra = intent != null ? intent.getStringExtra("language") : null;
                if ((stringExtra == null || stringExtra.length() == 0) ? true : true) {
                    return;
                }
                ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).setText(stringExtra);
                if (Intrinsics.areEqual(((TextView) _$_findCachedViewById(R.id.tvToLanguage)).getText().toString(), "Choose")) {
                    return;
                }
                ((TranslateSubtitlePresenter) this.mPresenter).startTranslate(this.subtitles, ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).getText().toString(), this.toLanguage);
            }
        } else if (i2 == -1) {
            String stringExtra2 = intent != null ? intent.getStringExtra("language") : null;
            String str = stringExtra2;
            if ((str == null || str.length() == 0) ? true : true) {
                return;
            }
            ((TextView) _$_findCachedViewById(R.id.tvToLanguage)).setText(str);
            this.toLanguage = stringExtra2;
            if (Intrinsics.areEqual(((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).getText().toString(), "Choose")) {
                return;
            }
            ((TranslateSubtitlePresenter) this.mPresenter).startTranslate(this.subtitles, ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).getText().toString(), this.toLanguage);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.adapter = new BaseQuickAdapter<SrtPraseModel, BaseViewHolder>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleActivity$initData$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, SrtPraseModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                holder.setText(R.id.textView, Html.fromHtml(item.getSrtBody()));
            }
        };
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).addItemDecoration(new LinearItemDecoration(5, true));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<SrtPraseModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.View
    public void showContent(List<SrtPraseModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        BaseQuickAdapter<SrtPraseModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(list);
        String stringExtra = getIntent().getStringExtra("from");
        if (stringExtra == null) {
            stringExtra = "";
        }
        String str = stringExtra;
        ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).setText(str);
        String deviceLang = App.deviceLang;
        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
        if (!equalsLanguages(stringExtra, deviceLang)) {
            if (str.length() > 0) {
                ((TextView) _$_findCachedViewById(R.id.tvToLanguage)).setText(App.deviceLang);
                ((TranslateSubtitlePresenter) this.mPresenter).startTranslate(this.subtitles, ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).getText().toString(), ((TextView) _$_findCachedViewById(R.id.tvToLanguage)).getText().toString());
                return;
            }
            ((TextView) _$_findCachedViewById(R.id.tvFromLanguage)).setText("Choose");
            ((TextView) _$_findCachedViewById(R.id.tvToLanguage)).setText(App.deviceLang);
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tvToLanguage)).setText("Choose");
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.View
    public void showProgress(int i, int i2) {
        if (i == 0) {
            TranslateProgressDialog translateProgressDialog = new TranslateProgressDialog(this, i2);
            this.progressDialog = translateProgressDialog;
            if (translateProgressDialog == null) {
                return;
            }
            translateProgressDialog.show();
            return;
        }
        TranslateProgressDialog translateProgressDialog2 = this.progressDialog;
        if (translateProgressDialog2 == null) {
            return;
        }
        translateProgressDialog2.updateProgress(i, i2);
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.View
    public void dismissProgress() {
        TranslateProgressDialog translateProgressDialog = this.progressDialog;
        if (translateProgressDialog == null) {
            return;
        }
        translateProgressDialog.dismiss();
    }

    private final boolean equalsLanguages(String str, String str2) {
        if (StringsKt.equals(str, "zh", true)) {
            return StringsKt.equals(str2, "zh-CN", true);
        }
        return Intrinsics.areEqual(str, str2);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onSubtitleData(TransformSubtitleDataEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String content = event.getContent();
        Intrinsics.checkNotNullExpressionValue(content, "event.content");
        this.subtitle = content;
        this.subtitles = new ArrayList<>(event.getSubtitles());
        ((TranslateSubtitlePresenter) this.mPresenter).parseContent(this.subtitle);
    }

    @Override // com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleContract.View
    public void showSubtitles(List<SrtPraseModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        BaseQuickAdapter<SrtPraseModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(list);
        this.haveTanslate = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public TranslateSubtitlePresenter bindPresenter() {
        return new TranslateSubtitlePresenter(this);
    }

    /* compiled from: TranslateSubtitleActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/TranslateSubtitleActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", FirebaseAnalytics.Param.CONTENT, "", "fromLanguage", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str, String str2) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, TranslateSubtitleActivity.class);
            intent.putExtra("data", str);
            intent.putExtra("from", str2);
            context.startActivity(intent);
        }
    }
}
