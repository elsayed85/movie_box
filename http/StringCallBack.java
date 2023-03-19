package com.movieboxpro.android.http;

import com.movieboxpro.android.view.listener.IViewController;
import retrofit2.Call;
/* loaded from: classes3.dex */
public abstract class StringCallBack<T> extends BaseCallback<String> {
    private Class<T> claz;

    public abstract boolean onParsed(BaseResponse<T> baseResponse);

    public StringCallBack(Class<T> cls) {
        this.claz = cls;
    }

    public StringCallBack(Call<String> call, Class<T> cls) {
        this(call, null, cls);
    }

    public StringCallBack(IViewController iViewController, Class<T> cls) {
        super(null, iViewController);
        this.claz = cls;
    }

    public StringCallBack(Call<String> call, IViewController iViewController, Class<T> cls) {
        super(call, iViewController);
        this.claz = cls;
    }

    @Override // com.movieboxpro.android.http.BaseCallback
    public boolean onResponse(String str) {
        BaseResponse<T> parse = HttpUtils.parse(str, this.claz);
        return parse != null && onParsed(parse);
    }
}
