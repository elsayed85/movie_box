package com.movieboxpro.android.view.activity.videoplayer.presenter;

import com.dueeeke.model.MediaQualityInfo;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack;
import java.util.List;
/* loaded from: classes3.dex */
public interface NormalPlayerPresenter extends MediaPlayerControl {
    void changeDefinition(int i);

    MediaQualityInfo getCurrDefinitionItem();

    int getDefinition();

    List<MediaQualityInfo> getDefinitionData();

    String getPreviewUrl();

    String getUrl();

    void resetDefinitionVideosAndPlay(List<MediaQualityInfo> list, PlayRecode playRecode, int i);

    void resetPlayUrl(String str);

    void setCallBack(CallBack callBack);

    void setDefinitionVideos(List<MediaQualityInfo> list, PlayRecode playRecode, int i);

    void setErrorVideos();

    void switchDefaultDefinition(boolean z);

    void switchDefinition(int i);
}
