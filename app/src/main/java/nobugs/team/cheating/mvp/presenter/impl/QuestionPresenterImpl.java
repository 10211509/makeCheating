package nobugs.team.cheating.mvp.presenter.impl;

import nobugs.team.cheating.mvp.presenter.BasePresenter;
import nobugs.team.cheating.mvp.presenter.QuestionPresenter;

/**
 * Created by xiayong on 2015/9/13.
 */
public class QuestionPresenterImpl extends BasePresenter<QuestionPresenter.View> implements QuestionPresenter{
    public QuestionPresenterImpl(View mView) {
        super(mView);
    }
}
