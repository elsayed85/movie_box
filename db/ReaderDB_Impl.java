package com.movieboxpro.android.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.messaging.Constants;
import com.movieboxpro.android.db.dao.ChartDao;
import com.movieboxpro.android.db.dao.ChartDao_Impl;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.dao.DownloadDao_Impl;
import com.movieboxpro.android.db.dao.PlayRecodeDao;
import com.movieboxpro.android.db.dao.PlayRecodeDao_Impl;
import com.movieboxpro.android.db.dao.SubtitleDelayRecordDao;
import com.movieboxpro.android.db.dao.SubtitleDelayRecordDao_Impl;
import com.movieboxpro.android.db.dao.TestNetRecodeDao;
import com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import java.util.HashMap;
import java.util.HashSet;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public final class ReaderDB_Impl extends ReaderDB {
    private volatile ChartDao _chartDao;
    private volatile DownloadDao _downloadDao;
    private volatile PlayRecodeDao _playRecodeDao;
    private volatile SubtitleDelayRecordDao _subtitleDelayRecordDao;
    private volatile TestNetRecodeDao _testNetRecodeDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(10) { // from class: com.movieboxpro.android.db.ReaderDB_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Char_list` (`id` TEXT NOT NULL, `user_id` TEXT, `book_id` TEXT, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `downloads` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `movie_id` TEXT, `mb_id` TEXT, `title` TEXT, `vip_only` TEXT, `display` TEXT, `poster` TEXT, `runtime` TEXT, `add_time` TEXT, `is_collect` INTEGER NOT NULL, `download` TEXT, `fileName` TEXT, `path` TEXT, `quality` TEXT, `fileLength` INTEGER NOT NULL, `size` TEXT, `statue` INTEGER NOT NULL, `progress` INTEGER NOT NULL, `speed` INTEGER NOT NULL, `format` TEXT, `count` TEXT, `dateline` INTEGER NOT NULL, `mmfid` TEXT, `season` INTEGER NOT NULL, `episode` INTEGER NOT NULL, `box_type` INTEGER NOT NULL, `tmfid` INTEGER NOT NULL, `seasonid` TEXT, `seasontitle` TEXT, `seasonthumbs` TEXT, `privateid` TEXT, `fid` TEXT, `contentRating` TEXT, `failReason` TEXT, `imdbid` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `playrecode` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `box_type` INTEGER NOT NULL, `imdb_id` TEXT, `movie_id` TEXT, `title` TEXT, `start_time` INTEGER NOT NULL, `quality` INTEGER NOT NULL, `season` INTEGER NOT NULL, `episode` INTEGER NOT NULL, `privateid` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `testnetrecode` (`ids` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` INTEGER NOT NULL, `country` TEXT, `description` TEXT, `domain` TEXT, `display_order` INTEGER NOT NULL, `ratio` REAL NOT NULL, `url` TEXT, `startTime` INTEGER, `endTime` INTEGER, `state` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `subtitle_delay_record` (`delaySecond` INTEGER NOT NULL, `sid` TEXT NOT NULL, PRIMARY KEY(`sid`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '157a8463cb23beddca223d4732b4d4c0')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Char_list`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `downloads`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `playrecode`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `testnetrecode`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `subtitle_delay_record`");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (ReaderDB_Impl.this.mCallbacks != null) {
                    int size = ReaderDB_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ReaderDB_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ReaderDB_Impl.this.mDatabase = supportSQLiteDatabase;
                ReaderDB_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (ReaderDB_Impl.this.mCallbacks != null) {
                    int size = ReaderDB_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ReaderDB_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(3);
                hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1));
                hashMap.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0));
                hashMap.put("book_id", new TableInfo.Column("book_id", "TEXT", false, 0));
                TableInfo tableInfo = new TableInfo("Char_list", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "Char_list");
                if (!tableInfo.equals(read)) {
                    throw new IllegalStateException("Migration didn't properly handle Char_list(com.movieboxpro.android.db.entity.Chart).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(35);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
                hashMap2.put(MovieDetailActivity.MOVIE_ID, new TableInfo.Column(MovieDetailActivity.MOVIE_ID, "TEXT", false, 0));
                hashMap2.put("mb_id", new TableInfo.Column("mb_id", "TEXT", false, 0));
                hashMap2.put("title", new TableInfo.Column("title", "TEXT", false, 0));
                hashMap2.put("vip_only", new TableInfo.Column("vip_only", "TEXT", false, 0));
                hashMap2.put(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, new TableInfo.Column(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION, "TEXT", false, 0));
                hashMap2.put("poster", new TableInfo.Column("poster", "TEXT", false, 0));
                hashMap2.put("runtime", new TableInfo.Column("runtime", "TEXT", false, 0));
                hashMap2.put("add_time", new TableInfo.Column("add_time", "TEXT", false, 0));
                hashMap2.put("is_collect", new TableInfo.Column("is_collect", "INTEGER", true, 0));
                hashMap2.put(DownloadInfo.DOWNLOAD, new TableInfo.Column(DownloadInfo.DOWNLOAD, "TEXT", false, 0));
                hashMap2.put("fileName", new TableInfo.Column("fileName", "TEXT", false, 0));
                hashMap2.put(FileDownloadService.PARAMS_KEY_PATH, new TableInfo.Column(FileDownloadService.PARAMS_KEY_PATH, "TEXT", false, 0));
                hashMap2.put("quality", new TableInfo.Column("quality", "TEXT", false, 0));
                hashMap2.put("fileLength", new TableInfo.Column("fileLength", "INTEGER", true, 0));
                hashMap2.put("size", new TableInfo.Column("size", "TEXT", false, 0));
                hashMap2.put("statue", new TableInfo.Column("statue", "INTEGER", true, 0));
                hashMap2.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0));
                hashMap2.put("speed", new TableInfo.Column("speed", "INTEGER", true, 0));
                hashMap2.put(IjkMediaMeta.IJKM_KEY_FORMAT, new TableInfo.Column(IjkMediaMeta.IJKM_KEY_FORMAT, "TEXT", false, 0));
                hashMap2.put("count", new TableInfo.Column("count", "TEXT", false, 0));
                hashMap2.put("dateline", new TableInfo.Column("dateline", "INTEGER", true, 0));
                hashMap2.put("mmfid", new TableInfo.Column("mmfid", "TEXT", false, 0));
                hashMap2.put("season", new TableInfo.Column("season", "INTEGER", true, 0));
                hashMap2.put("episode", new TableInfo.Column("episode", "INTEGER", true, 0));
                hashMap2.put("box_type", new TableInfo.Column("box_type", "INTEGER", true, 0));
                hashMap2.put("tmfid", new TableInfo.Column("tmfid", "INTEGER", true, 0));
                hashMap2.put("seasonid", new TableInfo.Column("seasonid", "TEXT", false, 0));
                hashMap2.put("seasontitle", new TableInfo.Column("seasontitle", "TEXT", false, 0));
                hashMap2.put("seasonthumbs", new TableInfo.Column("seasonthumbs", "TEXT", false, 0));
                hashMap2.put("privateid", new TableInfo.Column("privateid", "TEXT", false, 0));
                hashMap2.put("fid", new TableInfo.Column("fid", "TEXT", false, 0));
                hashMap2.put("contentRating", new TableInfo.Column("contentRating", "TEXT", false, 0));
                hashMap2.put("failReason", new TableInfo.Column("failReason", "TEXT", false, 0));
                hashMap2.put("imdbid", new TableInfo.Column("imdbid", "TEXT", false, 0));
                TableInfo tableInfo2 = new TableInfo("downloads", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "downloads");
                if (!tableInfo2.equals(read2)) {
                    throw new IllegalStateException("Migration didn't properly handle downloads(com.movieboxpro.android.db.entity.Download).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(10);
                hashMap3.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
                hashMap3.put("box_type", new TableInfo.Column("box_type", "INTEGER", true, 0));
                hashMap3.put("imdb_id", new TableInfo.Column("imdb_id", "TEXT", false, 0));
                hashMap3.put(MovieDetailActivity.MOVIE_ID, new TableInfo.Column(MovieDetailActivity.MOVIE_ID, "TEXT", false, 0));
                hashMap3.put("title", new TableInfo.Column("title", "TEXT", false, 0));
                hashMap3.put("start_time", new TableInfo.Column("start_time", "INTEGER", true, 0));
                hashMap3.put("quality", new TableInfo.Column("quality", "INTEGER", true, 0));
                hashMap3.put("season", new TableInfo.Column("season", "INTEGER", true, 0));
                hashMap3.put("episode", new TableInfo.Column("episode", "INTEGER", true, 0));
                hashMap3.put("privateid", new TableInfo.Column("privateid", "TEXT", false, 0));
                TableInfo tableInfo3 = new TableInfo("playrecode", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "playrecode");
                if (!tableInfo3.equals(read3)) {
                    throw new IllegalStateException("Migration didn't properly handle playrecode(com.movieboxpro.android.db.entity.PlayRecode).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(11);
                hashMap4.put("ids", new TableInfo.Column("ids", "INTEGER", true, 1));
                hashMap4.put("id", new TableInfo.Column("id", "INTEGER", true, 0));
                hashMap4.put("country", new TableInfo.Column("country", "TEXT", false, 0));
                hashMap4.put(MediaTrack.ROLE_DESCRIPTION, new TableInfo.Column(MediaTrack.ROLE_DESCRIPTION, "TEXT", false, 0));
                hashMap4.put("domain", new TableInfo.Column("domain", "TEXT", false, 0));
                hashMap4.put("display_order", new TableInfo.Column("display_order", "INTEGER", true, 0));
                hashMap4.put("ratio", new TableInfo.Column("ratio", "REAL", true, 0));
                hashMap4.put("url", new TableInfo.Column("url", "TEXT", false, 0));
                hashMap4.put("startTime", new TableInfo.Column("startTime", "INTEGER", false, 0));
                hashMap4.put("endTime", new TableInfo.Column("endTime", "INTEGER", false, 0));
                hashMap4.put("state", new TableInfo.Column("state", "TEXT", false, 0));
                TableInfo tableInfo4 = new TableInfo("testnetrecode", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "testnetrecode");
                if (!tableInfo4.equals(read4)) {
                    throw new IllegalStateException("Migration didn't properly handle testnetrecode(com.movieboxpro.android.db.entity.TestNetRecode).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                HashMap hashMap5 = new HashMap(2);
                hashMap5.put("delaySecond", new TableInfo.Column("delaySecond", "INTEGER", true, 0));
                hashMap5.put("sid", new TableInfo.Column("sid", "TEXT", true, 1));
                TableInfo tableInfo5 = new TableInfo("subtitle_delay_record", hashMap5, new HashSet(0), new HashSet(0));
                TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "subtitle_delay_record");
                if (tableInfo5.equals(read5)) {
                    return;
                }
                throw new IllegalStateException("Migration didn't properly handle subtitle_delay_record(com.movieboxpro.android.db.entity.SubtitleDelayRecord).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
        }, "157a8463cb23beddca223d4732b4d4c0", "02162c94cbcce0f1d56ff42f50ecb369")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "Char_list", "downloads", "playrecode", "testnetrecode", "subtitle_delay_record");
    }

    @Override // com.movieboxpro.android.db.ReaderDB, androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Char_list`");
            writableDatabase.execSQL("DELETE FROM `downloads`");
            writableDatabase.execSQL("DELETE FROM `playrecode`");
            writableDatabase.execSQL("DELETE FROM `testnetrecode`");
            writableDatabase.execSQL("DELETE FROM `subtitle_delay_record`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public ChartDao chartDao() {
        ChartDao chartDao;
        if (this._chartDao != null) {
            return this._chartDao;
        }
        synchronized (this) {
            if (this._chartDao == null) {
                this._chartDao = new ChartDao_Impl(this);
            }
            chartDao = this._chartDao;
        }
        return chartDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public DownloadDao downloadDao() {
        DownloadDao downloadDao;
        if (this._downloadDao != null) {
            return this._downloadDao;
        }
        synchronized (this) {
            if (this._downloadDao == null) {
                this._downloadDao = new DownloadDao_Impl(this);
            }
            downloadDao = this._downloadDao;
        }
        return downloadDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public PlayRecodeDao playRecodeDao() {
        PlayRecodeDao playRecodeDao;
        if (this._playRecodeDao != null) {
            return this._playRecodeDao;
        }
        synchronized (this) {
            if (this._playRecodeDao == null) {
                this._playRecodeDao = new PlayRecodeDao_Impl(this);
            }
            playRecodeDao = this._playRecodeDao;
        }
        return playRecodeDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public TestNetRecodeDao testnetRecodeDao() {
        TestNetRecodeDao testNetRecodeDao;
        if (this._testNetRecodeDao != null) {
            return this._testNetRecodeDao;
        }
        synchronized (this) {
            if (this._testNetRecodeDao == null) {
                this._testNetRecodeDao = new TestNetRecodeDao_Impl(this);
            }
            testNetRecodeDao = this._testNetRecodeDao;
        }
        return testNetRecodeDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public SubtitleDelayRecordDao subtitleDelayRecordDao() {
        SubtitleDelayRecordDao subtitleDelayRecordDao;
        if (this._subtitleDelayRecordDao != null) {
            return this._subtitleDelayRecordDao;
        }
        synchronized (this) {
            if (this._subtitleDelayRecordDao == null) {
                this._subtitleDelayRecordDao = new SubtitleDelayRecordDao_Impl(this);
            }
            subtitleDelayRecordDao = this._subtitleDelayRecordDao;
        }
        return subtitleDelayRecordDao;
    }
}
