package nobugs.team.cheating.presenter.impl;

import java.util.List;

import de.greenrobot.event.EventBus;
import nobugs.team.cheating.event.CourseChooseEvent;
import nobugs.team.cheating.model.Course;
import nobugs.team.cheating.presenter.CoursePresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.repo.api.CourseApi;
import nobugs.team.cheating.repo.api.impl.CourseApiImpl;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class CoursePresenterImpl extends BasePresenter<CoursePresenter.View> implements CoursePresenter {

    public CoursePresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void onRecyclerRefresh() {
        CourseApi api = new CourseApiImpl();
        api.getCourses(new CourseApi.Callback() {
            @Override
            public void onFinish(List<Course> courses) {
                getView().showData(courses);
            }

            @Override
            public void onError(int errType, String errMsg) {
                getView().showNetworkError();
            }
        });
    }

    @Override
    public void onRecyclerMore() {

    }

    @Override
    public void onChooseSubject(Course subject) {
        EventBus.getDefault().postSticky(new CourseChooseEvent(subject));

        getView().goExamPaperView();
    }

}
