package com.movieboxpro.android.model;

import java.util.List;
/* loaded from: classes3.dex */
public class ReviewDetailPageModel {
    private Variables Variables;

    public Variables getVariables() {
        return this.Variables;
    }

    public void setVariables(Variables variables) {
        this.Variables = variables;
    }

    /* loaded from: classes3.dex */
    public static class PostInfo {
        private String fid;
        private int recommend;
        private int recommends;
        private int replies;
        private String tid;

        public String getFid() {
            return this.fid;
        }

        public void setFid(String str) {
            this.fid = str;
        }

        public String getTid() {
            return this.tid;
        }

        public void setTid(String str) {
            this.tid = str;
        }

        public int getRecommend() {
            return this.recommend;
        }

        public void setRecommend(int i) {
            this.recommend = i;
        }

        public int getReplies() {
            return this.replies;
        }

        public void setReplies(int i) {
            this.replies = i;
        }

        public int getRecommends() {
            return this.recommends;
        }

        public void setRecommends(int i) {
            this.recommends = i;
        }
    }

    /* loaded from: classes3.dex */
    public static class Variables {
        private List<ReviewModel> postlist;
        private PostInfo thread;
        private int totalPage;

        public PostInfo getThread() {
            return this.thread;
        }

        public void setThread(PostInfo postInfo) {
            this.thread = postInfo;
        }

        public int getTotalPage() {
            return this.totalPage;
        }

        public void setTotalPage(int i) {
            this.totalPage = i;
        }

        public List<ReviewModel> getPostlist() {
            return this.postlist;
        }

        public void setPostlist(List<ReviewModel> list) {
            this.postlist = list;
        }
    }
}
