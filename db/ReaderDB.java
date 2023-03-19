package com.movieboxpro.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.room.RoomDatabase;
import com.movieboxpro.android.db.dao.ChartDao;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.dao.PlayRecodeDao;
import com.movieboxpro.android.db.dao.SubtitleDelayRecordDao;
import com.movieboxpro.android.db.dao.TestNetRecodeDao;
/* loaded from: classes.dex */
public abstract class ReaderDB extends RoomDatabase {
    public abstract ChartDao chartDao();

    public abstract DownloadDao downloadDao();

    public abstract PlayRecodeDao playRecodeDao();

    public abstract SubtitleDelayRecordDao subtitleDelayRecordDao();

    public abstract TestNetRecodeDao testnetRecodeDao();

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        chartDao().clear();
        downloadDao().clear();
        playRecodeDao().clear();
        testnetRecodeDao().clear();
        subtitleDelayRecordDao().clear();
    }

    public void clearPlayRecord() {
        playRecodeDao().clear();
        testnetRecodeDao().clear();
    }

    private void addColumn(SQLiteDatabase sQLiteDatabase, String[] strArr, String str) {
        if (sQLiteDatabase == null || strArr == null || strArr.length < 1 || TextUtils.isEmpty(str)) {
            return;
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery("select * from " + str, null);
        if (rawQuery == null) {
            return;
        }
        String[] columnNames = rawQuery.getColumnNames();
        String str2 = "_temp_" + str;
        sQLiteDatabase.execSQL("alter table " + str + " rename to " + str2);
        if (columnNames.length < 1) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists " + str + "(");
        for (int i = 0; i < columnNames.length; i++) {
            if (i == 0) {
                stringBuffer.append(columnNames[i] + " integer primary key autoincrement,");
            } else {
                stringBuffer.append(columnNames[i] + ",");
            }
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (i2 == strArr.length - 1) {
                stringBuffer.append(strArr[i2] + ")");
            } else {
                stringBuffer.append(strArr[i2] + ",");
            }
        }
        sQLiteDatabase.execSQL(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("insert into " + str + " select *,");
        for (int i3 = 0; i3 < strArr.length; i3++) {
            if (i3 == strArr.length - 1) {
                stringBuffer2.append("' ' from " + str2);
            } else {
                stringBuffer2.append("' ',");
            }
        }
        sQLiteDatabase.execSQL(stringBuffer2.toString());
        sQLiteDatabase.execSQL("drop table " + str2);
        rawQuery.close();
    }
}
