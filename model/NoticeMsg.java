package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class NoticeMsg {
    private long add_time;
    private NoticeDetail data;
    private String desc;
    private String id;
    private int is_read;
    private String type;
    private String uid;

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public NoticeDetail getData() {
        return this.data;
    }

    public void setData(NoticeDetail noticeDetail) {
        this.data = noticeDetail;
    }

    public long getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(long j) {
        this.add_time = j;
    }

    public int getIs_read() {
        return this.is_read;
    }

    public void setIs_read(int i) {
        this.is_read = i;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    /* loaded from: classes3.dex */
    public static class NoticeDetail {
        private String actor_id;
        private String actor_name;
        private String comment;
        private String comment_id;
        private String comment_type;
        private String from_user_avatar;
        private String mid;
        private String pid;
        private String uid;
        private String username;

        public String getComment() {
            return this.comment;
        }

        public void setComment(String str) {
            this.comment = str;
        }

        public String getPid() {
            return this.pid;
        }

        public void setPid(String str) {
            this.pid = str;
        }

        public String getMid() {
            return this.mid;
        }

        public void setMid(String str) {
            this.mid = str;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(String str) {
            this.username = str;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }

        public String getActor_name() {
            return this.actor_name;
        }

        public void setActor_name(String str) {
            this.actor_name = str;
        }

        public String getActor_id() {
            return this.actor_id;
        }

        public void setActor_id(String str) {
            this.actor_id = str;
        }

        public String getComment_type() {
            return this.comment_type;
        }

        public void setComment_type(String str) {
            this.comment_type = str;
        }

        public String getComment_id() {
            return this.comment_id;
        }

        public void setComment_id(String str) {
            this.comment_id = str;
        }

        public String getFrom_user_avatar() {
            return this.from_user_avatar;
        }

        public void setFrom_user_avatar(String str) {
            this.from_user_avatar = str;
        }
    }
}
