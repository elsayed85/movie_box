package com.movieboxpro.android.model;

import com.movieboxpro.android.model.search.SrearchResultModel;
import java.util.List;
/* loaded from: classes3.dex */
public class SearchResultModel {
    private List<ActorModel> actorModels;
    private List<SrearchResultModel> srearchResultModels;

    public SearchResultModel(List<ActorModel> list, List<SrearchResultModel> list2) {
        this.actorModels = list;
        this.srearchResultModels = list2;
    }

    public List<ActorModel> getActorModels() {
        return this.actorModels;
    }

    public void setActorModels(List<ActorModel> list) {
        this.actorModels = list;
    }

    public List<SrearchResultModel> getSrearchResultModels() {
        return this.srearchResultModels;
    }

    public void setSrearchResultModels(List<SrearchResultModel> list) {
        this.srearchResultModels = list;
    }
}
