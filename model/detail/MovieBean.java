package com.movieboxpro.android.model.detail;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.BuildConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.Observable;
import java.util.Locale;
/* loaded from: classes3.dex */
public class MovieBean extends BaseVideoBean {
    public String poster;

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> TV_episode(String str, int i, String str2, String str3) {
        return null;
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getDetailInfo(String str, String str2) {
        return Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", str, str2, "", "");
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> addFavorite(String str, JSONObject jSONObject, boolean z) {
        return Http.getService().Moviecollect(API.BASE_URL, API.Movie.MOVE_COLLECT, App.isLogin() ? App.getUserData().uid_v2 : "", jSONObject, null, z ? "del" : "add", "", "");
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getPath(String str, int i, int i2) {
        String str2;
        String str3;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
            str3 = "";
            str2 = str3;
        } else {
            str2 = string;
            str3 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        return Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", str, "", str3, str2);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getFeedBackType(int i) {
        return Http.getService().Movie_feedback_type(API.BASE_URL, API.Common.MOVIE_FEEDBACL_TYPE, i);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> setFeedBack(int i, int i2, String str, String str2, int i3, String str3, int i4, int i5) {
        APIService service = Http.getService();
        String str4 = API.BASE_URL;
        String str5 = App.isLogin() ? App.getUserData().uid_v2 : "";
        return service.Movie_feedback(str4, API.Common.MOVIE_FEEDBACK, str5, i2, str + "", i, str2, i3, str3, 0, 0);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> set_srt_auto(String str, int i, int i2) {
        return Http.getService().Movie_srt_auto(API.BASE_URL, API.Movie.MOVIE_SRT_AUTO, str, Locale.getDefault().getLanguage(), App.isLogin() ? App.getUserData().uid_v2 : "");
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> Playlists_list(String str) {
        return Http.getService().Playlists_list(API.BASE_URL, API.Common.PLAY_LIST, str, "mine", 0, 0, BuildConfig.VERSION_NAME);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> actorList(String str) {
        return Http.getService().MovieActors(API.BASE_URL, API.Movie.MOVIE_ACTORS, str, 1, 10);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getReviewNum(String str, int i) {
        return Http.getService().getReviewNum(API.BBS_URL, "get_threadlist_videoid_count", str, i, 2);
    }

    @Override // com.movieboxpro.android.model.detail.AbstractVideoBean
    public Observable<String> getReviewNum(String str, int i, boolean z) {
        return Http.getService().getReviewNum(API.BBS_URL, "get_threadlist_videoid_count", str, i, z ? 57 : 2);
    }
}
