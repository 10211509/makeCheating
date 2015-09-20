package nobugs.team.cheating.presenter.base;

import java.util.List;

/**
 * Created by xiayong on 2015/9/13.
 */
public interface IListViewPresenter extends IPresenter {

    interface View <DATA> extends IPresenter.IView{
        void showData(List<DATA> data);

        void showEmpty();

        void showNetworkError();
    }
}
