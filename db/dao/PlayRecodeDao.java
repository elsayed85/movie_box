package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.PlayRecode;
import java.util.List;
/* loaded from: classes3.dex */
public interface PlayRecodeDao {
    void clear();

    void delete(PlayRecode playRecode);

    void deleteById(String str);

    void deleteByTid(String str);

    PlayRecode findByEpisode(int i, String str, int i2, int i3);

    PlayRecode findByType(int i, String str);

    List<PlayRecode> findByType(int i);

    PlayRecode findByTypeid(int i, String str);

    List<PlayRecode> getAll();

    void insert(PlayRecode playRecode);

    void update(PlayRecode playRecode);
}
