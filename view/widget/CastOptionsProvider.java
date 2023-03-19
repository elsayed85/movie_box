package com.movieboxpro.android.view.widget;

import android.content.Context;
import com.google.android.exoplayer2.C;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.common.images.WebImage;
import com.movieboxpro.android.view.activity.Video.ExpandedControlsActivity;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes3.dex */
public class CastOptionsProvider implements OptionsProvider {
    public static final String CUSTOM_NAMESPACE = "urn:x-cast:custom_namespace";

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public List<SessionProvider> getAdditionalSessionProviders(Context context) {
        return null;
    }

    @Override // com.google.android.gms.cast.framework.OptionsProvider
    public CastOptions getCastOptions(Context context) {
        return new CastOptions.Builder().setReceiverApplicationId("D4A1B03F").setCastMediaOptions(new CastMediaOptions.Builder().setImagePicker(new ImagePickerImpl()).setNotificationOptions(new NotificationOptions.Builder().setActions(Arrays.asList(MediaIntentReceiver.ACTION_REWIND, MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_FORWARD, MediaIntentReceiver.ACTION_SKIP_PREV, MediaIntentReceiver.ACTION_SKIP_NEXT, MediaIntentReceiver.ACTION_STOP_CASTING), new int[]{1, 3}).setTargetActivityClassName(ExpandedControlsActivity.class.getName()).setSkipStepMs(C.DEFAULT_SEEK_FORWARD_INCREMENT_MS).build()).setExpandedControllerActivityClassName(ExpandedControlsActivity.class.getName()).build()).build();
    }

    /* loaded from: classes3.dex */
    private static class ImagePickerImpl extends ImagePicker {
        private ImagePickerImpl() {
        }

        @Override // com.google.android.gms.cast.framework.media.ImagePicker
        public WebImage onPickImage(MediaMetadata mediaMetadata, int i) {
            if (mediaMetadata == null || !mediaMetadata.hasImages()) {
                return null;
            }
            List<WebImage> images = mediaMetadata.getImages();
            if (images.size() == 1) {
                return images.get(0);
            }
            if (i == 0) {
                return images.get(0);
            }
            return images.get(1);
        }
    }
}
