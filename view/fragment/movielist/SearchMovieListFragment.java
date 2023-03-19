package com.movieboxpro.android.view.fragment.movielist;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.utils.PrefsUtils;
import io.reactivex.Observable;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* loaded from: classes3.dex */
public class SearchMovieListFragment extends BaseListFragment<NormalFilmModel, String> {
    private List<NormalFilmModel> checkedFilms;
    private String keyword;
    private OnItemCheckedListener listener;
    private String type;
    private int typeInt;
    private String uid;

    /* loaded from: classes3.dex */
    public interface OnItemCheckedListener {
        void onCheck(int i, int i2, NormalFilmModel normalFilmModel);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_selectable_movie_item;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isNeedLazyLoad() {
        return false;
    }

    public SearchMovieListFragment() {
        this.uid = App.isLogin() ? App.getUserData().uid_v2 : "";
    }

    public static SearchMovieListFragment newInstance(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("keyword", str2);
        bundle.putString(IjkMediaMeta.IJKM_KEY_TYPE, str);
        SearchMovieListFragment searchMovieListFragment = new SearchMovieListFragment();
        searchMovieListFragment.setArguments(bundle);
        return searchMovieListFragment;
    }

    public void setCheckedFilms(List<NormalFilmModel> list) {
        this.checkedFilms = list;
    }

    public void setCheckedListener(OnItemCheckedListener onItemCheckedListener) {
        this.listener = onItemCheckedListener;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void doSomethingWithData(List<NormalFilmModel> list) {
        List<NormalFilmModel> list2 = this.checkedFilms;
        if (list2 != null) {
            for (NormalFilmModel normalFilmModel : list2) {
                int i = 0;
                int size = list.size();
                while (true) {
                    if (i >= size) {
                        break;
                    } else if (normalFilmModel.equals(list.get(i))) {
                        list.get(i).setChecked(normalFilmModel.isChecked());
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    public void updateCheckStatus(NormalFilmModel normalFilmModel) {
        List data = this.mAdapter.getData();
        int indexOf = data.indexOf(normalFilmModel);
        if (indexOf != -1) {
            ((NormalFilmModel) data.get(indexOf)).setChecked(normalFilmModel.isChecked());
            this.mAdapter.notifyItemChanged(indexOf);
        }
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        this.mClass = NormalFilmModel.class;
        return Http.getService().Search(API.BASE_URL, API.Search.SEARCH4, this.type, this.keyword, this.uid, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), Integer.valueOf(PrefsUtils.getInstance().getBoolean(Constant.Prefs.Incognito_Mode, false) ? 1 : 0));
    }

    public void refreshData(String str) {
        this.keyword = str;
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$SearchMovieListFragment$gSPaXPARzPRo8zimEupC7jwy6H0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchMovieListFragment.this.lambda$onItemClick$0$SearchMovieListFragment(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemClick$0$SearchMovieListFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        NormalFilmModel normalFilmModel = (NormalFilmModel) this.mAdapter.getItem(i);
        if (normalFilmModel != null) {
            normalFilmModel.setChecked(!normalFilmModel.isChecked());
            this.mAdapter.notifyItemChanged(i);
            OnItemCheckedListener onItemCheckedListener = this.listener;
            if (onItemCheckedListener != null) {
                onItemCheckedListener.onCheck(this.typeInt, i, normalFilmModel);
            }
        }
    }

    private int getTypeInt(String str) {
        if (TtmlNode.COMBINE_ALL.equals(str)) {
            return 1;
        }
        if ("movie".equals(str)) {
            return 2;
        }
        return "tv".equals(str) ? 3 : 0;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        if (TextUtils.isEmpty(this.keyword)) {
            this.keyword = bundle.getString("keyword");
        }
        String string = bundle.getString(IjkMediaMeta.IJKM_KEY_TYPE);
        this.type = string;
        this.typeInt = getTypeInt(string);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b7  */
    @Override // com.movieboxpro.android.base.BaseListFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder r7, com.movieboxpro.android.model.movie.NormalFilmModel r8) {
        /*
            Method dump skipped, instructions count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.fragment.movielist.SearchMovieListFragment.initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder, com.movieboxpro.android.model.movie.NormalFilmModel):void");
    }
}
