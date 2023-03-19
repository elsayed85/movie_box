package com.movieboxpro.android.view.activity.settings;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes3.dex */
public class PayActivity_ViewBinding implements Unbinder {
    private PayActivity target;
    private View view7f09029b;
    private View view7f09071c;
    private View view7f0907c2;
    private View view7f09086c;

    public PayActivity_ViewBinding(PayActivity payActivity) {
        this(payActivity, payActivity.getWindow().getDecorView());
    }

    public PayActivity_ViewBinding(final PayActivity payActivity, View view) {
        this.target = payActivity;
        payActivity.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.frameLayout, "field 'frameLayout'", FrameLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.vip_describ_purchase, "field 'vipPurchase' and method 'click'");
        payActivity.vipPurchase = (ImageView) Utils.castView(findRequiredView, R.id.vip_describ_purchase, "field 'vipPurchase'", ImageView.class);
        this.view7f09086c = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.PayActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                payActivity.click();
            }
        });
        payActivity.constraintLayout = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.vip_payinfo, "field 'constraintLayout'", ConstraintLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.ivAvatar, "field 'avatar' and method 'onLoginClick'");
        payActivity.avatar = (CircleImageView) Utils.castView(findRequiredView2, R.id.ivAvatar, "field 'avatar'", CircleImageView.class);
        this.view7f09029b = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.PayActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                payActivity.onLoginClick();
            }
        });
        payActivity.ivVip = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivVip, "field 'ivVip'", ImageView.class);
        View findRequiredView3 = Utils.findRequiredView(view, R.id.tvUsername, "field 'tvUserName' and method 'onLoginClick'");
        payActivity.tvUserName = (TextView) Utils.castView(findRequiredView3, R.id.tvUsername, "field 'tvUserName'", TextView.class);
        this.view7f0907c2 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.PayActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                payActivity.onLoginClick();
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, R.id.tvEmail, "field 'tvEmail' and method 'onLoginClick'");
        payActivity.tvEmail = (TextView) Utils.castView(findRequiredView4, R.id.tvEmail, "field 'tvEmail'", TextView.class);
        this.view7f09071c = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.movieboxpro.android.view.activity.settings.PayActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                payActivity.onLoginClick();
            }
        });
        payActivity.rvUidNum = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rvUidNum, "field 'rvUidNum'", RecyclerView.class);
        payActivity.tvExpireDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tvExpireDate, "field 'tvExpireDate'", TextView.class);
        payActivity.tvNextBill = (TextView) Utils.findRequiredViewAsType(view, R.id.tvNextBill, "field 'tvNextBill'", TextView.class);
        payActivity.ivCardBg = (ImageView) Utils.findRequiredViewAsType(view, R.id.ivCardBg, "field 'ivCardBg'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PayActivity payActivity = this.target;
        if (payActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        payActivity.frameLayout = null;
        payActivity.vipPurchase = null;
        payActivity.constraintLayout = null;
        payActivity.avatar = null;
        payActivity.ivVip = null;
        payActivity.tvUserName = null;
        payActivity.tvEmail = null;
        payActivity.rvUidNum = null;
        payActivity.tvExpireDate = null;
        payActivity.tvNextBill = null;
        payActivity.ivCardBg = null;
        this.view7f09086c.setOnClickListener(null);
        this.view7f09086c = null;
        this.view7f09029b.setOnClickListener(null);
        this.view7f09029b = null;
        this.view7f0907c2.setOnClickListener(null);
        this.view7f0907c2 = null;
        this.view7f09071c.setOnClickListener(null);
        this.view7f09071c = null;
    }
}
