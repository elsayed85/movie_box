package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class BBsResponseModel {
    private MessageBean Message;

    public MessageBean getMessage() {
        return this.Message;
    }

    public void setMessage(MessageBean messageBean) {
        this.Message = messageBean;
    }

    /* loaded from: classes3.dex */
    public static class MessageBean {
        private String messagestr;
        private String messageval;

        public String getMessageval() {
            return this.messageval;
        }

        public void setMessageval(String str) {
            this.messageval = str;
        }

        public String getMessagestr() {
            return this.messagestr;
        }

        public void setMessagestr(String str) {
            this.messagestr = str;
        }
    }
}
