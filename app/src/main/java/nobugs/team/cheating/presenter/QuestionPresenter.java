package nobugs.team.cheating.presenter;

import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.base.IListViewPresenter;

/**
 * Created by xiayong on 2015/9/13.
 */
public interface QuestionPresenter extends IListViewPresenter {

    void navigateToExamDetails(int position);

    interface View extends IListViewPresenter.View<Question> {
        void startExamDetailsActivity();
    }
}
