package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hi.dhl.jdatabinding.ActivityDataBindingDelegate;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.databinding.ActivityMyBlockBinding;
import com.movieboxpro.android.model.BlockedUser;
import com.movieboxpro.android.model.BlockedUserResponse;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.view.activity.MyBlockListActivity;
import com.movieboxpro.android.view.widget.UserAvatarView;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
/* compiled from: MyBlockListActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/MyBlockListActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "()V", "binding", "Lcom/movieboxpro/android/databinding/ActivityMyBlockBinding;", "getBinding", "()Lcom/movieboxpro/android/databinding/ActivityMyBlockBinding;", "binding$delegate", "Lcom/hi/dhl/jdatabinding/ActivityDataBindingDelegate;", "initData", "", "initListener", "initView", "BlockedUserListFragment", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyBlockListActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(MyBlockListActivity.class, "binding", "getBinding()Lcom/movieboxpro/android/databinding/ActivityMyBlockBinding;", 0))};
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final ActivityDataBindingDelegate binding$delegate = new ActivityDataBindingDelegate(ActivityMyBlockBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
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

    private final ActivityMyBlockBinding getBinding() {
        return (ActivityMyBlockBinding) this.binding$delegate.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
        getBinding().toolBar.llBack.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyBlockListActivity$xDohqPlPUbg9pYNcrlGWKuDfyBc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyBlockListActivity.m173initListener$lambda0(MyBlockListActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m173initListener$lambda0(MyBlockListActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        FragmentUtils.add(getSupportFragmentManager(), new BlockedUserListFragment(), (int) R.id.flContainer);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
        getBinding().toolBar.tvTitle.setText("Blocked Users");
    }

    /* compiled from: MyBlockListActivity.kt */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bH\u0014J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0003H\u0014J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u0014H\u0014J\u0018\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0002H\u0014J\b\u0010\u0018\u001a\u00020\u0019H\u0014J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/activity/MyBlockListActivity$BlockedUserListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/BlockedUser;", "Lcom/movieboxpro/android/model/BlockedUserResponse;", "()V", "addOnItemClickViews", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "blockUser", "uid", "", "getBundle", "arguments", "Landroid/os/Bundle;", "getData", "", "model", "getServiceData", "Lio/reactivex/Observable;", "initHolder", "helper", "item", "initItemLayout", "", "onItemChildClick", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class BlockedUserListFragment extends BaseListFragment<BlockedUser, BlockedUserResponse> {
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
            return R.layout.adapter_blocked_user;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment, com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
        public /* synthetic */ void onDestroyView() {
            super.onDestroyView();
            _$_clearFindViewByIdCache();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return CommonExtKt.request$default(Api.INSTANCE.getBlockUsers(this.mCurrentPage, this.mPageSize), null, 1, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<BlockedUser> getData(BlockedUserResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<BlockedUser> user_list = model.getUser_list();
            if (user_list == null) {
                user_list = new ArrayList<>();
            }
            return user_list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemChildClickListener onItemChildClick() {
            return new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.-$$Lambda$MyBlockListActivity$BlockedUserListFragment$tdtTvYZfpaKfFU_UhG6Ealp1jgU
                @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MyBlockListActivity.BlockedUserListFragment.m174onItemChildClick$lambda0(MyBlockListActivity.BlockedUserListFragment.this, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onItemChildClick$lambda-0  reason: not valid java name */
        public static final void m174onItemChildClick$lambda0(BlockedUserListFragment this$0, BaseQuickAdapter noName_0, View noName_1, int i) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
            Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
            this$0.blockUser(((BlockedUser) this$0.mAdapter.getItem(i)).getUid());
        }

        private final void blockUser(String str) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(CommonExtKt.request$default(Api.INSTANCE.blockUser(str, "delete"), null, 1, null), this), new MyBlockListActivity$BlockedUserListFragment$blockUser$1(this), null, new MyBlockListActivity$BlockedUserListFragment$blockUser$2(this), null, new MyBlockListActivity$BlockedUserListFragment$blockUser$3(this), 10, null);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void addOnItemClickViews(BaseQuickAdapter<BlockedUser, BaseViewHolder> adapter) {
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            adapter.addChildClickViewIds(R.id.tvUnblock);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
            this.mClass = BlockedUser.class;
            this.mPageClass = BlockedUserResponse.class;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, BlockedUser item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            helper.setText(R.id.tvName, item.getUsername());
            ((UserAvatarView) helper.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getUsername());
        }
    }
}
