package com.movieboxpro.android.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.RequestCallback;
import com.movieboxpro.android.utils.MLog;
/* loaded from: classes.dex */
public class OnlineConfigAgent {
    private static final String TAG = "OnlineConfigAgent";
    private static OnlineConfigAgent agent;
    private OnlineConfigListener listener;

    /* loaded from: classes.dex */
    public interface OnlineConfigListener {
        void onDataReceived(String str);
    }

    public static synchronized OnlineConfigAgent getInstance() {
        OnlineConfigAgent onlineConfigAgent;
        synchronized (OnlineConfigAgent.class) {
            if (agent == null) {
                agent = new OnlineConfigAgent();
            }
            onlineConfigAgent = agent;
        }
        return onlineConfigAgent;
    }

    private OnlineConfigAgent() {
    }

    public void updateOnlineConfig() {
        Http.get("https://www.mini-install.com/api/index/get_param_by_id.html?id=38", null, new RequestCallback<String>() { // from class: com.movieboxpro.android.config.OnlineConfigAgent.1
            @Override // com.movieboxpro.android.http.RequestCallback
            public void onSuccess(String str) {
                super.onSuccess((AnonymousClass1) str);
                MLog.d(OnlineConfigAgent.TAG, "onDownloadSuccess: " + str);
                JSONArray jSONArray = JSONObject.parseObject(str).getJSONArray("data");
                OnlineConfigAgent.this.saveConfigParams(App.getContext(), jSONArray.toJSONString());
                if (OnlineConfigAgent.this.listener != null) {
                    OnlineConfigAgent.this.listener.onDataReceived(jSONArray.toJSONString());
                }
            }

            @Override // com.movieboxpro.android.http.RequestCallback
            public void onFailure(int i, String str) {
                super.onFailure(i, str);
                if (OnlineConfigAgent.this.listener != null) {
                    OnlineConfigAgent.this.listener.onDataReceived(null);
                }
            }
        });
    }

    public void setOnlineConfigListener(OnlineConfigListener onlineConfigListener) {
        this.listener = onlineConfigListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveConfigParams(Context context, String str) {
        clearAllConfig(context);
        JSONArray parseArray = JSONArray.parseArray(str);
        if (parseArray.isEmpty()) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences("online_config", 0).edit();
        edit.clear();
        int size = parseArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jSONObject = parseArray.getJSONObject(i);
            String trim = jSONObject.getString("param_name").trim();
            String trim2 = jSONObject.getString("param_value").trim();
            edit.putString(trim, trim2);
            String trim3 = jSONObject.getString("remark").trim();
            edit.putString("remark_" + trim, trim3);
            MLog.d(TAG, "saveConfigParams: key: " + trim + " value: " + trim2);
            StringBuilder sb = new StringBuilder();
            sb.append("saveConfigParams: remark: ");
            sb.append(trim3);
            MLog.d(TAG, sb.toString());
        }
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getConfigParam(Context context, String str) {
        String string = context.getSharedPreferences("online_config", 0).getString(str, "");
        MLog.d(TAG, "getConfigParam: key: " + str + " value: " + string);
        return string;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setConfigParam(Context context, String str, String str2) {
        context.getSharedPreferences("online_config", 0).edit().putString(str, str2).commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean containsKey(Context context, String str) {
        return context.getSharedPreferences("online_config", 0).contains(str);
    }

    private void clearAllConfig(Context context) {
        context.getSharedPreferences("online_config", 0).edit().clear().commit();
    }
}
