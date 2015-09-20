package nobugs.team.cheating.presenter;

import nobugs.team.cheating.model.Subject;
import nobugs.team.cheating.presenter.base.IRecyclerPresenter;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public interface SubjectPresenter extends IRecyclerPresenter {

    void onChooseSubject(Subject subject);

    interface View extends IRecyclerPresenter.View<Subject> {
        void goExamPaperView();
    }
}
