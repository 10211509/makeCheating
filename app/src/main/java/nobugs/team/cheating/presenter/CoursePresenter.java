package nobugs.team.cheating.presenter;

import nobugs.team.cheating.model.Course;
import nobugs.team.cheating.presenter.base.IRecyclerPresenter;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public interface CoursePresenter extends IRecyclerPresenter {

    void onChooseSubject(Course subject);

    interface View extends IRecyclerPresenter.View<Course> {
        void goExamPaperView();
    }
}
