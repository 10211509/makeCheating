package nobugs.team.cheating.repo.api;

import java.util.List;

import nobugs.team.cheating.model.Course;

/**
 * Created by wangyf on 2015/9/23 0023.
 */
public interface CourseApi {

    void getCourses(Callback callback);

    interface Callback {

        void onFinish(List<Course> courses);

        void onError(int errType, String errMsg);
    }
}
