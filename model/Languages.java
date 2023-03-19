package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class Languages {
    private boolean expanded;
    private int icon;
    private String name;
    private String province;

    public Languages(String str, String str2, boolean z) {
        this.expanded = true;
        this.name = str;
        this.province = str2;
        this.expanded = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setIcon(int i) {
        this.icon = i;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void setExpanded(boolean z) {
        this.expanded = z;
    }
}
