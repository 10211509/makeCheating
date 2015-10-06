package nobugs.team.cheating.presenter;

import nobugs.team.cheating.presenter.base.IPresenter;

/**
 * Created by xiayong on 2015/9/13.
 */
public interface AuthPresenter extends IPresenter {

    int ERR_CODE_WRONG = 404;
    int ERR_CODE_BINDED = 412;
    int ERR_CODE_LOCKED = 413;
    int ERR_PARAM = 400;
    int ERR_SERVER = 500;

    void submitAuthCode(String authCode);

    interface View extends IView {
        void showAuthResult(boolean success, int errType, String errMsg);
    }
}
