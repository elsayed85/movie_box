package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
/* compiled from: UserAvatarView.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u001a\u0010\u000e\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\fJ\u001a\u0010\u0011\u001a\u00020\n2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\f¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/widget/UserAvatarView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "clearView", "", "getNameFirstLetter", "", "name", "setAvatar", "avatar", "userName", "setAvatarNoCache", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserAvatarView extends FrameLayout {
    public Map<Integer, View> _$_findViewCache;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UserAvatarView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UserAvatarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public /* synthetic */ UserAvatarView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserAvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        LayoutInflater.from(context).inflate(R.layout.user_avatar_view, this);
    }

    private final String getNameFirstLetter(String str) {
        String str2 = str;
        Object[] array = new Regex("\\s+").split(str2, 0).toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            StringBuilder sb = new StringBuilder();
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str3 = strArr[i];
                i++;
                if (str3.length() > 0) {
                    sb.append(str3.charAt(0));
                }
            }
            if (sb.length() <= 1) {
                List split$default = StringsKt.split$default((CharSequence) str2, new String[]{"_"}, false, 0, 6, (Object) null);
                if (split$default.size() >= 2) {
                    StringBuilder sb2 = new StringBuilder();
                    Character firstOrNull = StringsKt.firstOrNull((CharSequence) split$default.get(0));
                    if (firstOrNull == null) {
                        firstOrNull = "";
                    }
                    sb2.append(firstOrNull);
                    Character firstOrNull2 = StringsKt.firstOrNull((CharSequence) split$default.get(1));
                    sb2.append(firstOrNull2 != null ? firstOrNull2 : "");
                    String sb3 = sb2.toString();
                    Locale locale = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                    String upperCase = sb3.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
                    if (upperCase.length() >= 2) {
                        Locale locale2 = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                        String upperCase2 = upperCase.toUpperCase(locale2);
                        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(locale)");
                        return upperCase2;
                    } else if (str.length() >= 2) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(str.charAt(0));
                        sb4.append(str.charAt(1));
                        String sb5 = sb4.toString();
                        Locale locale3 = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale3, "getDefault()");
                        String upperCase3 = sb5.toUpperCase(locale3);
                        Intrinsics.checkNotNullExpressionValue(upperCase3, "this as java.lang.String).toUpperCase(locale)");
                        return upperCase3;
                    } else {
                        Locale locale4 = Locale.getDefault();
                        Intrinsics.checkNotNullExpressionValue(locale4, "getDefault()");
                        String upperCase4 = str.toUpperCase(locale4);
                        Intrinsics.checkNotNullExpressionValue(upperCase4, "this as java.lang.String).toUpperCase(locale)");
                        return upperCase4;
                    }
                } else if (str.length() >= 2) {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append(str.charAt(0));
                    sb6.append(str.charAt(1));
                    String sb7 = sb6.toString();
                    Locale locale5 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale5, "getDefault()");
                    String upperCase5 = sb7.toUpperCase(locale5);
                    Intrinsics.checkNotNullExpressionValue(upperCase5, "this as java.lang.String).toUpperCase(locale)");
                    return upperCase5;
                } else {
                    Locale locale6 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale6, "getDefault()");
                    String upperCase6 = str.toUpperCase(locale6);
                    Intrinsics.checkNotNullExpressionValue(upperCase6, "this as java.lang.String).toUpperCase(locale)");
                    return upperCase6;
                }
            }
            String sb8 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb8, "nameFirst.toString()");
            Locale locale7 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale7, "getDefault()");
            String upperCase7 = sb8.toUpperCase(locale7);
            Intrinsics.checkNotNullExpressionValue(upperCase7, "this as java.lang.String).toUpperCase(locale)");
            return upperCase7;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
    }

    public final void clearView() {
        ((TextView) _$_findCachedViewById(R.id.tvNameAvatar)).setText("");
        ((ImageView) _$_findCachedViewById(R.id.ivAvatars)).setImageDrawable(null);
    }

    public final void setAvatarNoCache(String str, String str2) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvNameAvatar);
        if (str2 == null) {
            str2 = "";
        }
        textView.setText(getNameFirstLetter(str2));
        RequestManager with = Glide.with(this);
        with.load(((Object) str) + "?t=" + System.currentTimeMillis()).onlyRetrieveFromCache(false).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<Drawable>() { // from class: com.movieboxpro.android.view.widget.UserAvatarView$setAvatarNoCache$1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                TextView tvNameAvatar = (TextView) UserAvatarView.this._$_findCachedViewById(R.id.tvNameAvatar);
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.visible(tvNameAvatar);
                return true;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                TextView tvNameAvatar = (TextView) UserAvatarView.this._$_findCachedViewById(R.id.tvNameAvatar);
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.gone(tvNameAvatar);
                ((ImageView) UserAvatarView.this._$_findCachedViewById(R.id.ivAvatars)).setImageDrawable(drawable);
                return true;
            }
        }).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) _$_findCachedViewById(R.id.ivAvatars));
    }

    public final void setAvatar(String str, String str2) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvNameAvatar);
        if (str2 == null) {
            str2 = "";
        }
        textView.setText(getNameFirstLetter(str2));
        Glide.with(this).load(str).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<Drawable>() { // from class: com.movieboxpro.android.view.widget.UserAvatarView$setAvatar$1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                TextView tvNameAvatar = (TextView) UserAvatarView.this._$_findCachedViewById(R.id.tvNameAvatar);
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.visible(tvNameAvatar);
                return true;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                TextView tvNameAvatar = (TextView) UserAvatarView.this._$_findCachedViewById(R.id.tvNameAvatar);
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.gone(tvNameAvatar);
                ((ImageView) UserAvatarView.this._$_findCachedViewById(R.id.ivAvatars)).setImageDrawable(drawable);
                return true;
            }
        }).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into((ImageView) _$_findCachedViewById(R.id.ivAvatars));
    }
}
