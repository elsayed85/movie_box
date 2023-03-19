package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.movieboxpro.android.R;
import com.movieboxpro.android.utils.DensityUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class BottomSheetListDialog extends Dialog {
    private static final String TAG = "BottomSheetListDialog";
    private static final int mAnimationDuration = 200;
    private View mContentView;
    private boolean mIsAnimating;
    private OnBottomSheetShowListener mOnBottomSheetShowListener;

    /* loaded from: classes3.dex */
    public interface OnBottomSheetShowListener {
        void onShow();
    }

    public BottomSheetListDialog(Context context) {
        super(context, R.style.BottomSheet);
        this.mIsAnimating = false;
    }

    public void setOnBottomSheetShowListener(OnBottomSheetShowListener onBottomSheetShowListener) {
        this.mOnBottomSheetShowListener = onBottomSheetShowListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.height = -2;
        attributes.gravity = 81;
        int screenWidth = DensityUtils.getScreenWidth(getContext());
        int screenHeight = DensityUtils.getScreenHeight(getContext());
        if (screenWidth >= screenHeight) {
            screenWidth = screenHeight;
        }
        attributes.width = screenWidth;
        getWindow().setAttributes(attributes);
        setCanceledOnTouchOutside(true);
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        View inflate = LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null);
        this.mContentView = inflate;
        super.setContentView(inflate);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        this.mContentView = view;
        super.setContentView(view, layoutParams);
    }

    public View getContentView() {
        return this.mContentView;
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        this.mContentView = view;
        super.setContentView(view);
    }

    private void animateUp() {
        if (this.mContentView == null) {
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setDuration(200L);
        animationSet.setFillAfter(true);
        this.mContentView.startAnimation(animationSet);
    }

    private void animateDown() {
        if (this.mContentView == null) {
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setInterpolator(new DecelerateInterpolator());
        animationSet.setDuration(200L);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.dialog.BottomSheetListDialog.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                BottomSheetListDialog.this.mIsAnimating = true;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                BottomSheetListDialog.this.mIsAnimating = false;
                BottomSheetListDialog.this.mContentView.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.BottomSheetListDialog.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            BottomSheetListDialog.super.dismiss();
                        } catch (Exception unused) {
                        }
                    }
                });
            }
        });
        this.mContentView.startAnimation(animationSet);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        animateUp();
        OnBottomSheetShowListener onBottomSheetShowListener = this.mOnBottomSheetShowListener;
        if (onBottomSheetShowListener != null) {
            onBottomSheetShowListener.onShow();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.mIsAnimating) {
            return;
        }
        animateDown();
    }

    /* loaded from: classes3.dex */
    public static class BottomListSheetBuilder {
        private BaseAdapter mAdapter;
        private int mCheckedIndex;
        private ListView mContainerView;
        private Context mContext;
        private BottomSheetListDialog mDialog;
        private List<View> mHeaderViews;
        private List<BottomSheetListItemData> mItems;
        private boolean mNeedRightMark;
        private DialogInterface.OnDismissListener mOnBottomDialogDismissListener;
        private OnSheetItemClickListener mOnSheetItemClickListener;
        private String mTitle;
        private TextView mTitleTv;

        /* loaded from: classes3.dex */
        public interface OnSheetItemClickListener {
            void onClick(BottomSheetListDialog bottomSheetListDialog, View view, int i, String str);
        }

        protected int getContentViewLayoutId() {
            return R.layout.bottom_sheet_list;
        }

        public BottomListSheetBuilder(Context context) {
            this(context, false);
        }

        public BottomListSheetBuilder(Context context, boolean z) {
            this.mContext = context;
            this.mItems = new ArrayList();
            this.mHeaderViews = new ArrayList();
            this.mNeedRightMark = z;
        }

        public BottomListSheetBuilder setCheckedIndex(int i) {
            this.mCheckedIndex = i;
            return this;
        }

        public BottomListSheetBuilder addItem(String str) {
            this.mItems.add(new BottomSheetListItemData(str, str));
            return this;
        }

        public BottomListSheetBuilder addItems(List<String> list) {
            for (String str : list) {
                this.mItems.add(new BottomSheetListItemData(str, str));
            }
            return this;
        }

        public BottomListSheetBuilder addItem(Drawable drawable, String str) {
            this.mItems.add(new BottomSheetListItemData(drawable, str, str));
            return this;
        }

        public BottomListSheetBuilder addItem(String str, String str2) {
            this.mItems.add(new BottomSheetListItemData(str, str2));
            return this;
        }

        public BottomListSheetBuilder addItem(int i, String str, String str2) {
            this.mItems.add(new BottomSheetListItemData(i != 0 ? ContextCompat.getDrawable(this.mContext, i) : null, str, str2));
            return this;
        }

        public BottomListSheetBuilder addItem(int i, String str, String str2, boolean z) {
            this.mItems.add(new BottomSheetListItemData(i != 0 ? ContextCompat.getDrawable(this.mContext, i) : null, str, str2, z));
            return this;
        }

        public BottomListSheetBuilder addItem(int i, String str, String str2, boolean z, boolean z2) {
            this.mItems.add(new BottomSheetListItemData(i != 0 ? ContextCompat.getDrawable(this.mContext, i) : null, str, str2, z, z2));
            return this;
        }

        public BottomListSheetBuilder setOnSheetItemClickListener(OnSheetItemClickListener onSheetItemClickListener) {
            this.mOnSheetItemClickListener = onSheetItemClickListener;
            return this;
        }

        public BottomListSheetBuilder setOnBottomDialogDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            this.mOnBottomDialogDismissListener = onDismissListener;
            return this;
        }

        public BottomListSheetBuilder addHeaderView(View view) {
            if (view != null) {
                this.mHeaderViews.add(view);
            }
            return this;
        }

        public BottomListSheetBuilder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public BottomListSheetBuilder setTitle(int i) {
            this.mTitle = this.mContext.getResources().getString(i);
            return this;
        }

        public BottomSheetListDialog build() {
            this.mDialog = new BottomSheetListDialog(this.mContext);
            this.mDialog.setContentView(buildViews(), new ViewGroup.LayoutParams(-1, -2));
            DialogInterface.OnDismissListener onDismissListener = this.mOnBottomDialogDismissListener;
            if (onDismissListener != null) {
                this.mDialog.setOnDismissListener(onDismissListener);
            }
            return this.mDialog;
        }

        private View buildViews() {
            View inflate = View.inflate(this.mContext, getContentViewLayoutId(), null);
            this.mTitleTv = (TextView) inflate.findViewById(R.id.title);
            this.mContainerView = (ListView) inflate.findViewById(R.id.listview);
            String str = this.mTitle;
            if (str != null && str.length() != 0) {
                this.mTitleTv.setVisibility(0);
                this.mTitleTv.setText(this.mTitle);
            } else {
                this.mTitleTv.setVisibility(8);
            }
            if (this.mHeaderViews.size() > 0) {
                for (View view : this.mHeaderViews) {
                    this.mContainerView.addHeaderView(view);
                }
            }
            if (needToScroll()) {
                this.mContainerView.getLayoutParams().height = getListMaxHeight();
                this.mDialog.setOnBottomSheetShowListener(new OnBottomSheetShowListener() { // from class: com.movieboxpro.android.view.dialog.BottomSheetListDialog.BottomListSheetBuilder.1
                    @Override // com.movieboxpro.android.view.dialog.BottomSheetListDialog.OnBottomSheetShowListener
                    public void onShow() {
                        BottomListSheetBuilder.this.mContainerView.setSelection(BottomListSheetBuilder.this.mCheckedIndex);
                    }
                });
            }
            ListAdapter listAdapter = new ListAdapter();
            this.mAdapter = listAdapter;
            this.mContainerView.setAdapter((android.widget.ListAdapter) listAdapter);
            return inflate;
        }

        private boolean needToScroll() {
            int size = this.mItems.size() * DensityUtils.dp2px(this.mContext, 40.0f);
            if (this.mHeaderViews.size() > 0) {
                for (View view : this.mHeaderViews) {
                    if (view.getMeasuredHeight() == 0) {
                        view.measure(0, 0);
                    }
                    size += view.getMeasuredHeight();
                }
            }
            if (this.mTitleTv != null && !TextUtils.isEmpty(this.mTitle)) {
                size += DensityUtils.dp2px(this.mContext, 40.0f);
            }
            return size > getListMaxHeight();
        }

        protected int getListMaxHeight() {
            double screenHeight = DensityUtils.getScreenHeight(this.mContext);
            Double.isNaN(screenHeight);
            return (int) (screenHeight * 0.5d);
        }

        public void notifyDataSetChanged() {
            BaseAdapter baseAdapter = this.mAdapter;
            if (baseAdapter != null) {
                baseAdapter.notifyDataSetChanged();
            }
            if (needToScroll()) {
                this.mContainerView.getLayoutParams().height = getListMaxHeight();
                this.mContainerView.setSelection(this.mCheckedIndex);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static class BottomSheetListItemData {
            boolean hasRedPoint;
            Drawable image;
            boolean isDisabled;
            String tag;
            String text;

            public BottomSheetListItemData(String str, String str2) {
                this.image = null;
                this.tag = "";
                this.hasRedPoint = false;
                this.isDisabled = false;
                this.text = str;
                this.tag = str2;
            }

            public BottomSheetListItemData(Drawable drawable, String str, String str2) {
                this.image = null;
                this.tag = "";
                this.hasRedPoint = false;
                this.isDisabled = false;
                this.image = drawable;
                this.text = str;
                this.tag = str2;
            }

            public BottomSheetListItemData(Drawable drawable, String str, String str2, boolean z) {
                this.image = null;
                this.tag = "";
                this.hasRedPoint = false;
                this.isDisabled = false;
                this.image = drawable;
                this.text = str;
                this.tag = str2;
                this.hasRedPoint = z;
            }

            public BottomSheetListItemData(Drawable drawable, String str, String str2, boolean z, boolean z2) {
                this.image = null;
                this.tag = "";
                this.hasRedPoint = false;
                this.isDisabled = false;
                this.image = drawable;
                this.text = str;
                this.tag = str2;
                this.hasRedPoint = z;
                this.isDisabled = z2;
            }
        }

        /* loaded from: classes3.dex */
        private static class ViewHolder {
            ImageView imageView;
            View markView;
            View redPoint;
            TextView textView;

            private ViewHolder() {
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public class ListAdapter extends BaseAdapter {
            @Override // android.widget.Adapter
            public long getItemId(int i) {
                return 0L;
            }

            private ListAdapter() {
            }

            @Override // android.widget.Adapter
            public int getCount() {
                return BottomListSheetBuilder.this.mItems.size();
            }

            @Override // android.widget.Adapter
            public BottomSheetListItemData getItem(int i) {
                return (BottomSheetListItemData) BottomListSheetBuilder.this.mItems.get(i);
            }

            @Override // android.widget.Adapter
            public View getView(final int i, View view, ViewGroup viewGroup) {
                final ViewHolder viewHolder;
                final BottomSheetListItemData item = getItem(i);
                if (view == null) {
                    view = LayoutInflater.from(BottomListSheetBuilder.this.mContext).inflate(R.layout.bottom_sheet_list_item, viewGroup, false);
                    viewHolder = new ViewHolder();
                    viewHolder.imageView = (ImageView) view.findViewById(R.id.bottom_dialog_list_item_img);
                    viewHolder.textView = (TextView) view.findViewById(R.id.bottom_dialog_list_item_title);
                    viewHolder.markView = view.findViewById(R.id.bottom_dialog_list_item_mark_view_stub);
                    viewHolder.redPoint = view.findViewById(R.id.bottom_dialog_list_item_point);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                if (item.image != null) {
                    viewHolder.imageView.setVisibility(0);
                    viewHolder.imageView.setImageDrawable(item.image);
                } else {
                    viewHolder.imageView.setVisibility(8);
                }
                viewHolder.textView.setText(item.text);
                if (item.hasRedPoint) {
                    viewHolder.redPoint.setVisibility(0);
                } else {
                    viewHolder.redPoint.setVisibility(8);
                }
                if (item.isDisabled) {
                    viewHolder.textView.setEnabled(false);
                    view.setEnabled(false);
                } else {
                    viewHolder.textView.setEnabled(true);
                    view.setEnabled(true);
                }
                if (BottomListSheetBuilder.this.mNeedRightMark) {
                    if (viewHolder.markView instanceof ViewStub) {
                        viewHolder.markView = ((ViewStub) viewHolder.markView).inflate();
                    }
                    if (BottomListSheetBuilder.this.mCheckedIndex == i) {
                        viewHolder.markView.setVisibility(0);
                    } else {
                        viewHolder.markView.setVisibility(8);
                    }
                } else {
                    viewHolder.markView.setVisibility(8);
                }
                view.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.BottomSheetListDialog.BottomListSheetBuilder.ListAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (item.hasRedPoint) {
                            item.hasRedPoint = false;
                            viewHolder.redPoint.setVisibility(8);
                        }
                        if (BottomListSheetBuilder.this.mNeedRightMark) {
                            BottomListSheetBuilder.this.setCheckedIndex(i);
                            ListAdapter.this.notifyDataSetChanged();
                        }
                        if (BottomListSheetBuilder.this.mOnSheetItemClickListener != null) {
                            BottomListSheetBuilder.this.mOnSheetItemClickListener.onClick(BottomListSheetBuilder.this.mDialog, view2, i, item.tag);
                        }
                    }
                });
                return view;
            }
        }
    }
}
