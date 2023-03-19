package com.movieboxpro.android.viewmodel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.model.NoticeCountModel;
import com.movieboxpro.android.model.UserAgreementModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
/* compiled from: UserInfoViewModel.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0005H\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005J\u0006\u0010\u001d\u001a\u00020\u001bJ\b\u0010\u001e\u001a\u00020\u001bH\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0002J\b\u0010 \u001a\u00020\u001bH\u0002J\u000e\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0007\"\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0007¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/viewmodel/UserInfoViewModel;", "Lcom/movieboxpro/android/viewmodel/BaseObservableViewModel;", "()V", "nextBillData", "Landroidx/databinding/ObservableField;", "", "getNextBillData", "()Landroidx/databinding/ObservableField;", "noticeCountModel", "Landroidx/lifecycle/MutableLiveData;", "Lcom/movieboxpro/android/model/NoticeCountModel;", "getNoticeCountModel", "()Landroidx/lifecycle/MutableLiveData;", "uidString", "getUidString", "userAgreementModel", "Lcom/movieboxpro/android/model/UserAgreementModel;", "getUserAgreementModel", "userAvatar", "getUserAvatar", "setUserAvatar", "(Landroidx/databinding/ObservableField;)V", "userInfo", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "getUserInfo", "buildData", "doTvLogin", "", "result", "getData", "initUidString", "refreshNoticeCount", "refreshUserAgreement", "updateAvatar", IDataSource.SCHEME_FILE_TAG, "Ljava/io/File;", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserInfoViewModel extends BaseObservableViewModel {
    private final MutableLiveData<UserAgreementModel> userAgreementModel = new MutableLiveData<>();
    private final MutableLiveData<NoticeCountModel> noticeCountModel = new MutableLiveData<>();
    private final ObservableField<String> nextBillData = new ObservableField<>();
    private final ObservableField<String> uidString = new ObservableField<>();
    private final ObservableField<UserModel.UserData> userInfo = new ObservableField<>();
    private ObservableField<String> userAvatar = new ObservableField<>();

    public final MutableLiveData<UserAgreementModel> getUserAgreementModel() {
        return this.userAgreementModel;
    }

    public final MutableLiveData<NoticeCountModel> getNoticeCountModel() {
        return this.noticeCountModel;
    }

    public final ObservableField<String> getNextBillData() {
        return this.nextBillData;
    }

    public final ObservableField<String> getUidString() {
        return this.uidString;
    }

    public final ObservableField<UserModel.UserData> getUserInfo() {
        return this.userInfo;
    }

    public final ObservableField<String> getUserAvatar() {
        return this.userAvatar;
    }

    public final void setUserAvatar(ObservableField<String> observableField) {
        Intrinsics.checkNotNullParameter(observableField, "<set-?>");
        this.userAvatar = observableField;
    }

    public final void getData() {
        if (App.isLogin()) {
            refreshUserAgreement();
            refreshNoticeCount();
            this.userInfo.set(App.getUserData());
            ObservableField<String> observableField = this.userAvatar;
            UserModel.UserData userData = this.userInfo.get();
            observableField.set(userData == null ? null : userData.avatar);
            initUidString();
        }
    }

    private final void initUidString() {
        StringBuilder sb = new StringBuilder();
        UserModel.UserData userData = this.userInfo.get();
        if (userData != null && userData.getUid_v2() != null) {
            int i = 0;
            int length = userData.getUid_v2().length();
            while (i < length) {
                int i2 = i + 1;
                if (i != userData.getUid_v2().length() - 1) {
                    sb.append(String.valueOf(userData.getUid_v2().charAt(i)));
                    sb.append("     ");
                } else {
                    sb.append(userData.getUid_v2().charAt(i));
                }
                i = i2;
            }
        }
        this.uidString.set(sb.toString());
    }

    public final void updateAvatar(File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        if (file.exists()) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserInfoViewModel$updateAvatar$1(this, buildData(), MultipartBody.Part.createFormData("imgupload", file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file)), null), 3, null);
        }
    }

    public final void doTvLogin(String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserInfoViewModel$doTvLogin$1(this, result, null), 3, null);
    }

    private final String buildData() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put((JSONObject) "module", API.USER.PROFILE);
        jSONObject2.put((JSONObject) IjkMediaMeta.IJKM_KEY_TYPE, "upload");
        jSONObject2.put((JSONObject) "uid", App.getUserData().uid_v2);
        jSONObject2.put((JSONObject) "open_udid", SystemUtils.getUniqueId(App.getContext()));
        jSONObject2.put((JSONObject) "app_version", App.versionName);
        jSONObject2.put((JSONObject) "expired_date", (String) Long.valueOf((TimeUtils.getCurrentTime() / 1000) + 43200));
        return HttpUtils.encodeBody(jSONObject.toJSONString());
    }

    private final void refreshNoticeCount() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserInfoViewModel$refreshNoticeCount$1(this, null), 3, null);
    }

    private final void refreshUserAgreement() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new UserInfoViewModel$refreshUserAgreement$1(this, null), 3, null);
    }
}
