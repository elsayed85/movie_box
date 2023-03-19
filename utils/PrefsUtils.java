package com.movieboxpro.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class PrefsUtils {
    private static PrefsUtils instance;
    public Context context;
    public SharedPreferences prefs;

    public static synchronized PrefsUtils getInstance() {
        PrefsUtils prefsUtils;
        synchronized (PrefsUtils.class) {
            if (instance == null) {
                init(App.getContext(), Constant.Prefs.SETTING, 4);
            }
            prefsUtils = instance;
        }
        return prefsUtils;
    }

    public static void init(Context context, String str, int i) {
        PrefsUtils prefsUtils = new PrefsUtils();
        instance = prefsUtils;
        prefsUtils.context = context;
        prefsUtils.prefs = context.getSharedPreferences(str, i);
    }

    private PrefsUtils() {
    }

    public boolean getBoolean(String str, boolean z) {
        return this.prefs.getBoolean(str, z);
    }

    public boolean getBoolean(String str) {
        return this.prefs.getBoolean(str, false);
    }

    public String getString(String str, String str2) {
        return this.prefs.getString(str, str2);
    }

    public String getString(String str) {
        return this.prefs.getString(str, null);
    }

    public int getInt(String str, int i) {
        return this.prefs.getInt(str, i);
    }

    public int getInt(String str) {
        return this.prefs.getInt(str, 0);
    }

    public float getFloat(String str, float f) {
        return this.prefs.getFloat(str, f);
    }

    public float getFloat(String str) {
        return this.prefs.getFloat(str, 0.0f);
    }

    public long getLong(String str, long j) {
        return this.prefs.getLong(str, j);
    }

    public long getLong(String str) {
        return this.prefs.getLong(str, 0L);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this.prefs.getStringSet(str, set);
    }

    public Set<String> getStringSet(String str) {
        return this.prefs.getStringSet(str, null);
    }

    public Map<String, ?> getAll() {
        return this.prefs.getAll();
    }

    public boolean exists(String str) {
        return this.prefs.contains(str);
    }

    public PrefsUtils putString(String str, String str2) {
        this.prefs.edit().putString(str, str2).apply();
        return this;
    }

    public PrefsUtils putInt(String str, int i) {
        this.prefs.edit().putInt(str, i).apply();
        return this;
    }

    public PrefsUtils putFloat(String str, float f) {
        this.prefs.edit().putFloat(str, f).apply();
        return this;
    }

    public PrefsUtils putLong(String str, long j) {
        this.prefs.edit().putLong(str, j).apply();
        return this;
    }

    public PrefsUtils putBoolean(String str, boolean z) {
        this.prefs.edit().putBoolean(str, z).apply();
        return this;
    }

    public PrefsUtils putStringSet(String str, Set<String> set) {
        this.prefs.edit().putStringSet(str, set).apply();
        return this;
    }

    public void putObject(String str, Object obj) {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            try {
                try {
                    objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                } catch (IOException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
            try {
                objectOutputStream.writeObject(obj);
                this.prefs.edit().putString(str, new String(Base64.encode(byteArrayOutputStream.toByteArray(), 0))).apply();
                byteArrayOutputStream.close();
                objectOutputStream.close();
            } catch (IOException e2) {
                e = e2;
                objectOutputStream2 = objectOutputStream;
                e.printStackTrace();
                byteArrayOutputStream.close();
                if (objectOutputStream2 != null) {
                    objectOutputStream2.close();
                }
            } catch (Throwable th2) {
                th = th2;
                objectOutputStream2 = objectOutputStream;
                try {
                    byteArrayOutputStream.close();
                    if (objectOutputStream2 != null) {
                        objectOutputStream2.close();
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    public <T> T getObject(String str, Class<T> cls) {
        ObjectInputStream objectInputStream;
        if (this.prefs.contains(str)) {
            ?? decode = Base64.decode(this.prefs.getString(str, null), 0);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    T t = (T) objectInputStream.readObject();
                    try {
                        byteArrayInputStream.close();
                        objectInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return t;
                } catch (StreamCorruptedException e3) {
                    e = e3;
                    e.printStackTrace();
                    byteArrayInputStream.close();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    return null;
                } catch (IOException e4) {
                    e = e4;
                    e.printStackTrace();
                    byteArrayInputStream.close();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    return null;
                } catch (ClassNotFoundException e5) {
                    e = e5;
                    e.printStackTrace();
                    byteArrayInputStream.close();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    return null;
                }
            } catch (StreamCorruptedException e6) {
                e = e6;
                objectInputStream = null;
            } catch (IOException e7) {
                e = e7;
                objectInputStream = null;
            } catch (ClassNotFoundException e8) {
                e = e8;
                objectInputStream = null;
            } catch (Throwable th2) {
                th = th2;
                decode = 0;
                try {
                    byteArrayInputStream.close();
                    if (decode != 0) {
                        decode.close();
                    }
                } catch (IOException e9) {
                    e9.printStackTrace();
                }
                throw th;
            }
        }
        return null;
    }

    public PrefsUtils remove(String str) {
        this.prefs.edit().remove(str).apply();
        return this;
    }

    public PrefsUtils removeAll() {
        this.prefs.edit().clear().apply();
        return this;
    }
}
