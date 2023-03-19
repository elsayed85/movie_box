package com.movieboxpro.android.app;

import java.util.Observable;
/* loaded from: classes.dex */
public class AppObservable extends Observable {
    @Override // java.util.Observable
    protected synchronized void setChanged() {
        super.setChanged();
    }

    @Override // java.util.Observable
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    @Override // java.util.Observable
    public void notifyObservers(Object obj) {
        setChanged();
        super.notifyObservers(obj);
    }
}
