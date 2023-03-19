package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dueeeke.model.EncodeModel;
import com.dueeeke.model.LanguageModel;
import com.dueeeke.model.SrtPraseModel;
import com.movieboxpro.android.R;
import com.movieboxpro.android.adapter.SimpleSubtitleAdapter;
import com.movieboxpro.android.utils.SettingManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes3.dex */
public class TransCodingSubtitleController extends FrameLayout {
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> codeAdapter;
    private List<LanguageModel> codes;
    private List<EncodeModel> encodeModels;
    private boolean isLocalFile;
    private BaseQuickAdapter<LanguageModel, BaseViewHolder> languageAdapter;
    private List<LanguageModel> languages;
    private int lastCodePosition;
    private int lastPosition;
    private int localSubtitlePosition;
    private Context mContext;
    private RecyclerView mRvCodeType;
    private RecyclerView mRvLanguage;
    private RecyclerView mRvSubtitle;
    private TextView mTvClose;
    private TextView mTvDone;
    private int selectCodePosition;
    private int selectPosition;
    private SimpleSubtitleAdapter subtitleAdapter;
    private TransCodingSubtitleCallback transCodingSubtitleCallback;

    /* loaded from: classes3.dex */
    public interface TransCodingSubtitleCallback {
        void onClose();

        void onDone(List<SrtPraseModel> list, int i, boolean z, List<Subtitle> list2);

        void onEncodeSelected(String str, boolean z);
    }

    public TransCodingSubtitleController(Context context) {
        super(context);
        this.lastPosition = 0;
        this.lastCodePosition = -1;
        this.localSubtitlePosition = -1;
        this.isLocalFile = false;
        this.selectPosition = 0;
        this.selectCodePosition = 0;
        init(context);
    }

    public TransCodingSubtitleController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastPosition = 0;
        this.lastCodePosition = -1;
        this.localSubtitlePosition = -1;
        this.isLocalFile = false;
        this.selectPosition = 0;
        this.selectCodePosition = 0;
        init(context);
    }

    public TransCodingSubtitleController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastPosition = 0;
        this.lastCodePosition = -1;
        this.localSubtitlePosition = -1;
        this.isLocalFile = false;
        this.selectPosition = 0;
        this.selectCodePosition = 0;
        init(context);
    }

    private boolean isScreenLandscape() {
        return getResources().getConfiguration().orientation == 2;
    }

    public void init(Context context) {
        this.mContext = context;
        if (isScreenLandscape()) {
            LayoutInflater.from(this.mContext).inflate(R.layout.fragment_transcoding_subtitle_land, this);
        } else {
            LayoutInflater.from(this.mContext).inflate(R.layout.fragment_transcoding_subtitle, this);
        }
        initView();
        initData();
        initListener();
    }

    public void switchScreenLayout(boolean z) {
        removeAllViews();
        if (z) {
            LayoutInflater.from(this.mContext).inflate(R.layout.fragment_transcoding_subtitle_land, this);
        } else {
            LayoutInflater.from(this.mContext).inflate(R.layout.fragment_transcoding_subtitle, this);
        }
        initView();
        this.mTvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$TransCodingSubtitleController$mFBi8iTJ7l-wlj7vz2xAk5TX9_0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TransCodingSubtitleController.this.lambda$switchScreenLayout$0$TransCodingSubtitleController(view);
            }
        });
        this.mTvClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$TransCodingSubtitleController$WvUJIzc0N0uFhlAa8Ta5KiyiRtA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TransCodingSubtitleController.this.lambda$switchScreenLayout$1$TransCodingSubtitleController(view);
            }
        });
        this.mRvCodeType.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRvLanguage.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRvSubtitle.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRvCodeType.setAdapter(this.codeAdapter);
        this.mRvLanguage.setAdapter(this.languageAdapter);
        this.mRvSubtitle.setAdapter(this.subtitleAdapter);
    }

    public /* synthetic */ void lambda$switchScreenLayout$0$TransCodingSubtitleController(View view) {
        if (this.transCodingSubtitleCallback != null) {
            ArrayList arrayList = new ArrayList();
            for (SrtPraseModel srtPraseModel : this.subtitleAdapter.getData()) {
                Subtitle subtitle = new Subtitle();
                subtitle.content = srtPraseModel.getSrtBody();
                subtitle.start = new Time(srtPraseModel.getBeginTime());
                subtitle.end = new Time(srtPraseModel.getEndTime());
                arrayList.add(subtitle);
            }
            this.transCodingSubtitleCallback.onDone(this.subtitleAdapter.getData(), this.localSubtitlePosition, this.isLocalFile, arrayList);
        }
    }

    public /* synthetic */ void lambda$switchScreenLayout$1$TransCodingSubtitleController(View view) {
        TransCodingSubtitleCallback transCodingSubtitleCallback = this.transCodingSubtitleCallback;
        if (transCodingSubtitleCallback != null) {
            transCodingSubtitleCallback.onClose();
        }
    }

    public void setTransCodingSubtitleCallback(TransCodingSubtitleCallback transCodingSubtitleCallback) {
        this.transCodingSubtitleCallback = transCodingSubtitleCallback;
    }

    protected void initData() {
        this.languages = new ArrayList();
        this.codes = new ArrayList();
        this.subtitleAdapter = new SimpleSubtitleAdapter(new ArrayList());
        this.languageAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_selectable_text_item, this.languages) { // from class: com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, LanguageModel languageModel) {
                TextView textView = (TextView) baseViewHolder.getView(R.id.textView);
                textView.setSelected(languageModel.isSelected);
                textView.setText(languageModel.getLanguage());
            }
        };
        this.codeAdapter = new BaseQuickAdapter<LanguageModel, BaseViewHolder>(R.layout.adapter_encode_item, this.codes) { // from class: com.movieboxpro.android.view.videocontroller.TransCodingSubtitleController.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, LanguageModel languageModel) {
                ImageView imageView = (ImageView) baseViewHolder.getView(R.id.iv_selected);
                if (languageModel.isSelected) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
                baseViewHolder.setText(R.id.textView, languageModel.getLanguage());
            }
        };
        this.mRvSubtitle.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mRvSubtitle.setAdapter(this.subtitleAdapter);
        this.mRvLanguage.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemAnimator itemAnimator = this.mRvLanguage.getItemAnimator();
        if (itemAnimator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
        this.mRvLanguage.setAdapter(this.languageAdapter);
        this.mRvCodeType.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mRvCodeType.setAdapter(this.codeAdapter);
    }

    protected void initView() {
        this.mTvDone = (TextView) findViewById(R.id.tv_done);
        this.mTvClose = (TextView) findViewById(R.id.tv_cancel);
        this.mRvLanguage = (RecyclerView) findViewById(R.id.rv_country);
        this.mRvCodeType = (RecyclerView) findViewById(R.id.rv_code_type);
        this.mRvSubtitle = (RecyclerView) findViewById(R.id.rv_subtitle);
    }

    public void setSubtitleData(List<SrtPraseModel> list, List<EncodeModel> list2) {
        int i = 0;
        this.isLocalFile = false;
        this.localSubtitlePosition = -1;
        this.lastPosition = 0;
        this.lastCodePosition = 0;
        this.subtitleAdapter.setList(list);
        this.languages.clear();
        this.codes.clear();
        sortLanguages(list2);
        for (EncodeModel encodeModel : list2) {
            this.languages.add(new LanguageModel(encodeModel.getLanguage()));
            if (SettingManager.INSTANCE.getTranscodeLanguage().equals(encodeModel.getLanguage())) {
                for (String str : encodeModel.getCode()) {
                    this.codes.add(new LanguageModel(str));
                }
            }
        }
        int i2 = 0;
        while (true) {
            if (i2 >= this.languages.size()) {
                break;
            }
            LanguageModel languageModel = this.languages.get(i2);
            if (SettingManager.INSTANCE.getTranscodeLanguage().equals(languageModel.language)) {
                languageModel.setSelected(true);
                this.selectPosition = i2;
                this.lastPosition = i2;
                break;
            }
            i2++;
        }
        while (true) {
            if (i >= this.codes.size()) {
                break;
            }
            LanguageModel languageModel2 = this.codes.get(i);
            if (SettingManager.INSTANCE.getTranscodeCode().equals(languageModel2.language)) {
                languageModel2.setSelected(true);
                this.lastCodePosition = i;
                break;
            }
            i++;
        }
        this.codeAdapter.notifyDataSetChanged();
        this.languageAdapter.notifyDataSetChanged();
        this.encodeModels = list2;
    }

    public void setSubtitleDataFromLocal(List<SrtPraseModel> list, List<EncodeModel> list2, int i) {
        setSubtitleData(list, list2);
        this.localSubtitlePosition = i;
        this.isLocalFile = true;
    }

    private void sortLanguages(List<EncodeModel> list) {
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            EncodeModel encodeModel = list.get(i);
            if ("Full Unicode".equals(encodeModel.getLanguage())) {
                list.remove(i);
                list.add(0, encodeModel);
                return;
            }
        }
    }

    protected void initListener() {
        this.mTvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$TransCodingSubtitleController$mKui6mJfpMNBnn7kjUHjK0N_aH0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TransCodingSubtitleController.this.lambda$initListener$2$TransCodingSubtitleController(view);
            }
        });
        this.mTvClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$TransCodingSubtitleController$9LMwtPTzb6buRG2Ks2DfuS5E2FQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TransCodingSubtitleController.this.lambda$initListener$3$TransCodingSubtitleController(view);
            }
        });
        this.codeAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$TransCodingSubtitleController$MFkyXAsR1N7Up787OO74HuAHvn8
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TransCodingSubtitleController.this.lambda$initListener$4$TransCodingSubtitleController(baseQuickAdapter, view, i);
            }
        });
        this.languageAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$TransCodingSubtitleController$j1C63Qr7VwIDUpYNCy08kYrvz2U
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TransCodingSubtitleController.this.lambda$initListener$5$TransCodingSubtitleController(baseQuickAdapter, view, i);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$2$TransCodingSubtitleController(View view) {
        if (this.transCodingSubtitleCallback != null) {
            ArrayList arrayList = new ArrayList();
            for (SrtPraseModel srtPraseModel : this.subtitleAdapter.getData()) {
                Subtitle subtitle = new Subtitle();
                subtitle.content = srtPraseModel.getSrtBody();
                subtitle.start = new Time(srtPraseModel.getBeginTime());
                subtitle.end = new Time(srtPraseModel.getEndTime());
                arrayList.add(subtitle);
            }
            this.transCodingSubtitleCallback.onDone(this.subtitleAdapter.getData(), this.localSubtitlePosition, this.isLocalFile, arrayList);
        }
    }

    public /* synthetic */ void lambda$initListener$3$TransCodingSubtitleController(View view) {
        TransCodingSubtitleCallback transCodingSubtitleCallback = this.transCodingSubtitleCallback;
        if (transCodingSubtitleCallback != null) {
            transCodingSubtitleCallback.onClose();
        }
    }

    public /* synthetic */ void lambda$initListener$4$TransCodingSubtitleController(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        LanguageModel item = this.codeAdapter.getItem(i);
        if (this.transCodingSubtitleCallback == null || item == null) {
            return;
        }
        if (!item.isSelected) {
            item.setSelected(true);
            this.codeAdapter.notifyItemChanged(i);
            this.transCodingSubtitleCallback.onEncodeSelected(item.getLanguage(), this.isLocalFile);
            this.selectPosition = this.lastPosition;
        }
        LanguageModel itemOrNull = this.codeAdapter.getItemOrNull(this.lastCodePosition);
        if (itemOrNull != null && this.selectPosition == this.lastPosition && this.lastCodePosition != i) {
            itemOrNull.setSelected(false);
            this.codeAdapter.notifyItemChanged(this.lastCodePosition);
        }
        SettingManager.INSTANCE.saveTranscodeLanguage(this.languageAdapter.getItem(this.lastPosition).language, item.language);
        this.lastCodePosition = i;
    }

    public /* synthetic */ void lambda$initListener$5$TransCodingSubtitleController(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        int i2 = this.lastPosition;
        if (i2 == i) {
            return;
        }
        this.selectCodePosition = this.lastCodePosition;
        LanguageModel item = this.languageAdapter.getItem(i2);
        if (item != null) {
            item.setSelected(false);
            this.languageAdapter.notifyItemChanged(this.lastPosition);
        }
        LanguageModel item2 = this.languageAdapter.getItem(i);
        if (item2 != null) {
            item2.setSelected(true);
            this.languageAdapter.notifyItemChanged(i);
        }
        this.lastPosition = i;
        this.codes.clear();
        List<EncodeModel> list = this.encodeModels;
        if (list != null && list.size() > i) {
            for (String str : this.encodeModels.get(i).getCode()) {
                this.codes.add(new LanguageModel(str));
            }
            if (this.selectPosition == i) {
                this.codes.get(this.selectCodePosition).setSelected(true);
            }
        }
        this.codeAdapter.notifyDataSetChanged();
    }

    public void setSubtitle(List<SrtPraseModel> list) {
        this.subtitleAdapter.setList(list);
    }
}
