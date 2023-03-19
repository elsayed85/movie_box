package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.movieboxpro.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: VideoIconUtils.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u0006¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/utils/VideoIconUtils;", "", "()V", "getAudioChannelIcon", "", "channel", "", "getAudioCodeIcon", "code", "getAudioLangIcon", "lang", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoIconUtils {
    public static final VideoIconUtils INSTANCE = new VideoIconUtils();

    private VideoIconUtils() {
    }

    public final int getAudioChannelIcon(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return -1;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) ExifInterface.GPS_MEASUREMENT_2D, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "stereo", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_stereo;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "5.1", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_5_1;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "7.1", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_7_1;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "6.1", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_6_1;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "mono", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_mono;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "4.0", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_4_0;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "quad", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_quad;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "3.0", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_3_0;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "5.0", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_channel_5_0;
        }
        if (Intrinsics.areEqual(str, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            return R.mipmap.ic_audio_channel_mono;
        }
        return -1;
    }

    public final int getAudioCodeIcon(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return -1;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) HlsSegmentFormat.AAC, false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_aac;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) HlsSegmentFormat.AC3, false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_ac3;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "dts", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_dts;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "eac3", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_eac3;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) HlsSegmentFormat.MP3, false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_mp3;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "flac", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_flac;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "opus", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_opus;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "truehd", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_turehd;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "vorbis", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_vorbis;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "wmav2", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_wmav2;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "pcm_s24le", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_pcm_s24le;
        }
        if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "mp2", false, 2, (Object) null)) {
            return R.mipmap.ic_audio_code_mp2;
        }
        return -1;
    }

    public final int getAudioLangIcon(String str) {
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return -1;
        }
        switch (str.hashCode()) {
            case -899451350:
                if (str.equals("slovak")) {
                    return R.mipmap.ic_audio_lang_slovak;
                }
                break;
            case -176239783:
                if (str.equals("Romanian")) {
                    return R.mipmap.ic_audio_lang_romanian;
                }
                break;
            case 3241:
                if (str.equals("en")) {
                    return R.mipmap.ic_audio_lang_en;
                }
                break;
            case 3276:
                if (str.equals("fr")) {
                    return R.mipmap.ic_audio_lang_fr;
                }
                break;
            case 3371:
                if (str.equals("it")) {
                    return R.mipmap.ic_audio_lang_it;
                }
                break;
            case 96663:
                if (str.equals("alb")) {
                    return R.mipmap.ic_audio_lang_alb;
                }
                break;
            case 96848:
                if (str.equals("ara")) {
                    return R.mipmap.ic_audio_lang_ara;
                }
                break;
            case 97419:
                if (str.equals("ben")) {
                    return R.mipmap.ic_audio_lang_ben;
                }
                break;
            case 97734:
                if (str.equals("bos")) {
                    return R.mipmap.ic_audio_lang_bos;
                }
                break;
            case 97913:
                if (str.equals("bul")) {
                    return R.mipmap.ic_audio_lang_bul;
                }
                break;
            case 97919:
                if (str.equals("bur")) {
                    return R.mipmap.ic_audio_lang_bur;
                }
                break;
            case 98468:
                if (str.equals("chi")) {
                    return R.mipmap.ic_audio_lang_chi;
                }
                break;
            case 99022:
                if (str.equals("cze")) {
                    return R.mipmap.ic_audio_lang_cze;
                }
                break;
            case 99217:
                if (str.equals("dan")) {
                    return R.mipmap.ic_audio_lang_dan;
                }
                break;
            case 99348:
                if (str.equals("deu")) {
                    return R.mipmap.ic_audio_lang_deu;
                }
                break;
            case 99843:
                if (str.equals("dut")) {
                    return R.mipmap.ic_audio_lang_dut;
                }
                break;
            case 100574:
                if (str.equals("eng")) {
                    return R.mipmap.ic_audio_lang_eng;
                }
                break;
            case 100742:
                if (str.equals("est")) {
                    return R.mipmap.ic_audio_lang_est;
                }
                break;
            case 101387:
                if (str.equals("fin")) {
                    return R.mipmap.ic_audio_lang_fin;
                }
                break;
            case 101653:
                if (str.equals("fra")) {
                    return R.mipmap.ic_audio_lang_fra;
                }
                break;
            case 101657:
                if (str.equals("fre")) {
                    return R.mipmap.ic_audio_lang_fre;
                }
                break;
            case 102228:
                if (str.equals("ger")) {
                    return R.mipmap.ic_audio_lang_ger;
                }
                break;
            case 102618:
                if (str.equals("gre")) {
                    return R.mipmap.ic_audio_lang_gre;
                }
                break;
            case 103173:
                if (str.equals("heb")) {
                    return R.mipmap.ic_audio_lang_heb;
                }
                break;
            case 103309:
                if (str.equals("hin")) {
                    return R.mipmap.ic_audio_lang_hin;
                }
                break;
            case 103596:
                if (str.equals("hrv")) {
                    return R.mipmap.ic_audio_lang_hrv;
                }
                break;
            case 103681:
                if (str.equals("hun")) {
                    return R.mipmap.ic_audio_lang_hun;
                }
                break;
            case 104075:
                if (str.equals("ice")) {
                    return R.mipmap.ic_audio_lang_ice;
                }
                break;
            case 104415:
                if (str.equals("ind")) {
                    return R.mipmap.ic_audio_lang_ind;
                }
                break;
            case 104578:
                if (str.equals("isl")) {
                    return R.mipmap.ic_audio_lang_isl;
                }
                break;
            case 104598:
                if (str.equals("ita")) {
                    return R.mipmap.ic_audio_lang_ita;
                }
                break;
            case 105448:
                if (str.equals("jpn")) {
                    return R.mipmap.ic_audio_lang_jpn;
                }
                break;
            case 106382:
                if (str.equals("kor")) {
                    return R.mipmap.ic_audio_lang_kor;
                }
                break;
            case 106911:
                if (str.equals("lat")) {
                    return R.mipmap.ic_audio_lang_lat;
                }
                break;
            case 107864:
                if (str.equals("mal")) {
                    return R.mipmap.ic_audio_lang_mal;
                }
                break;
            case 107866:
                if (str.equals("man")) {
                    return R.mipmap.ic_audio_lang_man;
                }
                break;
            case 107870:
                if (str.equals("mar")) {
                    return R.mipmap.ic_audio_lang_mar;
                }
                break;
            case 108300:
                if (str.equals("mon")) {
                    return R.mipmap.ic_audio_lang_mon;
                }
                break;
            case 108960:
                if (str.equals("new")) {
                    return R.mipmap.ic_audio_lang_new;
                }
                break;
            case 109158:
                if (str.equals("nld")) {
                    return R.mipmap.ic_audio_lang_nld;
                }
                break;
            case 109265:
                if (str.equals("nor")) {
                    return R.mipmap.ic_audio_lang_nor;
                }
                break;
            case 109386:
                if (str.equals("nso")) {
                    return R.mipmap.ic_audio_lang_nso;
                }
                break;
            case 110749:
                if (str.equals("pan")) {
                    return R.mipmap.ic_audio_lang_pan;
                }
                break;
            case 110877:
                if (str.equals("per")) {
                    return R.mipmap.ic_audio_lang_per;
                }
                break;
            case 111181:
                if (str.equals("pol")) {
                    return R.mipmap.ic_audio_lang_pol;
                }
                break;
            case 111187:
                if (str.equals("por")) {
                    return R.mipmap.ic_audio_lang_por;
                }
                break;
            case 113105:
                if (str.equals("ron")) {
                    return R.mipmap.ic_audio_lang_ron;
                }
                break;
            case 113296:
                if (str.equals("rus")) {
                    return R.mipmap.ic_audio_lang_rus;
                }
                break;
            case 113974:
                if (str.equals("slo")) {
                    return R.mipmap.ic_audio_lang_slo;
                }
                break;
            case 114084:
                if (str.equals("spa")) {
                    return R.mipmap.ic_audio_lang_spa;
                }
                break;
            case 114301:
                if (str.equals("swa")) {
                    return R.mipmap.ic_audio_lang_swa;
                }
                break;
            case 114305:
                if (str.equals("swe")) {
                    return R.mipmap.ic_audio_lang_swe;
                }
                break;
            case 114588:
                if (str.equals("tai")) {
                    return R.mipmap.ic_audio_lang_tai;
                }
                break;
            case 114797:
                if (str.equals("tha")) {
                    return R.mipmap.ic_audio_lang_tha;
                }
                break;
            case 115217:
                if (str.equals("tur")) {
                    return R.mipmap.ic_audio_lang_tur;
                }
                break;
            case 116078:
                if (str.equals("urk")) {
                    return R.mipmap.ic_audio_lang_urk;
                }
                break;
            case 116754:
                if (str.equals("vie")) {
                    return R.mipmap.ic_audio_lang_vie;
                }
                break;
            case 95163315:
                if (str.equals("czech")) {
                    return R.mipmap.ic_audio_lang_czech;
                }
                break;
        }
        return -1;
    }
}
