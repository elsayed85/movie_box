package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class TransactionModel {
    private String amount;
    private String payer_email;
    private String payer_name;
    private String status;
    private String time_stamp;
    private String transaction_id;
    private String transaction_type;

    public String getTransaction_id() {
        return this.transaction_id;
    }

    public void setTransaction_id(String str) {
        this.transaction_id = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getTransaction_type() {
        return this.transaction_type;
    }

    public void setTransaction_type(String str) {
        this.transaction_type = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getPayer_email() {
        return this.payer_email;
    }

    public void setPayer_email(String str) {
        this.payer_email = str;
    }

    public String getPayer_name() {
        return this.payer_name;
    }

    public void setPayer_name(String str) {
        this.payer_name = str;
    }

    public String getTime_stamp() {
        return this.time_stamp;
    }

    public void setTime_stamp(String str) {
        this.time_stamp = str;
    }
}
