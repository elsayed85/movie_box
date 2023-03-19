package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class BBsInfoModel {
    private BBsInfo loginInfo;
    private Member member;

    public BBsInfo getLoginInfo() {
        return this.loginInfo;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setLoginInfo(BBsInfo bBsInfo) {
        this.loginInfo = bBsInfo;
    }

    /* loaded from: classes3.dex */
    public static class BBsInfo {
        private Variables Variables;

        public Variables getVariables() {
            return this.Variables;
        }

        public void setVariables(Variables variables) {
            this.Variables = variables;
        }
    }

    /* loaded from: classes3.dex */
    public static class Member {
        private String avatar;
        private String username;

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

    /* loaded from: classes3.dex */
    public static class Variables {
        private String auth;
        private String authkey;
        private String author;
        private String cookiepre;
        private String formhash;
        private String groupid;
        private Object ismoderator;
        private String member_uid;
        private String member_username;
        private Object qqweibo_publish;
        private String readaccess;
        private String saltkey;
        private String security_authkey;

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public String getCookiepre() {
            return this.cookiepre;
        }

        public void setCookiepre(String str) {
            this.cookiepre = str;
        }

        public String getAuth() {
            return this.auth;
        }

        public void setAuth(String str) {
            this.auth = str;
        }

        public String getAuthkey() {
            return this.authkey;
        }

        public void setAuthkey(String str) {
            this.authkey = str;
        }

        public String getSaltkey() {
            return this.saltkey;
        }

        public void setSaltkey(String str) {
            this.saltkey = str;
        }

        public String getMember_uid() {
            return this.member_uid;
        }

        public void setMember_uid(String str) {
            this.member_uid = str;
        }

        public String getMember_username() {
            return this.member_username;
        }

        public void setMember_username(String str) {
            this.member_username = str;
        }

        public String getGroupid() {
            return this.groupid;
        }

        public void setGroupid(String str) {
            this.groupid = str;
        }

        public String getFormhash() {
            return this.formhash;
        }

        public void setFormhash(String str) {
            this.formhash = str;
        }

        public Object getIsmoderator() {
            return this.ismoderator;
        }

        public void setIsmoderator(Object obj) {
            this.ismoderator = obj;
        }

        public String getReadaccess() {
            return this.readaccess;
        }

        public void setReadaccess(String str) {
            this.readaccess = str;
        }

        public String getSecurity_authkey() {
            return this.security_authkey;
        }

        public void setSecurity_authkey(String str) {
            this.security_authkey = str;
        }

        public Object getQqweibo_publish() {
            return this.qqweibo_publish;
        }

        public void setQqweibo_publish(Object obj) {
            this.qqweibo_publish = obj;
        }
    }
}
