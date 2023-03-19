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
import com.movieboxpro.android.db.entity.Chart;
/* loaded from: classes3.dex */
public final class ChartDao_Impl implements ChartDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfChart;
    private final EntityInsertionAdapter __insertionAdapterOfChart;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfClearNull;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByBookID;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfChart;

    public ChartDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfChart = new EntityInsertionAdapter<Chart>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Char_list`(`id`,`user_id`,`book_id`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Chart chart) {
                if (chart.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, chart.getId());
                }
                if (chart.getUserId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, chart.getUserId());
                }
                if (chart.getBookId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, chart.getBookId());
                }
            }
        };
        this.__deletionAdapterOfChart = new EntityDeletionOrUpdateAdapter<Chart>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `Char_list` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Chart chart) {
                if (chart.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, chart.getId());
                }
            }
        };
        this.__updateAdapterOfChart = new EntityDeletionOrUpdateAdapter<Chart>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `Char_list` SET `id` = ?,`user_id` = ?,`book_id` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Chart chart) {
                if (chart.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, chart.getId());
                }
                if (chart.getUserId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, chart.getUserId());
                }
                if (chart.getBookId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, chart.getBookId());
                }
                if (chart.getId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, chart.getId());
                }
            }
        };
        this.__preparedStmtOfClearNull = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Char_list WHERE book_id IS NULL";
            }
        };
        this.__preparedStmtOfDeleteByBookID = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Char_list WHERE book_id = ?";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Char_list";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void insert(Chart chart) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfChart.insert((EntityInsertionAdapter) chart);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void delete(Chart chart) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfChart.handle(chart);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void update(Chart chart) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfChart.handle(chart);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void clearNull() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfClearNull.acquire();
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClearNull.release(acquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void deleteByBookID(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByBookID.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByBookID.release(acquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
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

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public Chart loadByID(String str) {
        Chart chart;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM Char_list WHERE book_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "user_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "book_id");
            if (query.moveToFirst()) {
                chart = new Chart();
                chart.setId(query.getString(columnIndexOrThrow));
                chart.setUserId(query.getString(columnIndexOrThrow2));
                chart.setBookId(query.getString(columnIndexOrThrow3));
            } else {
                chart = null;
            }
            return chart;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
