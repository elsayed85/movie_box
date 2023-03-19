package com.movieboxpro.android.model.tv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/* loaded from: classes3.dex */
public class TvDetail extends BaseMediaModel implements Serializable {
    public String actors;
    public String banner_mini;
    public String content_rating;
    public List<SeasonDetail> episode;
    public String history;
    public String[] season;
    public SeasonDetail seasonDetail;
    public String season_episode;
    public String year_year;
    public String[] years;

    /* loaded from: classes3.dex */
    public static class History implements Serializable {
        public int episode;
        public int over;
        public int season;
        public int seconds;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getBoxType() {
        return 2;
    }

    public TvDetail() {
        this.episode = new ArrayList();
        this.content_rating = "";
        this.seasonDetail = new SeasonDetail();
    }

    public TvDetail(BaseMediaModel baseMediaModel) {
        this.episode = new ArrayList();
        this.content_rating = "";
        this.seasonDetail = new SeasonDetail();
        this.id = baseMediaModel.id;
    }

    public TvDetail(DownloadModel downloadModel) {
        this.episode = new ArrayList();
        this.content_rating = "";
        this.seasonDetail = new SeasonDetail();
        this.privateId = downloadModel.getPrivateid();
        this.id = downloadModel.getMovieId();
        this.title = downloadModel.getTitle();
        this.poster = downloadModel.getPoster();
        this.box_type = downloadModel.getBox_type();
        this.seasonDetail.season = downloadModel.getSeason();
        this.seasonDetail.episode = downloadModel.getEpisode();
        this.seasonDetail.runtime = downloadModel.getRuntime();
        this.display = Integer.parseInt(downloadModel.getDisplay());
        this.add_time = Long.parseLong(downloadModel.getAdd_time());
        this.seasonDetail.download = Integer.parseInt(downloadModel.getDownloads());
        this.seasonDetail.id = downloadModel.getSeasonid();
        this.seasonDetail.title = downloadModel.getSeasontitle();
        this.seasonDetail.thumbs = downloadModel.getSeasonthumbs();
        this.content_rating = downloadModel.content_rating;
        this.fileName = downloadModel.getFileName();
        this.path = downloadModel.getPath();
        this.qualitys = downloadModel.getQuality();
        this.size = downloadModel.getSize();
        this.format = downloadModel.getFormat();
        this.vip_only = Integer.parseInt(downloadModel.getVip_only());
        this.count = downloadModel.getCount();
        this.dateline = downloadModel.getDateline();
        this.tmfid = downloadModel.getTmfid();
        this.mmfid = downloadModel.getMmfid();
        this.imdb_id = downloadModel.getImdbid();
        this.fileLength = downloadModel.getFileLength();
        this.statue = downloadModel.getStatue();
        this.progress = downloadModel.getProgress();
        this.speed = downloadModel.getSpeed();
        BaseMediaModel.DownloadFile downloadFile = new BaseMediaModel.DownloadFile();
        downloadFile.path = downloadModel.getPath();
        downloadFile.quality = downloadModel.getQuality();
        downloadFile.format = downloadModel.getFormat();
        downloadFile.size = downloadModel.getSize();
        downloadFile.vip_only = Integer.valueOf(downloadModel.getVip_only()).intValue();
        downloadFile.count = downloadModel.getCount();
        downloadFile.dateline = downloadModel.getDateline();
        downloadFile.filename = downloadModel.getFileName();
        downloadFile.mmfid = downloadModel.getMmfid();
        downloadFile.tmfid = String.valueOf(downloadModel.getTmfid());
        downloadFile.real_quality = downloadModel.quality;
        try {
            downloadFile.fid = Integer.parseInt(downloadModel.fid);
        } catch (NumberFormatException unused) {
        }
        this.list.add(downloadFile);
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String str) {
        this.actors = str;
    }

    public String getBanner_mini() {
        return this.banner_mini;
    }

    public void setBanner_mini(String str) {
        this.banner_mini = str;
    }

    public void setSeason(String[] strArr) {
        this.season = strArr;
    }

    public String[] getYears() {
        return this.years;
    }

    public void setYears(String[] strArr) {
        this.years = strArr;
    }

    public String getHistory() {
        return this.history;
    }

    public void setHistory(String str) {
        this.history = str;
    }

    public void setEpisode(List<SeasonDetail> list) {
        this.episode = list;
    }

    public String getYear_year() {
        return this.year_year;
    }

    public void setYear_year(String str) {
        this.year_year = str;
    }

    public String getContent_rating() {
        return this.content_rating;
    }

    public void setContent_rating(String str) {
        this.content_rating = str;
    }

    public String getSeason_episode() {
        return this.season_episode;
    }

    public void setSeason_episode(String str) {
        this.season_episode = str;
    }

    public SeasonDetail getSeasonDetail() {
        return this.seasonDetail;
    }

    public void setSeasonDetail(SeasonDetail seasonDetail) {
        this.seasonDetail = seasonDetail;
    }

    /* loaded from: classes3.dex */
    public static class SeasonDetail implements Serializable {
        public long add_time;
        public int code_file;
        public int dislike_total;
        public int download;
        public int episode;
        public String id;
        public String imdb_id;
        public String imdb_id_status;
        public String imdb_rating;
        public int like_status;
        public int like_total;
        public String mb_id;
        public HashMap<String, Long> play_progress;
        public String quality_tag;
        public String released;
        public long released_timestamp;
        public String runtime;
        public int season;
        public int source_file;
        public int srt_status;
        public int state;
        private String status;
        public String synopsis;
        public String thumbs;
        public String tid;
        public String title;
        public int tomato_meter;
        public long update_time;
        public int view;

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getId() {
        return this.id + "_" + this.box_type + "_" + this.seasonDetail.season + "_" + this.seasonDetail.episode;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getTid() {
        return this.id;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getSeason() {
        return this.seasonDetail.season;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getEpisode() {
        return this.seasonDetail.episode;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getUrl() {
        return Http.getService().Tv_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLOADURL, App.isLogin() ? App.getUserData().uid_v2 : "", this.id, this.seasonDetail.season, this.seasonDetail.episode);
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getSrt() {
        return Http.getService().Tv_srt_auto(API.BASE_URL, API.Tv.TV_SRT_AUTO, this.id, this.seasonDetail.season, this.seasonDetail.episode, Locale.getDefault().getLanguage(), App.isLogin() ? App.getUserData().uid_v2 : "");
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public boolean saveInDao(int i, Activity activity) {
        super.saveInDao(i, activity);
        if (this.seasonDetail == null || this.list.get(i).path == null) {
            return false;
        }
        Download download = new Download();
        download.setPrivateid(this.id + "_" + this.seasonDetail.id);
        download.setMovieId(this.id);
        download.setTitle(this.title);
        download.setPoster(this.poster);
        download.setIs_collect(this.is_collect);
        download.setBox_type(this.box_type);
        download.setSeason(this.seasonDetail.season);
        download.setEpisode(this.seasonDetail.episode);
        download.setRuntime(this.seasonDetail.runtime);
        download.setDisplay(String.valueOf(this.display));
        download.setAdd_time(String.valueOf(this.add_time));
        download.setDownload(this.seasonDetail.download + "");
        download.setSeasonid(this.seasonDetail.id);
        download.setSeasontitle(this.seasonDetail.title);
        download.setSeasonthumbs(this.seasonDetail.thumbs);
        download.setFileName(this.list.get(i).filename);
        download.setPath(getDownloadPath(this.list.get(i).path));
        download.setQuality(this.list.get(i).real_quality);
        download.setSize(this.list.get(i).size);
        download.setFormat(this.list.get(i).format);
        download.setVip_only(this.list.get(i).vip_only + "");
        download.setCount(this.list.get(i).count);
        download.setDateline(this.list.get(i).dateline);
        download.setMmfid(this.list.get(i).mmfid);
        download.setFileLength(this.fileLength);
        download.setStatue(0);
        download.setProgress(this.progress);
        download.setSpeed(this.speed);
        download.setFid(String.valueOf(this.list.get(i).fid));
        download.setImdbid(this.imdb_id);
        if (this.list.get(i).tmfid != null) {
            download.setTmfid(Integer.parseInt(this.list.get(i).tmfid));
        }
        download.setContent_rating(this.content_rating);
        App.getDB().downloadDao().insert(download);
        startService(Constant.ACTION.MOVIE_DOWNLOAD, activity);
        return true;
    }

    private String getDownloadPath(String str) {
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "Error");
        if (!"0".equalsIgnoreCase(PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "")) || string.equals("Error")) {
            return str;
        }
        String[] split = str.split("/");
        StringBuilder sb = new StringBuilder();
        if (split.length > 2) {
            split[2] = string;
            for (int i = 2; i < split.length; i++) {
                if (i != split.length - 1) {
                    sb.append(split[i]);
                    sb.append("/");
                } else {
                    sb.append(split[i]);
                }
            }
            return sb.toString();
        }
        return str;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public void startService(String str, Activity activity) {
        Intent intent = new Intent(activity, DownloadService.class);
        ParamsUtils.Builder newBuilder = ParamsUtils.newBuilder();
        Bundle build = newBuilder.addParam(Constant.Download.PARAMS_KEY_MOVIE, this.id + "_" + this.seasonDetail.id).addParam(Constant.Download.PARAMS_KEY_TYPE, this.box_type).build();
        intent.setAction(str);
        intent.putExtras(build);
        activity.startService(intent);
    }
}
