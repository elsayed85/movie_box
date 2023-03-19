package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.lxj.xpopup.core.AttachPopupView;
import com.movieboxpro.android.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: AddMovieListPopView.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/widget/AddMovieListPopView;", "Lcom/lxj/xpopup/core/AttachPopupView;", "context", "Landroid/content/Context;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/widget/AddMovieListPopView$AddMovieListListener;", "(Landroid/content/Context;Lcom/movieboxpro/android/view/widget/AddMovieListPopView$AddMovieListListener;)V", "getImplLayoutId", "", "onCreate", "", "AddMovieListListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AddMovieListPopView extends AttachPopupView {
    public Map<Integer, View> _$_findViewCache;
    private final AddMovieListListener listener;

    /* compiled from: AddMovieListPopView.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/view/widget/AddMovieListPopView$AddMovieListListener;", "", "onDelete", "", "onFavorite", "onHistory", "onSearch", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface AddMovieListListener {
        void onDelete();

        void onFavorite();

        void onHistory();

        void onSearch();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public int getImplLayoutId() {
        return R.layout.add_movie_list_pop_layout;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AddMovieListPopView(Context context, AddMovieListListener listener) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this._$_findViewCache = new LinkedHashMap();
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lxj.xpopup.core.BasePopupView
    public void onCreate() {
        super.onCreate();
        ((TextView) findViewById(R.id.tvSearch)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddMovieListPopView$kJXWoHmcLlO-4BMw7TneVdqXtcI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieListPopView.m1419onCreate$lambda0(AddMovieListPopView.this, view);
            }
        });
        ((TextView) findViewById(R.id.tvFavorite)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddMovieListPopView$th7GzshFfLf2LvpqY_VwS7eUGjA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieListPopView.m1420onCreate$lambda1(AddMovieListPopView.this, view);
            }
        });
        ((TextView) findViewById(R.id.tvHistory)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddMovieListPopView$xxnT5IyZNCor4zv0nbMRLq6_O6o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieListPopView.m1421onCreate$lambda2(AddMovieListPopView.this, view);
            }
        });
        ((TextView) findViewById(R.id.tvDelete)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.-$$Lambda$AddMovieListPopView$GJgipg6pYcSzc9t5L2DCzrPh6K8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AddMovieListPopView.m1422onCreate$lambda3(AddMovieListPopView.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m1419onCreate$lambda0(AddMovieListPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onSearch();
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m1420onCreate$lambda1(AddMovieListPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onFavorite();
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m1421onCreate$lambda2(AddMovieListPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onHistory();
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3  reason: not valid java name */
    public static final void m1422onCreate$lambda3(AddMovieListPopView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.listener.onDelete();
        this$0.dismiss();
    }
}
