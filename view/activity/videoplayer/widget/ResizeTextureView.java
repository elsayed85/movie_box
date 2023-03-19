package com.movieboxpro.android.view.activity.videoplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;
/* loaded from: classes3.dex */
public class ResizeTextureView extends TextureView {
    private int mVideoHeight;
    private int mVideoWidth;
    private int screenType;

    public ResizeTextureView(Context context) {
        super(context);
    }

    public ResizeTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void setScreenScale(int i) {
        this.screenType = i;
        requestLayout();
    }

    private int getMeasureHeight(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (getRotation() == 90.0f || getRotation() == 270.0f) {
            int i3 = i + i2;
            i2 = i3 - i2;
            i = i3 - i2;
        }
        getDefaultSize(this.mVideoWidth, i);
        getDefaultSize(this.mVideoHeight, i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            int i4 = this.mVideoWidth;
            int i5 = i4 * size2;
            int i6 = this.mVideoHeight;
            if (i5 >= size * i6) {
                return i4 * size2 > size * i6 ? (size * i6) / i4 : size2;
            }
            int i7 = (i4 * size2) / i6;
            return size2;
        } else if (mode == 1073741824) {
            int i8 = (size * this.mVideoHeight) / this.mVideoWidth;
            return (mode2 != Integer.MIN_VALUE || i8 <= size2) ? i8 : size2;
        } else if (mode2 == 1073741824) {
            int i9 = (this.mVideoWidth * size2) / this.mVideoHeight;
            return size2;
        } else {
            int i10 = this.mVideoWidth;
            int i11 = this.mVideoHeight;
            if (mode2 != Integer.MIN_VALUE || i11 <= size2) {
                size2 = i11;
            } else {
                i10 = (i10 * size2) / i11;
            }
            return (mode != Integer.MIN_VALUE || i10 <= size) ? size2 : (size * this.mVideoHeight) / this.mVideoWidth;
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        if (getRotation() == 90.0f || getRotation() == 270.0f) {
            int i5 = i + i2;
            i2 = i5 - i2;
            i = i5 - i2;
        }
        int defaultSize = getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = getDefaultSize(this.mVideoHeight, i2);
        switch (this.screenType) {
            case 1:
                i2 = (defaultSize / 16) * 9;
                if (defaultSize2 <= i2) {
                    i = (defaultSize2 / 9) * 16;
                    i2 = defaultSize2;
                    break;
                }
                i = defaultSize;
                break;
            case 2:
                i2 = (defaultSize / 4) * 3;
                if (defaultSize2 <= i2) {
                    i = (defaultSize2 / 3) * 4;
                    i2 = defaultSize2;
                    break;
                }
                i = defaultSize;
                break;
            case 3:
                break;
            case 4:
                i = this.mVideoWidth;
                i2 = this.mVideoHeight;
                break;
            case 5:
                int i6 = this.mVideoWidth;
                if (i6 > 0 && (i3 = this.mVideoHeight) > 0) {
                    if (i6 * defaultSize2 > defaultSize * i3) {
                        i = (i6 * defaultSize2) / i3;
                        i2 = defaultSize2;
                        break;
                    } else {
                        i2 = (i3 * defaultSize) / i6;
                        i = defaultSize;
                        break;
                    }
                }
                i = defaultSize;
                i2 = defaultSize2;
                break;
            case 6:
                int i7 = this.mVideoWidth;
                if (i7 > 0 && (i4 = this.mVideoHeight) > 0) {
                    if (i7 * defaultSize2 > defaultSize * i4) {
                        i = (i7 * defaultSize2) / i4;
                        i2 = defaultSize2;
                        break;
                    } else {
                        i2 = (i4 * defaultSize) / i7;
                        i = defaultSize;
                        break;
                    }
                }
                i = defaultSize;
                i2 = defaultSize2;
                break;
            default:
                if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
                    int mode = View.MeasureSpec.getMode(i);
                    i = View.MeasureSpec.getSize(i);
                    int mode2 = View.MeasureSpec.getMode(i2);
                    i2 = View.MeasureSpec.getSize(i2);
                    if (mode != 1073741824 || mode2 != 1073741824) {
                        if (mode != 1073741824) {
                            if (mode2 == 1073741824) {
                                int i8 = (this.mVideoWidth * i2) / this.mVideoHeight;
                                if (mode != Integer.MIN_VALUE || i8 <= i) {
                                    i = i8;
                                    break;
                                }
                            } else {
                                int i9 = this.mVideoWidth;
                                int i10 = this.mVideoHeight;
                                if (mode2 != Integer.MIN_VALUE || i10 <= i2) {
                                    i2 = i10;
                                } else {
                                    i9 = (i9 * i2) / i10;
                                }
                                if (mode == Integer.MIN_VALUE && i9 > i) {
                                    i2 = (this.mVideoHeight * i) / this.mVideoWidth;
                                    break;
                                } else {
                                    i = i9;
                                    break;
                                }
                            }
                        } else {
                            int i11 = (this.mVideoHeight * i) / this.mVideoWidth;
                            if (mode2 != Integer.MIN_VALUE || i11 <= i2) {
                                i2 = i11;
                                break;
                            }
                        }
                    } else {
                        int i12 = this.mVideoWidth;
                        int i13 = i12 * i2;
                        int i14 = this.mVideoHeight;
                        if (i13 >= i * i14) {
                            if (i12 * i2 > i * i14) {
                                i2 = (i14 * i) / i12;
                                break;
                            }
                        } else {
                            i = (i12 * i2) / i14;
                            break;
                        }
                    }
                }
                i = defaultSize;
                i2 = defaultSize2;
                break;
        }
        setMeasuredDimension(i, i2);
    }
}
