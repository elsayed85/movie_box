package com.movieboxpro.android.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.movieboxpro.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: FixedHeightBottomSheetDialog.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0002J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0005H\u0002R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/base/FixedHeightBottomSheetDialog;", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", "context", "Landroid/content/Context;", "theme", "", "fixedHeight", "(Landroid/content/Context;II)V", "getBottomSheetBehavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "Landroid/view/View;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setMaxHeight", "maxHeight", "setPeekHeight", "peekHeight", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FixedHeightBottomSheetDialog extends BottomSheetDialog {
    private final int fixedHeight;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FixedHeightBottomSheetDialog(Context context, int i, int i2) {
        super(context, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.fixedHeight = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setPeekHeight(this.fixedHeight);
        setMaxHeight(this.fixedHeight);
    }

    private final void setPeekHeight(int i) {
        BottomSheetBehavior<View> bottomSheetBehavior;
        if (i > 0 && (bottomSheetBehavior = getBottomSheetBehavior()) != null) {
            bottomSheetBehavior.setPeekHeight(i);
        }
    }

    private final void setMaxHeight(int i) {
        if (i <= 0) {
            return;
        }
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, i);
        }
        Window window2 = getWindow();
        if (window2 == null) {
            return;
        }
        window2.setGravity(80);
    }

    private final BottomSheetBehavior<View> getBottomSheetBehavior() {
        Window window = getWindow();
        View findViewById = window == null ? null : window.findViewById(R.id.design_bottom_sheet);
        if (findViewById == null) {
            return null;
        }
        return BottomSheetBehavior.from(findViewById);
    }
}
