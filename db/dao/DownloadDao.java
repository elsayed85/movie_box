package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.Download;
import java.util.List;
/* loaded from: classes3.dex */
public interface DownloadDao {
    void clear();

    void delete(Download download);

    void deleteById(String str);

    void deleteByTid(String str);

    Download findByIdAndType(String str, int i, int i2);

    List<Download> findBySeason(String str, int i, int i2);

    List<Download> findByStatue(int i);

    List<Download> findByTid(String str, int i, int i2);

    Download findByTidAndSeasonEpisode(String str, int i, int i2);

    Download findByType(int i, String str);

    List<Download> findByType(int i);

    Download findByTypes(int i);

    List<Download> findByTypes2(int i);

    List<Download> findId(int i, int i2);

    List<Download> getAll();

    void insert(Download download);

    List<Download> selectByTvId(String str, int i);

    List<Download> selectByTvId(String str, int i, int i2);

    void update(Download download);
}
