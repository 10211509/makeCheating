package nobugs.team.cheating.mvp.presenter;

import java.util.List;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public interface IRecyclerPresenter extends IPresenter{

    void onRecyclerRefresh();

    void onRecyclerMore();


    interface View <DATA> extends IPresenter.IView{
        void showData(List<DATA> data);

        void showEmpty();

        void showNetworkError();
    }
}
