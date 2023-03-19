package com.movieboxpro.android.view.listener;

import android.view.View;
/* loaded from: classes.dex */
public interface ITitleController {
    void clearTitleRight();

    void clearTitleRightNew();

    void setTitle(String str);

    void setTitle(String str, View.OnClickListener onClickListener);

    void setTitleBack(boolean z);

    void setTitleBackground(int i);

    void setTitleBar(boolean z);

    void setTitleLeftIcon(int i, View.OnClickListener onClickListener);

    void setTitleLeftText(String str, View.OnClickListener onClickListener);

    void setTitleRightIcon(int i, View.OnClickListener onClickListener);

    void setTitleRightIcon(int i, boolean z, View.OnClickListener onClickListener);

    void setTitleRightText(String str, View.OnClickListener onClickListener);

    void setTitleRightText(String str, boolean z, View.OnClickListener onClickListener);
}
