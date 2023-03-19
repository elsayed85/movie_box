package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Size;
import android.util.SizeF;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.ares.downloader.jarvis.core.RemoteFileUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.databinding.ActivityListLayoutBinding;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadInfo;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteResponse;
import com.movieboxpro.android.model.PlayerStrategy;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.X86HintUtils;
import com.movieboxpro.android.view.activity.MoreWatchingActivity;
import com.movieboxpro.android.view.activity.Video.VideoPlayerActivity;
import com.movieboxpro.android.view.activity.choose.impl.ChooseActivity;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.videoplayer.MoviePlayerActivity;
import com.movieboxpro.android.view.activity.videoplayer.TvPlayerActivity;
import com.movieboxpro.android.view.dialog.ScreenManageDialog;
import com.movieboxpro.android.view.videocontroller.PopPlayerManager;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
/* compiled from: MoreWatchingActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreWatchingActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityListLayoutBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityListLayoutBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "initData", "", "initListener", "initView", "Companion", "WatchingListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MoreWatchingActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(MoreWatchingActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityListLayoutBinding;", 0))};
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityListLayoutBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
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

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
    }

    private final ActivityListLayoutBinding getBinding() {
        return (ActivityListLayoutBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$JcoTIamA3-tmEj1ciKvdTZEjpqo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreWatchingActivity.m160initListener$lambda0(MoreWatchingActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m160initListener$lambda0(MoreWatchingActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Watching");
        FragmentUtils.add(getSupportFragmentManager(), WatchingListFragment.Companion.newInstance(getIntent().getStringExtra("boxType"), getIntent().getStringExtra("quality"), getIntent().getStringExtra("sort"), getIntent().getStringExtra("country"), getIntent().getStringExtra("year"), getIntent().getStringExtra("rating"), getIntent().getStringExtra("gener")), (int) R.id.flContainer);
    }

    /* compiled from: MoreWatchingActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\b¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreWatchingActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "boxType", "", "quality", "sort", "country", "year", "rating", "gener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            if (context == null) {
                return;
            }
            int i = 0;
            Pair[] pairArr = {TuplesKt.to("boxType", str), TuplesKt.to("quality", str2), TuplesKt.to("sort", str3), TuplesKt.to("country", str4), TuplesKt.to("year", str5), TuplesKt.to("rating", str6), TuplesKt.to("gener", str7)};
            Intent intent = new Intent(context, MoreWatchingActivity.class);
            Bundle bundle = new Bundle(7);
            while (i < 7) {
                Pair pair = pairArr[i];
                i++;
                String str8 = (String) pair.component1();
                Object component2 = pair.component2();
                if (component2 == null) {
                    bundle.putString(str8, null);
                } else if (component2 instanceof Boolean) {
                    bundle.putBoolean(str8, ((Boolean) component2).booleanValue());
                } else if (component2 instanceof Byte) {
                    bundle.putByte(str8, ((Number) component2).byteValue());
                } else if (component2 instanceof Character) {
                    bundle.putChar(str8, ((Character) component2).charValue());
                } else if (component2 instanceof Double) {
                    bundle.putDouble(str8, ((Number) component2).doubleValue());
                } else if (component2 instanceof Float) {
                    bundle.putFloat(str8, ((Number) component2).floatValue());
                } else if (component2 instanceof Integer) {
                    bundle.putInt(str8, ((Number) component2).intValue());
                } else if (component2 instanceof Long) {
                    bundle.putLong(str8, ((Number) component2).longValue());
                } else if (component2 instanceof Short) {
                    bundle.putShort(str8, ((Number) component2).shortValue());
                } else if (component2 instanceof Bundle) {
                    bundle.putBundle(str8, (Bundle) component2);
                } else if (component2 instanceof CharSequence) {
                    bundle.putCharSequence(str8, (CharSequence) component2);
                } else if (component2 instanceof Parcelable) {
                    bundle.putParcelable(str8, (Parcelable) component2);
                } else if (component2 instanceof boolean[]) {
                    bundle.putBooleanArray(str8, (boolean[]) component2);
                } else if (component2 instanceof byte[]) {
                    bundle.putByteArray(str8, (byte[]) component2);
                } else if (component2 instanceof char[]) {
                    bundle.putCharArray(str8, (char[]) component2);
                } else if (component2 instanceof double[]) {
                    bundle.putDoubleArray(str8, (double[]) component2);
                } else if (component2 instanceof float[]) {
                    bundle.putFloatArray(str8, (float[]) component2);
                } else if (component2 instanceof int[]) {
                    bundle.putIntArray(str8, (int[]) component2);
                } else if (component2 instanceof long[]) {
                    bundle.putLongArray(str8, (long[]) component2);
                } else if (component2 instanceof short[]) {
                    bundle.putShortArray(str8, (short[]) component2);
                } else if (component2 instanceof Object[]) {
                    Class<?> componentType = component2.getClass().getComponentType();
                    Intrinsics.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                        }
                        bundle.putParcelableArray(str8, (Parcelable[]) component2);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                        }
                        bundle.putStringArray(str8, (String[]) component2);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        if (component2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                        }
                        bundle.putCharSequenceArray(str8, (CharSequence[]) component2);
                    } else if (Serializable.class.isAssignableFrom(componentType)) {
                        bundle.putSerializable(str8, (Serializable) component2);
                    } else {
                        String canonicalName = componentType.getCanonicalName();
                        throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + str8 + Typography.quote);
                    }
                } else if (component2 instanceof Serializable) {
                    bundle.putSerializable(str8, (Serializable) component2);
                } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                    bundle.putBinder(str8, (IBinder) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                    bundle.putSize(str8, (Size) component2);
                } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                    bundle.putSizeF(str8, (SizeF) component2);
                } else {
                    String canonicalName2 = component2.getClass().getCanonicalName();
                    throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + str8 + Typography.quote);
                }
            }
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }

    /* compiled from: MoreWatchingActivity.kt */
    @Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 Z2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001ZB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\u0016\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00020+2\u0006\u0010,\u001a\u00020\u0003H\u0014J(\u0010-\u001a\u00020'2\u0006\u0010.\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/H\u0002J\u000e\u00102\u001a\b\u0012\u0004\u0012\u00020\u000603H\u0014J\u0010\u00104\u001a\u00020'2\u0006\u00105\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020'2\u0006\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u00020/H\u0014J\u0018\u0010;\u001a\u00020'2\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0002H\u0014J\b\u0010?\u001a\u00020/H\u0014J\u0010\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020BH\u0014J\b\u0010C\u001a\u00020DH\u0014J2\u0010E\u001a\u00020'2\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0006\u0010F\u001a\u00020\u00062\u0006\u0010G\u001a\u00020\u00062\u0006\u0010H\u001a\u00020/2\u0006\u0010I\u001a\u00020/H\u0002J*\u0010J\u001a\u00020'2\u0006\u0010I\u001a\u00020/2\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020/2\u0006\u0010K\u001a\u00020/H\u0002J\u0010\u0010L\u001a\u00020'2\u0006\u0010M\u001a\u00020NH\u0016J\b\u0010O\u001a\u00020PH\u0014J\b\u0010Q\u001a\u00020RH\u0014J\"\u0010S\u001a\u00020'2\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020/2\u0006\u0010I\u001a\u00020/H\u0002JL\u0010T\u001a\u00020'2\u0016\u0010U\u001a\u0012\u0012\u0004\u0012\u00020W0Vj\b\u0012\u0004\u0012\u00020W`X2\b\u0010Y\u001a\u0004\u0018\u00010\u00062\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/H\u0002R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR+\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR+\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0019\u0010\r\u001a\u0004\b\u0017\u0010\t\"\u0004\b\u0018\u0010\u000bR+\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\r\u001a\u0004\b\u001b\u0010\t\"\u0004\b\u001c\u0010\u000bR+\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\r\u001a\u0004\b\u001f\u0010\t\"\u0004\b \u0010\u000bR+\u0010\"\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\r\u001a\u0004\b#\u0010\t\"\u0004\b$\u0010\u000b¨\u0006["}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreWatchingActivity$WatchingListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lcom/movieboxpro/android/model/FavoriteResponse;", "()V", "<set-?>", "", "boxType", "getBoxType", "()Ljava/lang/String;", "setBoxType", "(Ljava/lang/String;)V", "boxType$delegate", "Lkotlin/properties/ReadWriteProperty;", "country", "getCountry", "setCountry", "country$delegate", "gener", "getGener", "setGener", "gener$delegate", "quality", "getQuality", "setQuality", "quality$delegate", "rating", "getRating", "setRating", "rating$delegate", "sort", "getSort", "setSort", "sort$delegate", "year", "getYear", "setYear", "year$delegate", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getPlayPath", "id", "", "currSeason", "currEpisode", "getServiceData", "Lio/reactivex/Observable;", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "gridLayoutSpan", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "isVerticalLayout", "", "markTv", "season", "episode", DownloadInfo.DOWNLOAD_OVER, "position", "markWatched", "watched", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "removeFavorite", "showScreenManage", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class WatchingListFragment extends BaseListFragment<FavoriteItem, FavoriteResponse> {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "boxType", "getBoxType()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "quality", "getQuality()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "sort", "getSort()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "country", "getCountry()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "year", "getYear()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "rating", "getRating()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(WatchingListFragment.class, "gener", "getGener()Ljava/lang/String;", 0))};
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private final ReadWriteProperty boxType$delegate;
        private final ReadWriteProperty country$delegate;
        private final ReadWriteProperty gener$delegate;
        private final ReadWriteProperty quality$delegate;
        private final ReadWriteProperty rating$delegate;
        private final ReadWriteProperty sort$delegate;
        private final ReadWriteProperty year$delegate;

        public void _$_clearFindViewByIdCache() {
            this._$_findViewCache.clear();
        }

        public View _$_findCachedViewById(int i) {
            View findViewById;
            Map<Integer, View> map = this._$_findViewCache;
            View view = map.get(Integer.valueOf(i));
            if (view == null) {
                View view2 = getView();
                if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                    return null;
                }
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return view;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_watching_item2;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isVerticalLayout() {
            return false;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        public WatchingListFragment() {
            WatchingListFragment watchingListFragment = this;
            this.boxType$delegate = FragmentArgsKt.arg(watchingListFragment);
            this.quality$delegate = FragmentArgsKt.arg(watchingListFragment);
            this.sort$delegate = FragmentArgsKt.arg(watchingListFragment);
            this.country$delegate = FragmentArgsKt.arg(watchingListFragment);
            this.year$delegate = FragmentArgsKt.arg(watchingListFragment);
            this.rating$delegate = FragmentArgsKt.arg(watchingListFragment);
            this.gener$delegate = FragmentArgsKt.arg(watchingListFragment);
        }

        private final String getBoxType() {
            return (String) this.boxType$delegate.getValue(this, $$delegatedProperties[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setBoxType(String str) {
            this.boxType$delegate.setValue(this, $$delegatedProperties[0], str);
        }

        private final String getQuality() {
            return (String) this.quality$delegate.getValue(this, $$delegatedProperties[1]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setQuality(String str) {
            this.quality$delegate.setValue(this, $$delegatedProperties[1], str);
        }

        private final String getSort() {
            return (String) this.sort$delegate.getValue(this, $$delegatedProperties[2]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setSort(String str) {
            this.sort$delegate.setValue(this, $$delegatedProperties[2], str);
        }

        private final String getCountry() {
            return (String) this.country$delegate.getValue(this, $$delegatedProperties[3]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setCountry(String str) {
            this.country$delegate.setValue(this, $$delegatedProperties[3], str);
        }

        private final String getYear() {
            return (String) this.year$delegate.getValue(this, $$delegatedProperties[4]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setYear(String str) {
            this.year$delegate.setValue(this, $$delegatedProperties[4], str);
        }

        private final String getRating() {
            return (String) this.rating$delegate.getValue(this, $$delegatedProperties[5]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setRating(String str) {
            this.rating$delegate.setValue(this, $$delegatedProperties[5], str);
        }

        private final String getGener() {
            return (String) this.gener$delegate.getValue(this, $$delegatedProperties[6]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setGener(String str) {
            this.gener$delegate.setValue(this, $$delegatedProperties[6], str);
        }

        /* compiled from: MoreWatchingActivity.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JL\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreWatchingActivity$WatchingListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/MoreWatchingActivity$WatchingListFragment;", "boxType", "", "quality", "sort", "country", "year", "rating", "gener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final WatchingListFragment newInstance(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
                WatchingListFragment watchingListFragment = new WatchingListFragment();
                if (str == null) {
                    str = "";
                }
                watchingListFragment.setBoxType(str);
                if (str2 == null) {
                    str2 = "";
                }
                watchingListFragment.setQuality(str2);
                if (str3 == null) {
                    str3 = "";
                }
                watchingListFragment.setSort(str3);
                if (str4 == null) {
                    str4 = "";
                }
                watchingListFragment.setCountry(str4);
                if (str5 == null) {
                    str5 = "";
                }
                watchingListFragment.setYear(str5);
                if (str6 == null) {
                    str6 = "";
                }
                watchingListFragment.setRating(str6);
                if (str7 == null) {
                    str7 = "";
                }
                watchingListFragment.setGener(str7);
                return watchingListFragment;
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$KcYnC5m6_elVL9X9nGvhygZ_wW0
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MoreWatchingActivity.WatchingListFragment.m172onItemClick$lambda0(MoreWatchingActivity.WatchingListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-0  reason: not valid java name */
        public static final void m172onItemClick$lambda0(WatchingListFragment this$0, BaseQuickAdapter noName_0, View view, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            FavoriteItem favoriteItem = (FavoriteItem) this$0.mAdapter.getItem(i);
            int i2 = 1;
            int season = (favoriteItem.getBox_type() != 2 || favoriteItem.getLast_episode() == null) ? 1 : favoriteItem.getLast_episode().getSeason();
            if (favoriteItem.getBox_type() == 2 && favoriteItem.getLast_episode() != null) {
                i2 = favoriteItem.getLast_episode().getEpisode();
            }
            String id = favoriteItem.getId();
            Intrinsics.checkNotNullExpressionValue(id, "item.id");
            this$0.getPlayPath(id, favoriteItem.getBox_type(), season, i2);
        }

        private final void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str, final String str2, final int i, final int i2, final int i3) {
            ScreenManageDialog newInstance$default = ScreenManageDialog.Companion.newInstance$default(ScreenManageDialog.Companion, arrayList, str, false, 4, null);
            newInstance$default.setListener(new ScreenManageDialog.OnStopDeviceListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$WatchingListFragment$showScreenManage$1
                @Override // com.movieboxpro.android.view.dialog.ScreenManageDialog.OnStopDeviceListener
                public void onStopDevice() {
                    MoreWatchingActivity.WatchingListFragment watchingListFragment = MoreWatchingActivity.WatchingListFragment.this;
                    String str3 = str2;
                    if (str3 == null) {
                        str3 = "";
                    }
                    watchingListFragment.getPlayPath(str3, i, i2, i3);
                }
            });
            newInstance$default.show(getChildFragmentManager(), ScreenManageDialog.class.getSimpleName());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void getPlayPath(final String str, final int i, final int i2, final int i3) {
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 1;
            Observable flatMap = Http.getService().playingFeedback(API.BASE_URL, API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, str, SystemUtils.getUniqueId(App.getContext()), i, i2, i3, Build.BRAND, Build.MODEL).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$x5cIJOSeV3FeYYqZOIrt3j-fafk
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m162getPlayPath$lambda1;
                    m162getPlayPath$lambda1 = MoreWatchingActivity.WatchingListFragment.m162getPlayPath$lambda1(MoreWatchingActivity.WatchingListFragment.this, str, i, i2, i3, (String) obj);
                    return m162getPlayPath$lambda1;
                }
            }).onErrorResumeNext($$Lambda$MoreWatchingActivity$WatchingListFragment$9PJ3KlzHtYViTunqLlV6HxXrRY.INSTANCE).flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$l7t5b5olOemSe9O82Mt3yGAgD4c
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m164getPlayPath$lambda6;
                    m164getPlayPath$lambda6 = MoreWatchingActivity.WatchingListFragment.m164getPlayPath$lambda6(i, str, intRef, i3, i2, (String) obj);
                    return m164getPlayPath$lambda6;
                }
            });
            Intrinsics.checkNotNullExpressionValue(flatMap, "getService().playingFeed…      }\n                }");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(flatMap, this), new MoreWatchingActivity$WatchingListFragment$getPlayPath$4(this), null, new MoreWatchingActivity$WatchingListFragment$getPlayPath$5(this), MoreWatchingActivity$WatchingListFragment$getPlayPath$6.INSTANCE, new MoreWatchingActivity$WatchingListFragment$getPlayPath$7(this, intRef), 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-1  reason: not valid java name */
        public static final ObservableSource m162getPlayPath$lambda1(WatchingListFragment this$0, String id, int i, int i2, int i3, String it) {
            Observable just;
            List<DeviceModelResponse.DeviceModel> list;
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(it, "it");
            Object parseObject = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
            Intrinsics.checkNotNullExpressionValue(parseObject, "parseObject(\n           …va)\n                    )");
            BaseResponse baseResponse = (BaseResponse) parseObject;
            if (baseResponse.getCode() == -88) {
                this$0.showLoadingView();
                List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
                if (device_list != null) {
                    list = device_list;
                } else {
                    list = new ArrayList();
                }
                this$0.showScreenManage(new ArrayList<>(list), baseResponse.getMsg(), id, i, i2, i3);
                just = Observable.empty();
            } else {
                just = Observable.just("");
            }
            return just;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-2  reason: not valid java name */
        public static final Observable m163getPlayPath$lambda2(Throwable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Observable.just("");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference failed for: r2v1, types: [T, java.lang.String] */
        /* renamed from: getPlayPath$lambda-6  reason: not valid java name */
        public static final ObservableSource m164getPlayPath$lambda6(final int i, final String id, final Ref.IntRef position, final int i2, final int i3, String it) {
            Observable compose;
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(position, "$position");
            Intrinsics.checkNotNullParameter(it, "it");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (TextUtils.isEmpty((CharSequence) objectRef2.element)) {
                objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            } else if (StringsKt.equals("0", (String) objectRef2.element, true)) {
                objectRef.element = "";
                objectRef2.element = "";
            }
            if (i == 1) {
                compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, App.deviceLang, (String) objectRef.element, (String) objectRef2.element).compose(RxUtils.rxTranslate2Bean(MovieDetail.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            } else {
                compose = Http.getService().Tv_detail(API.BASE_URL, API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, App.deviceLang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
            }
            return compose.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$nMaTZhC2OFAa1tHGZZDBedprO1Y
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    ObservableSource m165getPlayPath$lambda6$lambda5;
                    m165getPlayPath$lambda6$lambda5 = MoreWatchingActivity.WatchingListFragment.m165getPlayPath$lambda6$lambda5(Ref.ObjectRef.this, objectRef2, position, i2, i3, id, i, (BaseMediaModel) obj);
                    return m165getPlayPath$lambda6$lambda5;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-6$lambda-5  reason: not valid java name */
        public static final ObservableSource m165getPlayPath$lambda6$lambda5(Ref.ObjectRef oss, Ref.ObjectRef groupID, Ref.IntRef position, final int i, final int i2, final String id, final int i3, final BaseMediaModel model) {
            Observable map;
            Intrinsics.checkNotNullParameter(oss, "$oss");
            Intrinsics.checkNotNullParameter(groupID, "$groupID");
            Intrinsics.checkNotNullParameter(position, "$position");
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(model, "model");
            if (!(model instanceof MovieDetail)) {
                TvDetail tvDetail = (TvDetail) model;
                position.element = i;
                map = Http.getService().TV_downloadurl(API.BASE_URL, API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(i2), String.valueOf(i), (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class)).map(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$p63wcDnGKwqAjSoYx4Qhte5yrjQ
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        Pair m167getPlayPath$lambda6$lambda5$lambda4;
                        m167getPlayPath$lambda6$lambda5$lambda4 = MoreWatchingActivity.WatchingListFragment.m167getPlayPath$lambda6$lambda5$lambda4(id, i2, i, model, (BaseMediaModel) obj);
                        return m167getPlayPath$lambda6$lambda5$lambda4;
                    }
                });
                Intrinsics.checkNotNullExpressionValue(map, "getService().TV_download…                        }");
            } else {
                Observable<R> compose = Http.getService().Movie_detail(API.BASE_URL, API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, App.deviceLang, (String) oss.element, (String) groupID.element).compose(RxUtils.rxTranslate2Bean(BaseMediaModel.class));
                Intrinsics.checkNotNullExpressionValue(compose, "this.compose(RxUtils.rxT…late2Bean(T::class.java))");
                map = compose.map(new Function() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$NLjgQUxB2kVhyQNe5XLz4O5x7g8
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        Pair m166getPlayPath$lambda6$lambda5$lambda3;
                        m166getPlayPath$lambda6$lambda5$lambda3 = MoreWatchingActivity.WatchingListFragment.m166getPlayPath$lambda6$lambda5$lambda3(id, i3, model, (BaseMediaModel) obj);
                        return m166getPlayPath$lambda6$lambda5$lambda3;
                    }
                });
                Intrinsics.checkNotNullExpressionValue(map, "getService().Movie_detai…                        }");
            }
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-6$lambda-5$lambda-3  reason: not valid java name */
        public static final Pair m166getPlayPath$lambda6$lambda5$lambda3(String id, int i, BaseMediaModel model, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(model, "$model");
            Intrinsics.checkNotNullParameter(it, "it");
            Download findByIdAndType = App.getDB().downloadDao().findByIdAndType(id, i, 2);
            if (findByIdAndType != null) {
                findByIdAndType.setPath(Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByIdAndType.getPath(), findByIdAndType.getTitle(), Constant.DIR_DOWNLOAD)));
                if (new File(findByIdAndType.getPath()).exists()) {
                    BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByIdAndType), BaseMediaModel.DownloadFile.class);
                    downloadFile.real_quality = findByIdAndType.getQuality();
                    it.list.add(0, downloadFile);
                }
            }
            return new Pair(model, it);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getPlayPath$lambda-6$lambda-5$lambda-4  reason: not valid java name */
        public static final Pair m167getPlayPath$lambda6$lambda5$lambda4(String id, int i, int i2, BaseMediaModel model, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(id, "$id");
            Intrinsics.checkNotNullParameter(model, "$model");
            Intrinsics.checkNotNullParameter(it, "it");
            Download findByTidAndSeasonEpisode = App.getDB().downloadDao().findByTidAndSeasonEpisode(id, i, i2);
            if (findByTidAndSeasonEpisode != null && findByTidAndSeasonEpisode.getStatue() == 2) {
                findByTidAndSeasonEpisode.setPath(Constant.DIR_DOWNLOAD + ((Object) File.separator) + ((Object) RemoteFileUtil.getFileName(findByTidAndSeasonEpisode.getPath(), findByTidAndSeasonEpisode.getTitle(), Constant.DIR_DOWNLOAD)));
                if (new File(findByTidAndSeasonEpisode.getPath()).exists()) {
                    BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) JSONObject.parseObject(JSONObject.toJSONString(findByTidAndSeasonEpisode), BaseMediaModel.DownloadFile.class);
                    downloadFile.real_quality = findByTidAndSeasonEpisode.getQuality();
                    it.list.add(0, downloadFile);
                }
            }
            return new Pair(model, it);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void goTvPlayer(TvDetail tvDetail) {
            if (X86HintUtils.checkX86(getContext())) {
                return;
            }
            Context context = getContext();
            CastSession castSession = null;
            if (context != null) {
                try {
                    castSession = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    castSession = null;
                }
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(tvDetail);
                Context context2 = getContext();
                if (context2 == null) {
                    return;
                }
                ChooseActivity.Companion.start(context2, false, playerStrategy);
                return;
            }
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            List<TvDetail.SeasonDetail> list = tvDetail.episode;
            Intrinsics.checkNotNullExpressionValue(list, "tvDetail.episode");
            for (TvDetail.SeasonDetail seasonDetail : list) {
                TvSeasonList tvSeasonList = new TvSeasonList();
                tvSeasonList.setEpisode(seasonDetail.episode);
                tvSeasonList.setSeason(seasonDetail.season);
                Long l = seasonDetail.play_progress.get(DownloadInfo.DOWNLOAD_OVER);
                long j = 0;
                tvSeasonList.setOver(l == null ? 0L : l.longValue());
                Long l2 = seasonDetail.play_progress.get("seconds");
                if (l2 != null) {
                    j = l2.longValue();
                }
                tvSeasonList.setSeconds(j);
                tvSeasonList.setTid(seasonDetail.tid);
                tvSeasonList.setId(seasonDetail.id);
                arrayList.add(tvSeasonList);
            }
            Bundle build = ParamsUtils.newBuilder().addParam(TvDetailActivity.SEASON_TV_LIST, arrayList).addParam("videoplayer_params", tvDetail).addParam(VideoPlayerActivity.VIDEO_ID, tvDetail.seasonDetail.season).addParam("videoplayer_episode", tvDetail.seasonDetail.episode).addParam("FeaturedFragment", false).build();
            if (PopPlayerManager.Companion.getInstance().isPopShow()) {
                PopPlayerManager.Companion.getInstance().setNewPlay(build, tvDetail);
                return;
            }
            Intent intent = new Intent(getContext(), TvPlayerActivity.class);
            intent.putExtras(build);
            startActivity(intent);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void goMoviePlayer(MovieDetail movieDetail) {
            Context context = getContext();
            CastSession castSession = null;
            if (context != null) {
                try {
                    castSession = CastContext.getSharedInstance(context).getSessionManager().getCurrentCastSession();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    castSession = null;
                }
            }
            if (castSession != null && castSession.isConnected()) {
                PlayerStrategy playerStrategy = new PlayerStrategy();
                playerStrategy.setInstace(movieDetail);
                Context context2 = getContext();
                if (context2 == null) {
                    return;
                }
                ChooseActivity.Companion.start(context2, false, playerStrategy);
                return;
            }
            Context context3 = getContext();
            if (PopPlayerManager.Companion.getInstance().isPopShow()) {
                PopPlayerManager.Companion.getInstance().setNewPlay(movieDetail);
            } else {
                MoviePlayerActivity.start(context3, movieDetail, movieDetail.id);
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return CommonExtKt.request$default(Api.INSTANCE.getCollectList(getBoxType(), getQuality(), getSort(), getCountry(), getYear(), getRating(), getGener(), this.mCurrentPage, this.mPageSize, 0), null, 1, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<FavoriteItem> getData(FavoriteResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<FavoriteItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            boolean z = false;
            if (getContext() != null && CommonUtils.isTablet()) {
                z = true;
            }
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(z ? 5 : 3, CommonExtKt.dp2Px(10), true));
            this.mAdapter.addChildClickViewIds(R.id.ivDetail, R.id.ivMark, R.id.ivMore);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markWatched(int i, String str, int i2, int i3) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_watch_plan_mark").param("box_type", Integer.valueOf(i2)).param("mid", str).param("watched", Integer.valueOf(i3)).asRequest(), this), new MoreWatchingActivity$WatchingListFragment$markWatched$1(this), null, new MoreWatchingActivity$WatchingListFragment$markWatched$2(this), null, new MoreWatchingActivity$WatchingListFragment$markWatched$3(this, i), 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markTv(String str, String str2, String str3, int i, int i2) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().AddWatchedFlag(API.BASE_URL, API.Tv.TV_over_v2, App.getUserData().uid_v2, i, str, str2, str3), this), new MoreWatchingActivity$WatchingListFragment$markTv$1(this), null, new MoreWatchingActivity$WatchingListFragment$markTv$2(this), null, new MoreWatchingActivity$WatchingListFragment$markTv$3(this, i2), 10, null);
        }

        private final void removeFavorite(String str, int i, int i2) {
            RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this, "User_watch_plan_del").param("mid", str).param("box_type", Integer.valueOf(i)).asMsg(), new MoreWatchingActivity$WatchingListFragment$removeFavorite$1(this), null, new MoreWatchingActivity$WatchingListFragment$removeFavorite$2(this), null, new MoreWatchingActivity$WatchingListFragment$removeFavorite$3(this, i2), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$wUhq2niZ_pd_W539UaQrJJ6chNM
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MoreWatchingActivity.WatchingListFragment.m170onItemChildClick$lambda16(MoreWatchingActivity.WatchingListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-16  reason: not valid java name */
        public static final void m170onItemChildClick$lambda16(final WatchingListFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            final FavoriteItem favoriteItem = (FavoriteItem) this$0.mAdapter.getItem(i);
            switch (view.getId()) {
                case R.id.ivDetail /* 2131296946 */:
                    if (favoriteItem.getBox_type() == 1) {
                        MovieDetailActivity.start(this$0.getContext(), favoriteItem.getId(), favoriteItem.getPoster());
                        return;
                    } else {
                        TvDetailActivity.start(this$0.getContext(), favoriteItem.getId());
                        return;
                    }
                case R.id.ivMark /* 2131296969 */:
                    if (favoriteItem.getLast_episode() == null) {
                        this$0.markWatched(i, favoriteItem.getId(), favoriteItem.getBox_type(), 1);
                        return;
                    } else {
                        this$0.markTv(favoriteItem.getId(), String.valueOf(favoriteItem.getLast_episode().getSeason()), String.valueOf(favoriteItem.getLast_episode().getEpisode()), 1, i);
                        return;
                    }
                case R.id.ivMore /* 2131296970 */:
                    ArrayList<Pair> arrayList = new ArrayList();
                    if (favoriteItem.getBox_type() == 1) {
                        arrayList.add(new Pair("ADD THIS MOVIE TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$WatchingListFragment$onItemChildClick$1$1
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public void onClick(int i2) {
                                MoreWatchingActivity.WatchingListFragment.this.markWatched(i, favoriteItem.getId(), 1, 1);
                            }
                        }));
                        arrayList.add(new Pair("ADD THIS MOVIE TO WAITING", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$WatchingListFragment$onItemChildClick$1$2
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public void onClick(int i2) {
                                MoreWatchingActivity.WatchingListFragment.this.markWatched(i, favoriteItem.getId(), 1, 0);
                            }
                        }));
                    } else {
                        if (favoriteItem.getLast_episode() != null) {
                            arrayList.add(new Pair("ADD CURRENT EPISODE TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$WatchingListFragment$onItemChildClick$1$3
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i2) {
                                    MoreWatchingActivity.WatchingListFragment.this.markTv(favoriteItem.getId(), String.valueOf(favoriteItem.getLast_episode().getSeason()), String.valueOf(favoriteItem.getLast_episode().getEpisode()), 1, i);
                                }
                            }));
                            arrayList.add(new Pair("ADD CURRENT SEASON TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$WatchingListFragment$onItemChildClick$1$4
                                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                                public void onClick(int i2) {
                                    MoreWatchingActivity.WatchingListFragment.this.markTv(favoriteItem.getId(), String.valueOf(favoriteItem.getLast_episode().getSeason()), "", 1, i);
                                }
                            }));
                        }
                        arrayList.add(new Pair("ADD THIS TV SHOW TO WATCHED", new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$WatchingListFragment$onItemChildClick$1$5
                            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                            public void onClick(int i2) {
                                MoreWatchingActivity.WatchingListFragment.this.markWatched(i, favoriteItem.getId(), 2, 1);
                            }
                        }));
                    }
                    ActionSheetDialog canceledOnTouchOutside = new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true);
                    for (Pair pair : arrayList) {
                        canceledOnTouchOutside.addSheetItem((String) pair.getFirst(), ActionSheetDialog.SheetItemColor.White, (ActionSheetDialog.OnSheetItemClickListener) pair.getSecond());
                    }
                    canceledOnTouchOutside.addSheetItem(favoriteItem.getBox_type() == 1 ? "REMOVE THIS MOVIE FROM WATCHING" : "REMOVE THIS TV SHOW FROM WATCHING", ActionSheetDialog.SheetItemColor.Red, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MoreWatchingActivity$WatchingListFragment$ifjxaoCSUayWRyP1tWLNOnNRIeM
                        @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                        public final void onClick(int i2) {
                            MoreWatchingActivity.WatchingListFragment.m171onItemChildClick$lambda16$lambda15(MoreWatchingActivity.WatchingListFragment.this, favoriteItem, i, i2);
                        }
                    }).show();
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-16$lambda-15  reason: not valid java name */
        public static final void m171onItemChildClick$lambda16$lambda15(WatchingListFragment this$0, FavoriteItem favoriteItem, int i, int i2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.removeFavorite(favoriteItem.getId(), favoriteItem.getBox_type(), i);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            boolean z = false;
            if (getContext() != null && CommonUtils.isTablet()) {
                z = true;
            }
            return z ? 5 : 3;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = FavoriteItem.class;
            this.mPageClass = FavoriteResponse.class;
        }

        @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration newConfig) {
            Intrinsics.checkNotNullParameter(newConfig, "newConfig");
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == 2) {
                RecyclerView mRecyclerView = this.mRecyclerView;
                Intrinsics.checkNotNullExpressionValue(mRecyclerView, "mRecyclerView");
                CommonExtKt.resetSpanCount(mRecyclerView, this.mAdapter, 5);
                if (this.mAdapter != null) {
                    this.mAdapter.notifyDataSetChanged();
                }
            } else if (getResources().getConfiguration().orientation == 1) {
                RecyclerView mRecyclerView2 = this.mRecyclerView;
                Intrinsics.checkNotNullExpressionValue(mRecyclerView2, "mRecyclerView");
                CommonExtKt.resetSpanCount(mRecyclerView2, this.mAdapter, 3);
                if (this.mAdapter != null) {
                    this.mAdapter.notifyDataSetChanged();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, FavoriteItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
            TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
            CommonExtKt.visible((ImageView) helper.getView(R.id.ivMark));
            CommonExtKt.visible((ImageView) helper.getView(R.id.ivMore));
            CommonExtKt.visible((ImageView) helper.getView(R.id.ivContinue));
            GlideUtils.loadCornerPortraitGifHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivPoster), 8);
            CommonExtKt.visible(progressBar);
            CommonExtKt.visible((ImageView) helper.getView(R.id.ivDetail));
            if (item.getBox_type() == 2) {
                if (item.getLast_episode() != null) {
                    progressBar.setMax(item.getLast_episode().getRuntime() * 60);
                    progressBar.setProgress(item.getLast_episode().getSeconds());
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format("S%s E%s", Arrays.copyOf(new Object[]{Integer.valueOf(item.getLast_episode().getSeason()), Integer.valueOf(item.getLast_episode().getEpisode())}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    CommonExtKt.textShadow$default(textView, format, 0, 0, 6, null);
                    CommonExtKt.visible(textView);
                    return;
                }
                CommonExtKt.invisible(textView);
                progressBar.setMax(1);
                progressBar.setProgress(0);
                return;
            }
            CommonExtKt.invisible(textView);
            progressBar.setMax(item.getRuntime() * 60);
            progressBar.setProgress(item.getSeconds());
        }
    }
}
