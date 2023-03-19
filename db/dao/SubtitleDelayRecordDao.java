package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.SubtitleDelayRecord;
/* loaded from: classes3.dex */
public interface SubtitleDelayRecordDao {
    void clear();

    SubtitleDelayRecord findDelayRecordBySid(String str);

    long insert(SubtitleDelayRecord subtitleDelayRecord);
}
