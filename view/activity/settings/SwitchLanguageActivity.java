package com.movieboxpro.android.view.activity.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.model.LanguageModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: SwitchLanguageActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0017R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/SwitchLanguageActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/LanguageModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "lastSelectedPos", "", "getLayoutResId", "initData", "", "initListener", "initView", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SwitchLanguageActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> adapter;
    private int lastSelectedPos;

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_switch_language;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Language");
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SwitchLanguageActivity$dXK2H2dAcOSt5Hs3U6iUN3fh3g0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SwitchLanguageActivity.m808initListener$lambda0(SwitchLanguageActivity.this, view);
            }
        });
        ((WaveSideBar) _$_findCachedViewById(R.id.sideBar)).setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SwitchLanguageActivity$XhG-KuQPp29GYbOrTMCLrPHsmaM
            @Override // com.gjiazhe.wavesidebar.WaveSideBar.OnSelectIndexItemListener
            public final void onSelectIndexItem(String str) {
                SwitchLanguageActivity.m809initListener$lambda2(SwitchLanguageActivity.this, str);
            }
        });
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SwitchLanguageActivity$1BYf8qTnfPV_3ptrkLtJ9qx-Hlk
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SwitchLanguageActivity.m810initListener$lambda3(SwitchLanguageActivity.this, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m808initListener$lambda0(SwitchLanguageActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m809initListener$lambda2(SwitchLanguageActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        int i = 0;
        for (Object obj : baseQuickAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String fullLanguage = ((LanguageModel) obj).getFullLanguage();
            Intrinsics.checkNotNullExpressionValue(fullLanguage, "languageModel.fullLanguage");
            String upperCase = String.valueOf(StringsKt.first(fullLanguage)).toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            if (Intrinsics.areEqual(upperCase, str)) {
                RecyclerView.LayoutManager layoutManager = ((RecyclerView) this$0._$_findCachedViewById(R.id.recyclerView)).getLayoutManager();
                if (layoutManager == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                }
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
                return;
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m810initListener$lambda3(SwitchLanguageActivity this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.lastSelectedPos == i) {
            return;
        }
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        List<LanguageModel> data = baseQuickAdapter.getData();
        data.get(this$0.lastSelectedPos).setSelect(false);
        data.get(i).setSelect(true);
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter3 = this$0.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.notifyItemChanged(this$0.lastSelectedPos);
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter4 = this$0.adapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        String language = data.get(i).getLanguage();
        PrefsUtils.getInstance().putString("language", language);
        App.deviceLang = language;
        this$0.lastSelectedPos = i;
        this$0.setResult(-1, new Intent());
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String[] stringArray = getResources().getStringArray(R.array.languages);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.languages)");
        final ArrayList arrayList = new ArrayList();
        int length = stringArray.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String str = stringArray[i];
            i++;
            int i3 = i2 + 1;
            Intrinsics.checkNotNullExpressionValue(str, "str");
            List split$default = StringsKt.split$default((CharSequence) str, new String[]{"->"}, false, 0, 6, (Object) null);
            if (split$default.size() == 2) {
                String str2 = (String) split$default.get(0);
                if (Intrinsics.areEqual(str2, App.deviceLang)) {
                    this.lastSelectedPos = i2;
                    arrayList.add(new LanguageModel(str2, (String) split$default.get(1), true));
                } else {
                    arrayList.add(new LanguageModel(str2, (String) split$default.get(1), false));
                }
            }
            i2 = i3;
        }
        this.adapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.activity.settings.SwitchLanguageActivity$initData$2
            final /* synthetic */ ArrayList<LanguageModel> $languages;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(R.layout.adapter_switch_language_item, arrayList);
                this.$languages = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, LanguageModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                holder.setText(R.id.tvLanguage, item.getFullLanguage());
                ImageView imageView = (ImageView) holder.getView(R.id.ivSelected);
                RelativeLayout relativeLayout = (RelativeLayout) holder.getView(R.id.relativeLayout);
                if (item.isSelect()) {
                    CommonExtKt.visible(imageView);
                    relativeLayout.setBackgroundColor(ContextCompat.getColor(App.getContext(), R.color.tag_color));
                    return;
                }
                CommonExtKt.gone(imageView);
                relativeLayout.setBackgroundColor(ContextCompat.getColor(App.getContext(), R.color.color_main));
            }
        };
        StickyDecoration build = StickyDecoration.Builder.init(new GroupListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$SwitchLanguageActivity$DJ0wgScsYl3xrObASBix7Deastg
            @Override // com.gavin.com.library.listener.GroupListener
            public final String getGroupName(int i4) {
                String m807initData$lambda5;
                m807initData$lambda5 = SwitchLanguageActivity.m807initData$lambda5(arrayList, i4);
                return m807initData$lambda5;
            }
        }).setGroupBackground(ContextCompat.getColor(App.getContext(), R.color.white_70alpha)).setGroupTextColor(ContextCompat.getColor(App.getContext(), R.color.black)).setDivideHeight(1).setGroupHeight(DensityUtils.dp2px(App.getContext(), 30.0f)).setDivideColor(ContextCompat.getColor(App.getContext(), R.color.white_70alpha)).setTextSideMargin(DensityUtils.dp2px(App.getContext(), 20.0f)).build();
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(this));
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).addItemDecoration(build);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<LanguageModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-5  reason: not valid java name */
    public static final String m807initData$lambda5(ArrayList languages, int i) {
        Intrinsics.checkNotNullParameter(languages, "$languages");
        if (CommonExtKt.checkIndexLegal(i, languages)) {
            String fullLanguage = ((LanguageModel) languages.get(i)).getFullLanguage();
            Intrinsics.checkNotNullExpressionValue(fullLanguage, "languages[position].fullLanguage");
            return String.valueOf(StringsKt.first(fullLanguage));
        }
        return null;
    }

    /* compiled from: SwitchLanguageActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/SwitchLanguageActivity$Companion;", "", "()V", TtmlNode.START, "", "activity", "Landroid/app/Activity;", "requestCode", "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, SwitchLanguageActivity.class));
        }

        public final void start(Activity activity, int i) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            activity.startActivityForResult(new Intent(activity, SwitchLanguageActivity.class), i);
        }
    }
}
