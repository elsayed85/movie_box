package com.movieboxpro.android.view.fragment.movielist;

import android.content.Context;
import android.content.DialogInterface;
import androidx.fragment.app.Fragment;
import com.movieboxpro.android.OnSetResultListener;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class AddFavoriteFragment extends BottomDialogFragment {
    private AddFavoriteListFragment addFavoriteListFragment;
    private OnSetResultListener listener;
    private List<NormalFilmModel> normalFilmModels;
    private DialogInterface.OnDismissListener onDismissListener;

    @Override // com.movieboxpro.android.view.fragment.movielist.BottomDialogFragment
    protected String setTitle() {
        return "Favorite";
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DialogInterface.OnDismissListener) {
            this.onDismissListener = (DialogInterface.OnDismissListener) context;
        }
    }

    public void setListener(OnSetResultListener onSetResultListener) {
        this.listener = onSetResultListener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDetach() {
        DialogInterface.OnDismissListener onDismissListener = this.onDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss(null);
        }
        super.onDetach();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        this.listener = null;
        super.onDestroy();
    }

    public void setNormalFilmModels(List<NormalFilmModel> list) {
        this.normalFilmModels = list;
    }

    @Override // com.movieboxpro.android.view.fragment.movielist.BottomDialogFragment
    protected Fragment setFragment() {
        if (this.normalFilmModels == null) {
            this.normalFilmModels = new ArrayList();
        }
        AddFavoriteListFragment newInstance = AddFavoriteListFragment.newInstance(new ArrayList(this.normalFilmModels));
        this.addFavoriteListFragment = newInstance;
        return newInstance;
    }

    @Override // com.movieboxpro.android.view.fragment.movielist.BottomDialogFragment
    protected void doDoneClick() {
        OnSetResultListener onSetResultListener = this.listener;
        if (onSetResultListener != null) {
            onSetResultListener.onSetResult(this.addFavoriteListFragment.getCheckedFilm());
            dismiss();
        }
    }
}
