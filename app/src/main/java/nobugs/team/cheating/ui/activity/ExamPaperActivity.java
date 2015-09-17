package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import nobugs.team.cheating.ui.adapter.QuestionListAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ExamPaperActivity extends BaseActivity<QuestionPresenter> implements QuestionPresenter.View,AdapterView.OnItemClickListener {


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
        QuestionListAdapter questionListAdapter = new QuestionListAdapter(this,data);
        listTestQuestions.setAdapter(questionListAdapter);
        listTestQuestions.setOnItemClickListener(this);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getPresenter().navigateToExamDetails(position);
    }

    @Override
    public void startExamDetailsActivity() {
        startActivity(new Intent(this,ExamDetailsActivity.class));
    }
}
