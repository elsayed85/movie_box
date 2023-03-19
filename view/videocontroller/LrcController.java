package com.movieboxpro.android.view.videocontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.dueeeke.adapter.AdapterSrtlist;
import com.dueeeke.model.SrtPraseModel;
import com.movieboxpro.android.R;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class LrcController extends FrameLayout {
    public boolean isFrist;
    public boolean isTouch;
    public ListView lrcView;
    private AdapterSrtlist mAdapter;
    private LrcCallBack mCallback;
    public Context mContext;
    private List<SrtPraseModel> mList;
    Runnable run;

    /* loaded from: classes3.dex */
    public interface LrcCallBack {
        void mLrcSelected(SrtPraseModel srtPraseModel);
    }

    public LrcController(Context context) {
        super(context);
        this.mList = new ArrayList();
        this.isFrist = false;
        this.isTouch = false;
        this.run = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.LrcController.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    if (!LrcController.this.isTouch) {
                        Log.d("ssss", "Time  run");
                        LrcController.this.isFrist = false;
                    }
                }
            }
        };
        init(context);
    }

    public LrcController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mList = new ArrayList();
        this.isFrist = false;
        this.isTouch = false;
        this.run = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.LrcController.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    if (!LrcController.this.isTouch) {
                        Log.d("ssss", "Time  run");
                        LrcController.this.isFrist = false;
                    }
                }
            }
        };
        init(context);
    }

    public LrcController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mList = new ArrayList();
        this.isFrist = false;
        this.isTouch = false;
        this.run = new Runnable() { // from class: com.movieboxpro.android.view.videocontroller.LrcController.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    if (!LrcController.this.isTouch) {
                        Log.d("ssss", "Time  run");
                        LrcController.this.isFrist = false;
                    }
                }
            }
        };
        init(context);
    }

    public void init(Context context) {
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.player_srt_pop_view, this);
        this.lrcView = (ListView) findViewById(R.id.lv_srt);
        AdapterSrtlist adapterSrtlist = new AdapterSrtlist(context);
        this.mAdapter = adapterSrtlist;
        this.lrcView.setAdapter((ListAdapter) adapterSrtlist);
        this.lrcView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.view.videocontroller.LrcController.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                LrcController.this.mCallback.mLrcSelected((SrtPraseModel) LrcController.this.mList.get(i));
            }
        });
        this.lrcView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.movieboxpro.android.view.videocontroller.LrcController.2
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == 1 || i == 0) {
                    LrcController lrcController = LrcController.this;
                    lrcController.isTouch = true ^ lrcController.isTouch;
                    Log.d("ssss", "Time  1 :" + LrcController.this.isTouch + " : " + i);
                    if (LrcController.this.isTouch) {
                        LrcController lrcController2 = LrcController.this;
                        lrcController2.removeCallbacks(lrcController2.run);
                        return;
                    }
                    LrcController lrcController3 = LrcController.this;
                    lrcController3.postDelayed(lrcController3.run, 5000L);
                }
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                Log.d("ssss", "Time  2" + LrcController.this.isTouch);
                LrcController.this.isFrist = true;
                LrcController lrcController = LrcController.this;
                lrcController.postDelayed(lrcController.run, 500L);
            }
        });
    }

    public void resetData(List<SrtPraseModel> list) {
        this.mList.clear();
        this.mList.addAll(list);
        this.mAdapter.updateItems(this.mList);
    }

    public void setDataList(List<SrtPraseModel> list, LrcCallBack lrcCallBack) {
        this.mList.clear();
        this.mList.addAll(list);
        this.isFrist = false;
        this.isTouch = false;
        this.mCallback = lrcCallBack;
        AdapterSrtlist adapterSrtlist = this.mAdapter;
        if (adapterSrtlist != null) {
            adapterSrtlist.updateItems(this.mList);
        }
    }

    public void setToPosition(int i) {
        if (this.mList == null || this.isFrist) {
            return;
        }
        this.lrcView.setSelection(i);
        this.mAdapter.setMediaSrt(i);
    }
}
