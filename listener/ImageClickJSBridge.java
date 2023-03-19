package com.movieboxpro.android.listener;

import android.webkit.JavascriptInterface;
/* loaded from: classes3.dex */
public class ImageClickJSBridge {
    private OnImageClickListener listener;

    /* loaded from: classes3.dex */
    public interface OnImageClickListener {
        void onAvatarClick(String str);

        void onImageClick(String[] strArr, String str);

        void onReviewClick(String str);
    }

    public void setListener(OnImageClickListener onImageClickListener) {
        this.listener = onImageClickListener;
    }

    @JavascriptInterface
    public void openImage(String[] strArr, String str) {
        OnImageClickListener onImageClickListener = this.listener;
        if (onImageClickListener != null) {
            onImageClickListener.onImageClick(strArr, str);
        }
    }

    @JavascriptInterface
    public void openAvatar(String str) {
        OnImageClickListener onImageClickListener = this.listener;
        if (onImageClickListener != null) {
            onImageClickListener.onAvatarClick(str);
        }
    }

    @JavascriptInterface
    public void reviewClick(String str) {
        OnImageClickListener onImageClickListener = this.listener;
        if (onImageClickListener != null) {
            onImageClickListener.onReviewClick(str);
        }
    }
}
