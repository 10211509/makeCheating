package nobugs.team.cheating.presenter.impl;

import android.telephony.TelephonyManager;
import android.text.TextUtils;

import nobugs.team.cheating.app.base.MyApplication;
import nobugs.team.cheating.presenter.AuthPresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.repo.api.AuthApi;
import nobugs.team.cheating.repo.api.LoginApi;
import nobugs.team.cheating.repo.api.impl.AuthApiImpl;
import nobugs.team.cheating.repo.api.impl.LoginApiImpl;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public class AuthPresenterImpl extends BasePresenter<AuthPresenter.View> implements AuthPresenter {
    private String imei;

    public AuthPresenterImpl(View mView) {
        super(mView);
    }

    private String getImei() {
        if (imei == null) {
            imei = ((TelephonyManager) MyApplication.getInstance().getSystemService(MyApplication.TELEPHONY_SERVICE)).getDeviceId();
        }
        return imei;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        String token = SpHelper.get(SpKeys.TOKEN, "");
        if (!TextUtils.isEmpty(token)) {
            getView().showLoadingDlg(null, "正在自动登录中, 请稍后...", false);

            String authCode = SpHelper.get(SpKeys.AUTH_CODE, "");

            doLogin(getImei(), authCode);
        }
    }

    private void doLogin(String imei, String authCode) {
        LoginApi api = new LoginApiImpl();

        api.login(imei, authCode, new LoginApi.Callback() {
            @Override
            public void onFinish() {
                getView().showAuthResult(true, 0, "");
            }

            @Override
            public void onError(int errType, String errMsg) {
                getView().showAuthResult(false, errType, errMsg);
            }
        });
    }


    @Override
    public void submitAuthCode(final String authCode) {
        AuthApi api = new AuthApiImpl();

        api.auth(getImei(), authCode, new AuthApi.Callback() {
            @Override
            public void onFinish() {
                getView().showAuthResult(true, 0, "");
            }

            @Override
            public void onError(int errType, String errMsg) {
                if (errType == ERR_CODE_BINDED) {
                    doLogin(imei, authCode);
                } else {
                    getView().showAuthResult(false, errType, errMsg);
                }
            }
        });

    }
}
