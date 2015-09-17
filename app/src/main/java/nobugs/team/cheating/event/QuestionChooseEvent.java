package nobugs.team.cheating.event;

import java.util.List;

import nobugs.team.cheating.mvp.model.Question;

/**
 * Created by xiayong on 2015/9/17.
 */
public class QuestionChooseEvent {
    private int selectedIndex;
    private List<Question> allQuestions;

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public List<Question> getAllQuestions() {
        return allQuestions;
    }

    public void setAllQuestions(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
    }
}
