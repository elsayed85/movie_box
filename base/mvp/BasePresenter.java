package com.movieboxpro.android.base.mvp;

import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.base.mvp.BaseContract.BaseView;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public abstract class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {
    protected LifecycleOwner mLifecycleOwner;
    private Reference<V> mReference;

    public BasePresenter(LifecycleOwner lifecycleOwner) {
        this.mLifecycleOwner = lifecycleOwner;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BasePresenter
    public void attachView(V v) {
        this.mReference = new WeakReference(v);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BasePresenter
    public void detachView() {
        Reference<V> reference = this.mReference;
        if (reference != null) {
            reference.clear();
            this.mReference = null;
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BasePresenter
    public boolean isViewAttached() {
        Reference<V> reference = this.mReference;
        return (reference == null || reference.get() == null) ? false : true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BasePresenter
    public V getView() {
        Reference<V> reference = this.mReference;
        if (reference == null) {
            return null;
        }
        return reference.get();
    }
}
