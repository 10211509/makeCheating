package nobugs.team.cheating.presenter;

import nobugs.team.cheating.model.App;
import nobugs.team.cheating.model.Company;
import nobugs.team.cheating.presenter.base.IPresenter;

/**
 * Created by xiayong on 2015/9/13.
 */
public interface AboutPresenter extends IPresenter {

    interface View extends IView {
        void showAppInfo(App app);
        void showCompanyInfo(Company company);
    }
}
