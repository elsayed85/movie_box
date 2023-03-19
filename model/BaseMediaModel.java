package com.movieboxpro.android.model;

import android.app.Activity;
import android.text.TextUtils;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.CheckUtils;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class BaseMediaModel implements MediaFunction, Serializable {
    public long add_time;
    public int audience_score;
    public String[] audio_lang;
    public int box_type;
    public String cats;
    public int code_file;
    public String collect;
    public int collect_watched;
    public String count;
    public long dateline;
    public String description;
    public String director;
    public int dislike_total;
    public int display;
    public String download;
    public int episode;
    public long fileLength;
    public String fileName;
    public String format;
    public String id;
    public String imdb_id;
    public String imdb_link;
    public int is_collect;
    public List<MoreLanguage> language;
    public int like_status;
    public int like_total;
    public String mb_id;
    public String mmfid;
    public String path;
    public long plan_watched_mark_time;
    public String poster;
    public String poster_min;
    public String privateId;
    public String[] quality;
    public String quality_tag_new;
    public String[] quality_tags_new;
    public String qualitys;
    public String released;
    public long released_timestamp;
    public int season;
    public int seconds;
    public String size;
    public long speed;
    public int state;
    public String title;
    public int tmfid;
    public int tomato_meter;
    public String tomato_url;
    public String trailer_url;
    public long update_time;
    public String view;
    public int vip_only;
    public String year = "";
    public int isChecked = 0;
    public String imdb_rating = "";
    public int plan_watched = -1;
    public int statue = 0;
    public int progress = 0;
    public List<DownloadFile> list = new ArrayList();

    /* loaded from: classes3.dex */
    public static class DownloadFile implements Serializable {
        public String bitstream;
        public int colorbit;
        public String count;
        public long dateline;
        public int fid;
        public String filename;
        public String format;
        public int h265;
        public boolean hasOrg;
        public int hdr;
        public boolean lastItem;
        public String mmfid;
        public boolean originShow;
        public int original;
        public String path;
        public String[] path2;
        public String quality;
        public String real_quality;
        public String size;
        public String tmfid;
        public int viewType;
        public int vip_only;
    }

    /* loaded from: classes3.dex */
    public static class MoreLanguage implements Serializable {
        public String lang;
        public String title;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int getBoxType() {
        return 0;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int getEpisode() {
        return 0;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public String getId() {
        return "";
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int getSeason() {
        return 0;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public Observable<String> getSrt() {
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public String getTid() {
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public Observable<String> getUrl() {
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public void startService(String str, Activity activity) {
    }

    public int getLike_status() {
        return this.like_status;
    }

    public void setLike_status(int i) {
        this.like_status = i;
    }

    public boolean isFileExists() {
        return CheckUtils.checkFileExists(getFilePath(), "");
    }

    public String getFilePath() {
        if (getFileName() != null) {
            return Constant.DIR_DOWNLOAD;
        }
        return null;
    }

    private String getFileName() {
        if (this.title != null) {
            return this.title + ".MP4";
        }
        return null;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public void addDonwload(BaseMediaModel baseMediaModel) {
        this.seconds = baseMediaModel.seconds;
        this.quality = baseMediaModel.quality;
        this.list = baseMediaModel.list;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public int ChoosePlayer() {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).path != null && !TextUtils.isEmpty(this.list.get(i).path)) {
                if (i > 4) {
                    return 4;
                }
                return i;
            }
        }
        return 0;
    }

    @Override // com.movieboxpro.android.model.MediaFunction
    public boolean saveInDao(int i, Activity activity) {
        List<DownloadFile> list = this.list;
        return list != null && list.size() <= i;
    }
}
