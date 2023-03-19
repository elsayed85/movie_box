package com.movieboxpro.android.view.fragment.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseFragment;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.BaseResponse;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.StringCallBack;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.view.listener.OnKeyWordSelectListener;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.movieboxpro.android.view.widget.MyRecyclerView;
import com.movieboxpro.android.view.widget.VerticalSpaceDecoration;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
/* loaded from: classes3.dex */
public class KeyWordFragment extends BaseFragment {
    private static final int PAGE_COUNT = 20;
    private String keyword;
    private OnKeyWordSelectListener listener;
    private ResultBookAdapter mAdapter;
    @BindView(R.id.text_empty)
    TextView mEmptyText;
    @BindView(R.id.fragment_normal_recycler)
    MyRecyclerView mRecycler;
    private List<String> list = new ArrayList();
    private List<String> extendword = new ArrayList();

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
    }

    /* loaded from: classes3.dex */
    public class ItemViewHolder_ViewBinding implements Unbinder {
        private ItemViewHolder target;

        public ItemViewHolder_ViewBinding(ItemViewHolder itemViewHolder, View view) {
            this.target = itemViewHolder;
            itemViewHolder.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.keyword_title, "field 'mTitle'", TextView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            ItemViewHolder itemViewHolder = this.target;
            if (itemViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            itemViewHolder.mTitle = null;
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.layout_recycler_normal, viewGroup, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.BaseFragment
    public void onVisible() {
        super.onVisible();
        if (this.loaded) {
            return;
        }
        this.list.clear();
        initData();
        this.loaded = true;
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (this.mAdapter == null) {
            this.mAdapter = new ResultBookAdapter(this.list);
            MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(this.context);
            this.mRecycler.setEmptyView(this.mEmptyText);
            this.mRecycler.setLayoutManager(myLinearLayoutManager);
            this.mRecycler.addItemDecoration(new VerticalSpaceDecoration(DensityUtils.dp2px(this.context, 1.0f)));
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.search.KeyWordFragment.1
                @Override // com.movieboxpro.android.base.OnItemClickListener
                public void onItemClick(int i) {
                    if (i < 0 || i > KeyWordFragment.this.mAdapter.getItemCount() - 1) {
                        return;
                    }
                    String model = KeyWordFragment.this.mAdapter.getModel(i);
                    if (KeyWordFragment.this.listener != null) {
                        KeyWordFragment.this.listener.onKeyWordSelected(model);
                    }
                }
            });
        }
        loadData(false);
    }

    public void setKeyword(String str) {
        this.keyword = str;
        if (this.loaded) {
            loadData(false);
        }
    }

    private void loadData(boolean z) {
        if (this.service != null) {
            Call<String> Autocomplate2 = this.service.Autocomplate2(API.BASE_URL, API.Search.AUTOCOMPLATE, this.keyword, "10");
            CallManager.add(getClass().getSimpleName(), Autocomplate2);
            Autocomplate2.enqueue(new StringCallBack<String>(z ? this : null, String.class) { // from class: com.movieboxpro.android.view.fragment.search.KeyWordFragment.2
                @Override // com.movieboxpro.android.http.StringCallBack
                public boolean onParsed(BaseResponse<String> baseResponse) {
                    if (baseResponse.code < 1000 || baseResponse.code >= 2000) {
                        return false;
                    }
                    KeyWordFragment.this.handleData(baseResponse.list);
                    return true;
                }

                @Override // com.movieboxpro.android.http.BaseCallback
                public void onFinish(boolean z2, String str) {
                    super.onFinish(z2, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleData(List<String> list) {
        String str = this.TAG;
        MLog.d(str, "sadasdasda" + list);
        this.list.clear();
        if (list != null) {
            this.list.addAll(list);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    public void setListener(OnKeyWordSelectListener onKeyWordSelectListener) {
        this.listener = onKeyWordSelectListener;
    }

    @Override // com.movieboxpro.android.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        super.onDestroyView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class ResultBookAdapter extends BaseAdapter<String> {
        ResultBookAdapter(List<String> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public ItemViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new ItemViewHolder(layoutInflater.inflate(R.layout.layout_keyword_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            ((ItemViewHolder) baseViewHolder).mTitle.setText(getModel(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ItemViewHolder extends BaseViewHolder {
        @BindView(R.id.keyword_title)
        TextView mTitle;

        public ItemViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
