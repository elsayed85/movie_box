package com.movieboxpro.android.view.fragment.movielist;

import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class AddFavoriteListFragment extends BaseListFragment<NormalFilmModel, String> {
    private List<NormalFilmModel> models;
    private String uid = App.getUserData().uid_v2;

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.adapter_selectable_movie_item;
    }

    public static AddFavoriteListFragment newInstance(ArrayList<NormalFilmModel> arrayList) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(CreateMovieListActivity.SELECT_VIDEOS, arrayList);
        AddFavoriteListFragment addFavoriteListFragment = new AddFavoriteListFragment();
        addFavoriteListFragment.setArguments(bundle);
        return addFavoriteListFragment;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void doSomethingWithData(List<NormalFilmModel> list) {
        List<NormalFilmModel> list2 = this.models;
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

    public List<NormalFilmModel> getCheckedFilm() {
        return this.models;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        this.mClass = NormalFilmModel.class;
        return Http.getService().Moviecollect(API.BASE_URL, API.Movie.MOVE_COLLECT, this.uid, null, null, "get", String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize));
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.movielist.-$$Lambda$AddFavoriteListFragment$eCgC6gOw6bTqvScoksfqe9uBFY0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AddFavoriteListFragment.this.lambda$onItemClick$0$AddFavoriteListFragment(baseQuickAdapter, view, i);
            }
        };
    }

    public /* synthetic */ void lambda$onItemClick$0$AddFavoriteListFragment(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        NormalFilmModel normalFilmModel = (NormalFilmModel) this.mAdapter.getItem(i);
        if (normalFilmModel != null) {
            normalFilmModel.setChecked(!normalFilmModel.isChecked());
            int indexOf = this.models.indexOf(normalFilmModel);
            if (indexOf == -1) {
                if (normalFilmModel.isChecked()) {
                    this.models.add(normalFilmModel);
                }
            } else if (!normalFilmModel.isChecked()) {
                this.models.remove(indexOf);
            }
        }
        this.mAdapter.notifyItemChanged(i);
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle bundle) {
        this.models = getArguments().getParcelableArrayList(CreateMovieListActivity.SELECT_VIDEOS);
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
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.view.fragment.movielist.AddFavoriteListFragment.initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder, com.movieboxpro.android.model.movie.NormalFilmModel):void");
    }
}
