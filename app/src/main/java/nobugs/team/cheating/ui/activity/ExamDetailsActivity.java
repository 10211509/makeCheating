package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.QuestionDetailPresenter;
import nobugs.team.cheating.presenter.impl.QuestionDetailPresenterImpl;

public class ExamDetailsActivity extends BaseActivity<QuestionDetailPresenter> implements QuestionDetailPresenter.View {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected QuestionDetailPresenter initPresenter() {
        return new QuestionDetailPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_exam_details;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showQuestionDetails(Question question) {

    }

    @Override
    public void navigateToExamPaperActivity() {
        startActivity(new Intent(this, ExamPaperActivity.class));
    }

}
