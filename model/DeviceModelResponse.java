package com.movieboxpro.android.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes3.dex */
public class DeviceModelResponse {
    private List<DeviceModel> device_list;

    public List<DeviceModel> getDevice_list() {
        return this.device_list;
    }

    public void setDevice_list(List<DeviceModel> list) {
        this.device_list = list;
    }

    /* loaded from: classes3.dex */
    public static class DeviceModel implements Serializable {
        private int box_type;
        private String device_model;
        private String device_name;
        private int disable;
        private int episode;
        private String id;
        private long last_time;
        private String master_uid;
        private String mid;
        private String open_udid;
        private String platform;
        private int season;
        private String uid;
        private String username;

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getMaster_uid() {
            return this.master_uid;
        }

        public void setMaster_uid(String str) {
            this.master_uid = str;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getOpen_udid() {
            return this.open_udid;
        }

        public void setOpen_udid(String str) {
            this.open_udid = str;
        }

        public int getBox_type() {
            return this.box_type;
        }

        public void setBox_type(int i) {
            this.box_type = i;
        }

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

        public int getDisable() {
            return this.disable;
        }

        public void setDisable(int i) {
            this.disable = i;
        }

        public String getMid() {
            return this.mid;
        }

        public void setMid(String str) {
            this.mid = str;
        }

        public String getPlatform() {
            return this.platform;
        }

        public void setPlatform(String str) {
            this.platform = str;
        }

        public String getDevice_name() {
            return this.device_name;
        }

        public void setDevice_name(String str) {
            this.device_name = str;
        }

        public String getDevice_model() {
            return this.device_model;
        }

        public void setDevice_model(String str) {
            this.device_model = str;
        }

        public long getLast_time() {
            return this.last_time;
        }

        public void setLast_time(long j) {
            this.last_time = j;
        }
    }
}
