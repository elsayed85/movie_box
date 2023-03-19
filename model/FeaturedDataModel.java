package com.movieboxpro.android.model;

import com.movieboxpro.android.model.common.Homelist;
import java.util.List;
/* loaded from: classes3.dex */
public class FeaturedDataModel {
    private List<AdvertisementModel> advertisementModels;
    private List<Homelist> homelists;

    public FeaturedDataModel() {
    }

    public FeaturedDataModel(List<AdvertisementModel> list, List<Homelist> list2) {
        this.advertisementModels = list;
        this.homelists = list2;
    }

    public List<AdvertisementModel> getAdvertisementModels() {
        return this.advertisementModels;
    }

    public void setAdvertisementModels(List<AdvertisementModel> list) {
        this.advertisementModels = list;
    }

    public List<Homelist> getHomelists() {
        return this.homelists;
    }

    public void setHomelists(List<Homelist> list) {
        this.homelists = list;
    }
}
