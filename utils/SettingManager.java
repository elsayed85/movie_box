package com.movieboxpro.android.utils;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.android.view.dialog.FilterVideoDialog;
import java.util.ArrayList;
import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
/* compiled from: SettingManager.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b.\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020\u0004J\u0006\u0010(\u001a\u00020\u0004J\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020\u00040*j\b\u0012\u0004\u0012\u00020\u0004`+J\u0006\u0010,\u001a\u00020-J\u0016\u0010.\u001a\u0012\u0012\u0004\u0012\u00020/0*j\b\u0012\u0004\u0012\u00020/`+J\u0006\u00100\u001a\u00020-J\u0006\u00101\u001a\u00020-J\u0006\u00102\u001a\u00020\u0004J\u0006\u00103\u001a\u00020\u0004J\u0006\u00104\u001a\u00020\u0004J\u0006\u00105\u001a\u00020-J\u0006\u00106\u001a\u00020-J\u0006\u00107\u001a\u00020-J\u0006\u00108\u001a\u00020&J\u0006\u00109\u001a\u00020&J\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u00020\u00040*j\b\u0012\u0004\u0012\u00020\u0004`+J\u0006\u0010;\u001a\u00020-J\u0016\u0010<\u001a\u0012\u0012\u0004\u0012\u00020/0*j\b\u0012\u0004\u0012\u00020/`+J\u0006\u0010=\u001a\u00020\u0004J\u0006\u0010>\u001a\u00020\u0004J\u0006\u0010?\u001a\u00020-J\u0006\u0010@\u001a\u00020\u0004J\u0006\u0010A\u001a\u00020\u0004J\u0006\u0010B\u001a\u00020-J\u0006\u0010C\u001a\u00020-J\u0006\u0010D\u001a\u00020-J\u0006\u0010E\u001a\u00020-J\u0006\u0010F\u001a\u00020&J\u0006\u0010G\u001a\u00020&J\u0006\u0010H\u001a\u00020&J\u000e\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020&J\u000e\u0010L\u001a\u00020J2\u0006\u0010M\u001a\u00020&J\u000e\u0010N\u001a\u00020J2\u0006\u0010K\u001a\u00020&J\u000e\u0010O\u001a\u00020J2\u0006\u0010P\u001a\u00020\u0004J\u001e\u0010Q\u001a\u00020J2\u0016\u0010R\u001a\u0012\u0012\u0004\u0012\u00020\u00040*j\b\u0012\u0004\u0012\u00020\u0004`+J\u000e\u0010S\u001a\u00020J2\u0006\u0010T\u001a\u00020-J^\u0010U\u001a\u00020J2\u0006\u0010V\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u00042\u0006\u0010X\u001a\u00020\u00042\u0016\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020/0*j\b\u0012\u0004\u0012\u00020/`+2\u0016\u0010R\u001a\u0012\u0012\u0004\u0012\u00020\u00040*j\b\u0012\u0004\u0012\u00020\u0004`+2\u0006\u0010Z\u001a\u00020-2\u0006\u0010T\u001a\u00020-J\u001e\u0010[\u001a\u00020J2\u0016\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020/0*j\b\u0012\u0004\u0012\u00020/`+J\u000e\u0010\\\u001a\u00020J2\u0006\u0010]\u001a\u00020-J\u000e\u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020-J\u000e\u0010`\u001a\u00020J2\u0006\u0010W\u001a\u00020\u0004J\u000e\u0010a\u001a\u00020J2\u0006\u0010X\u001a\u00020\u0004J\u000e\u0010b\u001a\u00020J2\u0006\u0010V\u001a\u00020\u0004J\u000e\u0010c\u001a\u00020J2\u0006\u0010Z\u001a\u00020-J\u000e\u0010d\u001a\u00020J2\u0006\u0010]\u001a\u00020-J\u000e\u0010e\u001a\u00020J2\u0006\u0010_\u001a\u00020-J\u0006\u0010f\u001a\u00020JJ\u000e\u0010g\u001a\u00020J2\u0006\u0010M\u001a\u00020&J\u000e\u0010h\u001a\u00020J2\u0006\u0010i\u001a\u00020&J\u001e\u0010j\u001a\u00020J2\u0016\u0010R\u001a\u0012\u0012\u0004\u0012\u00020\u00040*j\b\u0012\u0004\u0012\u00020\u0004`+J\u000e\u0010k\u001a\u00020J2\u0006\u0010T\u001a\u00020-JV\u0010l\u001a\u00020J2\u0006\u0010V\u001a\u00020\u00042\u0006\u0010W\u001a\u00020\u00042\u0016\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020/0*j\b\u0012\u0004\u0012\u00020/`+2\u0016\u0010R\u001a\u0012\u0012\u0004\u0012\u00020\u00040*j\b\u0012\u0004\u0012\u00020\u0004`+2\u0006\u0010Z\u001a\u00020-2\u0006\u0010T\u001a\u00020-J\u001e\u0010m\u001a\u00020J2\u0016\u0010Y\u001a\u0012\u0012\u0004\u0012\u00020/0*j\b\u0012\u0004\u0012\u00020/`+J\u000e\u0010n\u001a\u00020J2\u0006\u0010W\u001a\u00020\u0004J\u000e\u0010o\u001a\u00020J2\u0006\u0010V\u001a\u00020\u0004J\u000e\u0010p\u001a\u00020J2\u0006\u0010Z\u001a\u00020-J\u0016\u0010q\u001a\u00020J2\u0006\u0010r\u001a\u00020\u00042\u0006\u0010s\u001a\u00020\u0004J\u000e\u0010t\u001a\u00020J2\u0006\u0010]\u001a\u00020-J\u000e\u0010u\u001a\u00020J2\u0006\u0010_\u001a\u00020-J\u000e\u0010v\u001a\u00020J2\u0006\u0010]\u001a\u00020-J\u000e\u0010w\u001a\u00020J2\u0006\u0010_\u001a\u00020-R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006x"}, d2 = {"Lcom/movieboxpro/android/utils/SettingManager;", "", "()V", "AUTO_SELECT_DOWNLOAD_QUALITY", "", "AUTO_SELECT_PLAY_QUALITY", "DOWNLOAD_QUALITY_RULE", "MOVIE_COUNTRYS", "MOVIE_END_YEAR", "MOVIE_GENER", "MOVIE_GENERS", "MOVIE_IMDB_END", "MOVIE_IMDB_START", "MOVIE_RATING", "MOVIE_RESOLUTION", "MOVIE_SORT", "MOVIE_START_YEAR", "MOVIE_TOMATO_END", "MOVIE_TOMATO_START", "NEVER_SHOW_SMART_DOWNLOAD", "SMART_DOWNLOAD", "SMART_DOWNLOAD_AUTO_DELETE_WATCHED", "SMART_DOWNLOAD_WIFI_ONLY", "TRANSCODE_CODE", "TRANSCODE_LANGUAGE", "TV_COUNTRY", "TV_COUNTRYS", "TV_END_YEAR", "TV_GENER", "TV_GENERS", "TV_IMDB_END", "TV_IMDB_START", "TV_RATING", "TV_SORT", "TV_START_YEAR", "TV_TOMATO_END", "TV_TOMATO_START", "getAutoDeleteWatched", "", "getCurrDownloadRule", "getDownloadRule", "getMovieCountry", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getMovieEndYear", "", "getMovieGener", "", "getMovieImdbEnd", "getMovieImdbStart", "getMovieRating", "getMovieResolution", "getMovieSort", "getMovieStartYear", "getMovieTomatoEnd", "getMovieTomatoStart", "getSmartDownload", "getSmartDownloadWifiOnly", "getTVCountry", "getTVEndYear", "getTVGener", "getTVRating", "getTVSort", "getTVStartYear", "getTranscodeCode", "getTranscodeLanguage", "getTvImdbEnd", "getTvImdbStart", "getTvTomatoEnd", "getTvTomatoStart", "isAutoDownloadSelectQuality", "isAutoSelectPlayQuality", "neverShowSmartDownloadDialog", "saveAutoDeleteWatched", "", "auto", "saveAutoDownloadSelectQuality", DebugKt.DEBUG_PROPERTY_VALUE_ON, "saveAutoSelectPlayQuality", "saveDownloadRule", "rule", "saveMovieCountry", "country", "saveMovieEndYear", "endYear", "saveMovieFilter", "sort", "rating", "resolution", "genreId", "startYear", "saveMovieGener", "saveMovieImdbEnd", "endStar", "saveMovieImdbStart", "startStar", "saveMovieRating", "saveMovieResolution", "saveMovieSort", "saveMovieStartYear", "saveMovieTomatoEnd", "saveMovieTomatoStart", "saveNeverShowSmartDownloadDialog", "saveSmartDownload", "saveSmartDownloadWifiOnly", "only", "saveTVCountry", "saveTVEndYear", "saveTVFilter", "saveTVGener", "saveTVRating", "saveTVSort", "saveTVStartYear", "saveTranscodeLanguage", "language", "code", "saveTvImdbEnd", "saveTvImdbStart", "saveTvTomatoEnd", "saveTvTomatoStart", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SettingManager {
    private static final String AUTO_SELECT_DOWNLOAD_QUALITY = "auto_select_download_quality";
    private static final String AUTO_SELECT_PLAY_QUALITY = "auto_select_play_quality";
    private static final String DOWNLOAD_QUALITY_RULE = "download_quality_rule";
    public static final SettingManager INSTANCE = new SettingManager();
    private static final String MOVIE_COUNTRYS = "movie_countrys";
    private static final String MOVIE_END_YEAR = "movie_end_year";
    private static final String MOVIE_GENER = "movie_gener";
    private static final String MOVIE_GENERS = "movie_geners";
    private static final String MOVIE_IMDB_END = "movie_imdb_end";
    private static final String MOVIE_IMDB_START = "movie_imdb_start";
    private static final String MOVIE_RATING = "movie_rating";
    private static final String MOVIE_RESOLUTION = "movie_resolution";
    private static final String MOVIE_SORT = "movie_sort";
    private static final String MOVIE_START_YEAR = "movie_start_year";
    private static final String MOVIE_TOMATO_END = "movie_tomato_end";
    private static final String MOVIE_TOMATO_START = "movie_tomato_start";
    private static final String NEVER_SHOW_SMART_DOWNLOAD = "never_show_smart_download";
    private static final String SMART_DOWNLOAD = "smart_download";
    private static final String SMART_DOWNLOAD_AUTO_DELETE_WATCHED = "smart_download_auto_delete_watched";
    private static final String SMART_DOWNLOAD_WIFI_ONLY = "smart_download_wifi_only";
    private static final String TRANSCODE_CODE = "transcode_code";
    private static final String TRANSCODE_LANGUAGE = "transcode_language";
    private static final String TV_COUNTRY = "tv_country";
    private static final String TV_COUNTRYS = "tv_countrys";
    private static final String TV_END_YEAR = "tv_end_year";
    private static final String TV_GENER = "tv_gener";
    private static final String TV_GENERS = "tv_generS";
    private static final String TV_IMDB_END = "tv_imdb_end";
    private static final String TV_IMDB_START = "tv_imdb_start";
    private static final String TV_RATING = "tv_rating";
    private static final String TV_SORT = "tv_sort";
    private static final String TV_START_YEAR = "tv_start_year";
    private static final String TV_TOMATO_END = "tv_tomato_end";
    private static final String TV_TOMATO_START = "tv_tomato_start";

    private SettingManager() {
    }

    public final void saveTranscodeLanguage(String language, String code) {
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(code, "code");
        SPStaticUtils.put(TRANSCODE_LANGUAGE, language);
        SPStaticUtils.put(TRANSCODE_CODE, code);
    }

    public final String getTranscodeLanguage() {
        String string = SPStaticUtils.getString(TRANSCODE_LANGUAGE, "Full Unicode");
        Intrinsics.checkNotNullExpressionValue(string, "getString(TRANSCODE_LANGUAGE, \"Full Unicode\")");
        return string;
    }

    public final String getTranscodeCode() {
        String string = SPStaticUtils.getString(TRANSCODE_CODE, "UTF-8");
        Intrinsics.checkNotNullExpressionValue(string, "getString(TRANSCODE_CODE, \"UTF-8\")");
        return string;
    }

    public final void saveMovieFilter(String sort, String rating, String resolution, ArrayList<Integer> genreId, ArrayList<String> country, float f, float f2) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(resolution, "resolution");
        Intrinsics.checkNotNullParameter(genreId, "genreId");
        Intrinsics.checkNotNullParameter(country, "country");
        saveMovieCountry(country);
        saveMovieSort(sort);
        saveMovieRating(rating);
        saveMovieResolution(resolution);
        saveMovieGener(genreId);
        saveMovieStartYear(f);
        saveMovieEndYear(f2);
    }

    public final void saveTVFilter(String sort, String rating, ArrayList<Integer> genreId, ArrayList<String> country, float f, float f2) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(genreId, "genreId");
        Intrinsics.checkNotNullParameter(country, "country");
        saveTVCountry(country);
        saveTVSort(sort);
        saveTVRating(rating);
        saveTVGener(genreId);
        saveTVStartYear(f);
        saveTVEndYear(f2);
    }

    public final void saveMovieSort(String sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        PrefsUtils.getInstance().putString(MOVIE_SORT, sort);
    }

    public final String getMovieSort() {
        String string = PrefsUtils.getInstance().getString(MOVIE_SORT, FilterVideoDialog.DEFAULT_SORT);
        Intrinsics.checkNotNullExpressionValue(string, "getInstance().getString(…VideoDialog.DEFAULT_SORT)");
        return string;
    }

    public final void saveMovieRating(String rating) {
        Intrinsics.checkNotNullParameter(rating, "rating");
        PrefsUtils.getInstance().putString(MOVIE_RATING, rating);
    }

    public final String getMovieRating() {
        String string = PrefsUtils.getInstance().getString(MOVIE_RATING, "");
        Intrinsics.checkNotNullExpressionValue(string, "getInstance().getString(MOVIE_RATING,\"\")");
        return string;
    }

    public final void saveMovieResolution(String resolution) {
        Intrinsics.checkNotNullParameter(resolution, "resolution");
        PrefsUtils.getInstance().putString(MOVIE_RESOLUTION, resolution);
    }

    public final String getMovieResolution() {
        String string = PrefsUtils.getInstance().getString(MOVIE_RESOLUTION, "");
        Intrinsics.checkNotNullExpressionValue(string, "getInstance().getString(MOVIE_RESOLUTION,\"\")");
        return string;
    }

    public final void saveMovieGener(ArrayList<Integer> genreId) {
        Intrinsics.checkNotNullParameter(genreId, "genreId");
        PrefsUtils.getInstance().putString(MOVIE_GENERS, JSONObject.toJSONString(genreId));
    }

    public final ArrayList<Integer> getMovieGener() {
        String string = PrefsUtils.getInstance().getString(MOVIE_GENERS, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(JSONObject.parseArray(string, Integer.TYPE));
    }

    public final void saveMovieCountry(ArrayList<String> country) {
        Intrinsics.checkNotNullParameter(country, "country");
        PrefsUtils.getInstance().putString(MOVIE_COUNTRYS, JSONObject.toJSONString(country));
    }

    public final ArrayList<String> getMovieCountry() {
        String string = PrefsUtils.getInstance().getString(MOVIE_COUNTRYS, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(JSONObject.parseArray(string, String.class));
    }

    public final void saveMovieStartYear(float f) {
        PrefsUtils.getInstance().putFloat(MOVIE_START_YEAR, f);
    }

    public final float getMovieStartYear() {
        return PrefsUtils.getInstance().getFloat(MOVIE_START_YEAR, 1900.0f);
    }

    public final void saveMovieEndYear(float f) {
        PrefsUtils.getInstance().putFloat(MOVIE_END_YEAR, f);
    }

    public final float getMovieEndYear() {
        return PrefsUtils.getInstance().getFloat(MOVIE_END_YEAR, Calendar.getInstance().get(1));
    }

    public final void saveMovieImdbStart(float f) {
        PrefsUtils.getInstance().putFloat(MOVIE_IMDB_START, f);
    }

    public final float getMovieImdbStart() {
        return PrefsUtils.getInstance().getFloat(MOVIE_IMDB_START, 0.0f);
    }

    public final void saveMovieImdbEnd(float f) {
        PrefsUtils.getInstance().putFloat(MOVIE_IMDB_END, f);
    }

    public final float getMovieImdbEnd() {
        return PrefsUtils.getInstance().getFloat(MOVIE_IMDB_END, 10.0f);
    }

    public final void saveMovieTomatoStart(float f) {
        PrefsUtils.getInstance().putFloat(MOVIE_TOMATO_START, f);
    }

    public final float getMovieTomatoStart() {
        return PrefsUtils.getInstance().getFloat(MOVIE_TOMATO_START, 0.0f);
    }

    public final void saveMovieTomatoEnd(float f) {
        PrefsUtils.getInstance().putFloat(MOVIE_TOMATO_END, f);
    }

    public final float getMovieTomatoEnd() {
        return PrefsUtils.getInstance().getFloat(MOVIE_TOMATO_END, 100.0f);
    }

    public final void saveTvImdbStart(float f) {
        PrefsUtils.getInstance().putFloat(TV_IMDB_START, f);
    }

    public final float getTvImdbStart() {
        return PrefsUtils.getInstance().getFloat(TV_IMDB_START, 0.0f);
    }

    public final void saveTvImdbEnd(float f) {
        PrefsUtils.getInstance().putFloat(TV_IMDB_END, f);
    }

    public final float getTvImdbEnd() {
        return PrefsUtils.getInstance().getFloat(TV_IMDB_END, 10.0f);
    }

    public final void saveTvTomatoStart(float f) {
        PrefsUtils.getInstance().putFloat(TV_TOMATO_START, f);
    }

    public final float getTvTomatoStart() {
        return PrefsUtils.getInstance().getFloat(TV_TOMATO_START, 0.0f);
    }

    public final void saveTvTomatoEnd(float f) {
        PrefsUtils.getInstance().putFloat(TV_TOMATO_END, f);
    }

    public final float getTvTomatoEnd() {
        return PrefsUtils.getInstance().getFloat(TV_TOMATO_END, 100.0f);
    }

    public final void saveTVSort(String sort) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        PrefsUtils.getInstance().putString(TV_SORT, sort);
    }

    public final String getTVSort() {
        String string = PrefsUtils.getInstance().getString(TV_SORT, FilterVideoDialog.TV_DEFAULT_SORT);
        Intrinsics.checkNotNullExpressionValue(string, "getInstance().getString(…eoDialog.TV_DEFAULT_SORT)");
        return string;
    }

    public final void saveTVRating(String rating) {
        Intrinsics.checkNotNullParameter(rating, "rating");
        PrefsUtils.getInstance().putString(TV_RATING, rating);
    }

    public final String getTVRating() {
        String string = PrefsUtils.getInstance().getString(TV_RATING, "");
        Intrinsics.checkNotNullExpressionValue(string, "getInstance().getString(TV_RATING,\"\")");
        return string;
    }

    public final void saveTVGener(ArrayList<Integer> genreId) {
        Intrinsics.checkNotNullParameter(genreId, "genreId");
        PrefsUtils.getInstance().putString(TV_GENERS, JSONObject.toJSONString(genreId));
    }

    public final ArrayList<Integer> getTVGener() {
        String string = PrefsUtils.getInstance().getString(TV_GENERS, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(JSONObject.parseArray(string, Integer.TYPE));
    }

    public final void saveTVCountry(ArrayList<String> country) {
        Intrinsics.checkNotNullParameter(country, "country");
        PrefsUtils.getInstance().putString(TV_COUNTRYS, JSONObject.toJSONString(country));
    }

    public final ArrayList<String> getTVCountry() {
        String string = PrefsUtils.getInstance().getString(TV_COUNTRYS, "");
        String str = string;
        if (str == null || StringsKt.isBlank(str)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(JSONObject.parseArray(string, String.class));
    }

    public final void saveTVStartYear(float f) {
        PrefsUtils.getInstance().putFloat(TV_START_YEAR, f);
    }

    public final float getTVStartYear() {
        return PrefsUtils.getInstance().getFloat(TV_START_YEAR, 1900.0f);
    }

    public final void saveTVEndYear(float f) {
        PrefsUtils.getInstance().putFloat(TV_END_YEAR, f);
    }

    public final float getTVEndYear() {
        return PrefsUtils.getInstance().getFloat(TV_END_YEAR, Calendar.getInstance().get(1));
    }

    public final void saveNeverShowSmartDownloadDialog() {
        PrefsUtils.getInstance().putBoolean(NEVER_SHOW_SMART_DOWNLOAD, true);
    }

    public final boolean neverShowSmartDownloadDialog() {
        return PrefsUtils.getInstance().getBoolean(NEVER_SHOW_SMART_DOWNLOAD, false);
    }

    public final void saveSmartDownload(boolean z) {
        PrefsUtils.getInstance().putBoolean(SMART_DOWNLOAD, z);
    }

    public final boolean getSmartDownload() {
        return PrefsUtils.getInstance().getBoolean(SMART_DOWNLOAD, false);
    }

    public final void saveAutoDeleteWatched(boolean z) {
        PrefsUtils.getInstance().putBoolean(SMART_DOWNLOAD_AUTO_DELETE_WATCHED, z);
    }

    public final boolean getAutoDeleteWatched() {
        return PrefsUtils.getInstance().getBoolean(SMART_DOWNLOAD_AUTO_DELETE_WATCHED, true);
    }

    public final void saveSmartDownloadWifiOnly(boolean z) {
        PrefsUtils.getInstance().putBoolean(SMART_DOWNLOAD_WIFI_ONLY, z);
    }

    public final boolean getSmartDownloadWifiOnly() {
        return PrefsUtils.getInstance().getBoolean(SMART_DOWNLOAD_WIFI_ONLY, true);
    }

    public final void saveDownloadRule(String rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        SPStaticUtils.put(DOWNLOAD_QUALITY_RULE, rule);
    }

    public final String getDownloadRule() {
        String string = SPStaticUtils.getString(DOWNLOAD_QUALITY_RULE, "1080P,720P,360P,4K,8K,ORG");
        Intrinsics.checkNotNullExpressionValue(string, "getString(DOWNLOAD_QUALI…80P,720P,360P,4K,8K,ORG\")");
        return string;
    }

    public final String getCurrDownloadRule() {
        return (String) StringsKt.split$default((CharSequence) getDownloadRule(), new String[]{","}, false, 0, 6, (Object) null).get(0);
    }

    public final void saveAutoDownloadSelectQuality(boolean z) {
        SPStaticUtils.put(AUTO_SELECT_DOWNLOAD_QUALITY, z);
    }

    public final boolean isAutoDownloadSelectQuality() {
        return SPStaticUtils.getBoolean(AUTO_SELECT_DOWNLOAD_QUALITY, false);
    }

    public final boolean isAutoSelectPlayQuality() {
        return SPStaticUtils.getBoolean(AUTO_SELECT_PLAY_QUALITY, true);
    }

    public final void saveAutoSelectPlayQuality(boolean z) {
        SPStaticUtils.put(AUTO_SELECT_PLAY_QUALITY, z);
    }
}
