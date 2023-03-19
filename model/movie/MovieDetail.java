package com.movieboxpro.android.model.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadModel;
import com.movieboxpro.android.model.TrailerItem;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes3.dex */
public class MovieDetail extends BaseMediaModel implements Serializable {
    public String actors;
    public String content_rating;
    public List<MovieFile> file;
    public List<MovieListModel.MovieListItem> playlists;
    public String[] quality;
    public List<Recommend> recommend;
    public String runtime;
    public int seconds;
    public String source_file;
    public String trailer;
    private List<TrailerItem> trailer_url_arr;
    public String writer;

    /* loaded from: classes3.dex */
    public static class MovieFile implements Serializable {
        public String bitstream;
        public String count;
        public long dateline;
        public String filename;
        public String format;
        public String mmfid;
        public String path;
        public String quality;
        public String size;
        public String vip_only;
    }

    /* loaded from: classes3.dex */
    public static class Recommend implements Serializable {
        public String imdb_rating;
        public String mid;
        public String poster;
        public String quality_tag_new;
        public String title;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public int getBoxType() {
        return 1;
    }

    public MovieDetail() {
        this.runtime = "";
        this.content_rating = "";
        this.file = new ArrayList();
    }

    public MovieDetail(DownloadModel downloadModel) {
        this.runtime = "";
        this.content_rating = "";
        this.file = new ArrayList();
        this.privateId = downloadModel.getPrivateid();
        this.id = downloadModel.getMovieId();
        this.mb_id = downloadModel.getMbId();
        this.box_type = downloadModel.getBox_type();
        this.title = downloadModel.getTitle();
        this.display = Integer.parseInt(downloadModel.getDisplay());
        this.poster = downloadModel.getPoster();
        this.runtime = downloadModel.getRuntime();
        this.add_time = Long.parseLong(downloadModel.getAdd_time());
        this.is_collect = downloadModel.getIs_collect();
        this.download = downloadModel.getDownloads();
        this.fileName = downloadModel.getFileName();
        this.path = downloadModel.getPath();
        this.qualitys = downloadModel.getQuality();
        this.size = downloadModel.getSize();
        this.format = downloadModel.getFormat();
        this.vip_only = Integer.valueOf(downloadModel.getVip_only()).intValue();
        this.count = downloadModel.getCount();
        this.dateline = downloadModel.getDateline();
        this.fileLength = downloadModel.getFileLength();
        this.statue = downloadModel.getStatue();
        this.progress = downloadModel.getProgress();
        this.speed = downloadModel.getSpeed();
        this.mmfid = downloadModel.getMmfid();
        this.content_rating = downloadModel.content_rating;
        this.imdb_id = downloadModel.getImdbid();
        BaseMediaModel.DownloadFile downloadFile = new BaseMediaModel.DownloadFile();
        downloadFile.path = downloadModel.getPath();
        downloadFile.quality = downloadModel.getQuality();
        downloadFile.format = downloadModel.getFormat();
        downloadFile.size = downloadModel.getSize();
        downloadFile.vip_only = Integer.valueOf(downloadModel.getVip_only()).intValue();
        downloadFile.count = downloadModel.getCount();
        downloadFile.dateline = downloadModel.getDateline();
        downloadFile.filename = downloadModel.getFileName();
        downloadFile.mmfid = downloadModel.mmfid;
        downloadFile.real_quality = downloadModel.quality;
        try {
            downloadFile.fid = Integer.parseInt(downloadModel.fid);
        } catch (NumberFormatException unused) {
        }
        this.list.add(downloadFile);
    }

    public List<TrailerItem> getTrailer_url_arr() {
        return this.trailer_url_arr;
    }

    public void setTrailer_url_arr(List<TrailerItem> list) {
        this.trailer_url_arr = list;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public boolean saveInDao(int i, Activity activity) {
        super.saveInDao(i, activity);
        if (this.list.get(i).path == null) {
            return false;
        }
        Download download = new Download();
        download.setMovieId(this.id);
        download.setMbId(this.mb_id);
        download.setBox_type(this.box_type);
        download.setTitle(this.title);
        download.setDisplay(String.valueOf(this.display));
        download.setPoster(this.poster);
        download.setRuntime(this.runtime);
        download.setAdd_time(String.valueOf(this.add_time));
        download.setIs_collect(this.is_collect);
        download.setDownload(this.download);
        download.setFileName(this.list.get(i).filename);
        download.setPath(getDownloadPath(this.list.get(i).path));
        download.setQuality(this.list.get(i).real_quality);
        download.setSize(this.list.get(i).size);
        download.setFormat(this.list.get(i).format);
        download.setVip_only(this.list.get(i).vip_only + "");
        download.setCount(this.list.get(i).count);
        download.setDateline(this.list.get(i).dateline);
        download.setMmfid(this.list.get(i).tmfid);
        download.setFileLength(this.fileLength);
        download.setStatue(0);
        download.setProgress(this.progress);
        download.setSpeed(this.speed);
        download.setPrivateid(this.id);
        download.setFid(String.valueOf(this.list.get(i).fid));
        download.setMmfid(this.list.get(i).mmfid);
        download.setContent_rating(this.content_rating);
        download.setImdbid(this.imdb_id);
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
    public String getId() {
        return this.id;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public String getTid() {
        return this.id;
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getUrl() {
        String str;
        String str2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        return Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", this.id, "", str2, str);
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public Observable<String> getSrt() {
        return Http.getService().Movie_srt_auto(API.BASE_URL, API.Movie.MOVIE_SRT_AUTO, this.id, Locale.getDefault().getLanguage(), App.isLogin() ? App.getUserData().uid_v2 : "");
    }

    @Override // com.movieboxpro.android.model.BaseMediaModel, com.movieboxpro.android.model.MediaFunction
    public void startService(String str, Activity activity) {
        Intent intent = new Intent(activity, DownloadService.class);
        Bundle build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, this.id).addParam(Constant.Download.PARAMS_KEY_TYPE, this.box_type).build();
        intent.setAction(str);
        intent.putExtras(build);
        activity.startService(intent);
    }
}
