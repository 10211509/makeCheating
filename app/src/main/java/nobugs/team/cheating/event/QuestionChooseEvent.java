package nobugs.team.cheating.event;

import nobugs.team.cheating.model.Exam;

/**
 * Created by xiayong on 2015/9/17.
 */
public class QuestionChooseEvent {
    private int selectedIndex;
    private Exam exam;

    public QuestionChooseEvent(int selectedIndex, Exam exam) {
        this.selectedIndex = selectedIndex;
        this.exam = exam;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
