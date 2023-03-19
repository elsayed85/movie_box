package com.movieboxpro.android.view.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.adorkable.iosdialog.ActionSheetDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.listener.OnHistoryItemLongClickListener;
import com.movieboxpro.android.model.HistoryModel;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.detail.impl.MovieDetailActivity;
import com.movieboxpro.android.view.activity.detail.impl.TvDetailActivity;
import com.movieboxpro.android.view.activity.settings.HistoryActivity;
import com.movieboxpro.android.view.activity.settings.HistoryContract;
import io.reactivex.Observable;
import java.util.List;
/* loaded from: classes3.dex */
public class HistoryActivity extends BaseMvpActivity<HistoryPresenter> implements HistoryContract.View, OnHistoryItemLongClickListener {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    private HistoryListFragment historyListFragment;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private String uid = App.getUserData().uid_v2;
    private int deletePosition = -1;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_history;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, HistoryActivity.class));
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        this.tvTitle.setText("History");
        this.tvRight.setVisibility(0);
        this.tvRight.setText("Clear");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        requestData();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public HistoryPresenter bindPresenter() {
        return new HistoryPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        this.historyListFragment = new HistoryListFragment();
        FragmentUtils.add(getSupportFragmentManager(), this.historyListFragment, (int) R.id.frame_layout);
    }

    @OnClick({R.id.ll_back, R.id.ll_right})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll_back) {
            finish();
        } else if (id != R.id.ll_right) {
        } else {
            ((HistoryPresenter) this.mPresenter).clearHistory(this.uid);
        }
    }

    @Override // com.movieboxpro.android.view.activity.settings.HistoryContract.View
    public void clearHistoryResult(boolean z, String str) {
        if (z) {
            this.historyListFragment.refreshData();
        } else {
            ToastUtils.showShort("clear fail ");
        }
    }

    @Override // com.movieboxpro.android.view.activity.settings.HistoryContract.View
    public void deleteHistoryResult(boolean z, String str) {
        if (z) {
            this.historyListFragment.deleteItem(this.deletePosition);
        } else {
            ToastUtils.showShort("delete fail");
        }
    }

    @Override // com.movieboxpro.android.listener.OnHistoryItemLongClickListener
    public void onHistoryItemLongClick(final String str, int i, final int i2) {
        this.deletePosition = i;
        new ActionSheetDialog(this).builder().setCancelable(true).setCanceledOnTouchOutside(true).addSheetItem("Delete", ActionSheetDialog.SheetItemColor.White, new ActionSheetDialog.OnSheetItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$HistoryActivity$pB344Nq7958ZWV48cF0pbvkWKRs
            @Override // com.adorkable.iosdialog.ActionSheetDialog.OnSheetItemClickListener
            public final void onClick(int i3) {
                HistoryActivity.this.lambda$onHistoryItemLongClick$0$HistoryActivity(str, i2, i3);
            }
        }).show();
    }

    public /* synthetic */ void lambda$onHistoryItemLongClick$0$HistoryActivity(String str, int i, int i2) {
        ((HistoryPresenter) this.mPresenter).deleteHistory(this.uid, str, i);
    }

    /* loaded from: classes3.dex */
    public static class HistoryListFragment extends BaseListFragment<HistoryModel, String> {
        private OnHistoryItemLongClickListener listener;
        private String uid = App.getUserData().uid_v2;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle bundle) {
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_history_item;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isOpenLoadMore() {
            return true;
        }

        @Override // androidx.fragment.app.Fragment
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnHistoryItemLongClickListener) {
                this.listener = (OnHistoryItemLongClickListener) context;
            }
        }

        public void deleteItem(int i) {
            this.mAdapter.removeAt(i);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void doSomethingWithData(List<HistoryModel> list) {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false)) {
                list.clear();
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            this.mClass = HistoryModel.class;
            return Http.getService().MovieRecord(API.BASE_URL, API.SETTING.MOVIERECORD, this.uid, "get", "", String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize));
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$HistoryActivity$HistoryListFragment$dAAIWcLG3vK_dqMhj72EgzBcxws
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    HistoryActivity.HistoryListFragment.this.lambda$onItemClick$0$HistoryActivity$HistoryListFragment(baseQuickAdapter, view, i);
                }
            };
        }

        public /* synthetic */ void lambda$onItemClick$0$HistoryActivity$HistoryListFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            HistoryModel historyModel = (HistoryModel) this.mAdapter.getItem(i);
            if (historyModel != null) {
                if (historyModel.getBox_type() == 2) {
                    Bundle build = ParamsUtils.newBuilder().addParam(TvDetailActivity.TV_TID, String.valueOf(historyModel.getId())).addParam("tv_poster", historyModel.getPoster()).build();
                    Intent intent = new Intent(getContext(), TvDetailActivity.class);
                    intent.putExtras(build);
                    startActivity(intent);
                    return;
                }
                MovieDetailActivity.start(getContext(), String.valueOf(historyModel.getId()), historyModel.getPoster());
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemLongClickListener onLongClick() {
            return new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.settings.-$$Lambda$HistoryActivity$HistoryListFragment$zAfHmWmOTfIiqtmG7E6vgHAGtzQ
                @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    return HistoryActivity.HistoryListFragment.this.lambda$onLongClick$1$HistoryActivity$HistoryListFragment(baseQuickAdapter, view, i);
                }
            };
        }

        public /* synthetic */ boolean lambda$onLongClick$1$HistoryActivity$HistoryListFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
            HistoryModel historyModel;
            if (this.listener == null || (historyModel = (HistoryModel) this.mAdapter.getItem(i)) == null) {
                return false;
            }
            this.listener.onHistoryItemLongClick(String.valueOf(historyModel.getId()), i, historyModel.getBox_type());
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder baseViewHolder, HistoryModel historyModel) {
            baseViewHolder.setText(R.id.tv_name, historyModel.getTitle());
            HistoryModel.HistoryBean history = historyModel.getHistory();
            if (history != null) {
                baseViewHolder.setText(R.id.tv_last_play, String.format("Last play to: S%s:E%s: %s", Integer.valueOf(history.getSeason()), Integer.valueOf(history.getEpisode()), getSecondFormat(history.getSeconds())));
            } else {
                baseViewHolder.setText(R.id.tv_last_play, String.format("Last play to: %s", getSecondFormat(historyModel.getSeconds())));
            }
            GlideUtils.loadCornerPortraitGifHolder(getActivity(), historyModel.getPoster(), (ImageView) baseViewHolder.getView(R.id.iv_avatar), 8);
            baseViewHolder.setText(R.id.tvPlayTime, String.format("Played at %s", TimeUtils.formatTime4(historyModel.getDateline() * 1000)));
            if (historyModel.getDevice() != null) {
                if ("app".equals(historyModel.getDevice().getType())) {
                    baseViewHolder.setText(R.id.tvModel, historyModel.getDevice().getModel() + " · " + historyModel.getDevice().getName());
                    return;
                } else if ("web".equals(historyModel.getDevice().getType())) {
                    baseViewHolder.setText(R.id.tvModel, historyModel.getDevice().getOs() + " · " + historyModel.getDevice().getBrowser());
                    return;
                } else {
                    baseViewHolder.setText(R.id.tvModel, "");
                    return;
                }
            }
            baseViewHolder.setText(R.id.tvModel, "");
        }

        private String getSecondFormat(int i) {
            return String.format("%s:%s", Integer.valueOf(i / 60), Integer.valueOf(i % 60));
        }
    }
}
