package com.movieboxpro.android.view.activity.managesubtitle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.R;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.model.DirectoryModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.EventUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
/* compiled from: ManageSubtitleActivity.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u0000 %2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001%B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0002H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0016J\b\u0010\u001a\u001a\u00020\nH\u0014J\b\u0010\u001b\u001a\u00020\u0017H\u0014J\b\u0010\u001c\u001a\u00020\u0017H\u0014J\b\u0010\u001d\u001a\u00020\u0017H\u0014J\b\u0010\u001e\u001a\u00020\u000fH\u0014J\b\u0010\u001f\u001a\u00020\u0017H\u0014J\b\u0010 \u001a\u00020\u0017H\u0002J\u0016\u0010!\u001a\u00020\u00172\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#H\u0016J\b\u0010$\u001a\u00020\u0017H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitlePresenter;", "Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleContract$View;", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/DirectoryModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "value", "", "checkedCount", "setCheckedCount", "(I)V", "isEdit", "", "lastPath", "", "pathStack", "Ljava/util/Stack;", "Ljava/io/File;", "bindPresenter", "changeListCheckState", "", "deleteCheckedFile", "deleteFileComplete", "getLayoutResId", "initData", "initListener", "initView", "isNeedLoadData", "requestData", "setEmptyView", "showFiles", "list", "", "startAnimator", "Companion", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ManageSubtitleActivity extends BaseMvpActivity<ManageSubtitlePresenter> implements ManageSubtitleContract.View {
    private static final String ALL_SUBTITLE_DIR = "all_subtitle_dir";
    public static final Companion Companion = new Companion(null);
    private BaseQuickAdapter<DirectoryModel, BaseViewHolder> adapter;
    private int checkedCount;
    private boolean isEdit;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private Stack<File> pathStack = new Stack<>();
    private String lastPath = ALL_SUBTITLE_DIR;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_manage_subtitle;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
    }

    private final void setCheckedCount(int i) {
        this.checkedCount = i;
        if (i == 0) {
            TextView textView = (TextView) _$_findCachedViewById(R.id.tvDelete);
            if (textView != null) {
                textView.setText("Delete");
            }
            TextView textView2 = (TextView) _$_findCachedViewById(R.id.tvDelete);
            if (textView2 != null) {
                textView2.setEnabled(false);
            }
            TextView textView3 = (TextView) _$_findCachedViewById(R.id.tvAll);
            if (textView3 == null) {
                return;
            }
            textView3.setText("Select All");
            return;
        }
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        if (i == baseQuickAdapter.getData().size()) {
            TextView textView4 = (TextView) _$_findCachedViewById(R.id.tvAll);
            if (textView4 != null) {
                textView4.setText("Deselect All");
            }
            TextView textView5 = (TextView) _$_findCachedViewById(R.id.tvDelete);
            if (textView5 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("Delete (%s)", Arrays.copyOf(new Object[]{Integer.valueOf(this.checkedCount)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                textView5.setText(format);
            }
            TextView textView6 = (TextView) _$_findCachedViewById(R.id.tvDelete);
            if (textView6 == null) {
                return;
            }
            textView6.setEnabled(true);
            return;
        }
        TextView textView7 = (TextView) _$_findCachedViewById(R.id.tvDelete);
        if (textView7 != null) {
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("Delete (%s)", Arrays.copyOf(new Object[]{Integer.valueOf(this.checkedCount)}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            textView7.setText(format2);
        }
        TextView textView8 = (TextView) _$_findCachedViewById(R.id.tvDelete);
        if (textView8 != null) {
            textView8.setEnabled(true);
        }
        TextView textView9 = (TextView) _$_findCachedViewById(R.id.tvAll);
        if (textView9 == null) {
            return;
        }
        textView9.setText("Select All");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ImageView) _$_findCachedViewById(R.id.ivBack)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitleActivity$ZSKI1eH5wRlS-Nfi0VYbXKWApuE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageSubtitleActivity.m365initListener$lambda0(ManageSubtitleActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvEdit)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitleActivity$k_x8ZgysXSMfjH4US9oEwZMV-1k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageSubtitleActivity.m366initListener$lambda1(ManageSubtitleActivity.this, view);
            }
        });
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitleActivity$3IVUJdDLuU6yyWJC_vAXugCKB6Q
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                ManageSubtitleActivity.m367initListener$lambda2(ManageSubtitleActivity.this, baseQuickAdapter2, view, i);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvAll)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitleActivity$4mQidy-sQkUlB-Cu3w08Y9rGLMU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageSubtitleActivity.m368initListener$lambda5(ManageSubtitleActivity.this, view);
            }
        });
        ((TextView) _$_findCachedViewById(R.id.tvDelete)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.activity.managesubtitle.-$$Lambda$ManageSubtitleActivity$4xx4hYJ3Lb_i8jyOum1HQLkvhOU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ManageSubtitleActivity.m369initListener$lambda6(ManageSubtitleActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-0  reason: not valid java name */
    public static final void m365initListener$lambda0(ManageSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.pathStack.isEmpty()) {
            this$0.finish();
            return;
        }
        File pop = this$0.pathStack.pop();
        String path = pop.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "file.path");
        this$0.lastPath = path;
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
        if (Intrinsics.areEqual(pop.getPath(), ALL_SUBTITLE_DIR)) {
            TextView tvTitle = (TextView) this$0._$_findCachedViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
            CommonExtKt.visible(tvTitle);
            TextView tvDirName = (TextView) this$0._$_findCachedViewById(R.id.tvDirName);
            Intrinsics.checkNotNullExpressionValue(tvDirName, "tvDirName");
            CommonExtKt.gone(tvDirName);
            ((ManageSubtitlePresenter) this$0.mPresenter).loadAllSubtitleFiles();
        } else {
            String path2 = pop.getPath();
            Intrinsics.checkNotNullExpressionValue(path2, "file.path");
            ((ManageSubtitlePresenter) this$0.mPresenter).loadSubtitleFiles(path2);
        }
        ((TextView) this$0._$_findCachedViewById(R.id.tvDirName)).setText(pop.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-1  reason: not valid java name */
    public static final void m366initListener$lambda1(ManageSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = !this$0.isEdit;
        this$0.isEdit = z;
        if (z) {
            ((TextView) this$0._$_findCachedViewById(R.id.tvEdit)).setText("Done");
        } else {
            ((TextView) this$0._$_findCachedViewById(R.id.tvEdit)).setText("Edit");
        }
        this$0.changeListCheckState();
        this$0.startAnimator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-2  reason: not valid java name */
    public static final void m367initListener$lambda2(ManageSubtitleActivity this$0, BaseQuickAdapter adapter, View noName_1, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
        Object item = adapter.getItem(i);
        if (item == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.movieboxpro.android.model.DirectoryModel");
        }
        DirectoryModel directoryModel = (DirectoryModel) item;
        if (this$0.isEdit) {
            directoryModel.setChecked(!directoryModel.getChecked());
            adapter.notifyItemChanged(i);
            this$0.setCheckedCount(this$0.checkedCount + (directoryModel.getChecked() ? 1 : -1));
        } else if (directoryModel.isDir()) {
            ((ManageSubtitlePresenter) this$0.mPresenter).loadSubtitleFiles(directoryModel.getPath());
            this$0.pathStack.add(new File(this$0.lastPath));
            this$0.lastPath = directoryModel.getPath();
            adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
            TextView tvDirName = (TextView) this$0._$_findCachedViewById(R.id.tvDirName);
            Intrinsics.checkNotNullExpressionValue(tvDirName, "tvDirName");
            CommonExtKt.visible(tvDirName);
            TextView tvTitle = (TextView) this$0._$_findCachedViewById(R.id.tvTitle);
            Intrinsics.checkNotNullExpressionValue(tvTitle, "tvTitle");
            CommonExtKt.gone(tvTitle);
            ((TextView) this$0._$_findCachedViewById(R.id.tvDirName)).setText(directoryModel.getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-5  reason: not valid java name */
    public static final void m368initListener$lambda5(ManageSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.checkedCount;
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this$0.adapter;
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        if (i == baseQuickAdapter.getData().size()) {
            BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter3 = this$0.adapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter3 = null;
            }
            for (DirectoryModel directoryModel : baseQuickAdapter3.getData()) {
                directoryModel.setChecked(false);
            }
            this$0.setCheckedCount(0);
        } else {
            BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter4 = this$0.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter4 = null;
            }
            for (DirectoryModel directoryModel2 : baseQuickAdapter4.getData()) {
                directoryModel2.setChecked(true);
            }
            BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter5 = this$0.adapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter5 = null;
            }
            this$0.setCheckedCount(baseQuickAdapter5.getData().size());
        }
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter6 = this$0.adapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter6;
        }
        baseQuickAdapter2.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initListener$lambda-6  reason: not valid java name */
    public static final void m369initListener$lambda6(ManageSubtitleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.deleteCheckedFile();
    }

    private final void deleteCheckedFile() {
        ManageSubtitlePresenter manageSubtitlePresenter = (ManageSubtitlePresenter) this.mPresenter;
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        manageSubtitlePresenter.deleteFile(baseQuickAdapter.getData());
    }

    @Override // com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract.View
    public void deleteFileComplete() {
        ((ManageSubtitlePresenter) this.mPresenter).loadSubtitleFiles(this.lastPath);
    }

    private final void changeListCheckState() {
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        for (DirectoryModel directoryModel : baseQuickAdapter.getData()) {
            directoryModel.setEdit(this.isEdit);
        }
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.notifyDataSetChanged();
    }

    private final void startAnimator() {
        TranslateAnimation translateAnimation;
        if (this.isEdit) {
            LinearLayout llController = (LinearLayout) _$_findCachedViewById(R.id.llController);
            Intrinsics.checkNotNullExpressionValue(llController, "llController");
            CommonExtKt.visible(llController);
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        } else {
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleActivity$startAnimator$1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    LinearLayout llController2 = (LinearLayout) ManageSubtitleActivity.this._$_findCachedViewById(R.id.llController);
                    Intrinsics.checkNotNullExpressionValue(llController2, "llController");
                    CommonExtKt.gone(llController2);
                }
            });
        }
        translateAnimation.setDuration(200L);
        ((LinearLayout) _$_findCachedViewById(R.id.llController)).startAnimation(translateAnimation);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        ((TextView) _$_findCachedViewById(R.id.tvTitle)).setText("My Subtitle Files");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        ManageSubtitleActivity manageSubtitleActivity = this;
        CommonExtKt.onMobEvent(manageSubtitleActivity, "SubtitlesManage");
        EventUtils.event("进入字幕管理");
        final ArrayList arrayList = new ArrayList();
        this.adapter = new BaseQuickAdapter<DirectoryModel, BaseViewHolder>(arrayList) { // from class: com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleActivity$initData$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, DirectoryModel item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                CheckBox checkBox = (CheckBox) holder.getView(R.id.checkbox);
                ImageView imageView = (ImageView) holder.getView(R.id.ivDir);
                TextView textView = (TextView) holder.getView(R.id.tvDirName);
                checkBox.setChecked(item.getChecked());
                CheckBox checkBox2 = checkBox;
                if (item.isEdit()) {
                    CommonExtKt.visible(checkBox2);
                } else {
                    CommonExtKt.gone(checkBox2);
                }
                if (item.isDir()) {
                    GlideUtils.load(getContext(), (int) R.mipmap.ic_blue_dir, imageView);
                } else {
                    GlideUtils.load(getContext(), (int) R.mipmap.ic_subtitle_file, imageView);
                }
                textView.setText(item.getName());
            }
        };
        ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).setLayoutManager(new LinearLayoutManager(manageSubtitleActivity));
        RecyclerView.ItemAnimator itemAnimator = ((RecyclerView) _$_findCachedViewById(R.id.recyclerView)).getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(R.id.recyclerView);
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        ((ManageSubtitlePresenter) this.mPresenter).loadAllSubtitleFiles();
    }

    @Override // com.movieboxpro.android.view.activity.managesubtitle.ManageSubtitleContract.View
    public void showFiles(List<DirectoryModel> list) {
        String name;
        Intrinsics.checkNotNullParameter(list, "list");
        for (DirectoryModel directoryModel : list) {
            if (!directoryModel.isDir()) {
                List split$default = StringsKt.split$default((CharSequence) directoryModel.getName(), new String[]{"_"}, false, 0, 6, (Object) null);
                if (split$default.size() >= 2) {
                    name = (String) split$default.get(1);
                } else {
                    name = directoryModel.getName();
                }
                directoryModel.setName(name);
            }
        }
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(new ArrayList(list));
        if (list.isEmpty()) {
            setEmptyView();
        }
        setCheckedCount(0);
    }

    private final void setEmptyView() {
        BaseQuickAdapter<DirectoryModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setEmptyView((int) R.layout.file_empty_layout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public ManageSubtitlePresenter bindPresenter() {
        return new ManageSubtitlePresenter(this);
    }

    /* compiled from: ManageSubtitleActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/activity/managesubtitle/ManageSubtitleActivity$Companion;", "", "()V", "ALL_SUBTITLE_DIR", "", "starter", "", "context", "Landroid/content/Context;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void starter(Context context) {
            if (context == null) {
                return;
            }
            context.startActivity(new Intent(context, ManageSubtitleActivity.class));
        }
    }
}
