package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.SRTModel;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.model.SubTitleFeedbackModel;
import com.movieboxpro.android.R;
import com.movieboxpro.android.event.TransformSubtitleDataEvent;
import com.movieboxpro.android.view.videocontroller.fragment.TranslateSubtitleActivity;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
/* loaded from: classes3.dex */
public class LrcCheckController extends FrameLayout {
    private BaseQuickAdapter<SubTitleFeedbackModel, BaseViewHolder> adapter;
    private TextView confirm;
    private List<SubTitleFeedbackModel> feedbackModels;
    private FrameLayout flSelect;
    private boolean haveTranslate;
    public boolean isFrist;
    public boolean isTouch;
    private ImageView ivTranslate;
    private LinearLayout llUserSelected;
    public ListView lrcView;
    private SubtitleFeedbackCallback mCallback;
    public Context mContext;
    private List<SrtPraseModel> mList;
    private List<SrtPraseModel> originSubtitles;
    private RecyclerView recyclerView;
    private int selectedPosition;
    private SRTModel.SubTitles subTitles;
    private BaseQuickAdapter<SrtPraseModel, BaseViewHolder> subtitleAdapter;
    private String subtitleContent;
    private List<String> subtitls;
    private List<SrtPraseModel> translateSubtitle;
    private TextView tvUserSelected;

    /* loaded from: classes3.dex */
    public interface SubtitleFeedbackCallback {
        void onCancel();

        void onSelected(SrtPraseModel srtPraseModel, boolean z, List<SrtPraseModel> list);

        void onVoteClick(int i);
    }

    public LrcCheckController(Context context) {
        super(context);
        this.mList = new ArrayList();
        this.feedbackModels = new ArrayList();
        this.originSubtitles = new ArrayList();
        this.isFrist = false;
        this.isTouch = false;
        init(context);
    }

    public LrcCheckController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mList = new ArrayList();
        this.feedbackModels = new ArrayList();
        this.originSubtitles = new ArrayList();
        this.isFrist = false;
        this.isTouch = false;
        init(context);
    }

    public LrcCheckController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mList = new ArrayList();
        this.feedbackModels = new ArrayList();
        this.originSubtitles = new ArrayList();
        this.isFrist = false;
        this.isTouch = false;
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.check_lrc_layout, this);
        initView();
        initListener();
    }

    private void initView() {
        this.llUserSelected = (LinearLayout) findViewById(R.id.ll_user_selected);
        this.tvUserSelected = (TextView) findViewById(R.id.tv_user_selected_count);
        this.ivTranslate = (ImageView) findViewById(R.id.ivTranslate);
        this.flSelect = (FrameLayout) findViewById(R.id.flSelect);
        this.confirm = (TextView) findViewById(R.id.tv_select);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    }

    private void initListener() {
        ((TextView) findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$LrcCheckController$0jgIp1t-SfK77t7voZA3ZtQs-TY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LrcCheckController.this.lambda$initListener$0$LrcCheckController(view);
            }
        });
        this.flSelect.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$LrcCheckController$oSTLwCGdAN-jxWMCKh7DIaKd9QU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LrcCheckController.this.lambda$initListener$1$LrcCheckController(view);
            }
        });
        this.ivTranslate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$LrcCheckController$qUmLVtdB4a76SAP_KwTB6jJNrMY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LrcCheckController.this.lambda$initListener$2$LrcCheckController(view);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$LrcCheckController(View view) {
        setVisibility(8);
        SubtitleFeedbackCallback subtitleFeedbackCallback = this.mCallback;
        if (subtitleFeedbackCallback != null) {
            subtitleFeedbackCallback.onCancel();
        }
    }

    public /* synthetic */ void lambda$initListener$1$LrcCheckController(View view) {
        int i;
        if (this.mCallback == null || (i = this.selectedPosition) < 0 || i >= this.mList.size()) {
            return;
        }
        this.mCallback.onSelected(this.mList.get(this.selectedPosition), this.haveTranslate, this.mList);
    }

    public /* synthetic */ void lambda$initListener$2$LrcCheckController(View view) {
        if (this.subTitles != null) {
            EventBus.getDefault().postSticky(new TransformSubtitleDataEvent(this.subtitleContent, this.subTitles.lang, new ArrayList()));
            TranslateSubtitleActivity.Companion.start(this.mContext, this.subtitleContent, this.subTitles.lang);
        }
    }

    private void initSubtitle(int i) {
        this.subtitleAdapter = new BaseQuickAdapter<SrtPraseModel, BaseViewHolder>(R.layout.adapter_simple_subtitle_item, this.mList) { // from class: com.movieboxpro.android.view.videocontroller.LrcCheckController.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, SrtPraseModel srtPraseModel) {
                if (srtPraseModel != null && srtPraseModel.getSrtBody() != null) {
                    baseViewHolder.setText(R.id.textView, Html.fromHtml(srtPraseModel.getSrtBody()).toString());
                } else {
                    baseViewHolder.setText(R.id.textView, "");
                }
            }
        };
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.recyclerView.setAdapter(this.subtitleAdapter);
    }

    private void initFeedback(List<SubTitleFeedbackModel> list) {
        this.feedbackModels.clear();
        this.feedbackModels.addAll(list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.feedback_recyclerView);
        this.adapter = new BaseQuickAdapter<SubTitleFeedbackModel, BaseViewHolder>(R.layout.subtitle_feedback_item, this.feedbackModels) { // from class: com.movieboxpro.android.view.videocontroller.LrcCheckController.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, SubTitleFeedbackModel subTitleFeedbackModel) {
                baseViewHolder.setText(R.id.tv_num, String.valueOf(subTitleFeedbackModel.getTotal()));
                baseViewHolder.setText(R.id.tv_title, subTitleFeedbackModel.getType_name());
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        recyclerView.setAdapter(this.adapter);
        this.adapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$LrcCheckController$debSo1SQ3fOeKnW0woLwrN1M928
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                LrcCheckController.this.lambda$initFeedback$3$LrcCheckController(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$initFeedback$3$LrcCheckController(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        SubtitleFeedbackCallback subtitleFeedbackCallback = this.mCallback;
        if (subtitleFeedbackCallback != null) {
            subtitleFeedbackCallback.onVoteClick(this.feedbackModels.get(i).getType());
        }
    }

    public void voteSuccess(int i) {
        for (int i2 = 0; i2 < this.feedbackModels.size(); i2++) {
            SubTitleFeedbackModel subTitleFeedbackModel = this.feedbackModels.get(i2);
            if (i == subTitleFeedbackModel.getType()) {
                subTitleFeedbackModel.setTotal(subTitleFeedbackModel.getTotal() + 1);
                this.adapter.notifyItemChanged(i2);
                return;
            }
        }
    }

    public void setDataList(String str, int i, int i2, List<SubTitleFeedbackModel> list, List<SrtPraseModel> list2, SRTModel.SubTitles subTitles, SubtitleFeedbackCallback subtitleFeedbackCallback) {
        this.subtitleContent = str;
        this.originSubtitles.clear();
        this.originSubtitles.addAll(list2);
        this.subTitles = subTitles;
        if (subTitles == null) {
            this.ivTranslate.setVisibility(8);
        }
        if (i2 > 0) {
            this.llUserSelected.setVisibility(0);
            this.tvUserSelected.setText(String.valueOf(i2));
        }
        this.mCallback = subtitleFeedbackCallback;
        this.mList = list2;
        initFeedback(list);
        initSubtitle(i);
        this.ivTranslate.setImageResource(R.mipmap.ic_circle_translate_normal);
        this.haveTranslate = false;
    }

    public void setTranslateSubtitle(List<SrtPraseModel> list) {
        this.translateSubtitle = list;
        this.haveTranslate = true;
        this.mList = list;
        this.subtitleAdapter.setNewData(list);
        this.ivTranslate.setImageResource(R.mipmap.ic_circle_translate_selected);
    }

    public void setConfirmText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.flSelect.setVisibility(8);
            this.confirm.setVisibility(8);
            return;
        }
        this.flSelect.setVisibility(0);
        this.confirm.setVisibility(0);
        this.confirm.setText(str);
    }

    public void setToPosition(int i) {
        if (this.mList == null || this.isFrist) {
            return;
        }
        this.lrcView.setSelection(i);
    }
}
