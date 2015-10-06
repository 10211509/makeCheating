package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.phrase.Phrase;

import butterknife.Bind;
import butterknife.OnClick;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.Exam;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.QuestionDetailPresenter;
import nobugs.team.cheating.presenter.impl.QuestionPresenterImpl;

public class QuestionActivity extends BaseActivity<QuestionDetailPresenter> implements QuestionDetailPresenter.View {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_exam_title)
    TextView tvExamTitle;
    @Bind(R.id.tv_subject)
    TextView tvSubject;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_exam_order)
    TextView tvExamOrder;
    @Bind(R.id.tv_question_index)
    TextView tvQuestionIndex;
    @Bind(R.id.tv_section_value)
    TextView tvSectionValue;
    @Bind(R.id.tv_question_value)
    TextView tvQuestionValue;
    @Bind(R.id.tv_type_value)
    TextView tvTypeValue;
    @Bind(R.id.tv_answer_value)
    TextView tvAnswerValue;
    @Bind(R.id.btn_pre_question)
    Button btnPreQuestion;
    @Bind(R.id.btn_question_list)
    Button btnQuestionList;
    @Bind(R.id.btn_next_question)
    Button btnNextQuestion;

    @Override
    protected QuestionDetailPresenter initPresenter() {
        return new QuestionPresenterImpl(this);
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
        tvQuestionIndex.setText(Phrase.from("第{x}题").put("x", question.getSn()).format());
        tvSectionValue.setText(Phrase.from("第{x}部分").put("x", question.getSection()).format());
        if (!TextUtils.isEmpty(question.getTitle())) {
            tvQuestionValue.setText(question.getTitle());
        }
        if (!TextUtils.isEmpty(question.getAnswer())) {
            tvAnswerValue.setText(question.getAnswer());
        }
    }

    @Override
    public void navigateToExamPaperActivity() {
        startActivity(new Intent(this, ExamActivity.class));
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
    }

    @Override
    public void showNetworkError() {

    }

    @OnClick(R.id.btn_pre_question)
    void onPreClick(){
        getPresenter().handlePreBtnClick();
    }

    @OnClick(R.id.btn_next_question)
    void onNextClick(){
        getPresenter().handleNextBtnClick();
    }

    @OnClick(R.id.btn_question_list)
    void onListClick(){
        onBackPressed();
    }
}
