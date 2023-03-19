package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Pair;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.google.android.material.tabs.TabLayout;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.TabLayoutPagerAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.FavoriteAllFilterLiveData;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.SortVideoModel;
import com.movieboxpro.android.model.common.Collectlist;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DoubleClickHelper;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog;
import com.movieboxpro.android.view.fragment.FavoriteFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class CollectFragment extends BaseLazyFragment {
    private FavoriteFragment allFragment;
    private ArrayList<FilterCountry> countries;
    private FilterFavoriteVideoDialog favoriteVideoDialog;
    @BindView(R.id.flDragHint)
    FrameLayout flDragHint;
    private ArrayList<Gener> geners;
    private KProgressHUD hud;
    @BindView(R.id.ivFilter)
    ImageView ivFilter;
    @BindView(R.id.llEdit)
    LinearLayout llEdit;
    @BindView(R.id.fragmen_collect_tabs)
    LinearLayout mCollectTab;
    @BindView(R.id.fragmen_collect_delete)
    TextView mDelete;
    @BindView(R.id.iv_add)
    ImageView mEdit;
    @BindView(R.id.fragmen_collect_select)
    TextView mSelect;
    private FavoriteFragment movieFragment;
    private int selectPos;
    private int startPos;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private FavoriteFragment tvFragment;
    private Unbinder unbinder;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private boolean isEdit = false;
    public List<Collectlist> list = new ArrayList();

    @OnClick({R.id.fragmen_collect_select, R.id.fragmen_collect_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragmen_collect_delete /* 2131296781 */:
                SparseArray<List<String>> sparseArray = new SparseArray<>();
                int i = this.selectPos;
                if (i == 0) {
                    sparseArray = this.allFragment.getSelectIds();
                } else if (i == 1) {
                    sparseArray = this.movieFragment.getSelectIds();
                } else if (i == 2) {
                    sparseArray = this.tvFragment.getSelectIds();
                }
                List<String> list = sparseArray.get(1);
                List<String> list2 = sparseArray.get(2);
                if (list.isEmpty() && list2.isEmpty()) {
                    ToastUtils.showShort("empty");
                    return;
                } else {
                    setData(false, "del", list, list2);
                    return;
                }
            case R.id.fragmen_collect_select /* 2131296782 */:
                int i2 = this.selectPos;
                if (i2 == 0) {
                    this.allFragment.selectAll();
                    return;
                } else if (i2 == 1) {
                    this.movieFragment.selectAll();
                    return;
                } else if (i2 == 2) {
                    this.tvFragment.selectAll();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private void getFilterData() {
        Observable map;
        Observable map2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.FILTER_GENER);
        if (string == null) {
            map = Http.getService().Cat_list(API.BASE_URL, "Cat_list").compose(RxUtils.rxTranslate2Bean(String.class)).map($$Lambda$CollectFragment$wNBiQFhK9Uv2sbhI8bSgcDLrGsk.INSTANCE);
        } else {
            map = Observable.just(string).map($$Lambda$CollectFragment$ZGIRzM_JiR6lMbGJ77r48AQBMA.INSTANCE);
        }
        String string2 = PrefsUtils.getInstance().getString("movie_country_list");
        if (string2 == null) {
            map2 = HttpRequest.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class)).map($$Lambda$CollectFragment$yBLSxuHGrzHSbs_a1DWH63Uj78.INSTANCE);
        } else {
            map2 = Observable.just(string2).map($$Lambda$CollectFragment$alS2LJrgboG1GDxvnWAun3RMomY.INSTANCE);
        }
        ((ObservableSubscribeProxy) Observable.zip(map, map2, $$Lambda$CollectFragment$9CSUuj9n5wHnpwMMyYZgNLuaS8.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<ArrayList<Gener>, ArrayList<FilterCountry>>>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                CollectFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<ArrayList<Gener>, ArrayList<FilterCountry>> pair) {
                CollectFragment.this.hideLoading();
                CollectFragment.this.geners = pair.first;
                CollectFragment.this.countries = pair.second;
                CollectFragment.this.showFilterDialog();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                CollectFragment.this.hideLoading();
                ToastUtils.showShort("Load failed:" + apiException.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArrayList lambda$getFilterData$1(String str) throws Exception {
        return new ArrayList(JSONObject.parseArray(str, Gener.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Pair lambda$getFilterData$4(ArrayList arrayList, ArrayList arrayList2) throws Exception {
        return new Pair(arrayList, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFilterDialog() {
        boolean z = this.selectPos == 1;
        boolean z2 = this.selectPos == 0;
        if (this.favoriteVideoDialog == null) {
            FilterFavoriteVideoDialog newInstance = FilterFavoriteVideoDialog.Companion.newInstance(this.geners);
            this.favoriteVideoDialog = newInstance;
            newInstance.setType(z, z2);
            this.favoriteVideoDialog.setFilterListener(this.countries, new FilterFavoriteVideoDialog.OnFilterListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.2
                @Override // com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog.OnFilterListener
                public void onFilter(String str, String str2, String str3, String str4, String str5, String str6) {
                    boolean haveFilter;
                    HashMap hashMap = new HashMap();
                    hashMap.put("year", str);
                    hashMap.put("genre", str2);
                    hashMap.put("sort", str3);
                    hashMap.put("rating", str4);
                    hashMap.put("quality", str5);
                    hashMap.put("country", str6);
                    String jSONString = JSONObject.toJSONString(hashMap);
                    if (CollectFragment.this.selectPos == 0) {
                        CollectFragment.this.allFragment.setFilter("0", str, str2, str3, str4, str5, str6);
                        haveFilter = CollectFragment.this.allFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.FAVORITE_FILTER_ALL, jSONString);
                    } else if (CollectFragment.this.selectPos == 1) {
                        CollectFragment.this.movieFragment.setFilter("0", str, str2, str3, str4, str5, str6);
                        haveFilter = CollectFragment.this.movieFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.FAVORITE_FILTER_MOVIE, jSONString);
                    } else {
                        CollectFragment.this.tvFragment.setFilter("0", str, str2, str3, str4, str5, str6);
                        haveFilter = CollectFragment.this.tvFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.FAVORITE_FILTER_TV, jSONString);
                    }
                    if (haveFilter) {
                        CollectFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                    } else {
                        CollectFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                    }
                }

                @Override // com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog.OnFilterListener
                public void typeSelect(int i) {
                    CollectFragment.this.viewPager.setCurrentItem(i, false);
                }
            });
        }
        this.favoriteVideoDialog.setType(z, z2);
        HashMap<String, String> hashMap = new HashMap<>();
        int i = this.selectPos;
        if (i == 0) {
            hashMap = this.allFragment.getFilterData();
        } else if (i == 1) {
            hashMap = this.movieFragment.getFilterData();
        } else if (i == 2) {
            hashMap = this.tvFragment.getFilterData();
        }
        this.favoriteVideoDialog.setFilterData(hashMap);
        this.favoriteVideoDialog.show(getChildFragmentManager(), FilterFavoriteVideoDialog.class.getSimpleName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(LoginEvent loginEvent) {
        int i = this.selectPos;
        if (i == 0) {
            this.allFragment.refreshData();
        } else if (i == 1) {
            this.movieFragment.refreshData();
        } else if (i == 2) {
            this.tvFragment.refreshData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutSuccess(LogoutEvent logoutEvent) {
        int i = this.selectPos;
        if (i == 0) {
            this.allFragment.refreshData();
        } else if (i == 1) {
            this.movieFragment.refreshData();
        } else if (i == 2) {
            this.tvFragment.refreshData();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_collect, viewGroup, false);
        this.unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        CommonUtils.onEvent("EnterFavorite");
        EventUtils.event("进入收藏页");
        initView();
        initListener();
    }

    private void initListener() {
        DoubleClickHelper.click(this.ivFilter, new Runnable() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment$InHbUNKclXSrtz9SQcVBc-67kJg
            @Override // java.lang.Runnable
            public final void run() {
                CollectFragment.this.lambda$initListener$5$CollectFragment();
            }
        });
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.3
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                boolean z;
                int i2;
                int i3;
                CollectFragment.this.selectPos = i;
                if (CollectFragment.this.selectPos == 0) {
                    z = CollectFragment.this.allFragment.haveFilter();
                    CollectFragment collectFragment = CollectFragment.this;
                    collectFragment.isEdit = collectFragment.allFragment.isEditMode();
                } else if (CollectFragment.this.selectPos == 1) {
                    z = CollectFragment.this.movieFragment.haveFilter();
                    CollectFragment collectFragment2 = CollectFragment.this;
                    collectFragment2.isEdit = collectFragment2.movieFragment.isEditMode();
                } else if (CollectFragment.this.selectPos == 2) {
                    z = CollectFragment.this.tvFragment.haveFilter();
                    CollectFragment collectFragment3 = CollectFragment.this;
                    collectFragment3.isEdit = collectFragment3.tvFragment.isEditMode();
                } else {
                    z = false;
                }
                if (CollectFragment.this.isEdit) {
                    GlideUtils.load((Activity) CollectFragment.this.getActivity(), (int) R.mipmap.ic_edit_down, CollectFragment.this.mEdit);
                    CollectFragment.this.startAnimation();
                } else {
                    GlideUtils.load((Activity) CollectFragment.this.getActivity(), (int) R.mipmap.ic_edit, CollectFragment.this.mEdit);
                    CollectFragment.this.startAnimation();
                }
                if (z) {
                    CollectFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                } else {
                    CollectFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                }
                if (CollectFragment.this.selectPos == 0) {
                    i2 = CollectFragment.this.allFragment.getAllCount();
                    i3 = CollectFragment.this.allFragment.getSelectCount();
                } else if (CollectFragment.this.selectPos == 1) {
                    i2 = CollectFragment.this.movieFragment.getAllCount();
                    i3 = CollectFragment.this.movieFragment.getSelectCount();
                } else if (CollectFragment.this.selectPos == 2) {
                    i2 = CollectFragment.this.tvFragment.getAllCount();
                    i3 = CollectFragment.this.tvFragment.getSelectCount();
                } else {
                    i2 = 0;
                    i3 = 0;
                }
                if (i2 == i3) {
                    CollectFragment.this.mSelect.setText("Deselect All");
                    CollectFragment.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(i3)));
                } else if (i3 > 0) {
                    CollectFragment.this.mSelect.setText("Select All");
                    CollectFragment.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(i3)));
                } else {
                    CollectFragment.this.mSelect.setText("Select All");
                    CollectFragment.this.mDelete.setText("Delete");
                }
            }
        });
    }

    public /* synthetic */ void lambda$initListener$5$CollectFragment() {
        if (this.geners == null || this.countries == null) {
            getFilterData();
        } else {
            showFilterDialog();
        }
    }

    public void initView() {
        this.mEdit.setVisibility(0);
        this.mEdit.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment$7n50TtzdBCfFS7ywJfmVqhGwsvI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CollectFragment.this.lambda$initView$6$CollectFragment(view);
            }
        });
        ArrayList arrayList = new ArrayList(3);
        this.allFragment = FavoriteFragment.Companion.newInstance(1, true);
        this.movieFragment = FavoriteFragment.Companion.newInstance(1, false);
        this.tvFragment = FavoriteFragment.Companion.newInstance(2, false);
        arrayList.add(this.allFragment);
        arrayList.add(this.movieFragment);
        arrayList.add(this.tvFragment);
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, new String[]{"All", "Movies", "TV Shows"});
        this.viewPager.setOffscreenPageLimit(arrayList.size());
        this.viewPager.setAdapter(tabLayoutPagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        FavoriteFragment.OnFavoriteListener onFavoriteListener = new FavoriteFragment.OnFavoriteListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.4
            @Override // com.movieboxpro.android.view.fragment.FavoriteFragment.OnFavoriteListener
            public void onSelect(int i, int i2) {
                int i3;
                int i4;
                if (CollectFragment.this.selectPos == 0) {
                    i3 = CollectFragment.this.allFragment.getAllCount();
                    i4 = CollectFragment.this.allFragment.getSelectCount();
                } else if (CollectFragment.this.selectPos == 1) {
                    i3 = CollectFragment.this.movieFragment.getAllCount();
                    i4 = CollectFragment.this.movieFragment.getSelectCount();
                } else if (CollectFragment.this.selectPos == 2) {
                    i3 = CollectFragment.this.tvFragment.getAllCount();
                    i4 = CollectFragment.this.tvFragment.getSelectCount();
                } else {
                    i3 = 0;
                    i4 = 0;
                }
                if (i3 == i4) {
                    CollectFragment.this.mSelect.setText("Deselect All");
                    CollectFragment.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(i4)));
                } else if (i4 > 0) {
                    CollectFragment.this.mSelect.setText("Select All");
                    CollectFragment.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(i4)));
                } else {
                    CollectFragment.this.mSelect.setText("Select All");
                    CollectFragment.this.mDelete.setText("Delete");
                }
            }
        };
        this.allFragment.setFavoriteListener(onFavoriteListener);
        this.movieFragment.setFavoriteListener(onFavoriteListener);
        this.tvFragment.setFavoriteListener(onFavoriteListener);
        this.allFragment.setDragListener(new OnItemDragListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.5
            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2) {
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {
                if (CollectFragment.this.flDragHint.getVisibility() == 0) {
                    CollectFragment.this.flDragHint.setVisibility(8);
                }
                CollectFragment.this.startPos = i;
                CollectFragment.this.allFragment.setRefreshEnable(false);
                if (CollectFragment.this.isEdit) {
                    return;
                }
                CollectFragment.this.isEdit = true;
                CollectFragment.this.allFragment.setEditMode(true);
                GlideUtils.load((Activity) CollectFragment.this.getActivity(), (int) R.mipmap.ic_edit_down, CollectFragment.this.mEdit);
                CollectFragment.this.startAnimation();
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {
                if (CollectFragment.this.startPos != i) {
                    CollectFragment.this.allFragment.setRefreshEnable(true);
                    CollectFragment.this.saveSort(CollectFragment.this.allFragment.getVideos());
                }
            }
        });
        FavoriteAllFilterLiveData.Companion.get().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.6
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    CollectFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                } else {
                    CollectFragment.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initView$6$CollectFragment(View view) {
        boolean z = !this.isEdit;
        this.isEdit = z;
        int i = this.selectPos;
        if (i == 0) {
            this.allFragment.setEditMode(z);
        } else if (i == 1) {
            this.movieFragment.setEditMode(z);
        } else if (i == 2) {
            this.tvFragment.setEditMode(z);
        }
        if (this.isEdit) {
            GlideUtils.load((Activity) getActivity(), (int) R.mipmap.ic_edit_down, this.mEdit);
            startAnimation();
            return;
        }
        GlideUtils.load((Activity) getActivity(), (int) R.mipmap.ic_edit, this.mEdit);
        startAnimation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveSort(List<Collectlist> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new SortVideoModel(list.get(i).getCollect_id(), size - i));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("module", "Movie_collect_sort");
        hashMap.put("uid", App.getUserData().uid_v2);
        hashMap.put("open_udid", SystemUtils.getUniqueId(App.getContext()));
        hashMap.put("sort", arrayList);
        ((ObservableSubscribeProxy) Http.getService().sortFavorite(API.BASE_URL, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(hashMap))).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.7
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String str) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimation() {
        TranslateAnimation translateAnimation;
        if (this.isEdit) {
            if (this.llEdit.getVisibility() == 0) {
                return;
            }
            if (this.selectPos == 0) {
                if (this.allFragment.haveFilter()) {
                    this.flDragHint.setVisibility(8);
                } else {
                    this.flDragHint.setVisibility(0);
                }
            } else {
                this.flDragHint.setVisibility(8);
            }
            this.llEdit.setVisibility(0);
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        } else if (this.llEdit.getVisibility() == 8) {
            return;
        } else {
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.8
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CollectFragment.this.llEdit.setVisibility(8);
                }
            });
        }
        translateAnimation.setDuration(200L);
        this.llEdit.startAnimation(translateAnimation);
    }

    private void setData(boolean z, String str, List<String> list, List<String> list2) {
        HttpRequest.post(this, API.Movie.MOVE_COLLECT).param("mids", list).param("tids", list2).param(IjkMediaMeta.IJKM_KEY_TYPE, "del").asMsg().subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment.9
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                CollectFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass9) str2);
                CollectFragment.this.allFragment.refreshSelectStatus();
                CollectFragment.this.allFragment.beginRefresh();
                CollectFragment.this.movieFragment.refreshSelectStatus();
                CollectFragment.this.movieFragment.beginRefresh();
                CollectFragment.this.tvFragment.refreshSelectStatus();
                CollectFragment.this.tvFragment.beginRefresh();
                CollectFragment.this.isEdit = false;
                CollectFragment.this.startAnimation();
                GlideUtils.load((Activity) CollectFragment.this.getActivity(), (int) R.mipmap.ic_edit, CollectFragment.this.mEdit);
                ToastUtils.showShort("Delete successfully");
                CollectFragment.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                CollectFragment.this.hideLoading();
                ToastUtils.showShort("Delete failed:" + apiException.getMessage());
            }
        });
    }

    public void showLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            KProgressHUD kProgressHUD2 = this.hud;
            if ((kProgressHUD2 == null || !kProgressHUD2.isShowing()) && getContext() != null) {
                this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
            }
        }
    }

    public void hideLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            return;
        }
        this.hud.dismiss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.unbinder.unbind();
        super.onDestroyView();
    }
}
