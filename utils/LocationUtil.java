package com.movieboxpro.android.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.lxj.xpopup.util.PermissionConstants;
/* loaded from: classes3.dex */
public class LocationUtil {
    private static String TAG = "LocationUtil";

    /* loaded from: classes3.dex */
    public interface LocationCallBack {
        void onFail(String str);

        void onSuccess(Location location);
    }

    public static void getCurrentLocation(Context context, LocationCallBack locationCallBack) {
        if (locationCallBack == null || context == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(context, PermissionConstants.LOCATION) == 0) {
            locationCallBack.onFail("请确保已经获取定位权限");
        }
        LocationManager locationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        MyLocationListener myLocationListener = new MyLocationListener(locationManager, locationCallBack);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(1);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (!TextUtils.isEmpty(bestProvider)) {
            String str = TAG;
            Log.e(str, "bestProvider = " + bestProvider + "可用");
            locationManager.requestLocationUpdates(bestProvider, 0L, 0.0f, myLocationListener);
        } else if (locationManager.isProviderEnabled("network")) {
            Log.e(TAG, "network可用");
            locationManager.requestLocationUpdates("network", 0L, 0.0f, myLocationListener);
        } else if (locationManager.isProviderEnabled("gps")) {
            Log.e(TAG, "gps可用");
            locationManager.requestLocationUpdates("gps", 0L, 0.0f, myLocationListener);
        } else {
            Log.e(TAG, "定位不可用，提示打开GPS");
            locationCallBack.onFail("无可用的定位方式，请打开GPS");
        }
    }

    /* loaded from: classes3.dex */
    private static class MyLocationListener implements LocationListener {
        private LocationCallBack mLocationCallBack;
        private LocationManager mLocationManager;

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public MyLocationListener(LocationManager locationManager, LocationCallBack locationCallBack) {
            this.mLocationManager = locationManager;
            this.mLocationCallBack = locationCallBack;
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                LocationCallBack locationCallBack = this.mLocationCallBack;
                if (locationCallBack != null) {
                    locationCallBack.onSuccess(location);
                }
                LocationManager locationManager = this.mLocationManager;
                if (locationManager != null) {
                    locationManager.removeUpdates(this);
                    return;
                }
                return;
            }
            LocationCallBack locationCallBack2 = this.mLocationCallBack;
            if (locationCallBack2 != null) {
                locationCallBack2.onFail("location == null");
            }
        }
    }
}
