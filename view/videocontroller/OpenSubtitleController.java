package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.dueeeke.adapter.AdapterSRTExtra;
import com.dueeeke.adapter.AdapterSRTLanguage;
import com.dueeeke.model.ExtrModel;
import com.dueeeke.model.LanguageModel;
import com.movieboxpro.android.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public class OpenSubtitleController extends FrameLayout {
    List<LanguageModel> language;
    private AdapterSRTExtra mAdapter;
    private AdapterSRTLanguage mCCLanguageAdapter;
    private mOpenSubtitle mCallback;
    private Context mContext;
    private int mCorrentLanguage;
    private int mCurSelectSRT;
    private ListView mLanguageView;
    private ArrayList<ExtrModel> mList;
    private ListView mListView;
    private int mSrtSlelct;
    LinkedHashMap<String, List<ExtrModel>> modelLinkedHashMap;

    /* loaded from: classes3.dex */
    public interface mOpenSubtitle {
        void disDownlaod(ExtrModel extrModel);

        void download(ExtrModel extrModel);
    }

    public OpenSubtitleController(Context context) {
        super(context);
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        init(context);
    }

    public OpenSubtitleController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        init(context);
    }

    public OpenSubtitleController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
        LayoutInflater.from(this.mContext).inflate(R.layout.player_opensubtitle_popup_view, this);
        this.mLanguageView = (ListView) findViewById(R.id.lv_openlanguage);
        AdapterSRTLanguage adapterSRTLanguage = new AdapterSRTLanguage(context);
        this.mCCLanguageAdapter = adapterSRTLanguage;
        this.mLanguageView.setAdapter((ListAdapter) adapterSRTLanguage);
        this.mLanguageView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$OpenSubtitleController$StNpalHcm16xmLS6bwcciEIV7x0
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                OpenSubtitleController.this.lambda$init$0$OpenSubtitleController(adapterView, view, i, j);
            }
        });
        ListView listView = (ListView) findViewById(R.id.lv_opensubtitle);
        this.mListView = listView;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.-$$Lambda$OpenSubtitleController$vB9U7Qyp87Dsj6I9iCp7q7DulGc
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                OpenSubtitleController.this.lambda$init$1$OpenSubtitleController(adapterView, view, i, j);
            }
        });
        AdapterSRTExtra adapterSRTExtra = new AdapterSRTExtra(context);
        this.mAdapter = adapterSRTExtra;
        this.mListView.setAdapter((ListAdapter) adapterSRTExtra);
    }

    public /* synthetic */ void lambda$init$0$OpenSubtitleController(AdapterView adapterView, View view, int i, long j) {
        int i2;
        this.mList.clear();
        this.mCorrentLanguage = i;
        this.mList.addAll((Collection) Objects.requireNonNull(this.modelLinkedHashMap.get(this.language.get(i).language)));
        for (int i3 = 0; i3 < this.mList.size(); i3++) {
            this.mList.get(i3).setSelect(false);
            if (this.mCurSelectSRT == this.mCorrentLanguage && i == (i2 = this.mSrtSlelct)) {
                this.mList.get(i2).setSelect(true);
            }
        }
        this.mCCLanguageAdapter.setMediaLanguage(this.mCorrentLanguage);
        this.mAdapter.updateItems(this.mList);
    }

    public /* synthetic */ void lambda$init$1$OpenSubtitleController(AdapterView adapterView, View view, int i, long j) {
        mOpenSubtitle mopensubtitle = this.mCallback;
        if (mopensubtitle != null) {
            mopensubtitle.download(this.mList.get(i));
        }
    }

    public void setVideoSubtitle(List<ExtrModel> list) {
        this.language = new ArrayList();
        ArrayList arrayList = new ArrayList();
        ArrayList<ExtrModel> arrayList2 = new ArrayList();
        this.modelLinkedHashMap = new LinkedHashMap<>();
        for (ExtrModel extrModel : list) {
            if (!arrayList2.contains(extrModel)) {
                if (extrModel.getLanguageName().equals("English")) {
                    arrayList2.add(0, extrModel);
                } else {
                    arrayList2.add(extrModel);
                }
            }
        }
        Collections.sort(arrayList2, $$Lambda$OpenSubtitleController$OxQ4FBSqsiUZyz1UzcknYotQfI.INSTANCE);
        ExtrModel extrModel2 = new ExtrModel();
        for (ExtrModel extrModel3 : arrayList2) {
            if (extrModel3.getLanguageName().equals("English")) {
                extrModel2 = extrModel3;
            }
        }
        if (extrModel2.getLanguageName() != null && extrModel2.getLanguageName().equals("English")) {
            arrayList2.remove(extrModel2);
            arrayList2.add(0, extrModel2);
        }
        for (ExtrModel extrModel4 : arrayList2) {
            if (arrayList.contains(extrModel4.getLanguageName())) {
                this.modelLinkedHashMap.get(extrModel4.getLanguageName()).add(extrModel4);
            } else {
                LanguageModel languageModel = new LanguageModel();
                languageModel.setSelected(false);
                languageModel.setLanguage(extrModel4.getLanguageName());
                arrayList.add(extrModel4.getLanguageName());
                this.language.add(languageModel);
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(extrModel4);
                this.modelLinkedHashMap.put(extrModel4.getLanguageName(), arrayList3);
            }
        }
        List<LanguageModel> list2 = this.language;
        if (list2 != null) {
            this.mCCLanguageAdapter.updateItems(list2);
            if (this.language.size() > 0) {
                this.mLanguageView.performItemClick(null, 0, 2131298292L);
            }
        }
    }

    public void setCallBack(mOpenSubtitle mopensubtitle) {
        this.mCallback = mopensubtitle;
    }
}
