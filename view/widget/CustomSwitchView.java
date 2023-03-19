package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CustomSwitchView.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0012B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/widget/CustomSwitchView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "checked", "", "imageView", "Landroid/widget/ImageView;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/widget/CustomSwitchView$OnCheckListener;", "setCheckListener", "", "setChecked", "OnCheckListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomSwitchView extends FrameLayout {
    public Map<Integer, View> _$_findViewCache;
    private boolean checked;
    private ImageView imageView;
    private OnCheckListener listener;

    /* compiled from: CustomSwitchView.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/view/widget/CustomSwitchView$OnCheckListener;", "", "onCheckChangeListener", "", "checked", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnCheckListener {
        void onCheckChangeListener(boolean z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomSwitchView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CustomSwitchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomSwitchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        ImageView imageView = new ImageView(context);
        this.imageView = imageView;
        imageView.setLayoutParams(new FrameLayout.LayoutParams(CommonExtKt.dp2Px(44), CommonExtKt.dp2Px(24)));
        this.imageView.setImageResource(R.mipmap.ic_switch_unchecked);
        addView(this.imageView);
        this.imageView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$CustomSwitchView$2BEbxgyDV_MBjjZXpT950vWkVRc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomSwitchView.m1426_init_$lambda0(CustomSwitchView.this, view);
            }
        });
    }

    public /* synthetic */ CustomSwitchView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final void setCheckListener(OnCheckListener onCheckListener) {
        this.listener = onCheckListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-0  reason: not valid java name */
    public static final void m1426_init_$lambda0(CustomSwitchView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setChecked(!this$0.checked);
        OnCheckListener onCheckListener = this$0.listener;
        if (onCheckListener == null) {
            return;
        }
        onCheckListener.onCheckChangeListener(this$0.checked);
    }

    public final void setChecked(boolean z) {
        this.checked = z;
        if (z) {
            this.imageView.setImageResource(R.mipmap.ic_switch_checked);
        } else {
            this.imageView.setImageResource(R.mipmap.ic_switch_unchecked);
        }
    }
}
