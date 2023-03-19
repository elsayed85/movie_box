package com.movieboxpro.android.view.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: ShowSubtitleDialog.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u000e\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000b0\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ShowSubtitleDialog;", "Landroidx/appcompat/app/AppCompatDialog;", "activity", "Landroid/app/Activity;", MediaTrack.ROLE_SUBTITLE, "", "(Landroid/app/Activity;Ljava/lang/String;)V", "getActivity", "()Landroid/app/Activity;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "initDialog", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setDownloadListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ShowSubtitleDialog extends AppCompatDialog {
    private final Activity activity;
    private BaseQuickAdapter<String, BaseViewHolder> adapter;
    private DialogAction.ActionListener listener;
    private final String subtitle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShowSubtitleDialog(Activity activity, String subtitle) {
        super(activity, R.style.BottomSheetFullScreenDialog);
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        this.activity = activity;
        this.subtitle = subtitle;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog();
        initView();
    }

    public final void setDownloadListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void initView() {
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = null;
        setContentView(LayoutInflater.from(getContext()).inflate(R.layout.show_subtitle_dialog, (ViewGroup) null));
        final ArrayList arrayList = new ArrayList(CollectionsKt.toList(StringsKt.split$default((CharSequence) this.subtitle, new String[]{ShellUtil.COMMAND_LINE_END}, false, 0, 6, (Object) null)));
        this.adapter = new BaseQuickAdapter<String, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.dialog.ShowSubtitleDialog$initView$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, String item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                holder.setText(R.id.textView, item);
            }
        };
        ((RecyclerView) findViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = this.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        ((TextView) findViewById(R.id.tvDownload)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ShowSubtitleDialog$MzhFBkuKWT01ZpmNiyrEqhyPvOA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowSubtitleDialog.m1110initView$lambda0(ShowSubtitleDialog.this, view);
            }
        });
        ((TextView) findViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ShowSubtitleDialog$ZKImtfe1sxmaNDYeRN4OPol3FKw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShowSubtitleDialog.m1111initView$lambda1(ShowSubtitleDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-0  reason: not valid java name */
    public static final void m1110initView$lambda0(ShowSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DialogAction.ActionListener actionListener = this$0.listener;
        if (actionListener == null) {
            return;
        }
        actionListener.onClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initView$lambda-1  reason: not valid java name */
    public static final void m1111initView$lambda1(ShowSubtitleDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    private final void initDialog() {
        View decorView;
        Window window = getWindow();
        if (window != null && (decorView = window.getDecorView()) != null) {
            decorView.setPadding(0, 0, 0, 0);
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(ContextCompat.getDrawable(this.activity, 17170445));
        }
        Window window3 = getWindow();
        WindowManager.LayoutParams attributes = window3 == null ? null : window3.getAttributes();
        if (attributes != null) {
            attributes.gravity = 80;
        }
        if (attributes != null) {
            attributes.dimAmount = 0.0f;
        }
        if (attributes != null) {
            attributes.width = -1;
        }
        if (attributes != null) {
            attributes.height = -2;
        }
        Window window4 = getWindow();
        if (window4 == null) {
            return;
        }
        window4.setAttributes(attributes);
    }
}
