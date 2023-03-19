package com.movieboxpro.android.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.ares.downloader.jarvis.Jarvis;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.ParamsUtils;
/* loaded from: classes3.dex */
public class DownloadModel {
    public String add_time;
    public int box_type;
    public int childCount;
    public String content_rating;
    public String count;
    public long dateline;
    public String display;
    public Jarvis.Downloader downloader;
    public String downloads;
    public int episode;
    private String failReason;
    public String fid;
    public long fileLength;
    public String fileName;
    public String format;
    private String imdbid;
    public int isChecked;
    public int is_collect;
    public String localPath;
    public String mbId;
    public String mmfid;
    public String movieId;
    public String path;
    public String poster;
    public String privateid;
    public int progress;
    public String quality;
    public String runtime;
    public int season;
    public String seasonid;
    public String seasonthumbs;
    public String seasontitle;
    public String size;
    public long speed;
    public int statue;
    public String title;
    public int tmfid;
    public String vip_only;

    public DownloadModel() {
        this.vip_only = "0";
        this.statue = 0;
        this.progress = 0;
        this.isChecked = 0;
        this.childCount = 0;
    }

    public DownloadModel(Download download) {
        this.vip_only = "0";
        this.statue = 0;
        this.progress = 0;
        this.isChecked = 0;
        this.childCount = 0;
        this.movieId = download.getMovieId();
        this.mbId = download.getMbId();
        this.title = download.getTitle();
        this.vip_only = download.getVip_only();
        this.display = download.getDisplay();
        this.poster = download.getPoster();
        this.runtime = download.getRuntime();
        this.add_time = download.getAdd_time();
        this.is_collect = download.getIs_collect();
        this.downloads = download.getDownload();
        this.fileName = download.getFileName();
        this.path = download.getPath();
        this.quality = download.getQuality();
        this.fileLength = download.getFileLength();
        this.size = download.getSize();
        this.statue = download.getStatue();
        this.progress = download.getProgress();
        this.speed = download.getSpeed();
        this.format = download.getFormat();
        this.count = download.getCount();
        this.dateline = download.getDateline();
        this.mmfid = download.getMmfid();
        this.season = download.getSeason();
        this.episode = download.getEpisode();
        this.box_type = download.getBox_type();
        this.tmfid = download.getTmfid();
        this.seasonid = download.getSeasonid();
        this.seasontitle = download.getSeasontitle();
        this.seasonthumbs = download.getSeasonthumbs();
        this.privateid = download.getPrivateid();
        this.fid = download.getFid();
        this.content_rating = download.getContent_rating();
        this.failReason = download.getFailReason();
        this.imdbid = download.getImdbid();
    }

    public String getImdbid() {
        return this.imdbid;
    }

    public void setImdbid(String str) {
        this.imdbid = str;
    }

    public String getFailReason() {
        return this.failReason;
    }

    public void setFailReason(String str) {
        this.failReason = str;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public void setChildCount(int i) {
        this.childCount = i;
    }

    public Jarvis.Downloader getDownloader() {
        return this.downloader;
    }

    public void setDownloader(Jarvis.Downloader downloader) {
        this.downloader = downloader;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public void setMovieId(String str) {
        this.movieId = str;
    }

    public String getMbId() {
        return this.mbId;
    }

    public void setMbId(String str) {
        this.mbId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getVip_only() {
        return this.vip_only;
    }

    public void setVip_only(String str) {
        this.vip_only = str;
    }

    public String getDisplay() {
        return this.display;
    }

    public void setDisplay(String str) {
        this.display = str;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String str) {
        this.poster = str;
    }

    public String getRuntime() {
        return this.runtime;
    }

    public void setRuntime(String str) {
        this.runtime = str;
    }

    public String getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(String str) {
        this.add_time = str;
    }

    public int getIs_collect() {
        return this.is_collect;
    }

    public void setIs_collect(int i) {
        this.is_collect = i;
    }

    public String getDownloads() {
        return this.downloads;
    }

    public void setDownloads(String str) {
        this.downloads = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String getQuality() {
        return this.quality;
    }

    public void setQuality(String str) {
        this.quality = str;
    }

    public long getFileLength() {
        return this.fileLength;
    }

    public void setFileLength(long j) {
        this.fileLength = j;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String str) {
        this.size = str;
    }

    public int getStatue() {
        return this.statue;
    }

    public void setStatue(int i) {
        this.statue = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public long getSpeed() {
        return this.speed;
    }

    public void setSpeed(long j) {
        this.speed = j;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public String getCount() {
        return this.count;
    }

    public void setCount(String str) {
        this.count = str;
    }

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long j) {
        this.dateline = j;
    }

    public String getMmfid() {
        return this.mmfid;
    }

    public void setMmfid(String str) {
        this.mmfid = str;
    }

    public int getSeason() {
        return this.season;
    }

    public void setSeason(int i) {
        this.season = i;
    }

    public int getEpisode() {
        return this.episode;
    }

    public void setEpisode(int i) {
        this.episode = i;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int i) {
        this.box_type = i;
    }

    public int getTmfid() {
        return this.tmfid;
    }

    public void setTmfid(int i) {
        this.tmfid = i;
    }

    public String getSeasonid() {
        return this.seasonid;
    }

    public void setSeasonid(String str) {
        this.seasonid = str;
    }

    public String getSeasontitle() {
        return this.seasontitle;
    }

    public void setSeasontitle(String str) {
        this.seasontitle = str;
    }

    public String getSeasonthumbs() {
        return this.seasonthumbs;
    }

    public void setSeasonthumbs(String str) {
        this.seasonthumbs = str;
    }

    public String getPrivateid() {
        return this.privateid;
    }

    public void setPrivateid(String str) {
        this.privateid = str;
    }

    public int getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(int i) {
        this.isChecked = i;
    }

    public void startService(String str, Context context) {
        Intent intent = new Intent(context, DownloadService.class);
        Bundle build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, this.privateid).addParam(Constant.Download.PARAMS_KEY_TYPE, this.box_type).build();
        intent.setAction(str);
        intent.putExtras(build);
        context.startService(intent);
    }

    public void startService(String str, Activity activity) {
        Intent intent = new Intent(activity, DownloadService.class);
        Bundle build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, this.privateid).addParam(Constant.Download.PARAMS_KEY_TYPE, this.box_type).build();
        intent.setAction(str);
        intent.putExtras(build);
        activity.startService(intent);
    }

    public void deleteTvAll(Activity activity) {
        Intent intent = new Intent(activity, DownloadService.class);
        Bundle build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, this.movieId).addParam(Constant.Download.PARAMS_KEY_TYPE, this.box_type).build();
        intent.setAction(Constant.ACTION.DELETE_DOWNLOADED_TV);
        intent.putExtras(build);
        activity.startService(intent);
    }

    public void startService(String str, Activity activity, int i) {
        Bundle build;
        Intent intent = new Intent(activity, DownloadService.class);
        if (i == 2) {
            build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, this.privateid).addParam(Constant.Download.PARAMS_KEY_TYPE, i).build();
        } else {
            build = ParamsUtils.newBuilder().addParam(Constant.Download.PARAMS_KEY_MOVIE, this.movieId).addParam(Constant.Download.PARAMS_KEY_TYPE, i).build();
        }
        intent.setAction(str);
        intent.putExtras(build);
        activity.startService(intent);
    }
}
