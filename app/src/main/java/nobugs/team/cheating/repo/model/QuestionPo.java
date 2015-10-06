package nobugs.team.cheating.repo.model;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class QuestionPo {

    /**
     * title : 第二题
     * unit : 1
     * question_no : 2
     * answer : 答案二
     */
    private String id;
    private String title;
    private String unit;
    private String question_no;
    private String answer;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setQuestion_no(String question_no) {
        this.question_no = question_no;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

    public String getQuestion_no() {
        return question_no;
    }

    public String getAnswer() {
        return answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
