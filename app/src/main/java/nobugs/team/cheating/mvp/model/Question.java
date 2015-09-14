package nobugs.team.cheating.mvp.model;

/**
 * Created by xiayong on 2015/9/13.
 */
public class Question {
    private String title;
    private String type;
    private String answer;
    private int section;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }
}
