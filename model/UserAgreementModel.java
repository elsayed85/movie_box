package com.movieboxpro.android.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes3.dex */
public class UserAgreementModel implements Parcelable {
    public static final Parcelable.Creator<UserAgreementModel> CREATOR = new Parcelable.Creator<UserAgreementModel>() { // from class: com.movieboxpro.android.model.UserAgreementModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserAgreementModel createFromParcel(Parcel parcel) {
            return new UserAgreementModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UserAgreementModel[] newArray(int i) {
            return new UserAgreementModel[i];
        }
    };
    private String agreement_id;
    private String amount;
    private String cycles_completed;
    private String cycles_remaining;
    private String description;
    private String failed_payment_count;
    private String frequency;
    private String frequency_interval;
    private int id;
    private String last_payment_amount;
    private String last_payment_date;
    private String next_billing_date;
    private String start_date;
    private String status;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getAgreement_id() {
        return this.agreement_id;
    }

    public void setAgreement_id(String str) {
        this.agreement_id = str;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public String getFrequency_interval() {
        return this.frequency_interval;
    }

    public void setFrequency_interval(String str) {
        this.frequency_interval = str;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String str) {
        this.start_date = str;
    }

    public String getNext_billing_date() {
        return this.next_billing_date;
    }

    public void setNext_billing_date(String str) {
        this.next_billing_date = str;
    }

    public String getCycles_completed() {
        return this.cycles_completed;
    }

    public void setCycles_completed(String str) {
        this.cycles_completed = str;
    }

    public String getCycles_remaining() {
        return this.cycles_remaining;
    }

    public void setCycles_remaining(String str) {
        this.cycles_remaining = str;
    }

    public String getLast_payment_date() {
        return this.last_payment_date;
    }

    public void setLast_payment_date(String str) {
        this.last_payment_date = str;
    }

    public String getLast_payment_amount() {
        return this.last_payment_amount;
    }

    public void setLast_payment_amount(String str) {
        this.last_payment_amount = str;
    }

    public String getFailed_payment_count() {
        return this.failed_payment_count;
    }

    public void setFailed_payment_count(String str) {
        this.failed_payment_count = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.agreement_id);
        parcel.writeString(this.frequency);
        parcel.writeString(this.frequency_interval);
        parcel.writeString(this.amount);
        parcel.writeString(this.status);
        parcel.writeString(this.description);
        parcel.writeString(this.start_date);
        parcel.writeString(this.next_billing_date);
        parcel.writeString(this.cycles_completed);
        parcel.writeString(this.cycles_remaining);
        parcel.writeString(this.last_payment_date);
        parcel.writeString(this.last_payment_amount);
        parcel.writeString(this.failed_payment_count);
    }

    public UserAgreementModel() {
    }

    protected UserAgreementModel(Parcel parcel) {
        this.id = parcel.readInt();
        this.agreement_id = parcel.readString();
        this.frequency = parcel.readString();
        this.frequency_interval = parcel.readString();
        this.amount = parcel.readString();
        this.status = parcel.readString();
        this.description = parcel.readString();
        this.start_date = parcel.readString();
        this.next_billing_date = parcel.readString();
        this.cycles_completed = parcel.readString();
        this.cycles_remaining = parcel.readString();
        this.last_payment_date = parcel.readString();
        this.last_payment_amount = parcel.readString();
        this.failed_payment_count = parcel.readString();
    }
}
