package com.movieboxpro.android.view.videocontroller;

import com.dueeeke.model.ExtrModel;
import java.util.Comparator;
/* compiled from: lambda */
/* renamed from: com.movieboxpro.android.view.videocontroller.-$$Lambda$OpenSubtitleController$OxQ4FBSqsiUZyz1Uzck-nYotQfI  reason: invalid class name */
/* loaded from: classes3.dex */
public final /* synthetic */ class $$Lambda$OpenSubtitleController$OxQ4FBSqsiUZyz1UzcknYotQfI implements Comparator {
    public static final /* synthetic */ $$Lambda$OpenSubtitleController$OxQ4FBSqsiUZyz1UzcknYotQfI INSTANCE = new $$Lambda$OpenSubtitleController$OxQ4FBSqsiUZyz1UzcknYotQfI();

    private /* synthetic */ $$Lambda$OpenSubtitleController$OxQ4FBSqsiUZyz1UzcknYotQfI() {
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int compareTo;
        compareTo = ((ExtrModel) obj).getLanguageName().compareTo(((ExtrModel) obj2).getLanguageName());
        return compareTo;
    }
}
