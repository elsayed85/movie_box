package com.movieboxpro.android.model;

import java.io.Serializable;
import java.util.List;
/* loaded from: classes3.dex */
public class ReviewModel implements Serializable {
    public static final int COMMENT = 0;
    public static final int NORMAL_REVIEW = 1;
    public static final int NORMAL_REVIEW_HTML = 2;
    public static final int POST = 1;
    public static final int REPLY = 1;
    public static final int REPLY_NESTED = 3;
    public static final int REVIEW_TOP = 4;
    public static final int SINGLE_ALT_MOVIELIST = 500;
    public static final int SINGLE_ALT_VIDEO = 400;
    public static final int SINGLE_IMAGE = 300;
    public static final int SPECIAL_REVIEW = 3;
    public static final int THREE_IMAGE = 100;
    public static final int TOP = 0;
    public static final int TOP_MAIN = 2;
    public static final int TWO_IMAGE = 200;
    private long add_time;
    private List<AltTypeItem> altImages;
    private String author;
    private String authorid;
    private String avatar;
    private String content;
    private String dateline;
    private long dbdateline;
    private String fid;
    private int first;
    private String for_quote;
    private String id;
    private List<ImageModel> images;
    private boolean isLike;
    private int itemType;
    private String lid;
    private int likeNumber;
    private boolean linkDeal;
    private String message;
    private String message_type;
    private String note;
    private List<String> originImages;
    private int page;
    private String pid;
    private int position;
    private String poster;
    private Postreview postreview;
    private String quote;
    private int recommends;
    private int replies;
    private String reviewAvatar;
    private String reviewContent;
    private String reviewNickname;
    private long reviewTime;
    private int status;
    private String subject;
    private int support;
    private String tid;
    private String title;
    private String username;

    public String getQuote() {
        return this.quote;
    }

    public void setQuote(String str) {
        this.quote = str;
    }

    /* loaded from: classes3.dex */
    public static class ImageModel implements Serializable {
        private int height;
        private String url;
        private int width;

        public ImageModel() {
        }

        public ImageModel(String str, int i, int i2) {
            this.url = str;
            this.height = i;
            this.width = i2;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int i) {
            this.height = i;
        }

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int i) {
            this.width = i;
        }
    }

    /* loaded from: classes3.dex */
    public static class AltTypeItem implements Serializable {
        private String id;
        private String type;
        private String url;

        public AltTypeItem() {
        }

        public AltTypeItem(String str, String str2, String str3) {
            this.type = str;
            this.url = str2;
            this.id = str3;
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

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    /* loaded from: classes3.dex */
    public static class Postreview implements Serializable {
        private int support;
        private int support_status;

        public int getSupport() {
            return this.support;
        }

        public void setSupport(int i) {
            this.support = i;
        }

        public int getSupport_status() {
            return this.support_status;
        }

        public void setSupport_status(int i) {
            this.support_status = i;
        }
    }

    public String getLid() {
        return this.lid;
    }

    public void setLid(String str) {
        this.lid = str;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String str) {
        this.note = str;
    }

    public String getDateline() {
        return this.dateline;
    }

    public void setDateline(String str) {
        this.dateline = str;
    }

    public int getFirst() {
        return this.first;
    }

    public void setFirst(int i) {
        this.first = i;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String str) {
        this.poster = str;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int i) {
        this.page = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public List<String> getOriginImages() {
        return this.originImages;
    }

    public void setOriginImages(List<String> list) {
        this.originImages = list;
    }

    public int getSupport() {
        return this.support;
    }

    public void setSupport(int i) {
        this.support = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public List<AltTypeItem> getAltImages() {
        return this.altImages;
    }

    public void setAltImages(List<AltTypeItem> list) {
        this.altImages = list;
    }

    public List<ImageModel> getImages() {
        return this.images;
    }

    public void setImages(List<ImageModel> list) {
        this.images = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public Postreview getPostreview() {
        return this.postreview;
    }

    public void setPostreview(Postreview postreview) {
        this.postreview = postreview;
    }

    public int getRecommends() {
        return this.recommends;
    }

    public void setRecommends(int i) {
        this.recommends = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getReplies() {
        return this.replies;
    }

    public void setReplies(int i) {
        this.replies = i;
    }

    public String getFor_quote() {
        return this.for_quote;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void setFor_quote(String str) {
        this.for_quote = str;
    }

    public String getAuthorid() {
        return this.authorid;
    }

    public void setAuthorid(String str) {
        this.authorid = str;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public long getDbdateline() {
        return this.dbdateline;
    }

    public void setDbdateline(long j) {
        this.dbdateline = j;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String str) {
        this.tid = str;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String str) {
        this.subject = str;
    }

    public boolean isLinkDeal() {
        return this.linkDeal;
    }

    public void setLinkDeal(boolean z) {
        this.linkDeal = z;
    }

    public String getMessage_type() {
        return this.message_type;
    }

    public void setMessage_type(String str) {
        this.message_type = str;
    }

    public boolean isLike() {
        return this.isLike;
    }

    public void setLike(boolean z) {
        this.isLike = z;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
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

    public long getAdd_time() {
        return this.add_time;
    }

    public void setAdd_time(long j) {
        this.add_time = j;
    }

    public int getLikeNumber() {
        return this.likeNumber;
    }

    public void setLikeNumber(int i) {
        this.likeNumber = i;
    }

    public String getReviewAvatar() {
        return this.reviewAvatar;
    }

    public void setReviewAvatar(String str) {
        this.reviewAvatar = str;
    }

    public String getReviewNickname() {
        return this.reviewNickname;
    }

    public void setReviewNickname(String str) {
        this.reviewNickname = str;
    }

    public long getReviewTime() {
        return this.reviewTime;
    }

    public void setReviewTime(long j) {
        this.reviewTime = j;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getReviewContent() {
        return this.reviewContent;
    }

    public void setReviewContent(String str) {
        this.reviewContent = str;
    }
}
