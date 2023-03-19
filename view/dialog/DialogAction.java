package com.movieboxpro.android.view.dialog;
/* loaded from: classes3.dex */
public class DialogAction {
    private ActionListener actionListener;

    /* loaded from: classes3.dex */
    public interface ActionListener {
        void onClick();
    }

    /* loaded from: classes3.dex */
    public interface EditActionListener {
        void onClick(String str);
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }
}
