package com.movieboxpro.android.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
/* loaded from: classes.dex */
public class NetTestModel {
    @JSONField(serialize = false)
    public static final int COMPLETE = 3;
    @JSONField(serialize = false)
    public static final int FAILED = 2;
    @JSONField(serialize = false)
    public static final int NOT_START = 0;
    public static final int READY = 4;
    @JSONField(serialize = false)
    public static final int TESTING = 1;
    @JSONField(serialize = false)
    public static final int TEST_SPEED = 1;
    @JSONField(serialize = false)
    public static final int TITLE = 2;
    @JSONField(serialize = false)
    private Entry addEntry;
    private double average;
    private long contentLength;
    public String country;
    @JSONField(serialize = false)
    private long currSize;
    public String description;
    public int display_order;
    public String domain;
    @JSONField(serialize = false)
    private String domainIp;
    @JSONField(serialize = false)
    public Long endTime;
    public String group_id;
    public int id;
    @JSONField(serialize = false)
    private boolean init;
    @JSONField(serialize = false)
    private int itemType;
    @JSONField(serialize = false)
    private LineData lineData;
    @JSONField(name = "100m_url")
    private String newUrl;
    @JSONField(serialize = false)
    private int our;
    public float ratio;
    @JSONField(serialize = false)
    private long size;
    @JSONField(serialize = false)
    private List<Integer> speeds;
    @JSONField(serialize = false)
    private int status;
    public String url;
    @JSONField(serialize = false)
    private List<Entry> values;
    @JSONField(serialize = false)
    public Long startTime = -1L;
    @JSONField(serialize = false)
    public String state = TtmlNode.START;
    @JSONField(serialize = false)
    public boolean isSelect = false;

    public long getContentLength() {
        return this.contentLength;
    }

    public void setContentLength(long j) {
        this.contentLength = j;
    }

    public String getDomainIp() {
        return this.domainIp;
    }

    public void setDomainIp(String str) {
        this.domainIp = str;
    }

    public double getAverage() {
        return this.average;
    }

    public void setAverage(double d) {
        this.average = d;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public int getOur() {
        return this.our;
    }

    public void setOur(int i) {
        this.our = i;
    }

    public List<Integer> getSpeeds() {
        return this.speeds;
    }

    public void setSpeeds(List<Integer> list) {
        this.speeds = list;
    }

    public String getNewUrl() {
        return this.newUrl;
    }

    public void setNewUrl(String str) {
        this.newUrl = str;
    }

    public Entry getAddEntry() {
        return this.addEntry;
    }

    public void setAddEntry(Entry entry) {
        this.addEntry = entry;
    }

    public LineData getLineData() {
        return this.lineData;
    }

    public void setLineData(LineData lineData) {
        this.lineData = lineData;
    }

    public boolean isInit() {
        return this.init;
    }

    public void setInit(boolean z) {
        this.init = z;
    }

    public long getCurrSize() {
        return this.currSize;
    }

    public void setCurrSize(long j) {
        this.currSize = j;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public List<Entry> getValues() {
        return this.values;
    }

    public void setValues(List<Entry> list) {
        this.values = list;
    }
}
