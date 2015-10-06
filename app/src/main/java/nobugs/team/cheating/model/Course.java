package nobugs.team.cheating.model;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class Course {

    public enum Status{
        WAIT(1), //1. 考卷尚未准备好：等待
        READY(2), //2. 考卷已经准备好：正常
        LOCKED(3); //3. 考卷已经锁定：锁定

        private int statVal;
        Status(int i) {
            statVal = i;
        }
    }

    private int id;

    private String name;

    private Status status;

    private String time;

    private String code;

    public Course() {
    }

    public Course(int id, String name, Status status, String time, String code) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.time = time;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
