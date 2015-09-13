package nobugs.team.cheating.mvp.presenter;

import nobugs.team.cheating.mvp.model.Subject;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public interface SubjectPresenter extends IRecyclerPresenter {

    void onChooseSubject(Subject subject);

    interface View extends IRecyclerPresenter.View<Subject>{
        void goExamPaperView();
    }
}
