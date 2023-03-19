package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.CipherKeys;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.ScreenMember;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenManageDialog.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 -2\u00020\u0001:\u0002-.B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\b\u0010\u0015\u001a\u00020\u0013H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0002J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u0013H\u0002J\u0012\u0010\u001b\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u00020\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u001a\u0010(\u001a\u00020\u00132\u0006\u0010)\u001a\u00020#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\u000e\u0010*\u001a\u00020\u00132\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010+\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010,\u001a\u00020\u0013H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ScreenManageDialog;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "()V", "adapter", "Lcom/movieboxpro/android/base/CommBaseAdapter;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "exitListener", "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "kProgressHUD", "Lcom/kaopiz/kprogresshud/KProgressHUD;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ScreenManageDialog$OnStopDeviceListener;", "memberAdapter", "Lcom/movieboxpro/android/model/ScreenMember;", "payUrl", "", "buildData", "env", "deleteDevice", "", "item", "getPayUrl", "hideLoadingView", "initData", "initDialog", "initListener", "initView", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "setExitListener", "setListener", "showLoadingView", "Companion", "OnStopDeviceListener", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ScreenManageDialog extends AppCompatDialogFragment {
    public static final Companion Companion = new Companion(null);
    private CommBaseAdapter<DeviceModelResponse.DeviceModel> adapter;
    private DialogAction.ActionListener exitListener;
    private KProgressHUD kProgressHUD;
    private OnStopDeviceListener listener;
    private CommBaseAdapter<ScreenMember> memberAdapter;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String payUrl = "https://www.xuedingcongming.com/index/pay";

    /* compiled from: ScreenManageDialog.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ScreenManageDialog$OnStopDeviceListener;", "", "onStopDevice", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public interface OnStopDeviceListener {
        void onStopDevice();
    }

    @JvmStatic
    public static final ScreenManageDialog newInstance(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str) {
        return Companion.newInstance(arrayList, str);
    }

    @JvmStatic
    public static final ScreenManageDialog newInstance(ArrayList<DeviceModelResponse.DeviceModel> arrayList, String str, boolean z) {
        return Companion.newInstance(arrayList, str, z);
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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final void setExitListener(DialogAction.ActionListener exitListener) {
        Intrinsics.checkNotNullParameter(exitListener, "exitListener");
        this.exitListener = exitListener;
    }

    public final void setListener(OnStopDeviceListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initDialog();
    }

    private final void initDialog() {
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean("exitable", true) : true;
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCancelable(z);
        }
        Dialog dialog2 = getDialog();
        if (dialog2 != null) {
            dialog2.setCanceledOnTouchOutside(z);
        }
        Dialog dialog3 = getDialog();
        Window window = dialog3 == null ? null : dialog3.getWindow();
        if (window == null) {
            return;
        }
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setBackgroundDrawable(ContextCompat.getDrawable(App.getContext(), 17170445));
        WindowManager.LayoutParams attributes = window.getAttributes();
        Intrinsics.checkNotNullExpressionValue(attributes, "window.attributes");
        attributes.gravity = 80;
        attributes.dimAmount = 0.0f;
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.dialog_screen_manage, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initView();
        initData();
        initListener();
    }

    private final void initListener() {
        ((TextView) _$_findCachedViewById(R.id.tvDone)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ScreenManageDialog$9UsoGY32dWSoyh42wdAaIwGBK5g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenManageDialog.m1102initListener$lambda1(ScreenManageDialog.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ScreenManageDialog$Hp6D4SViiXAMloI4_IvztvS39Z0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenManageDialog.m1103initListener$lambda2(ScreenManageDialog.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m1102initListener$lambda1(ScreenManageDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (App.getUserData().getFamily() != 0) {
            Bundle arguments = this$0.getArguments();
            if (!(arguments != null ? arguments.getBoolean("exitable", true) : true)) {
                DialogAction.ActionListener actionListener = this$0.exitListener;
                if (actionListener != null) {
                    actionListener.onClick();
                }
                this$0.dismiss();
                return;
            }
            this$0.dismiss();
            return;
        }
        this$0.getPayUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m1103initListener$lambda2(ScreenManageDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle arguments = this$0.getArguments();
        if (arguments != null ? arguments.getBoolean("exitable", true) : true) {
            this$0.dismiss();
            return;
        }
        DialogAction.ActionListener actionListener = this$0.exitListener;
        if (actionListener != null) {
            actionListener.onClick();
        }
        this$0.dismiss();
    }

    private final void getPayUrl() {
        RxSubscribersKt.subscribeTo$default(HttpRequest.Companion.post(this, "Pay_url").param("uid", App.getUserData().uid_v2).asBean(HashMap.class), new ScreenManageDialog$getPayUrl$1(this), null, new ScreenManageDialog$getPayUrl$2(this), null, new ScreenManageDialog$getPayUrl$3(this), 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteDevice(DeviceModelResponse.DeviceModel deviceModel) {
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(Http.getService().deletePlayingStatus(API.BASE_URL, API.Common.DELETE_PLAYING_STATUS, deviceModel.getOpen_udid(), App.getUserData().uid_v2), this), new ScreenManageDialog$deleteDevice$1(this), null, new ScreenManageDialog$deleteDevice$2(this), null, new ScreenManageDialog$deleteDevice$3(this), 10, null);
    }

    private final void initData() {
        String string;
        TextView textView = (TextView) _$_findCachedViewById(R.id.tvHint);
        Bundle arguments = getArguments();
        textView.setText((arguments == null || (string = arguments.getString(NotificationCompat.CATEGORY_MESSAGE)) == null) ? "" : string);
        Bundle arguments2 = getArguments();
        CommBaseAdapter<ScreenMember> commBaseAdapter = null;
        Serializable serializable = arguments2 == null ? null : arguments2.getSerializable("data");
        if (serializable == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.DeviceModelResponse.DeviceModel>{ kotlin.collections.TypeAliasesKt.ArrayList<com.movieboxpro.android.model.DeviceModelResponse.DeviceModel> }");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : (ArrayList) serializable) {
            String uid = ((DeviceModelResponse.DeviceModel) obj).getUid();
            Object obj2 = linkedHashMap.get(uid);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(uid, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            ScreenMember screenMember = new ScreenMember();
            screenMember.setUid((String) entry.getKey());
            DeviceModelResponse.DeviceModel deviceModel = (DeviceModelResponse.DeviceModel) CollectionsKt.getOrNull((List) entry.getValue(), 0);
            screenMember.setUsername(deviceModel == null ? null : deviceModel.getUsername());
            screenMember.setDevices((List) entry.getValue());
            arrayList.add(screenMember);
        }
        List sortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.movieboxpro.android.view.dialog.ScreenManageDialog$initData$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Boolean.valueOf(!Intrinsics.areEqual(((ScreenMember) t).getUid(), App.getUserData().getUid())), Boolean.valueOf(!Intrinsics.areEqual(((ScreenMember) t2).getUid(), App.getUserData().getUid())));
            }
        });
        this.memberAdapter = new CommBaseAdapter<>(R.layout.adapter_screen_member, new ScreenManageDialog$initData$2(this), null, 4, null);
        ((RecyclerView) _$_findCachedViewById(R.id.rvMembers)).setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.rvMembers);
        CommBaseAdapter<ScreenMember> commBaseAdapter2 = this.memberAdapter;
        if (commBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
            commBaseAdapter2 = null;
        }
        recyclerView.setAdapter(commBaseAdapter2);
        CommBaseAdapter<ScreenMember> commBaseAdapter3 = this.memberAdapter;
        if (commBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
        } else {
            commBaseAdapter = commBaseAdapter3;
        }
        commBaseAdapter.setList(sortedWith);
    }

    private final void initView() {
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean("exitable", true) : true;
        if (App.getUserData().getFamily() != 0) {
            ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText(Intrinsics.stringPlus(App.getUserData().getMaster_username(), "'s Family"));
            ((TextView) _$_findCachedViewById(R.id.tvDone)).setText("Done");
            TextView tvCancel = (TextView) _$_findCachedViewById(R.id.tvCancel);
            Intrinsics.checkNotNullExpressionValue(tvCancel, "tvCancel");
            CommonExtKt.gone(tvCancel);
            if (z) {
                return;
            }
            ((TextView) _$_findCachedViewById(R.id.tvDone)).setText("Exit");
            return;
        }
        TextView tvPlaying = (TextView) _$_findCachedViewById(R.id.tvPlaying);
        Intrinsics.checkNotNullExpressionValue(tvPlaying, "tvPlaying");
        CommonExtKt.gone(tvPlaying);
        ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText("Maximum Playing Screen!");
        ((TextView) _$_findCachedViewById(R.id.tvDone)).setText("Upgrade");
        TextView tvCancel2 = (TextView) _$_findCachedViewById(R.id.tvCancel);
        Intrinsics.checkNotNullExpressionValue(tvCancel2, "tvCancel");
        CommonExtKt.visible(tvCancel2);
        if (z) {
            return;
        }
        ((TextView) _$_findCachedViewById(R.id.tvCancel)).setText("Exit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoadingView() {
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoadingView() {
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f);
        }
        KProgressHUD kProgressHUD = this.kProgressHUD;
        if (kProgressHUD == null) {
            return;
        }
        kProgressHUD.show();
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildData(String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        HttpUtils.decodeBody("eyJhcHBfa2V5IjoiNjQwNWMyZTYwNDVmNjU5YjdmZGNkOTU3ZmU1MmZlOWEiLCJlbmNyeXB0X2RhdGEiOiJWQUtzQ1JJY0k4NGFBQWgzV3UrXC9kcU1hY2lUNGMxNHFaUnAza0F4RzNVRTJlVDcwTFB4QUZ4ZE9KejhGMGRaSiIsInZlcmlmeSI6ImIzYTJmZWE2ZGRmYzBhYzgzMTQ3OTc3Njg1MGFmN2ZkIn0=", CipherKeys.getCiperKeys());
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    /* compiled from: ScreenManageDialog.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0007¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ScreenManageDialog$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ScreenManageDialog;", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "", "exitable", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ScreenManageDialog newInstance(ArrayList<DeviceModelResponse.DeviceModel> list, String str) {
            Intrinsics.checkNotNullParameter(list, "list");
            return newInstance$default(this, list, str, false, 4, null);
        }

        private Companion() {
        }

        public static /* synthetic */ ScreenManageDialog newInstance$default(Companion companion, ArrayList arrayList, String str, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = true;
            }
            return companion.newInstance(arrayList, str, z);
        }

        @JvmStatic
        public final ScreenManageDialog newInstance(ArrayList<DeviceModelResponse.DeviceModel> list, String str, boolean z) {
            Intrinsics.checkNotNullParameter(list, "list");
            ScreenManageDialog screenManageDialog = new ScreenManageDialog();
            screenManageDialog.setArguments(CommonExtKt.bundleOf(TuplesKt.to("data", list), TuplesKt.to("exitable", Boolean.valueOf(z)), TuplesKt.to(NotificationCompat.CATEGORY_MESSAGE, str)));
            return screenManageDialog;
        }
    }
}
