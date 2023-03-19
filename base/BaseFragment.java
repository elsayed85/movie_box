package com.movieboxpro.android.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.UrlChangeEvent;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.view.dialog.NoInternetDialog;
import com.movieboxpro.android.view.listener.ITabListener;
import com.movieboxpro.android.view.listener.ITitleController;
import com.movieboxpro.android.view.listener.IViewController;
import java.io.Serializable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment implements IViewController, ITitleController {
    protected Activity activity;
    protected App app;
    protected Context appContext;
    protected Context context;
    public ITabListener listener;
    protected FrameLayout mContent;
    protected View mTitleBar;
    protected ImageView mTitleLeftIconImg;
    protected TextView mTitleLeftText;
    protected RelativeLayout mTitleRightIcon;
    protected ImageView mTitleRightIconDot;
    protected ImageView mTitleRightIconImg;
    protected TextView mTitleRightText;
    protected TextView mTitleText;
    protected String name;
    protected IPresenter presenter;
    protected APIService service;
    protected boolean isViewInited = false;
    private boolean isAddIn = false;
    protected boolean loaded = false;
    private boolean isWarningShowed = false;
    protected String TAG = getClass().getSimpleName();

    protected boolean isRequireNetWork() {
        return true;
    }

    protected boolean isShowWarning() {
        return true;
    }

    protected boolean isSupportBack() {
        return false;
    }

    protected boolean isTitleBarShow() {
        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void onTryAgainClick() {
    }

    public void setListener(ITabListener iTabListener) {
        this.listener = iTabListener;
    }

    public String getName() {
        if (this.name == null) {
            this.name = getClass().getSimpleName();
        }
        return this.name;
    }

    public void setAddIn(boolean z) {
        this.isAddIn = z;
    }

    public void setName(String str) {
        if (this.name == null) {
            this.name = str;
            this.TAG += ":" + str;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MLog.d(this.TAG, "onCreate");
        this.service = Http.getService();
        initContext();
    }

    protected void initContext() {
        this.app = App.getInstance();
        this.appContext = App.getContext();
        this.context = getActivity();
        this.activity = getActivity();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        MLog.d(this.TAG, "onCreateView");
        View inflate = layoutInflater.inflate(R.layout.fragment_base, viewGroup, false);
        this.mContent = (FrameLayout) inflate.findViewById(R.id.fragment_base_content);
        EventBus.getDefault().register(this);
        View loadView = loadView(layoutInflater, this.mContent);
        initRootView(inflate, loadView);
        initTitleBar();
        ButterKnife.bind(this, loadView);
        initView();
        initPresenter();
        this.isViewInited = true;
        if (this.isAddIn) {
            if (isVisible()) {
                onVisible();
            }
        } else if (getUserVisibleHint()) {
            String str = this.TAG;
            MLog.d(str, "getUserVisibleHint:" + getUserVisibleHint());
            onVisible();
        }
        return inflate;
    }

    private void initRootView(View view, View view2) {
        this.mTitleBar = view.findViewById(R.id.fragment_base_title);
        this.mTitleLeftIconImg = (ImageView) view.findViewById(R.id.titlebar_left_icon_img);
        this.mTitleLeftText = (TextView) view.findViewById(R.id.titlebar_left_text);
        this.mTitleText = (TextView) view.findViewById(R.id.titlebar_title_text);
        this.mTitleRightText = (TextView) view.findViewById(R.id.titlebar_right_text);
        this.mTitleRightIcon = (RelativeLayout) view.findViewById(R.id.titlebar_right_icon);
        this.mTitleRightIconImg = (ImageView) view.findViewById(R.id.titlebar_right_icon_img);
        this.mTitleRightIconDot = (ImageView) view.findViewById(R.id.titlebar_right_icon_dot);
        this.mContent.removeAllViews();
        this.mContent.addView(view2);
    }

    private void initTitleBar() {
        if (isTitleBarShow()) {
            setTitleBar(true);
            if (isSupportBack()) {
                setTitleBack(true);
                return;
            } else {
                setTitleBack(false);
                return;
            }
        }
        setTitleBar(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBar(boolean z) {
        if (z) {
            this.mTitleBar.setVisibility(0);
        } else {
            this.mTitleBar.setVisibility(8);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitle(String str, View.OnClickListener onClickListener) {
        this.mTitleText.setVisibility(0);
        this.mTitleText.setText(str);
        if (onClickListener != null) {
            this.mTitleText.setClickable(true);
            this.mTitleText.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleText.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitle(String str) {
        setTitle(str, null);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBack(boolean z) {
        if (z) {
            this.mTitleLeftText.setVisibility(8);
            this.mTitleLeftIconImg.setVisibility(0);
            this.mTitleLeftIconImg.setImageResource(R.drawable.selector_titlebar_back);
            this.mTitleLeftIconImg.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseFragment.this.getActivity().finish();
                }
            });
            return;
        }
        this.mTitleLeftIconImg.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBackground(int i) {
        this.mTitleBar.setBackgroundColor(i);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleLeftText(String str, View.OnClickListener onClickListener) {
        if (isTitleBarShow()) {
            if (str != null) {
                this.mTitleLeftIconImg.setVisibility(8);
                this.mTitleLeftText.setVisibility(0);
                this.mTitleLeftText.setText(str);
                if (onClickListener != null) {
                    this.mTitleLeftText.setOnClickListener(onClickListener);
                    return;
                }
                return;
            }
            this.mTitleLeftText.setVisibility(8);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleLeftIcon(int i, View.OnClickListener onClickListener) {
        this.mTitleLeftIconImg.setVisibility(8);
        this.mTitleLeftIconImg.setVisibility(0);
        this.mTitleLeftIconImg.setImageResource(i);
        if (onClickListener != null) {
            this.mTitleLeftIconImg.setClickable(true);
            this.mTitleLeftIconImg.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleLeftIconImg.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String str, boolean z, View.OnClickListener onClickListener) {
        this.mTitleRightIcon.setVisibility(8);
        this.mTitleRightText.setVisibility(0);
        this.mTitleRightText.setText(str);
        if (z) {
            this.mTitleRightText.setBackgroundResource(R.drawable.bg_border_btn_white);
        } else {
            this.mTitleRightText.setBackgroundResource(R.drawable.bg_null);
        }
        if (onClickListener != null) {
            this.mTitleRightText.setClickable(true);
            this.mTitleRightText.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleRightText.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String str, View.OnClickListener onClickListener) {
        setTitleRightText(str, false, onClickListener);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightIcon(int i, boolean z, View.OnClickListener onClickListener) {
        this.mTitleRightText.setVisibility(8);
        this.mTitleRightIcon.setVisibility(0);
        this.mTitleRightIconImg.setImageResource(i);
        if (z) {
            this.mTitleRightIconDot.setVisibility(0);
        } else {
            this.mTitleRightIconDot.setVisibility(8);
        }
        if (onClickListener != null) {
            this.mTitleRightIcon.setClickable(true);
            this.mTitleRightIcon.setOnClickListener(onClickListener);
            return;
        }
        this.mTitleRightIcon.setClickable(false);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightIcon(int i, View.OnClickListener onClickListener) {
        setTitleRightIcon(i, false, onClickListener);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void clearTitleRightNew() {
        this.mTitleRightIconDot.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void clearTitleRight() {
        this.mTitleRightText.setVisibility(8);
        this.mTitleRightIcon.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        MLog.d(this.TAG, "onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MLog.d(this.TAG, "onResume");
        if (this.isViewInited) {
            if (this.isAddIn) {
                if (isVisible()) {
                    onVisible();
                }
            } else if (getUserVisibleHint()) {
                String str = this.TAG;
                MLog.d(str, "getUserVisibleHint:" + getUserVisibleHint());
                onVisible();
            }
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        MLog.d(this.TAG, "initPresenter");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public <T> void updateView(T t) {
        MLog.d(this.TAG, "updateView");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showToast(String str) {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToast(str);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showNoInternetDialog() {
        new NoInternetDialog(getContext(), new NoInternetDialog.OnButtonClickListener() { // from class: com.movieboxpro.android.base.BaseFragment.2
            @Override // com.movieboxpro.android.view.dialog.NoInternetDialog.OnButtonClickListener
            public void onClick() {
                BaseFragment.this.onTryAgainClick();
            }
        }).show();
    }

    public void showToast(int i) {
        showToast(getString(i));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showAlert(String str) {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showAlert(str);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showLoading() {
        MLog.d(this.TAG, "showLoading");
        FragmentActivity activity = getActivity();
        if (activity == null || !(activity instanceof IViewController)) {
            return;
        }
        ((IViewController) activity).showLoading();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading() {
        hideLoading(true);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading(boolean z) {
        MLog.d(this.TAG, "hideLoading");
        FragmentActivity activity = getActivity();
        if (activity instanceof IViewController) {
            ((IViewController) activity).hideLoading(z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        MLog.d(this.TAG, "onDownloadPause");
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MLog.d(this.TAG, "onStop");
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        MLog.d(this.TAG, "onDestroyView");
        EventBus.getDefault().unregister(this);
        this.loaded = false;
        this.isViewInited = false;
        IPresenter iPresenter = this.presenter;
        if (iPresenter != null) {
            iPresenter.detachView();
        }
        hideLoading();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        MLog.d(this.TAG, "onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        String str = this.TAG;
        MLog.d(str, "setUserVisibleHint:" + z);
        if (this.isViewInited) {
            if (getUserVisibleHint()) {
                onVisible();
            } else {
                onInVisible();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        String str = this.TAG;
        MLog.d(str, "onHiddenChanged:" + z);
        if (this.isViewInited) {
            if (!isHidden()) {
                onVisible();
            } else {
                onInVisible();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onVisible() {
        MLog.d(this.TAG, "onVisible");
    }

    protected void onInVisible() {
        MLog.d(this.TAG, "onInVisible");
        this.isWarningShowed = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void route(Class<? extends Activity> cls) {
        route(cls, (Bundle) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void route(Class<? extends Activity> cls, Bundle bundle) {
        Intent intent = new Intent(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void route(Class<? extends Activity> cls, int i) {
        route(cls, null, i);
    }

    protected void route(Class<? extends Activity> cls, Bundle bundle, int i) {
        Intent intent = new Intent(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void route(Class<? extends Activity> cls, Bundle bundle, View view, String str) {
        Intent intent = new Intent(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(this.activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, Pair.create(view, str)).toBundle());
            return;
        }
        startActivity(intent);
    }

    protected boolean getBooleanParam(String str, boolean z) {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getBoolean(str, z) : z;
    }

    protected String getStringParam(String str, String str2) {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getString(str, str2) : str2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getIntParam(String str, int i) {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getInt(str, i) : i;
    }

    protected <T extends Serializable> T getSerializable(String str, T t) {
        T t2;
        Bundle arguments = getArguments();
        return (arguments == null || (t2 = (T) arguments.getSerializable(str)) == null) ? t : t2;
    }

    protected <T extends Parcelable> T getParcelable(String str, T t) {
        T t2;
        Bundle arguments = getArguments();
        return (arguments == null || (t2 = (T) arguments.getParcelable(str)) == null) ? t : t2;
    }

    protected boolean isScreenPortrait() {
        MLog.d(this.TAG, "isScreenPortrait");
        return getResources().getConfiguration().orientation == 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isScreenLandscape() {
        MLog.d(this.TAG, "isScreenLandscape");
        return getResources().getConfiguration().orientation == 2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UrlChangeEvent urlChangeEvent) {
        this.service = Http.getService();
    }
}
