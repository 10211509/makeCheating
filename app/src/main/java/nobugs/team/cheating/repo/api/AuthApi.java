package nobugs.team.cheating.repo.api;

/**
 * Created by wangyf on 2015/9/23 0023.
 */
public interface AuthApi {

    void auth(String imei, String authCode, Callback callback);

    interface Callback {

        void onFinish();

        void onError(int errType, String errMsg);
    }
}
