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
import com.google.firebase.messaging.Constants;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import java.util.ArrayList;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public final class DownloadDao_Impl implements DownloadDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDownload;
    private final EntityInsertionAdapter __insertionAdapterOfDownload;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByTid;
    private final EntityDeletionOrUpdateAdapter __updateAdapterOfDownload;

    public DownloadDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfDownload = new EntityInsertionAdapter<Download>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `downloads`(`id`,`movie_id`,`mb_id`,`title`,`vip_only`,`display`,`poster`,`runtime`,`add_time`,`is_collect`,`download`,`fileName`,`path`,`quality`,`fileLength`,`size`,`statue`,`progress`,`speed`,`format`,`count`,`dateline`,`mmfid`,`season`,`episode`,`box_type`,`tmfid`,`seasonid`,`seasontitle`,`seasonthumbs`,`privateid`,`fid`,`contentRating`,`failReason`,`imdbid`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Download download) {
                supportSQLiteStatement.bindLong(1, download.getId());
                if (download.getMovieId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, download.getMovieId());
                }
                if (download.getMbId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, download.getMbId());
                }
                if (download.getTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, download.getTitle());
                }
                if (download.getVip_only() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, download.getVip_only());
                }
                if (download.getDisplay() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, download.getDisplay());
                }
                if (download.getPoster() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, download.getPoster());
                }
                if (download.getRuntime() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, download.getRuntime());
                }
                if (download.getAdd_time() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, download.getAdd_time());
                }
                supportSQLiteStatement.bindLong(10, download.getIs_collect());
                if (download.getDownload() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, download.getDownload());
                }
                if (download.getFileName() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, download.getFileName());
                }
                if (download.getPath() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, download.getPath());
                }
                if (download.getQuality() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, download.getQuality());
                }
                supportSQLiteStatement.bindLong(15, download.getFileLength());
                if (download.getSize() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, download.getSize());
                }
                supportSQLiteStatement.bindLong(17, download.getStatue());
                supportSQLiteStatement.bindLong(18, download.getProgress());
                supportSQLiteStatement.bindLong(19, download.getSpeed());
                if (download.getFormat() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, download.getFormat());
                }
                if (download.getCount() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, download.getCount());
                }
                supportSQLiteStatement.bindLong(22, download.getDateline());
                if (download.getMmfid() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, download.getMmfid());
                }
                supportSQLiteStatement.bindLong(24, download.getSeason());
                supportSQLiteStatement.bindLong(25, download.getEpisode());
                supportSQLiteStatement.bindLong(26, download.getBox_type());
                supportSQLiteStatement.bindLong(27, download.getTmfid());
                if (download.getSeasonid() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, download.getSeasonid());
                }
                if (download.getSeasontitle() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, download.getSeasontitle());
                }
                if (download.getSeasonthumbs() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, download.getSeasonthumbs());
                }
                if (download.getPrivateid() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, download.getPrivateid());
                }
                if (download.getFid() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, download.getFid());
                }
                if (download.getContent_rating() == null) {
                    supportSQLiteStatement.bindNull(33);
                } else {
                    supportSQLiteStatement.bindString(33, download.getContent_rating());
                }
                if (download.getFailReason() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, download.getFailReason());
                }
                if (download.getImdbid() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, download.getImdbid());
                }
            }
        };
        this.__deletionAdapterOfDownload = new EntityDeletionOrUpdateAdapter<Download>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `downloads` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Download download) {
                supportSQLiteStatement.bindLong(1, download.getId());
            }
        };
        this.__updateAdapterOfDownload = new EntityDeletionOrUpdateAdapter<Download>(roomDatabase) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `downloads` SET `id` = ?,`movie_id` = ?,`mb_id` = ?,`title` = ?,`vip_only` = ?,`display` = ?,`poster` = ?,`runtime` = ?,`add_time` = ?,`is_collect` = ?,`download` = ?,`fileName` = ?,`path` = ?,`quality` = ?,`fileLength` = ?,`size` = ?,`statue` = ?,`progress` = ?,`speed` = ?,`format` = ?,`count` = ?,`dateline` = ?,`mmfid` = ?,`season` = ?,`episode` = ?,`box_type` = ?,`tmfid` = ?,`seasonid` = ?,`seasontitle` = ?,`seasonthumbs` = ?,`privateid` = ?,`fid` = ?,`contentRating` = ?,`failReason` = ?,`imdbid` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Download download) {
                supportSQLiteStatement.bindLong(1, download.getId());
                if (download.getMovieId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, download.getMovieId());
                }
                if (download.getMbId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, download.getMbId());
                }
                if (download.getTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, download.getTitle());
                }
                if (download.getVip_only() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, download.getVip_only());
                }
                if (download.getDisplay() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, download.getDisplay());
                }
                if (download.getPoster() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, download.getPoster());
                }
                if (download.getRuntime() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, download.getRuntime());
                }
                if (download.getAdd_time() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, download.getAdd_time());
                }
                supportSQLiteStatement.bindLong(10, download.getIs_collect());
                if (download.getDownload() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, download.getDownload());
                }
                if (download.getFileName() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, download.getFileName());
                }
                if (download.getPath() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, download.getPath());
                }
                if (download.getQuality() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, download.getQuality());
                }
                supportSQLiteStatement.bindLong(15, download.getFileLength());
                if (download.getSize() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, download.getSize());
                }
                supportSQLiteStatement.bindLong(17, download.getStatue());
                supportSQLiteStatement.bindLong(18, download.getProgress());
                supportSQLiteStatement.bindLong(19, download.getSpeed());
                if (download.getFormat() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, download.getFormat());
                }
                if (download.getCount() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, download.getCount());
                }
                supportSQLiteStatement.bindLong(22, download.getDateline());
                if (download.getMmfid() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, download.getMmfid());
                }
                supportSQLiteStatement.bindLong(24, download.getSeason());
                supportSQLiteStatement.bindLong(25, download.getEpisode());
                supportSQLiteStatement.bindLong(26, download.getBox_type());
                supportSQLiteStatement.bindLong(27, download.getTmfid());
                if (download.getSeasonid() == null) {
                    supportSQLiteStatement.bindNull(28);
                } else {
                    supportSQLiteStatement.bindString(28, download.getSeasonid());
                }
                if (download.getSeasontitle() == null) {
                    supportSQLiteStatement.bindNull(29);
                } else {
                    supportSQLiteStatement.bindString(29, download.getSeasontitle());
                }
                if (download.getSeasonthumbs() == null) {
                    supportSQLiteStatement.bindNull(30);
                } else {
                    supportSQLiteStatement.bindString(30, download.getSeasonthumbs());
                }
                if (download.getPrivateid() == null) {
                    supportSQLiteStatement.bindNull(31);
                } else {
                    supportSQLiteStatement.bindString(31, download.getPrivateid());
                }
                if (download.getFid() == null) {
                    supportSQLiteStatement.bindNull(32);
                } else {
                    supportSQLiteStatement.bindString(32, download.getFid());
                }
                if (download.getContent_rating() == null) {
                    supportSQLiteStatement.bindNull(33);
                } else {
                    supportSQLiteStatement.bindString(33, download.getContent_rating());
                }
                if (download.getFailReason() == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, download.getFailReason());
                }
                if (download.getImdbid() == null) {
                    supportSQLiteStatement.bindNull(35);
                } else {
                    supportSQLiteStatement.bindString(35, download.getImdbid());
                }
                supportSQLiteStatement.bindLong(36, download.getId());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads WHERE movie_id = ?";
            }
        };
        this.__preparedStmtOfDeleteByTid = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads WHERE privateid== ? ";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void insert(Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDownload.insert((EntityInsertionAdapter) download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void delete(Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDownload.handle(download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void update(Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfDownload.handle(download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> getAll() {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i2 = i;
                    int i3 = columnIndexOrThrow;
                    download.setQuality(query.getString(i2));
                    int i4 = columnIndexOrThrow15;
                    int i5 = columnIndexOrThrow2;
                    download.setFileLength(query.getLong(i4));
                    int i6 = columnIndexOrThrow16;
                    download.setSize(query.getString(i6));
                    int i7 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i7));
                    int i8 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i8));
                    int i9 = columnIndexOrThrow3;
                    int i10 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i10));
                    int i11 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i11));
                    int i12 = columnIndexOrThrow21;
                    download.setCount(query.getString(i12));
                    int i13 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i13));
                    int i14 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i14));
                    int i15 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i15));
                    int i16 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i16));
                    columnIndexOrThrow25 = i16;
                    int i17 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i17));
                    columnIndexOrThrow26 = i17;
                    int i18 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i18));
                    columnIndexOrThrow27 = i18;
                    int i19 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i19));
                    columnIndexOrThrow28 = i19;
                    int i20 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i20));
                    columnIndexOrThrow29 = i20;
                    int i21 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i21));
                    columnIndexOrThrow30 = i21;
                    int i22 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i22));
                    columnIndexOrThrow31 = i22;
                    int i23 = columnIndexOrThrow32;
                    download.setFid(query.getString(i23));
                    columnIndexOrThrow32 = i23;
                    int i24 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i24));
                    columnIndexOrThrow33 = i24;
                    int i25 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i25));
                    columnIndexOrThrow34 = i25;
                    int i26 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i26));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i26;
                    columnIndexOrThrow = i3;
                    i = i2;
                    columnIndexOrThrow24 = i15;
                    columnIndexOrThrow2 = i5;
                    columnIndexOrThrow15 = i4;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow17 = i7;
                    columnIndexOrThrow19 = i10;
                    columnIndexOrThrow21 = i12;
                    columnIndexOrThrow3 = i9;
                    columnIndexOrThrow18 = i8;
                    columnIndexOrThrow20 = i11;
                    columnIndexOrThrow22 = i13;
                    columnIndexOrThrow23 = i14;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findByType(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type== ? LIMIT 1", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i3 = i2;
                    int i4 = columnIndexOrThrow;
                    download.setQuality(query.getString(i3));
                    int i5 = columnIndexOrThrow15;
                    int i6 = columnIndexOrThrow2;
                    download.setFileLength(query.getLong(i5));
                    int i7 = columnIndexOrThrow16;
                    download.setSize(query.getString(i7));
                    int i8 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i9));
                    int i10 = columnIndexOrThrow3;
                    int i11 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i11));
                    int i12 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i12));
                    int i13 = columnIndexOrThrow21;
                    download.setCount(query.getString(i13));
                    int i14 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i14));
                    int i15 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i15));
                    int i16 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i16));
                    int i17 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i17));
                    columnIndexOrThrow25 = i17;
                    int i18 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i18));
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i19));
                    columnIndexOrThrow27 = i19;
                    int i20 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i20));
                    columnIndexOrThrow28 = i20;
                    int i21 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i21));
                    columnIndexOrThrow29 = i21;
                    int i22 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i22));
                    columnIndexOrThrow30 = i22;
                    int i23 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i23));
                    columnIndexOrThrow31 = i23;
                    int i24 = columnIndexOrThrow32;
                    download.setFid(query.getString(i24));
                    columnIndexOrThrow32 = i24;
                    int i25 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i25));
                    columnIndexOrThrow33 = i25;
                    int i26 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i26));
                    columnIndexOrThrow34 = i26;
                    int i27 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i27));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i27;
                    columnIndexOrThrow = i4;
                    i2 = i3;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i8;
                    columnIndexOrThrow19 = i11;
                    columnIndexOrThrow21 = i13;
                    columnIndexOrThrow3 = i10;
                    columnIndexOrThrow18 = i9;
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow22 = i14;
                    columnIndexOrThrow23 = i15;
                    columnIndexOrThrow24 = i16;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public Download findByType(int i, String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        Download download;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type== ? AND privateid = ? LIMIT 1 ", 2);
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
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                if (query.moveToFirst()) {
                    download = new Download();
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    download.setQuality(query.getString(columnIndexOrThrow14));
                    download.setFileLength(query.getLong(columnIndexOrThrow15));
                    download.setSize(query.getString(columnIndexOrThrow16));
                    download.setStatue(query.getInt(columnIndexOrThrow17));
                    download.setProgress(query.getInt(columnIndexOrThrow18));
                    download.setSpeed(query.getLong(columnIndexOrThrow19));
                    download.setFormat(query.getString(columnIndexOrThrow20));
                    download.setCount(query.getString(columnIndexOrThrow21));
                    download.setDateline(query.getLong(columnIndexOrThrow22));
                    download.setMmfid(query.getString(columnIndexOrThrow23));
                    download.setSeason(query.getInt(columnIndexOrThrow24));
                    download.setEpisode(query.getInt(columnIndexOrThrow25));
                    download.setBox_type(query.getInt(columnIndexOrThrow26));
                    download.setTmfid(query.getInt(columnIndexOrThrow27));
                    download.setSeasonid(query.getString(columnIndexOrThrow28));
                    download.setSeasontitle(query.getString(columnIndexOrThrow29));
                    download.setSeasonthumbs(query.getString(columnIndexOrThrow30));
                    download.setPrivateid(query.getString(columnIndexOrThrow31));
                    download.setFid(query.getString(columnIndexOrThrow32));
                    download.setContent_rating(query.getString(columnIndexOrThrow33));
                    download.setFailReason(query.getString(columnIndexOrThrow34));
                    download.setImdbid(query.getString(columnIndexOrThrow35));
                } else {
                    download = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return download;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public Download findByTypes(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        Download download;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE statue != ?  LIMIT 1 ", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                if (query.moveToFirst()) {
                    download = new Download();
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    download.setQuality(query.getString(columnIndexOrThrow14));
                    download.setFileLength(query.getLong(columnIndexOrThrow15));
                    download.setSize(query.getString(columnIndexOrThrow16));
                    download.setStatue(query.getInt(columnIndexOrThrow17));
                    download.setProgress(query.getInt(columnIndexOrThrow18));
                    download.setSpeed(query.getLong(columnIndexOrThrow19));
                    download.setFormat(query.getString(columnIndexOrThrow20));
                    download.setCount(query.getString(columnIndexOrThrow21));
                    download.setDateline(query.getLong(columnIndexOrThrow22));
                    download.setMmfid(query.getString(columnIndexOrThrow23));
                    download.setSeason(query.getInt(columnIndexOrThrow24));
                    download.setEpisode(query.getInt(columnIndexOrThrow25));
                    download.setBox_type(query.getInt(columnIndexOrThrow26));
                    download.setTmfid(query.getInt(columnIndexOrThrow27));
                    download.setSeasonid(query.getString(columnIndexOrThrow28));
                    download.setSeasontitle(query.getString(columnIndexOrThrow29));
                    download.setSeasonthumbs(query.getString(columnIndexOrThrow30));
                    download.setPrivateid(query.getString(columnIndexOrThrow31));
                    download.setFid(query.getString(columnIndexOrThrow32));
                    download.setContent_rating(query.getString(columnIndexOrThrow33));
                    download.setFailReason(query.getString(columnIndexOrThrow34));
                    download.setImdbid(query.getString(columnIndexOrThrow35));
                } else {
                    download = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return download;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findByTypes2(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE statue != ? ", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i3 = i2;
                    int i4 = columnIndexOrThrow;
                    download.setQuality(query.getString(i3));
                    int i5 = columnIndexOrThrow15;
                    int i6 = columnIndexOrThrow2;
                    download.setFileLength(query.getLong(i5));
                    int i7 = columnIndexOrThrow16;
                    download.setSize(query.getString(i7));
                    int i8 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i9));
                    int i10 = columnIndexOrThrow3;
                    int i11 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i11));
                    int i12 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i12));
                    int i13 = columnIndexOrThrow21;
                    download.setCount(query.getString(i13));
                    int i14 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i14));
                    int i15 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i15));
                    int i16 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i16));
                    int i17 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i17));
                    columnIndexOrThrow25 = i17;
                    int i18 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i18));
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i19));
                    columnIndexOrThrow27 = i19;
                    int i20 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i20));
                    columnIndexOrThrow28 = i20;
                    int i21 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i21));
                    columnIndexOrThrow29 = i21;
                    int i22 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i22));
                    columnIndexOrThrow30 = i22;
                    int i23 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i23));
                    columnIndexOrThrow31 = i23;
                    int i24 = columnIndexOrThrow32;
                    download.setFid(query.getString(i24));
                    columnIndexOrThrow32 = i24;
                    int i25 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i25));
                    columnIndexOrThrow33 = i25;
                    int i26 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i26));
                    columnIndexOrThrow34 = i26;
                    int i27 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i27));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i27;
                    columnIndexOrThrow = i4;
                    i2 = i3;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i8;
                    columnIndexOrThrow19 = i11;
                    columnIndexOrThrow21 = i13;
                    columnIndexOrThrow3 = i10;
                    columnIndexOrThrow18 = i9;
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow22 = i14;
                    columnIndexOrThrow23 = i15;
                    columnIndexOrThrow24 = i16;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findByStatue(int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE statue == ?", 1);
        acquire.bindLong(1, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i3 = i2;
                    int i4 = columnIndexOrThrow;
                    download.setQuality(query.getString(i3));
                    int i5 = columnIndexOrThrow15;
                    int i6 = columnIndexOrThrow2;
                    download.setFileLength(query.getLong(i5));
                    int i7 = columnIndexOrThrow16;
                    download.setSize(query.getString(i7));
                    int i8 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i8));
                    int i9 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i9));
                    int i10 = columnIndexOrThrow3;
                    int i11 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i11));
                    int i12 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i12));
                    int i13 = columnIndexOrThrow21;
                    download.setCount(query.getString(i13));
                    int i14 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i14));
                    int i15 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i15));
                    int i16 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i16));
                    int i17 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i17));
                    columnIndexOrThrow25 = i17;
                    int i18 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i18));
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i19));
                    columnIndexOrThrow27 = i19;
                    int i20 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i20));
                    columnIndexOrThrow28 = i20;
                    int i21 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i21));
                    columnIndexOrThrow29 = i21;
                    int i22 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i22));
                    columnIndexOrThrow30 = i22;
                    int i23 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i23));
                    columnIndexOrThrow31 = i23;
                    int i24 = columnIndexOrThrow32;
                    download.setFid(query.getString(i24));
                    columnIndexOrThrow32 = i24;
                    int i25 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i25));
                    columnIndexOrThrow33 = i25;
                    int i26 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i26));
                    columnIndexOrThrow34 = i26;
                    int i27 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i27));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i27;
                    columnIndexOrThrow = i4;
                    i2 = i3;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow16 = i7;
                    columnIndexOrThrow17 = i8;
                    columnIndexOrThrow19 = i11;
                    columnIndexOrThrow21 = i13;
                    columnIndexOrThrow3 = i10;
                    columnIndexOrThrow18 = i9;
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow22 = i14;
                    columnIndexOrThrow23 = i15;
                    columnIndexOrThrow24 = i16;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findByTid(String str, int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads  WHERE box_type== ? AND movie_id =  ? AND statue = ? AND season", 3);
        acquire.bindLong(1, i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    download.setQuality(query.getString(i4));
                    int i6 = columnIndexOrThrow15;
                    int i7 = columnIndexOrThrow2;
                    int i8 = columnIndexOrThrow3;
                    download.setFileLength(query.getLong(i6));
                    int i9 = columnIndexOrThrow16;
                    download.setSize(query.getString(i9));
                    int i10 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i10));
                    int i11 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i11));
                    columnIndexOrThrow17 = i10;
                    int i12 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i12));
                    int i13 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i13));
                    int i14 = columnIndexOrThrow21;
                    download.setCount(query.getString(i14));
                    int i15 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i15));
                    int i16 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i16));
                    int i17 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i17));
                    int i18 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i18));
                    columnIndexOrThrow25 = i18;
                    int i19 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i19));
                    columnIndexOrThrow26 = i19;
                    int i20 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i20));
                    columnIndexOrThrow27 = i20;
                    int i21 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i21));
                    columnIndexOrThrow28 = i21;
                    int i22 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i22));
                    columnIndexOrThrow29 = i22;
                    int i23 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i23));
                    columnIndexOrThrow30 = i23;
                    int i24 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i24));
                    columnIndexOrThrow31 = i24;
                    int i25 = columnIndexOrThrow32;
                    download.setFid(query.getString(i25));
                    columnIndexOrThrow32 = i25;
                    int i26 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i26));
                    columnIndexOrThrow33 = i26;
                    int i27 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i27));
                    columnIndexOrThrow34 = i27;
                    int i28 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i28));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i28;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow18 = i11;
                    columnIndexOrThrow20 = i13;
                    columnIndexOrThrow21 = i14;
                    columnIndexOrThrow22 = i15;
                    columnIndexOrThrow23 = i16;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i9;
                    columnIndexOrThrow19 = i12;
                    columnIndexOrThrow24 = i17;
                    columnIndexOrThrow3 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public Download findByTidAndSeasonEpisode(String str, int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        Download download;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type = 2 AND movie_id = ? AND season = ? AND episode = ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        acquire.bindLong(3, i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
            if (query.moveToFirst()) {
                download = new Download();
                download.setId(query.getInt(columnIndexOrThrow));
                download.setMovieId(query.getString(columnIndexOrThrow2));
                download.setMbId(query.getString(columnIndexOrThrow3));
                download.setTitle(query.getString(columnIndexOrThrow4));
                download.setVip_only(query.getString(columnIndexOrThrow5));
                download.setDisplay(query.getString(columnIndexOrThrow6));
                download.setPoster(query.getString(columnIndexOrThrow7));
                download.setRuntime(query.getString(columnIndexOrThrow8));
                download.setAdd_time(query.getString(columnIndexOrThrow9));
                download.setIs_collect(query.getInt(columnIndexOrThrow10));
                download.setDownload(query.getString(columnIndexOrThrow11));
                download.setFileName(query.getString(columnIndexOrThrow12));
                download.setPath(query.getString(columnIndexOrThrow13));
                download.setQuality(query.getString(columnIndexOrThrow14));
                download.setFileLength(query.getLong(columnIndexOrThrow15));
                download.setSize(query.getString(columnIndexOrThrow16));
                download.setStatue(query.getInt(columnIndexOrThrow17));
                download.setProgress(query.getInt(columnIndexOrThrow18));
                download.setSpeed(query.getLong(columnIndexOrThrow19));
                download.setFormat(query.getString(columnIndexOrThrow20));
                download.setCount(query.getString(columnIndexOrThrow21));
                download.setDateline(query.getLong(columnIndexOrThrow22));
                download.setMmfid(query.getString(columnIndexOrThrow23));
                download.setSeason(query.getInt(columnIndexOrThrow24));
                download.setEpisode(query.getInt(columnIndexOrThrow25));
                download.setBox_type(query.getInt(columnIndexOrThrow26));
                download.setTmfid(query.getInt(columnIndexOrThrow27));
                download.setSeasonid(query.getString(columnIndexOrThrow28));
                download.setSeasontitle(query.getString(columnIndexOrThrow29));
                download.setSeasonthumbs(query.getString(columnIndexOrThrow30));
                download.setPrivateid(query.getString(columnIndexOrThrow31));
                download.setFid(query.getString(columnIndexOrThrow32));
                download.setContent_rating(query.getString(columnIndexOrThrow33));
                download.setFailReason(query.getString(columnIndexOrThrow34));
                download.setImdbid(query.getString(columnIndexOrThrow35));
            } else {
                download = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return download;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findBySeason(String str, int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads  WHERE box_type== ? AND movie_id =  ? AND statue = ? group by season", 3);
        acquire.bindLong(1, i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    download.setQuality(query.getString(i4));
                    int i6 = columnIndexOrThrow15;
                    int i7 = columnIndexOrThrow2;
                    int i8 = columnIndexOrThrow3;
                    download.setFileLength(query.getLong(i6));
                    int i9 = columnIndexOrThrow16;
                    download.setSize(query.getString(i9));
                    int i10 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i10));
                    int i11 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i11));
                    columnIndexOrThrow17 = i10;
                    int i12 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i12));
                    int i13 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i13));
                    int i14 = columnIndexOrThrow21;
                    download.setCount(query.getString(i14));
                    int i15 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i15));
                    int i16 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i16));
                    int i17 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i17));
                    int i18 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i18));
                    columnIndexOrThrow25 = i18;
                    int i19 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i19));
                    columnIndexOrThrow26 = i19;
                    int i20 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i20));
                    columnIndexOrThrow27 = i20;
                    int i21 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i21));
                    columnIndexOrThrow28 = i21;
                    int i22 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i22));
                    columnIndexOrThrow29 = i22;
                    int i23 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i23));
                    columnIndexOrThrow30 = i23;
                    int i24 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i24));
                    columnIndexOrThrow31 = i24;
                    int i25 = columnIndexOrThrow32;
                    download.setFid(query.getString(i25));
                    columnIndexOrThrow32 = i25;
                    int i26 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i26));
                    columnIndexOrThrow33 = i26;
                    int i27 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i27));
                    columnIndexOrThrow34 = i27;
                    int i28 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i28));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i28;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow18 = i11;
                    columnIndexOrThrow20 = i13;
                    columnIndexOrThrow21 = i14;
                    columnIndexOrThrow22 = i15;
                    columnIndexOrThrow23 = i16;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i9;
                    columnIndexOrThrow19 = i12;
                    columnIndexOrThrow24 = i17;
                    columnIndexOrThrow3 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findId(int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type== ? AND statue = ? group by movie_id ", 2);
        acquire.bindLong(1, i);
        acquire.bindLong(2, i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    download.setQuality(query.getString(i4));
                    int i6 = columnIndexOrThrow15;
                    int i7 = columnIndexOrThrow2;
                    download.setFileLength(query.getLong(i6));
                    int i8 = columnIndexOrThrow16;
                    download.setSize(query.getString(i8));
                    int i9 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i9));
                    columnIndexOrThrow16 = i8;
                    int i10 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i10));
                    int i11 = columnIndexOrThrow3;
                    int i12 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i12));
                    int i13 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i13));
                    int i14 = columnIndexOrThrow21;
                    download.setCount(query.getString(i14));
                    int i15 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i15));
                    int i16 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i16));
                    int i17 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i17));
                    int i18 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i18));
                    columnIndexOrThrow25 = i18;
                    int i19 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i19));
                    columnIndexOrThrow26 = i19;
                    int i20 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i20));
                    columnIndexOrThrow27 = i20;
                    int i21 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i21));
                    columnIndexOrThrow28 = i21;
                    int i22 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i22));
                    columnIndexOrThrow29 = i22;
                    int i23 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i23));
                    columnIndexOrThrow30 = i23;
                    int i24 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i24));
                    columnIndexOrThrow31 = i24;
                    int i25 = columnIndexOrThrow32;
                    download.setFid(query.getString(i25));
                    columnIndexOrThrow32 = i25;
                    int i26 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i26));
                    columnIndexOrThrow33 = i26;
                    int i27 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i27));
                    columnIndexOrThrow34 = i27;
                    int i28 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i28));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i28;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow17 = i9;
                    columnIndexOrThrow19 = i12;
                    columnIndexOrThrow21 = i14;
                    columnIndexOrThrow3 = i11;
                    columnIndexOrThrow18 = i10;
                    columnIndexOrThrow20 = i13;
                    columnIndexOrThrow22 = i15;
                    columnIndexOrThrow23 = i16;
                    columnIndexOrThrow24 = i17;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public Download findByIdAndType(String str, int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        Download download;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads  WHERE box_type== ? AND movie_id =  ? AND statue = ? LIMIT 1 ", 3);
        acquire.bindLong(1, i);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
            int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
            int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
            int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
            int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
            int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
            int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
            int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
            int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
            int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
            int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
            int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
            int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
            int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
            int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
            int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
            int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
            int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
            int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
            if (query.moveToFirst()) {
                download = new Download();
                download.setId(query.getInt(columnIndexOrThrow));
                download.setMovieId(query.getString(columnIndexOrThrow2));
                download.setMbId(query.getString(columnIndexOrThrow3));
                download.setTitle(query.getString(columnIndexOrThrow4));
                download.setVip_only(query.getString(columnIndexOrThrow5));
                download.setDisplay(query.getString(columnIndexOrThrow6));
                download.setPoster(query.getString(columnIndexOrThrow7));
                download.setRuntime(query.getString(columnIndexOrThrow8));
                download.setAdd_time(query.getString(columnIndexOrThrow9));
                download.setIs_collect(query.getInt(columnIndexOrThrow10));
                download.setDownload(query.getString(columnIndexOrThrow11));
                download.setFileName(query.getString(columnIndexOrThrow12));
                download.setPath(query.getString(columnIndexOrThrow13));
                download.setQuality(query.getString(columnIndexOrThrow14));
                download.setFileLength(query.getLong(columnIndexOrThrow15));
                download.setSize(query.getString(columnIndexOrThrow16));
                download.setStatue(query.getInt(columnIndexOrThrow17));
                download.setProgress(query.getInt(columnIndexOrThrow18));
                download.setSpeed(query.getLong(columnIndexOrThrow19));
                download.setFormat(query.getString(columnIndexOrThrow20));
                download.setCount(query.getString(columnIndexOrThrow21));
                download.setDateline(query.getLong(columnIndexOrThrow22));
                download.setMmfid(query.getString(columnIndexOrThrow23));
                download.setSeason(query.getInt(columnIndexOrThrow24));
                download.setEpisode(query.getInt(columnIndexOrThrow25));
                download.setBox_type(query.getInt(columnIndexOrThrow26));
                download.setTmfid(query.getInt(columnIndexOrThrow27));
                download.setSeasonid(query.getString(columnIndexOrThrow28));
                download.setSeasontitle(query.getString(columnIndexOrThrow29));
                download.setSeasonthumbs(query.getString(columnIndexOrThrow30));
                download.setPrivateid(query.getString(columnIndexOrThrow31));
                download.setFid(query.getString(columnIndexOrThrow32));
                download.setContent_rating(query.getString(columnIndexOrThrow33));
                download.setFailReason(query.getString(columnIndexOrThrow34));
                download.setImdbid(query.getString(columnIndexOrThrow35));
            } else {
                download = null;
            }
            query.close();
            roomSQLiteQuery.release();
            return download;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> selectByTvId(String str, int i) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE movie_id==? AND box_type = ? ", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i2 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i3 = i2;
                    int i4 = columnIndexOrThrow;
                    download.setQuality(query.getString(i3));
                    int i5 = columnIndexOrThrow15;
                    int i6 = columnIndexOrThrow2;
                    int i7 = columnIndexOrThrow3;
                    download.setFileLength(query.getLong(i5));
                    int i8 = columnIndexOrThrow16;
                    download.setSize(query.getString(i8));
                    int i9 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i9));
                    int i10 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i10));
                    columnIndexOrThrow16 = i8;
                    int i11 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i11));
                    int i12 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i12));
                    int i13 = columnIndexOrThrow21;
                    download.setCount(query.getString(i13));
                    int i14 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i14));
                    int i15 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i15));
                    int i16 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i16));
                    int i17 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i17));
                    columnIndexOrThrow25 = i17;
                    int i18 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i18));
                    columnIndexOrThrow26 = i18;
                    int i19 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i19));
                    columnIndexOrThrow27 = i19;
                    int i20 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i20));
                    columnIndexOrThrow28 = i20;
                    int i21 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i21));
                    columnIndexOrThrow29 = i21;
                    int i22 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i22));
                    columnIndexOrThrow30 = i22;
                    int i23 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i23));
                    columnIndexOrThrow31 = i23;
                    int i24 = columnIndexOrThrow32;
                    download.setFid(query.getString(i24));
                    columnIndexOrThrow32 = i24;
                    int i25 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i25));
                    columnIndexOrThrow33 = i25;
                    int i26 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i26));
                    columnIndexOrThrow34 = i26;
                    int i27 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i27));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i27;
                    columnIndexOrThrow = i4;
                    i2 = i3;
                    columnIndexOrThrow18 = i10;
                    columnIndexOrThrow20 = i12;
                    columnIndexOrThrow21 = i13;
                    columnIndexOrThrow22 = i14;
                    columnIndexOrThrow23 = i15;
                    columnIndexOrThrow2 = i6;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow17 = i9;
                    columnIndexOrThrow19 = i11;
                    columnIndexOrThrow24 = i16;
                    columnIndexOrThrow3 = i7;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> selectByTvId(String str, int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE movie_id==? AND box_type = ? AND statue = ? ", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, i);
        acquire.bindLong(3, i2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, MovieDetailActivity.MOVIE_ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, DownloadInfo.DOWNLOAD);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "quality");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "season");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "episode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "fid");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "contentRating");
                int columnIndexOrThrow34 = CursorUtil.getColumnIndexOrThrow(query, "failReason");
                int columnIndexOrThrow35 = CursorUtil.getColumnIndexOrThrow(query, "imdbid");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(query.getInt(columnIndexOrThrow));
                    download.setMovieId(query.getString(columnIndexOrThrow2));
                    download.setMbId(query.getString(columnIndexOrThrow3));
                    download.setTitle(query.getString(columnIndexOrThrow4));
                    download.setVip_only(query.getString(columnIndexOrThrow5));
                    download.setDisplay(query.getString(columnIndexOrThrow6));
                    download.setPoster(query.getString(columnIndexOrThrow7));
                    download.setRuntime(query.getString(columnIndexOrThrow8));
                    download.setAdd_time(query.getString(columnIndexOrThrow9));
                    download.setIs_collect(query.getInt(columnIndexOrThrow10));
                    download.setDownload(query.getString(columnIndexOrThrow11));
                    download.setFileName(query.getString(columnIndexOrThrow12));
                    download.setPath(query.getString(columnIndexOrThrow13));
                    int i4 = i3;
                    int i5 = columnIndexOrThrow;
                    download.setQuality(query.getString(i4));
                    int i6 = columnIndexOrThrow15;
                    int i7 = columnIndexOrThrow2;
                    int i8 = columnIndexOrThrow3;
                    download.setFileLength(query.getLong(i6));
                    int i9 = columnIndexOrThrow16;
                    download.setSize(query.getString(i9));
                    int i10 = columnIndexOrThrow17;
                    download.setStatue(query.getInt(i10));
                    int i11 = columnIndexOrThrow18;
                    download.setProgress(query.getInt(i11));
                    columnIndexOrThrow17 = i10;
                    int i12 = columnIndexOrThrow19;
                    download.setSpeed(query.getLong(i12));
                    int i13 = columnIndexOrThrow20;
                    download.setFormat(query.getString(i13));
                    int i14 = columnIndexOrThrow21;
                    download.setCount(query.getString(i14));
                    int i15 = columnIndexOrThrow22;
                    download.setDateline(query.getLong(i15));
                    int i16 = columnIndexOrThrow23;
                    download.setMmfid(query.getString(i16));
                    int i17 = columnIndexOrThrow24;
                    download.setSeason(query.getInt(i17));
                    int i18 = columnIndexOrThrow25;
                    download.setEpisode(query.getInt(i18));
                    columnIndexOrThrow25 = i18;
                    int i19 = columnIndexOrThrow26;
                    download.setBox_type(query.getInt(i19));
                    columnIndexOrThrow26 = i19;
                    int i20 = columnIndexOrThrow27;
                    download.setTmfid(query.getInt(i20));
                    columnIndexOrThrow27 = i20;
                    int i21 = columnIndexOrThrow28;
                    download.setSeasonid(query.getString(i21));
                    columnIndexOrThrow28 = i21;
                    int i22 = columnIndexOrThrow29;
                    download.setSeasontitle(query.getString(i22));
                    columnIndexOrThrow29 = i22;
                    int i23 = columnIndexOrThrow30;
                    download.setSeasonthumbs(query.getString(i23));
                    columnIndexOrThrow30 = i23;
                    int i24 = columnIndexOrThrow31;
                    download.setPrivateid(query.getString(i24));
                    columnIndexOrThrow31 = i24;
                    int i25 = columnIndexOrThrow32;
                    download.setFid(query.getString(i25));
                    columnIndexOrThrow32 = i25;
                    int i26 = columnIndexOrThrow33;
                    download.setContent_rating(query.getString(i26));
                    columnIndexOrThrow33 = i26;
                    int i27 = columnIndexOrThrow34;
                    download.setFailReason(query.getString(i27));
                    columnIndexOrThrow34 = i27;
                    int i28 = columnIndexOrThrow35;
                    download.setImdbid(query.getString(i28));
                    arrayList = arrayList2;
                    arrayList.add(download);
                    columnIndexOrThrow35 = i28;
                    columnIndexOrThrow = i5;
                    i3 = i4;
                    columnIndexOrThrow18 = i11;
                    columnIndexOrThrow20 = i13;
                    columnIndexOrThrow21 = i14;
                    columnIndexOrThrow22 = i15;
                    columnIndexOrThrow23 = i16;
                    columnIndexOrThrow2 = i7;
                    columnIndexOrThrow15 = i6;
                    columnIndexOrThrow16 = i9;
                    columnIndexOrThrow19 = i12;
                    columnIndexOrThrow24 = i17;
                    columnIndexOrThrow3 = i8;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }
}
