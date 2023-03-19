package com.movieboxpro.android.utils;

import android.content.Context;
import com.huantansheng.easyphotos.models.ad.AdListener;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.model.AdParamModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: AdManager.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0014\u001a\u00020\u0012J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J\u0010\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0010H\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/utils/AdManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "adListener", "Lcom/huantansheng/easyphotos/models/ad/AdListener;", "getContext", "()Landroid/content/Context;", "total", "", "valueList", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/AdParamModel;", "Lkotlin/collections/ArrayList;", "getAdKey", "", "getParams", "", "release", "showAd", "showAdByKey", "key", "showGoogleAd", "showNextAd", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AdManager {
    public static final String Appnext_banner = "Appnext_banner";
    public static final String Appnext_interstitial = "Appnext_interstitial";
    public static final String Appnext_native = "Appnext_native";
    public static final String Cloudmobi_banner = "Cloudmobi_banner";
    public static final String Cloudmobi_interstitial = "Cloudmobi_interstitial";
    public static final String Cloudmobi_native = "Cloudmobi_native";
    public static final Companion Companion = new Companion(null);
    public static final String Google_banner = "Google_banner";
    public static final String Google_interstitial = "Google_interstitial";
    public static final String Google_native = "Google_native";
    public static final String Mintegral_banner = "Mintegral_banner";
    public static final String Mintegral_interstitial = "Mintegral_interstitial";
    public static final String Mintegral_native = "Mintegral_native";
    public static final String PANGLE_interstitial = "PANGLE_interstitial";
    private AdListener adListener;
    private final Context context;
    private int total;
    private final ArrayList<AdParamModel> valueList;

    private final void showGoogleAd() {
    }

    public AdManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.valueList = new ArrayList<>();
    }

    public final Context getContext() {
        return this.context;
    }

    /* compiled from: AdManager.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/utils/AdManager$Companion;", "", "()V", AdManager.Appnext_banner, "", AdManager.Appnext_interstitial, AdManager.Appnext_native, AdManager.Cloudmobi_banner, AdManager.Cloudmobi_interstitial, AdManager.Cloudmobi_native, AdManager.Google_banner, AdManager.Google_interstitial, AdManager.Google_native, AdManager.Mintegral_banner, AdManager.Mintegral_interstitial, AdManager.Mintegral_native, AdManager.PANGLE_interstitial, "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void showAd() {
        getParams();
        showAdByKey(getAdKey());
    }

    private final void showAdByKey(String str) {
        String str2;
        switch (str.hashCode()) {
            case -1210509302:
                str2 = ConfigKey.AD_INTERSTITIAL_MINTEGRAL_PORTION;
                break;
            case -699336986:
                str2 = ConfigKey.AD_INTERSTITIAL_MINTEGRAL_IMAGE_PORTION;
                break;
            case -557922974:
                str2 = ConfigKey.AD_INTERSTITIAL_PANGLE_PORTION;
                break;
            case 13545272:
                if (str.equals(ConfigKey.AD_INTERSTITIAL_GOOGLE_PORTION)) {
                    showGoogleAd();
                    return;
                }
                return;
            case 1123211376:
                str2 = ConfigKey.AD_INTERSTITIAL_CLOUDMOBI_PORTION_NEW;
                break;
            default:
                return;
        }
        str.equals(str2);
    }

    public final void release() {
        this.adListener = null;
    }

    private final void getParams() {
        String google = ConfigUtils.readStringConfig(ConfigKey.AD_INTERSTITIAL_GOOGLE_PORTION);
        String mintegral = ConfigUtils.readStringConfig(ConfigKey.AD_INTERSTITIAL_MINTEGRAL_PORTION);
        String mintegralImage = ConfigUtils.readStringConfig(ConfigKey.AD_INTERSTITIAL_MINTEGRAL_IMAGE_PORTION);
        String cloudmobiInterstitial = ConfigUtils.readStringConfig(ConfigKey.AD_INTERSTITIAL_CLOUDMOBI_PORTION_NEW);
        String pangleInterstitial = ConfigUtils.readStringConfig(ConfigKey.AD_INTERSTITIAL_PANGLE_PORTION);
        String str = google;
        boolean z = true;
        if (!(str == null || StringsKt.isBlank(str))) {
            Intrinsics.checkNotNullExpressionValue(google, "google");
            int parseInt = Integer.parseInt(google);
            if (parseInt != 0) {
                this.valueList.add(new AdParamModel(ConfigKey.AD_INTERSTITIAL_GOOGLE_PORTION, parseInt));
            }
        }
        String str2 = mintegral;
        if (!(str2 == null || StringsKt.isBlank(str2))) {
            Intrinsics.checkNotNullExpressionValue(mintegral, "mintegral");
            int parseInt2 = Integer.parseInt(mintegral);
            if (parseInt2 != 0) {
                this.valueList.add(new AdParamModel(ConfigKey.AD_INTERSTITIAL_MINTEGRAL_PORTION, parseInt2));
            }
        }
        String str3 = mintegralImage;
        if (!(str3 == null || StringsKt.isBlank(str3))) {
            Intrinsics.checkNotNullExpressionValue(mintegralImage, "mintegralImage");
            int parseInt3 = Integer.parseInt(mintegralImage);
            if (parseInt3 != 0) {
                this.valueList.add(new AdParamModel(ConfigKey.AD_INTERSTITIAL_MINTEGRAL_IMAGE_PORTION, parseInt3));
            }
        }
        String str4 = cloudmobiInterstitial;
        if (!(str4 == null || StringsKt.isBlank(str4))) {
            Intrinsics.checkNotNullExpressionValue(cloudmobiInterstitial, "cloudmobiInterstitial");
            int parseInt4 = Integer.parseInt(cloudmobiInterstitial);
            if (parseInt4 != 0) {
                this.valueList.add(new AdParamModel(ConfigKey.AD_INTERSTITIAL_CLOUDMOBI_PORTION_NEW, parseInt4));
            }
        }
        String str5 = pangleInterstitial;
        if (str5 != null && !StringsKt.isBlank(str5)) {
            z = false;
        }
        if (!z) {
            Intrinsics.checkNotNullExpressionValue(pangleInterstitial, "pangleInterstitial");
            int parseInt5 = Integer.parseInt(pangleInterstitial);
            if (parseInt5 != 0) {
                this.valueList.add(new AdParamModel(ConfigKey.AD_INTERSTITIAL_PANGLE_PORTION, parseInt5));
            }
        }
        this.total = 0;
        for (AdParamModel adParamModel : this.valueList) {
            adParamModel.setStart(this.total);
            int num = this.total + adParamModel.getNum();
            this.total = num;
            adParamModel.setEnd(num);
        }
    }

    private final String getAdKey() {
        int nextInt = new Random().nextInt((this.total - 0) + 1) + 0;
        for (AdParamModel adParamModel : this.valueList) {
            if (nextInt >= adParamModel.getStart() && nextInt < adParamModel.getEnd()) {
                String key = adParamModel.getKey();
                Intrinsics.checkNotNullExpressionValue(key, "it.key");
                return key;
            }
        }
        if (!this.valueList.isEmpty()) {
            String key2 = this.valueList.get(0).getKey();
            Intrinsics.checkNotNullExpressionValue(key2, "{\n            valueList[0].key\n        }");
            return key2;
        }
        return "";
    }

    private final void showNextAd(String str) {
        Iterator<AdParamModel> it = this.valueList.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "valueList.iterator()");
        while (it.hasNext()) {
            AdParamModel next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "iterator.next()");
            if (Intrinsics.areEqual(next.getKey(), str)) {
                it.remove();
            }
        }
        if (this.valueList.isEmpty()) {
            return;
        }
        String adKey = getAdKey();
        String str2 = adKey;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return;
        }
        showAdByKey(adKey);
    }
}
