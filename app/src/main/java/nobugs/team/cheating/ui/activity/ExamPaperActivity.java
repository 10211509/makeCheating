package nobugs.team.cheating.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.mvp.model.Question;
import nobugs.team.cheating.mvp.presenter.IPresenter;
import nobugs.team.cheating.mvp.presenter.QuestionPresenter;
import nobugs.team.cheating.mvp.presenter.impl.QuestionPresenterImpl;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ExamPaperActivity extends BaseActivity<QuestionPresenter> implements QuestionPresenter.View {


    @Bind(R.id.tv_subject)
    TextView tvSubject;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_eaxm_order)
    TextView tvEaxmOrder;
    @Bind(R.id.list_test_questions)
    StickyListHeadersListView listTestQuestions;

    @Override
    protected QuestionPresenter initPresenter() {
        return new QuestionPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_exam_paper;
    }

    @Override
    public void showData(List<Question> data) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showNetworkError() {

    }
}
