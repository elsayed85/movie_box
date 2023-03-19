package com.movieboxpro.android.model;

import com.movieboxpro.android.model.common.Device;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.beanutils.PropertyUtils;
/* compiled from: Bean.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005¢\u0006\u0002\u0010\bJ\u001d\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J\u001d\u0010\r\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005HÆ\u0003JE\u0010\u000e\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00052\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R%\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR%\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/model/DeviceResponse;", "", "app_device", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Device;", "Lkotlin/collections/ArrayList;", "web_device", "Lcom/movieboxpro/android/model/LoginBrowser;", "(Ljava/util/ArrayList;Ljava/util/ArrayList;)V", "getApp_device", "()Ljava/util/ArrayList;", "getWeb_device", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceResponse {
    private final ArrayList<Device> app_device;
    private final ArrayList<LoginBrowser> web_device;

    public DeviceResponse() {
        this(null, null, 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DeviceResponse copy$default(DeviceResponse deviceResponse, ArrayList arrayList, ArrayList arrayList2, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = deviceResponse.app_device;
        }
        if ((i & 2) != 0) {
            arrayList2 = deviceResponse.web_device;
        }
        return deviceResponse.copy(arrayList, arrayList2);
    }

    public final ArrayList<Device> component1() {
        return this.app_device;
    }

    public final ArrayList<LoginBrowser> component2() {
        return this.web_device;
    }

    public final DeviceResponse copy(ArrayList<Device> arrayList, ArrayList<LoginBrowser> arrayList2) {
        return new DeviceResponse(arrayList, arrayList2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceResponse) {
            DeviceResponse deviceResponse = (DeviceResponse) obj;
            return Intrinsics.areEqual(this.app_device, deviceResponse.app_device) && Intrinsics.areEqual(this.web_device, deviceResponse.web_device);
        }
        return false;
    }

    public int hashCode() {
        ArrayList<Device> arrayList = this.app_device;
        int hashCode = (arrayList == null ? 0 : arrayList.hashCode()) * 31;
        ArrayList<LoginBrowser> arrayList2 = this.web_device;
        return hashCode + (arrayList2 != null ? arrayList2.hashCode() : 0);
    }

    public String toString() {
        return "DeviceResponse(app_device=" + this.app_device + ", web_device=" + this.web_device + PropertyUtils.MAPPED_DELIM2;
    }

    public DeviceResponse(ArrayList<Device> arrayList, ArrayList<LoginBrowser> arrayList2) {
        this.app_device = arrayList;
        this.web_device = arrayList2;
    }

    public /* synthetic */ DeviceResponse(ArrayList arrayList, ArrayList arrayList2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : arrayList, (i & 2) != 0 ? null : arrayList2);
    }

    public final ArrayList<Device> getApp_device() {
        return this.app_device;
    }

    public final ArrayList<LoginBrowser> getWeb_device() {
        return this.web_device;
    }
}
