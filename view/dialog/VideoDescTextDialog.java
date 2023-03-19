package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.VideoDescTextDialog;
import com.movieboxpro.android.view.widget.WrapContentScrollView;
/* loaded from: classes3.dex */
public class VideoDescTextDialog extends AppCompatDialog {
    private Context mContext;

    public VideoDescTextDialog(Context context) {
        super(context, R.style.DialogStyle);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog();
    }

    private void initDialog() {
        Window window = getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        double d = this.mContext.getResources().getDisplayMetrics().widthPixels;
        Double.isNaN(d);
        attributes.width = (int) (d * 0.8d);
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private String content;
        private Context context;
        private DialogAction.ActionListener negativeActionListener;
        private String negativeText = "Cancel";

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setContent(String str) {
            this.content = str;
            return this;
        }

        public Builder setNegativeText(String str) {
            this.negativeText = str;
            return this;
        }

        public Builder setActionListener(DialogAction.ActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public Builder setNegativeActionListener(DialogAction.ActionListener actionListener) {
            this.negativeActionListener = actionListener;
            return this;
        }

        public VideoDescTextDialog create() {
            final VideoDescTextDialog videoDescTextDialog = new VideoDescTextDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.video_desc_layout, (ViewGroup) null);
            videoDescTextDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            double d = this.context.getResources().getDisplayMetrics().heightPixels;
            Double.isNaN(d);
            ((WrapContentScrollView) inflate.findViewById(R.id.scrollView)).setMaxHeight((int) (d * 0.85d));
            ((TextView) inflate.findViewById(R.id.tvContent)).setText(this.content);
            ((TextView) inflate.findViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$VideoDescTextDialog$Builder$O-MVAfvEuUp2rXqu7ZxJGR9aO3M
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VideoDescTextDialog.Builder.this.lambda$create$0$VideoDescTextDialog$Builder(videoDescTextDialog, view);
                }
            });
            return videoDescTextDialog;
        }

        public /* synthetic */ void lambda$create$0$VideoDescTextDialog$Builder(VideoDescTextDialog videoDescTextDialog, View view) {
            DialogAction.ActionListener actionListener = this.actionListener;
            if (actionListener != null) {
                actionListener.onClick();
            }
            videoDescTextDialog.dismiss();
        }
    }
}
