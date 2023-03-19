package com.movieboxpro.android.model;

import android.app.Activity;
import io.reactivex.Observable;
/* loaded from: classes3.dex */
public interface MediaFunction {
    int ChoosePlayer();

    void addDonwload(BaseMediaModel baseMediaModel);

    int getBoxType();

    int getEpisode();

    String getId();

    int getSeason();

    Observable<String> getSrt();

    String getTid();

    Observable<String> getUrl();

    boolean saveInDao(int i, Activity activity);

    void startService(String str, Activity activity);
}
