package nobugs.team.cheating.presenter.impl;

import nobugs.team.cheating.presenter.AuthPresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public class AuthPresenterImpl extends BasePresenter<AuthPresenter.View> implements AuthPresenter {
    public AuthPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void submitAuthCode(String authCode) {
        getView().showAuthResult(true, 0);
    }
}
