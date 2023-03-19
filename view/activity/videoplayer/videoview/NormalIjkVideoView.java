package com.movieboxpro.android.view.activity.videoplayer.videoview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.just.agentweb.DefaultWebClient;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.activity.exoplayer.controller.BaseVideoController;
import com.movieboxpro.android.view.activity.videoplayer.player.IjkVideoView;
import com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter;
import com.movieboxpro.android.view.activity.vlcvideoplayer.listener.CallBack;
import java.util.List;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;
/* loaded from: classes3.dex */
public class NormalIjkVideoView extends IjkVideoView implements NormalPlayerPresenter {
    public CallBack mCallBack;
    private int mCurrentDefinition;
    private List<MediaQualityInfo> mDefinitionMap;
    private String previewUrl;

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.BaseIjkVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public IMedia.AudioTrack[] getTrackInfo() {
        return null;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.IjkVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setExoVideoController(BaseVideoController baseVideoController) {
    }

    public NormalIjkVideoView(Context context) {
        super(context);
        this.mCurrentDefinition = 0;
    }

    public NormalIjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentDefinition = 0;
    }

    public NormalIjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentDefinition = 0;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public List<MediaQualityInfo> getDefinitionData() {
        return this.mDefinitionMap;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void switchDefinition(int i) {
        String path = this.mDefinitionMap.get(i).getPath();
        if (i == this.mCurrentDefinition) {
            return;
        }
        this.mCurrentUrl = path;
        setUrl();
        addDisplay();
        if (TextUtils.isEmpty(this.mCurrentUrl) && this.mVideoController != null) {
            this.mVideoController.showError("Video url is empty,you can try switch quality or report to us");
            return;
        }
        startPrepare(true);
        this.mCurrentDefinition = i;
        if (this.mCallBack == null || i > this.mDefinitionMap.size() || this.mDefinitionMap.size() == 0) {
            return;
        }
        this.mCallBack.setQuality(this.mDefinitionMap.get(i));
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void changeDefinition(int i) {
        this.mCurrentDefinition = i;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void resetDefinitionVideosAndPlay(List<MediaQualityInfo> list, PlayRecode playRecode, int i) {
        int size = list.size() - 1;
        if (size >= 0 && size < list.size()) {
            this.previewUrl = list.get(size).getPath();
        }
        stopPlayback();
        addDisplay();
        setDefinitionVideos(list, playRecode, i);
        startPrepare(true);
        setKeepScreenOn(true);
        requestFocusAudio();
        if (this.mCallBack == null || this.mCurrentDefinition > this.mDefinitionMap.size() || this.mDefinitionMap.size() == 0) {
            return;
        }
        this.mCallBack.setQuality(this.mDefinitionMap.get(this.mCurrentDefinition));
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void switchDefaultDefinition(boolean z) {
        setPlayerConfig(new PlayerConfig.Builder().enableMediaCodec(z).autoRotate().build());
        addDisplay();
        startPrepare(true);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public int getDefinition() {
        return this.mCurrentDefinition;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public MediaQualityInfo getCurrDefinitionItem() {
        int i = this.mCurrentDefinition;
        if (i < 0 || i >= this.mDefinitionMap.size()) {
            return null;
        }
        return this.mDefinitionMap.get(this.mCurrentDefinition);
    }

    public void setPlayUrl(String str) {
        this.mCurrentUrl2 = str;
        this.mCurrentUrl = str;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void setDefinitionVideos(List<MediaQualityInfo> list, PlayRecode playRecode, int i) {
        int i2;
        CallBack callBack;
        if (list == null) {
            return;
        }
        int size = list.size() - 1;
        if (size >= 0 && size < list.size()) {
            this.previewUrl = list.get(size).getPath();
        }
        this.mDefinitionMap = list;
        if (i != 0) {
            seekTo(i * 1000);
            if (playRecode != null && playRecode.getMovieId() != null) {
                this.mCurrentDefinition = playRecode.getQuality();
            }
            int i3 = this.mCurrentDefinition;
            if (i3 == -1 || i3 == 0 || i3 >= list.size()) {
                this.mCurrentDefinition = choose();
            }
        } else if (playRecode != null) {
            int choose = (playRecode.getQuality() >= list.size() || playRecode.getQuality() < 0) ? choose() : playRecode.getQuality();
            this.mCurrentDefinition = choose;
            if (choose > 0 && choose < this.mDefinitionMap.size() && TextUtils.isEmpty(this.mDefinitionMap.get(this.mCurrentDefinition).getPath())) {
                this.mCurrentDefinition = choose();
            }
            restChoose(this.mCurrentDefinition);
            seekTo(playRecode.getStart_time());
        } else {
            seekTo(0L);
        }
        this.mCurrentUrl = getValueFromLinkedMap(list, this.mCurrentDefinition);
        if (TextUtils.isEmpty(this.mCurrentUrl)) {
            int choose2 = choose();
            this.mCurrentDefinition = choose2;
            this.mCurrentUrl = getValueFromLinkedMap(list, choose2);
        }
        if (SettingManager.INSTANCE.isAutoSelectPlayQuality()) {
            int i4 = 0;
            while (true) {
                if (i4 >= list.size()) {
                    break;
                }
                MediaQualityInfo mediaQualityInfo = list.get(i4);
                if (!"4K".equals(mediaQualityInfo.getReal_quality()) && !TextUtils.isEmpty(mediaQualityInfo.getPath()) && mediaQualityInfo.getOriginal() != 1) {
                    this.mCurrentDefinition = i4;
                    this.mCurrentUrl = getValueFromLinkedMap(list, i4);
                    break;
                }
                i4++;
            }
        }
        if (list.size() > 0 && !list.get(0).getPath().startsWith("http") && !TextUtils.isEmpty(list.get(0).getPath())) {
            this.mCurrentDefinition = 0;
            this.mCurrentUrl = getValueFromLinkedMap(list, 0);
        }
        this.mCurrentUrl2 = this.mCurrentUrl;
        setUrl();
        if (this.mCurrentDefinition >= list.size() || (i2 = this.mCurrentDefinition) < 0 || (callBack = this.mCallBack) == null) {
            return;
        }
        callBack.setQuality(list.get(i2));
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void setErrorVideos() {
        String error;
        if (this.mCurrentDefinition == -1 || this.mMediaPlayer == null || (error = this.mDefinitionMap.get(this.mCurrentDefinition).getError()) == null) {
            return;
        }
        this.mCurrentUrl = error;
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
    }

    public void setOtherPath() {
        setUrl();
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public String getUrl() {
        return this.mCurrentUrl;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public String getPreviewUrl() {
        return this.previewUrl;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void resetPlayUrl(String str) {
        this.mCurrentUrl2 = str;
        this.mCurrentUrl = str;
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
    }

    public void setUrl() {
        if (this.mCurrentUrl == null) {
            return;
        }
        if ("0".equalsIgnoreCase(PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "")) && (this.mCurrentUrl.startsWith(DefaultWebClient.HTTP_SCHEME) || this.mCurrentUrl.startsWith(DefaultWebClient.HTTPS_SCHEME))) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "Error");
            if (!string.equals("Error")) {
                Log.d("tag", "截取链接1 : " + this.mCurrentUrl);
                String[] split = this.mCurrentUrl.split("/");
                StringBuilder sb = new StringBuilder();
                if (split.length > 2) {
                    split[2] = string;
                    for (int i = 2; i < split.length; i++) {
                        if (i != split.length - 1) {
                            sb.append(split[i]);
                            sb.append("/");
                        } else {
                            sb.append(split[i]);
                        }
                    }
                    this.mCurrentUrl = sb.toString();
                }
            } else {
                PrefsUtils.getInstance().remove(Constant.Prefs.NETWORK_STATE);
            }
        }
        Log.d("NormalIjkVideoView", "播放链接 : " + this.mCurrentUrl + "groupId:" + PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, ""));
    }

    public int choose() {
        List<MediaQualityInfo> list = this.mDefinitionMap;
        if (list == null || list.size() == 0) {
            return 0;
        }
        for (int i = 0; i < this.mDefinitionMap.size(); i++) {
            if (this.mDefinitionMap.get(i).getPath() != null && !TextUtils.isEmpty(this.mDefinitionMap.get(i).getPath())) {
                boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_ORG_QUALITY, false);
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.LAST_SELECT_QUALITY, "");
                int i2 = PrefsUtils.getInstance().getInt(Constant.Prefs.IS_LAST_ORIGIN, 0);
                if (TextUtils.isEmpty(string)) {
                    if (z) {
                        if (i2 == 1) {
                            this.mDefinitionMap.get(i).setSelect(true);
                            return i;
                        } else if (this.mDefinitionMap.get(i).getOriginal() != 1) {
                            this.mDefinitionMap.get(i).setSelect(true);
                            return i;
                        }
                    } else if (this.mDefinitionMap.get(i).getOriginal() != 1) {
                        this.mDefinitionMap.get(i).setSelect(true);
                        return i;
                    }
                } else if (string.equals(this.mDefinitionMap.get(i).getReal_quality())) {
                    this.mDefinitionMap.get(i).setSelect(true);
                    return i;
                }
            }
        }
        for (int i3 = 0; i3 < this.mDefinitionMap.size(); i3++) {
            if (!TextUtils.isEmpty(this.mDefinitionMap.get(i3).getPath())) {
                this.mDefinitionMap.get(i3).setSelect(true);
                return i3;
            }
        }
        return this.mDefinitionMap.size() - 1;
    }

    public void restChoose(int i) {
        for (int i2 = 0; i2 < this.mDefinitionMap.size(); i2++) {
            if (i == i2) {
                this.mDefinitionMap.get(i2).setSelect(true);
            } else {
                this.mDefinitionMap.get(i2).setSelect(false);
            }
        }
    }

    public static String getValueFromLinkedMap(List<MediaQualityInfo> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i).getPath();
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.BaseIjkVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public IjkTrackInfo[] getIjkTrackInfo() {
        if (this.mVideoController != null) {
            this.mVideoController.getTrackInfo(super.getIjkTrackInfo());
        }
        return super.getIjkTrackInfo();
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.IjkVideoView, com.movieboxpro.android.view.activity.videoplayer.player.BaseIjkVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void release() {
        super.release();
        this.mCallBack = null;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.BaseIjkVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setTitle(String str) {
        super.setTitle(str);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.IjkVideoView, com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onSeekComplete(long j, long j2) {
        CallBack callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onSeekComplete(j, j2);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.player.BaseIjkVideoView, com.movieboxpro.android.view.activity.vlcvideoplayer.controller.MediaPlayerControl
    public void setTrackInfo(int i) {
        super.setTrackInfo(i);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }
}
