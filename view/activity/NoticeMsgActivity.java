package com.movieboxpro.android.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.NoticeMsg;
import com.movieboxpro.android.model.NoticeMsgResponse;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.activity.NoticeMsgActivity;
import io.reactivex.Observable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: NoticeMsgActivity.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/NoticeMsgActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "()V", "getLayoutResId", "", "initData", "", "initListener", "initView", "NoticeListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NoticeMsgActivity extends BaseSimpleActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_notice_msg;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.ll_back)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$NoticeMsgActivity$nPlkvAesuMCK7tj8lS4kZAb7y5Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NoticeMsgActivity.m184initListener$lambda0(NoticeMsgActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tv_title)).setText("Notification");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m184initListener$lambda0(NoticeMsgActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        FragmentUtils.add(getSupportFragmentManager(), new NoticeListFragment(), (int) R.id.flContainer);
    }

    /* compiled from: NoticeMsgActivity.kt */
    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0014J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0014H\u0002¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/activity/NoticeMsgActivity$NoticeListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/NoticeMsg;", "Lcom/movieboxpro/android/model/NoticeMsgResponse;", "()V", "getBundle", "", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "initItemLayout", "", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "readMsg", "position", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class NoticeListFragment extends BaseListFragment<NoticeMsg, NoticeMsgResponse> {
        public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

        public void _$_clearFindViewByIdCache() {
            this._$_findViewCache.clear();
        }

        public View _$_findCachedViewById(int i) {
            View findViewById;
            Map<Integer, View> map = this._$_findViewCache;
            View view = map.get(Integer.valueOf(i));
            if (view == null) {
                View view2 = getView();
                if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                    return null;
                }
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return view;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_notice_msg;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.Companion.post("User_activity_list").param("page", Integer.valueOf(this.mCurrentPage)).param("pagelimit", Integer.valueOf(this.mPageSize)).asRequest();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            int colorInt = CommonExtKt.colorInt(this, (int) R.color.white_70alpha);
            StickyDecoration build = StickyDecoration.Builder.init(new GroupListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$NoticeMsgActivity$NoticeListFragment$BZwI2s87Z8xWCfUmpv6UTaD7538
                @Override // com.gavin.com.library.listener.GroupListener
                public final String getGroupName(int i) {
                    String m185initRecyclerView$lambda0;
                    m185initRecyclerView$lambda0 = NoticeMsgActivity.NoticeListFragment.m185initRecyclerView$lambda0(NoticeMsgActivity.NoticeListFragment.this, i);
                    return m185initRecyclerView$lambda0;
                }
            }).setGroupBackground(colorInt).setGroupTextColor(CommonExtKt.colorInt(this, (int) R.color.black)).setGroupTextSize(CommonExtKt.dp2Px(13)).setDivideHeight(0).setGroupHeight(CommonExtKt.dp2Px(28)).setTextSideMargin(CommonExtKt.dp2Px(10)).build();
            Intrinsics.checkNotNull(build);
            recyclerView.addItemDecoration(build);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: initRecyclerView$lambda-0  reason: not valid java name */
        public static final String m185initRecyclerView$lambda0(NoticeListFragment this$0, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            NoticeMsg noticeMsg = (NoticeMsg) this$0.mAdapter.getItemOrNull(i);
            if (noticeMsg != null) {
                return TimeUtils.INSTANCE.formatRecentFileTime(noticeMsg.getAdd_time() * 1000);
            }
            return null;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = NoticeMsg.class;
            this.mPageClass = NoticeMsgResponse.class;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$NoticeMsgActivity$NoticeListFragment$TTA2ZymFSsNwfWzWzC9pp7fyvvM
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    NoticeMsgActivity.NoticeListFragment.m186onItemClick$lambda1(NoticeMsgActivity.NoticeListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemClick$lambda-1  reason: not valid java name */
        public static final void m186onItemClick$lambda1(NoticeListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            NoticeMsg item = (NoticeMsg) this$0.mAdapter.getItem(i);
            String comment_type = item.getData().getComment_type();
            if (comment_type != null) {
                int hashCode = comment_type.hashCode();
                if (hashCode != 3714) {
                    if (hashCode != 92645877) {
                        if (hashCode != 104087344) {
                            if (hashCode == 1879474642 && comment_type.equals("playlist")) {
                                ReplyDetailActivity.Companion.start(this$0.getContext(), item.getData().getPid(), 3, item.getData().getComment_id(), null);
                            }
                        } else if (comment_type.equals("movie")) {
                            ReplyDetailActivity.Companion.start(this$0.getContext(), item.getData().getMid(), 1, item.getData().getComment_id(), null);
                        }
                    } else if (comment_type.equals("actor")) {
                        ReplyDetailActivity.Companion.start(this$0.getContext(), item.getData().getActor_id(), 4, item.getData().getComment_id(), null);
                    }
                } else if (comment_type.equals("tv")) {
                    ReplyDetailActivity.Companion.start(this$0.getContext(), item.getData().getMid(), 2, item.getData().getComment_id(), null);
                }
            }
            if (item.getIs_read() == 0) {
                Intrinsics.checkNotNullExpressionValue(item, "item");
                this$0.readMsg(item, i);
            }
        }

        private final void readMsg(NoticeMsg noticeMsg, int i) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.Companion.post("User_activity_read").param("id", noticeMsg.getId()).asRequest(), this), new NoticeMsgActivity$NoticeListFragment$readMsg$1(this), null, null, null, new NoticeMsgActivity$NoticeListFragment$readMsg$2(noticeMsg, this, i), 14, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<NoticeMsg> getData(NoticeMsgResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            List<NoticeMsg> list = model.getList();
            Intrinsics.checkNotNullExpressionValue(list, "model.list");
            return list;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, NoticeMsg item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = (TextView) helper.getView(R.id.tvContent);
            String comment = item.getData().getComment();
            if (comment == null || StringsKt.isBlank(comment)) {
                CommonExtKt.gone(textView);
            } else {
                CommonExtKt.visible(textView);
                textView.setText(item.getData().getComment());
            }
            helper.setText(R.id.tvName, item.getData().getUsername());
            helper.setText(R.id.tvNote, item.getDesc());
            helper.setText(R.id.tvTime, TimeUtils.formatTime(item.getAdd_time() * 1000));
            GlideUtils.loadWithCircle(getContext(), item.getData().getFrom_user_avatar(), (ImageView) helper.getView(R.id.ivAvatar), R.mipmap.ic_no_login_avatar);
            View view = helper.getView(R.id.viewDot);
            if (item.getIs_read() == 1) {
                CommonExtKt.gone(view);
            } else {
                CommonExtKt.visible(view);
            }
        }
    }
}
