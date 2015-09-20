package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.QuestionPresenter;
import nobugs.team.cheating.presenter.impl.QuestionPresenterImpl;
import nobugs.team.cheating.ui.adapter.QuestionListAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ExamPaperActivity extends BaseActivity<QuestionPresenter> implements QuestionPresenter.View, AdapterView.OnItemClickListener {

    @Bind(R.id.tv_subject)
    TextView tvSubject;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_exam_order)
    TextView tvEaxmOrder;
    @Bind(R.id.lv_questions)
    StickyListHeadersListView listTestQuestions;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_exam_title)
    TextView tvExamTitle;

    @Override
    protected QuestionPresenter initPresenter() {
        return new QuestionPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_exam_paper;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

//        ActionBar actionbar = getSupportActionBar();
//        if (actionbar != null) {
//            actionbar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP);
//        }
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
    public void showData(List<Question> data) {
        QuestionListAdapter questionListAdapter = new QuestionListAdapter(this, data);
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
        startActivity(new Intent(this, ExamDetailsActivity.class));
    }

}
