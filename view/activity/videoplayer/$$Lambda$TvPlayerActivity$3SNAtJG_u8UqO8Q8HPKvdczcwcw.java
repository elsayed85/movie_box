package com.movieboxpro.android.view.activity.videoplayer;

import com.movieboxpro.android.event.OnPlayFinishEvent;
import io.reactivex.functions.BiFunction;
import org.greenrobot.eventbus.EventBus;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.activity.videoplayer.-$$Lambda$TvPlayerActivity$3SNAtJG_u8UqO8Q8HPKvdczcwcw  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$TvPlayerActivity$3SNAtJG_u8UqO8Q8HPKvdczcwcw implements BiFunction {
    public static final /* synthetic */ $$Lambda$TvPlayerActivity$3SNAtJG_u8UqO8Q8HPKvdczcwcw INSTANCE = new $$Lambda$TvPlayerActivity$3SNAtJG_u8UqO8Q8HPKvdczcwcw();

    private /* synthetic */ $$Lambda$TvPlayerActivity$3SNAtJG_u8UqO8Q8HPKvdczcwcw() {
    }

    @Override // io.reactivex.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        return EventBus.getDefault().post(new OnPlayFinishEvent());
    }
}
