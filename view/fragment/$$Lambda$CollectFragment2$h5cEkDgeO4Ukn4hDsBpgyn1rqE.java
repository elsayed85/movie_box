package com.movieboxpro.android.view.fragment;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.model.CountryResponse;
import io.reactivex.functions.Function;
import java.util.ArrayList;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment2$h5cEkDgeO4-Ukn4hDsBpgyn1rqE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CollectFragment2$h5cEkDgeO4Ukn4hDsBpgyn1rqE implements Function {
    public static final /* synthetic */ $$Lambda$CollectFragment2$h5cEkDgeO4Ukn4hDsBpgyn1rqE INSTANCE = new $$Lambda$CollectFragment2$h5cEkDgeO4Ukn4hDsBpgyn1rqE();

    private /* synthetic */ $$Lambda$CollectFragment2$h5cEkDgeO4Ukn4hDsBpgyn1rqE() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ArrayList country_list;
        country_list = ((CountryResponse) JSONObject.parseObject((String) obj, CountryResponse.class)).getCountry_list();
        return country_list;
    }
}
