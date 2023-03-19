package com.movieboxpro.android.view.activity.Video;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.dl7.player.media.IjkPlayerView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class VideoPlayerActivity_ViewBinding implements Unbinder {
    private VideoPlayerActivity target;
    private View view7f090072;

    public VideoPlayerActivity_ViewBinding(VideoPlayerActivity videoPlayerActivity) {
        this(videoPlayerActivity, videoPlayerActivity.getWindow().getDecorView());
    }

    public VideoPlayerActivity_ViewBinding(final VideoPlayerActivity videoPlayerActivity, View view) {
        this.target = videoPlayerActivity;
        videoPlayerActivity.mPlayerView = (IjkPlayerView) Utils.findRequiredViewAsType(view, R.id.player_view, "field 'mPlayerView'", IjkPlayerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.adView_close, "field 'mAdsClose' and method 'onclick'");
        videoPlayerActivity.mAdsClose = (ImageView) Utils.castView(findRequiredView, R.id.adView_close, "field 'mAdsClose'", ImageView.class);
        this.view7f090072 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.Video.VideoPlayerActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                videoPlayerActivity.onclick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        VideoPlayerActivity videoPlayerActivity = this.target;
        if (videoPlayerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        videoPlayerActivity.mPlayerView = null;
        videoPlayerActivity.mAdsClose = null;
        this.view7f090072.setOnClickListener(null);
        this.view7f090072 = null;
    }
}
