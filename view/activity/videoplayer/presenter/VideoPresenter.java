package com.movieboxpro.android.view.activity.videoplayer.presenter;

import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.SRTModel;
import com.movieboxpro.android.db.entity.PlayRecode;
/* loaded from: classes.dex */
public interface VideoPresenter {
    void _geiPrama();

    void _initAds();

    void _initChromCast();

    void _initQuality();

    PlayRecode getInDao();

    void getSrt(MediaQualityInfo mediaQualityInfo);

    void get_Movie_select(int i, int i2, String str);

    void get_srt_select(String str, String str2);

    void saveInDao(int i, int i2);

    void setSrts(SRTModel.SubTitles subTitles);
}
