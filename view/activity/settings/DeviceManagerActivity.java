package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.material.tabs.TabLayout;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.DeviceResponse;
import com.movieboxpro.android.model.LoginBrowser;
import com.movieboxpro.android.model.common.Device;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.settings.DeviceManagerActivity;
import io.reactivex.Observable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: DeviceManagerActivity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \n2\u00020\u0001:\u0003\t\n\u000bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "getLayoutResId", "", "initData", "", "initListener", "initView", "BrowserListFragment", "Companion", "DeviceListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceManagerActivity extends BaseSimpleActivity {
    public static final Companion Companion = new Companion(null);
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @JvmStatic
    public static final void start(Context context, String str) {
        Companion.start(context, str);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
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

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_device_manager;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) findViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$DeviceManagerActivity$CS2C7YYObaaOyFN_gmLMHrhuXHU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceManagerActivity.m775initListener$lambda0(DeviceManagerActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m775initListener$lambda0(DeviceManagerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ArrayList arrayList = new ArrayList();
        String stringExtra = getIntent().getStringExtra("devices");
        String str = stringExtra;
        if (!(str == null || StringsKt.isBlank(str))) {
            DeviceResponse deviceResponse = (DeviceResponse) JSONObject.parseObject(stringExtra, DeviceResponse.class);
            DeviceListFragment.Companion companion = DeviceListFragment.Companion;
            ArrayList<Device> app_device = deviceResponse.getApp_device();
            if (app_device == null) {
                app_device = new ArrayList<>();
            }
            arrayList.add(companion.newInstance(app_device));
            BrowserListFragment.Companion companion2 = BrowserListFragment.Companion;
            ArrayList<LoginBrowser> web_device = deviceResponse.getWeb_device();
            if (web_device == null) {
                web_device = new ArrayList<>();
            }
            arrayList.add(companion2.newInstance(web_device));
        } else {
            arrayList.add(new DeviceListFragment());
            arrayList.add(new BrowserListFragment());
        }
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getSupportFragmentManager(), arrayList, new String[]{"Apps", "Browsers"});
        viewPager.setOffscreenPageLimit(arrayList.size());
        viewPager.setAdapter(tabLayoutPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        ((TextView) findViewById(R.id.tv_title)).setText("Device");
    }

    /* compiled from: DeviceManagerActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$Companion;", "", "()V", TtmlNode.START, "", "context", "Landroid/content/Context;", "devices", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final void start(Context context, String devices) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(devices, "devices");
            Intent intent = new Intent(context, DeviceManagerActivity.class);
            intent.putExtra("devices", devices);
            context.startActivity(intent);
        }
    }

    /* compiled from: DeviceManagerActivity.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001d2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0014J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\tH\u0014J\b\u0010\u001c\u001a\u00020\tH\u0014R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$DeviceListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/common/Device;", "", "()V", "devices", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "isNeedTest", "", "isOpenLoadMore", "onLongClick", "Lcom/chad/library/adapter/base/listener/OnItemLongClickListener;", "removeDevice", "position", "setEmptyView", "test", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DeviceListFragment extends BaseListFragment<Device, String> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private ArrayList<Device> devices;

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
            return R.layout.layout_device_list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return false;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post(API.Common.DEVICELIST).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isNeedTest() {
            ArrayList<Device> arrayList = this.devices;
            return !(arrayList == null || arrayList.isEmpty());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void test() {
            super.test();
            ArrayList<Device> arrayList = this.devices;
            if (arrayList == null || arrayList.isEmpty()) {
                return;
            }
            this.mAdapter.setList(this.devices);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemLongClickListener onLongClick() {
            return new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$DeviceManagerActivity$DeviceListFragment$XZGmNy_c8JsNi3eE_sfyZMEfFNc
                @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    boolean m780onLongClick$lambda1;
                    m780onLongClick$lambda1 = DeviceManagerActivity.DeviceListFragment.m780onLongClick$lambda1(DeviceManagerActivity.DeviceListFragment.this, baseQuickAdapter, view, i);
                    return m780onLongClick$lambda1;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onLongClick$lambda-1  reason: not valid java name */
        public static final boolean m780onLongClick$lambda1(final DeviceListFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            final Device device = (Device) this$0.mAdapter.getItem(i);
            new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("Delete", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$DeviceManagerActivity$DeviceListFragment$p3nlgFI5v5UcP3imWdFurJfFo58
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public final void onClick(int i2) {
                    DeviceManagerActivity.DeviceListFragment.m781onLongClick$lambda1$lambda0(DeviceManagerActivity.DeviceListFragment.this, device, i, i2);
                }
            }).show();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onLongClick$lambda-1$lambda-0  reason: not valid java name */
        public static final void m781onLongClick$lambda1$lambda0(DeviceListFragment this$0, Device item, int i, int i2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(item, "item");
            this$0.removeDevice(item, i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void setEmptyView() {
            super.setEmptyView();
        }

        private final void removeDevice(Device device, int i) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post(API.USER.DEVICE_DELETE).param("open_udid", device.open_udid).param("device_id", device.id).asRequest(), this), new DeviceManagerActivity$DeviceListFragment$removeDevice$1(this), null, new DeviceManagerActivity$DeviceListFragment$removeDevice$2(this), null, new DeviceManagerActivity$DeviceListFragment$removeDevice$3(this, i), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = Device.class;
            Serializable serializable = bundle == null ? null : bundle.getSerializable("devices");
            this.devices = serializable instanceof ArrayList ? (ArrayList) serializable : null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, Device item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.ic_device_Model, item.model);
            helper.setText(R.id.ic_device_name, item.name);
            long j = 1000;
            helper.setText(R.id.ic_device_start_time, TimeUtils.formatTime3(item.start_time.longValue() * j));
            helper.setText(R.id.ic_device_last_time, TimeUtils.formatTime3(item.end_time.longValue() * j));
        }

        /* compiled from: DeviceManagerActivity.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$DeviceListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$DeviceListFragment;", "devices", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Device;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final DeviceListFragment newInstance(ArrayList<Device> devices) {
                Intrinsics.checkNotNullParameter(devices, "devices");
                DeviceListFragment deviceListFragment = new DeviceListFragment();
                deviceListFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("devices", devices)));
                return deviceListFragment;
            }
        }
    }

    /* compiled from: DeviceManagerActivity.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001c2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0014J\u0018\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0002H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0015H\u0014J\b\u0010\u0017\u001a\u00020\u0018H\u0014J\u0018\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0013H\u0002J\b\u0010\u001b\u001a\u00020\tH\u0014R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$BrowserListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/LoginBrowser;", "", "()V", "devices", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "isNeedTest", "", "isOpenLoadMore", "onLongClick", "Lcom/chad/library/adapter/base/listener/OnItemLongClickListener;", "removeDevice", "position", "test", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class BrowserListFragment extends BaseListFragment<LoginBrowser, String> {
        public static final Companion Companion = new Companion(null);
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
        private ArrayList<LoginBrowser> devices;

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
            return R.layout.layout_device_list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return false;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post("Device_list_web").asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isNeedTest() {
            ArrayList<LoginBrowser> arrayList = this.devices;
            return !(arrayList == null || arrayList.isEmpty());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void test() {
            super.test();
            ArrayList<LoginBrowser> arrayList = this.devices;
            if (arrayList == null || arrayList.isEmpty()) {
                return;
            }
            this.mAdapter.setList(this.devices);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemLongClickListener onLongClick() {
            return new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$DeviceManagerActivity$BrowserListFragment$TQhjxRhaLvU2enbpumqxCwvU1-g
                @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    boolean m778onLongClick$lambda1;
                    m778onLongClick$lambda1 = DeviceManagerActivity.BrowserListFragment.m778onLongClick$lambda1(DeviceManagerActivity.BrowserListFragment.this, baseQuickAdapter, view, i);
                    return m778onLongClick$lambda1;
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onLongClick$lambda-1  reason: not valid java name */
        public static final boolean m778onLongClick$lambda1(final BrowserListFragment this$0, BaseQuickAdapter noName_0, View view, final int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(view, "view");
            final LoginBrowser loginBrowser = (LoginBrowser) this$0.mAdapter.getItem(i);
            new ActionSheetDialog(this$0.getContext()).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("Delete", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$DeviceManagerActivity$BrowserListFragment$t5c7YYrIbwZpXUO23ucbzl52fJE
                @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
                public final void onClick(int i2) {
                    DeviceManagerActivity.BrowserListFragment.m779onLongClick$lambda1$lambda0(DeviceManagerActivity.BrowserListFragment.this, loginBrowser, i, i2);
                }
            }).show();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onLongClick$lambda-1$lambda-0  reason: not valid java name */
        public static final void m779onLongClick$lambda1$lambda0(BrowserListFragment this$0, LoginBrowser item, int i, int i2) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullExpressionValue(item, "item");
            this$0.removeDevice(item, i);
        }

        private final void removeDevice(LoginBrowser loginBrowser, int i) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("Device_delete_web").param("device_id", loginBrowser.getId()).asRequest(), this), new DeviceManagerActivity$BrowserListFragment$removeDevice$1(this), null, new DeviceManagerActivity$BrowserListFragment$removeDevice$2(this), null, new DeviceManagerActivity$BrowserListFragment$removeDevice$3(this, i), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = LoginBrowser.class;
            Serializable serializable = bundle == null ? null : bundle.getSerializable("devices");
            this.devices = serializable instanceof ArrayList ? (ArrayList) serializable : null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, LoginBrowser item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.ic_device_Model, item.getBrowser());
            helper.setText(R.id.ic_device_name, item.getOs());
            long j = 1000;
            helper.setText(R.id.ic_device_start_time, TimeUtils.formatTime3(item.getAdd_time() * j));
            helper.setText(R.id.ic_device_last_time, TimeUtils.formatTime3(item.getLast_time() * j));
        }

        /* compiled from: DeviceManagerActivity.kt */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$BrowserListFragment$Companion;", "", "()V", "newInstance", "Lcom/movieboxpro/android/view/activity/settings/DeviceManagerActivity$BrowserListFragment;", "devices", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/LoginBrowser;", "Lkotlin/collections/ArrayList;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        /* loaded from: classes3.dex */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final BrowserListFragment newInstance(ArrayList<LoginBrowser> devices) {
                Intrinsics.checkNotNullParameter(devices, "devices");
                BrowserListFragment browserListFragment = new BrowserListFragment();
                browserListFragment.setArguments(CommonExtKt.bundleOf(TuplesKt.to("devices", devices)));
                return browserListFragment;
            }
        }
    }
}
