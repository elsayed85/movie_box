package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class HistoryModel {
    private String actors;
    private int box_type;
    private long dateline;
    private String description;
    private PlayHistoryDevice device;
    private HistoryBean history;
    private int id;
    private String poster;
    private int seconds;
    private String title;
    private int year;

    public long getDateline() {
        return this.dateline;
    }

    public void setDateline(long j) {
        this.dateline = j;
    }

    public PlayHistoryDevice getDevice() {
        return this.device;
    }

    public void setDevice(PlayHistoryDevice playHistoryDevice) {
        this.device = playHistoryDevice;
    }

    public int getSeconds() {
        return this.seconds;
    }

    public void setSeconds(int i) {
        this.seconds = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String str) {
        this.poster = str;
    }

    public int getBox_type() {
        return this.box_type;
    }

    public void setBox_type(int i) {
        this.box_type = i;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int i) {
        this.year = i;
    }

    public String getActors() {
        return this.actors;
    }

    public void setActors(String str) {
        this.actors = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public HistoryBean getHistory() {
        return this.history;
    }

    public void setHistory(HistoryBean historyBean) {
        this.history = historyBean;
    }

    /* loaded from: classes3.dex */
    public static class PlayHistoryDevice {
        private String browser;
        private long dateline;
        private String model;
        private String name;
        private String os;
        private String type;

        public String getBrowser() {
            return this.browser;
        }

        public void setBrowser(String str) {
            this.browser = str;
        }

        public String getOs() {
            return this.os;
        }

        public void setOs(String str) {
            this.os = str;
        }

        public long getDateline() {
            return this.dateline;
        }

        public void setDateline(long j) {
            this.dateline = j;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getModel() {
            return this.model;
        }

        public void setModel(String str) {
            this.model = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    /* loaded from: classes3.dex */
    public static class HistoryBean {
        private int episode;
        private int season;
        private int seconds;

        public int getSeason() {
            return this.season;
        }

        public void setSeason(int i) {
            this.season = i;
        }

        public int getEpisode() {
            return this.episode;
        }

        public void setEpisode(int i) {
            this.episode = i;
        }

        public int getSeconds() {
            return this.seconds;
        }

        public void setSeconds(int i) {
            this.seconds = i;
        }
    }
}
