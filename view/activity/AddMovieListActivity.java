package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.databinding.ActivityAddMovieListBinding;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity;
import com.movieboxpro.android.view.fragment.movielist.MovieListFragment;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
/* compiled from: AddMovieListActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/AddMovieListActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityAddMovieListBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityAddMovieListBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "initData", "", "initListener", "initView", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddMovieListActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(AddMovieListActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityAddMovieListBinding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityAddMovieListBinding.class, this);

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

    private final ActivityAddMovieListBinding getBinding() {
        return (ActivityAddMovieListBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AddMovieListActivity$zbXIqXqy0VnJjmqHhm3Ph5EKxEI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieListActivity.m138initListener$lambda0(AddMovieListActivity.this, view);
            }
        });
        getBinding().toolBar.llRight.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$AddMovieListActivity$t-vqr6Dal7esODzsPMXTfQlbmGw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieListActivity.m139initListener$lambda1(AddMovieListActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m138initListener$lambda0(AddMovieListActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m139initListener$lambda1(AddMovieListActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CreateMovieListActivity.start((Context) this$0, true);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        FragmentUtils.add(getSupportFragmentManager(), MovieListFragment.newInstance(true), (int) R.id.flContainer);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Add Lists");
        ImageView imageView = getBinding().toolBar.ivRight;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.toolBar.ivRight");
        CommonExtKt.visible(imageView);
        int dp2Px = CommonExtKt.dp2Px(5);
        getBinding().toolBar.ivRight.setPadding(dp2Px, dp2Px, dp2Px, dp2Px);
        DensityUtils.addMargin(getBinding().toolBar.ivRight, 0, 0, 10, 0);
        getBinding().toolBar.ivRight.setImageResource(R.mipmap.ic_yellow_add);
    }
}
