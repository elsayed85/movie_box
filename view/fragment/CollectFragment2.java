package com.movieboxpro.android.view.fragment;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
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
import com.movieboxpro.android.livedata.SwitchRecommendLiveData;
import com.movieboxpro.android.livedata.SwitchWaitingLiveData;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.SortVideoModel;
import com.movieboxpro.android.model.common.Collectlist;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.DoubleClickHelper;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog;
import com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog3;
import com.movieboxpro.android.view.fragment.FavoriteFragment;
import com.movieboxpro.android.view.fragment.FavoriteFragment2;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xmlpull.v1.XmlPullParserException;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes.dex */
public class CollectFragment2 extends BaseLazyFragment {
    private ArrayList<FilterCountry> countries;
    private FavoriteFragment favoriteFragment;
    private FilterFavoriteVideoDialog3 favoriteVideoDialog;
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
    private RecommendFragment recommendFragment;
    private int selectPos;
    private int startPos;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private Unbinder unbinder;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private FavoriteFragment2.FavoriteListFragment waitingFragment;
    private WatchedFragment watchedFragment;
    private boolean isEdit = false;
    public List<Collectlist> list = new ArrayList();

    @OnClick({R.id.fragmen_collect_select, R.id.fragmen_collect_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragmen_collect_delete /* 2131296781 */:
                new SparseArray();
                SparseArray<List<String>> selectIds = this.favoriteFragment.getSelectIds();
                List<String> list = selectIds.get(1);
                List<String> list2 = selectIds.get(2);
                if (list.isEmpty() && list2.isEmpty()) {
                    ToastUtils.showShort("empty");
                    return;
                } else {
                    setData(false, "del", list, list2);
                    return;
                }
            case R.id.fragmen_collect_select /* 2131296782 */:
                if (this.selectPos == 2) {
                    this.favoriteFragment.selectAll();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void getFilterData() {
        Observable map;
        Observable map2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.FILTER_GENER);
        if (string == null) {
            map = Http.getService().Cat_list(API.BASE_URL, "Cat_list").compose(RxUtils.rxTranslate2Bean(String.class)).map($$Lambda$CollectFragment2$OYN86Glreq738OtNjx7ESsmk4Q.INSTANCE);
        } else {
            map = Observable.just(string).map($$Lambda$CollectFragment2$pP3eHEclbirX8wrSru336G8odE.INSTANCE);
        }
        String string2 = PrefsUtils.getInstance().getString("movie_country_list");
        if (string2 == null) {
            map2 = HttpRequest.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class)).map($$Lambda$CollectFragment2$0EXKk_hQhdzN0jM99zcO7tqkBLU.INSTANCE);
        } else {
            map2 = Observable.just(string2).map($$Lambda$CollectFragment2$h5cEkDgeO4Ukn4hDsBpgyn1rqE.INSTANCE);
        }
        ((ObservableSubscribeProxy) Observable.zip(map, map2, $$Lambda$CollectFragment2$vJp8RCOs3qTKwuWueuVgSSxwGEc.INSTANCE).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<ArrayList<Gener>, ArrayList<FilterCountry>>>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                CollectFragment2.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<ArrayList<Gener>, ArrayList<FilterCountry>> pair) {
                CollectFragment2.this.hideLoading();
                CollectFragment2.this.geners = pair.first;
                CollectFragment2.this.countries = pair.second;
                CollectFragment2.this.showFilterDialog();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException apiException) {
                CollectFragment2.this.hideLoading();
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
        String str;
        if (this.tabLayout.getSelectedTabPosition() == 0) {
            str = FilterFavoriteVideoDialog3.WAITING_TYPE;
        } else if (this.tabLayout.getSelectedTabPosition() == 1) {
            str = FilterFavoriteVideoDialog3.WATCHED_TYPE;
        } else {
            str = this.tabLayout.getSelectedTabPosition() == 2 ? FilterFavoriteVideoDialog3.COLLECT_TYPE : "";
        }
        if (this.favoriteVideoDialog == null) {
            FilterFavoriteVideoDialog3 newInstance = FilterFavoriteVideoDialog3.Companion.newInstance(this.geners);
            this.favoriteVideoDialog = newInstance;
            newInstance.setType(false, true, str);
            this.favoriteVideoDialog.setFilterListener(this.countries, new FilterFavoriteVideoDialog3.OnFilterListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.2
                @Override // com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog3.OnFilterListener
                public void onFilter(String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
                    boolean z;
                    HashMap hashMap = new HashMap();
                    hashMap.put("year", str3);
                    hashMap.put("genre", str4);
                    hashMap.put("sort", str5);
                    hashMap.put("rating", str6);
                    hashMap.put("quality", str7);
                    hashMap.put("country", str8);
                    hashMap.put("boxType", str2);
                    String jSONString = JSONObject.toJSONString(hashMap);
                    if (CollectFragment2.this.selectPos == 0) {
                        CollectFragment2.this.waitingFragment.setFilter(str2, str3, str4, str5, str6, str7, str8);
                        z = CollectFragment2.this.waitingFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.FAVORITE_FILTER_WAITING, jSONString);
                    } else if (CollectFragment2.this.selectPos != 1 && CollectFragment2.this.selectPos == 2) {
                        CollectFragment2.this.favoriteFragment.setFilter(str2, str3, str4, str5, str6, str7, str8);
                        z = CollectFragment2.this.favoriteFragment.haveFilter();
                        PrefsUtils.getInstance().putString(Constant.Prefs.FAVORITE_FILTER_COLLECT, jSONString);
                    } else {
                        z = false;
                    }
                    if (z) {
                        CollectFragment2.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                    } else {
                        CollectFragment2.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                    }
                }

                @Override // com.movieboxpro.android.view.dialog.FilterFavoriteVideoDialog3.OnFilterListener
                public void typeSelect(int i) {
                    CollectFragment2.this.viewPager.setCurrentItem(i, false);
                }
            });
        }
        this.favoriteVideoDialog.setType(false, true, str);
        HashMap<String, String> hashMap = new HashMap<>();
        int i = this.selectPos;
        if (i == 0) {
            hashMap = this.waitingFragment.getFilterData();
        } else if (i != 1 && i == 2) {
            hashMap = this.favoriteFragment.getFilterData();
        }
        this.favoriteVideoDialog.setFilterData(hashMap);
        this.favoriteVideoDialog.show(getChildFragmentManager(), FilterFavoriteVideoDialog.class.getSimpleName());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginSuccess(LoginEvent loginEvent) {
        int i = this.selectPos;
        if (i == 0) {
            this.waitingFragment.refreshData();
        } else if (i == 1) {
            this.watchedFragment.refreshData();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogoutSuccess(LogoutEvent logoutEvent) {
        int i = this.selectPos;
        if (i == 0) {
            this.waitingFragment.refreshData();
        } else if (i == 1) {
            this.watchedFragment.refreshData();
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
        EventUtils.event("进入收藏页");
        initView();
        initListener();
        observeData();
    }

    private void observeData() {
        SwitchWaitingLiveData.Companion.get().observeInFragment(this, new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                CollectFragment2.this.viewPager.setCurrentItem(0);
            }
        });
        SwitchRecommendLiveData.Companion.get().observeInFragment(this, new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                CollectFragment2.this.viewPager.setCurrentItem(3);
            }
        });
    }

    private void initListener() {
        DoubleClickHelper.click(this.ivFilter, new Runnable() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment2$yNpyvpsHfYSQrR5atrC1P6mIPA8
            @Override // java.lang.Runnable
            public final void run() {
                CollectFragment2.this.lambda$initListener$5$CollectFragment2();
            }
        });
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.5
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                CollectFragment2.this.selectPos = i;
                boolean z = false;
                if (CollectFragment2.this.selectPos != 0) {
                    if (CollectFragment2.this.selectPos != 1) {
                        if (CollectFragment2.this.selectPos != 2) {
                            if (CollectFragment2.this.selectPos != 3) {
                                if (CollectFragment2.this.selectPos == 4) {
                                    CollectFragment2.this.mEdit.setVisibility(8);
                                    CollectFragment2.this.ivFilter.setVisibility(8);
                                    CollectFragment2.this.mCollectTab.setVisibility(8);
                                }
                            } else {
                                CollectFragment2.this.mEdit.setVisibility(8);
                                CollectFragment2.this.ivFilter.setVisibility(8);
                                CollectFragment2.this.mCollectTab.setVisibility(8);
                            }
                        } else {
                            CollectFragment2.this.mCollectTab.setVisibility(0);
                            CollectFragment2.this.mEdit.setVisibility(0);
                            CollectFragment2.this.ivFilter.setVisibility(0);
                            int selectCount = CollectFragment2.this.favoriteFragment.getSelectCount();
                            if (selectCount == 0) {
                                CollectFragment2.this.mSelect.setText("Deselect All");
                                CollectFragment2.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(selectCount)));
                            } else if (selectCount > 0) {
                                CollectFragment2.this.mSelect.setText("Select All");
                                CollectFragment2.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(selectCount)));
                            } else {
                                CollectFragment2.this.mSelect.setText("Select All");
                                CollectFragment2.this.mDelete.setText("Delete");
                            }
                        }
                    } else {
                        CollectFragment2.this.mEdit.setVisibility(8);
                        CollectFragment2.this.ivFilter.setVisibility(8);
                        CollectFragment2.this.mCollectTab.setVisibility(8);
                    }
                } else {
                    CollectFragment2.this.mEdit.setVisibility(8);
                    CollectFragment2.this.ivFilter.setVisibility(0);
                    CollectFragment2.this.mCollectTab.setVisibility(8);
                }
                if (CollectFragment2.this.selectPos == 0) {
                    z = CollectFragment2.this.waitingFragment.haveFilter();
                } else if (CollectFragment2.this.selectPos != 1 && CollectFragment2.this.selectPos == 2) {
                    z = CollectFragment2.this.favoriteFragment.haveFilter();
                    CollectFragment2 collectFragment2 = CollectFragment2.this;
                    collectFragment2.isEdit = collectFragment2.favoriteFragment.isEditMode();
                }
                if (CollectFragment2.this.isEdit) {
                    GlideUtils.load((Activity) CollectFragment2.this.getActivity(), (int) R.mipmap.ic_edit_down, CollectFragment2.this.mEdit);
                    CollectFragment2.this.startAnimation();
                } else {
                    GlideUtils.load((Activity) CollectFragment2.this.getActivity(), (int) R.mipmap.ic_edit, CollectFragment2.this.mEdit);
                    CollectFragment2.this.startAnimation();
                }
                if (z) {
                    CollectFragment2.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                } else {
                    CollectFragment2.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initListener$5$CollectFragment2() {
        if (this.geners == null || this.countries == null) {
            getFilterData();
        } else {
            showFilterDialog();
        }
    }

    public void initView() {
        this.mEdit.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.-$$Lambda$CollectFragment2$18ztcQimfkP-0YEGwhFFXxhG3Co
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CollectFragment2.this.lambda$initView$6$CollectFragment2(view);
            }
        });
        ArrayList arrayList = new ArrayList(3);
        this.waitingFragment = FavoriteFragment2.FavoriteListFragment.Companion.newInstance(0);
        this.watchedFragment = new WatchedFragment();
        this.favoriteFragment = FavoriteFragment.Companion.newInstance(1, true);
        this.recommendFragment = new RecommendFragment();
        arrayList.add(this.waitingFragment);
        arrayList.add(this.watchedFragment);
        arrayList.add(this.favoriteFragment);
        arrayList.add(this.recommendFragment);
        String[] strArr = {"WAITING", "WATCHED", "FAVORITES", "RECOMMENDED"};
        TabLayoutPagerAdapter tabLayoutPagerAdapter = new TabLayoutPagerAdapter(getChildFragmentManager(), arrayList, strArr);
        this.viewPager.setOffscreenPageLimit(arrayList.size());
        this.viewPager.setAdapter(tabLayoutPagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        for (int i = 0; i < 4; i++) {
            TabLayout.Tab tabAt = this.tabLayout.getTabAt(i);
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            textView.setText(strArr[i]);
            textView.setTextSize(15.0f);
            try {
                textView.setTextColor(ColorStateList.createFromXml(App.getContext().getResources(), App.getContext().getResources().getXml(R.color.tab_text_color_selector)));
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
            textView.setTypeface(Typeface.defaultFromStyle(1));
            if (tabAt != null) {
                tabAt.setCustomView(textView);
                tabAt.setTag(Integer.valueOf(i));
            }
        }
        this.favoriteFragment.setFavoriteListener(new FavoriteFragment.OnFavoriteListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.6
            @Override // com.movieboxpro.android.view.fragment.FavoriteFragment.OnFavoriteListener
            public void onSelect(int i2, int i3) {
                int selectCount = CollectFragment2.this.favoriteFragment.getSelectCount();
                if (selectCount == 0) {
                    CollectFragment2.this.mSelect.setText("Deselect All");
                    CollectFragment2.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(selectCount)));
                } else if (selectCount > 0) {
                    CollectFragment2.this.mSelect.setText("Select All");
                    CollectFragment2.this.mDelete.setText(String.format("Delete (%s)", Integer.valueOf(selectCount)));
                } else {
                    CollectFragment2.this.mSelect.setText("Select All");
                    CollectFragment2.this.mDelete.setText("Delete");
                }
            }
        });
        this.favoriteFragment.setDragListener(new OnItemDragListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.7
            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3) {
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i2) {
                if (CollectFragment2.this.flDragHint.getVisibility() == 0) {
                    CollectFragment2.this.flDragHint.setVisibility(8);
                }
                CollectFragment2.this.startPos = i2;
                CollectFragment2.this.favoriteFragment.setRefreshEnable(false);
                if (CollectFragment2.this.isEdit) {
                    return;
                }
                CollectFragment2.this.isEdit = true;
                CollectFragment2.this.favoriteFragment.setEditMode(true);
                GlideUtils.load((Activity) CollectFragment2.this.getActivity(), (int) R.mipmap.ic_edit_down, CollectFragment2.this.mEdit);
                CollectFragment2.this.startAnimation();
            }

            @Override // com.chad.library.adapter.base.listener.OnItemDragListener
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i2) {
                if (CollectFragment2.this.startPos != i2) {
                    CollectFragment2.this.favoriteFragment.setRefreshEnable(true);
                    CollectFragment2.this.saveSort(CollectFragment2.this.favoriteFragment.getVideos());
                }
            }
        });
        FavoriteAllFilterLiveData.Companion.get().observe(getViewLifecycleOwner(), new Observer<Boolean>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.8
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    CollectFragment2.this.ivFilter.setImageResource(R.mipmap.ic_filter_seleted);
                } else {
                    CollectFragment2.this.ivFilter.setImageResource(R.mipmap.ic_filter);
                }
            }
        });
    }

    public /* synthetic */ void lambda$initView$6$CollectFragment2(View view) {
        boolean z = !this.isEdit;
        this.isEdit = z;
        this.favoriteFragment.setEditMode(z);
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
        ((ObservableSubscribeProxy) Http.getService().sortFavorite(API.BASE_URL, RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), JSON.toJSONString(hashMap))).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.9
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
                if (this.waitingFragment.haveFilter()) {
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
            translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.10
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    CollectFragment2.this.llEdit.setVisibility(8);
                }
            });
        }
        translateAnimation.setDuration(200L);
        this.llEdit.startAnimation(translateAnimation);
    }

    private void setData(boolean z, String str, List<String> list, List<String> list2) {
        HttpRequest.post(this, API.Movie.MOVE_COLLECT).param("mids", list).param("tids", list2).param(IjkMediaMeta.IJKM_KEY_TYPE, "del").asMsg().subscribe(new BaseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.CollectFragment2.11
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                super.onSubscribe(disposable);
                CollectFragment2.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(String str2) {
                super.onNext((AnonymousClass11) str2);
                CollectFragment2.this.favoriteFragment.refreshSelectStatus();
                CollectFragment2.this.favoriteFragment.beginRefresh();
                CollectFragment2.this.isEdit = false;
                CollectFragment2.this.startAnimation();
                GlideUtils.load((Activity) CollectFragment2.this.getActivity(), (int) R.mipmap.ic_edit, CollectFragment2.this.mEdit);
                ToastUtils.showShort("Delete successfully");
                CollectFragment2.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException apiException) {
                CollectFragment2.this.hideLoading();
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
