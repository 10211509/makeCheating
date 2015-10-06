package nobugs.team.cheating.repo.api.retrofit;

import android.text.TextUtils;
import android.util.Log;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import nobugs.team.cheating.consts.AppConfig;
import nobugs.team.cheating.utils.DigestUtils;
import okio.Buffer;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by wangyf on 2015/9/23 0023.
 */
public class RestClient {

    public static final int CONN_TIMEOUT = 5000;
    public static final int READ_TIMEOUT = 15000;

    private static ApiService mApiService;

    private RestClient() {
        initDefaultAdapter();
    }

    public static void initDefaultAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.URL.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkClient())
                .build();
        mApiService = retrofit.create(ApiService.class);
    }

    public static ApiService getApiService() {
        if (mApiService == null) {
            initDefaultAdapter();
        }
        return mApiService;
    }


    static OkHttpClient getOkClient() {
        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(CONN_TIMEOUT, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS);
        client.interceptors().add(new HeaderInterceptor());

        return client;
    }

    /**
     * 2.0后的拦截器
     */
    static class HeaderInterceptor implements Interceptor {

        private static final String TAG = "OkHttp";

        private static final String F_BREAK = " %n";
        private static final String F_URL = " %s";
        private static final String F_TIME = " in %.1fms";
        private static final String F_HEADERS = "%s";
        private static final String F_RESPONSE = F_BREAK + "Response: %d";
        private static final String F_BODY = "body: %s";

        private static final String F_BREAKER = F_BREAK + "-------------------------------------------" + F_BREAK;
        private static final String F_REQUEST_WITHOUT_BODY = F_URL + F_TIME + F_BREAK + F_HEADERS;
        private static final String F_RESPONSE_WITHOUT_BODY = F_RESPONSE + F_BREAK + F_HEADERS + F_BREAKER;
        private static final String F_REQUEST_WITH_BODY = F_URL + F_TIME + F_BREAK + F_HEADERS + F_BODY + F_BREAK;
        private static final String F_RESPONSE_WITH_BODY = F_RESPONSE + F_BREAK + F_HEADERS + F_BODY + F_BREAK + F_BREAKER;


        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            request = request.newBuilder().addHeader("apiver", "v1").addHeader("signature", calcSignature(request)).build();

            long t1 = System.nanoTime();

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();


            MediaType contentType = null;
            String bodyString = null;
            if (response.body() != null) {
                contentType = response.body().contentType();
                bodyString = response.body().string();
            }

            double time = (t2 - t1) / 1e6d;

            if (request.method().equals("GET")) {
                Log.d(TAG, String.format("GET " + F_REQUEST_WITHOUT_BODY + F_RESPONSE_WITH_BODY, request.url(), time, request.headers(), response.code(), response.headers(), stringifyResponseBody(bodyString)));
            } else if (request.method().equals("POST")) {
                Log.d(TAG, String.format("POST " + F_REQUEST_WITH_BODY + F_RESPONSE_WITH_BODY, request.url(), time, request.headers(), stringifyRequestBody(request), response.code(), response.headers(), stringifyResponseBody(bodyString)));
            } else if (request.method().equals("PUT")) {
                Log.d(TAG, String.format("PUT " + F_REQUEST_WITH_BODY + F_RESPONSE_WITH_BODY, request.url(), time, request.headers(), request.body().toString(), response.code(), response.headers(), stringifyResponseBody(bodyString)));
            } else if (request.method().equals("DELETE")) {
                Log.d(TAG, String.format("DELETE " + F_REQUEST_WITHOUT_BODY + F_RESPONSE_WITHOUT_BODY, request.url(), time, request.headers(), response.code(), response.headers()));
            }

            if (response.body() != null && bodyString != null) {
                ResponseBody body = ResponseBody.create(contentType, bodyString);
                return response.newBuilder().body(body).build();
            } else {
                return response;
            }
        }

        private String calcSignature(Request request) {

            String signature;

            String token = request.headers().get("token");

            HttpUrl url = request.httpUrl();
            StringBuilder paths = new StringBuilder();
            for (String path: url.pathSegments()) {
                paths.append(path);
            }

            StringBuilder signs = new StringBuilder(DigestUtils.getMD5(paths.toString()));

            if (TextUtils.isEmpty(token)) {
                signature = DigestUtils.getMD5(signs.append("v1").append("kdt201509").toString());
            } else {
                signature = DigestUtils.getMD5(signs.append("v1").append(token).append("kdt201509").toString());
            }

            return signature;
        }

        private String stringifyResponseBody(String responseBody) {
            return responseBody;
        }

        private String stringifyRequestBody(Request request) {
            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                return buffer.readUtf8();
            } catch (final IOException e) {
                return "did not work";
            }
        }
    }


    static class MyConverter implements Converter<String> {

        @Override
        public String fromBody(ResponseBody body) throws IOException {
            return null;
        }

        @Override
        public RequestBody toBody(String value) {
            return null;
        }

    }

}
