package com.movieboxpro.android.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
/* loaded from: classes.dex */
public class Migrations {
    public static final Migration migration1_2 = new Migration(1, 2) { // from class: com.movieboxpro.android.db.Migrations.1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'season' INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'episode' INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'box_type' INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'tmfid' INTEGER NOT NULL DEFAULT 0");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'seasonid' TEXT");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'seasontitle' TEXT");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'seasonthumbs' TEXT");
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'privateid' TEXT ");
        }
    };
    public static final Migration migration4_5 = new Migration(4, 5) { // from class: com.movieboxpro.android.db.Migrations.2
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE 'subtitle_delay_record' ('delaySecond' INTEGER NOT NULL DEFAULT 0, 'sid' TEXT NOT NULL DEFAULT '0', PRIMARY KEY('sid'))");
        }
    };
    public static final Migration migration5_6 = new Migration(5, 6) { // from class: com.movieboxpro.android.db.Migrations.3
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'fid' TEXT ");
        }
    };
    public static final Migration migration6_7 = new Migration(6, 7) { // from class: com.movieboxpro.android.db.Migrations.4
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'contentRating' TEXT ");
        }
    };
    public static final Migration migration7_8 = new Migration(7, 8) { // from class: com.movieboxpro.android.db.Migrations.5
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    };
    public static final Migration migration8_9 = new Migration(8, 9) { // from class: com.movieboxpro.android.db.Migrations.6
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'failReason' TEXT ");
        }
    };
    public static final Migration migration9_10 = new Migration(9, 10) { // from class: com.movieboxpro.android.db.Migrations.7
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("alter table 'downloads' add column 'imdbid' TEXT ");
        }
    };
}
