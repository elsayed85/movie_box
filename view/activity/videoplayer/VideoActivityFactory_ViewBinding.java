package com.movieboxpro.android.view.activity.videoplayer;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class VideoActivityFactory_ViewBinding implements Unbinder {
    private VideoActivityFactory target;

    public VideoActivityFactory_ViewBinding(VideoActivityFactory videoActivityFactory) {
        this(videoActivityFactory, videoActivityFactory.getWindow().getDecorView());
    }

    public VideoActivityFactory_ViewBinding(VideoActivityFactory videoActivityFactory, View view) {
        this.target = videoActivityFactory;
        videoActivityFactory.playerContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.player_container, "field 'playerContainer'", FrameLayout.class);
        videoActivityFactory.flPreview = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flPreview, "field 'flPreview'", FrameLayout.class);
        videoActivityFactory.flContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.flContainer, "field 'flContainer'", FrameLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        VideoActivityFactory videoActivityFactory = this.target;
        if (videoActivityFactory == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        videoActivityFactory.playerContainer = null;
        videoActivityFactory.flPreview = null;
        videoActivityFactory.flContainer = null;
    }
}
