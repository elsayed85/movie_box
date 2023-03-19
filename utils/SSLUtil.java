package com.movieboxpro.android.utils;

import com.movieboxpro.android.R;
import com.movieboxpro.android.app.App;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
/* compiled from: SSLUtil.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\nH\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\b\u0010\u000f\u001a\u00020\fH\u0002R\"\u0010\u0003\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0005`\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/utils/SSLUtil;", "", "()V", "CERTIFICATES_DATA", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "addCertificate", "", "getKeyStore", "Ljava/security/KeyStore;", "getMX509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "getSSLSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getTrustManager", "app_webRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SSLUtil {
    private ArrayList<byte[]> CERTIFICATES_DATA = new ArrayList<>();

    public final X509TrustManager getMX509TrustManager() {
        return getTrustManager();
    }

    public final synchronized void addCertificate() {
        ArrayList<Number> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf((int) R.raw.ca4));
        for (Number number : arrayList) {
            InputStream openRawResource = App.getContext().getResources().openRawResource(number.intValue());
            Intrinsics.checkNotNullExpressionValue(openRawResource, "getContext().resources.openRawResource(it)");
            try {
                ArrayList arrayList2 = new ArrayList();
                int i = 0;
                for (int available = openRawResource.available(); available > 0; available = openRawResource.available()) {
                    byte[] bArr = new byte[available];
                    openRawResource.read(bArr);
                    arrayList2.add(bArr);
                    i += available;
                }
                byte[] bArr2 = new byte[i];
                Iterator it = arrayList2.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    byte[] bArr3 = (byte[]) it.next();
                    int length = bArr3.length;
                    System.arraycopy(bArr3, 0, bArr2, i2, length);
                    i2 += length;
                }
                this.CERTIFICATES_DATA.add(bArr2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        InputStream openRawResource2 = App.getContext().getResources().openRawResource(R.raw.ca);
        Intrinsics.checkNotNullExpressionValue(openRawResource2, "getContext().resources.openRawResource(R.raw.ca)");
        ArrayList<String> arrayList3 = new ArrayList();
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource2));
        boolean z = false;
        for (String str : TextStreamsKt.lineSequence(bufferedReader)) {
            if (Intrinsics.areEqual(str, "-----BEGIN CERTIFICATE-----")) {
                sb.setLength(0);
                z = true;
            } else if (Intrinsics.areEqual(str, "-----END CERTIFICATE-----")) {
                sb.append(str);
                arrayList3.add(sb.toString());
                z = false;
            }
            if (z) {
                sb.append(str);
                sb.append(ShellUtil.COMMAND_LINE_END);
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(bufferedReader, null);
        for (String str2 : arrayList3) {
            ArrayList<byte[]> arrayList4 = this.CERTIFICATES_DATA;
            byte[] bytes = str2.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            arrayList4.add(bytes);
        }
    }

    private final X509TrustManager getTrustManager() {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(getKeyStore());
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException(Intrinsics.stringPlus("Unexpected default trust managers:", Arrays.toString(trustManagers)));
        }
        TrustManager trustManager = trustManagers[0];
        if (trustManager != null) {
            return (X509TrustManager) trustManager;
        }
        throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
    }

    public final SSLSocketFactory getSSLSocketFactory() {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(null, new X509TrustManager[]{getMX509TrustManager()}, null);
        SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
        Intrinsics.checkNotNullExpressionValue(socketFactory, "sslContext.socketFactory");
        return socketFactory;
    }

    private final KeyStore getKeyStore() {
        ArrayList arrayList = new ArrayList();
        if (!this.CERTIFICATES_DATA.isEmpty()) {
            Iterator<byte[]> it = this.CERTIFICATES_DATA.iterator();
            while (it.hasNext()) {
                arrayList.add(new ByteArrayInputStream(it.next()));
            }
        }
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        int i = 0;
        try {
            int size = arrayList.size();
            while (i < size) {
                Object obj = arrayList.get(i);
                Intrinsics.checkNotNullExpressionValue(obj, "certificates[i]");
                InputStream inputStream = (InputStream) obj;
                int i2 = i + 1;
                keyStore.setCertificateEntry(Integer.toString(i), certificateFactory.generateCertificate(inputStream));
                inputStream.close();
                i = i2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intrinsics.checkNotNullExpressionValue(keyStore, "keyStore");
        return keyStore;
    }
}
