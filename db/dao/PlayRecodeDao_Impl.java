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
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public final class PlayRecodeDao_Impl implements PlayRecodeDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPlayRecode;
    private final EntityInsertionAdapter __insertionAdapterOfPlayRecode;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByTid;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfPlayRecode;

    public PlayRecodeDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPlayRecode = new EntityInsertionAdapter<PlayRecode>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `playrecode`(`id`,`box_type`,`imdb_id`,`movie_id`,`title`,`start_time`,`quality`,`season`,`episode`,`privateid`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlayRecode playRecode) {
                supportSQLiteStatement.bindLong(1, playRecode.getId());
                supportSQLiteStatement.bindLong(2, playRecode.getBox_type());
                if (playRecode.getImdb_id() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, playRecode.getImdb_id());
                }
                if (playRecode.getMovieId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, playRecode.getMovieId());
                }
                if (playRecode.getTitle() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, playRecode.getTitle());
                }
                supportSQLiteStatement.bindLong(6, playRecode.getStart_time());
                supportSQLiteStatement.bindLong(7, playRecode.getQuality());
                supportSQLiteStatement.bindLong(8, playRecode.getSeason());
                supportSQLiteStatement.bindLong(9, playRecode.getEpisode());
                if (playRecode.getPrivateid() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, playRecode.getPrivateid());
                }
            }
        };
        this.__deletionAdapterOfPlayRecode = new EntityDeletionOrUpdateAdapter<PlayRecode>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `playrecode` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlayRecode playRecode) {
                supportSQLiteStatement.bindLong(1, playRecode.getId());
            }
        };
        this.__updateAdapterOfPlayRecode = new EntityDeletionOrUpdateAdapter<PlayRecode>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `playrecode` SET `id` = ?,`box_type` = ?,`imdb_id` = ?,`movie_id` = ?,`title` = ?,`start_time` = ?,`quality` = ?,`season` = ?,`episode` = ?,`privateid` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, PlayRecode playRecode) {
                supportSQLiteStatement.bindLong(1, playRecode.getId());
                supportSQLiteStatement.bindLong(2, playRecode.getBox_type());
                if (playRecode.getImdb_id() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, playRecode.getImdb_id());
                }
                if (playRecode.getMovieId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, playRecode.getMovieId());
                }
                if (playRecode.getTitle() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, playRecode.getTitle());
                }
                supportSQLiteStatement.bindLong(6, playRecode.getStart_time());
                supportSQLiteStatement.bindLong(7, playRecode.getQuality());
                supportSQLiteStatement.bindLong(8, playRecode.getSeason());
                supportSQLiteStatement.bindLong(9, playRecode.getEpisode());
                if (playRecode.getPrivateid() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, playRecode.getPrivateid());
                }
                supportSQLiteStatement.bindLong(11, playRecode.getId());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playrecode WHERE movie_id = ?";
            }
        };
        this.__preparedStmtOfDeleteByTid = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playrecode WHERE privateid== ? ";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playrecode";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void insert(PlayRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlayRecode.insert((EntityInsertionAdapter) playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void delete(PlayRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPlayRecode.handle(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void update(PlayRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPlayRecode.handle(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void deleteById(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteById.acquire();
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
            this.__preparedStmtOfDeleteById.release(acquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void deleteByTid(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteByTid.acquire();
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
            this.__preparedStmtOfDeleteByTid.release(acquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
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

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public List<PlayRecode> getAll() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PlayRecode playRecode = new PlayRecode();
                playRecode.setId(query.getInt(columnIndexOrThrow));
                playRecode.setBox_type(query.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(query.getString(columnIndexOrThrow3));
                playRecode.setMovieId(query.getString(columnIndexOrThrow4));
                playRecode.setTitle(query.getString(columnIndexOrThrow5));
                playRecode.setStart_time(query.getInt(columnIndexOrThrow6));
                playRecode.setQuality(query.getInt(columnIndexOrThrow7));
                playRecode.setSeason(query.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(query.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(query.getString(columnIndexOrThrow10));
                arrayList.add(playRecode);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public List<PlayRecode> findByType(int i) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? LIMIT 1", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PlayRecode playRecode = new PlayRecode();
                playRecode.setId(query.getInt(columnIndexOrThrow));
                playRecode.setBox_type(query.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(query.getString(columnIndexOrThrow3));
                playRecode.setMovieId(query.getString(columnIndexOrThrow4));
                playRecode.setTitle(query.getString(columnIndexOrThrow5));
                playRecode.setStart_time(query.getInt(columnIndexOrThrow6));
                playRecode.setQuality(query.getInt(columnIndexOrThrow7));
                playRecode.setSeason(query.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(query.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(query.getString(columnIndexOrThrow10));
                arrayList.add(playRecode);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public PlayRecode findByType(int i, String str) {
        PlayRecode playRecode;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? AND privateid = ? LIMIT 1 ", 2);
        acquire.bindLong(1, i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            if (query.moveToFirst()) {
                playRecode = new PlayRecode();
                playRecode.setId(query.getInt(columnIndexOrThrow));
                playRecode.setBox_type(query.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(query.getString(columnIndexOrThrow3));
                playRecode.setMovieId(query.getString(columnIndexOrThrow4));
                playRecode.setTitle(query.getString(columnIndexOrThrow5));
                playRecode.setStart_time(query.getInt(columnIndexOrThrow6));
                playRecode.setQuality(query.getInt(columnIndexOrThrow7));
                playRecode.setSeason(query.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(query.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(query.getString(columnIndexOrThrow10));
            } else {
                playRecode = null;
            }
            return playRecode;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public PlayRecode findByTypeid(int i, String str) {
        PlayRecode playRecode;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? AND movie_id = ? LIMIT 1 ", 2);
        acquire.bindLong(1, i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            if (query.moveToFirst()) {
                playRecode = new PlayRecode();
                playRecode.setId(query.getInt(columnIndexOrThrow));
                playRecode.setBox_type(query.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(query.getString(columnIndexOrThrow3));
                playRecode.setMovieId(query.getString(columnIndexOrThrow4));
                playRecode.setTitle(query.getString(columnIndexOrThrow5));
                playRecode.setStart_time(query.getInt(columnIndexOrThrow6));
                playRecode.setQuality(query.getInt(columnIndexOrThrow7));
                playRecode.setSeason(query.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(query.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(query.getString(columnIndexOrThrow10));
            } else {
                playRecode = null;
            }
            return playRecode;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public PlayRecode findByEpisode(int i, String str, int i2, int i3) {
        PlayRecode playRecode;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? AND movie_id = ? AND season= ? AND episode= ?   LIMIT 1 ", 4);
        acquire.bindLong(1, i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, i2);
        acquire.bindLong(4, i3);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start_time");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            if (query.moveToFirst()) {
                playRecode = new PlayRecode();
                playRecode.setId(query.getInt(columnIndexOrThrow));
                playRecode.setBox_type(query.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(query.getString(columnIndexOrThrow3));
                playRecode.setMovieId(query.getString(columnIndexOrThrow4));
                playRecode.setTitle(query.getString(columnIndexOrThrow5));
                playRecode.setStart_time(query.getInt(columnIndexOrThrow6));
                playRecode.setQuality(query.getInt(columnIndexOrThrow7));
                playRecode.setSeason(query.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(query.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(query.getString(columnIndexOrThrow10));
            } else {
                playRecode = null;
            }
            return playRecode;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
