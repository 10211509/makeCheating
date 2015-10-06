package nobugs.team.cheating.repo.model;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class CoursePo {

    private String exam_id;
    private String exam_status;
    private String exam_term;
    private String course_name;
    private String course_code;

    public void setExam_id(String exam_id) {
        this.exam_id = exam_id;
    }

    public void setExam_status(String exam_status) {
        this.exam_status = exam_status;
    }

    public void setExam_term(String exam_term) {
        this.exam_term = exam_term;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getExam_id() {
        return exam_id;
    }

    public String getExam_status() {
        return exam_status;
    }

    public String getExam_term() {
        return exam_term;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_code() {
        return course_code;
    }
}
