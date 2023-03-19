package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivitySuperChildModeBinding;
import com.movieboxpro.android.event.SuperChildModeChangedEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.settings.InputSuperChildModePasswordActivity;
import com.movieboxpro.android.view.activity.settings.SuperChildModePasswordActivity;
import com.movieboxpro.android.view.fragment.ChildModeListFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.greenrobot.eventbus.EventBus;
/* compiled from: SuperChildModeActivity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0016J\"\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/activity/SuperChildModeActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "CHILD_MODE_CODE", "", "INPUT_ADD_CODE", "INPUT_CHILD_MODE_CODE", "INPUT_EDIT_CODE", "binding", "Lcom/movieboxpro/android/databinding/ActivitySuperChildModeBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivitySuperChildModeBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "childModeEnable", "", "value", "edit", "setEdit", "(Z)V", "fragment", "Lcom/movieboxpro/android/view/fragment/ChildModeListFragment;", "initData", "", "initListener", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SuperChildModeActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SuperChildModeActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivitySuperChildModeBinding;", 0))};
    private boolean edit;
    private ChildModeListFragment fragment;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivitySuperChildModeBinding.class, this);
    private boolean childModeEnable = true;
    private final int CHILD_MODE_CODE = 1;
    private final int INPUT_CHILD_MODE_CODE = 2;
    private final int INPUT_ADD_CODE = 3;
    private final int INPUT_EDIT_CODE = 4;

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
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

    private final ActivitySuperChildModeBinding getBinding() {
        return (ActivitySuperChildModeBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    private final void setEdit(boolean z) {
        this.edit = z;
        if (z) {
            getBinding().toolBar.tvRight.setText("Done");
        } else {
            getBinding().toolBar.tvRight.setText("Edit");
        }
        ChildModeListFragment childModeListFragment = this.fragment;
        if (childModeListFragment == null) {
            return;
        }
        childModeListFragment.setEdit(z);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llRight.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeActivity$D1URaq8Aga2wLu_28Nt6o67JP_U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModeActivity.m293initListener$lambda0(SuperChildModeActivity.this, view);
            }
        });
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeActivity$lrAPnuz3g20xoSpRn8lBAuEb9Zk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModeActivity.m294initListener$lambda1(SuperChildModeActivity.this, view);
            }
        });
        getBinding().switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeActivity$mq9VY5LXa0udUThlmwn7vxsOfrs
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SuperChildModeActivity.m295initListener$lambda2(SuperChildModeActivity.this, compoundButton, z);
            }
        });
        getBinding().llAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$SuperChildModeActivity$TTX8CMvu7HPM3Bym_LUOF3-vkXY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SuperChildModeActivity.m296initListener$lambda3(SuperChildModeActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m293initListener$lambda0(SuperChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE) && !this$0.edit) {
            InputSuperChildModePasswordActivity.Companion.start(this$0, this$0.INPUT_EDIT_CODE);
        } else {
            this$0.setEdit(!this$0.edit);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m294initListener$lambda1(SuperChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m295initListener$lambda2(SuperChildModeActivity this$0, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.childModeEnable) {
            if (z) {
                SuperChildModePasswordActivity.Companion.start(this$0, this$0.CHILD_MODE_CODE);
            } else {
                InputSuperChildModePasswordActivity.Companion.start(this$0, this$0.INPUT_CHILD_MODE_CODE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-3  reason: not valid java name */
    public static final void m296initListener$lambda3(SuperChildModeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
            InputSuperChildModePasswordActivity.Companion.start(this$0, this$0.INPUT_ADD_CODE);
            return;
        }
        SuperChildModeActivity superChildModeActivity = this$0;
        superChildModeActivity.startActivity(new Intent(superChildModeActivity, AddMovieListActivity.class));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.CHILD_MODE_CODE) {
            if (i2 == -1) {
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, true);
                return;
            }
            this.childModeEnable = false;
            getBinding().switchCompat.setChecked(false);
            this.childModeEnable = true;
        } else if (i == this.INPUT_CHILD_MODE_CODE) {
            if (i2 == -1) {
                EventBus.getDefault().post(new SuperChildModeChangedEvent(false));
                PrefsUtils.getInstance().putBoolean(Constant.Prefs.SUPER_CHILD_MODE, false);
                PrefsUtils.getInstance().putString(Constant.Prefs.SUPER_CHILD_MODE_PASSWORD, "");
                return;
            }
            this.childModeEnable = false;
            getBinding().switchCompat.setChecked(true);
            this.childModeEnable = true;
        } else if (i == this.INPUT_ADD_CODE) {
            if (i2 == -1) {
                SuperChildModeActivity superChildModeActivity = this;
                superChildModeActivity.startActivity(new Intent(superChildModeActivity, AddMovieListActivity.class));
            }
        } else if (i == this.INPUT_EDIT_CODE && i2 == -1) {
            setEdit(!this.edit);
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        this.fragment = new ChildModeListFragment();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        ChildModeListFragment childModeListFragment = this.fragment;
        Intrinsics.checkNotNull(childModeListFragment);
        FragmentUtils.add(supportFragmentManager, childModeListFragment, (int) R.id.flContainer);
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE, false)) {
            getBinding().switchCompat.setChecked(true);
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Super Child Mode");
        TextView textView = getBinding().toolBar.tvRight;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.toolBar.tvRight");
        CommonExtKt.visible(textView);
        getBinding().toolBar.tvRight.setText("Edit");
    }
}
