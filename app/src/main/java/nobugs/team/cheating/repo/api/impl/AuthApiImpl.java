package nobugs.team.cheating.repo.api.impl;

import nobugs.team.cheating.repo.api.AuthApi;
import nobugs.team.cheating.repo.api.model.RespToken;
import nobugs.team.cheating.repo.api.retrofit.RestClient;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by wangyf on 2015/10/1 0001.
 */
public class AuthApiImpl implements AuthApi{

    @Override
    public void auth(final String imei, final String authCode, final Callback callback) {

        final Call<RespToken> call = RestClient.getApiService().auth(imei, authCode);
        call.enqueue(new retrofit.Callback<RespToken>() {
            @Override
            public void onResponse(Response<RespToken> response) {
                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getMessage() == 200) {

                        String token = response.body().getToken();
                        //储存token
                        SpHelper.put(SpKeys.TOKEN, token);
                        SpHelper.put(SpKeys.AUTH_CODE, authCode);

                        callback.onFinish();

                    } else {
                        callback.onError(response.body().getMessage(), "");
                    }
                } else {
                    callback.onError(response.code(), "HTTP错误：" + response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(0, t.getMessage());
            }
        });
    }
}
