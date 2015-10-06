package nobugs.team.cheating.repo.model;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class UserPo {

    /**
     * status : 1
     * classes_name : 印刷包装技术2015年秋季班
     * subject_name : 印刷包装技术
     * course : 中国现代史纲要,英语二
     */

    private int status;
    private String classes_name;
    private String subject_name;
    private String course;

    public void setStatus(int status) {
        this.status = status;
    }

    public void setClasses_name(String classes_name) {
        this.classes_name = classes_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getStatus() {
        return status;
    }

    public String getClasses_name() {
        return classes_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public String getCourse() {
        return course;
    }
}
