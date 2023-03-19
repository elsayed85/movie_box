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
import androidx.core.content.ContextCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.view.dialog.DialogAction;
/* loaded from: classes3.dex */
public class ReleasedHintDialog extends AppCompatDialog {
    private Context mContext;

    public ReleasedHintDialog(Context context) {
        super(context, R.style.BottomSheetFullScreenDialog);
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
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawable(ContextCompat.getDrawable(this.mContext, 17170445));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.dimAmount = 0.5f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private DialogAction.ActionListener actionListener;
        private int boxType;
        private Context context;
        private boolean favorite;
        private boolean unknown;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setActionListener(DialogAction.ActionListener actionListener) {
            this.actionListener = actionListener;
            return this;
        }

        public Builder setPositiveText(boolean z) {
            this.favorite = z;
            return this;
        }

        public Builder setUnknown(boolean z) {
            this.unknown = z;
            return this;
        }

        public Builder setBoxType(int i) {
            this.boxType = i;
            return this;
        }

        public ReleasedHintDialog create() {
            final ReleasedHintDialog releasedHintDialog = new ReleasedHintDialog(this.context);
            View inflate = LayoutInflater.from(this.context).inflate(R.layout.released_hint_dialog, (ViewGroup) null);
            releasedHintDialog.setContentView(inflate);
            releasedHintDialog.setCancelable(true);
            releasedHintDialog.setCanceledOnTouchOutside(true);
            TextView textView = (TextView) inflate.findViewById(R.id.tvHint);
            TextView textView2 = (TextView) inflate.findViewById(R.id.tvPositive);
            if (this.favorite) {
                textView2.setText("Done");
                if (this.boxType == 1) {
                    textView.setText("You have added the movie to your favorite,\nand will be notified when  update.");
                } else {
                    textView.setText("You have added the TV show to your favorite,\nand will be notified when  update.");
                }
            } else {
                textView2.setText("Add Favorite");
                if (this.boxType == 1) {
                    textView.setText("You can add the movie to favorite,\nyou will get notice when it update.");
                } else {
                    textView.setText("You can add the TV show to favorite,\nyou will get notice when it update.");
                }
            }
            if (this.unknown) {
                ((TextView) inflate.findViewById(R.id.textView)).setText("Unknown");
            }
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.ReleasedHintDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (Builder.this.actionListener != null) {
                        Builder.this.actionListener.onClick();
                        releasedHintDialog.dismiss();
                    }
                }
            });
            return releasedHintDialog;
        }
    }
}
