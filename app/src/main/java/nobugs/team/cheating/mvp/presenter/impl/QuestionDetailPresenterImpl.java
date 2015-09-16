package nobugs.team.cheating.mvp.presenter.impl;

import java.util.List;

import nobugs.team.cheating.mvp.model.Question;
import nobugs.team.cheating.mvp.presenter.BasePresenter;
import nobugs.team.cheating.mvp.presenter.QuestionDetailPresenter;

/**
 * Created by xiayong on 2015/9/15.
 */
public class QuestionDetailPresenterImpl extends BasePresenter<QuestionDetailPresenter.View> implements QuestionDetailPresenter{
    private List<Question> questions;
    private int currentPageIndex = -1;
    public QuestionDetailPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void handlePreBtnClick() {

    }

    @Override
    public void handleNextBtnClick() {

    }

    @Override
    public void handleContentsClick() {

    }
}
