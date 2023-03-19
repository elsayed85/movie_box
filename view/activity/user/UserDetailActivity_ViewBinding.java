package com.movieboxpro.android.view.activity.user;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public class UserDetailActivity_ViewBinding implements Unbinder {
    private UserDetailActivity target;
    private View view7f090837;
    private View view7f090838;
    private View view7f090839;
    private View view7f09083a;
    private View view7f09083c;
    private View view7f090841;
    private View view7f090844;
    private View view7f090847;

    public UserDetailActivity_ViewBinding(UserDetailActivity userDetailActivity) {
        this(userDetailActivity, userDetailActivity.getWindow().getDecorView());
    }

    public UserDetailActivity_ViewBinding(final UserDetailActivity userDetailActivity, View view) {
        this.target = userDetailActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.user_detail_avatar, "field 'userDetailAvatar' and method 'onViewClicked'");
        userDetailActivity.userDetailAvatar = (CircleImageView) Utils.castView(findRequiredView, R.id.user_detail_avatar, "field 'userDetailAvatar'", CircleImageView.class);
        this.view7f090837 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.user_detail_describ, "field 'userDetailDescrib' and method 'onViewClicked'");
        userDetailActivity.userDetailDescrib = (TextView) Utils.castView(findRequiredView2, R.id.user_detail_describ, "field 'userDetailDescrib'", TextView.class);
        this.view7f090838 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        userDetailActivity.userUsernameMore = (TextView) Utils.findRequiredViewAsType(view, R.id.user_username_more, "field 'userUsernameMore'", TextView.class);
        userDetailActivity.userDeviceMyEail = (TextView) Utils.findRequiredViewAsType(view, R.id.user_device_myEail, "field 'userDeviceMyEail'", TextView.class);
        userDetailActivity.userDeviceInvate = (TextView) Utils.findRequiredViewAsType(view, R.id.user_invate_code_describ, "field 'userDeviceInvate'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.user_detail_more, "field 'userUserVipTime' and method 'onViewClicked'");
        userDetailActivity.userUserVipTime = (TextView) Utils.castView(findRequiredView3, R.id.user_detail_more, "field 'userUserVipTime'", TextView.class);
        this.view7f090839 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.user_invate_code, "field 'userInvate' and method 'onViewClicked'");
        userDetailActivity.userInvate = (ConstraintLayout) Utils.castView(findRequiredView4, R.id.user_invate_code, "field 'userInvate'", ConstraintLayout.class);
        this.view7f090844 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        userDetailActivity.userVip = (ImageView) Utils.findRequiredViewAsType(view, R.id.user_detail_vip, "field 'userVip'", ImageView.class);
        View findRequiredView5 = Utils.findRequiredView(view, R.id.user_order, "method 'onViewClicked'");
        this.view7f090847 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.user_email, "method 'onViewClicked'");
        this.view7f090841 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.user_device, "method 'onViewClicked'");
        this.view7f09083c = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.user_detail_signout, "method 'onViewClicked'");
        this.view7f09083a = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.user.UserDetailActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userDetailActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        UserDetailActivity userDetailActivity = this.target;
        if (userDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        userDetailActivity.userDetailAvatar = null;
        userDetailActivity.userDetailDescrib = null;
        userDetailActivity.userUsernameMore = null;
        userDetailActivity.userDeviceMyEail = null;
        userDetailActivity.userDeviceInvate = null;
        userDetailActivity.userUserVipTime = null;
        userDetailActivity.userInvate = null;
        userDetailActivity.userVip = null;
        this.view7f090837.setOnClickListener(null);
        this.view7f090837 = null;
        this.view7f090838.setOnClickListener(null);
        this.view7f090838 = null;
        this.view7f090839.setOnClickListener(null);
        this.view7f090839 = null;
        this.view7f090844.setOnClickListener(null);
        this.view7f090844 = null;
        this.view7f090847.setOnClickListener(null);
        this.view7f090847 = null;
        this.view7f090841.setOnClickListener(null);
        this.view7f090841 = null;
        this.view7f09083c.setOnClickListener(null);
        this.view7f09083c = null;
        this.view7f09083a.setOnClickListener(null);
        this.view7f09083a = null;
    }
}
