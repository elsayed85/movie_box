package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Size;
import android.util.SizeF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.flurry.android.FlurryAgent;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.gyf.immersionbar.ImmersionBar;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
/* compiled from: CommonExt.kt */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a;\u0010\u0000\u001a\u00020\u00012.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004¢\u0006\u0002\u0010\u0007\u001a\u001b\u0010\b\u001a\u00020\t\"\u0006\b\u0000\u0010\n\u0018\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0086\b\u001aP\u0010\b\u001a\u00020\t\"\u0006\b\u0000\u0010\n\u0018\u00012\b\u0010\u000b\u001a\u0004\u0018\u00010\f2.\u0010\u0002\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00040\u0003\"\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0086\b¢\u0006\u0002\u0010\r\u001a\"\u0010\u000e\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012\u001a\u0083\u0002\u0010\u0014\u001a\u00020\u0015*\u00020\u00162d\b\u0006\u0010\u0017\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u00182d\b\u0006\u0010 \u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u00182%\b\u0006\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u001f0\"H\u0086\bø\u0001\u0000\u001a\u0018\u0010$\u001a\u00020%*\u00020\u00122\f\u0010&\u001a\b\u0012\u0002\b\u0003\u0018\u00010'\u001a\u0012\u0010(\u001a\u00020\u0012*\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0012\u001a\u0012\u0010(\u001a\u00020\u0012*\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0012\u001a\n\u0010)\u001a\u00020\u001f*\u00020*\u001a7\u0010+\u001a\u00020\u0015*\u00020\u00162%\b\u0004\u0010,\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010#¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u001f0\"H\u0086\bø\u0001\u0000\u001av\u0010-\u001a\u00020\u0015*\u00020\u00162d\b\u0004\u0010,\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u0018H\u0086\bø\u0001\u0000\u001av\u0010.\u001a\u00020\u0015*\u00020\u00162d\b\u0004\u0010,\u001a^\u0012\u0015\u0012\u0013\u0018\u00010\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u001f0\u0018H\u0086\bø\u0001\u0000\u001a\n\u0010/\u001a\u00020\u0012*\u00020\u0012\u001a\n\u00100\u001a\u00020\u0005*\u00020\u0012\u001a\n\u00101\u001a\u00020\u0012*\u00020\f\u001a\n\u00102\u001a\u00020\u0012*\u00020\f\u001a\n\u00103\u001a\u00020\u001f*\u000204\u001a\n\u00105\u001a\u00020\u001f*\u000206\u001a\n\u00107\u001a\u00020\u001f*\u000206\u001a.\u00108\u001a\u00020\u001f*\u00020*2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00109\u001a\u00020\u00122\b\b\u0002\u0010:\u001a\u00020\u00122\b\b\u0002\u0010;\u001a\u00020%\u001a\u0014\u0010<\u001a\u00020\u001f*\u00020=2\b\b\u0002\u0010>\u001a\u00020\u0012\u001a2\u0010?\u001a\u00020\u001f*\u00020*2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010@\u001a\u00020%2\b\b\u0002\u0010:\u001a\u00020\u00122\b\b\u0002\u0010;\u001a\u00020%\u001a\n\u0010A\u001a\u00020\u001f*\u000204\u001aT\u0010B\u001a\u00020\u001f\"\u0004\b\u0000\u0010\n*\u000e\u0012\u0004\u0012\u0002H\n\u0012\u0004\u0012\u00020D0C26\u0010E\u001a2\u0012\u0013\u0012\u0011H\n¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(G\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(H\u0012\u0004\u0012\u00020\u001f0F\u001a\u0014\u0010I\u001a\u000204*\u00020J2\b\b\u0001\u0010K\u001a\u00020\u0012\u001a\u0012\u0010L\u001a\u00020\u001f*\u00020\u00062\u0006\u0010M\u001a\u00020\u0006\u001a\u0014\u0010N\u001a\u00020\u001f*\u00020\u00062\b\u0010O\u001a\u0004\u0018\u00010\u0005\u001a\u0014\u0010P\u001a\u00020\u001f*\u00020\u00062\b\u0010Q\u001a\u0004\u0018\u00010\u0005\u001a\u0012\u0010R\u001a\u00020\u001f*\u00020\f2\u0006\u0010S\u001a\u00020\u0005\u001a<\u0010T\u001a\b\u0012\u0004\u0012\u00020\u00050U*\"\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060Vj\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0006`W2\n\b\u0002\u0010X\u001a\u0004\u0018\u00010Y\u001a&\u0010Z\u001a\u00020\u001f*\u00020*2\u0012\u0010[\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u00020D\u0018\u00010C2\u0006\u00109\u001a\u00020\u0012\u001a\n\u0010\\\u001a\u00020\u001f*\u000206\u001a\u0015\u0010]\u001a\u00020\u001f\"\u0006\b\u0000\u0010\n\u0018\u0001*\u00020\fH\u0086\b\u001a&\u0010^\u001a\u00020\u001f*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010_\u001a\u00020\u00122\b\b\u0002\u0010`\u001a\u00020\u0012\u001a\u0012\u0010`\u001a\u00020\u001f*\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0012\u001a&\u0010a\u001a\u00020\u001f*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010_\u001a\u00020\u00122\b\b\u0002\u0010`\u001a\u00020\u0012\u001a\n\u0010b\u001a\u00020\u001f*\u000204\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006c"}, d2 = {"bundleOf", "Landroid/os/Bundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "intentOf", "Landroid/content/Intent;", ExifInterface.GPS_DIRECTION_TRUE, "context", "Landroid/content/Context;", "(Landroid/content/Context;[Lkotlin/Pair;)Landroid/content/Intent;", "addText", "Lcom/movieboxpro/android/utils/SpanUtils;", "text", "sizeSp", "", "colorId", "addTextChangedListener", "Landroid/text/TextWatcher;", "Landroid/widget/TextView;", "beforeTextChanged", "Lkotlin/Function4;", "", "Lkotlin/ParameterName;", "name", TtmlNode.START, "count", TtmlNode.ANNOTATION_POSITION_AFTER, "", "onTextChanged", "afterTextChanged", "Lkotlin/Function1;", "Landroid/text/Editable;", "checkIndexLegal", "", "list", "", "colorInt", "disableRefreshAnimation", "Landroidx/recyclerview/widget/RecyclerView;", "doAfterTextChanged", "action", "doBeforeTextChanged", "doOnTextChanged", "dp2Px", "format2", "getScreenHeight", "getScreenWidth", "gone", "Landroid/view/View;", "hide", "Landroidx/swiperefreshlayout/widget/SwipeRefreshLayout;", "initColor", "initGridAndMargin", "spanCount", "margin", "includeEdge", "initImmersionBar", "Landroid/app/Activity;", "color", "initLinearAndMargin", "isVertical", "invisible", "itemClick", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "click", "Lkotlin/Function2;", "item", "position", "layoutInflate", "Landroid/view/ViewGroup;", "layoutResId", "logCollections", "any", "logD", "s", "logXmlD", "xml", "onMobEvent", NotificationCompat.CATEGORY_EVENT, "request", "Lio/reactivex/Observable;", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "resetSpanCount", "adapter", "show", "startActivity", "textBoldShadow", "textSize", "textColor", "textShadow", "visible", "app_webRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CommonExtKt {
    public static final String format2(int i) {
        if (i < 10) {
            return Intrinsics.stringPlus("0", Integer.valueOf(i));
        }
        return String.valueOf(i);
    }

    public static final void visible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public static final void gone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }

    public static final void invisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
    }

    public static /* synthetic */ Observable request$default(HashMap hashMap, LifecycleOwner lifecycleOwner, int i, Object obj) {
        if ((i & 1) != 0) {
            lifecycleOwner = null;
        }
        return request(hashMap, lifecycleOwner);
    }

    public static final Observable<String> request(HashMap<String, Object> hashMap, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(hashMap, "<this>");
        return HttpRequest.Companion.post(hashMap, lifecycleOwner).asRequest();
    }

    public static final int colorInt(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return ContextCompat.getColor(context, i);
    }

    public static final int colorInt(Object obj, int i) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        return ContextCompat.getColor(App.getContext(), i);
    }

    public static final View layoutInflate(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(this.context).infla…layoutResId, this, false)");
        return inflate;
    }

    public static final int dp2Px(int i) {
        return DensityUtils.dp2px(App.getContext(), i);
    }

    public static /* synthetic */ void textShadow$default(TextView textView, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 12;
        }
        if ((i3 & 4) != 0) {
            i2 = R.color.white;
        }
        textShadow(textView, str, i, i2);
    }

    public static final void textShadow(TextView textView, String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        SpanUtils.with(textView).append(text).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), i2)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
    }

    public static /* synthetic */ void textBoldShadow$default(TextView textView, String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 14;
        }
        if ((i3 & 4) != 0) {
            i2 = R.color.white;
        }
        textBoldShadow(textView, str, i, i2);
    }

    public static final void textBoldShadow(TextView textView, String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        SpanUtils.with(textView).append(text).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), i2)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
    }

    public static final void disableRefreshAnimation(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    public static final <T> void itemClick(final BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter, final Function2<? super T, ? super Integer, Unit> click) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<this>");
        Intrinsics.checkNotNullParameter(click, "click");
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.utils.-$$Lambda$CommonExtKt$QJB6pBAQVhJZmEY3zpApIc-A1bE
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                CommonExtKt.m97itemClick$lambda0(Function2.this, baseQuickAdapter, baseQuickAdapter2, view, i);
            }
        });
    }

    /* renamed from: itemClick$lambda-0 */
    public static final void m97itemClick$lambda0(Function2 click, BaseQuickAdapter this_itemClick, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(click, "$click");
        Intrinsics.checkNotNullParameter(this_itemClick, "$this_itemClick");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        click.invoke(this_itemClick.getItem(i), Integer.valueOf(i));
    }

    public static final boolean checkIndexLegal(int i, List<?> list) {
        if (i >= 0) {
            return i < (list == null ? 0 : list.size());
        }
        return false;
    }

    public static final SpanUtils addText(SpanUtils spanUtils, String text, int i, int i2) {
        Intrinsics.checkNotNullParameter(spanUtils, "<this>");
        Intrinsics.checkNotNullParameter(text, "text");
        spanUtils.append(text).setFontSize(i, true).setForegroundColor(ContextCompat.getColor(App.getContext(), i2));
        return spanUtils;
    }

    public static final void textColor(TextView textView, int i) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        textView.setTextColor(ContextCompat.getColor(App.getContext(), i));
    }

    public static final /* synthetic */ <T> void startActivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        context.startActivity(new Intent(context, Object.class));
    }

    public static /* synthetic */ void initLinearAndMargin$default(RecyclerView recyclerView, Context context, boolean z, int i, boolean z2, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        if ((i2 & 4) != 0) {
            i = 10;
        }
        if ((i2 & 8) != 0) {
            z2 = true;
        }
        initLinearAndMargin(recyclerView, context, z, i, z2);
    }

    public static final void initLinearAndMargin(RecyclerView recyclerView, Context context, boolean z, int i, boolean z2) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        if (z) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 1, false));
            recyclerView.addItemDecoration(new LinearItemDecoration(i, z2));
        } else {
            recyclerView.setLayoutManager(new LinearLayoutManager(context, 0, false));
            recyclerView.addItemDecoration(new LinearItemDecoration(0, i, z2));
        }
        disableRefreshAnimation(recyclerView);
    }

    public static /* synthetic */ void initGridAndMargin$default(RecyclerView recyclerView, Context context, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 10;
        }
        if ((i3 & 8) != 0) {
            z = true;
        }
        initGridAndMargin(recyclerView, context, i, i2, z);
    }

    public static final void initGridAndMargin(RecyclerView recyclerView, Context context, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        recyclerView.setLayoutManager(new GridLayoutManager(context, i));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(i2, z));
        disableRefreshAnimation(recyclerView);
    }

    public static final void resetSpanCount(RecyclerView recyclerView, BaseQuickAdapter<?, BaseViewHolder> baseQuickAdapter, int i) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            ((GridLayoutManager) layoutManager).setSpanCount(i);
        }
        if (baseQuickAdapter == null) {
            return;
        }
        baseQuickAdapter.notifyDataSetChanged();
    }

    public static final void onMobEvent(Context context, String event) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        FlurryAgent.logEvent(event);
    }

    public static final int getScreenWidth(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static final int getScreenHeight(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static /* synthetic */ void initImmersionBar$default(Activity activity, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = R.color.color_main_back;
        }
        initImmersionBar(activity, i);
    }

    public static final void initImmersionBar(Activity activity, int i) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        ImmersionBar.with(activity).fitsSystemWindows(true).navigationBarColor(i).statusBarColor(i).init();
    }

    public static final void initColor(SwipeRefreshLayout swipeRefreshLayout) {
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<this>");
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FFDD00"));
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.parseColor("#464646"));
    }

    public static final void show(SwipeRefreshLayout swipeRefreshLayout) {
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<this>");
        swipeRefreshLayout.setRefreshing(true);
    }

    public static final void hide(SwipeRefreshLayout swipeRefreshLayout) {
        Intrinsics.checkNotNullParameter(swipeRefreshLayout, "<this>");
        swipeRefreshLayout.setRefreshing(false);
    }

    public static final void logD(Object obj, String str) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        LogUtils logUtils = LogUtils.INSTANCE;
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this::class.java.simpleName");
        logUtils.logD(simpleName, str);
    }

    public static final void logXmlD(Object obj, String str) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        LogUtils logUtils = LogUtils.INSTANCE;
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "this::class.java.simpleName");
        logUtils.logD(simpleName, str);
    }

    public static final void logCollections(Object obj, Object any) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(any, "any");
        LogUtils.INSTANCE.logCollections(any);
    }

    public static final /* synthetic */ <T> Intent intentOf(Context context, Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intent intent = new Intent(context, Object.class);
        Bundle bundle = new Bundle(pairs.length);
        int length = pairs.length;
        int i = 0;
        while (i < length) {
            Pair<String, ? extends Object> pair = pairs[i];
            i++;
            String component1 = pair.component1();
            Object component2 = pair.component2();
            if (component2 == null) {
                bundle.putString(component1, null);
            } else if (component2 instanceof Boolean) {
                bundle.putBoolean(component1, ((Boolean) component2).booleanValue());
            } else if (component2 instanceof Byte) {
                bundle.putByte(component1, ((Number) component2).byteValue());
            } else if (component2 instanceof Character) {
                bundle.putChar(component1, ((Character) component2).charValue());
            } else if (component2 instanceof Double) {
                bundle.putDouble(component1, ((Number) component2).doubleValue());
            } else if (component2 instanceof Float) {
                bundle.putFloat(component1, ((Number) component2).floatValue());
            } else if (component2 instanceof Integer) {
                bundle.putInt(component1, ((Number) component2).intValue());
            } else if (component2 instanceof Long) {
                bundle.putLong(component1, ((Number) component2).longValue());
            } else if (component2 instanceof Short) {
                bundle.putShort(component1, ((Number) component2).shortValue());
            } else if (component2 instanceof Bundle) {
                bundle.putBundle(component1, (Bundle) component2);
            } else if (component2 instanceof CharSequence) {
                bundle.putCharSequence(component1, (CharSequence) component2);
            } else if (component2 instanceof Parcelable) {
                bundle.putParcelable(component1, (Parcelable) component2);
            } else if (component2 instanceof boolean[]) {
                bundle.putBooleanArray(component1, (boolean[]) component2);
            } else if (component2 instanceof byte[]) {
                bundle.putByteArray(component1, (byte[]) component2);
            } else if (component2 instanceof char[]) {
                bundle.putCharArray(component1, (char[]) component2);
            } else if (component2 instanceof double[]) {
                bundle.putDoubleArray(component1, (double[]) component2);
            } else if (component2 instanceof float[]) {
                bundle.putFloatArray(component1, (float[]) component2);
            } else if (component2 instanceof int[]) {
                bundle.putIntArray(component1, (int[]) component2);
            } else if (component2 instanceof long[]) {
                bundle.putLongArray(component1, (long[]) component2);
            } else if (component2 instanceof short[]) {
                bundle.putShortArray(component1, (short[]) component2);
            } else if (component2 instanceof Object[]) {
                Class<?> componentType = component2.getClass().getComponentType();
                Intrinsics.checkNotNull(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    if (component2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                    }
                    bundle.putParcelableArray(component1, (Parcelable[]) component2);
                } else if (String.class.isAssignableFrom(componentType)) {
                    if (component2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                    }
                    bundle.putStringArray(component1, (String[]) component2);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    if (component2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                    }
                    bundle.putCharSequenceArray(component1, (CharSequence[]) component2);
                } else if (Serializable.class.isAssignableFrom(componentType)) {
                    bundle.putSerializable(component1, (Serializable) component2);
                } else {
                    String canonicalName = componentType.getCanonicalName();
                    throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + component1 + Typography.quote);
                }
            } else if (component2 instanceof Serializable) {
                bundle.putSerializable(component1, (Serializable) component2);
            } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                bundle.putBinder(component1, (IBinder) component2);
            } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                bundle.putSize(component1, (Size) component2);
            } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                bundle.putSizeF(component1, (SizeF) component2);
            } else {
                String canonicalName2 = component2.getClass().getCanonicalName();
                throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + component1 + Typography.quote);
            }
        }
        intent.putExtras(bundle);
        return intent;
    }

    public static final Bundle bundleOf(Pair<String, ? extends Object>... pairs) {
        Intrinsics.checkNotNullParameter(pairs, "pairs");
        Bundle bundle = new Bundle(pairs.length);
        int length = pairs.length;
        int i = 0;
        while (i < length) {
            Pair<String, ? extends Object> pair = pairs[i];
            i++;
            String component1 = pair.component1();
            Object component2 = pair.component2();
            if (component2 == null) {
                bundle.putString(component1, null);
            } else if (component2 instanceof Boolean) {
                bundle.putBoolean(component1, ((Boolean) component2).booleanValue());
            } else if (component2 instanceof Byte) {
                bundle.putByte(component1, ((Number) component2).byteValue());
            } else if (component2 instanceof Character) {
                bundle.putChar(component1, ((Character) component2).charValue());
            } else if (component2 instanceof Double) {
                bundle.putDouble(component1, ((Number) component2).doubleValue());
            } else if (component2 instanceof Float) {
                bundle.putFloat(component1, ((Number) component2).floatValue());
            } else if (component2 instanceof Integer) {
                bundle.putInt(component1, ((Number) component2).intValue());
            } else if (component2 instanceof Long) {
                bundle.putLong(component1, ((Number) component2).longValue());
            } else if (component2 instanceof Short) {
                bundle.putShort(component1, ((Number) component2).shortValue());
            } else if (component2 instanceof Bundle) {
                bundle.putBundle(component1, (Bundle) component2);
            } else if (component2 instanceof CharSequence) {
                bundle.putCharSequence(component1, (CharSequence) component2);
            } else if (component2 instanceof Parcelable) {
                bundle.putParcelable(component1, (Parcelable) component2);
            } else if (component2 instanceof boolean[]) {
                bundle.putBooleanArray(component1, (boolean[]) component2);
            } else if (component2 instanceof byte[]) {
                bundle.putByteArray(component1, (byte[]) component2);
            } else if (component2 instanceof char[]) {
                bundle.putCharArray(component1, (char[]) component2);
            } else if (component2 instanceof double[]) {
                bundle.putDoubleArray(component1, (double[]) component2);
            } else if (component2 instanceof float[]) {
                bundle.putFloatArray(component1, (float[]) component2);
            } else if (component2 instanceof int[]) {
                bundle.putIntArray(component1, (int[]) component2);
            } else if (component2 instanceof long[]) {
                bundle.putLongArray(component1, (long[]) component2);
            } else if (component2 instanceof short[]) {
                bundle.putShortArray(component1, (short[]) component2);
            } else if (component2 instanceof Object[]) {
                Class<?> componentType = component2.getClass().getComponentType();
                Intrinsics.checkNotNull(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    if (component2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
                    }
                    bundle.putParcelableArray(component1, (Parcelable[]) component2);
                } else if (String.class.isAssignableFrom(componentType)) {
                    if (component2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                    }
                    bundle.putStringArray(component1, (String[]) component2);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    if (component2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
                    }
                    bundle.putCharSequenceArray(component1, (CharSequence[]) component2);
                } else if (Serializable.class.isAssignableFrom(componentType)) {
                    bundle.putSerializable(component1, (Serializable) component2);
                } else {
                    String canonicalName = componentType.getCanonicalName();
                    throw new IllegalArgumentException("Illegal value array type " + ((Object) canonicalName) + " for key \"" + component1 + Typography.quote);
                }
            } else if (component2 instanceof Serializable) {
                bundle.putSerializable(component1, (Serializable) component2);
            } else if (Build.VERSION.SDK_INT >= 18 && (component2 instanceof Binder)) {
                bundle.putBinder(component1, (IBinder) component2);
            } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof Size)) {
                bundle.putSize(component1, (Size) component2);
            } else if (Build.VERSION.SDK_INT >= 21 && (component2 instanceof SizeF)) {
                bundle.putSizeF(component1, (SizeF) component2);
            } else {
                String canonicalName2 = component2.getClass().getCanonicalName();
                throw new IllegalArgumentException("Illegal value type " + ((Object) canonicalName2) + " for key \"" + component1 + Typography.quote);
            }
        }
        return bundle;
    }

    public static /* synthetic */ TextWatcher addTextChangedListener$default(TextView textView, Function4 beforeTextChanged, Function4 onTextChanged, Function1 afterTextChanged, int i, Object obj) {
        if ((i & 1) != 0) {
            beforeTextChanged = CommonExtKt$addTextChangedListener$1.INSTANCE;
        }
        if ((i & 2) != 0) {
            onTextChanged = CommonExtKt$addTextChangedListener$2.INSTANCE;
        }
        if ((i & 4) != 0) {
            afterTextChanged = CommonExtKt$addTextChangedListener$3.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(beforeTextChanged, "beforeTextChanged");
        Intrinsics.checkNotNullParameter(onTextChanged, "onTextChanged");
        Intrinsics.checkNotNullParameter(afterTextChanged, "afterTextChanged");
        CommonExtKt$addTextChangedListener$textWatcher$1 commonExtKt$addTextChangedListener$textWatcher$1 = new CommonExtKt$addTextChangedListener$textWatcher$1(afterTextChanged, beforeTextChanged, onTextChanged);
        textView.addTextChangedListener(commonExtKt$addTextChangedListener$textWatcher$1);
        return commonExtKt$addTextChangedListener$textWatcher$1;
    }

    public static final TextWatcher addTextChangedListener(TextView textView, Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> beforeTextChanged, Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> onTextChanged, Function1<? super Editable, Unit> afterTextChanged) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(beforeTextChanged, "beforeTextChanged");
        Intrinsics.checkNotNullParameter(onTextChanged, "onTextChanged");
        Intrinsics.checkNotNullParameter(afterTextChanged, "afterTextChanged");
        CommonExtKt$addTextChangedListener$textWatcher$1 commonExtKt$addTextChangedListener$textWatcher$1 = new CommonExtKt$addTextChangedListener$textWatcher$1(afterTextChanged, beforeTextChanged, onTextChanged);
        textView.addTextChangedListener(commonExtKt$addTextChangedListener$textWatcher$1);
        return commonExtKt$addTextChangedListener$textWatcher$1;
    }

    public static final TextWatcher doBeforeTextChanged(TextView textView, final Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        TextWatcher textWatcher = new TextWatcher() { // from class: com.movieboxpro.android.utils.CommonExtKt$doBeforeTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Function4.this.invoke(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }

    public static final TextWatcher doOnTextChanged(TextView textView, final Function4<? super CharSequence, ? super Integer, ? super Integer, ? super Integer, Unit> action) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        TextWatcher textWatcher = new TextWatcher() { // from class: com.movieboxpro.android.utils.CommonExtKt$doOnTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Function4.this.invoke(charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }

    public static final TextWatcher doAfterTextChanged(TextView textView, final Function1<? super Editable, Unit> action) {
        Intrinsics.checkNotNullParameter(textView, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        TextWatcher textWatcher = new TextWatcher() { // from class: com.movieboxpro.android.utils.CommonExtKt$doAfterTextChanged$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                Function1.this.invoke(editable);
            }
        };
        textView.addTextChangedListener(textWatcher);
        return textWatcher;
    }
}
