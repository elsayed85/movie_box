package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.android.gms.cast.MediaTrack;
import com.movieboxpro.android.db.entity.TestNetRecode;
/* loaded from: classes3.dex */
public final class TestNetRecodeDao_Impl implements TestNetRecodeDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfTestNetRecode;
    private final EntityInsertionAdapter __insertionAdapterOfTestNetRecode;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfTestNetRecode;

    public TestNetRecodeDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfTestNetRecode = new EntityInsertionAdapter<TestNetRecode>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `testnetrecode`(`ids`,`id`,`country`,`description`,`domain`,`display_order`,`ratio`,`url`,`startTime`,`endTime`,`state`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TestNetRecode testNetRecode) {
                supportSQLiteStatement.bindLong(1, testNetRecode.getIds());
                supportSQLiteStatement.bindLong(2, testNetRecode.id);
                if (testNetRecode.country == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, testNetRecode.country);
                }
                if (testNetRecode.description == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, testNetRecode.description);
                }
                if (testNetRecode.domain == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, testNetRecode.domain);
                }
                supportSQLiteStatement.bindLong(6, testNetRecode.display_order);
                supportSQLiteStatement.bindDouble(7, testNetRecode.ratio);
                if (testNetRecode.url == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, testNetRecode.url);
                }
                if (testNetRecode.startTime == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, testNetRecode.startTime.longValue());
                }
                if (testNetRecode.endTime == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindLong(10, testNetRecode.endTime.longValue());
                }
                if (testNetRecode.state == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, testNetRecode.state);
                }
            }
        };
        this.__deletionAdapterOfTestNetRecode = new EntityDeletionOrUpdateAdapter<TestNetRecode>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `testnetrecode` WHERE `ids` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TestNetRecode testNetRecode) {
                supportSQLiteStatement.bindLong(1, testNetRecode.getIds());
            }
        };
        this.__updateAdapterOfTestNetRecode = new EntityDeletionOrUpdateAdapter<TestNetRecode>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `testnetrecode` SET `ids` = ?,`id` = ?,`country` = ?,`description` = ?,`domain` = ?,`display_order` = ?,`ratio` = ?,`url` = ?,`startTime` = ?,`endTime` = ?,`state` = ? WHERE `ids` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, TestNetRecode testNetRecode) {
                supportSQLiteStatement.bindLong(1, testNetRecode.getIds());
                supportSQLiteStatement.bindLong(2, testNetRecode.id);
                if (testNetRecode.country == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, testNetRecode.country);
                }
                if (testNetRecode.description == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, testNetRecode.description);
                }
                if (testNetRecode.domain == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, testNetRecode.domain);
                }
                supportSQLiteStatement.bindLong(6, testNetRecode.display_order);
                supportSQLiteStatement.bindDouble(7, testNetRecode.ratio);
                if (testNetRecode.url == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, testNetRecode.url);
                }
                if (testNetRecode.startTime == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, testNetRecode.startTime.longValue());
                }
                if (testNetRecode.endTime == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindLong(10, testNetRecode.endTime.longValue());
                }
                if (testNetRecode.state == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, testNetRecode.state);
                }
                supportSQLiteStatement.bindLong(12, testNetRecode.getIds());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM testnetrecode WHERE ids = ?";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM testnetrecode";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void insert(TestNetRecode testNetRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTestNetRecode.insert((EntityInsertionAdapter) testNetRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void delete(TestNetRecode testNetRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTestNetRecode.handle(testNetRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void update(TestNetRecode testNetRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTestNetRecode.handle(testNetRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void deleteById(int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
        acquire.bindLong(1, i);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void clear() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfClear.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClear.release(acquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public TestNetRecode findAll(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM testnetrecode WHERE ids== ? LIMIT 1 ", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "ids");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "country");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MediaTrack.ROLE_DESCRIPTION);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "domain");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "display_order");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "ratio");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "startTime");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "endTime");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "state");
            TestNetRecode testNetRecode = null;
            if (query.moveToFirst()) {
                TestNetRecode testNetRecode2 = new TestNetRecode();
                testNetRecode2.setIds(query.getInt(columnIndexOrThrow));
                testNetRecode2.id = query.getInt(columnIndexOrThrow2);
                testNetRecode2.country = query.getString(columnIndexOrThrow3);
                testNetRecode2.description = query.getString(columnIndexOrThrow4);
                testNetRecode2.domain = query.getString(columnIndexOrThrow5);
                testNetRecode2.display_order = query.getInt(columnIndexOrThrow6);
                testNetRecode2.ratio = query.getFloat(columnIndexOrThrow7);
                testNetRecode2.url = query.getString(columnIndexOrThrow8);
                if (query.isNull(columnIndexOrThrow9)) {
                    testNetRecode2.startTime = null;
                } else {
                    testNetRecode2.startTime = Long.valueOf(query.getLong(columnIndexOrThrow9));
                }
                if (query.isNull(columnIndexOrThrow10)) {
                    testNetRecode2.endTime = null;
                } else {
                    testNetRecode2.endTime = Long.valueOf(query.getLong(columnIndexOrThrow10));
                }
                testNetRecode2.state = query.getString(columnIndexOrThrow11);
                testNetRecode = testNetRecode2;
            }
            return testNetRecode;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
