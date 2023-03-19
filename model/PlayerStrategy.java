package com.movieboxpro.android.model;

import android.app.Activity;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.model.detail.AbstractVideoBean;
import com.movieboxpro.android.model.detail.MovieBean;
import com.movieboxpro.android.model.detail.TvBean;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.MLog;
import java.io.Serializable;
/* loaded from: classes3.dex */
public class PlayerStrategy implements Serializable {
    private MediaFunction play;

    public void setInstace(MediaFunction mediaFunction) {
        this.play = mediaFunction;
    }

    public MediaFunction getInstace() {
        return this.play;
    }

    public int choose() {
        return this.play.ChoosePlayer();
    }

    public BaseMediaModel getDownload(Download download) {
        if (download != null) {
            download.getBox_type();
        }
        this.play = null;
        return null;
    }

    public Download getDownload() {
        Download findByType;
        int boxType = this.play.getBoxType();
        try {
            if (boxType != 1) {
                if (boxType == 2) {
                    TvDetail tvDetail = (TvDetail) TvDetail.class.cast(this.play);
                    if (tvDetail != null) {
                        MLog.d("player", "电视tid" + tvDetail.box_type + "电视剧集" + tvDetail.id + "_" + tvDetail.seasonDetail.id);
                        DownloadDao downloadDao = App.getDB().downloadDao();
                        int i = tvDetail.box_type;
                        StringBuilder sb = new StringBuilder();
                        sb.append(tvDetail.id);
                        sb.append("_");
                        sb.append(tvDetail.seasonDetail.id);
                        findByType = downloadDao.findByType(i, sb.toString());
                    }
                }
                return null;
            } else if (((MovieDetail) MovieDetail.class.cast(this.play)) == null) {
                return null;
            } else {
                findByType = App.getDB().downloadDao().findByType(this.play.getBoxType(), this.play.getId());
            }
            return findByType;
        } catch (Exception unused) {
            return null;
        }
    }

    public AbstractVideoBean switchBean() {
        int boxType = this.play.getBoxType();
        if (boxType != 1) {
            if (boxType == 2) {
                return new TvBean();
            }
            return new MovieBean();
        }
        return new MovieBean();
    }

    public String getId() {
        return this.play.getId();
    }

    public int getSeason() {
        return this.play.getSeason();
    }

    public int getEpisode() {
        return this.play.getEpisode();
    }

    public boolean SaveInDao(int i, Activity activity) {
        return this.play.saveInDao(i, activity);
    }

    public void addDonwload(BaseMediaModel baseMediaModel) {
        this.play.addDonwload(baseMediaModel);
    }
}
