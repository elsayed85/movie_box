package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.ares.downloader.jarvis.Jarvis;
import com.ares.downloader.jarvis.core.DownloadListener;
import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.TestNetRecode;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.common.NetTestModel;
import com.movieboxpro.android.utils.LocationUtil;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.widget.AntiShakeUtils;
import com.movieboxpro.android.view.widget.MyLinearLayoutManager;
import com.movieboxpro.android.view.widget.adapter.NetTestAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes3.dex */
public class TestNetDialog extends DialogFragment {
    public static TestNetDialog payDialog;
    private ConstraintLayout lLayout_pay;
    private String latitude;
    private String longitude;
    private NetTestAdapter mAdapter;
    private RecyclerView mRecycler;
    private onNoOnclickListener noOnclickListener;
    private ExecutorService singleExecutor;
    private List<NetTestModel> list = new ArrayList();
    private LocationUtil.LocationCallBack callBack = new LocationUtil.LocationCallBack() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.1
        @Override // com.movieboxpro.android.utils.LocationUtil.LocationCallBack
        public void onFail(String str) {
        }

        @Override // com.movieboxpro.android.utils.LocationUtil.LocationCallBack
        public void onSuccess(Location location) {
            TestNetDialog.this.latitude = new DecimalFormat("#.00").format(location.getLatitude());
            TestNetDialog.this.longitude = new DecimalFormat("#.00").format(location.getLongitude());
        }
    };
    int index = 0;
    private int setIntager = 0;
    private StringBuilder testResult = new StringBuilder();
    private int testResultCount = 0;

    /* loaded from: classes3.dex */
    public interface onNoOnclickListener {
        void onNoClick();
    }

    private void initEvent() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$requestLocationPermission$1(Throwable th) throws Exception {
    }

    static /* synthetic */ int access$408(TestNetDialog testNetDialog) {
        int i = testNetDialog.setIntager;
        testNetDialog.setIntager = i + 1;
        return i;
    }

    static /* synthetic */ int access$608(TestNetDialog testNetDialog) {
        int i = testNetDialog.testResultCount;
        testNetDialog.testResultCount = i + 1;
        return i;
    }

    public TestNetDialog setNoOnclickListener(onNoOnclickListener onnoonclicklistener) {
        this.noOnclickListener = onnoonclicklistener;
        return this;
    }

    public TestNetDialog setNet(List<NetTestModel> list) {
        TestNetRecode findAll = App.getDB().testnetRecodeDao().findAll(1);
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
            if (findAll != null) {
                for (NetTestModel netTestModel : this.list) {
                    if (findAll.id == netTestModel.id) {
                        netTestModel.isSelect = true;
                    }
                }
            } else if (this.list.size() > 0) {
                this.list.get(0).isSelect = true;
            }
        }
        return this;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            double d = getContext().getResources().getDisplayMetrics().widthPixels;
            Double.isNaN(d);
            attributes.width = (int) (d * 0.8d);
            attributes.height = -2;
            window.setAttributes(attributes);
            getDialog().setCanceledOnTouchOutside(true);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogStyle);
        requestLocationPermission();
    }

    private void requestLocationPermission() {
        new RxPermissions(this).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer() { // from class: com.movieboxpro.android.view.dialog.-$$Lambda$TestNetDialog$zPxzO828Wu9wZ5hZOe2e_aP8taI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TestNetDialog.this.lambda$requestLocationPermission$0$TestNetDialog((Boolean) obj);
            }
        }, $$Lambda$TestNetDialog$BpelbPpuWDT2HkRW8InMvdBKRc.INSTANCE);
    }

    public /* synthetic */ void lambda$requestLocationPermission$0$TestNetDialog(Boolean bool) throws Exception {
        if (getContext() != null) {
            LocationUtil.getCurrentLocation(getContext(), this.callBack);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.layout_dialog_nettest, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    private void initView(View view) {
        this.mRecycler = (RecyclerView) view.findViewById(R.id.nettest_myview);
        this.lLayout_pay = (ConstraintLayout) view.findViewById(R.id.lLayout_pay);
        if (this.mAdapter == null) {
            this.mAdapter = new NetTestAdapter(this.list);
            this.mRecycler.setLayoutManager(new MyLinearLayoutManager(getContext()));
            this.mRecycler.setAdapter(this.mAdapter);
            this.mAdapter.setListener(new AnonymousClass2());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.dialog.TestNetDialog$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass2 implements NetTestAdapter.OnNetClickListener {
        AnonymousClass2() {
        }

        @Override // com.movieboxpro.android.base.OnItemClickListener
        public void onItemClick(int i) {
            NetTestModel netTestModel = (NetTestModel) TestNetDialog.this.list.get(i);
            if (netTestModel.id != 1) {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, netTestModel.domain);
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, netTestModel.group_id);
            } else {
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_STATE, "Error");
                PrefsUtils.getInstance().putString(Constant.Prefs.NETWORK_GROUP, "");
            }
            TestNetDialog.this.SaveInDao(netTestModel);
            if (TestNetDialog.this.noOnclickListener != null) {
                TestNetDialog.this.noOnclickListener.onNoClick();
            }
            TestNetDialog.this.dismiss();
        }

        @Override // com.movieboxpro.android.view.widget.adapter.NetTestAdapter.OnNetClickListener
        public void getReload(final int i, ImageView imageView) {
            if (!AntiShakeUtils.isInvalidClick(imageView) && TestNetDialog.this.setIntager >= TestNetDialog.this.list.size()) {
                Jarvis.Downloader downloadListener = Jarvis.with(App.getContext()).withUrl(((NetTestModel) TestNetDialog.this.list.get(i)).url).fileName(((NetTestModel) TestNetDialog.this.list.get(i)).url).filePath(Constant.DIR_DOWNLOAD).allowBackgroundDownload(true).threadCount(1).refreshTime(1000L).setDownloadListener(new DownloadListener() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.2.1
                    @Override // com.ares.downloader.jarvis.core.DownloadListener
                    public void onDelete(boolean z) {
                    }

                    @Override // com.ares.downloader.jarvis.core.DownloadListener
                    public void onPause() {
                    }

                    @Override // com.ares.downloader.jarvis.core.DownloadListener
                    public void onProgress(long j, float f, long j2) {
                    }

                    @Override // com.ares.downloader.jarvis.core.DownloadListener
                    public void onSuccess(File file) {
                        ((NetTestModel) TestNetDialog.this.list.get(i)).endTime = Long.valueOf(TimeUtils.getCurrentTime());
                        MLog.d("SSS", "SSWEDW : success : " + ((NetTestModel) TestNetDialog.this.list.get(i)).startTime + " : " + ((NetTestModel) TestNetDialog.this.list.get(i)).endTime + " : " + file.length());
                        int length = (int) (((float) (file.length() / (((NetTestModel) TestNetDialog.this.list.get(i)).endTime.longValue() - ((NetTestModel) TestNetDialog.this.list.get(i)).startTime.longValue()))) * ((NetTestModel) TestNetDialog.this.list.get(i)).ratio);
                        StringBuilder sb = new StringBuilder();
                        sb.append("SSWEDW : success22 : ");
                        sb.append(length);
                        MLog.d("SSS", sb.toString());
                        ((NetTestModel) TestNetDialog.this.list.get(i)).state = length + "KB/S";
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.2.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                TestNetDialog.this.mAdapter.notifyItemChanged(i);
                            }
                        });
                    }

                    @Override // com.ares.downloader.jarvis.core.DownloadListener
                    public void onStart() {
                        ((NetTestModel) TestNetDialog.this.list.get(i)).startTime = Long.valueOf(TimeUtils.getCurrentTime());
                        ((NetTestModel) TestNetDialog.this.list.get(i)).state = null;
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.2.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                TestNetDialog.this.mAdapter.notifyItemChanged(i);
                            }
                        });
                    }

                    @Override // com.ares.downloader.jarvis.core.DownloadListener
                    public void onFail(String str, int i2) {
                        ((NetTestModel) TestNetDialog.this.list.get(i)).endTime = 0L;
                        ((NetTestModel) TestNetDialog.this.list.get(i)).state = "false";
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.2.1.3
                            @Override // java.lang.Runnable
                            public void run() {
                                TestNetDialog.this.mAdapter.notifyItemChanged(i);
                            }
                        });
                    }
                });
                downloadListener.delete();
                downloadListener.download();
            }
        }
    }

    public void SaveInDao(NetTestModel netTestModel) {
        TestNetRecode findAll = App.getDB().testnetRecodeDao().findAll(1);
        if (findAll == null) {
            TestNetRecode testNetRecode = new TestNetRecode();
            testNetRecode.setIds(1);
            testNetRecode.setId(netTestModel.id);
            testNetRecode.setCountry(netTestModel.country);
            testNetRecode.setDescription(netTestModel.description);
            testNetRecode.setDomain(netTestModel.domain);
            testNetRecode.setDisplay_order(netTestModel.display_order);
            testNetRecode.setRatio(netTestModel.ratio);
            testNetRecode.setUrl(netTestModel.url);
            testNetRecode.setStartTime(netTestModel.startTime);
            testNetRecode.setEndTime(netTestModel.endTime);
            testNetRecode.setState(netTestModel.state);
            App.getDB().testnetRecodeDao().insert(testNetRecode);
            return;
        }
        findAll.setId(netTestModel.id);
        findAll.setCountry(netTestModel.country);
        findAll.setDescription(netTestModel.description);
        findAll.setDomain(netTestModel.domain);
        findAll.setDisplay_order(netTestModel.display_order);
        findAll.setRatio(netTestModel.ratio);
        findAll.setUrl(netTestModel.url);
        findAll.setStartTime(netTestModel.startTime);
        findAll.setEndTime(netTestModel.endTime);
        findAll.setState(netTestModel.state);
        App.getDB().testnetRecodeDao().update(findAll);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * getScreenDendity(context)) + 0.5f);
    }

    public static float getScreenDendity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    private long getContentLength(String str) {
        try {
            Response execute = Http.getOkHttpClient().newCall(new Request.Builder().url(str).build()).execute();
            if (execute.isSuccessful()) {
                long contentLength = execute.body().contentLength();
                execute.close();
                if (contentLength == 0) {
                    return -1L;
                }
                return contentLength;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    private void initData() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.list.size(); i++) {
            arrayList.add(this.list.get(i).url);
        }
        Jarvis.getInstance().forceDeleteSM(arrayList);
        this.singleExecutor = Executors.newSingleThreadExecutor();
        for (int i2 = 0; i2 < this.list.size(); i2++) {
            this.singleExecutor.submit(new AnonymousClass3(i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.movieboxpro.android.view.dialog.TestNetDialog$3  reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ int val$mSelect;

        AnonymousClass3(int i) {
            this.val$mSelect = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            Jarvis.with(App.getContext()).withUrl(((NetTestModel) TestNetDialog.this.list.get(this.val$mSelect)).url).fileName(((NetTestModel) TestNetDialog.this.list.get(this.val$mSelect)).url).filePath(Constant.DIR_DOWNLOAD).allowBackgroundDownload(true).threadCount(10).refreshTime(1000L).setDownloadListener(new DownloadListener() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.3.1
                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onDelete(boolean z) {
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onPause() {
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onProgress(long j, float f, long j2) {
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onSuccess(File file) {
                    ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).endTime = Long.valueOf(TimeUtils.getCurrentTime());
                    MLog.d("SSS", "SSWEDW : success : " + ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).startTime + " : " + ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).endTime + " : " + file.length());
                    final int length = (int) (((float) (file.length() / (((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).endTime.longValue() - ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).startTime.longValue()))) * ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).ratio);
                    StringBuilder sb = new StringBuilder();
                    sb.append("SSWEDW : success22 : ");
                    sb.append(length);
                    MLog.d("SSS", sb.toString());
                    ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).state = length + " KB/S";
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.3.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TestNetDialog.this.mAdapter.notifyItemChanged(AnonymousClass3.this.val$mSelect);
                        }
                    });
                    TestNetDialog.access$408(TestNetDialog.this);
                    final NetTestModel netTestModel = (NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect);
                    if (TestNetDialog.this.testResultCount == 0) {
                        TestNetDialog.this.testResult.append(TestNetDialog.this.addHeadTestSpeedInfo("Server", "IP", "Time Used", "Speed"));
                    }
                    Observable.just(netTestModel.domain).map(new Function<String, String>() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.3.1.3
                        @Override // io.reactivex.functions.Function
                        public String apply(String str) {
                            return TestNetDialog.getDomainAddress(str.substring(str.lastIndexOf("/") + 1));
                        }
                    }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.3.1.2
                        @Override // io.reactivex.Observer
                        public void onComplete() {
                        }

                        @Override // io.reactivex.Observer
                        public void onError(Throwable th) {
                        }

                        @Override // io.reactivex.Observer
                        public void onSubscribe(Disposable disposable) {
                        }

                        @Override // io.reactivex.Observer
                        public void onNext(String str) {
                            double d;
                            Double.isNaN(netTestModel.endTime.longValue() - netTestModel.startTime.longValue());
                            TestNetDialog.this.testResult.append(TestNetDialog.this.addTestSpeedInfo(netTestModel.domain, str, ((float) (d / 1000.0d)) + "s", length + " KB/s"));
                            TestNetDialog.access$608(TestNetDialog.this);
                            if (TestNetDialog.this.testResultCount == TestNetDialog.this.list.size()) {
                                TestNetDialog.this.feedbackNetTestResult(TestNetDialog.this.testResult.toString());
                            }
                        }
                    });
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onStart() {
                    ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).startTime = Long.valueOf(TimeUtils.getCurrentTime());
                    ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).state = null;
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.3.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            TestNetDialog.this.mAdapter.notifyItemChanged(AnonymousClass3.this.val$mSelect);
                        }
                    });
                }

                @Override // com.ares.downloader.jarvis.core.DownloadListener
                public void onFail(String str, int i) {
                    ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).endTime = 0L;
                    ((NetTestModel) TestNetDialog.this.list.get(AnonymousClass3.this.val$mSelect)).state = "Failed";
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.3.1.5
                        @Override // java.lang.Runnable
                        public void run() {
                            TestNetDialog.this.mAdapter.notifyItemChanged(AnonymousClass3.this.val$mSelect);
                        }
                    });
                    TestNetDialog.access$408(TestNetDialog.this);
                }
            }).download();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String addHeadTestSpeedInfo(String str, String str2, String str3, String str4) {
        return str + "                                                  " + str2 + "                                                  " + str3 + "                                                  " + str4 + ShellUtil.COMMAND_LINE_END;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String addTestSpeedInfo(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getSpace(str, "Server"));
        if (TextUtils.isEmpty(str2)) {
            sb.append("               ");
            sb.append(getSpace("145.239.252.191", "IP"));
        } else {
            sb.append(str2);
            sb.append(getSpace(str2, "IP"));
        }
        sb.append(str3);
        sb.append(getSpace(str3, "Time Used"));
        sb.append(str4);
        sb.append(ShellUtil.COMMAND_LINE_END);
        return sb.toString();
    }

    private String getSpace(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (str2.length() + 50) - str.length(); i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public Call download(String str, Callback callback) {
        Call newCall = Http.getOkHttpClient().newCall(new Request.Builder().url(str).build());
        newCall.enqueue(callback);
        return newCall;
    }

    public static String getDomainAddress(String str) {
        try {
            String[] split = str.split(":");
            return InetAddress.getByName(split.length > 0 ? split[0] : "").getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void feedbackNetTestResult(String str) {
        Http.getService().Movie_feedback(API.BASE_URL, API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", -1, "", -1, "", 14, "(api)Test_network:\n" + SystemUtils.getMsg() + "\nlatitude:" + this.latitude + "longitude:" + this.longitude + "\n\n\n" + str, 0, 0).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.view.dialog.TestNetDialog.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String str2) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        ExecutorService executorService = this.singleExecutor;
        if (executorService != null) {
            executorService.shutdown();
        }
        super.dismiss();
    }
}
