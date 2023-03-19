package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.R;
import com.movieboxpro.android.event.LocationCompleteEvent;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.LocationUtils;
import com.movieboxpro.android.view.activity.PermissionRequestActivity;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
/* compiled from: IpLocationFragment.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\"\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001d\u001a\u00020\fH\u0017J\b\u0010\u001e\u001a\u00020\fH\u0017J\u001a\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010!\u001a\u00020\fH\u0003J\b\u0010\"\u001a\u00020\fH\u0002¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/IpLocationFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "checkPermission", "", "context", "Landroid/content/Context;", "permissions", "", "", "(Landroid/content/Context;[Ljava/lang/String;)Z", "initData", "", "initListener", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onDestroy", "onResume", "onViewCreated", "view", "requestLocationPermission", "startLocation", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class IpLocationFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    private static final int REQUEST_CODE_LOCATION = 100;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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

    /* compiled from: IpLocationFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/activity/settings/IpLocationFragment$Companion;", "", "()V", "REQUEST_CODE_LOCATION", "", "newInstance", "Lcom/movieboxpro/android/view/activity/settings/IpLocationFragment;", IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, "", FirebaseAnalytics.Param.LOCATION, "ipLocation", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final IpLocationFragment newInstance(String ip, String location, String ipLocation) {
            Intrinsics.checkNotNullParameter(ip, "ip");
            Intrinsics.checkNotNullParameter(location, "location");
            Intrinsics.checkNotNullParameter(ipLocation, "ipLocation");
            IpLocationFragment ipLocationFragment = new IpLocationFragment();
            Bundle bundle = new Bundle();
            bundle.putString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, ip);
            bundle.putString(FirebaseAnalytics.Param.LOCATION, location);
            bundle.putString("ipLocation", ipLocation);
            ipLocationFragment.setArguments(bundle);
            return ipLocationFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.TransparentDialog);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_ip_location, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        initData();
        initListener();
    }

    private final void initListener() {
        ((TextView) _$_findCachedViewById(R.id.tvDone)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$IpLocationFragment$YHB8Y_9l75utr3M-dp3xiUzJT4M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IpLocationFragment.m784initListener$lambda0(IpLocationFragment.this, view);
            }
        });
        ((LinearLayout) _$_findCachedViewById(R.id.llAccess)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$IpLocationFragment$HiQvz6cTcPytvdLeT97eCRDuaRY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IpLocationFragment.m785initListener$lambda2(IpLocationFragment.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m784initListener$lambda0(IpLocationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m785initListener$lambda2(IpLocationFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LocationUtils.isLocationEnabled()) {
            this$0.requestLocationPermission();
        } else if (LocationUtils.isGpsEnabled()) {
        } else {
            new MsgHintDialog.Builder(this$0.getContext()).setTitle("Note").setContent("Open GPS?").setActionListener($$Lambda$IpLocationFragment$vX1vZfX0BiFXRriCjw8P3LzNv5E.INSTANCE).create().show();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (LocationUtils.isLocationEnabled()) {
            Context context = getContext();
            if (context == null) {
                return;
            }
            if (checkPermission(context, new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})) {
                requestLocationPermission();
                return;
            }
            LinearLayout llAccess = (LinearLayout) _$_findCachedViewById(R.id.llAccess);
            Intrinsics.checkNotNullExpressionValue(llAccess, "llAccess");
            CommonExtKt.visible(llAccess);
            return;
        }
        LinearLayout llAccess2 = (LinearLayout) _$_findCachedViewById(R.id.llAccess);
        Intrinsics.checkNotNullExpressionValue(llAccess2, "llAccess");
        CommonExtKt.visible(llAccess2);
        ((TextView) _$_findCachedViewById(R.id.tvRetry)).setText("GPS is not available,tap to open");
    }

    private final void requestLocationPermission() {
        startLocation();
    }

    private final void startLocation() {
        if (CommonUtils.havePermissions(getContext(), new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"})) {
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                ((TextView) _$_findCachedViewById(R.id.tvRetry)).setText("Locating");
                LocationUtils.register(1000L, 0L, new LocationUtils.OnLocationChangeListener() { // from class: com.movieboxpro.android.view.activity.settings.IpLocationFragment$startLocation$1
                    @Override // com.movieboxpro.android.utils.LocationUtils.OnLocationChangeListener
                    public void getLastKnownLocation(Location location) {
                    }

                    @Override // com.movieboxpro.android.utils.LocationUtils.OnLocationChangeListener
                    public void onStatusChanged(String str, int i, Bundle bundle) {
                    }

                    @Override // com.movieboxpro.android.utils.LocationUtils.OnLocationChangeListener
                    public void onLocationChanged(Location location) {
                        List<Address> fromLocation;
                        try {
                            if (IpLocationFragment.this.getContext() == null || location == null || (fromLocation = new Geocoder(IpLocationFragment.this.getContext(), Locale.ENGLISH).getFromLocation(location.getLatitude(), location.getLongitude(), 1)) == null || !(!fromLocation.isEmpty())) {
                                return;
                            }
                            Address address = fromLocation.get(0);
                            StringBuilder sb = new StringBuilder();
                            sb.append((Object) address.getCountryName());
                            sb.append(',');
                            sb.append((Object) address.getAdminArea());
                            sb.append(',');
                            sb.append((Object) address.getSubLocality());
                            ((TextView) IpLocationFragment.this._$_findCachedViewById(R.id.tvGpsLocation)).setText(sb.toString());
                            TextView tvGpsLocation = (TextView) IpLocationFragment.this._$_findCachedViewById(R.id.tvGpsLocation);
                            Intrinsics.checkNotNullExpressionValue(tvGpsLocation, "tvGpsLocation");
                            CommonExtKt.visible(tvGpsLocation);
                            LinearLayout llAccess = (LinearLayout) IpLocationFragment.this._$_findCachedViewById(R.id.llAccess);
                            Intrinsics.checkNotNullExpressionValue(llAccess, "llAccess");
                            CommonExtKt.gone(llAccess);
                            EventBus.getDefault().post(new LocationCompleteEvent(location.getLatitude(), location.getLongitude(), ((TextView) IpLocationFragment.this._$_findCachedViewById(R.id.tvGpsLocation)).getText().toString()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            return;
        }
        PermissionRequestActivity.Companion.start(this, 1, 100);
    }

    private final boolean checkPermission(Context context, String[] strArr) {
        if (context == null) {
            return false;
        }
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            i++;
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 100 && i2 == -1) {
            startLocation();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (checkPermission(getContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION"})) {
            LocationUtils.unregister();
        }
        LocationUtils.unregister();
        super.onDestroy();
    }

    private final void initData() {
        String string;
        Bundle arguments = getArguments();
        String str = "";
        if (arguments != null && (string = arguments.getString(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP)) != null) {
            str = string;
        }
        ((TextView) _$_findCachedViewById(R.id.tvIp)).setText(str);
        Bundle arguments2 = getArguments();
        ((TextView) _$_findCachedViewById(R.id.tvLocation)).setText(arguments2 == null ? null : arguments2.getString("ipLocation"));
    }
}
