package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class UserProfileResponse {
    private Variables variables;

    public Variables getVariables() {
        return this.variables;
    }

    public void setVariables(Variables variables) {
        this.variables = variables;
    }

    /* loaded from: classes3.dex */
    public static class Variables {
        private String avatar;
        private String mbp_uid;
        private Variables space;
        private String username;

        public String getMbp_uid() {
            return this.mbp_uid;
        }

        public void setMbp_uid(String str) {
            this.mbp_uid = str;
        }

        public Variables getSpace() {
            return this.space;
        }

        public void setSpace(Variables variables) {
            this.space = variables;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }
    }
}
