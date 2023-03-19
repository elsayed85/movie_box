package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.TestNetRecode;
/* loaded from: classes3.dex */
public interface TestNetRecodeDao {
    void clear();

    void delete(TestNetRecode testNetRecode);

    void deleteById(int i);

    TestNetRecode findAll(int i);

    void insert(TestNetRecode testNetRecode);

    void update(TestNetRecode testNetRecode);
}
