package com.movieboxpro.android.view.fragment;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.model.CountryResponse;
import io.reactivex.functions.Function;
import java.util.ArrayList;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment$alS2LJrgboG1GDxvnWAun3RMomY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CollectFragment$alS2LJrgboG1GDxvnWAun3RMomY implements Function {
    public static final /* synthetic */ $$Lambda$CollectFragment$alS2LJrgboG1GDxvnWAun3RMomY INSTANCE = new $$Lambda$CollectFragment$alS2LJrgboG1GDxvnWAun3RMomY();

    private /* synthetic */ $$Lambda$CollectFragment$alS2LJrgboG1GDxvnWAun3RMomY() {
    }

    @Override // io.reactivex.functions.Function
    public final Object apply(Object obj) {
        ArrayList country_list;
        country_list = ((CountryResponse) JSONObject.parseObject((String) obj, CountryResponse.class)).getCountry_list();
        return country_list;
    }
}
