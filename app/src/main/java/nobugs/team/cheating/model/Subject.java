package nobugs.team.cheating.model;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class Subject {

    private String name;

    private String time;

    public Subject() {
    }

    public Subject(String name, String time) {
        this.name = name;
        this.time = time;
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
