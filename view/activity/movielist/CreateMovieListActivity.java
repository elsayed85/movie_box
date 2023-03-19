package com.movieboxpro.android.view.activity.movielist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.gyf.immersionbar.ImmersionBar;
import com.lxj.xpopup.XPopup;
import com.movieboxpro.android.OnSetResultListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.AddMovieListDragAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.model.CreateMovieListModel;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity;
import com.movieboxpro.android.view.activity.movielist.CreateMovieListContract;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.android.view.fragment.movielist.AddFavoriteFragment;
import com.movieboxpro.android.view.fragment.movielist.AddHistoryFragment;
import com.movieboxpro.android.view.fragment.movielist.AddSearchFragment;
import com.movieboxpro.android.view.widget.AddMovieListPopView;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class CreateMovieListActivity extends BaseMvpActivity<CreateMovieListPresenter> implements CreateMovieListContract.View, OnSetResultListener {
    public static final int REQUEST_CODE = 1;
    public static final String SELECT_VIDEOS = "select_videos";
    private AddMovieListDragAdapter adapter;
    private CheckBox checkBox;
    private String cover;
    private File currCoverFile;
    private EditText etDesc;
    private EditText etTitle;
    @BindView(R.id.ivChooseImage)
    ImageView ivChooseImage;
    private ImageView ivClearTitle;
    @BindView(R.id.ivBg)
    ImageView ivCover;
    private String lid;
    @BindView(R.id.llEmptyView)
    LinearLayout llEmptyView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_done)
    TextView tvDone;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    private TextView tvVideoNum;
    private HashMap<String, CreateMovieListModel> deleteList = new HashMap<>();
    private String uid = App.getUserData().uid_v2;
    private int currPage = 1;
    private List<File> cropFiles = new ArrayList();

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_create_movie_list;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return true;
    }

    static /* synthetic */ int access$508(CreateMovieListActivity createMovieListActivity) {
        int i = createMovieListActivity.currPage;
        createMovieListActivity.currPage = i + 1;
        return i;
    }

    public static void start(Context context, boolean z) {
        Intent intent = new Intent(context, CreateMovieListActivity.class);
        intent.putExtra("fromAdd", z);
        context.startActivity(intent);
    }

    public static void start(Context context, String str) {
        Intent intent = new Intent(context, CreateMovieListActivity.class);
        intent.putExtra("lid", str);
        context.startActivity(intent);
    }

    public static void start(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent(activity, CreateMovieListActivity.class);
        intent.putExtra("lid", str);
        intent.putExtra("cover", str2);
        activity.startActivityForResult(intent, i);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        this.adapter.setOnItemChildClickListener(new OnItemChildClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$CreateMovieListActivity$fYHi1Vv6tFi1LY0q1dNg0R2HBOU
            @Override // com.chad.library.adapter.base.listener.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                CreateMovieListActivity.this.lambda$initListener$0$CreateMovieListActivity(baseQuickAdapter, view, i);
            }
        });
        this.adapter.setOnAfterEditTextChangedListener(new AddMovieListDragAdapter.OnAfterEditTextChangedListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$CreateMovieListActivity$033lOMtuKwy-WEHP11cKrHBzvW4
            @Override // com.movieboxpro.android.adapter.AddMovieListDragAdapter.OnAfterEditTextChangedListener
            public final void onTextChanged(int i, String str) {
                CreateMovieListActivity.this.lambda$initListener$1$CreateMovieListActivity(i, str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$CreateMovieListActivity(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        NormalFilmModel item = this.adapter.getItem(i);
        CreateMovieListModel createMovieListModel = new CreateMovieListModel();
        createMovieListModel.setBox_type(item.getBox_type());
        createMovieListModel.setMid(String.valueOf(item.getId()));
        HashMap<String, CreateMovieListModel> hashMap = this.deleteList;
        hashMap.put(item.getId() + "_" + item.getBox_type(), createMovieListModel);
        this.adapter.removeAt(i);
        this.tvVideoNum.setText(String.format("%s videos", Integer.valueOf(this.adapter.getData().size())));
        switchEmptyView();
    }

    public /* synthetic */ void lambda$initListener$1$CreateMovieListActivity(int i, String str) {
        NormalFilmModel item = this.adapter.getItem(i);
        if (item != null) {
            item.setMark(str);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        ImmersionBar.with(this).autoDarkModeEnable(true).transparentStatusBar().init();
        View inflate = getLayoutInflater().inflate(R.layout.create_movie_list_header, (ViewGroup) this.recyclerView.getParent(), false);
        this.checkBox = (CheckBox) inflate.findViewById(R.id.checkbox);
        this.etTitle = (EditText) inflate.findViewById(R.id.et_title);
        this.etDesc = (EditText) inflate.findViewById(R.id.et_desc);
        this.tvVideoNum = (TextView) inflate.findViewById(R.id.tvVideoNum);
        this.ivClearTitle = (ImageView) inflate.findViewById(R.id.ivClearTitle);
        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAdd);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new XPopup.Builder(CreateMovieListActivity.this).hasShadowBg(false).isDestroyOnDismiss(true).atView(imageView).offsetX(30).asCustom(new AddMovieListPopView(CreateMovieListActivity.this, new C00891())).show();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity$1$1  reason: invalid class name and collision with other inner class name */
            /* loaded from: classes3.dex */
            public class C00891 implements AddMovieListPopView.AddMovieListListener {
                C00891() {
                }

                @Override // com.movieboxpro.android.view.widget.AddMovieListPopView.AddMovieListListener
                public void onSearch() {
                    AddSearchFragment newInstance = AddSearchFragment.newInstance(new ArrayList(CreateMovieListActivity.this.adapter.getData()));
                    newInstance.setCheckedFilms(CreateMovieListActivity.this.adapter.getData());
                    newInstance.setResultListener(CreateMovieListActivity.this);
                    newInstance.show(CreateMovieListActivity.this.getSupportFragmentManager(), "Search");
                }

                @Override // com.movieboxpro.android.view.widget.AddMovieListPopView.AddMovieListListener
                public void onFavorite() {
                    AddFavoriteFragment addFavoriteFragment = new AddFavoriteFragment();
                    addFavoriteFragment.setListener(CreateMovieListActivity.this);
                    addFavoriteFragment.setNormalFilmModels(CreateMovieListActivity.this.adapter.getData());
                    addFavoriteFragment.show(CreateMovieListActivity.this.getSupportFragmentManager(), "Favorite");
                }

                @Override // com.movieboxpro.android.view.widget.AddMovieListPopView.AddMovieListListener
                public void onHistory() {
                    AddHistoryFragment addHistoryFragment = new AddHistoryFragment();
                    addHistoryFragment.setListener(CreateMovieListActivity.this);
                    addHistoryFragment.setNormalFilmModels(CreateMovieListActivity.this.adapter.getData());
                    addHistoryFragment.show(CreateMovieListActivity.this.getSupportFragmentManager(), "History");
                }

                @Override // com.movieboxpro.android.view.widget.AddMovieListPopView.AddMovieListListener
                public void onDelete() {
                    new MsgHintDialog.Builder(CreateMovieListActivity.this).setTitle("Note").setContent("Are you sure to delete it?").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$CreateMovieListActivity$1$1$UwwRGXCHlmHRY2giM9UUMQzAl5g
                        @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                        public final void onClick() {
                            CreateMovieListActivity.AnonymousClass1.C00891.this.lambda$onDelete$0$CreateMovieListActivity$1$1();
                        }
                    }).create().show();
                }

                public /* synthetic */ void lambda$onDelete$0$CreateMovieListActivity$1$1() {
                    ((CreateMovieListPresenter) CreateMovieListActivity.this.mPresenter).doDeleteList(CreateMovieListActivity.this.uid, CreateMovieListActivity.this.lid);
                }
            }
        });
        this.etTitle.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(charSequence)) {
                    CreateMovieListActivity.this.ivClearTitle.setVisibility(4);
                } else {
                    CreateMovieListActivity.this.ivClearTitle.setVisibility(0);
                }
                SpanUtils.with(CreateMovieListActivity.this.tvTitle).append(charSequence).setFontSize(14, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.white)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).setBold().create();
            }
        });
        this.ivClearTitle.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.movielist.-$$Lambda$CreateMovieListActivity$9mBzr1SK90SNxTScNdyZgxqnl_s
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CreateMovieListActivity.this.lambda$initView$2$CreateMovieListActivity(view);
            }
        });
        this.adapter.addHeaderView(inflate);
        DensityUtils.addMarginTopEqualStatusBarHeight(this.rlTop, this);
    }

    public /* synthetic */ void lambda$initView$2$CreateMovieListActivity(View view) {
        this.etTitle.setText("");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        AddMovieListDragAdapter addMovieListDragAdapter = new AddMovieListDragAdapter(R.layout.adapter_drag_movie_item, new ArrayList());
        this.adapter = addMovieListDragAdapter;
        addMovieListDragAdapter.addChildClickViewIds(R.id.iv_remove);
        this.adapter.getDraggableModule().setDragEnabled(true);
        this.adapter.getDraggableModule().setSwipeEnabled(false);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adapter);
        this.lid = getIntent().getStringExtra("lid");
        String stringExtra = getIntent().getStringExtra("cover");
        this.cover = stringExtra;
        if (!TextUtils.isEmpty(stringExtra)) {
            GlideUtils.load((Activity) this, this.cover, this.ivCover, (int) R.drawable.default_movie_list_pic);
            this.ivChooseImage.setVisibility(0);
        }
        this.adapter.getLoadMore().setAutoLoadMore(true);
        this.adapter.getLoadMore().setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.movieboxpro.android.view.activity.movielist.CreateMovieListActivity.3
            @Override // com.chad.library.adapter.base.listener.OnLoadMoreListener
            public void onLoadMore() {
                CreateMovieListActivity.access$508(CreateMovieListActivity.this);
                ((CreateMovieListPresenter) CreateMovieListActivity.this.mPresenter).loadList(CreateMovieListActivity.this.uid, CreateMovieListActivity.this.lid, CreateMovieListActivity.this.currPage, 10, CreateMovieListActivity.this.adapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public CreateMovieListPresenter bindPresenter() {
        return new CreateMovieListPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        if (!TextUtils.isEmpty(this.lid)) {
            ((CreateMovieListPresenter) this.mPresenter).loadList(this.uid, this.lid, 1, 10, this.adapter);
        } else {
            hideLoading();
        }
    }

    @OnClick({R.id.iv_cancel, R.id.tv_done, R.id.ivChooseImage})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ivChooseImage) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (NormalFilmModel normalFilmModel : this.adapter.getData()) {
                arrayList.add(normalFilmModel.getPoster());
            }
            ChooseMovieListCoverActivity.Companion.start(this, arrayList, 1);
        } else if (id == R.id.iv_cancel) {
            finish();
        } else if (id == R.id.tv_done) {
            doCreateList();
        }
    }

    private void doCreateList() {
        String obj = this.etTitle.getText().toString();
        String obj2 = this.etDesc.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastUtils.showShort("Title cannot be blank");
            return;
        }
        List<NormalFilmModel> data = this.adapter.getData();
        if (data.size() == 0) {
            ToastUtils.showShort("Playlist should contains at least 1 movie or tv");
        } else if (TextUtils.isEmpty(this.cover) && this.currCoverFile == null) {
            ToastUtils.showShort("Please choose a cover");
        } else {
            int i = !this.checkBox.isChecked();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < data.size(); i2++) {
                CreateMovieListModel createMovieListModel = new CreateMovieListModel();
                createMovieListModel.setBox_type(data.get(i2).getBox_type());
                createMovieListModel.setMid(String.valueOf(data.get(i2).getId()));
                createMovieListModel.setMark(data.get(i2).getMark());
                arrayList.add(createMovieListModel);
            }
            if (TextUtils.isEmpty(this.lid)) {
                ((CreateMovieListPresenter) this.mPresenter).doCreateList(obj, obj2, i, this.lid, arrayList, this.currCoverFile);
            } else if (this.currCoverFile != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Map.Entry<String, CreateMovieListModel> entry : this.deleteList.entrySet()) {
                    arrayList2.add(entry.getValue());
                }
                ((CreateMovieListPresenter) this.mPresenter).uploadCover(obj, obj2, i, this.lid, arrayList, this.currCoverFile, arrayList2);
            } else {
                ArrayList arrayList3 = new ArrayList();
                for (Map.Entry<String, CreateMovieListModel> entry2 : this.deleteList.entrySet()) {
                    arrayList3.add(entry2.getValue());
                }
                ((CreateMovieListPresenter) this.mPresenter).doEditList(obj, obj2, i, this.lid, arrayList, null, arrayList3);
            }
        }
    }

    private void switchEmptyView() {
        if (this.adapter.getData().size() == 0) {
            this.llEmptyView.setVisibility(0);
            this.ivChooseImage.setVisibility(8);
            return;
        }
        this.llEmptyView.setVisibility(8);
        this.ivChooseImage.setVisibility(0);
    }

    @Override // com.movieboxpro.android.OnSetResultListener
    public void onSetResult(List<NormalFilmModel> list) {
        this.adapter.setList(list);
        switchEmptyView();
        if (list != null) {
            this.tvVideoNum.setText(String.format("%s videos", Integer.valueOf(list.size())));
        }
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.View
    public void doCreateResult(boolean z, String str) {
        if (z) {
            EventUtils.event("Movielist创建");
            ToastUtils.showShort("Publish success");
            EventBus.getDefault().post(new RefreshMovieListEvent());
            setResult(-1);
            finish();
            return;
        }
        ToastUtils.showShort("Publish error");
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.View
    public void doDeleteListResult(boolean z, String str) {
        if (z) {
            EventUtils.event("Movielist删除");
            ToastUtils.showShort("Delete success");
            EventBus.getDefault().post(new RefreshMovieListEvent());
            setResult(-1);
            finish();
            return;
        }
        ToastUtils.showShort("Delete fail");
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.View
    public void loadMoreDataComplete(MovieListDetailModel movieListDetailModel) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < movieListDetailModel.getList().size(); i++) {
            NormalFilmModel normalFilmModel = (NormalFilmModel) JSON.parseObject(JSON.toJSONString(movieListDetailModel.getList().get(i)), NormalFilmModel.class);
            normalFilmModel.setChecked(true);
            arrayList.add(normalFilmModel);
        }
        this.adapter.addData((Collection) arrayList);
    }

    @Override // com.movieboxpro.android.view.activity.movielist.CreateMovieListContract.View
    public void loadDataComplete(MovieListDetailModel movieListDetailModel) {
        this.etTitle.setText(movieListDetailModel.getName());
        this.etTitle.setSelection(movieListDetailModel.getName().length());
        this.etDesc.setText(movieListDetailModel.getDesc());
        this.etDesc.setSelection(movieListDetailModel.getDesc().length());
        if (movieListDetailModel.getStatus() == 1) {
            this.checkBox.setChecked(false);
        } else {
            this.checkBox.setChecked(true);
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < movieListDetailModel.getList().size(); i++) {
            NormalFilmModel normalFilmModel = (NormalFilmModel) JSON.parseObject(JSON.toJSONString(movieListDetailModel.getList().get(i)), NormalFilmModel.class);
            normalFilmModel.setChecked(true);
            arrayList.add(normalFilmModel);
        }
        this.tvVideoNum.setText(String.format("%s videos", Integer.valueOf(movieListDetailModel.getCount())));
        this.adapter.addData((Collection) arrayList);
        switchEmptyView();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        for (File file : this.cropFiles) {
            file.delete();
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1 && intent != null) {
            Uri uri = (Uri) intent.getParcelableExtra("uri");
            this.cropFiles.add(new File(uri.getPath()));
            this.currCoverFile = new File(uri.getPath());
            GlideUtils.load((Activity) this, uri.getPath(), this.ivCover);
        }
    }
}
