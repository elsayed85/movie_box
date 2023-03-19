package com.movieboxpro.android.view.fragment.movielist;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.FragmentUtils;
/* loaded from: classes3.dex */
public abstract class BottomDialogFragment extends AppCompatDialogFragment {
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_done)
    TextView tvDone;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    Unbinder unbinder;

    protected abstract void doDoneClick();

    protected abstract Fragment setFragment();

    protected abstract String setTitle();

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    private int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.requestFeature(1);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), 17170445));
            int contextRect = getContextRect(getActivity());
            if (contextRect == 0) {
                contextRect = -1;
            }
            window.setLayout(-1, contextRect);
            attributes.dimAmount = 0.0f;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        View inflate = layoutInflater.inflate(R.layout.dialog_fragment_add_movie, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
    }

    private void initData() {
        this.tvTitle.setText(setTitle());
        FragmentUtils.add(getChildFragmentManager(), setFragment(), (int) R.id.frame_layout);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @OnClick({R.id.tv_cancel, R.id.tv_done})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_cancel) {
            dismiss();
        } else if (id != R.id.tv_done) {
        } else {
            doDoneClick();
        }
    }
}
