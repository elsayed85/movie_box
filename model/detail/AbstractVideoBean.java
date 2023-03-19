package com.movieboxpro.android.model.detail;

import com.alibaba.fastjson.JSONObject;
import io.reactivex.Observable;
/* loaded from: classes3.dex */
public interface AbstractVideoBean {
    Observable<String> Playlists_list(String str);

    Observable<String> TV_episode(String str, int i, String str2, String str3);

    Observable<String> actorList(String str);

    Observable<String> addFavorite(String str, JSONObject jSONObject, boolean z);

    Observable<String> getDetailInfo(String str, String str2);

    Observable<String> getFeedBackType(int i);

    Observable<String> getPath(String str, int i, int i2);

    Observable<String> getReviewNum(String str, int i);

    Observable<String> getReviewNum(String str, int i, boolean z);

    Observable<String> setFeedBack(int i, int i2, String str, String str2, int i3, String str3, int i4, int i5);

    Observable<String> set_srt_auto(String str, int i, int i2);
}
