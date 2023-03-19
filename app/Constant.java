package com.movieboxpro.android.app;
/* loaded from: classes.dex */
public class Constant {
    public static final String APPNEXT_INTERSTITIAL_KEY = "3fb0812b-78b9-4d0c-9039-5b3b9ac83027";
    public static final int BBS_ID_ACTOR = 41;
    public static final int BBS_ID_MOVIE = 2;
    public static final int BBS_ID_MOVIE_LIST = 37;
    public static final int BBS_ID_MOVIE_MUSIC = 57;
    public static final int BBS_ID_TV = 36;
    public static final String CHANNEL_KEY = "UMENG_CHANNEL";
    public static String CLICK_EVENT_JS = "";
    public static final String CLOUDMOBI_INTERSTITIAL_KEY = "33728081";
    public static String DIR = "";
    public static String[] DIRS = null;
    public static String[] DIRS_CLEAR = null;
    public static String DIR_CACHE = null;
    public static String DIR_CONFIG = null;
    public static String DIR_CROP = null;
    public static String DIR_DOLOAD_SUBTITLE = null;
    public static String DIR_DOWNLOAD = null;
    public static String DIR_DOWNLOAD_MOVIE_SUBTITLE = null;
    public static String DIR_DOWNLOAD_TEST_SPEED = null;
    public static String DIR_DOWNLOAD_TV_SUBTITLE = null;
    public static String DIR_FONT = null;
    public static String DIR_LOG = null;
    public static String DIR_NET_DATA_CACHE = null;
    public static String DIR_NOVEL = null;
    public static String DIR_PICTURE = null;
    public static String DIR_SUBTITLE = null;
    public static String DIR_THUMB = null;
    public static String DIR_TRANS_CODE_SUBTITLE = null;
    public static String DIR_TXT = null;
    public static String DIR_UPLOAD_MOVIE_SUBTITLE = null;
    public static String DIR_UPLOAD_SUBTITLE = null;
    public static String DIR_UPLOAD_TV_SUBTITLE = null;
    public static String DIR_VIDEO = null;
    public static String DIR_WEB_DOWNLOAD_SUBTITLE = null;
    public static final String FLURRY_API_KEY = "Z77PK52YJHKX48RBP3KQ";
    public static final String GOOGLE_APP_ID = "ca-app-pub-1206750276639556~9871969718";
    public static final String GOOGLE_BANNER_AD_KEY = "ca-app-pub-1206750276639556/9700030585";
    public static final String GOOGLE_FULLSCREEN_AD_KEY = "ca-app-pub-1206750276639556/4858767440";
    public static final String GOOGLE_SEARCH_AD_KEY = "ca-app-pub-1206750276639556/5858780504";
    public static final String GOOGLE_YOUTUBE_KEY = "AIzaSyDGWqlptSVtyyf42cluBm69EmaCW6w0SCY";
    public static final int HTTP_PORT = 8888;
    public static final String LECAST_APPID = "12469";
    public static final String LECAST_APPSECRET = "db1c1d748a3f30d6a6e2fa55957acd35";
    public static final String MAX_DOWNLOAD_COUNT = "max_download_count";
    public static final int MAX_SCREEN_CODE = -88;
    public static final String MINTEGRAL_IMAGE_INTERSTITIAL_KEY = "181760";
    public static final String MINTEGRAL_VIDEO_INTERSTITIAL_KEY = "181677";
    public static final String PANDA_BBS_URL = "https://www.kfpanda.club";
    public static final String SUFFIX_ZIP = ".zip";
    public static final String WIFI_AP_STATE_CHANGED_ACTION = "android.net.wifi.WIFI_AP_STATE_CHANGED";

    /* loaded from: classes3.dex */
    public static final class ACTION {
        public static final String DELETE_DOWNLOADED_TV = "com.movieboxpro.android.ACTION_DELETE_DOWNLOADED_TV";
        public static final String DOWNLOAD_FILE = "com.movieboxpro.android.ACTION_DOWNLOAD_FILE";
        public static final String DOWNLOAD_FILE_COMPLETE = "com.movieboxpro.android.DOWNLOAD_FILE_COMPLETE";
        public static final String DOWNLOAD_FILE_FAILURE = "com.movieboxpro.android.DOWNLOAD_FILE_FAILURE";
        public static final String DOWNLOAD_FILE_PROGRESS = "com.movieboxpro.android.DOWNLOAD_FILE_PROGRESS";
        public static final String DOWNLOAD_FILE_STARTED = "com.movieboxpro.android.DOWNLOAD_FILE_STARTED";
        public static final String DOWNLOAD_MOVIE_COMPLETE = "com.movieboxpro.android.DOWNLOAD_MOVIE_COMPLETE";
        public static final String DOWNLOAD_MOVIE_DELETE = "com.movieboxpro.android.DOWNLOAD_MOVIE_DELETE";
        public static final String DOWNLOAD_MOVIE_FAILURE = "com.movieboxpro.android.DOWNLOAD_MOVIE_FAILURE";
        public static final String DOWNLOAD_MOVIE_PAUSED = "com.movieboxpro.android.DOWNLOAD_MOVIE_PAUSED";
        public static final String DOWNLOAD_MOVIE_PROGRESS = "com.movieboxpro.android.DOWNLOAD_MOVIE_PROGRESS";
        public static final String DOWNLOAD_MOVIE_READY = "com.movieboxpro.android.DOWNLOAD_MOVIE_READY";
        public static final String DOWNLOAD_MOVIE_STARTED = "com.movieboxpro.android.DOWNLOAD_MOVIE_STARTED";
        public static final String DOWNLOAD_NOTIFY_DELETE = "com.movieboxpro.android.ACTION_NOTIFY_DELETE";
        public static final String DOWNLOAD_NOTIFY_FAIL = "com.movieboxpro.android.ACTION_NOTIFY_FAIL";
        public static final String DOWNLOAD_NOTIFY_PAUSE = "com.movieboxpro.android.ACTION_NOTIFY_PAUSE";
        public static final String DOWNLOAD_NOTIFY_PROGRESS = "com.movieboxpro.android.ACTION_NOTIFY_PROGRESS";
        public static final String DOWNLOAD_NOTIFY_START = "com.movieboxpro.android.ACTION_NOTIFY_START";
        public static final String DOWNLOAD_NOTIFY_SUCCESS = "com.movieboxpro.android.ACTION_NOTIFY_SUCCESS";
        public static final String MOVIE_ALLDOWNLOAD = "com.movieboxpro.android.ACTION_DOWNLOAD_ALLMOVIE";
        public static final String MOVIE_DELETE = "com.movieboxpro.android.ACTION_DOWNLOAD_DELETE";
        public static final String MOVIE_DOWNLOAD = "com.movieboxpro.android.ACTION_DOWNLOAD_MOVIE";
        public static final String MOVIE_ERROR = "com.movieboxpro.android.ACTION_DOWNLOAD_ERROR";
        public static final String MOVIE_PAUSED = "com.movieboxpro.android.ACTION_DOWNLOAD_PAUSED";
        public static final String MOVIE_PAUSEDALL = "com.movieboxpro.android.ACTION_DOWNLOAD_PAUSEDALL";
        public static final String MOVIE_RESET_MAX_COUNT = "com.movieboxpro.android.ACTION_DOWNLOAD_RESET_MAX_COUNT";
        public static final String MOVIE_STARTALL = "com.movieboxpro.android.ACTION_DOWNLOAD_STARTALL";
        public static final String MOVIE_TV_DELETE = "com.movieboxpro.android.ACTION_DOWNLOAD_TV_DELETE";
    }

    /* loaded from: classes3.dex */
    public static final class Download {
        public static final String PARAMS_KEY_EPISODE = "params_key_episode";
        public static final String PARAMS_KEY_MOVIE = "params_key_movie";
        public static final String PARAMS_KEY_MOVIE_ID = "params_key_movie_id";
        public static final String PARAMS_KEY_MOVIE_TYPE = "params_key_movie_type";
        public static final String PARAMS_KEY_PATH = "params_key_path";
        public static final String PARAMS_KEY_PROGRESS = "params_key_progress";
        public static final String PARAMS_KEY_REASON = "params_key_reason";
        public static final String PARAMS_KEY_SEASON = "params_key_season";
        public static final String PARAMS_KEY_SIZE = "params_key_size";
        public static final String PARAMS_KEY_TYPE = "params_key_type";
        public static final int STATUS_DELETE = 5;
        public static final int STATUS_DOWNLOADED = 2;
        public static final int STATUS_DOWNLOADING = 1;
        public static final int STATUS_ERROR = 4;
        public static final int STATUS_IDLE = 0;
        public static final int STATUS_PASUED = 3;
    }

    /* loaded from: classes3.dex */
    public static final class Prefs {
        public static final String AUDIO_SYN_TIME = "audio_syn_time";
        public static final String AUTO_LANDSCAPE = "auto_landscape";
        public static final String AUTO_PLAY_NEXT = "auto_play_next";
        public static final String AUTO_SELECT_SUBTITLE = "auto_select_subtitle";
        public static final String BLACK_MODE = "black_mode";
        public static final String CHAPTER_PRICE = "chapter_price";
        public static final String CHECK_TIME_REMIND = "check_time_remind";
        public static final String CHILD_MODE = "child_mode";
        public static final String CHILD_MODE_PASSWORD = "child_mode_password";
        public static final float DEFAULT_CHAPTER_PRICE = 0.1f;
        public static final String DLNA_CAST_SEARCH_TIME = "dlna_cast_search_time";
        public static final String DOWNLOAD_DIR = "download_dir";
        public static final String ENTER_BACK_TIME = "enter_back_time";
        public static final String FAVORITE_FILTER_ALL = "favorite_filter_all";
        public static final String FAVORITE_FILTER_COLLECT = "favorite_filter_collect";
        public static final String FAVORITE_FILTER_MOVIE = "favorite_filter_movie";
        public static final String FAVORITE_FILTER_TV = "favorite_filter_tv";
        public static final String FAVORITE_FILTER_WAITING = "favorite_filter_waiting";
        public static final String FAVORITE_FILTER_WATCHED = "favorite_filter_watched";
        public static final String FILTER_GENER = "filter_gener";
        public static final String FLIP_STYLE = "flipStyle";
        public static final int FLIP_STYLE_NONE = 0;
        public static final int FLIP_STYLE_OVERFLOW = 2;
        public static final int FLIP_STYLE_SCROLL = 3;
        public static final int FLIP_STYLE_SIMULATE = 1;
        public static final String FULLSCREEN_PLAY = "fullscreen_play";
        public static final String HIDE_MOVIELIST = "hide_movielist";
        public static final String INTERNAL_STORAGE = "internal_storage";
        public static final String ISNIGHT = "isNight";
        public static final String IS_FIRST_OPEN = "is_first_open";
        public static final String IS_FIRST_SHORTCUT = "is_first_shorcut";
        public static final String IS_LAST_ORIGIN = "is_last_origin";
        public static final String Incognito_Mode = "incognito_mode";
        public static final String LANGUAGE = "language";
        public static final String LAST_SELECT_QUALITY = "last_select_quality";
        public static final String MOVIE_COUNTRY = "movie_country_list";
        public static final String NETWORK_GROUP = "network_group";
        public static final String NETWORK_STATE = "network_state";
        public static final String OPEN_HARD_CODEC = "open_hard_codec";
        public static final String OPEN_SUBTITLE_BG = "open_subtitle_bg";
        public static final String PLAYER_ENGINE = "player_engine";
        public static final String PLAY_SCALE_VALUE = "play_scale_value";
        public static final String PLAY_SPEED = "play_speed";
        public static final String REMEMBER_ORG_QUALITY = "remember_org_quality";
        public static final String REMEMBER_PLAY_SPEED = "remember_play_speed";
        public static final String REVIEW_SORT = "review_sort";
        public static final String SEARCH_FILTER_ALL = "search_filter_all";
        public static final String SEARCH_FILTER_MOVIE = "search_filter_movie";
        public static final String SEARCH_FILTER_TV = "search_filter_tv";
        public static final String SETTING = "setting";
        public static final String SHOW_ADVERT_FRIST = "show_advert_frist";
        public static final String SHOW_BDYY_FRIST = "show_bdyy_frist";
        public static final String SHOW_FLOAT = "show_float";
        public static final String SHOW_H265 = "show_h265";
        public static final String SHOW_READ_TIPS = "showReadTips";
        public static final String SKIP_OPENING_ENDING = "skip_opening_ending";
        public static final String SUBTITLE_BG_COLOR = "subtitle_bg_color";
        public static final String SUBTITLE_COLOR = "subtitle_color";
        public static final String SUBTITLE_FONT = "subtitle_font";
        public static final String SUBTITLE_POS = "subtitle_pos";
        public static final String SUBTITLE_SIZE = "subtitle_size";
        public static final String SUPER_CHILD_MODE = "super_child_mode";
        public static final String SUPER_CHILD_MODE_PASSWORD = "super_child_mode_password";
        public static final String TVDETAIL_SORT = "tvdetail_sort";
        public static final String TVDETAIL_YEARS = "tvdetail_year";
        public static final String TV_COUNTRY = "movie_country_list";
        public static final String USER_CELLUAR_DOWNLOAD = "user_celluar_download";
    }

    /* loaded from: classes3.dex */
    public static final class Video {
        public static final String VIDEO_EPISODE = "videoplayer_episode";
        public static final String VIDEO_ID_MOVIE = "videoplayer_id_movie";
        public static final String VIDEO_ID_TV = "videoplayer_id_tv";
        public static final String VIDEO_PARAMS = "videoplayer_params";
        public static final String VIDEO_SEASON = "videoplayer_season";
    }
}
