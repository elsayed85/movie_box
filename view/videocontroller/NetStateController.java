package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.movieboxpro.android.R;
/* loaded from: classes3.dex */
public class NetStateController extends FrameLayout {
    public onclick mCallBack;
    private Context mContext;

    /* loaded from: classes3.dex */
    public interface onclick {
        void clickYse();
    }

    public NetStateController(Context context) {
        super(context);
        init(context);
    }

    public NetStateController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public NetStateController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.layout_network_state, this);
        ((TextView) findViewById(R.id.network_yes)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.NetStateController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NetStateController.this.mCallBack != null) {
                    NetStateController.this.mCallBack.clickYse();
                }
            }
        });
        ((TextView) findViewById(R.id.network_no)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.NetStateController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NetStateController.this.setVisibility(8);
            }
        });
    }

    public NetStateController setYes(onclick onclickVar) {
        this.mCallBack = onclickVar;
        return this;
    }
}
