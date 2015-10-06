package nobugs.team.cheating.repo.api.mapper;

import nobugs.team.cheating.model.Course;
import nobugs.team.cheating.repo.model.CoursePo;

/**
 * Created by wangyf on 2015/9/29 0029.
 */
public class CourseMapper implements IModelMapper<Course, CoursePo>{
    @Override
    public CoursePo fromModel(Course course) {
        return null;
    }

    @Override
    public Course toModel(CoursePo coursePo) {
        Course course = new Course();
        course.setId(Integer.parseInt(coursePo.getExam_id()));
        course.setName(coursePo.getCourse_name());
        course.setCode(coursePo.getCourse_code());
        course.setTime(coursePo.getExam_term());
        if (coursePo.getExam_status() != null) {
            switch (coursePo.getExam_status()){
                case "1":
                    course.setStatus(Course.Status.WAIT);
                    break;
                case "2":
                default:
                    course.setStatus(Course.Status.READY);
                    break;
                case "3":
                    course.setStatus(Course.Status.LOCKED);
                    break;
            }
        }
        return course;
    }
}
