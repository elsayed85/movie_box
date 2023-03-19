package com.movieboxpro.android.http;

import com.movieboxpro.android.model.DownloadInfo;
import io.reactivex.Observable;
import kotlin.Metadata;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
/* compiled from: APIService2.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H'¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/http/APIService2;", "", DownloadInfo.DOWNLOAD, "Lio/reactivex/Observable;", "Lokhttp3/ResponseBody;", "url", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface APIService2 {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String str);
}
