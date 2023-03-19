package com.movieboxpro.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.ReplyDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: PushMessageClickReceiver.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001c\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/receiver/PushMessageClickReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "buildData", "", "env", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PushMessageClickReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (Intrinsics.areEqual(intent == null ? null : intent.getAction(), "Handle_Push_Msg")) {
            String stringExtra = intent.getStringExtra(IjkMediaMeta.IJKM_KEY_TYPE);
            String stringExtra2 = intent.getStringExtra("tid");
            String stringExtra3 = intent.getStringExtra("mid");
            String stringExtra4 = intent.getStringExtra("lid");
            String stringExtra5 = intent.getStringExtra("ticket_id");
            String stringExtra6 = intent.getStringExtra("url");
            if (TextUtils.isEmpty(stringExtra) || stringExtra == null) {
                return;
            }
            switch (stringExtra.hashCode()) {
                case -873960692:
                    if (stringExtra.equals("ticket")) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String format = String.format("https://www.movieboxpro.app/index/order/detail?auth=%s&id=%s", Arrays.copyOf(new Object[]{buildData(""), stringExtra5}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                        SystemUtils.startBrowser(context, format);
                        return;
                    }
                    return;
                case 3714:
                    if (stringExtra.equals("tv")) {
                        TvDetailActivity.startWithFlag(context, stringExtra2);
                        return;
                    }
                    return;
                case 3377875:
                    if (stringExtra.equals("news")) {
                        SystemUtils.startBrowser(context, stringExtra6);
                        return;
                    }
                    return;
                case 104087344:
                    if (stringExtra.equals("movie")) {
                        MovieDetailActivity.startWithFlag(context, stringExtra3);
                        return;
                    }
                    return;
                case 108401386:
                    if (stringExtra.equals("reply")) {
                        String stringExtra7 = intent.getStringExtra("comment_id");
                        String stringExtra8 = intent.getStringExtra("actor_id");
                        String stringExtra9 = intent.getStringExtra("mid");
                        String stringExtra10 = intent.getStringExtra("pid");
                        String stringExtra11 = intent.getStringExtra("comment_type");
                        if (stringExtra11 != null) {
                            int hashCode = stringExtra11.hashCode();
                            if (hashCode == 3714) {
                                if (stringExtra11.equals("tv")) {
                                    ReplyDetailActivity.Companion.startNewTask(context, stringExtra9, 2, stringExtra7);
                                    return;
                                }
                                return;
                            } else if (hashCode == 92645877) {
                                if (stringExtra11.equals("actor")) {
                                    ReplyDetailActivity.Companion.startNewTask(context, stringExtra8, 4, stringExtra7);
                                    return;
                                }
                                return;
                            } else if (hashCode == 104087344) {
                                if (stringExtra11.equals("movie")) {
                                    ReplyDetailActivity.Companion.startNewTask(context, stringExtra9, 1, stringExtra7);
                                    return;
                                }
                                return;
                            } else if (hashCode == 1879474642 && stringExtra11.equals("playlist")) {
                                ReplyDetailActivity.Companion.startNewTask(context, stringExtra10, 3, stringExtra7);
                                return;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    return;
                case 1188288462:
                    if (stringExtra.equals("movielist")) {
                        MovieListDetailActivity.startWithFlag(context, stringExtra4, "", "");
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    private final String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "env", str);
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }
}
