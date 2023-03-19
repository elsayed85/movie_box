package com.movieboxpro.android.view.fragment.search;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.model.CountryResponse;
import io.reactivex.functions.Function;
import java.util.ArrayList;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.search.-$$Lambda$ResultPagerFragment$27WCSmDdPXP2EkPYqyiTmON9egE  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$ResultPagerFragment$27WCSmDdPXP2EkPYqyiTmON9egE implements Function {
    public static final /* synthetic */ $$Lambda$ResultPagerFragment$27WCSmDdPXP2EkPYqyiTmON9egE INSTANCE = new $$Lambda$ResultPagerFragment$27WCSmDdPXP2EkPYqyiTmON9egE();

    private /* synthetic */ $$Lambda$ResultPagerFragment$27WCSmDdPXP2EkPYqyiTmON9egE() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ArrayList country_list;
        country_list = ((CountryResponse) JSONObject.parseObject((String) obj, CountryResponse.class)).getCountry_list();
        return country_list;
    }
}
