package com.movieboxpro.android.view.activity.settings;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.gavin.com.library.StickyDecoration;
import com.gavin.com.library.listener.GroupListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseAdapter;
import com.movieboxpro.android.base.BaseViewHolder;
import com.movieboxpro.android.base.OnItemClickListener;
import com.movieboxpro.android.model.Languages;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.view.widget.MyGridLayoutManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes3.dex */
public class Language extends BaseActivity {
    private MyGridLayoutManager layoutManager;
    private ChooseAdapter mAdapter;
    @BindView(R.id.myRecycler)
    RecyclerView mRecycler;
    public List<Languages> list = new ArrayList();
    OnItemClickListener onClickListener = new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.settings.Language.1
        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            PrefsUtils.getInstance().putString("language", Language.this.list.get(i).getName());
            App.deviceLang = Language.this.list.get(i).getName();
            Language.this.setSelceted(i);
            Language.this.showToast("Success");
        }
    };

    /* loaded from: classes3.dex */
    public class Item1ViewHolder_ViewBinding implements Unbinder {
        private Item1ViewHolder target;

        public Item1ViewHolder_ViewBinding(Item1ViewHolder item1ViewHolder, View view) {
            this.target = item1ViewHolder;
            item1ViewHolder.mText = (TextView) Utils.findRequiredViewAsType(view, R.id.language_text, "field 'mText'", TextView.class);
            item1ViewHolder.mSelect = (ImageView) Utils.findRequiredViewAsType(view, R.id.language_select, "field 'mSelect'", ImageView.class);
        }

        @Override // butterknife.Unbinder
        public void unbind() {
            Item1ViewHolder item1ViewHolder = this.target;
            if (item1ViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            item1ViewHolder.mText = null;
            item1ViewHolder.mSelect = null;
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        return layoutInflater.inflate(R.layout.activity_language, (ViewGroup) null);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitle("Languages");
        if (this.mAdapter == null) {
            this.mAdapter = new ChooseAdapter(this.list);
            StickyDecoration build = StickyDecoration.Builder.init(new GroupListener() { // from class: com.movieboxpro.android.view.activity.settings.Language.2
                @Override // com.gavin.com.library.listener.GroupListener
                public String getGroupName(int i) {
                    if (Language.this.list.size() <= i || i <= -1) {
                        return null;
                    }
                    return Language.this.list.get(i).getProvince();
                }
            }).setGroupBackground(getResources().getColor(R.color.gray_666)).setGroupHeight(ScreenUtils.dip2px(this, 35.0f)).setDivideColor(getResources().getColor(R.color.gray_666)).setDivideHeight(ScreenUtils.dip2px(this, 0.5f)).setGroupTextColor(ViewCompat.MEASURED_STATE_MASK).setGroupTextSize(ScreenUtils.sp2px(this, 18.0f)).setTextSideMargin(ScreenUtils.dip2px(this, 10.0f)).build();
            MyGridLayoutManager myGridLayoutManager = new MyGridLayoutManager(this.context, 1);
            this.layoutManager = myGridLayoutManager;
            this.mRecycler.setLayoutManager(myGridLayoutManager);
            this.mAdapter.setShowFooter(false);
            this.layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.activity.settings.Language.3
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    return 1;
                }
            });
            this.mRecycler.setLayoutManager(this.layoutManager);
            this.mRecycler.addItemDecoration(build);
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(this.onClickListener);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        String[] iSOLanguages = Locale.getISOLanguages();
        int i = 0;
        for (int i2 = 0; i2 < iSOLanguages.length; i2++) {
            if (TextUtils.equals(iSOLanguages[i2], App.deviceLang)) {
                i = i2;
            }
            this.list.add(new Languages(iSOLanguages[i2], iSOLanguages[i2].substring(0, 1).toUpperCase(), TextUtils.equals(iSOLanguages[i2], App.deviceLang)));
        }
        if (this.list.size() > 0) {
            this.mAdapter.notifyDataSetChanged();
        }
        this.mRecycler.scrollToPosition(i);
    }

    public void setSelceted(int i) {
        for (Languages languages : this.list) {
            languages.setExpanded(false);
        }
        this.list.get(i).setExpanded(true);
        this.mAdapter.notifyDataSetChanged();
    }

    /* loaded from: classes3.dex */
    public class ChooseAdapter extends BaseAdapter<Languages> {
        public ChooseAdapter(List<Languages> list) {
            super(list);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public BaseViewHolder getHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, OnItemClickListener onItemClickListener) {
            return new Item1ViewHolder(layoutInflater.inflate(R.layout.layout_language_item, viewGroup, false), onItemClickListener);
        }

        @Override // com.movieboxpro.android.base.BaseAdapter
        public void setView(BaseViewHolder baseViewHolder, int i) {
            if (i < 0 || i > getItemCount() - 1) {
                return;
            }
            Item1ViewHolder item1ViewHolder = (Item1ViewHolder) baseViewHolder;
            Languages model = getModel(i);
            item1ViewHolder.mText.setText(model.getName());
            item1ViewHolder.mSelect.setVisibility(model.isExpanded() ? 0 : 8);
        }
    }

    /* loaded from: classes3.dex */
    static class Item1ViewHolder extends BaseViewHolder {
        @BindView(R.id.language_select)
        ImageView mSelect;
        @BindView(R.id.language_text)
        TextView mText;

        Item1ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view, onItemClickListener);
        }
    }
}
