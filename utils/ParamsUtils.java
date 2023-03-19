package com.movieboxpro.android.utils;

import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes3.dex */
public class ParamsUtils {
    public static Builder newBuilder() {
        return new Builder();
    }

    /* loaded from: classes3.dex */
    public static class Builder {
        private Bundle params = new Bundle();

        Builder() {
        }

        public Builder addParam(String str, ArrayList<? extends Parcelable> arrayList) {
            this.params.putParcelableArrayList(str, arrayList);
            return this;
        }

        public Builder addParam(String str, String str2) {
            this.params.putString(str, str2);
            return this;
        }

        public Builder addParam(String str, int i) {
            this.params.putInt(str, i);
            return this;
        }

        public Builder addParam(String str, boolean z) {
            this.params.putBoolean(str, z);
            return this;
        }

        public Builder addParam(String str, double d) {
            this.params.putDouble(str, d);
            return this;
        }

        public Builder addParam(String str, Serializable serializable) {
            this.params.putSerializable(str, serializable);
            return this;
        }

        public Builder addParam(String str, Parcelable parcelable) {
            this.params.putParcelable(str, parcelable);
            return this;
        }

        public Bundle build() {
            return this.params;
        }
    }
}
