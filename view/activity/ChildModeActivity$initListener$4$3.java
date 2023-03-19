package com.movieboxpro.android.view.activity;

import android.content.Intent;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.view.activity.settings.SuperChildModePasswordActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ChildModeActivity.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/movieboxpro/android/model/movie/MovieListModel;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ChildModeActivity$initListener$4$3 extends Lambda implements Function1<MovieListModel, Unit> {
    final /* synthetic */ ChildModeActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChildModeActivity$initListener$4$3(ChildModeActivity childModeActivity) {
        super(1);
        this.this$0 = childModeActivity;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(MovieListModel movieListModel) {
        invoke2(movieListModel);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(MovieListModel movieListModel) {
        this.this$0.hideLoadingView();
        List<MovieListModel.MovieListItem> list = movieListModel.getList();
        if (list == null || list.isEmpty()) {
            MsgHintDialog.Builder positiveText = new MsgHintDialog.Builder(this.this$0).setContent("Playlist is empty, please add at least one").setCancelable(false).setPositiveText("Add");
            final ChildModeActivity childModeActivity = this.this$0;
            positiveText.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$ChildModeActivity$initListener$4$3$rfeKPAc12Od3W52yH0Ylzq03RBc
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    ChildModeActivity$initListener$4$3.m149invoke$lambda0(ChildModeActivity.this);
                }
            }).create().show();
            return;
        }
        SuperChildModePasswordActivity.Companion.start(this.this$0, 400);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0  reason: not valid java name */
    public static final void m149invoke$lambda0(ChildModeActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ChildModeActivity childModeActivity = this$0;
        childModeActivity.startActivity(new Intent(childModeActivity, AddMovieListActivity.class));
    }
}
