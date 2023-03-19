package com.movieboxpro.android.view.activity.choose;

import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.common.Srt;
import com.movieboxpro.android.view.listener.IViewController;
import java.util.List;
/* loaded from: classes3.dex */
public interface IChoose extends IViewController {
    void getSrt(List<Srt> list, String str, int i);

    void hideSwipeView();

    void setPath(BaseMediaModel baseMediaModel);

    void showSwipeView();
}
