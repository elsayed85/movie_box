package com.movieboxpro.android.view.activity.vlcvideoplayer.listener;

import android.widget.ImageView;
import androidx.fragment.app.FragmentManager;
import androidx.mediarouter.app.MediaRouteButton;
import com.avery.subtitle.model.Subtitle;
import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.SRTModel;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
/* loaded from: classes3.dex */
public interface PlayerController {
    void cancelShowTestSpeed();

    void changeSubtitleBgColor(int i);

    void changeSubtitleColor(int i);

    void clearSubtitle();

    void destroy();

    String getAudioTrackLanguage();

    String getAudioTrackUrl();

    ImageView getCastButton();

    MediaRouteButton getMediaButton();

    MediaPlayerControl getMediaPlayer();

    int getSrtspeed();

    int getSubtitleDelay();

    List<Subtitle> getSubtitlesData();

    void hideCastingHolder();

    void hideOpenSubtitle();

    void hideSubtitleView();

    boolean onBackPressed();

    void resetDefinition(List<MediaQualityInfo> list);

    void saveDelayExtraSubtitleRecord(String str);

    void selectSubtitle(int i, SRTModel.SubTitles subTitles);

    void selectSubtitle(SRTModel.SubTitles subTitles);

    void setCallBack(ControllerCallback controllerCallback);

    void setCastViewGone();

    void setContinueText(String str);

    void setControllerMargin(int i);

    void setNewSubtitle(ArrayList<Subtitle> arrayList);

    void setNextViewGone();

    void setNextViewVisible();

    void setOpenSubtitle(List<ExtrModel> list);

    void setQualityController();

    void setSrt(LinkedHashMap<String, List<SRTModel.SubTitles>> linkedHashMap);

    void setSubtitle(List<SrtPraseModel> list);

    void setTransCodeResult(List<SrtPraseModel> list);

    void setVideoModel(BaseMediaModel baseMediaModel);

    void showCastingHolder(String str);

    void showLocalSubtitle(List<SrtPraseModel> list, List<Subtitle> list2, String str);

    void showMuteHint();

    void showPlaySpeed(float f);

    void showRecommendSkipTime(int i, boolean z, String str, String str2, int i2, int i3, FragmentManager fragmentManager, DialogAction.ActionListener actionListener);

    void showSkipTime(int i, boolean z, String str, String str2, int i2, int i3, FragmentManager fragmentManager);

    void showSubtitleFeedback(String str, List<SubTitleFeedbackModel> list, List<SrtPraseModel> list2, SRTModel.SubTitles subTitles);

    void showTransCodingView(List<SrtPraseModel> list, List<EncodeModel> list2);

    void showTransCodingViewFromViewLocal(List<SrtPraseModel> list, List<EncodeModel> list2, int i);

    void showTranslateSubtitle(List<SrtPraseModel> list);

    void showWillSkipTime(int i, boolean z, String str, String str2, int i2, int i3, FragmentManager fragmentManager);

    void startSRT(List<SrtPraseModel> list);

    void switchScreenOrientation(boolean z);

    void updateSkipSettingStatus();

    void updateTitle(String str);

    void voteFeedbackSuccess(int i);
}
