package nobugs.team.cheating.mvp.presenter;

import nobugs.team.cheating.mvp.model.Question;

/**
 * Created by xiayong on 2015/9/13.
 */
public interface QuestionPresenter extends IListViewPresenter {

    void navigateToExamDetails(int position);
    interface View extends IListViewPresenter.View<Question> {
        void startExamDetailsActivity();
    }
}
