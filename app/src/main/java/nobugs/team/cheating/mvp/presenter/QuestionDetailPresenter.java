package nobugs.team.cheating.mvp.presenter;

import nobugs.team.cheating.mvp.model.Question;

/**
 * Created by xiayong on 2015/9/15.
 */
public interface QuestionDetailPresenter extends IPresenter {
    void handlePreBtnClick();
    void handleNextBtnClick();
    void handleContentsClick();
    interface View extends IPresenter.IView{
        void showQuestionDetails(Question question);
        void navigateToExamPaperActivity();
    }
}
