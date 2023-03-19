package com.movieboxpro.android.model;

import com.movieboxpro.android.model.DeviceModelResponse;
import java.util.List;
/* loaded from: classes3.dex */
public class ScreenMember {
    private List<DeviceModelResponse.DeviceModel> devices;
    private String uid;
    private String username;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public List<DeviceModelResponse.DeviceModel> getDevices() {
        return this.devices;
    }

    public void setDevices(List<DeviceModelResponse.DeviceModel> list) {
        this.devices = list;
    }
}
