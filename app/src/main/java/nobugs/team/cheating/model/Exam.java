package nobugs.team.cheating.model;

import java.util.List;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class Exam {

    private int id;

    private String title;

    private String name;

    private String courseName;

    private String time;


    private List<Question> questions;

    public Exam() {
    }

    public Exam(int id, String title, String name, String courseName, String time, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.courseName = courseName;
        this.time = time;
        this.questions = questions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


}
