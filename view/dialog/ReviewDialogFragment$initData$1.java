package com.movieboxpro.android.view.dialog;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.CommBaseAdapter;
import com.movieboxpro.android.model.Comment;
import com.movieboxpro.android.model.ImageItem;
import com.movieboxpro.android.model.ReviewItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.widget.GridSpacingItemDecoration;
import com.movieboxpro.android.view.widget.UserAvatarView;
import com.movieboxpro.android.view.widget.textview.QMUISpanTouchFixTextView;
import com.movieboxpro.android.view.widget.textview.QMUITouchableSpan;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
/* compiled from: ReviewDialogFragment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Lcom/movieboxpro/android/model/ReviewItem;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class ReviewDialogFragment$initData$1 extends Lambda implements Function2<BaseViewHolder, ReviewItem, Unit> {
    final /* synthetic */ ReviewDialogFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReviewDialogFragment$initData$1(ReviewDialogFragment reviewDialogFragment) {
        super(2);
        this.this$0 = reviewDialogFragment;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(BaseViewHolder baseViewHolder, ReviewItem reviewItem) {
        invoke2(baseViewHolder, reviewItem);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(BaseViewHolder helper, ReviewItem item) {
        Integer is_delete;
        Integer is_delete2;
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvUsername, item.getUsername());
        helper.setText(R.id.tvTime, TimeUtils.formatTime(item.getDateline() * 1000));
        QMUISpanTouchFixTextView qMUISpanTouchFixTextView = (QMUISpanTouchFixTextView) helper.getView(R.id.tvContent);
        qMUISpanTouchFixTextView.setMovementMethodDefault();
        qMUISpanTouchFixTextView.setNeedForceEventToParent(true);
        ImageView imageView = (ImageView) helper.getView(R.id.ivAction);
        if (Intrinsics.areEqual(item.getUid(), App.getUserData().uid_v2) && (is_delete2 = item.is_delete()) != null && is_delete2.intValue() == 1) {
            CommonExtKt.gone(imageView);
        } else {
            CommonExtKt.visible(imageView);
        }
        QMUISpanTouchFixTextView qMUISpanTouchFixTextView2 = qMUISpanTouchFixTextView;
        SpanUtils span = SpanUtils.with(qMUISpanTouchFixTextView2);
        Integer is_delete3 = item.is_delete();
        int i = R.color.white30_transparent;
        if (is_delete3 != null && is_delete3.intValue() == 0) {
            List<Comment> comment = item.getComment();
            if (comment != null) {
                ReviewDialogFragment reviewDialogFragment = this.this$0;
                for (Comment comment2 : comment) {
                    String uid = comment2.getUid();
                    if (uid == null || StringsKt.isBlank(uid)) {
                        Intrinsics.checkNotNullExpressionValue(span, "span");
                        CommonExtKt.addText(span, String.valueOf(comment2.getText()), 16, i);
                    } else {
                        String stringPlus = Intrinsics.stringPlus("@", comment2.getUsername());
                        if (stringPlus == null) {
                            stringPlus = "";
                        }
                        SpanUtils append = span.append(stringPlus);
                        final int colorInt = CommonExtKt.colorInt(reviewDialogFragment, (int) R.color.color_main_blue);
                        final int colorInt2 = CommonExtKt.colorInt(reviewDialogFragment, (int) R.color.color_main_blue);
                        final int colorInt3 = CommonExtKt.colorInt(reviewDialogFragment, (int) R.color.transparent);
                        final int colorInt4 = CommonExtKt.colorInt(reviewDialogFragment, (int) R.color.transparent);
                        append.setClickSpan(new QMUITouchableSpan(colorInt, colorInt2, colorInt3, colorInt4) { // from class: com.movieboxpro.android.view.dialog.ReviewDialogFragment$initData$1$1$1
                            @Override // com.movieboxpro.android.view.widget.textview.QMUITouchableSpan
                            public void onSpanClick(View view) {
                            }
                        }).setForegroundColor(CommonExtKt.colorInt(reviewDialogFragment, (int) R.color.color_main_blue)).setFontSize(16, true);
                    }
                    i = R.color.white30_transparent;
                }
            }
            span.create();
        } else {
            SpanUtils with = SpanUtils.with(qMUISpanTouchFixTextView2);
            Intrinsics.checkNotNullExpressionValue(with, "with(content)");
            CommonExtKt.addText(with, "This review is deleted by user.", 16, R.color.white30_transparent).setItalic().create();
        }
        TextView textView = (TextView) helper.getView(R.id.tvLikeNum);
        Integer support = item.getSupport();
        if ((support == null ? 0 : support.intValue()) > 0) {
            CommonExtKt.visible(textView);
            textView.setText(String.valueOf(item.getSupport()));
        } else {
            CommonExtKt.gone(textView);
        }
        TextView textView2 = (TextView) helper.getView(R.id.tvReplyNum);
        Integer reply = item.getReply();
        if ((reply == null ? 0 : reply.intValue()) > 0) {
            CommonExtKt.visible(textView2);
            textView2.setText(String.valueOf(item.getReply()));
        } else {
            CommonExtKt.gone(textView2);
        }
        ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
        LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llLike);
        Integer support_status = item.getSupport_status();
        linearLayout.setSelected(support_status != null && support_status.intValue() == 1);
        final RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$initData$1$YhPsU-SW-6iLFOl-STGclkEewC4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean m1098invoke$lambda1;
                m1098invoke$lambda1 = ReviewDialogFragment$initData$1.m1098invoke$lambda1(RecyclerView.this, view, motionEvent);
                return m1098invoke$lambda1;
            }
        });
        List<ImageItem> img_list = item.getImg_list();
        if ((img_list == null || img_list.isEmpty()) || ((is_delete = item.is_delete()) != null && is_delete.intValue() == 1)) {
            CommonExtKt.gone(recyclerView);
            return;
        }
        CommonExtKt.visible(recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this.this$0.getContext(), 4));
        if (recyclerView.getTag() == null) {
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, CommonExtKt.dp2Px(10), false));
            recyclerView.setTag("added");
        }
        CommBaseAdapter commBaseAdapter = new CommBaseAdapter(R.layout.adapter_review_image_item, new ReviewDialogFragment$initData$1$imgAdapter$1(this.this$0), item.getImg_list());
        recyclerView.setAdapter(commBaseAdapter);
        final ArrayList arrayList = new ArrayList();
        for (ImageItem imageItem : item.getImg_list()) {
            String url = imageItem.getUrl();
            if (url == null) {
                url = "";
            }
            arrayList.add(url);
        }
        final ReviewDialogFragment reviewDialogFragment2 = this.this$0;
        commBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$ReviewDialogFragment$initData$1$cWuFQdeaCzl7MGCAKFu1xTviBmw
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                ReviewDialogFragment$initData$1.m1099invoke$lambda3(ReviewDialogFragment.this, arrayList, baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-1  reason: not valid java name */
    public static final boolean m1098invoke$lambda1(RecyclerView recyclerView, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(recyclerView, "$recyclerView");
        if (motionEvent.getAction() == 1) {
            recyclerView.performClick();
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-3  reason: not valid java name */
    public static final void m1099invoke$lambda3(ReviewDialogFragment this$0, ArrayList images, BaseQuickAdapter noName_0, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(images, "$images");
        Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
        Intrinsics.checkNotNullParameter(view, "view");
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
        this$0.toImageShow(i, imageView, images, imageView);
    }
}
