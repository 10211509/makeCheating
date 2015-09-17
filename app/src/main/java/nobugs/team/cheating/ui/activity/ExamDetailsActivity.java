package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.mvp.model.Question;
import nobugs.team.cheating.mvp.presenter.QuestionDetailPresenter;
import nobugs.team.cheating.mvp.presenter.impl.QuestionDetailPresenterImpl;

public class ExamDetailsActivity extends BaseActivity<QuestionDetailPresenter> implements QuestionDetailPresenter.View{


    @Override
    protected QuestionDetailPresenter initPresenter() {
        return new QuestionDetailPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_exam_details;
    }

    @Override
    public void showQuestionDetails(Question question) {

    }

    @Override
    public void navigateToExamPaperActivity() {
        startActivity(new Intent(this,ExamPaperActivity.class));
    }
}
