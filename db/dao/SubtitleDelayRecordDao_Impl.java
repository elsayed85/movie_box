package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.movieboxpro.android.db.entity.SubtitleDelayRecord;
/* loaded from: classes3.dex */
public final class SubtitleDelayRecordDao_Impl implements SubtitleDelayRecordDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfSubtitleDelayRecord;
    private final SharedSQLiteStatement __preparedStmtOfClear;

    public SubtitleDelayRecordDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSubtitleDelayRecord = new EntityInsertionAdapter<SubtitleDelayRecord>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.SubtitleDelayRecordDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `subtitle_delay_record`(`delaySecond`,`sid`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, SubtitleDelayRecord subtitleDelayRecord) {
                supportSQLiteStatement.bindLong(1, subtitleDelayRecord.getDelaySecond());
                if (subtitleDelayRecord.getSid() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, subtitleDelayRecord.getSid());
                }
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.SubtitleDelayRecordDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM subtitle_delay_record";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.SubtitleDelayRecordDao
    public long insert(SubtitleDelayRecord subtitleDelayRecord) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfSubtitleDelayRecord.insertAndReturnId(subtitleDelayRecord);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.SubtitleDelayRecordDao
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

    @Override // com.movieboxpro.android.db.dao.SubtitleDelayRecordDao
    public SubtitleDelayRecord findDelayRecordBySid(String str) {
        SubtitleDelayRecord subtitleDelayRecord;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM subtitle_delay_record WHERE sid == ? LIMIT 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "delaySecond");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "sid");
            if (query.moveToFirst()) {
                subtitleDelayRecord = new SubtitleDelayRecord();
                subtitleDelayRecord.setDelaySecond(query.getInt(columnIndexOrThrow));
                subtitleDelayRecord.setSid(query.getString(columnIndexOrThrow2));
            } else {
                subtitleDelayRecord = null;
            }
            return subtitleDelayRecord;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
