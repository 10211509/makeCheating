package nobugs.team.cheating.mvp.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import nobugs.team.cheating.mvp.model.Question;
import nobugs.team.cheating.mvp.presenter.BasePresenter;
import nobugs.team.cheating.mvp.presenter.QuestionPresenter;

/**
 * Created by xiayong on 2015/9/13.
 */
public class QuestionPresenterImpl extends BasePresenter<QuestionPresenter.View> implements QuestionPresenter {
    List<Question> questions;

    public QuestionPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void onCreate() {
        getQuestionList();
    }

    private void getQuestionList() {
        questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Question question = new Question();
            question.setAnswer("answer");
            question.setSection(i / 5);
            question.setTitle("title");
            question.setType("type");
            questions.add(question);
        }
        getView().showData(questions);
    }

    @Override
    public void navigateToExamDetails(int position) {
        getView().startExamDetailsActivity();
    }
}
