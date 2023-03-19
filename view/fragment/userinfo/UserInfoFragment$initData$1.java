package com.movieboxpro.android.view.fragment.userinfo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.livedata.IncognitoModeLiveData;
import com.movieboxpro.android.model.DrawerItem;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: UserInfoFragment.kt */
@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0014¨\u0006\b"}, d2 = {"com/movieboxpro/android/view/fragment/userinfo/UserInfoFragment$initData$1", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/DrawerItem;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "convert", "", "holder", "item", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserInfoFragment$initData$1 extends BaseQuickAdapter<DrawerItem, BaseViewHolder> {
    final /* synthetic */ ArrayList<DrawerItem> $drawerItems;
    final /* synthetic */ UserInfoFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserInfoFragment$initData$1(ArrayList<DrawerItem> arrayList, UserInfoFragment userInfoFragment) {
        super(R.layout.adapter_drawer_item, arrayList);
        this.$drawerItems = arrayList;
        this.this$0 = userInfoFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, DrawerItem item) {
        boolean z;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) holder.getView(R.id.imageView);
        if (item.getType() == 8) {
            z = this.this$0.haveNewMsg;
            if (z) {
                CommonExtKt.visible(imageView);
            } else {
                CommonExtKt.gone(imageView);
            }
        } else {
            CommonExtKt.gone(imageView);
        }
        String title = item.getTitle();
        if (title == null) {
            title = "";
        }
        holder.setText(R.id.tvTitle, title);
        View view = holder.getView(R.id.line);
        if (item.isLast()) {
            CommonExtKt.gone(view);
        } else {
            CommonExtKt.visible(view);
        }
        TextView textView = (TextView) holder.getView(R.id.tvContent);
        String content = item.getContent();
        if (content == null || StringsKt.isBlank(content)) {
            CommonExtKt.gone(textView);
        } else {
            CommonExtKt.visible(textView);
            textView.setText(item.getContent());
        }
        final ImageView imageView2 = (ImageView) holder.getView(R.id.ivSwitch);
        if (item.getType() == 14) {
            CommonExtKt.visible(imageView2);
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
                imageView2.setImageResource(R.mipmap.ic_switch_checked);
            } else {
                imageView2.setImageResource(R.mipmap.ic_switch_unchecked);
            }
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$initData$1$wjboqke1x6-rpWJf7b6eOY_RgvE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    UserInfoFragment$initData$1.m1301convert$lambda2(UserInfoFragment$initData$1.this, imageView2, view2);
                }
            });
            return;
        }
        CommonExtKt.gone(imageView2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-2  reason: not valid java name */
    public static final void m1301convert$lambda2(UserInfoFragment$initData$1 this$0, final ImageView switchCompat, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(switchCompat, "$switchCompat");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
            new MsgHintDialog.Builder(this$0.getContext()).setContent("Are you sure to turn off private mode? playback history and search history will be recorded and displayed").setCancelable(false).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$initData$1$s1273EIbEJOcb73Lb4dn90AxubU
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    UserInfoFragment$initData$1.m1302convert$lambda2$lambda0(switchCompat);
                }
            }).create().show();
        } else {
            new MsgHintDialog.Builder(this$0.getContext()).setContent("Are you sure to turn on private mode? App won't record playback history and search history").setCancelable(false).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.userinfo.-$$Lambda$UserInfoFragment$initData$1$uTrBHnSyRr0EibBr0TNrKK5aJrA
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    UserInfoFragment$initData$1.m1303convert$lambda2$lambda1(switchCompat);
                }
            }).create().show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-2$lambda-0  reason: not valid java name */
    public static final void m1302convert$lambda2$lambda0(ImageView switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "$switchCompat");
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.Incognito_Mode, false);
        IncognitoModeLiveData.Companion.get().postValue(false);
        switchCompat.setImageResource(R.mipmap.ic_switch_unchecked);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-2$lambda-1  reason: not valid java name */
    public static final void m1303convert$lambda2$lambda1(ImageView switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "$switchCompat");
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.Incognito_Mode, true);
        IncognitoModeLiveData.Companion.get().postValue(true);
        switchCompat.setImageResource(R.mipmap.ic_switch_checked);
    }
}
