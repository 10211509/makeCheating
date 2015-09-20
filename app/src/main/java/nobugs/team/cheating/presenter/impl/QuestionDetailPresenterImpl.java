package nobugs.team.cheating.presenter.impl;

import android.widget.Toast;

import java.util.List;

import de.greenrobot.event.EventBus;
import nobugs.team.cheating.event.QuestionChooseEvent;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.presenter.QuestionDetailPresenter;

/**
 * Created by xiayong on 2015/9/15.
 */
public class QuestionDetailPresenterImpl extends BasePresenter<QuestionDetailPresenter.View> implements QuestionDetailPresenter{
    private List<Question> questions;
    private int currentPageIndex = -1;

    @Override
    public void onCreate() {
        EventBus.getDefault().registerSticky(this);
    }
    public void onEventMainThread(QuestionChooseEvent questionChooseEvent){
        questions = questionChooseEvent.getAllQuestions();
        currentPageIndex = questionChooseEvent.getSelectedIndex();
        if(currentPageIndex>=0 && currentPageIndex<questions.size()){
            getView().showQuestionDetails(questions.get(currentPageIndex));
        }
    }

    public QuestionDetailPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void handlePreBtnClick() {
        if(currentPageIndex == 0){
            Toast.makeText(getContext(),"已经是第一题了",Toast.LENGTH_SHORT).show();
            return;
        }
        getView().showQuestionDetails(questions.get(--currentPageIndex));
    }

    @Override
    public void handleNextBtnClick() {
        if(currentPageIndex>=questions.size()){
            Toast.makeText(getContext(),"已经是最后一题了",Toast.LENGTH_SHORT).show();
            return;
        }
        getView().showQuestionDetails(questions.get(++currentPageIndex));

    }

    @Override
    public void handleContentsClick() {
        getView().navigateToExamPaperActivity();
    }
}
