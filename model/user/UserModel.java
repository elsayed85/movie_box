package com.movieboxpro.android.model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class UserModel implements Serializable {
    public BBsInfo bBsInfo;
    public UserData member;

    /* loaded from: classes3.dex */
    public static class Bind implements Serializable {
        public String email;
        public String id;
        public String lock;
        public String name;
        public String nick;
        public String openid;
        public String token;
        public String type;
        public String uid;
        public String validtime;
    }

    /* loaded from: classes.dex */
    public static class UserData implements Serializable {
        public long add_time;
        public String appid;
        public String avatar;
        private BBsInfo bbs_bind;
        public long dead_time;
        public String email;
        private int email_notice;
        private int family;
        private int family_member_count;
        public String firebase_token;
        public String id;
        public int isvip;
        public long last_time;
        private String master_uid;
        private String master_username;
        public int movie_update_remind_status;
        public String name;
        public String nickname;
        public String password;
        public String phone;
        private int push_notice;
        private String uid;
        public String uid_v2;
        public String username;
        public int invite_count = 0;
        public int invite_code_count = 0;
        public List<Bind> bind = new ArrayList();
        private int hide_tv_movielist = 0;

        public int getPush_notice() {
            return this.push_notice;
        }

        public void setPush_notice(int i) {
            this.push_notice = i;
        }

        public int getEmail_notice() {
            return this.email_notice;
        }

        public void setEmail_notice(int i) {
            this.email_notice = i;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getMaster_username() {
            return this.master_username;
        }

        public void setMaster_username(String str) {
            this.master_username = str;
        }

        public int getFamily_member_count() {
            return this.family_member_count;
        }

        public void setFamily_member_count(int i) {
            this.family_member_count = i;
        }

        public String getMaster_uid() {
            return this.master_uid;
        }

        public void setMaster_uid(String str) {
            this.master_uid = str;
        }

        public int getFamily() {
            return this.family;
        }

        public void setFamily(int i) {
            this.family = i;
        }

        public int getHide_tv_movielist() {
            return this.hide_tv_movielist;
        }

        public void setHide_tv_movielist(int i) {
            this.hide_tv_movielist = i;
        }

        public BBsInfo getBbs_bind() {
            return this.bbs_bind;
        }

        public void setBbs_bind(BBsInfo bBsInfo) {
            this.bbs_bind = bBsInfo;
        }

        public int getMovie_update_remind_status() {
            return this.movie_update_remind_status;
        }

        public void setMovie_update_remind_status(int i) {
            this.movie_update_remind_status = i;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getUid_v2() {
            return this.uid_v2;
        }

        public void setUid_v2(String str) {
            this.uid_v2 = str;
        }

        public String getAppid() {
            return this.appid;
        }

        public void setAppid(String str) {
            this.appid = str;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(String str) {
            this.password = str;
        }

        public String getNickname() {
            return this.nickname;
        }

        public void setNickname(String str) {
            this.nickname = str;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String str) {
            this.email = str;
        }

        public String getPhone() {
            return this.phone;
        }

        public void setPhone(String str) {
            this.phone = str;
        }

        public long getAdd_time() {
            return this.add_time;
        }

        public void setAdd_time(long j) {
            this.add_time = j;
        }

        public long getLast_time() {
            return this.last_time;
        }

        public void setLast_time(long j) {
            this.last_time = j;
        }

        public long getDead_time() {
            return this.dead_time;
        }

        public void setDead_time(long j) {
            this.dead_time = j;
        }

        public int getIsvip() {
            return this.isvip;
        }

        public void setIsvip(int i) {
            this.isvip = i;
        }

        public int getInvite_count() {
            return this.invite_count;
        }

        public void setInvite_count(int i) {
            this.invite_count = i;
        }

        public int getInvite_code_count() {
            return this.invite_code_count;
        }

        public void setInvite_code_count(int i) {
            this.invite_code_count = i;
        }

        public List<Bind> getBind() {
            return this.bind;
        }

        public void setBind(List<Bind> list) {
            this.bind = list;
        }

        public String getFirebase_token() {
            return this.firebase_token;
        }

        public void setFirebase_token(String str) {
            this.firebase_token = str;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }
    }

    /* loaded from: classes.dex */
    public static class BBsInfo implements Serializable {
        private long add_time;
        private String auth;
        private String authkey;
        private String author;
        private String avatar;
        private String bbs_uid;
        private String cookiepre;
        private String formhash;
        private String groupid;
        private String id;
        private String saltkey;
        private String security_authkey;
        private String uid;

        public long getAdd_time() {
            return this.add_time;
        }

        public void setAdd_time(long j) {
            this.add_time = j;
        }

        public String getAvatar() {
            return this.avatar;
        }

        public void setAvatar(String str) {
            this.avatar = str;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String str) {
            this.id = str;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getBbs_uid() {
            return this.bbs_uid;
        }

        public void setBbs_uid(String str) {
            this.bbs_uid = str;
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

        public String getSecurity_authkey() {
            return this.security_authkey;
        }

        public void setSecurity_authkey(String str) {
            this.security_authkey = str;
        }
    }
}
