package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.dueeeke.adapter.AdapterMediaQuality;
import com.dueeeke.adapter.AdapterSRTLanguage;
import com.dueeeke.model.LanguageModel;
import com.dueeeke.model.SRTModel;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.movieboxpro.android.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes3.dex */
public class CcController extends FrameLayout {
    private FrameLayout container;
    List<SRTModel.SubTitles> getstr;
    private List<LanguageModel> languages;
    private LikeStatusListener likeStatusListener;
    private AdapterMediaQuality mCCAdapter;
    private ListView mCCCountrySelect;
    private AdapterSRTLanguage mCCLanguageAdapter;
    private ListView mCCListSelect;
    private CcCallBack mCallback;
    private Context mContext;
    private int mCorrentLanguage;
    private int mCurSelectSRT;
    private int mSrtSlelct;
    private OnShowFragmentListener showFragmentListener;
    private LinkedHashMap<String, List<SRTModel.SubTitles>> stringListHashMap;

    /* loaded from: classes3.dex */
    public interface CcCallBack {
        void onDisSelectedCc(String str);

        void onSelectedCc(int i, SRTModel.SubTitles subTitles);

        void onViewSubtitleClick(int i, SRTModel.SubTitles subTitles);
    }

    /* loaded from: classes3.dex */
    public interface LikeStatusListener {
        void onLikeStatus(int i, String str, int i2);
    }

    /* loaded from: classes3.dex */
    public interface OnShowFragmentListener {
        void showFragment(int i);
    }

    public int getMoreSubtitleFragmentContinerId() {
        return R.id.searUploadSubtitleFrameLayout;
    }

    public CcController(Context context) {
        super(context);
        this.languages = new ArrayList();
        this.stringListHashMap = new LinkedHashMap<>();
        this.getstr = new ArrayList();
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        init(context);
    }

    public CcController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.languages = new ArrayList();
        this.stringListHashMap = new LinkedHashMap<>();
        this.getstr = new ArrayList();
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        init(context);
    }

    public CcController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.languages = new ArrayList();
        this.stringListHashMap = new LinkedHashMap<>();
        this.getstr = new ArrayList();
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        init(context);
    }

    public void setContinerVisible() {
        this.container.setVisibility(0);
    }

    public void setShowFragmentListener(OnShowFragmentListener onShowFragmentListener) {
        this.showFragmentListener = onShowFragmentListener;
    }

    public void setOnLikeStatusListener(LikeStatusListener likeStatusListener) {
        this.likeStatusListener = likeStatusListener;
    }

    public void setShowSubtitlePage() {
        setContinerVisible();
        OnShowFragmentListener onShowFragmentListener = this.showFragmentListener;
        if (onShowFragmentListener != null) {
            onShowFragmentListener.showFragment(R.id.searUploadSubtitleFrameLayout);
        }
    }

    public void updateLikeStatus(int i, int i2) {
        this.mCCAdapter.updateLikeStatus(i, i2);
    }

    private void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.player_cc_popup_view, this);
        this.mCCCountrySelect = (ListView) findViewById(R.id.lv_cc_country);
        this.mCCListSelect = (ListView) findViewById(R.id.lv_cc_quality);
        this.container = (FrameLayout) findViewById(R.id.searUploadSubtitleFrameLayout);
        ((TextView) findViewById(R.id.tv_more)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.videocontroller.CcController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CcController.this.setContinerVisible();
                if (CcController.this.showFragmentListener != null) {
                    CcController.this.showFragmentListener.showFragment(R.id.searUploadSubtitleFrameLayout);
                }
            }
        });
        this.mCCCountrySelect.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.CcController.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                CcController.this.getstr.clear();
                CcController.this.mCorrentLanguage = i;
                CcController.this.getstr.addAll((Collection) CcController.this.stringListHashMap.get(((LanguageModel) CcController.this.languages.get(i)).language));
                for (int i2 = 0; i2 < CcController.this.getstr.size(); i2++) {
                    CcController.this.getstr.get(i2).setSelect(false);
                    if (CcController.this.mCurSelectSRT == CcController.this.mCorrentLanguage && i2 == CcController.this.mSrtSlelct) {
                        CcController.this.getstr.get(CcController.this.mSrtSlelct).setSelect(true);
                    }
                }
                CcController.this.container.setVisibility(8);
                CcController.this.mCCLanguageAdapter.setMediaLanguage(CcController.this.mCorrentLanguage);
                CcController.this.mCCAdapter.updateItems(CcController.this.getstr);
            }
        });
        this.mCCListSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.CcController.3
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (CcController.this.getstr.get(i).isSelect()) {
                    CcController.this.mSrtSlelct = -1;
                    CcController.this.mCurSelectSRT = -1;
                    CcController.this.mCCAdapter._cleanSelected();
                    CcController.this.mCCAdapter.notifyDataSetChanged();
                    if (CcController.this.mCallback != null) {
                        CcController.this.mCallback.onDisSelectedCc(CcController.this.getstr.get(i).sid);
                        return;
                    }
                    return;
                }
                CcController ccController = CcController.this;
                ccController.mCurSelectSRT = ccController.mCorrentLanguage;
                CcController.this.mSrtSlelct = i;
                CcController.this.mCCAdapter.setMediaQuality(CcController.this.mSrtSlelct);
                if (CcController.this.mCallback != null) {
                    CcController.this.mCallback.onSelectedCc(i, CcController.this.mCCAdapter.getItem(i));
                }
            }
        });
        this.mCCLanguageAdapter = new AdapterSRTLanguage(context);
        AdapterMediaQuality adapterMediaQuality = new AdapterMediaQuality(context);
        this.mCCAdapter = adapterMediaQuality;
        adapterMediaQuality.setListener(new AdapterMediaQuality.OnViewSubtitleClickListener() { // from class: com.movieboxpro.android.view.videocontroller.CcController.4
            @Override // com.dueeeke.adapter.AdapterMediaQuality.OnViewSubtitleClickListener
            public void onViewSubtitleClick(int i) {
                if (CcController.this.mCallback != null) {
                    CcController.this.mCallback.onViewSubtitleClick(i, CcController.this.mCCAdapter.getItem(i));
                }
            }

            @Override // com.dueeeke.adapter.AdapterMediaQuality.OnViewSubtitleClickListener
            public void likeSubtitle(int i, String str, int i2) {
                if (CcController.this.likeStatusListener != null) {
                    CcController.this.likeStatusListener.onLikeStatus(i, str, i2);
                }
            }
        });
        this.mCCCountrySelect.setAdapter((ListAdapter) this.mCCLanguageAdapter);
        this.mCCListSelect.setAdapter((ListAdapter) this.mCCAdapter);
    }

    public void setCallback(CcCallBack ccCallBack) {
        this.mCallback = ccCallBack;
    }

    public void setVideoQualityList(LinkedHashMap<String, List<SRTModel.SubTitles>> linkedHashMap) {
        if (linkedHashMap.isEmpty()) {
            setContinerVisible();
            OnShowFragmentListener onShowFragmentListener = this.showFragmentListener;
            if (onShowFragmentListener != null) {
                onShowFragmentListener.showFragment(R.id.searUploadSubtitleFrameLayout);
                return;
            }
            return;
        }
        this.languages.clear();
        this.stringListHashMap = linkedHashMap;
        for (Map.Entry<String, List<SRTModel.SubTitles>> entry : linkedHashMap.entrySet()) {
            LanguageModel languageModel = new LanguageModel();
            languageModel.setLanguage(entry.getKey());
            languageModel.setSelected(false);
            this.languages.add(languageModel);
        }
        List<LanguageModel> list = this.languages;
        if (list != null) {
            this.mCCLanguageAdapter.updateItems(list);
            for (int i = 0; i < this.languages.size(); i++) {
                List<SRTModel.SubTitles> list2 = linkedHashMap.get(this.languages.get(i).language);
                for (int i2 = 0; i2 < list2.size(); i2++) {
                    if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(list2.get(i2).getMyselect())) {
                        this.mCorrentLanguage = i;
                        this.mCurSelectSRT = i;
                        this.mSrtSlelct = i2;
                        this.mCCCountrySelect.performItemClick(null, i, 2131298292L);
                        this.mCCAdapter.setSelectPos(i2);
                        return;
                    }
                }
            }
            if (this.languages.size() > 0) {
                this.mCCCountrySelect.performItemClick(null, 0, 2131298292L);
            }
        }
    }

    public void setSelectPosition(int i) {
        this.mCurSelectSRT = this.mCorrentLanguage;
        this.mSrtSlelct = i;
        this.mCCAdapter.setMediaQuality(i);
    }

    public void _clear() {
        this.mSrtSlelct = -1;
        this.mCurSelectSRT = -1;
        this.mCCAdapter._cleanSelected();
        this.mCCAdapter.notifyDataSetChanged();
        CcCallBack ccCallBack = this.mCallback;
        if (ccCallBack != null) {
            ccCallBack.onDisSelectedCc("");
        }
    }

    public void disSelect() {
        this.mSrtSlelct = -1;
        this.mCurSelectSRT = -1;
        this.mCCAdapter._cleanSelected();
        this.mCCAdapter.notifyDataSetChanged();
    }
}
