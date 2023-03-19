package com.movieboxpro.android.utils;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BaseResponse;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.List;
/* loaded from: classes.dex */
public class RxUtils {
    public static ObservableTransformer<String, String> rxTranslateMsg() {
        return new ObservableTransformer<String, String>() { // from class: com.movieboxpro.android.utils.RxUtils.1
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<String> apply(Observable<String> observable) {
                return observable.map(new Function<String, String>() { // from class: com.movieboxpro.android.utils.RxUtils.1.1
                    @Override // io.reactivex.functions.Function
                    public String apply(String str) {
                        LogUtils.INSTANCE.logInterfaceJson(str);
                        BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str, BaseResponse.class);
                        if (baseResponse.getCode() == 1) {
                            if (baseResponse.getMsg() == null) {
                                baseResponse.setMsg("");
                            }
                            return baseResponse.getMsg();
                        }
                        throw new ServerException(baseResponse.getCode(), baseResponse.getMsg());
                    }
                });
            }
        };
    }

    public static <T> ObservableTransformer<String, T> rxTranslate2Bean(final Class<T> cls) {
        return new ObservableTransformer<String, T>() { // from class: com.movieboxpro.android.utils.RxUtils.2
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<String> observable) {
                return observable.map(new Function<String, T>() { // from class: com.movieboxpro.android.utils.RxUtils.2.1
                    /* JADX WARN: Type inference failed for: r5v4, types: [T, java.lang.Object] */
                    @Override // io.reactivex.functions.Function
                    public T apply(String str) {
                        LogUtils.INSTANCE.logInterfaceJson(str);
                        BaseResponse baseResponse = (BaseResponse) JSON.parseObject(str, RxUtils.buildType(BaseResponse.class, cls), new Feature[0]);
                        if (baseResponse.getCode() == 1) {
                            return baseResponse.getData();
                        }
                        throw new ServerException(baseResponse.getCode(), baseResponse.getMsg());
                    }
                });
            }
        };
    }

    public static <T> ObservableTransformer<String, List<T>> rxTranslate2List(final Class<T> cls) {
        return new ObservableTransformer<String, List<T>>() { // from class: com.movieboxpro.android.utils.RxUtils.3
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<List<T>> apply(Observable<String> observable) {
                return observable.map(new Function<String, List<T>>() { // from class: com.movieboxpro.android.utils.RxUtils.3.1
                    @Override // io.reactivex.functions.Function
                    public List<T> apply(String str) {
                        LogUtils.INSTANCE.logInterfaceJson(str);
                        BaseResponse baseResponse = (BaseResponse) JSONObject.parseObject(str, RxUtils.buildType(BaseResponse.class, List.class, cls), new Feature[0]);
                        if (baseResponse.getCode() == 1) {
                            return (List) baseResponse.getData();
                        }
                        throw new ServerException(baseResponse.getCode(), baseResponse.getMsg());
                    }
                });
            }
        };
    }

    public static Type buildType(Type... typeArr) {
        if (typeArr == null || typeArr.length <= 0) {
            return null;
        }
        int length = typeArr.length - 1;
        ParameterizedTypeImpl parameterizedTypeImpl = null;
        while (length > 0) {
            Type[] typeArr2 = new Type[1];
            if (parameterizedTypeImpl == null) {
                parameterizedTypeImpl = typeArr[length];
            }
            typeArr2[0] = parameterizedTypeImpl;
            length--;
            parameterizedTypeImpl = new ParameterizedTypeImpl(typeArr2, null, typeArr[length - 1]);
        }
        return parameterizedTypeImpl;
    }

    public static <T> AutoDisposeConverter<T> bindLifecycleOwner(LifecycleOwner lifecycleOwner) {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, Lifecycle.Event.ON_DESTROY));
    }

    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() { // from class: com.movieboxpro.android.utils.RxUtils.4
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> rxSchedulerComputeHelper() {
        return new ObservableTransformer<T, T>() { // from class: com.movieboxpro.android.utils.RxUtils.5
            @Override // io.reactivex.ObservableTransformer
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
