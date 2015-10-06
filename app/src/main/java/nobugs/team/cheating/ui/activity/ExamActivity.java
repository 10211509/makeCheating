package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.squareup.phrase.Phrase;

import java.util.List;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.Exam;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.ExamPresenter;
import nobugs.team.cheating.presenter.impl.ExamPresenterImpl;
import nobugs.team.cheating.ui.adapter.QuestionAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ExamActivity extends BaseActivity<ExamPresenter> implements ExamPresenter.View, AdapterView.OnItemClickListener {

    @Bind(R.id.tv_subject)
    TextView tvSubject;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_exam_order)
    TextView tvEaxmOrder;
    @Bind(R.id.lv_questions)
    StickyListHeadersListView lvTestQuestions;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_exam_title)
    TextView tvExamTitle;

    @Override
    protected ExamPresenter initPresenter() {
        return new ExamPresenterImpl(this);
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
        QuestionAdapter questionAdapter = new QuestionAdapter(this, data);
        lvTestQuestions.setAdapter(questionAdapter);
        lvTestQuestions.setOnItemClickListener(this);
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
    public void showExamInfo(Exam exam) {
        if (!TextUtils.isEmpty(exam.getTitle())) {
            tvExamTitle.setText(exam.getTitle());
        } else if (!TextUtils.isEmpty(exam.getName())) {
            tvExamTitle.setText(exam.getName());
        }

        if (!TextUtils.isEmpty(exam.getCourseName())) {
            tvSubject.setText(Phrase.from("科目：{subject}").put("subject", exam.getCourseName()).format());
        }
        if (!TextUtils.isEmpty(exam.getTime())) {
            tvTime.setText(Phrase.from("时间：{time}").put("time", exam.getTime()).format());
        }
//        if (!TextUtils.isEmpty(exam.getCourseName())) {
//            tvSubject.setText(Phrase.from(tvSubject.getText()).put("subject", exam.getCourseName()).format());
//        }
    }

    @Override
    public void startExamDetailsActivity() {
        startActivity(new Intent(this, QuestionActivity.class));
    }

}
