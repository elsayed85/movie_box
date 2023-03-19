package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.Chart;
/* loaded from: classes3.dex */
public interface ChartDao {
    void clear();

    void clearNull();

    void delete(Chart chart);

    void deleteByBookID(String str);

    void insert(Chart chart);

    Chart loadByID(String str);

    void update(Chart chart);
}
