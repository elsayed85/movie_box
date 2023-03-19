package com.movieboxpro.android.view.fragment.movielist;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.OnSetResultListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class AddSearchFragment extends AppCompatDialogFragment {
    private BaseQuickAdapter<String, BaseViewHolder> adapter;
    private List<NormalFilmModel> checkedFilms;
    private String currKeyword;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private OnSetResultListener listener;
    private SearchMovieResultFragment movieResultFragment;
    private DialogInterface.OnDismissListener onDismissListener;
    @BindView(R.id.keywordRecyclerView)
    RecyclerView recyclerView;
    private ArrayList<NormalFilmModel> selectedVideo;
    @BindView(R.id.tv_done)
    TextView tvDone;
    Unbinder unbinder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initListener$0(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DialogInterface.OnDismissListener) {
            this.onDismissListener = (DialogInterface.OnDismissListener) context;
        }
    }

    public static AddSearchFragment newInstance(ArrayList<NormalFilmModel> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CreateMovieListActivity.SELECT_VIDEOS, arrayList);
        AddSearchFragment addSearchFragment = new AddSearchFragment();
        addSearchFragment.setArguments(bundle);
        return addSearchFragment;
    }

    public static AddSearchFragment newInstance() {
        return new AddSearchFragment();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    public void setCheckedFilms(List<NormalFilmModel> list) {
        this.checkedFilms = list;
    }

    public void setResultListener(OnSetResultListener onSetResultListener) {
        this.listener = onSetResultListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.listener = null;
        super.onDestroy();
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
        View inflate = layoutInflater.inflate(R.layout.search_fragment_add_movie, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
        initListener();
        this.recyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.fragment.movielist.AddSearchFragment.1
            @Override // java.lang.Runnable
            public void run() {
                InputMethodUtils.showSoftInput(AddSearchFragment.this.etSearch);
            }
        }, 300L);
    }

    private void initListener() {
        $$Lambda$AddSearchFragment$DfhdoVz90Yckd_DhYitz9_H01c __lambda_addsearchfragment_dfhdovz90yckd_dhyitz9_h01c = $$Lambda$AddSearchFragment$DfhdoVz90Yckd_DhYitz9_H01c.INSTANCE;
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$AddSearchFragment$sgKitgct_AF4MAozXqZ_X7eE8Ug
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AddSearchFragment.this.lambda$initListener$1$AddSearchFragment(baseQuickAdapter, view, i);
            }
        });
        this.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$AddSearchFragment$AdGlb5D5UhFDW4dM1SXIbOFrEoA
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return AddSearchFragment.this.lambda$initListener$2$AddSearchFragment(textView, i, keyEvent);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$1$AddSearchFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        this.recyclerView.setVisibility(8);
        String item = this.adapter.getItem(i);
        this.currKeyword = item;
        this.etSearch.setText(item);
        this.etSearch.setSelection(this.currKeyword.length());
        SearchMovieResultFragment searchMovieResultFragment = this.movieResultFragment;
        if (searchMovieResultFragment == null) {
            SearchMovieResultFragment newInstance = SearchMovieResultFragment.newInstance(this.currKeyword, this.selectedVideo);
            this.movieResultFragment = newInstance;
            newInstance.setCheckFilms(this.checkedFilms);
            FragmentUtils.add(getChildFragmentManager(), this.movieResultFragment, (int) R.id.frame_layout);
        } else {
            searchMovieResultFragment.refreshData(this.currKeyword);
        }
        this.adapter.setNewData(new ArrayList());
        InputMethodUtils.hideSoftInput(this.etSearch);
    }

    public /* synthetic */ boolean lambda$initListener$2$AddSearchFragment(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 3) {
            this.recyclerView.setVisibility(8);
            String obj = this.etSearch.getText().toString();
            this.currKeyword = obj;
            SearchMovieResultFragment searchMovieResultFragment = this.movieResultFragment;
            if (searchMovieResultFragment == null) {
                SearchMovieResultFragment newInstance = SearchMovieResultFragment.newInstance(obj, this.selectedVideo);
                this.movieResultFragment = newInstance;
                newInstance.setCheckFilms(this.checkedFilms);
                FragmentUtils.add(getChildFragmentManager(), this.movieResultFragment, (int) R.id.frame_layout);
            } else {
                searchMovieResultFragment.refreshData(obj);
            }
            this.adapter.setNewData(new ArrayList());
            InputMethodUtils.hideSoftInput(this.etSearch);
            return true;
        }
        return false;
    }

    private void initData() {
        if (getArguments() != null) {
            this.selectedVideo = getArguments().getParcelableArrayList(CreateMovieListActivity.SELECT_VIDEOS);
        }
        this.adapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.layout_keyword_item, new ArrayList()) { // from class: com.movieboxpro.android.view.fragment.movielist.AddSearchFragment.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, String str) {
                baseViewHolder.setText(R.id.keyword_title, str);
            }
        };
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(this.adapter);
        this.etSearch.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.fragment.movielist.AddSearchFragment.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                String obj = editable.toString();
                if (obj.equals(AddSearchFragment.this.currKeyword)) {
                    return;
                }
                if (TextUtils.isEmpty(obj)) {
                    AddSearchFragment.this.ivClear.setVisibility(4);
                    return;
                }
                AddSearchFragment.this.recyclerView.setVisibility(0);
                AddSearchFragment.this.ivClear.setVisibility(0);
                AddSearchFragment.this.doAutoComplete(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAutoComplete(String str) {
        ((ObservableSubscribeProxy) Http.getService().Autocomplate(API.BASE_URL, API.Search.AUTOCOMPLATE, str, "15").compose(RxUtils.rxTranslate2List(String.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<String>>() { // from class: com.movieboxpro.android.view.fragment.movielist.AddSearchFragment.4
            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<String> list) {
                super.onNext((AnonymousClass4) list);
                AddSearchFragment.this.adapter.setNewData(list);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        DialogInterface.OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(null);
        }
        super.onDetach();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.onDismissListener = null;
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @OnClick({R.id.iv_clear, R.id.tv_done})
    public void onViewClicked(View view) {
        OnSetResultListener onSetResultListener;
        int id = view.getId();
        if (id == R.id.iv_clear) {
            this.etSearch.setText("");
            this.adapter.setNewData(new ArrayList());
            this.recyclerView.setVisibility(8);
            InputMethodUtils.showSoftInput(this.etSearch);
        } else if (id == R.id.tv_done && (onSetResultListener = this.listener) != null) {
            if (this.movieResultFragment != null) {
                onSetResultListener.onSetResult(new ArrayList(this.movieResultFragment.getCheckedFilms()));
            }
            InputMethodUtils.hideSoftInput(this.etSearch);
            dismiss();
        }
    }
}
