package com.movieboxpro.android.model;
/* loaded from: classes3.dex */
public class DrawerItem {
    public static final int ADD_TICKET = 4;
    public static final int BLOCK_LIST = 16;
    public static final int CHILD_MODE = 15;
    public static final int COMMENT = 13;
    public static final int DEVICE = 10;
    public static final int EMAIL = 1;
    public static final int EMAIL_US = 9;
    public static final int FAMILY_PLAN = 12;
    public static final int FAQ = 7;
    public static final int HELP = 18;
    public static final int INVITAION_CODE = 2;
    public static final int INVITATION_CODE = 17;
    public static final int MY_ORDER = 6;
    public static final int MY_TICKETS = 5;
    public static final int PRIVATE_MODE = 14;
    public static final int SERVER_SPEED_TEST = 3;
    public static final int SETTING = 8;
    public static final int TV_LOGIN = 11;
    private String content;
    private boolean contentVisible;
    private boolean haveNew;
    private boolean isLast;
    private String title;
    private int type;

    public DrawerItem(String str, int i, boolean z) {
        this.title = str;
        this.type = i;
        this.isLast = z;
    }

    public DrawerItem(String str, int i, String str2) {
        this.title = str;
        this.type = i;
        this.content = str2;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public boolean isContentVisible() {
        return this.contentVisible;
    }

    public void setContentVisible(boolean z) {
        this.contentVisible = z;
    }

    public boolean isHaveNew() {
        return this.haveNew;
    }

    public void setHaveNew(boolean z) {
        this.haveNew = z;
    }

    public boolean isLast() {
        return this.isLast;
    }

    public void setLast(boolean z) {
        this.isLast = z;
    }

    public DrawerItem(String str, int i) {
        this.title = str;
        this.type = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
