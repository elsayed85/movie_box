package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Switch;
import com.dueeeke.adapter.QualityAdapter;
import com.dueeeke.model.MediaQualityInfo;
import com.movieboxpro.android.R;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class QualityController extends FrameLayout {
    public List<MediaQualityInfo> H264;
    public List<MediaQualityInfo> H265;
    private boolean h265;
    private QualityAdapter mAdapter;
    private QualityCallBack mCallback;
    private int mClickPos;
    private Context mContext;
    private List<MediaQualityInfo> mList;
    private List<MediaQualityInfo> mList2;
    private ListView mListView;
    private Switch mSwitch;

    /* loaded from: classes3.dex */
    public interface QualityCallBack {
        boolean onQualitySelect(MediaQualityInfo mediaQualityInfo, int i, boolean z);
    }

    public QualityController(Context context) {
        super(context);
        this.mClickPos = -1;
        this.h265 = true;
        this.H265 = new ArrayList();
        this.H264 = new ArrayList();
        init(context);
    }

    public QualityController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mClickPos = -1;
        this.h265 = true;
        this.H265 = new ArrayList();
        this.H264 = new ArrayList();
        init(context);
    }

    public QualityController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mClickPos = -1;
        this.h265 = true;
        this.H265 = new ArrayList();
        this.H264 = new ArrayList();
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        this.mList = new ArrayList();
        this.mList2 = new ArrayList();
        LayoutInflater.from(this.mContext).inflate(R.layout.player_quality_popup_view, this);
        ListView listView = (ListView) findViewById(R.id.lv_quality);
        this.mListView = listView;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.QualityController.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MediaQualityInfo item;
                if (QualityController.this.mClickPos == i) {
                    return;
                }
                if (QualityController.this.mCallback != null && QualityController.this.mList != null && QualityController.this.mList.size() > 0 && (item = QualityController.this.mAdapter.getItem(i)) != null && !QualityController.this.mCallback.onQualitySelect(item, i, false)) {
                    QualityController.this.mAdapter.setMediaQuality(i);
                }
                QualityController.this.mClickPos = i;
            }
        });
        QualityAdapter qualityAdapter = new QualityAdapter(context);
        this.mAdapter = qualityAdapter;
        this.mListView.setAdapter((ListAdapter) qualityAdapter);
        Switch r3 = (Switch) findViewById(R.id.setting_h265_more);
        this.mSwitch = r3;
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.movieboxpro.android.view.videocontroller.QualityController.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    QualityController qualityController = QualityController.this;
                    qualityController.mList = qualityController.H265;
                    QualityController.this.mAdapter.updateItems(QualityController.this.mList);
                } else {
                    QualityController qualityController2 = QualityController.this;
                    qualityController2.mList = qualityController2.H264;
                    QualityController.this.mAdapter.updateItems(QualityController.this.mList);
                }
                QualityController qualityController3 = QualityController.this;
                qualityController3.mClickPos = qualityController3.getSelect();
            }
        });
    }

    public void setCallback(QualityCallBack qualityCallBack) {
        this.mCallback = qualityCallBack;
    }

    public void setVideoQualityList(List<MediaQualityInfo> list, boolean z) {
        this.mList.clear();
        this.mList.addAll(list);
        QualityAdapter qualityAdapter = this.mAdapter;
        if (qualityAdapter != null) {
            qualityAdapter.updateItems(this.mList);
        }
    }

    public void reset(int i) {
        for (int i2 = 0; i2 < this.H265.size(); i2++) {
            this.H265.get(i2).setSelect(false);
        }
        for (int i3 = 0; i3 < this.H264.size(); i3++) {
            this.H264.get(i3).setSelect(false);
        }
        if (this.mSwitch.isChecked()) {
            this.H265.get(i).setSelect(true);
        } else {
            this.H264.get(i).setSelect(true);
        }
    }

    public int choose(MediaQualityInfo mediaQualityInfo) {
        for (int i = 0; i < this.mList2.size(); i++) {
            if (this.mList2.get(i).getPath().equals(mediaQualityInfo.getPath())) {
                return i;
            }
        }
        return 0;
    }

    public int getSelect() {
        for (int i = 0; i < this.mList.size(); i++) {
            if (this.mList.get(i).isSelect()) {
                return i;
            }
        }
        return -1;
    }
}
