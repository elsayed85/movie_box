package com.movieboxpro.android.view.videocontroller.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.dueeeke.model.MediaQualityInfo;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.CommMultiBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.db.entity.TestNetRecode;
import com.movieboxpro.android.event.CancelShowTestSpeedEvent;
import com.movieboxpro.android.event.OnSwitchServerEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.livedata.NetSpeedLiveData;
import com.movieboxpro.android.model.common.Feedback;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.settings.TestSpeedActivity;
import com.movieboxpro.android.view.activity.user.VipActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.FeedbackDialog;
import com.movieboxpro.android.view.dialog.SwitchOriginRateDialog;
import com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionContract;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
/* compiled from: VideoDefinitionFragment.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 72\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u000278B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0002H\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0017H\u0002J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\rH\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0014J\b\u0010\u001f\u001a\u00020\u0019H\u0014J\b\u0010 \u001a\u00020\u0019H\u0002J\b\u0010!\u001a\u00020\u0019H\u0014J\u0006\u0010\"\u001a\u00020\u0012J\u0010\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u0010H\u0002J\b\u0010%\u001a\u00020\u0019H\u0014J\"\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u00102\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u0014\u0010+\u001a\u00020\u00192\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0,J\u000e\u0010-\u001a\u00020\u00192\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010.\u001a\u00020\u00192\b\u0010/\u001a\u0004\u0018\u00010\rJ\u001a\u00100\u001a\u00020\u00192\b\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u00020\u0012H\u0002J\u0010\u00104\u001a\u00020\u00192\u0006\u00105\u001a\u000206H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionPresenter;", "Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionContract$View;", "()V", "actionDialog", "Lcom/adorkable/iosdialog/ActionSheetDialog;", "adapter", "Lcom/movieboxpro/android/base/CommMultiBaseAdapter;", "Lcom/dueeeke/model/MediaQualityInfo;", "callback", "Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionFragment$SwitchDefinitionCallback;", "currQualityId", "", "orgItem", "selectedPosition", "", "showOrg", "", "bindLayout", "bindPresenter", "checkHaveOrg", "list", "", "feedbackError", "", "state", "ftid", "message", "getFeedbackType", "initData", "initListener", "initServerInfo", "initView", "isFragmentVisible", "itemClick", "position", "loadData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "resetDefinition", "", "setCallback", "setSelectQuality", "id", "setViewVisibility", "view", "Landroid/view/View;", "visible", "showFeedbackDialog", "feedback", "Lcom/movieboxpro/android/model/common/Feedback;", "Companion", "SwitchDefinitionCallback", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class VideoDefinitionFragment extends BaseMvpFragment<VideoDefinitionPresenter> implements VideoDefinitionContract.View {
    public static final Companion Companion = new Companion(null);
    private ActionSheetDialog actionDialog;
    private CommMultiBaseAdapter<MediaQualityInfo> adapter;
    private SwitchDefinitionCallback callback;
    private MediaQualityInfo orgItem;
    private boolean showOrg;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int selectedPosition = -1;
    private String currQualityId = "";

    /* compiled from: VideoDefinitionFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionFragment$SwitchDefinitionCallback;", "", "onSwitchDefinition", "", "qualityInfo", "Lcom/dueeeke/model/MediaQualityInfo;", "position", "", "hideDialog", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface SwitchDefinitionCallback {
        void onSwitchDefinition(MediaQualityInfo mediaQualityInfo, int i, boolean z);
    }

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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.video_definition_layout;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setCallback(SwitchDefinitionCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public VideoDefinitionPresenter bindPresenter() {
        return new VideoDefinitionPresenter(this);
    }

    private final void itemClick(final int i) {
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this.adapter;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        final List<MediaQualityInfo> data = commMultiBaseAdapter.getData();
        MediaQualityInfo mediaQualityInfo = data.get(i);
        if (mediaQualityInfo.getViewType() == 1 || mediaQualityInfo.getViewType() == 3) {
            return;
        }
        if (mediaQualityInfo.getViewType() == 4) {
            CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this.adapter;
            if (commMultiBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter3 = null;
            }
            Iterator<MediaQualityInfo> it = commMultiBaseAdapter3.getData().iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                }
                MediaQualityInfo next = it.next();
                if ((Intrinsics.areEqual(next.getReal_quality(), "4K") || TextUtils.isEmpty(next.getPath())) ? false : true) {
                    break;
                }
                i2++;
            }
            if (i2 != -1) {
                SettingManager.INSTANCE.saveAutoSelectPlayQuality(true);
                int i3 = this.selectedPosition;
                if (i3 >= 0 && i3 < data.size()) {
                    data.get(this.selectedPosition).setSelect(false);
                }
                mediaQualityInfo.setSelect(true);
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this.adapter;
                if (commMultiBaseAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter4 = null;
                }
                commMultiBaseAdapter4.notifyItemChanged(this.selectedPosition);
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter5 = this.adapter;
                if (commMultiBaseAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter5 = null;
                }
                commMultiBaseAdapter5.notifyItemChanged(i);
                this.selectedPosition = i2;
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter6 = this.adapter;
                if (commMultiBaseAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter6 = null;
                }
                String mmid = commMultiBaseAdapter6.getItem(i2).getMmid();
                if (mmid == null) {
                    CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter7 = this.adapter;
                    if (commMultiBaseAdapter7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter7 = null;
                    }
                    mmid = commMultiBaseAdapter7.getItem(i2).getTmid();
                }
                this.currQualityId = mmid;
                SwitchDefinitionCallback switchDefinitionCallback = this.callback;
                if (switchDefinitionCallback == null) {
                    return;
                }
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter8 = this.adapter;
                if (commMultiBaseAdapter8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commMultiBaseAdapter2 = commMultiBaseAdapter8;
                }
                MediaQualityInfo item = commMultiBaseAdapter2.getItem(i2);
                if (item == null) {
                    item = new MediaQualityInfo();
                }
                switchDefinitionCallback.onSwitchDefinition(item, i2, true);
            }
        } else if (Intrinsics.areEqual(data.get(i).getIsVip(), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) && App.getUserData().isvip != 1) {
            Context context = getContext();
            if (context == null) {
                return;
            }
            VipActivity.Companion.start(context);
        } else {
            final MediaQualityInfo mediaQualityInfo2 = data.get(i);
            if (mediaQualityInfo2.getOriginal() == 1) {
                new SwitchOriginRateDialog.Builder(getContext()).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$0X90pcoNwSAmrza2AffjaNnJ-nY
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        VideoDefinitionFragment.m1409itemClick$lambda2(VideoDefinitionFragment.this, data, mediaQualityInfo2, i);
                    }
                }).create().show();
            } else {
                int i4 = this.selectedPosition;
                if (i4 >= 0 && i4 < data.size()) {
                    data.get(this.selectedPosition).setSelect(false);
                }
                mediaQualityInfo2.setSelect(true);
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter9 = this.adapter;
                if (commMultiBaseAdapter9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter9 = null;
                }
                commMultiBaseAdapter9.notifyItemChanged(this.selectedPosition);
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter10 = this.adapter;
                if (commMultiBaseAdapter10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter10 = null;
                }
                commMultiBaseAdapter10.notifyItemChanged(i);
                this.selectedPosition = i;
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter11 = this.adapter;
                if (commMultiBaseAdapter11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter11 = null;
                }
                String mmid2 = commMultiBaseAdapter11.getItem(i).getMmid();
                if (mmid2 == null) {
                    CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter12 = this.adapter;
                    if (commMultiBaseAdapter12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter12 = null;
                    }
                    mmid2 = commMultiBaseAdapter12.getItem(i).getTmid();
                }
                this.currQualityId = mmid2;
                SwitchDefinitionCallback switchDefinitionCallback2 = this.callback;
                if (switchDefinitionCallback2 != null) {
                    CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter13 = this.adapter;
                    if (commMultiBaseAdapter13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        commMultiBaseAdapter2 = commMultiBaseAdapter13;
                    }
                    MediaQualityInfo item2 = commMultiBaseAdapter2.getItem(i);
                    if (item2 == null) {
                        item2 = new MediaQualityInfo();
                    }
                    switchDefinitionCallback2.onSwitchDefinition(item2, i, true);
                }
            }
            SettingManager.INSTANCE.saveAutoSelectPlayQuality(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: itemClick$lambda-2  reason: not valid java name */
    public static final void m1409itemClick$lambda2(VideoDefinitionFragment this$0, List data, MediaQualityInfo currModel, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(currModel, "$currModel");
        Context context = this$0.getContext();
        if (context != null) {
            CommonExtKt.onMobEvent(context, "PlaySourceFile");
        }
        EventUtils.event("使用源视频文件播放");
        int i2 = this$0.selectedPosition;
        if (i2 >= 0 && i2 < data.size()) {
            ((MediaQualityInfo) data.get(this$0.selectedPosition)).setSelect(false);
        }
        currModel.setSelect(true);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this$0.adapter;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyItemChanged(this$0.selectedPosition);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this$0.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        commMultiBaseAdapter3.notifyItemChanged(i);
        this$0.selectedPosition = i;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this$0.adapter;
        if (commMultiBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter4 = null;
        }
        String mmid = commMultiBaseAdapter4.getItem(i).getMmid();
        if (mmid == null) {
            CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter5 = this$0.adapter;
            if (commMultiBaseAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter5 = null;
            }
            mmid = commMultiBaseAdapter5.getItem(i).getTmid();
        }
        this$0.currQualityId = mmid;
        SwitchDefinitionCallback switchDefinitionCallback = this$0.callback;
        if (switchDefinitionCallback == null) {
            return;
        }
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter6 = this$0.adapter;
        if (commMultiBaseAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter6;
        }
        MediaQualityInfo item = commMultiBaseAdapter2.getItem(i);
        if (item == null) {
            item = new MediaQualityInfo();
        }
        switchDefinitionCallback.onSwitchDefinition(item, i, true);
    }

    public final void setSelectQuality(String str) {
        if (this.adapter != null) {
            Bundle arguments = getArguments();
            CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = null;
            Integer valueOf = arguments == null ? null : Integer.valueOf(arguments.getInt("boxType"));
            CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = this.adapter;
            if (commMultiBaseAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter2 = null;
            }
            Iterator<MediaQualityInfo> it = commMultiBaseAdapter2.getData().iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                MediaQualityInfo next = it.next();
                if (Intrinsics.areEqual(str, (valueOf != null && valueOf.intValue() == 1) ? next.getMmid() : next.getTmid())) {
                    break;
                }
                i++;
            }
            if (i != -1) {
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this.adapter;
                if (commMultiBaseAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter3 = null;
                }
                List<MediaQualityInfo> data = commMultiBaseAdapter3.getData();
                if (SettingManager.INSTANCE.isAutoSelectPlayQuality()) {
                    MediaQualityInfo mediaQualityInfo = data.get(i);
                    CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this.adapter;
                    if (commMultiBaseAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter4 = null;
                    }
                    commMultiBaseAdapter4.getItem(this.selectedPosition).setReal_quality(mediaQualityInfo.getReal_quality());
                } else {
                    int i2 = this.selectedPosition;
                    if (i2 >= 0 && i2 < data.size()) {
                        data.get(this.selectedPosition).setSelect(false);
                    }
                    data.get(i).setSelect(true);
                    CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter5 = this.adapter;
                    if (commMultiBaseAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        commMultiBaseAdapter5 = null;
                    }
                    commMultiBaseAdapter5.notifyItemChanged(i);
                }
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter6 = this.adapter;
                if (commMultiBaseAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter6 = null;
                }
                commMultiBaseAdapter6.notifyItemChanged(this.selectedPosition);
                this.selectedPosition = i;
                SwitchDefinitionCallback switchDefinitionCallback = this.callback;
                if (switchDefinitionCallback == null) {
                    return;
                }
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter7 = this.adapter;
                if (commMultiBaseAdapter7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    commMultiBaseAdapter = commMultiBaseAdapter7;
                }
                MediaQualityInfo item = commMultiBaseAdapter.getItem(i);
                if (item == null) {
                    item = new MediaQualityInfo();
                }
                switchDefinitionCallback.onSwitchDefinition(item, i, false);
            }
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this.adapter;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$n-Mjggeaf_AiwFkmwzXOF2ZKqFg
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VideoDefinitionFragment.m1405initListener$lambda5(VideoDefinitionFragment.this, baseQuickAdapter, view, i);
            }
        });
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        commMultiBaseAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$HUytTFsdOzn0USH1xDZhAFkfBZU
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VideoDefinitionFragment.m1406initListener$lambda6(VideoDefinitionFragment.this, baseQuickAdapter, view, i);
            }
        });
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this.adapter;
        if (commMultiBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter4;
        }
        commMultiBaseAdapter2.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$V7MGs2-NuD0l-Wqe70q-egfyzNA
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                boolean m1407initListener$lambda7;
                m1407initListener$lambda7 = VideoDefinitionFragment.m1407initListener$lambda7(VideoDefinitionFragment.this, baseQuickAdapter, view, i);
                return m1407initListener$lambda7;
            }
        });
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$h1VJtMsrVAzOaHeyAXk7YzWhUEI
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                VideoDefinitionFragment.m1408initListener$lambda8(VideoDefinitionFragment.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m1405initListener$lambda5(VideoDefinitionFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this$0.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        MediaQualityInfo item = commMultiBaseAdapter.getItem(i);
        item.setOriginShow(true);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = this$0.adapter;
        if (commMultiBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter2 = null;
        }
        commMultiBaseAdapter2.notifyItemChanged(i);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this$0.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        int i2 = 0;
        for (Object obj : commMultiBaseAdapter3.getData()) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            MediaQualityInfo mediaQualityInfo = (MediaQualityInfo) obj;
            if (mediaQualityInfo.getViewType() == 2 && mediaQualityInfo.fid == item.fid && mediaQualityInfo.getOriginal() == 1) {
                mediaQualityInfo.setOriginShow(true);
                CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this$0.adapter;
                if (commMultiBaseAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    commMultiBaseAdapter4 = null;
                }
                commMultiBaseAdapter4.notifyItemChanged(i2);
            }
            i2 = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m1406initListener$lambda6(VideoDefinitionFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        if (this$0.selectedPosition == i) {
            return;
        }
        this$0.itemClick(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-7  reason: not valid java name */
    public static final boolean m1407initListener$lambda7(VideoDefinitionFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        this$0.getFeedbackType();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-8  reason: not valid java name */
    public static final void m1408initListener$lambda8(VideoDefinitionFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showOrg = true;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this$0.adapter;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.notifyDataSetChanged();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) this$0._$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.hide(swipeRefreshLayout);
        this$0.selectedPosition++;
        ((SwipeRefreshLayout) this$0._$_findCachedViewById(R.id.swipeRefreshLayout)).setEnabled(false);
        ImageView ivMoreDefinition = (ImageView) this$0._$_findCachedViewById(R.id.ivMoreDefinition);
        Intrinsics.checkNotNullExpressionValue(ivMoreDefinition, "ivMoreDefinition");
        CommonExtKt.gone(ivMoreDefinition);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showFeedbackDialog(final Feedback feedback) {
        FragmentActivity activity = getActivity();
        FeedbackDialog feedbackDialog = activity != null ? new FeedbackDialog(activity, null, 2, null) : null;
        if (feedbackDialog != null) {
            feedbackDialog.setMessageListener(new FeedbackDialog.OnMessageListener() { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$showFeedbackDialog$1
                @Override // com.movieboxpro.android.view.dialog.FeedbackDialog.OnMessageListener
                public void onMessage(String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    VideoDefinitionFragment.this.feedbackError(feedback.state, feedback.ftid, Intrinsics.stringPlus(message, SystemUtils.getMsg()));
                }
            });
        }
        if (feedbackDialog == null) {
            return;
        }
        feedbackDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void feedbackError(int i, int i2, String str) {
        Bundle arguments = getArguments();
        String string = arguments == null ? null : arguments.getString("id");
        Bundle arguments2 = getArguments();
        Integer valueOf = arguments2 == null ? null : Integer.valueOf(arguments2.getInt("boxType"));
        Bundle arguments3 = getArguments();
        Integer valueOf2 = arguments3 == null ? null : Integer.valueOf(arguments3.getInt("season"));
        Bundle arguments4 = getArguments();
        Integer valueOf3 = arguments4 != null ? Integer.valueOf(arguments4.getInt("episode")) : null;
        ((ObservableSubscribeProxy) Http.getService().Movie_feedback(API.BASE_URL, API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", valueOf == null ? 0 : valueOf.intValue(), string, i, "", i2, str, valueOf2 == null ? 0 : valueOf2.intValue(), valueOf3 == null ? 0 : valueOf3.intValue()).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$feedbackError$1
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                super.onSubscribe(d);
                VideoDefinitionFragment.this.showLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String t) {
                ActionSheetDialog actionSheetDialog;
                Intrinsics.checkNotNullParameter(t, "t");
                super.onNext((VideoDefinitionFragment$feedbackError$1) t);
                ToastUtils.showShort("feedback successfully", new Object[0]);
                actionSheetDialog = VideoDefinitionFragment.this.actionDialog;
                if (actionSheetDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("actionDialog");
                    actionSheetDialog = null;
                }
                actionSheetDialog.dismiss();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoDefinitionFragment.this.hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                ToastUtils.showShort(Intrinsics.stringPlus("feedback error", apiException == null ? null : apiException.getMessage()), new Object[0]);
            }
        });
    }

    private final void getFeedbackType() {
        ((ObservableSubscribeProxy) Http.getService().Movie_feedback_type(API.BASE_URL, API.Common.MOVIE_FEEDBACL_TYPE, 2).compose(RxUtils.rxTranslate2List(Feedback.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new VideoDefinitionFragment$getFeedbackType$1(this));
    }

    public final void resetDefinition(List<MediaQualityInfo> list) {
        int i;
        boolean areEqual;
        Intrinsics.checkNotNullParameter(list, "list");
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this.adapter;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        commMultiBaseAdapter.getData().clear();
        List<MediaQualityInfo> list2 = list;
        for (MediaQualityInfo mediaQualityInfo : list2) {
            String path = mediaQualityInfo.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "it.path");
            if (!StringsKt.startsWith$default(path, "http", false, 2, (Object) null)) {
                mediaQualityInfo.setFileName("Downloaded File");
            }
        }
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : list2) {
                String fileName = ((MediaQualityInfo) obj).getFileName();
                Object obj2 = linkedHashMap.get(fileName);
                if (obj2 == null) {
                    obj2 = (List) new ArrayList();
                    linkedHashMap.put(fileName, obj2);
                }
                ((List) obj2).add(obj);
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                MediaQualityInfo mediaQualityInfo2 = new MediaQualityInfo();
                mediaQualityInfo2.setFileName((String) entry.getKey());
                mediaQualityInfo2.setViewType(1);
                MediaQualityInfo mediaQualityInfo3 = (MediaQualityInfo) CollectionsKt.firstOrNull((List<? extends Object>) entry.getValue());
                mediaQualityInfo2.fid = mediaQualityInfo3 == null ? 0 : mediaQualityInfo3.fid;
                MediaQualityInfo mediaQualityInfo4 = (MediaQualityInfo) CollectionsKt.firstOrNull((List<? extends Object>) entry.getValue());
                mediaQualityInfo2.setTime(mediaQualityInfo4 == null ? null : mediaQualityInfo4.getTime());
                if (checkHaveOrg((List) entry.getValue())) {
                    mediaQualityInfo2.setHasOrg(true);
                }
                arrayList.add(mediaQualityInfo2);
                for (MediaQualityInfo mediaQualityInfo5 : (Iterable) entry.getValue()) {
                    mediaQualityInfo5.setViewType(2);
                }
                MediaQualityInfo mediaQualityInfo6 = (MediaQualityInfo) CollectionsKt.lastOrNull((List<? extends Object>) entry.getValue());
                if (mediaQualityInfo6 != null) {
                    mediaQualityInfo6.setLastItem(true);
                }
                arrayList.addAll((Collection) entry.getValue());
                MediaQualityInfo mediaQualityInfo7 = new MediaQualityInfo();
                mediaQualityInfo7.setViewType(3);
                arrayList.add(mediaQualityInfo7);
            }
            arrayList.remove(arrayList.size() - 1);
        }
        ArrayList arrayList2 = arrayList;
        Iterator it = arrayList2.iterator();
        int i2 = 0;
        while (true) {
            i = -1;
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            MediaQualityInfo mediaQualityInfo8 = (MediaQualityInfo) it.next();
            if (mediaQualityInfo8.getMmid() != null) {
                areEqual = Intrinsics.areEqual(this.currQualityId, mediaQualityInfo8.getMmid());
            } else {
                areEqual = Intrinsics.areEqual(this.currQualityId, mediaQualityInfo8.getTmid());
            }
            if (areEqual) {
                break;
            }
            i2++;
        }
        if (i2 != -1) {
            list.get(i2).setSelect(true);
        }
        MediaQualityInfo mediaQualityInfo9 = new MediaQualityInfo();
        mediaQualityInfo9.setViewType(4);
        Iterator it2 = arrayList2.iterator();
        int i3 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i3 = -1;
                break;
            }
            MediaQualityInfo mediaQualityInfo10 = (MediaQualityInfo) it2.next();
            if (!Intrinsics.areEqual(mediaQualityInfo10.getFileName(), "Downloaded File") && mediaQualityInfo10.getViewType() == 1) {
                break;
            }
            i3++;
        }
        if (i3 != -1) {
            arrayList.add(i3 + 1, mediaQualityInfo9);
        }
        Iterator it3 = arrayList2.iterator();
        int i4 = 0;
        while (true) {
            if (!it3.hasNext()) {
                break;
            } else if (((MediaQualityInfo) it3.next()).isSelect()) {
                i = i4;
                break;
            } else {
                i4++;
            }
        }
        MediaQualityInfo mediaQualityInfo11 = (MediaQualityInfo) CollectionsKt.getOrNull(arrayList2, i);
        mediaQualityInfo9.setReal_quality(mediaQualityInfo11 == null ? null : mediaQualityInfo11.getReal_quality());
        if (SettingManager.INSTANCE.isAutoSelectPlayQuality()) {
            mediaQualityInfo9.setSelect(true);
            MediaQualityInfo mediaQualityInfo12 = (MediaQualityInfo) CollectionsKt.getOrNull(arrayList2, i);
            if (mediaQualityInfo12 != null) {
                mediaQualityInfo12.setSelect(false);
            }
        }
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        commMultiBaseAdapter3.getData().addAll(arrayList);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this.adapter;
        if (commMultiBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter2 = commMultiBaseAdapter4;
        }
        commMultiBaseAdapter2.notifyDataSetChanged();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        int i;
        String real_quality;
        boolean areEqual;
        Bundle arguments = getArguments();
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = null;
        ArrayList parcelableArrayList = arguments == null ? null : arguments.getParcelableArrayList("list");
        if (parcelableArrayList == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<com.dueeeke.model.MediaQualityInfo>{ kotlin.collections.TypeAliasesKt.ArrayList<com.dueeeke.model.MediaQualityInfo> }");
        }
        ArrayList<MediaQualityInfo> arrayList = parcelableArrayList;
        for (MediaQualityInfo mediaQualityInfo : arrayList) {
            mediaQualityInfo.setSelect(false);
            String path = mediaQualityInfo.getPath();
            Intrinsics.checkNotNullExpressionValue(path, "it.path");
            if (!StringsKt.startsWith$default(path, "http", false, 2, (Object) null)) {
                mediaQualityInfo.setFileName("Downloaded File");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (!parcelableArrayList.isEmpty()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Object obj : arrayList) {
                String fileName = ((MediaQualityInfo) obj).getFileName();
                Object obj2 = linkedHashMap.get(fileName);
                if (obj2 == null) {
                    obj2 = (List) new ArrayList();
                    linkedHashMap.put(fileName, obj2);
                }
                ((List) obj2).add(obj);
            }
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                MediaQualityInfo mediaQualityInfo2 = new MediaQualityInfo();
                mediaQualityInfo2.setFileName((String) entry.getKey());
                mediaQualityInfo2.setViewType(1);
                MediaQualityInfo mediaQualityInfo3 = (MediaQualityInfo) CollectionsKt.firstOrNull((List<? extends Object>) entry.getValue());
                mediaQualityInfo2.fid = mediaQualityInfo3 == null ? 0 : mediaQualityInfo3.fid;
                MediaQualityInfo mediaQualityInfo4 = (MediaQualityInfo) CollectionsKt.firstOrNull((List<? extends Object>) entry.getValue());
                mediaQualityInfo2.setTime(mediaQualityInfo4 == null ? null : mediaQualityInfo4.getTime());
                if (checkHaveOrg((List) entry.getValue())) {
                    mediaQualityInfo2.setHasOrg(true);
                }
                arrayList2.add(mediaQualityInfo2);
                for (MediaQualityInfo mediaQualityInfo5 : (Iterable) entry.getValue()) {
                    mediaQualityInfo5.setViewType(2);
                }
                MediaQualityInfo mediaQualityInfo6 = (MediaQualityInfo) CollectionsKt.lastOrNull((List<? extends Object>) entry.getValue());
                if (mediaQualityInfo6 != null) {
                    mediaQualityInfo6.setLastItem(true);
                }
                arrayList2.addAll((Collection) entry.getValue());
                MediaQualityInfo mediaQualityInfo7 = new MediaQualityInfo();
                mediaQualityInfo7.setViewType(3);
                arrayList2.add(mediaQualityInfo7);
            }
            arrayList2.remove(arrayList2.size() - 1);
        }
        Bundle arguments2 = getArguments();
        String string = arguments2 == null ? null : arguments2.getString("mmid");
        this.currQualityId = string;
        ArrayList arrayList3 = arrayList2;
        Iterator it = arrayList3.iterator();
        int i2 = 0;
        while (true) {
            i = -1;
            if (!it.hasNext()) {
                i2 = -1;
                break;
            }
            MediaQualityInfo mediaQualityInfo8 = (MediaQualityInfo) it.next();
            if (mediaQualityInfo8.getMmid() != null) {
                areEqual = Intrinsics.areEqual(string, mediaQualityInfo8.getMmid());
            } else {
                areEqual = Intrinsics.areEqual(string, mediaQualityInfo8.getTmid());
            }
            if (areEqual) {
                break;
            }
            i2++;
        }
        if (i2 != -1) {
            this.selectedPosition = i2;
            Object obj3 = arrayList2.get(i2);
            Intrinsics.checkNotNullExpressionValue(obj3, "listQuality[index]");
            MediaQualityInfo mediaQualityInfo9 = (MediaQualityInfo) obj3;
            mediaQualityInfo9.setSelect(true);
            if (mediaQualityInfo9.getOriginal() == 1) {
                mediaQualityInfo9.setOriginShow(true);
            }
        }
        ((SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout)).setEnabled(false);
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(new Pair(1, Integer.valueOf((int) R.layout.adapter_quality_title)));
        arrayList4.add(new Pair(2, Integer.valueOf((int) R.layout.adapter_quality_item)));
        arrayList4.add(new Pair(3, Integer.valueOf((int) R.layout.adapter_quality_line)));
        arrayList4.add(new Pair(4, Integer.valueOf((int) R.layout.adapter_quality_auto_select)));
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = new CommMultiBaseAdapter<>(new VideoDefinitionFragment$initData$4(this), VideoDefinitionFragment$initData$5.INSTANCE, new ArrayList(arrayList4));
        this.adapter = commMultiBaseAdapter2;
        if (commMultiBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter2 = null;
        }
        commMultiBaseAdapter2.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        ((RecyclerView) _$_findCachedViewById(R.id.definitionRecyclerView)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.definitionRecyclerView);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this.adapter;
        if (commMultiBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter3 = null;
        }
        recyclerView.setAdapter(commMultiBaseAdapter3);
        MediaQualityInfo mediaQualityInfo10 = new MediaQualityInfo();
        mediaQualityInfo10.setViewType(4);
        Iterator it2 = arrayList3.iterator();
        int i3 = 0;
        while (true) {
            if (!it2.hasNext()) {
                i3 = -1;
                break;
            }
            MediaQualityInfo mediaQualityInfo11 = (MediaQualityInfo) it2.next();
            if (!Intrinsics.areEqual(mediaQualityInfo11.getFileName(), "Downloaded File") && mediaQualityInfo11.getViewType() == 1) {
                break;
            }
            i3++;
        }
        if (i3 != -1) {
            arrayList2.add(i3 + 1, mediaQualityInfo10);
        }
        Iterator it3 = arrayList3.iterator();
        int i4 = 0;
        while (true) {
            if (!it3.hasNext()) {
                break;
            } else if (((MediaQualityInfo) it3.next()).isSelect()) {
                i = i4;
                break;
            } else {
                i4++;
            }
        }
        MediaQualityInfo mediaQualityInfo12 = (MediaQualityInfo) CollectionsKt.getOrNull(arrayList3, i);
        mediaQualityInfo10.setReal_quality(mediaQualityInfo12 == null ? null : mediaQualityInfo12.getReal_quality());
        if (SettingManager.INSTANCE.isAutoSelectPlayQuality()) {
            MediaQualityInfo mediaQualityInfo13 = (MediaQualityInfo) CollectionsKt.getOrNull(arrayList3, i);
            if ((mediaQualityInfo13 == null || (real_quality = mediaQualityInfo13.getReal_quality()) == null || !StringsKt.startsWith$default(real_quality, "http", false, 2, (Object) null)) ? false : true) {
                mediaQualityInfo10.setSelect(true);
                MediaQualityInfo mediaQualityInfo14 = (MediaQualityInfo) CollectionsKt.getOrNull(arrayList3, i);
                if (mediaQualityInfo14 != null) {
                    mediaQualityInfo14.setSelect(false);
                }
            }
        }
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this.adapter;
        if (commMultiBaseAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter4 = null;
        }
        commMultiBaseAdapter4.setList(arrayList2);
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter5 = this.adapter;
        if (commMultiBaseAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commMultiBaseAdapter = commMultiBaseAdapter5;
        }
        commMultiBaseAdapter.addChildClickViewIds(R.id.ivMore);
        NetSpeedLiveData.Companion.get().observe(this, new Observer() { // from class: com.movieboxpro.android.view.videocontroller.fragment.-$$Lambda$VideoDefinitionFragment$8f2t3Q6V9m2RiJyYr5Lqafw7LI0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj4) {
                VideoDefinitionFragment.m1404initData$lambda25(VideoDefinitionFragment.this, (Long) obj4);
            }
        });
        initServerInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-25  reason: not valid java name */
    public static final void m1404initData$lambda25(VideoDefinitionFragment this$0, Long it) {
        String format;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.longValue() < 1024) {
            format = it + "KB/s";
        } else {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Locale locale = Locale.getDefault();
            double longValue = it.longValue();
            Double.isNaN(longValue);
            format = String.format(locale, "%.1fMB/s", Arrays.copyOf(new Object[]{Double.valueOf(longValue / 1024.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        }
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter = this$0.adapter;
        CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter2 = null;
        if (commMultiBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commMultiBaseAdapter = null;
        }
        Iterator<MediaQualityInfo> it2 = commMultiBaseAdapter.getData().iterator();
        int i = 0;
        while (true) {
            if (!it2.hasNext()) {
                i = -1;
                break;
            }
            if (it2.next().getViewType() == 4) {
                break;
            }
            i++;
        }
        if (i != -1) {
            CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter3 = this$0.adapter;
            if (commMultiBaseAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                commMultiBaseAdapter3 = null;
            }
            commMultiBaseAdapter3.getItem(i).setSpeed(format);
            CommMultiBaseAdapter<MediaQualityInfo> commMultiBaseAdapter4 = this$0.adapter;
            if (commMultiBaseAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                commMultiBaseAdapter2 = commMultiBaseAdapter4;
            }
            commMultiBaseAdapter2.notifyItemChanged(i);
        }
    }

    private final void initServerInfo() {
        TestNetRecode findAll = App.getDB().testnetRecodeDao().findAll(1);
        if (findAll != null && !Intrinsics.areEqual(findAll.country, "AUTO")) {
            SpanUtils with = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvServer));
            Intrinsics.checkNotNullExpressionValue(with, "with(tvServer)");
            SpanUtils addText = CommonExtKt.addText(with, "Current Server:", 13, R.color.white_54alpha);
            String str = findAll.country;
            Intrinsics.checkNotNullExpressionValue(str, "item.country");
            SpanUtils addText2 = CommonExtKt.addText(addText, str, 13, R.color.color_main_blue);
            final int colorInt = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
            addText2.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$initServerInfo$1
                @Override // android.text.style.ClickableSpan
                public void onClick(View p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    TestSpeedActivity.Companion.startForResult(VideoDefinitionFragment.this, 100);
                }
            }).create();
            return;
        }
        SpanUtils with2 = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvServer));
        Intrinsics.checkNotNullExpressionValue(with2, "with(tvServer)");
        SpanUtils addText3 = CommonExtKt.addText(with2, "Speed Test", 14, R.color.color_main_blue);
        final int colorInt2 = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
        addText3.setClickSpan(new CustomClickableSpan(colorInt2) { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$initServerInfo$2
            @Override // android.text.style.ClickableSpan
            public void onClick(View p0) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                TestSpeedActivity.Companion.startForResult(VideoDefinitionFragment.this, 100);
            }
        }).create();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 100) {
            EventBus.getDefault().post(new CancelShowTestSpeedEvent());
            TestNetRecode findAll = App.getDB().testnetRecodeDao().findAll(1);
            if (findAll != null && !Intrinsics.areEqual(findAll.country, "AUTO")) {
                SpanUtils with = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvServer));
                Intrinsics.checkNotNullExpressionValue(with, "with(tvServer)");
                SpanUtils addText = CommonExtKt.addText(with, "Current Server:", 13, R.color.white_54alpha);
                String str = findAll.country;
                Intrinsics.checkNotNullExpressionValue(str, "item.country");
                SpanUtils addText2 = CommonExtKt.addText(addText, str, 13, R.color.color_main_blue);
                final int colorInt = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
                addText2.setClickSpan(new CustomClickableSpan(colorInt) { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$onActivityResult$1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View p0) {
                        Intrinsics.checkNotNullParameter(p0, "p0");
                        TestSpeedActivity.Companion.startForResult(VideoDefinitionFragment.this, 100);
                    }
                }).create();
                EventBus.getDefault().post(new OnSwitchServerEvent());
                return;
            }
            SpanUtils with2 = SpanUtils.with((TextView) _$_findCachedViewById(R.id.tvServer));
            Intrinsics.checkNotNullExpressionValue(with2, "with(tvServer)");
            SpanUtils addText3 = CommonExtKt.addText(with2, "Speed Test", 14, R.color.color_main_blue);
            final int colorInt2 = CommonExtKt.colorInt(this, (int) R.color.color_main_blue);
            addText3.setClickSpan(new CustomClickableSpan(colorInt2) { // from class: com.movieboxpro.android.view.videocontroller.fragment.VideoDefinitionFragment$onActivityResult$2
                @Override // android.text.style.ClickableSpan
                public void onClick(View p0) {
                    Intrinsics.checkNotNullParameter(p0, "p0");
                    TestSpeedActivity.Companion.startForResult(VideoDefinitionFragment.this, 100);
                }
            }).create();
            EventBus.getDefault().post(new OnSwitchServerEvent());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setViewVisibility(View view, boolean z) {
        ViewGroup.LayoutParams layoutParams = view == null ? null : view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
        if (z) {
            layoutParams2.height = -2;
            layoutParams2.width = -1;
            CommonExtKt.visible(view);
        } else {
            CommonExtKt.gone(view);
            layoutParams2.height = 0;
            layoutParams2.width = 0;
        }
        view.setLayoutParams(layoutParams2);
    }

    private final boolean checkHaveOrg(List<? extends MediaQualityInfo> list) {
        for (MediaQualityInfo mediaQualityInfo : list) {
            if (mediaQualityInfo.getOriginal() == 1) {
                return true;
            }
        }
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) _$_findCachedViewById(R.id.swipeRefreshLayout);
        Intrinsics.checkNotNullExpressionValue(swipeRefreshLayout, "swipeRefreshLayout");
        CommonExtKt.initColor(swipeRefreshLayout);
    }

    public final boolean isFragmentVisible() {
        return isVisible();
    }

    /* compiled from: VideoDefinitionFragment.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/videocontroller/fragment/VideoDefinitionFragment;", "list", "", "Lcom/dueeeke/model/MediaQualityInfo;", "boxType", "", "id", "", "season", "episode", "mmid", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoDefinitionFragment newInstance(List<? extends MediaQualityInfo> list, int i, String id, int i2, int i3, String str) {
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(id, "id");
            VideoDefinitionFragment videoDefinitionFragment = new VideoDefinitionFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", new ArrayList<>(list));
            bundle.putInt("boxType", i);
            bundle.putString("id", id);
            bundle.putInt("season", i2);
            bundle.putInt("episode", i3);
            bundle.putString("mmid", str);
            videoDefinitionFragment.setArguments(bundle);
            return videoDefinitionFragment;
        }
    }
}
