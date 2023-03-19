package com.movieboxpro.android.utils;

import android.content.Context;
import android.widget.Toast;
/* loaded from: classes3.dex */
public class Toastor {
    private Context context;
    private Toast mToast;

    public Toastor(Context context) {
        this.context = context.getApplicationContext();
    }

    public Toast getSingletonToast(int i) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, i, 0);
        } else {
            toast.setText(i);
        }
        return this.mToast;
    }

    public Toast getSingletonToast(String str) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, str, 0);
        } else {
            toast.setText(str);
        }
        return this.mToast;
    }

    public Toast getSingleLongToast(int i) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, i, 1);
        } else {
            toast.setText(i);
        }
        return this.mToast;
    }

    public Toast getSingleLongToast(String str) {
        Toast toast = this.mToast;
        if (toast == null) {
            this.mToast = Toast.makeText(this.context, str, 1);
        } else {
            toast.setText(str);
        }
        return this.mToast;
    }

    public Toast getToast(int i) {
        return Toast.makeText(this.context, i, 0);
    }

    public Toast getToast(String str) {
        return Toast.makeText(this.context, str, 0);
    }

    public Toast getLongToast(int i) {
        return Toast.makeText(this.context, i, 1);
    }

    public Toast getLongToast(String str) {
        return Toast.makeText(this.context, str, 1);
    }

    public void showSingletonToast(int i) {
        getSingletonToast(i).show();
    }

    public void showSingletonToast(String str) {
        getSingletonToast(str).show();
    }

    public void showSingleLongToast(int i) {
        getSingleLongToast(i).show();
    }

    public void showSingleLongToast(String str) {
        getSingleLongToast(str).show();
    }

    public void showToast(int i) {
        getToast(i).show();
    }

    public void showToast(String str) {
        getToast(str).show();
    }

    public void showLongToast(int i) {
        getLongToast(i).show();
    }

    public void showLongToast(String str) {
        getLongToast(str).show();
    }
}
