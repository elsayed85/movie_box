package com.movieboxpro.android.view.dialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.SkipTimeAdapter;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingBottomDialogFragment;
import com.movieboxpro.android.base.BaseBindingFragment;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.databinding.AdapterMoreImageBinding;
import com.movieboxpro.android.databinding.FragmentSkipTimeBinding;
import com.movieboxpro.android.databinding.FragmentSkipTimeSettingBinding;
import com.movieboxpro.android.event.UpdateSkipEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.SkipEnd;
import com.movieboxpro.android.model.SkipStart;
import com.movieboxpro.android.model.SkipTimeItem;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.model.VideoThumb;
import com.movieboxpro.android.model.VideoThumbResponse;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.databinding.FragmentBindingDelegate;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.SkipTimeFragment;
import com.movieboxpro.android.view.widget.GalleryItemDecoration;
import com.movieboxpro.android.view.widget.NoScrollViewPager;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: SkipTimeFragment.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010,\u001a\u00020-H\u0016J\b\u0010.\u001a\u00020-H\u0016J\b\u0010/\u001a\u00020-H\u0016J\u0010\u00100\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0016J$\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R/\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\f\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR/\u0010\u001c\u001a\u0004\u0018\u00010\u00152\b\u0010\f\u001a\u0004\u0018\u00010\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001d\u0010\u0018\"\u0004\b\u001e\u0010\u001aR\u000e\u0010 \u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010!\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010\u0014\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R+\u0010%\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010\u0014\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/movieboxpro/android/view/dialog/SkipTimeFragment;", "Lcom/movieboxpro/android/base/BaseBindingBottomDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/TabLayoutPagerAdapter;", "binding", "Lcom/movieboxpro/android/databinding/FragmentSkipTimeBinding;", "endFragment", "Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$SkipTimeSettingFragment;", "endSelected", "", "endingLoaded", "<set-?>", "", "episode", "getEpisode", "()I", "setEpisode", "(I)V", "episode$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "fid", "getFid", "()Ljava/lang/String;", "setFid", "(Ljava/lang/String;)V", "fid$delegate", "id", "getId", "setId", "id$delegate", "openingLoaded", "season", "getSeason", "setSeason", "season$delegate", "showEnd", "getShowEnd", "()Z", "setShowEnd", "(Z)V", "showEnd$delegate", "startFragment", "initData", "", "initListener", "initView", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "SkipTimeSettingFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkipTimeFragment extends BaseBindingBottomDialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeFragment.class, "id", "getId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeFragment.class, "fid", "getFid()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeFragment.class, "season", "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeFragment.class, "episode", "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeFragment.class, "showEnd", "getShowEnd()Z", 0))};
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private TabLayoutPagerAdapter adapter;
    private FragmentSkipTimeBinding binding;
    private SkipTimeSettingFragment endFragment;
    private boolean endSelected;
    private boolean endingLoaded;
    private final ReadWriteProperty episode$delegate;
    private final ReadWriteProperty fid$delegate;
    private final ReadWriteProperty id$delegate;
    private boolean openingLoaded;
    private final ReadWriteProperty season$delegate;
    private final ReadWriteProperty showEnd$delegate;
    private SkipTimeSettingFragment startFragment;

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

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public SkipTimeFragment() {
        SkipTimeFragment skipTimeFragment = this;
        this.id$delegate = FragmentArgsKt.argOrNull(skipTimeFragment);
        this.fid$delegate = FragmentArgsKt.argOrNull(skipTimeFragment);
        this.season$delegate = FragmentArgsKt.arg(skipTimeFragment);
        this.episode$delegate = FragmentArgsKt.arg(skipTimeFragment);
        this.showEnd$delegate = FragmentArgsKt.arg(skipTimeFragment);
    }

    private final String getId() {
        return (String) this.id$delegate.getValue(this, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setId(String str) {
        this.id$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    private final String getFid() {
        return (String) this.fid$delegate.getValue(this, $$delegatedProperties[1]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setFid(String str) {
        this.fid$delegate.setValue(this, $$delegatedProperties[1], str);
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

    private final boolean getShowEnd() {
        return ((Boolean) this.showEnd$delegate.getValue(this, $$delegatedProperties[4])).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setShowEnd(boolean z) {
        this.showEnd$delegate.setValue(this, $$delegatedProperties[4], Boolean.valueOf(z));
    }

    /* compiled from: SkipTimeFragment.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J6\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/SkipTimeFragment;", "id", "", "fid", "season", "", "episode", "showEnd", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SkipTimeFragment newInstance(String str, String str2, int i, int i2) {
            return newInstance$default(this, str, str2, i, i2, false, 16, null);
        }

        private Companion() {
        }

        public static /* synthetic */ SkipTimeFragment newInstance$default(Companion companion, String str, String str2, int i, int i2, boolean z, int i3, Object obj) {
            return companion.newInstance(str, str2, i, i2, (i3 & 16) != 0 ? false : z);
        }

        public final SkipTimeFragment newInstance(String str, String str2, int i, int i2, boolean z) {
            SkipTimeFragment skipTimeFragment = new SkipTimeFragment();
            skipTimeFragment.setId(str);
            skipTimeFragment.setFid(str2);
            skipTimeFragment.setSeason(i);
            skipTimeFragment.setEpisode(i2);
            skipTimeFragment.setShowEnd(z);
            return skipTimeFragment;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_skip_time, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater, R.layo…p_time, container, false)");
        FragmentSkipTimeBinding fragmentSkipTimeBinding = (FragmentSkipTimeBinding) inflate;
        this.binding = fragmentSkipTimeBinding;
        if (fragmentSkipTimeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding = null;
        }
        View root = fragmentSkipTimeBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initListener() {
        FragmentSkipTimeBinding fragmentSkipTimeBinding = this.binding;
        FragmentSkipTimeBinding fragmentSkipTimeBinding2 = null;
        if (fragmentSkipTimeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding = null;
        }
        fragmentSkipTimeBinding.llEnableSkip.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$A_mOiqSw0SLO_NgwRVHxy_LyCOI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SkipTimeFragment.m1113initListener$lambda0(SkipTimeFragment.this, view);
            }
        });
        FragmentSkipTimeBinding fragmentSkipTimeBinding3 = this.binding;
        if (fragmentSkipTimeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding3 = null;
        }
        fragmentSkipTimeBinding3.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$M21xq_NqOgZkLZhtpPCeJN1Sq3E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SkipTimeFragment.m1114initListener$lambda1(SkipTimeFragment.this, view);
            }
        });
        FragmentSkipTimeBinding fragmentSkipTimeBinding4 = this.binding;
        if (fragmentSkipTimeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding4 = null;
        }
        fragmentSkipTimeBinding4.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.dialog.SkipTimeFragment$initListener$3
            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                if (tab.getPosition() == 1) {
                    SkipTimeFragment.this.endSelected = true;
                    SkipTimeFragment.this.endingLoaded = true;
                } else if (tab.getPosition() == 0) {
                    SkipTimeFragment.this.openingLoaded = true;
                }
            }
        });
        FragmentSkipTimeBinding fragmentSkipTimeBinding5 = this.binding;
        if (fragmentSkipTimeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentSkipTimeBinding2 = fragmentSkipTimeBinding5;
        }
        fragmentSkipTimeBinding2.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$Fw9P814X1BVJilQsFAhXKJVKuYc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SkipTimeFragment.m1115initListener$lambda3(SkipTimeFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m1113initListener$lambda0(SkipTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentSkipTimeBinding fragmentSkipTimeBinding = this$0.binding;
        FragmentSkipTimeBinding fragmentSkipTimeBinding2 = null;
        if (fragmentSkipTimeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding = null;
        }
        AppCompatImageView appCompatImageView = fragmentSkipTimeBinding.ivCheck;
        FragmentSkipTimeBinding fragmentSkipTimeBinding3 = this$0.binding;
        if (fragmentSkipTimeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding3 = null;
        }
        appCompatImageView.setSelected(!fragmentSkipTimeBinding3.ivCheck.isSelected());
        FragmentSkipTimeBinding fragmentSkipTimeBinding4 = this$0.binding;
        if (fragmentSkipTimeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding4 = null;
        }
        if (fragmentSkipTimeBinding4.ivCheck.isSelected()) {
            FragmentSkipTimeBinding fragmentSkipTimeBinding5 = this$0.binding;
            if (fragmentSkipTimeBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding5 = null;
            }
            fragmentSkipTimeBinding5.ivCheck.setImageResource(R.mipmap.ic_blue_checked);
        } else {
            FragmentSkipTimeBinding fragmentSkipTimeBinding6 = this$0.binding;
            if (fragmentSkipTimeBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding6 = null;
            }
            fragmentSkipTimeBinding6.ivCheck.setImageResource(R.mipmap.ic_blue_check);
        }
        PrefsUtils prefsUtils = PrefsUtils.getInstance();
        FragmentSkipTimeBinding fragmentSkipTimeBinding7 = this$0.binding;
        if (fragmentSkipTimeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding7 = null;
        }
        prefsUtils.putBoolean(Constant.Prefs.SKIP_OPENING_ENDING, fragmentSkipTimeBinding7.ivCheck.isSelected());
        FragmentSkipTimeBinding fragmentSkipTimeBinding8 = this$0.binding;
        if (fragmentSkipTimeBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding8 = null;
        }
        if (fragmentSkipTimeBinding8.ivCheck.isSelected()) {
            FragmentSkipTimeBinding fragmentSkipTimeBinding9 = this$0.binding;
            if (fragmentSkipTimeBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding9 = null;
            }
            fragmentSkipTimeBinding9.tvOk.setEnabled(true);
            FragmentSkipTimeBinding fragmentSkipTimeBinding10 = this$0.binding;
            if (fragmentSkipTimeBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding10 = null;
            }
            fragmentSkipTimeBinding10.tvOk.setAlpha(1.0f);
            FragmentSkipTimeBinding fragmentSkipTimeBinding11 = this$0.binding;
            if (fragmentSkipTimeBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding11 = null;
            }
            TextView textView = fragmentSkipTimeBinding11.tvDisableHint;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDisableHint");
            CommonExtKt.gone(textView);
        } else {
            FragmentSkipTimeBinding fragmentSkipTimeBinding12 = this$0.binding;
            if (fragmentSkipTimeBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding12 = null;
            }
            TextView textView2 = fragmentSkipTimeBinding12.tvDisableHint;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvDisableHint");
            CommonExtKt.visible(textView2);
            FragmentSkipTimeBinding fragmentSkipTimeBinding13 = this$0.binding;
            if (fragmentSkipTimeBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding13 = null;
            }
            fragmentSkipTimeBinding13.tvOk.setEnabled(false);
            FragmentSkipTimeBinding fragmentSkipTimeBinding14 = this$0.binding;
            if (fragmentSkipTimeBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding14 = null;
            }
            fragmentSkipTimeBinding14.tvOk.setAlpha(0.2f);
        }
        if (this$0.openingLoaded) {
            SkipTimeSettingFragment skipTimeSettingFragment = this$0.startFragment;
            if (skipTimeSettingFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("startFragment");
                skipTimeSettingFragment = null;
            }
            FragmentSkipTimeBinding fragmentSkipTimeBinding15 = this$0.binding;
            if (fragmentSkipTimeBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding15 = null;
            }
            skipTimeSettingFragment.setEnable(fragmentSkipTimeBinding15.ivCheck.isSelected());
        }
        if (this$0.endingLoaded) {
            SkipTimeSettingFragment skipTimeSettingFragment2 = this$0.endFragment;
            if (skipTimeSettingFragment2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("endFragment");
                skipTimeSettingFragment2 = null;
            }
            FragmentSkipTimeBinding fragmentSkipTimeBinding16 = this$0.binding;
            if (fragmentSkipTimeBinding16 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentSkipTimeBinding2 = fragmentSkipTimeBinding16;
            }
            skipTimeSettingFragment2.setEnable(fragmentSkipTimeBinding2.ivCheck.isSelected());
        }
        EventBus.getDefault().post(new UpdateSkipEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1114initListener$lambda1(SkipTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m1115initListener$lambda3(SkipTimeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ArrayList arrayList = new ArrayList();
        if (this$0.openingLoaded) {
            SkipTimeSettingFragment skipTimeSettingFragment = this$0.startFragment;
            if (skipTimeSettingFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("startFragment");
                skipTimeSettingFragment = null;
            }
            if (skipTimeSettingFragment.isScrolled()) {
                SkipTimeSettingFragment skipTimeSettingFragment2 = this$0.startFragment;
                if (skipTimeSettingFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("startFragment");
                    skipTimeSettingFragment2 = null;
                }
                int selectTime = skipTimeSettingFragment2.getSelectTime();
                Api api = Api.INSTANCE;
                String id = this$0.getId();
                int season = this$0.getSeason();
                SkipTimeSettingFragment skipTimeSettingFragment3 = this$0.startFragment;
                if (skipTimeSettingFragment3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("startFragment");
                    skipTimeSettingFragment3 = null;
                }
                arrayList.add(RxSubscribersKt.toMsg(CommonExtKt.request$default(api.saveSkipTime(id, season, skipTimeSettingFragment3.isCheckSeason() ? 0 : this$0.getEpisode(), "add", selectTime, -1), null, 1, null)).subscribeOn(Schedulers.io()));
                arrayList.add(RxSubscribersKt.toMsg(CommonExtKt.request$default(Api.INSTANCE.voteSkipTime(this$0.getId(), this$0.getSeason(), this$0.getEpisode(), selectTime, -1), null, 1, null)).subscribeOn(Schedulers.io()));
            }
        }
        if (this$0.endingLoaded) {
            SkipTimeSettingFragment skipTimeSettingFragment4 = this$0.endFragment;
            if (skipTimeSettingFragment4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("endFragment");
                skipTimeSettingFragment4 = null;
            }
            if (skipTimeSettingFragment4.isScrolled()) {
                SkipTimeSettingFragment skipTimeSettingFragment5 = this$0.endFragment;
                if (skipTimeSettingFragment5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("endFragment");
                    skipTimeSettingFragment5 = null;
                }
                int selectTime2 = skipTimeSettingFragment5.getSelectTime();
                Api api2 = Api.INSTANCE;
                String id2 = this$0.getId();
                int season2 = this$0.getSeason();
                SkipTimeSettingFragment skipTimeSettingFragment6 = this$0.endFragment;
                if (skipTimeSettingFragment6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("endFragment");
                    skipTimeSettingFragment6 = null;
                }
                arrayList.add(RxSubscribersKt.toMsg(CommonExtKt.request$default(api2.saveSkipTime(id2, season2, skipTimeSettingFragment6.isCheckSeason() ? 0 : this$0.getEpisode(), "add", -1, selectTime2), null, 1, null)).subscribeOn(Schedulers.io()));
                arrayList.add(RxSubscribersKt.toMsg(CommonExtKt.request$default(Api.INSTANCE.voteSkipTime(this$0.getId(), this$0.getSeason(), this$0.getEpisode(), -1, selectTime2), null, 1, null)).subscribeOn(Schedulers.io()));
            }
        }
        Object as = Observable.zip(arrayList, $$Lambda$SkipTimeFragment$ZoXDYkG1wIANYF6pSp8epzeJpzI.INSTANCE).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this$0));
        Intrinsics.checkNotNullExpressionValue(as, "zip(requests){\n         …bindLifecycleOwner(this))");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new SkipTimeFragment$initListener$4$2(this$0), null, new SkipTimeFragment$initListener$4$3(this$0), null, new SkipTimeFragment$initListener$4$4(this$0), 10, null);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        FragmentManager supportFragmentManager;
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        SkipTimeFragment newInstance = Companion.newInstance(getId(), getFid(), getSeason(), getEpisode(), getShowEnd());
        FragmentActivity activity = getActivity();
        if (activity != null && (supportFragmentManager = activity.getSupportFragmentManager()) != null) {
            newInstance.show(supportFragmentManager, SkipTimeFragment.class.getSimpleName());
        }
        dismissAllowingStateLoss();
    }

    @Override // com.movieboxpro.android.base.BaseBindingBottomDialogFragment
    public void initData() {
        String[] strArr = {"Skip Opening", "Skip Ending"};
        ArrayList arrayList = new ArrayList();
        this.startFragment = SkipTimeSettingFragment.Companion.newInstance(getSeason(), getEpisode(), getId(), getFid(), true);
        this.endFragment = SkipTimeSettingFragment.Companion.newInstance(getSeason(), getEpisode(), getId(), getFid(), false);
        SkipTimeSettingFragment skipTimeSettingFragment = this.startFragment;
        FragmentSkipTimeBinding fragmentSkipTimeBinding = null;
        if (skipTimeSettingFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startFragment");
            skipTimeSettingFragment = null;
        }
        arrayList.add(skipTimeSettingFragment);
        SkipTimeSettingFragment skipTimeSettingFragment2 = this.endFragment;
        if (skipTimeSettingFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endFragment");
            skipTimeSettingFragment2 = null;
        }
        arrayList.add(skipTimeSettingFragment2);
        SkipTimeSettingFragment.OnScrollListener onScrollListener = new SkipTimeSettingFragment.OnScrollListener() { // from class: com.movieboxpro.android.view.dialog.SkipTimeFragment$initData$scrollListener$1
            @Override // com.movieboxpro.android.view.dialog.SkipTimeFragment.SkipTimeSettingFragment.OnScrollListener
            public void onScroll() {
                FragmentSkipTimeBinding fragmentSkipTimeBinding2;
                FragmentSkipTimeBinding fragmentSkipTimeBinding3;
                FragmentSkipTimeBinding fragmentSkipTimeBinding4;
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true)) {
                    fragmentSkipTimeBinding2 = SkipTimeFragment.this.binding;
                    FragmentSkipTimeBinding fragmentSkipTimeBinding5 = null;
                    if (fragmentSkipTimeBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentSkipTimeBinding2 = null;
                    }
                    if (fragmentSkipTimeBinding2.tvOk.isEnabled()) {
                        return;
                    }
                    fragmentSkipTimeBinding3 = SkipTimeFragment.this.binding;
                    if (fragmentSkipTimeBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentSkipTimeBinding3 = null;
                    }
                    fragmentSkipTimeBinding3.tvOk.setEnabled(true);
                    fragmentSkipTimeBinding4 = SkipTimeFragment.this.binding;
                    if (fragmentSkipTimeBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentSkipTimeBinding5 = fragmentSkipTimeBinding4;
                    }
                    fragmentSkipTimeBinding5.tvOk.setAlpha(1.0f);
                }
            }
        };
        SkipTimeSettingFragment skipTimeSettingFragment3 = this.startFragment;
        if (skipTimeSettingFragment3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startFragment");
            skipTimeSettingFragment3 = null;
        }
        SkipTimeSettingFragment.OnScrollListener onScrollListener2 = onScrollListener;
        skipTimeSettingFragment3.setScrollListener(onScrollListener2);
        SkipTimeSettingFragment skipTimeSettingFragment4 = this.endFragment;
        if (skipTimeSettingFragment4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("endFragment");
            skipTimeSettingFragment4 = null;
        }
        skipTimeSettingFragment4.setScrollListener(onScrollListener2);
        this.adapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, strArr);
        FragmentSkipTimeBinding fragmentSkipTimeBinding2 = this.binding;
        if (fragmentSkipTimeBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding2 = null;
        }
        fragmentSkipTimeBinding2.viewPager.setOffscreenPageLimit(arrayList.size());
        FragmentSkipTimeBinding fragmentSkipTimeBinding3 = this.binding;
        if (fragmentSkipTimeBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding3 = null;
        }
        NoScrollViewPager noScrollViewPager = fragmentSkipTimeBinding3.viewPager;
        TabLayoutPagerAdapter tabLayoutPagerAdapter = this.adapter;
        if (tabLayoutPagerAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tabLayoutPagerAdapter = null;
        }
        noScrollViewPager.setAdapter(tabLayoutPagerAdapter);
        FragmentSkipTimeBinding fragmentSkipTimeBinding4 = this.binding;
        if (fragmentSkipTimeBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding4 = null;
        }
        TabLayout tabLayout = fragmentSkipTimeBinding4.tabLayout;
        FragmentSkipTimeBinding fragmentSkipTimeBinding5 = this.binding;
        if (fragmentSkipTimeBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding5 = null;
        }
        tabLayout.setupWithViewPager(fragmentSkipTimeBinding5.viewPager);
        if (getShowEnd()) {
            FragmentSkipTimeBinding fragmentSkipTimeBinding6 = this.binding;
            if (fragmentSkipTimeBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding6 = null;
            }
            fragmentSkipTimeBinding6.viewPager.setCurrentItem(1);
        }
        FragmentSkipTimeBinding fragmentSkipTimeBinding7 = this.binding;
        if (fragmentSkipTimeBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding7 = null;
        }
        fragmentSkipTimeBinding7.tvOk.setEnabled(false);
        FragmentSkipTimeBinding fragmentSkipTimeBinding8 = this.binding;
        if (fragmentSkipTimeBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding8 = null;
        }
        fragmentSkipTimeBinding8.tvOk.setAlpha(0.2f);
        FragmentSkipTimeBinding fragmentSkipTimeBinding9 = this.binding;
        if (fragmentSkipTimeBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding9 = null;
        }
        fragmentSkipTimeBinding9.ivCheck.setSelected(PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true));
        FragmentSkipTimeBinding fragmentSkipTimeBinding10 = this.binding;
        if (fragmentSkipTimeBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSkipTimeBinding10 = null;
        }
        if (fragmentSkipTimeBinding10.ivCheck.isSelected()) {
            FragmentSkipTimeBinding fragmentSkipTimeBinding11 = this.binding;
            if (fragmentSkipTimeBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding11 = null;
            }
            fragmentSkipTimeBinding11.ivCheck.setImageResource(R.mipmap.ic_blue_checked);
            FragmentSkipTimeBinding fragmentSkipTimeBinding12 = this.binding;
            if (fragmentSkipTimeBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentSkipTimeBinding = fragmentSkipTimeBinding12;
            }
            TextView textView = fragmentSkipTimeBinding.tvDisableHint;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvDisableHint");
            CommonExtKt.gone(textView);
        } else {
            FragmentSkipTimeBinding fragmentSkipTimeBinding13 = this.binding;
            if (fragmentSkipTimeBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSkipTimeBinding13 = null;
            }
            fragmentSkipTimeBinding13.ivCheck.setImageResource(R.mipmap.ic_blue_check);
            FragmentSkipTimeBinding fragmentSkipTimeBinding14 = this.binding;
            if (fragmentSkipTimeBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentSkipTimeBinding = fragmentSkipTimeBinding14;
            }
            TextView textView2 = fragmentSkipTimeBinding.tvDisableHint;
            Intrinsics.checkNotNullExpressionValue(textView2, "binding.tvDisableHint");
            CommonExtKt.visible(textView2);
        }
        if (getShowEnd()) {
            this.endingLoaded = true;
        } else {
            this.openingLoaded = true;
        }
    }

    /* compiled from: SkipTimeFragment.kt */
    @Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u0000 W2\u00020\u0001:\u0002WXB\u0005¢\u0006\u0002\u0010\u0002J@\u00108\u001a\u0002092\u001a\u0010:\u001a\u0016\u0012\u0004\u0012\u00020<\u0018\u00010;j\n\u0012\u0004\u0012\u00020<\u0018\u0001`=2\u001a\u0010>\u001a\u0016\u0012\u0004\u0012\u00020?\u0018\u00010;j\n\u0012\u0004\u0012\u00020?\u0018\u0001`=H\u0002J\u0006\u0010@\u001a\u00020\fJ\b\u0010A\u001a\u000209H\u0002JH\u0010B\u001a\u0002092\u001a\u0010:\u001a\u0016\u0012\u0004\u0012\u00020<\u0018\u00010;j\n\u0012\u0004\u0012\u00020<\u0018\u0001`=2\u001a\u0010>\u001a\u0016\u0012\u0004\u0012\u00020?\u0018\u00010;j\n\u0012\u0004\u0012\u00020?\u0018\u0001`=2\u0006\u0010C\u001a\u00020DH\u0002J\b\u0010E\u001a\u000209H\u0016J\b\u0010F\u001a\u000209H\u0016Jb\u0010G\u001a\u0002092\u0006\u0010H\u001a\u00020\f2\u0006\u0010I\u001a\u00020\f2\u001c\b\u0002\u0010J\u001a\u0016\u0012\u0004\u0012\u00020<\u0018\u00010;j\n\u0012\u0004\u0012\u00020<\u0018\u0001`=2\u001c\b\u0002\u0010K\u001a\u0016\u0012\u0004\u0012\u00020?\u0018\u00010;j\n\u0012\u0004\u0012\u00020?\u0018\u0001`=2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020$0;H\u0002J\u001e\u0010M\u001a\u0002092\u0006\u0010N\u001a\u00020D2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020$0;H\u0002J\b\u0010O\u001a\u000209H\u0016J\u0006\u0010P\u001a\u00020\u000eJ\u0006\u0010&\u001a\u00020\u000eJ\b\u0010Q\u001a\u000209H\u0016J\b\u0010R\u001a\u000209H\u0016J\u0006\u0010S\u001a\u000209J\u000e\u0010T\u001a\u0002092\u0006\u0010U\u001a\u00020\u000eJ\u000e\u0010V\u001a\u0002092\u0006\u0010*\u001a\u00020+R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u0010\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R/\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u0016\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR/\u0010\u001e\u001a\u0004\u0018\u00010\u00172\b\u0010\u000f\u001a\u0004\u0018\u00010\u00178B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u0016\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010,\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b/\u0010\u0016\u001a\u0004\b-\u0010\u0012\"\u0004\b.\u0010\u0014R\u000e\u00100\u001a\u000201X\u0082.¢\u0006\u0002\n\u0000R+\u00102\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b7\u0010\u0016\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u0006Y"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$SkipTimeSettingFragment;", "Lcom/movieboxpro/android/base/BaseBindingFragment;", "()V", "adapter", "Lcom/movieboxpro/android/adapter/SkipTimeAdapter;", "binding", "Lcom/movieboxpro/android/databinding/FragmentSkipTimeSettingBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/FragmentSkipTimeSettingBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/databinding/FragmentBindingDelegate;", "count", "", "dataLoaded", "", "<set-?>", "episode", "getEpisode", "()I", "setEpisode", "(I)V", "episode$delegate", "Lkotlin/properties/ReadWriteProperty;", "", "fid", "getFid", "()Ljava/lang/String;", "setFid", "(Ljava/lang/String;)V", "fid$delegate", "id", "getId", "setId", "id$delegate", "imageAdapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/VideoThumb;", "imageItemWidth", "isScrolled", "moreBinding", "Lcom/movieboxpro/android/databinding/AdapterMoreImageBinding;", "mostChooseTime", "scrollListener", "Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$SkipTimeSettingFragment$OnScrollListener;", "season", "getSeason", "setSeason", "season$delegate", "snapHelper", "Landroidx/recyclerview/widget/LinearSnapHelper;", "startTimeType", "getStartTimeType", "()Z", "setStartTimeType", "(Z)V", "startTimeType$delegate", "getMoreImage", "", "times", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/SkipStart;", "Lkotlin/collections/ArrayList;", "endTimes", "Lcom/movieboxpro/android/model/SkipEnd;", "getSelectTime", "getSkipTime", "getVideoImage", "skipTime", "Lcom/movieboxpro/android/model/SkipTimeResponse;", "initData", "initListener", "initRecyclerview", "startMax", "endMax", "startTime", "endTime", "videoThumbs", "initSkipTime", "response", "initView", "isCheckSeason", "onFragmentFirstVisible", "onFragmentResume", "refreshData", "setEnable", "enable", "setScrollListener", "Companion", "OnScrollListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class SkipTimeSettingFragment extends BaseBindingFragment {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SkipTimeSettingFragment.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/FragmentSkipTimeSettingBinding;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeSettingFragment.class, "season", "getSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeSettingFragment.class, "episode", "getEpisode()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeSettingFragment.class, "startTimeType", "getStartTimeType()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeSettingFragment.class, "id", "getId()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SkipTimeSettingFragment.class, "fid", "getFid()Ljava/lang/String;", 0))};
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache;
        private SkipTimeAdapter adapter;
        private final FragmentBindingDelegate binding$delegate;
        private int count;
        private boolean dataLoaded;
        private final ReadWriteProperty episode$delegate;
        private final ReadWriteProperty fid$delegate;
        private final ReadWriteProperty id$delegate;
        private CommBaseAdapter<VideoThumb> imageAdapter;
        private int imageItemWidth;
        private boolean isScrolled;
        private AdapterMoreImageBinding moreBinding;
        private int mostChooseTime;
        private OnScrollListener scrollListener;
        private final ReadWriteProperty season$delegate;
        private LinearSnapHelper snapHelper;
        private final ReadWriteProperty startTimeType$delegate;

        /* compiled from: SkipTimeFragment.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$SkipTimeSettingFragment$OnScrollListener;", "", "onScroll", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public interface OnScrollListener {
            void onScroll();
        }

        @Override // com.movieboxpro.android.base.BaseBindingFragment
        public void _$_clearFindViewByIdCache() {
            this._$_findViewCache.clear();
        }

        @Override // com.movieboxpro.android.base.BaseBindingFragment
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

        @Override // com.movieboxpro.android.base.BaseBindingFragment
        public void initView() {
        }

        @Override // com.movieboxpro.android.base.BaseBindingFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        public SkipTimeSettingFragment() {
            super(R.layout.fragment_skip_time_setting);
            this._$_findViewCache = new LinkedHashMap();
            SkipTimeSettingFragment skipTimeSettingFragment = this;
            this.binding$delegate = new FragmentBindingDelegate(FragmentSkipTimeSettingBinding.class, skipTimeSettingFragment);
            this.season$delegate = FragmentArgsKt.arg(skipTimeSettingFragment);
            this.episode$delegate = FragmentArgsKt.arg(skipTimeSettingFragment);
            this.startTimeType$delegate = FragmentArgsKt.arg(skipTimeSettingFragment);
            this.id$delegate = FragmentArgsKt.argOrNull(skipTimeSettingFragment);
            this.fid$delegate = FragmentArgsKt.argOrNull(skipTimeSettingFragment);
            this.mostChooseTime = -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FragmentSkipTimeSettingBinding getBinding() {
            return (FragmentSkipTimeSettingBinding) this.binding$delegate.getValue2((Fragment) this, $$delegatedProperties[0]);
        }

        private final int getSeason() {
            return ((Number) this.season$delegate.getValue(this, $$delegatedProperties[1])).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setSeason(int i) {
            this.season$delegate.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
        }

        private final int getEpisode() {
            return ((Number) this.episode$delegate.getValue(this, $$delegatedProperties[2])).intValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setEpisode(int i) {
            this.episode$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean getStartTimeType() {
            return ((Boolean) this.startTimeType$delegate.getValue(this, $$delegatedProperties[3])).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setStartTimeType(boolean z) {
            this.startTimeType$delegate.setValue(this, $$delegatedProperties[3], Boolean.valueOf(z));
        }

        private final String getId() {
            return (String) this.id$delegate.getValue(this, $$delegatedProperties[4]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setId(String str) {
            this.id$delegate.setValue(this, $$delegatedProperties[4], str);
        }

        private final String getFid() {
            return (String) this.fid$delegate.getValue(this, $$delegatedProperties[5]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setFid(String str) {
            this.fid$delegate.setValue(this, $$delegatedProperties[5], str);
        }

        /* compiled from: SkipTimeFragment.kt */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$SkipTimeSettingFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/SkipTimeFragment$SkipTimeSettingFragment;", "season", "", "episode", "id", "", "fid", "startTimeType", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SkipTimeSettingFragment newInstance(int i, int i2, String str, String str2, boolean z) {
                SkipTimeSettingFragment skipTimeSettingFragment = new SkipTimeSettingFragment();
                skipTimeSettingFragment.setSeason(i);
                skipTimeSettingFragment.setEpisode(i2);
                skipTimeSettingFragment.setStartTimeType(z);
                skipTimeSettingFragment.setId(str);
                skipTimeSettingFragment.setFid(str2);
                return skipTimeSettingFragment;
            }
        }

        public final boolean isScrolled() {
            return this.isScrolled;
        }

        public final void setScrollListener(OnScrollListener scrollListener) {
            Intrinsics.checkNotNullParameter(scrollListener, "scrollListener");
            this.scrollListener = scrollListener;
        }

        public final boolean isCheckSeason() {
            return getBinding().ivCheck.isSelected();
        }

        public final int getSelectTime() {
            RecyclerView.LayoutManager layoutManager = getBinding().rvOpening.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            SkipTimeAdapter skipTimeAdapter = this.adapter;
            if (skipTimeAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                skipTimeAdapter = null;
            }
            return skipTimeAdapter.getItem(linearLayoutManager.findFirstCompletelyVisibleItemPosition() + (this.count / 2)).getTime();
        }

        public final void setEnable(boolean z) {
            if (z) {
                getBinding().rvOpening.setAlpha(1.0f);
                getBinding().rvImage.setAlpha(1.0f);
                getBinding().llAll.setAlpha(1.0f);
                return;
            }
            getBinding().rvOpening.setAlpha(0.2f);
            getBinding().rvImage.setAlpha(0.2f);
            getBinding().llAll.setAlpha(0.2f);
        }

        @Override // com.movieboxpro.android.base.BaseBindingFragment
        public void initListener() {
            getBinding().llAll.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$JrV9Ctlh6sddHxj26iLrHxEcDi0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SkipTimeFragment.SkipTimeSettingFragment.m1120initListener$lambda0(SkipTimeFragment.SkipTimeSettingFragment.this, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initListener$lambda-0  reason: not valid java name */
        public static final void m1120initListener$lambda0(SkipTimeSettingFragment this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getBinding().ivCheck.setSelected(!this$0.getBinding().ivCheck.isSelected());
            if (this$0.getBinding().ivCheck.isSelected()) {
                this$0.getBinding().ivCheck.setImageResource(R.mipmap.ic_blue_checked);
            } else {
                this$0.getBinding().ivCheck.setImageResource(R.mipmap.ic_blue_check);
            }
            this$0.isScrolled = true;
            this$0.dataLoaded = true;
            OnScrollListener onScrollListener = this$0.scrollListener;
            if (onScrollListener == null) {
                return;
            }
            onScrollListener.onScroll();
        }

        @Override // com.movieboxpro.android.base.BaseLazyFragment
        public void onFragmentResume() {
            super.onFragmentResume();
            setEnable(PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true));
        }

        @Override // com.movieboxpro.android.base.BaseBindingFragment
        public void initData() {
            this.imageAdapter = new CommBaseAdapter<>(R.layout.adapter_skip_image_item, new SkipTimeFragment$SkipTimeSettingFragment$initData$1(this), null, 4, null);
            getBinding().rvImage.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
            getBinding().rvImage.addItemDecoration(new GalleryItemDecoration());
            RecyclerView recyclerView = getBinding().rvImage;
            CommBaseAdapter<VideoThumb> commBaseAdapter = this.imageAdapter;
            LinearSnapHelper linearSnapHelper = null;
            if (commBaseAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                commBaseAdapter = null;
            }
            recyclerView.setAdapter(commBaseAdapter);
            LinearSnapHelper linearSnapHelper2 = new LinearSnapHelper();
            this.snapHelper = linearSnapHelper2;
            if (linearSnapHelper2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("snapHelper");
            } else {
                linearSnapHelper = linearSnapHelper2;
            }
            linearSnapHelper.attachToRecyclerView(getBinding().rvImage);
            getBinding().loadingView.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$ErNX2dtEKh5YF0bUDbX_UwufpcY
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    SkipTimeFragment.SkipTimeSettingFragment.m1119initData$lambda1(SkipTimeFragment.SkipTimeSettingFragment.this);
                }
            });
            TextView textView = getBinding().tvSeasonInfo;
            StringBuilder sb = new StringBuilder();
            sb.append("Apply to All Episodes of Season ");
            sb.append(getSeason());
            sb.append(" for ");
            sb.append(getStartTimeType() ? "Opening" : "Ending");
            textView.setText(sb.toString());
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = -1;
            final Ref.IntRef intRef2 = new Ref.IntRef();
            getBinding().rvOpening.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.dialog.SkipTimeFragment$SkipTimeSettingFragment$initData$3
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                    boolean z;
                    SkipTimeFragment.SkipTimeSettingFragment.OnScrollListener onScrollListener;
                    FragmentSkipTimeSettingBinding binding;
                    int i3;
                    SkipTimeAdapter skipTimeAdapter;
                    boolean startTimeType;
                    CommBaseAdapter commBaseAdapter2;
                    FragmentSkipTimeSettingBinding binding2;
                    int i4;
                    int i5;
                    FragmentSkipTimeSettingBinding binding3;
                    int i6;
                    int i7;
                    CommBaseAdapter commBaseAdapter3;
                    FragmentSkipTimeSettingBinding binding4;
                    int i8;
                    FragmentSkipTimeSettingBinding binding5;
                    FragmentSkipTimeSettingBinding binding6;
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                    z = SkipTimeFragment.SkipTimeSettingFragment.this.dataLoaded;
                    if (z) {
                        onScrollListener = SkipTimeFragment.SkipTimeSettingFragment.this.scrollListener;
                        if (onScrollListener != null) {
                            onScrollListener.onScroll();
                        }
                        SkipTimeFragment.SkipTimeSettingFragment.this.isScrolled = true;
                        if (recyclerView2.getScrollState() != 0) {
                            binding = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                            RecyclerView.LayoutManager layoutManager = binding.rvOpening.getLayoutManager();
                            if (layoutManager == null) {
                                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                            }
                            int findFirstCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
                            i3 = SkipTimeFragment.SkipTimeSettingFragment.this.count;
                            int i9 = findFirstCompletelyVisibleItemPosition + (i3 / 2);
                            if (i9 != intRef.element) {
                                skipTimeAdapter = SkipTimeFragment.SkipTimeSettingFragment.this.adapter;
                                CommBaseAdapter commBaseAdapter4 = null;
                                if (skipTimeAdapter == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                    skipTimeAdapter = null;
                                }
                                int time = skipTimeAdapter.getItem(i9).getTime();
                                startTimeType = SkipTimeFragment.SkipTimeSettingFragment.this.getStartTimeType();
                                if (startTimeType) {
                                    commBaseAdapter3 = SkipTimeFragment.SkipTimeSettingFragment.this.imageAdapter;
                                    if (commBaseAdapter3 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                                    } else {
                                        commBaseAdapter4 = commBaseAdapter3;
                                    }
                                    Iterator it = commBaseAdapter4.getData().iterator();
                                    int i10 = 0;
                                    while (true) {
                                        if (!it.hasNext()) {
                                            i10 = -1;
                                            break;
                                        }
                                        if (((VideoThumb) it.next()).getSeconds() == time) {
                                            break;
                                        }
                                        i10++;
                                    }
                                    if (i10 != -1) {
                                        if (intRef2.element == 0) {
                                            binding5 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                            RecyclerView.LayoutManager layoutManager2 = binding5.rvImage.getLayoutManager();
                                            if (layoutManager2 == null) {
                                                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                            }
                                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager2;
                                            intRef2.element = linearLayoutManager.findLastVisibleItemPosition() - linearLayoutManager.findFirstVisibleItemPosition();
                                            binding6 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                            RecyclerView.LayoutManager layoutManager3 = binding6.rvImage.getLayoutManager();
                                            if (layoutManager3 == null) {
                                                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                            }
                                            ((LinearLayoutManager) layoutManager3).scrollToPositionWithOffset(i10, 0);
                                        } else {
                                            binding4 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                            RecyclerView.LayoutManager layoutManager4 = binding4.rvImage.getLayoutManager();
                                            if (layoutManager4 == null) {
                                                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                            }
                                            i8 = SkipTimeFragment.SkipTimeSettingFragment.this.imageItemWidth;
                                            ((LinearLayoutManager) layoutManager4).scrollToPositionWithOffset(i10, (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i8 / 2));
                                        }
                                    }
                                } else {
                                    commBaseAdapter2 = SkipTimeFragment.SkipTimeSettingFragment.this.imageAdapter;
                                    if (commBaseAdapter2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                                    } else {
                                        commBaseAdapter4 = commBaseAdapter2;
                                    }
                                    if (commBaseAdapter4.hasHeaderLayout()) {
                                        binding3 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                        RecyclerView.LayoutManager layoutManager5 = binding3.rvImage.getLayoutManager();
                                        if (layoutManager5 != null) {
                                            i6 = SkipTimeFragment.SkipTimeSettingFragment.this.count;
                                            i7 = SkipTimeFragment.SkipTimeSettingFragment.this.imageItemWidth;
                                            ((LinearLayoutManager) layoutManager5).scrollToPositionWithOffset((i9 - (i6 / 2)) + 1, (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i7 / 2));
                                        } else {
                                            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                        }
                                    } else {
                                        binding2 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                        RecyclerView.LayoutManager layoutManager6 = binding2.rvImage.getLayoutManager();
                                        if (layoutManager6 != null) {
                                            i4 = SkipTimeFragment.SkipTimeSettingFragment.this.count;
                                            i5 = SkipTimeFragment.SkipTimeSettingFragment.this.imageItemWidth;
                                            ((LinearLayoutManager) layoutManager6).scrollToPositionWithOffset(i9 - (i4 / 2), (DensityUtils.getScreenWidth(App.getContext()) / 2) - (i5 / 2));
                                        } else {
                                            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                        }
                                    }
                                }
                            }
                            intRef.element = i9;
                        }
                    }
                }
            });
            final Ref.IntRef intRef3 = new Ref.IntRef();
            intRef3.element = -1;
            RecyclerView.LayoutManager layoutManager = getBinding().rvImage.getLayoutManager();
            if (layoutManager == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            }
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            getBinding().rvImage.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.movieboxpro.android.view.dialog.SkipTimeFragment$SkipTimeSettingFragment$initData$4
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                    boolean z;
                    SkipTimeFragment.SkipTimeSettingFragment.OnScrollListener onScrollListener;
                    LinearSnapHelper linearSnapHelper3;
                    FragmentSkipTimeSettingBinding binding;
                    Integer valueOf;
                    boolean startTimeType;
                    CommBaseAdapter commBaseAdapter2;
                    FragmentSkipTimeSettingBinding binding2;
                    FragmentSkipTimeSettingBinding binding3;
                    FragmentSkipTimeSettingBinding binding4;
                    Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                    z = SkipTimeFragment.SkipTimeSettingFragment.this.dataLoaded;
                    if (z) {
                        onScrollListener = SkipTimeFragment.SkipTimeSettingFragment.this.scrollListener;
                        if (onScrollListener != null) {
                            onScrollListener.onScroll();
                        }
                        SkipTimeFragment.SkipTimeSettingFragment.this.isScrolled = true;
                        if (recyclerView2.getScrollState() != 0) {
                            linearSnapHelper3 = SkipTimeFragment.SkipTimeSettingFragment.this.snapHelper;
                            CommBaseAdapter commBaseAdapter3 = null;
                            if (linearSnapHelper3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("snapHelper");
                                linearSnapHelper3 = null;
                            }
                            View findSnapView = linearSnapHelper3.findSnapView(linearLayoutManager);
                            if (findSnapView == null) {
                                valueOf = null;
                            } else {
                                binding = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                valueOf = Integer.valueOf(binding.rvImage.getChildAdapterPosition(findSnapView));
                            }
                            if (valueOf != null) {
                                if (valueOf.intValue() != intRef3.element) {
                                    startTimeType = SkipTimeFragment.SkipTimeSettingFragment.this.getStartTimeType();
                                    if (startTimeType) {
                                        binding4 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                        RecyclerView.LayoutManager layoutManager2 = binding4.rvOpening.getLayoutManager();
                                        if (layoutManager2 == null) {
                                            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                        }
                                        ((LinearLayoutManager) layoutManager2).scrollToPositionWithOffset(valueOf.intValue(), 0);
                                    } else {
                                        commBaseAdapter2 = SkipTimeFragment.SkipTimeSettingFragment.this.imageAdapter;
                                        if (commBaseAdapter2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                                        } else {
                                            commBaseAdapter3 = commBaseAdapter2;
                                        }
                                        if (commBaseAdapter3.hasHeaderLayout()) {
                                            binding3 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                            RecyclerView.LayoutManager layoutManager3 = binding3.rvOpening.getLayoutManager();
                                            if (layoutManager3 == null) {
                                                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                            }
                                            ((LinearLayoutManager) layoutManager3).scrollToPositionWithOffset(valueOf.intValue() - 1, 0);
                                        } else {
                                            binding2 = SkipTimeFragment.SkipTimeSettingFragment.this.getBinding();
                                            RecyclerView.LayoutManager layoutManager4 = binding2.rvOpening.getLayoutManager();
                                            if (layoutManager4 == null) {
                                                throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                                            }
                                            ((LinearLayoutManager) layoutManager4).scrollToPositionWithOffset(valueOf.intValue(), 0);
                                        }
                                    }
                                }
                            }
                            if (valueOf != null) {
                                intRef3.element = valueOf.intValue();
                            }
                        }
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initData$lambda-1  reason: not valid java name */
        public static final void m1119initData$lambda1(SkipTimeSettingFragment this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.getSkipTime();
        }

        public final void refreshData() {
            getSkipTime();
        }

        @Override // com.movieboxpro.android.base.BaseLazyFragment
        public void onFragmentFirstVisible() {
            super.onFragmentFirstVisible();
            getSkipTime();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initRecyclerview(final int i, final int i2, final ArrayList<SkipStart> arrayList, final ArrayList<SkipEnd> arrayList2, final ArrayList<VideoThumb> arrayList3) {
            this.count = CommonUtils.isTablet() ? 31 : 15;
            getBinding().rvOpening.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$r6HbXI-lOd5kR6QE32tHpNfgrD8
                @Override // java.lang.Runnable
                public final void run() {
                    SkipTimeFragment.SkipTimeSettingFragment.m1121initRecyclerview$lambda11(SkipTimeFragment.SkipTimeSettingFragment.this, arrayList3, arrayList, arrayList2, i, i2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initRecyclerview$lambda-11  reason: not valid java name */
        public static final void m1121initRecyclerview$lambda11(SkipTimeSettingFragment this$0, ArrayList videoThumbs, ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
            Object obj;
            Object obj2;
            boolean z;
            boolean z2;
            Object obj3;
            boolean z3;
            int i3;
            Object obj4;
            boolean z4;
            Object obj5;
            boolean z5;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(videoThumbs, "$videoThumbs");
            int width = this$0.getBinding().rvOpening.getWidth() / this$0.count;
            ArrayList arrayList3 = new ArrayList();
            int i4 = this$0.count / 2;
            int i5 = 0;
            while (i5 < i4) {
                i5++;
                arrayList3.add(new SkipTimeItem(-1, -1));
            }
            int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, (videoThumbs.size() - 1) * 2, 2);
            if (progressionLastElement >= 0) {
                int i6 = 0;
                while (true) {
                    int i7 = i6 + 2;
                    arrayList3.add(new SkipTimeItem(i6, 0));
                    if (i6 == progressionLastElement) {
                        break;
                    }
                    i6 = i7;
                }
            }
            int i8 = this$0.count / 2;
            int i9 = 0;
            while (i9 < i8) {
                i9++;
                arrayList3.add(new SkipTimeItem(-1, -1));
            }
            SkipTimeAdapter skipTimeAdapter = null;
            if (this$0.getStartTimeType()) {
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        SkipStart skipStart = (SkipStart) it.next();
                        Iterator it2 = arrayList3.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                obj5 = null;
                                break;
                            }
                            obj5 = it2.next();
                            if (((SkipTimeItem) obj5).getTime() == skipStart.getStart()) {
                                z5 = true;
                                continue;
                            } else {
                                z5 = false;
                                continue;
                            }
                            if (z5) {
                                break;
                            }
                        }
                        SkipTimeItem skipTimeItem = (SkipTimeItem) obj5;
                        if (skipTimeItem != null) {
                            skipTimeItem.setTotal(skipStart.getTotal());
                        }
                    }
                }
                ArrayList arrayList4 = arrayList;
                if (!(arrayList4 == null || arrayList4.isEmpty())) {
                    ArrayList arrayList5 = arrayList;
                    Iterator it3 = arrayList5.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            obj4 = null;
                            break;
                        }
                        obj4 = it3.next();
                        SkipStart skipStart2 = (SkipStart) obj4;
                        Iterator it4 = arrayList5.iterator();
                        if (!it4.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        int total = ((SkipStart) it4.next()).getTotal();
                        while (it4.hasNext()) {
                            int total2 = ((SkipStart) it4.next()).getTotal();
                            if (total < total2) {
                                total = total2;
                            }
                        }
                        if (total == skipStart2.getTotal()) {
                            z4 = true;
                            continue;
                        } else {
                            z4 = false;
                            continue;
                        }
                        if (z4) {
                            break;
                        }
                    }
                    SkipStart skipStart3 = (SkipStart) obj4;
                    this$0.mostChooseTime = skipStart3 == null ? 0 : skipStart3.getStart();
                }
            } else {
                if (arrayList2 != null) {
                    Iterator it5 = arrayList2.iterator();
                    while (it5.hasNext()) {
                        SkipEnd skipEnd = (SkipEnd) it5.next();
                        Iterator it6 = arrayList3.iterator();
                        while (true) {
                            if (!it6.hasNext()) {
                                obj3 = null;
                                break;
                            }
                            obj3 = it6.next();
                            if (((SkipTimeItem) obj3).getTime() == skipEnd.getEnd()) {
                                z3 = true;
                                continue;
                            } else {
                                z3 = false;
                                continue;
                            }
                            if (z3) {
                                break;
                            }
                        }
                        SkipTimeItem skipTimeItem2 = (SkipTimeItem) obj3;
                        if (skipTimeItem2 != null) {
                            skipTimeItem2.setTotal(skipEnd.getTotal());
                        }
                    }
                }
                ArrayList arrayList6 = arrayList2;
                if (!(arrayList6 == null || arrayList6.isEmpty())) {
                    CommBaseAdapter<VideoThumb> commBaseAdapter = this$0.imageAdapter;
                    if (commBaseAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                        commBaseAdapter = null;
                    }
                    VideoThumb videoThumb = (VideoThumb) CollectionsKt.lastOrNull((List<? extends Object>) commBaseAdapter.getData());
                    if (videoThumb != null) {
                        ArrayList arrayList7 = arrayList2;
                        Iterator it7 = arrayList7.iterator();
                        while (true) {
                            if (!it7.hasNext()) {
                                obj = null;
                                break;
                            }
                            obj = it7.next();
                            SkipEnd skipEnd2 = (SkipEnd) obj;
                            Iterator it8 = arrayList7.iterator();
                            if (!it8.hasNext()) {
                                throw new NoSuchElementException();
                            }
                            int total3 = ((SkipEnd) it8.next()).getTotal();
                            while (it8.hasNext()) {
                                int total4 = ((SkipEnd) it8.next()).getTotal();
                                if (total3 < total4) {
                                    total3 = total4;
                                }
                            }
                            if (total3 == skipEnd2.getTotal()) {
                                z2 = true;
                                continue;
                            } else {
                                z2 = false;
                                continue;
                            }
                            if (z2) {
                                break;
                            }
                        }
                        SkipEnd skipEnd3 = (SkipEnd) obj;
                        int end = skipEnd3 == null ? 0 : skipEnd3.getEnd();
                        CommBaseAdapter<VideoThumb> commBaseAdapter2 = this$0.imageAdapter;
                        if (commBaseAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("imageAdapter");
                            commBaseAdapter2 = null;
                        }
                        Iterator<T> it9 = commBaseAdapter2.getData().iterator();
                        while (true) {
                            if (!it9.hasNext()) {
                                obj2 = null;
                                break;
                            }
                            obj2 = it9.next();
                            if (videoThumb.getSeconds() - ((VideoThumb) obj2).getSeconds() == end) {
                                z = true;
                                continue;
                            } else {
                                z = false;
                                continue;
                            }
                            if (z) {
                                break;
                            }
                        }
                        VideoThumb videoThumb2 = (VideoThumb) obj2;
                        this$0.mostChooseTime = videoThumb2 == null ? 0 : videoThumb2.getSeconds();
                    }
                }
            }
            this$0.getBinding().rvOpening.setLayoutManager(new LinearLayoutManager(this$0.getContext(), 0, false));
            if (!this$0.getStartTimeType() ? (i3 = i2) < 40 : (i3 = i) < 40) {
                i3 = 40;
            }
            if (!this$0.getStartTimeType()) {
                CollectionsKt.reverse(arrayList3);
            }
            this$0.adapter = new SkipTimeAdapter(arrayList3, width, i3);
            RecyclerView recyclerView = this$0.getBinding().rvOpening;
            SkipTimeAdapter skipTimeAdapter2 = this$0.adapter;
            if (skipTimeAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                skipTimeAdapter = skipTimeAdapter2;
            }
            recyclerView.setAdapter(skipTimeAdapter);
            new GravitySnapHelper(8388611).attachToRecyclerView(this$0.getBinding().rvOpening);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getVideoImage(ArrayList<SkipStart> arrayList, ArrayList<SkipEnd> arrayList2, SkipTimeResponse skipTimeResponse) {
            Observable compose;
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            boolean startTimeType = getStartTimeType();
            String str = TtmlNode.START;
            if ((startTimeType && skipTimeResponse.getStart() > 180) || (!getStartTimeType() && skipTimeResponse.getEnd() > 180)) {
                booleanRef.element = true;
                Observable<R> compose2 = HttpRequest.Companion.post("Video_thumbs_v2", API.VIDEO_THUMB_URL).param("fid", getFid()).param("box_type", Integer.valueOf(getSeason() == 0 ? 1 : 2)).param(IjkMediaMeta.IJKM_KEY_TYPE, getStartTimeType() ? TtmlNode.START : TtmlNode.END).asRequest().compose(RxUtils.rxTranslate2Bean(VideoThumbResponse.class));
                Intrinsics.checkNotNullExpressionValue(compose2, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
                Observable subscribeOn = compose2.subscribeOn(Schedulers.io());
                HttpRequest param = HttpRequest.Companion.post("Video_thumbs_v2", API.VIDEO_THUMB_URL).param("fid", getFid()).param("box_type", Integer.valueOf(getSeason() == 0 ? 1 : 2));
                if (!getStartTimeType()) {
                    str = TtmlNode.END;
                }
                Observable<R> compose3 = param.param(IjkMediaMeta.IJKM_KEY_TYPE, str).param("range", (Object) 360).asRequest().compose(RxUtils.rxTranslate2Bean(VideoThumbResponse.class));
                Intrinsics.checkNotNullExpressionValue(compose3, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
                compose = Observable.zip(subscribeOn, compose3.subscribeOn(Schedulers.io()), new BiFunction() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$SkipTimeFragment$SkipTimeSettingFragment$lJ742JkwPdJq22P6V6MhNuj0WdI
                    @Override // io.reactivex.functions.BiFunction
                    public final Object apply(Object obj, Object obj2) {
                        ArrayList m1117getVideoImage$lambda12;
                        m1117getVideoImage$lambda12 = SkipTimeFragment.SkipTimeSettingFragment.m1117getVideoImage$lambda12(SkipTimeFragment.SkipTimeSettingFragment.this, (VideoThumbResponse) obj, (VideoThumbResponse) obj2);
                        return m1117getVideoImage$lambda12;
                    }
                }).observeOn(AndroidSchedulers.mainThread());
            } else {
                HttpRequest param2 = HttpRequest.Companion.post("Video_thumbs_v2", API.VIDEO_THUMB_URL).param("fid", getFid()).param("box_type", Integer.valueOf(getSeason() == 0 ? 1 : 2));
                if (!getStartTimeType()) {
                    str = TtmlNode.END;
                }
                Observable<R> compose4 = param2.param(IjkMediaMeta.IJKM_KEY_TYPE, str).asRequest().compose(RxUtils.rxTranslate2Bean(VideoThumbResponse.class));
                Intrinsics.checkNotNullExpressionValue(compose4, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
                compose = compose4.map($$Lambda$SkipTimeFragment$SkipTimeSettingFragment$eDq2jBcHz5m_vhueD8umsmYgug.INSTANCE).compose(RxUtils.rxSchedulerHelper());
            }
            Object as = compose.as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(as, "request.`as`(RxUtils.bindLifecycleOwner(this))");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$1(this), null, new SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$2(this), null, new SkipTimeFragment$SkipTimeSettingFragment$getVideoImage$3(this, booleanRef, skipTimeResponse, arrayList, arrayList2), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getVideoImage$lambda-12  reason: not valid java name */
        public static final ArrayList m1117getVideoImage$lambda12(SkipTimeSettingFragment this$0, VideoThumbResponse data1, VideoThumbResponse data2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(data1, "data1");
            Intrinsics.checkNotNullParameter(data2, "data2");
            ArrayList arrayList = new ArrayList();
            if (this$0.getStartTimeType()) {
                ArrayList<VideoThumb> thumbs = data1.getThumbs();
                if (thumbs == null) {
                    thumbs = new ArrayList<>();
                }
                arrayList.addAll(thumbs);
                ArrayList<VideoThumb> thumbs2 = data2.getThumbs();
                if (thumbs2 == null) {
                    thumbs2 = new ArrayList<>();
                }
                arrayList.addAll(thumbs2);
            } else {
                ArrayList<VideoThumb> thumbs3 = data2.getThumbs();
                if (thumbs3 == null) {
                    thumbs3 = new ArrayList<>();
                }
                arrayList.addAll(thumbs3);
                ArrayList<VideoThumb> thumbs4 = data1.getThumbs();
                if (thumbs4 == null) {
                    thumbs4 = new ArrayList<>();
                }
                arrayList.addAll(thumbs4);
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getVideoImage$lambda-13  reason: not valid java name */
        public static final ArrayList m1118getVideoImage$lambda13(VideoThumbResponse it) {
            Intrinsics.checkNotNullParameter(it, "it");
            ArrayList<VideoThumb> thumbs = it.getThumbs();
            return thumbs == null ? new ArrayList() : thumbs;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getMoreImage(ArrayList<SkipStart> arrayList, ArrayList<SkipEnd> arrayList2) {
            Object as = HttpRequest.Companion.post("Video_thumbs_v2", API.VIDEO_THUMB_URL).param("fid", getFid()).param("box_type", Integer.valueOf(getSeason() == 0 ? 1 : 2)).param(IjkMediaMeta.IJKM_KEY_TYPE, getStartTimeType() ? TtmlNode.START : TtmlNode.END).param("range", (Object) 360).asRequest().compose(RxUtils.rxTranslate2Bean(VideoThumbResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(as, "HttpRequest.post(\"Video_…bindLifecycleOwner(this))");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$1(this), null, new SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$2(this), null, new SkipTimeFragment$SkipTimeSettingFragment$getMoreImage$3(this, arrayList, arrayList2), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void initSkipTime(SkipTimeResponse skipTimeResponse, ArrayList<VideoThumb> arrayList) {
            int i;
            int i2;
            int i3;
            ArrayList<SkipStart> start_top_list = skipTimeResponse.getStart_top_list();
            if (start_top_list == null || start_top_list.isEmpty()) {
                i = 0;
            } else {
                Iterator<T> it = skipTimeResponse.getStart_top_list().iterator();
                if (!it.hasNext()) {
                    throw new NoSuchElementException();
                }
                i = ((SkipStart) it.next()).getTotal();
                while (it.hasNext()) {
                    int total = ((SkipStart) it.next()).getTotal();
                    if (i < total) {
                        i = total;
                    }
                }
            }
            ArrayList<SkipEnd> end_top_list = skipTimeResponse.getEnd_top_list();
            if (end_top_list == null || end_top_list.isEmpty()) {
                i2 = 0;
            } else {
                Iterator<T> it2 = skipTimeResponse.getEnd_top_list().iterator();
                if (!it2.hasNext()) {
                    throw new NoSuchElementException();
                }
                int total2 = ((SkipEnd) it2.next()).getTotal();
                while (it2.hasNext()) {
                    int total3 = ((SkipEnd) it2.next()).getTotal();
                    if (total2 < total3) {
                        total2 = total3;
                    }
                }
                i2 = total2;
            }
            initRecyclerview(i, i2, skipTimeResponse.getStart_top_list(), skipTimeResponse.getEnd_top_list(), arrayList);
            if (getStartTimeType()) {
                if (skipTimeResponse.getStart_is_multi() == 1) {
                    getBinding().ivCheck.setSelected(true);
                    getBinding().ivCheck.setImageResource(R.mipmap.ic_blue_checked);
                }
                if (skipTimeResponse.getStart() != -1) {
                    i3 = CommonUtils.isTablet() ? 31 : 15;
                    Object as = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                    Intrinsics.checkNotNullExpressionValue(as, "timer(200, TimeUnit.MILL…bindLifecycleOwner(this))");
                    RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, null, new SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$1(this, i3, skipTimeResponse), null, null, null, 29, null);
                    return;
                }
                Object as2 = Observable.timer(300L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(as2, "timer(300, TimeUnit.MILL…bindLifecycleOwner(this))");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as2, null, new SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$2(this), null, null, null, 29, null);
                return;
            }
            if (skipTimeResponse.getEnd_is_multi() == 1) {
                getBinding().ivCheck.setSelected(true);
                getBinding().ivCheck.setImageResource(R.mipmap.ic_blue_checked);
            }
            if (skipTimeResponse.getEnd() != -1) {
                i3 = CommonUtils.isTablet() ? 31 : 15;
                Object as3 = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(as3, "timer(200, TimeUnit.MILL…bindLifecycleOwner(this))");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as3, null, new SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$3(this, i3, skipTimeResponse), null, null, null, 29, null);
                return;
            }
            Object as4 = Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(as4, "timer(200, TimeUnit.MILL…bindLifecycleOwner(this))");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as4, null, new SkipTimeFragment$SkipTimeSettingFragment$initSkipTime$4(this), null, null, null, 29, null);
        }

        private final void getSkipTime() {
            Object as = CommonExtKt.request$default(Api.INSTANCE.getSkipTime(getId(), getSeason(), getEpisode()), null, 1, null).compose(RxUtils.rxTranslate2Bean(SkipTimeResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(as, "Api.getSkipTime(id, seas…bindLifecycleOwner(this))");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) as, new SkipTimeFragment$SkipTimeSettingFragment$getSkipTime$1(this), null, new SkipTimeFragment$SkipTimeSettingFragment$getSkipTime$2(this), null, new SkipTimeFragment$SkipTimeSettingFragment$getSkipTime$3(this), 10, null);
        }
    }
}
