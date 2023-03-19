package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class UploadImgResponse {
    private UploadImage img;

    public UploadImage getImg() {
        return this.img;
    }

    public void setImg(UploadImage uploadImage) {
        this.img = uploadImage;
    }

    /* loaded from: classes3.dex */
    public static class UploadImage {
        private String fid;
        private int height;
        private String img_id;
        private String uid;
        private String url;
        private int width;

        public int getWidth() {
            return this.width;
        }

        public void setWidth(int i) {
            this.width = i;
        }

        public int getHeight() {
            return this.height;
        }

        public void setHeight(int i) {
            this.height = i;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getImg_id() {
            return this.img_id;
        }

        public void setImg_id(String str) {
            this.img_id = str;
        }

        public String getFid() {
            return this.fid;
        }

        public void setFid(String str) {
            this.fid = str;
        }

        public String getUid() {
            return this.uid;
        }

        public void setUid(String str) {
            this.uid = str;
        }
    }
}
