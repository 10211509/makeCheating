package nobugs.team.cheating.event;

import nobugs.team.cheating.model.Course;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class CourseChooseEvent {
    private Course subjectChoose;

    public CourseChooseEvent(Course subjectChoose) {
        this.subjectChoose = subjectChoose;
    }

    public Course getCourseChoose() {
        return subjectChoose;
    }

    public void setSubjectChoose(Course subjectChoose) {
        this.subjectChoose = subjectChoose;
    }
}
