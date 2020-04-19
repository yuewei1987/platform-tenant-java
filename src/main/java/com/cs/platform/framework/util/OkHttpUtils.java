package com.cs.platform.framework.util;

import okhttp3.OkHttpClient;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 */
public class OkHttpUtils {
    /**
     * 接收端可能需要添加 https 支持
     *
     * @param builder OkHttpClient.Builder
     * @see 参考 http://stackoverflow.com/questions/25509296/trusting-all-
     * certificates-with-okhttp
     */
    private static void addHttpsSupport(OkHttpClient.Builder builder) {
        try {
            final X509TrustManager tm = new X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            final HostnameVerifier v = new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            // 此 X509TrustManager 的写法在 okhttp 3 之前有效，如果更新依赖版本则需要修改写法
            final TrustManager[] trustAllCerts = new TrustManager[]{tm};
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            // 针对 HTTPS 的请求不自动重定向，进行手动处理，否则 OkHttp 会报错
            builder.sslSocketFactory(sslSocketFactory, tm).hostnameVerifier(v).followSslRedirects(false);
        } catch (Exception e) {
            System.out.println("给 OkHttpClient 添加 https 支持时出错");
            e.printStackTrace();
        }
    }

    public static OkHttpClient getIgnoreSSLClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        addHttpsSupport(builder);
        return builder.build();
    }
}
