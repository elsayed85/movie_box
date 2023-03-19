package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class TimeLineModel {
    private String day;
    private int dayNumber;
    private boolean select;
    private boolean today;
    private String weekCode;

    public TimeLineModel(int i, String str, String str2) {
        this.dayNumber = i;
        this.weekCode = str;
        this.day = str2;
    }

    public TimeLineModel(int i, String str, String str2, boolean z) {
        this.dayNumber = i;
        this.weekCode = str;
        this.today = z;
        this.day = str2;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(String str) {
        this.day = str;
    }

    public boolean isToday() {
        return this.today;
    }

    public void setToday(boolean z) {
        this.today = z;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }

    public int getDayNumber() {
        return this.dayNumber;
    }

    public void setDayNumber(int i) {
        this.dayNumber = i;
    }

    public String getWeekCode() {
        return this.weekCode;
    }

    public void setWeekCode(String str) {
        this.weekCode = str;
    }
}
