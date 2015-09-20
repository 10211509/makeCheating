package nobugs.team.cheating.presenter;

import nobugs.team.cheating.model.User;
import nobugs.team.cheating.presenter.base.IPresenter;

/**
 * Created by xiayong on 2015/9/13.
 */
public interface UserInfoPresenter extends IPresenter {

    interface View extends IPresenter.IView {
        void showUserInfo(User user);
    }
}
