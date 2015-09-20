package nobugs.team.cheating.event;

import nobugs.team.cheating.model.Subject;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class SubjectChooseEvent {
    private Subject subjectChoose;

    public SubjectChooseEvent(Subject subjectChoose) {
        this.subjectChoose = subjectChoose;
    }

    public Subject getSubjectChoose() {
        return subjectChoose;
    }

    public void setSubjectChoose(Subject subjectChoose) {
        this.subjectChoose = subjectChoose;
    }
}
