package com.movieboxpro.android.view.activity.review;

import android.widget.EditText;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.CommonExtKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EditReviewActivity.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EditReviewActivity$uploadImages$6 extends Lambda implements Function1<String, Unit> {
    final /* synthetic */ Ref.ObjectRef<List<String>> $idList;
    final /* synthetic */ EditReviewActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EditReviewActivity$uploadImages$6(EditReviewActivity editReviewActivity, Ref.ObjectRef<List<String>> objectRef) {
        super(1);
        this.this$0 = editReviewActivity;
        this.$idList = objectRef;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(String str) {
        invoke2(str);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(String str) {
        this.this$0.hideLoadingView();
        CommonExtKt.logD(this.this$0, str);
        StringBuilder sb = new StringBuilder();
        List<String> list = this.$idList.element;
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String str2 = (String) obj;
                if (i == list.size() - 1) {
                    sb.append(str2);
                } else {
                    sb.append(str2);
                    sb.append(",");
                }
                i = i2;
            }
        }
        EditReviewActivity editReviewActivity = this.this$0;
        editReviewActivity.updateReview(str, ((EditText) editReviewActivity._$_findCachedViewById(R.id.etTitle)).getText().toString(), sb.toString());
    }
}
