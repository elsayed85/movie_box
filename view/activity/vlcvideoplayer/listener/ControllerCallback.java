package com.movieboxpro.android.view.activity.vlcvideoplayer.listener;

import androidx.core.util.Pair;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.model.SRTModel;
import java.io.File;
import java.util.List;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes3.dex */
public interface ControllerCallback {
    void Floatstart();

    void addFragment(Fragment fragment, int i);

    void castVideo();

    void changeColor();

    void changePlayerEngine(int i);

    void changeSubtitleBgColor();

    void checkSubtitleCode(int i, SRTModel.SubTitles subTitles);

    void closeFullscreen();

    void desSelectSubtitle(String str);

    void downOpenSubtitle(ExtrModel extrModel);

    void downloadAndTransCodeSubtitle(String str, String str2, String str3, String str4);

    void finishPlay();

    int getBoxType();

    int getEpisode();

    String getFid();

    String getId();

    IjkMediaPlayer getMediaPlayer();

    void getNetState();

    boolean getOpenSubtitle();

    String getPlayUrl();

    String getPreviewUrl();

    int getSeason();

    String getServerSubtitleContent();

    Pair<String, String> getSubtitleContentLang(int i);

    String getTitle();

    void hideFragment(Fragment fragment);

    boolean isLocalFile();

    void onIjkTrackComplete(List<IjkTrackInfo> list);

    void onNextEpisode();

    void onSelectedExtraSubtitle();

    void onSelectedServiceSubtitle();

    void onShowTrackHintDialog();

    void onTrackComplete(List<IMedia.AudioTrack> list);

    void onVideoPlayComplete();

    void onVideoPlaying();

    void openFullscreen();

    void playError();

    void popPlayer();

    void redownOpenSubtitle(ExtrModel extrModel);

    void requestSubtitleFeedbackVote(String str, SRTModel.SubTitles subTitles);

    void saveRecord();

    void setSrt(SRTModel.SubTitles subTitles);

    boolean showAlert();

    void showFragment(Fragment fragment);

    void showFragmentDialog(DialogFragment dialogFragment);

    void subtitleFileSelected(File file);

    void switchQuality(MediaQualityInfo mediaQualityInfo);

    void transCode(String str, boolean z);

    void uploadSrtDelayInfo(String str, int i);

    void viewLocalSubtitleFile(File file, int i);

    void voteSubtitleFeedback(int i, String str);
}
