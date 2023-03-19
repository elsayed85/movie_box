package com.movieboxpro.android.event;

import com.movieboxpro.android.model.movie.NormalFilmModel;
/* loaded from: classes3.dex */
public class OnVideoCheckChangedEvent {
    private NormalFilmModel normalFilmModel;

    public OnVideoCheckChangedEvent(NormalFilmModel normalFilmModel) {
        this.normalFilmModel = normalFilmModel;
    }

    public NormalFilmModel getNormalFilmModel() {
        return this.normalFilmModel;
    }

    public void setNormalFilmModel(NormalFilmModel normalFilmModel) {
        this.normalFilmModel = normalFilmModel;
    }
}
