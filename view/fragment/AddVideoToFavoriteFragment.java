package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.OnFavoriteStatusChangedListener;
import com.movieboxpro.android.model.CreateMovieListModel;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.movielist.MovieListDetailActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.EditTextDialog;
import com.movieboxpro.android.view.widget.CustomClickableSpan;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class AddVideoToFavoriteFragment extends BottomSheetDialogFragment {
    private BaseQuickAdapter<MovieListModel.MovieListItem, BaseViewHolder> adapter;
    private int boxType;
    private int collectWatched;
    private String id;
    private boolean isFavorite;
    private OnFavoriteStatusChangedListener listener;
    @BindView(R.id.llCreate)
    LinearLayout llCreate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvWaitingAdded)
    TextView tvWaitingAdded;
    @BindView(R.id.tvWatchedAdded)
    TextView tvWatchedAdded;
    Unbinder unbinder;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFavoriteStatusChangedListener) {
            this.listener = (OnFavoriteStatusChangedListener) context;
        }
    }

    public static AddVideoToFavoriteFragment newInstance(List<MovieListModel.MovieListItem> list, String str, boolean z, int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Movie", (Serializable) list);
        bundle.putString("id", str);
        bundle.putBoolean("isFavorite", z);
        bundle.putInt("boxType", i);
        bundle.putInt("collectWatched", i2);
        AddVideoToFavoriteFragment addVideoToFavoriteFragment = new AddVideoToFavoriteFragment();
        addVideoToFavoriteFragment.setArguments(bundle);
        return addVideoToFavoriteFragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.TransparentDialog);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        final View view = getView();
        if (view != null) {
            view.post(new Runnable() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.1
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) ((CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams()).getBehavior();
                    int i = AddVideoToFavoriteFragment.this.getResources().getDisplayMetrics().heightPixels / 2;
                    if (bottomSheetBehavior != null) {
                        bottomSheetBehavior.setPeekHeight(i);
                    }
                    bottomSheetBehavior.setState(3);
                }
            });
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dialog_add_favorite, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
        initListener();
    }

    private void initListener() {
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) baseQuickAdapter.getItem(i);
                if (movieListItem != null) {
                    AddVideoToFavoriteFragment.this.addToMovieList(movieListItem.getLid(), i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addToMovieList(String str, final int i) {
        ((ObservableSubscribeProxy) Http.getService().Playlists_video_add(API.BASE_URL, "Playlists_video_add", App.getUserData().uid_v2, str, this.boxType, this.id).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.3
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                AddVideoToFavoriteFragment.this.listener.showLoadLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass3) str2);
                AddVideoToFavoriteFragment.this.dismiss();
                AddVideoToFavoriteFragment.this.listener.showViewToast("Add Success");
                MovieListModel.MovieListItem movieListItem = (MovieListModel.MovieListItem) AddVideoToFavoriteFragment.this.adapter.getItem(i);
                if (movieListItem != null) {
                    movieListItem.setCount(movieListItem.getCount() + 1);
                }
                AddVideoToFavoriteFragment.this.adapter.notifyItemChanged(i);
                EventBus.getDefault().post(new RefreshMovieListEvent());
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                AddVideoToFavoriteFragment.this.listener.showViewToast(apiException.getMessage());
            }
        });
    }

    private void initData() {
        this.boxType = getArguments().getInt("boxType");
        this.collectWatched = getArguments().getInt("collectWatched");
        this.id = getArguments().getString("id");
        this.isFavorite = getArguments().getBoolean("isFavorite");
        initStatus();
        this.adapter = new BaseQuickAdapter<MovieListModel.MovieListItem, BaseViewHolder>(R.layout.adapter_add_to_movie_list, (List) getArguments().getSerializable("Movie")) { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, final MovieListModel.MovieListItem movieListItem) {
                List<String> imgArr = movieListItem.getImgArr();
                if (imgArr != null && imgArr.size() > 0) {
                    GlideUtils.loadWithCorner(getContext(), imgArr.get(0), (ImageView) baseViewHolder.getView(R.id.imageView), DensityUtils.dp2px(getContext(), 4.0f));
                }
                SpanUtils.with((TextView) baseViewHolder.getView(R.id.tv_title)).append(movieListItem.getName()).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setUnderline().setClickSpan(new CustomClickableSpan(ContextCompat.getColor(App.getContext(), R.color.white), true) { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.4.1
                    @Override // android.text.style.ClickableSpan
                    public void onClick(View view) {
                        MovieListDetailActivity.start(getContext(), movieListItem.getLid(), movieListItem.getUsername(), movieListItem.getCover());
                    }
                }).create();
                baseViewHolder.setText(R.id.tv_video_num, String.format("%s videos", Integer.valueOf(movieListItem.getCount())));
            }
        };
        if (CommonUtils.isTablet()) {
            this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        RecyclerView.ItemAnimator itemAnimator = this.recyclerView.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.recyclerView.setAdapter(this.adapter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStatus() {
        int i = this.collectWatched;
        if (i == 1) {
            this.tvWatchedAdded.setVisibility(0);
            this.tvWaitingAdded.setVisibility(8);
        } else if (i == 0) {
            this.tvWaitingAdded.setVisibility(0);
            this.tvWatchedAdded.setVisibility(8);
        } else {
            this.tvWaitingAdded.setVisibility(8);
            this.tvWatchedAdded.setVisibility(8);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.listener = null;
        super.onDestroyView();
        this.unbinder.unbind();
    }

    @OnClick({R.id.llCreate, R.id.llAddWaiting, R.id.llAddWatched})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llAddWaiting /* 2131297143 */:
                int i = this.collectWatched;
                if (i == 0) {
                    cancelWatch();
                    return;
                } else if (i == 1) {
                    changeWatched(0);
                    return;
                } else {
                    markWatched(0);
                    return;
                }
            case R.id.llAddWatched /* 2131297144 */:
                int i2 = this.collectWatched;
                if (i2 == 1) {
                    cancelWatch();
                    return;
                } else if (i2 == 0) {
                    changeWatched(1);
                    return;
                } else {
                    markWatched(1);
                    return;
                }
            case R.id.llCreate /* 2131297172 */:
                new EditTextDialog.Builder(getContext()).setTitle("New Playlist").setContent("Enter a name for this playlist").setHint("Playlist Title").setActionListener(new DialogAction.EditActionListener() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.5
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.EditActionListener
                    public void onClick(String str) {
                        AddVideoToFavoriteFragment.this.createMovieList(str);
                    }
                }).create().show();
                return;
            default:
                return;
        }
    }

    private void cancelWatch() {
        HttpRequest.post(this, "User_watch_plan_del").param("mid", this.id).param("box_type", Integer.valueOf(this.boxType)).asMsg().subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                AddVideoToFavoriteFragment.this.listener.showLoadLoading();
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                AddVideoToFavoriteFragment.this.collectWatched = -1;
                AddVideoToFavoriteFragment.this.listener.onFavoriteStatusChanged(AddVideoToFavoriteFragment.this.isFavorite, AddVideoToFavoriteFragment.this.collectWatched);
                AddVideoToFavoriteFragment.this.initStatus();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMovieList(String str) {
        CreateMovieListModel createMovieListModel = new CreateMovieListModel();
        createMovieListModel.setBox_type(this.boxType);
        createMovieListModel.setMark("");
        createMovieListModel.setMid(this.id);
        ArrayList arrayList = new ArrayList();
        arrayList.add(createMovieListModel);
        HashMap hashMap = new HashMap();
        hashMap.put("module", "Playlists");
        hashMap.put("uid", App.getUserData().uid_v2);
        hashMap.put("name", str);
        hashMap.put("desc", "");
        hashMap.put("isshare", "0");
        hashMap.put("lid", "");
        hashMap.put("videos", arrayList);
        ((ObservableSubscribeProxy) Http.getService().Playlists_create(API.BASE_URL, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(hashMap))).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.7
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                AddVideoToFavoriteFragment.this.listener.showLoadLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass7) str2);
                AddVideoToFavoriteFragment.this.listener.showViewToast("New Playlist Success");
                InputMethodUtils.hideSoftInput(AddVideoToFavoriteFragment.this.getActivity());
                EventBus.getDefault().post(new RefreshMovieListEvent());
                AddVideoToFavoriteFragment.this.dismiss();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
            }
        });
    }

    private void changeWatched(final int i) {
        ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_mark").param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("watched", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.8
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.showLoadLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                }
                AddVideoToFavoriteFragment.this.collectWatched = i;
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.onFavoriteStatusChanged(AddVideoToFavoriteFragment.this.isFavorite, AddVideoToFavoriteFragment.this.collectWatched);
                }
                AddVideoToFavoriteFragment.this.initStatus();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                }
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    private void markWatched(final int i) {
        ((ObservableSubscribeProxy) HttpRequest.post("User_watch_plan_add").param("box_type", Integer.valueOf(this.boxType)).param("mid", this.id).param("watched", Integer.valueOf(i)).asRequest().compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.9
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.showLoadLoading();
                }
            }

            @Override // io.reactivex.Observer
            public void onNext(String str) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                }
                AddVideoToFavoriteFragment.this.collectWatched = i;
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.onFavoriteStatusChanged(AddVideoToFavoriteFragment.this.isFavorite, AddVideoToFavoriteFragment.this.collectWatched);
                }
                AddVideoToFavoriteFragment.this.initStatus();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                }
                ToastUtils.showShort("Load failed:" + th.getMessage());
            }
        });
    }

    private void addToFavorite(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.id);
        ArrayList arrayList2 = null;
        if (this.boxType != 1) {
            arrayList2 = arrayList;
            arrayList = null;
        }
        HttpRequest.post(this, API.Movie.MOVE_COLLECT).param("mids", arrayList).param("tids", arrayList2).param(IjkMediaMeta.IJKM_KEY_TYPE, this.isFavorite ? "del" : "add").param("watched", Integer.valueOf(i)).asMsg().subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.AddVideoToFavoriteFragment.10
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.showLoadLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str) {
                super.onNext((AnonymousClass10) str);
                AddVideoToFavoriteFragment addVideoToFavoriteFragment = AddVideoToFavoriteFragment.this;
                addVideoToFavoriteFragment.isFavorite = !addVideoToFavoriteFragment.isFavorite;
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.onFavoriteStatusChanged(AddVideoToFavoriteFragment.this.isFavorite, AddVideoToFavoriteFragment.this.collectWatched);
                }
                EventBus.getDefault().post(new RefreshFavoriteEvent());
                if (AddVideoToFavoriteFragment.this.isFavorite) {
                    ToastUtils.showShort("Add successfully");
                } else {
                    ToastUtils.showShort("Remove successfully");
                }
                AddVideoToFavoriteFragment.this.dismiss();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                if (AddVideoToFavoriteFragment.this.listener != null) {
                    AddVideoToFavoriteFragment.this.listener.hideLoadLoading();
                }
            }
        });
    }
}
