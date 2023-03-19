package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class VipPlanModel {
    private boolean isAutoPayment;
    private boolean isTransaction;
    private String title;
    private String value;

    public VipPlanModel(String str, String str2) {
        this.title = "";
        this.value = "";
        this.title = str;
        this.value = str2;
    }

    public VipPlanModel(String str, String str2, boolean z, boolean z2) {
        this.title = "";
        this.value = "";
        this.title = str;
        this.value = str2;
        this.isTransaction = z;
        this.isAutoPayment = z2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public boolean isTransaction() {
        return this.isTransaction;
    }

    public void setTransaction(boolean z) {
        this.isTransaction = z;
    }

    public boolean isAutoPayment() {
        return this.isAutoPayment;
    }

    public void setAutoPayment(boolean z) {
        this.isAutoPayment = z;
    }
}
