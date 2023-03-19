package com.movieboxpro.android.utils.databinding;

import androidx.databinding.ViewDataBinding;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.hi.dhl.jdatabinding.ext.LifecycleExtKt;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
/* compiled from: DataBindingFragment.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\bJ\b\u0010\u001a\u001a\u00020\u001bH\u0002J\"\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00020\u00042\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001fH\u0096\u0002¢\u0006\u0002\u0010 R\u0019\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006!"}, d2 = {"Lcom/movieboxpro/android/utils/databinding/FragmentBindingDelegate;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "Lkotlin/properties/ReadOnlyProperty;", "Landroidx/fragment/app/Fragment;", "classes", "Ljava/lang/Class;", "fragment", "(Ljava/lang/Class;Landroidx/fragment/app/Fragment;)V", "bindView", "Ljava/lang/reflect/Method;", "kotlin.jvm.PlatformType", "getBindView", "()Ljava/lang/reflect/Method;", "lifecycle", "Landroidx/lifecycle/LifecycleOwner;", "getLifecycle", "()Landroidx/lifecycle/LifecycleOwner;", "setLifecycle", "(Landroidx/lifecycle/LifecycleOwner;)V", "viewBinding", "getViewBinding", "()Landroidx/databinding/ViewDataBinding;", "setViewBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "destroyed", "", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Landroidx/fragment/app/Fragment;Lkotlin/reflect/KProperty;)Landroidx/databinding/ViewDataBinding;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FragmentBindingDelegate<T extends ViewDataBinding> implements ReadOnlyProperty<Fragment, T> {
    private final Method bindView;
    private LifecycleOwner lifecycle;
    private T viewBinding;

    public FragmentBindingDelegate(Class<T> classes, Fragment fragment) {
        Intrinsics.checkNotNullParameter(classes, "classes");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.bindView = ReflectExtKt.bindMethod(classes);
        this.lifecycle = fragment;
        Lifecycle lifecycle = fragment.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "fragment.lifecycle");
        LifecycleExtKt.addObserver(lifecycle, new AnonymousClass1(this));
    }

    @Override // kotlin.properties.ReadOnlyProperty
    public /* bridge */ /* synthetic */ Object getValue(Fragment fragment, KProperty kProperty) {
        return getValue2(fragment, (KProperty<?>) kProperty);
    }

    public final T getViewBinding() {
        return this.viewBinding;
    }

    public final void setViewBinding(T t) {
        this.viewBinding = t;
    }

    public final Method getBindView() {
        return this.bindView;
    }

    public final LifecycleOwner getLifecycle() {
        return this.lifecycle;
    }

    public final void setLifecycle(LifecycleOwner lifecycleOwner) {
        this.lifecycle = lifecycleOwner;
    }

    /* compiled from: DataBindingFragment.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/databinding/ViewDataBinding;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.movieboxpro.android.utils.databinding.FragmentBindingDelegate$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ FragmentBindingDelegate<T> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(FragmentBindingDelegate<T> fragmentBindingDelegate) {
            super(0);
            this.this$0 = fragmentBindingDelegate;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.this$0.destroyed();
        }
    }

    /* renamed from: getValue  reason: avoid collision after fix types in other method */
    public T getValue2(Fragment thisRef, KProperty<?> property) {
        Intrinsics.checkNotNullParameter(thisRef, "thisRef");
        Intrinsics.checkNotNullParameter(property, "property");
        T t = this.viewBinding;
        if (t == null) {
            Object invoke = getBindView().invoke(null, thisRef.getView());
            if (invoke == null) {
                throw new NullPointerException("null cannot be cast to non-null type T of com.movieboxpro.android.utils.databinding.FragmentBindingDelegate.getValue$lambda-2");
            }
            T t2 = (T) invoke;
            t2.setLifecycleOwner(getLifecycle());
            setViewBinding(t2);
            return t2;
        }
        return t;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void destroyed() {
        this.viewBinding = null;
    }
}
