package nobugs.team.cheating.presenter.impl;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import nobugs.team.cheating.event.CourseChooseEvent;
import nobugs.team.cheating.event.QuestionChooseEvent;
import nobugs.team.cheating.model.Exam;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.ExamPresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.repo.api.mapper.ExamMapper;
import nobugs.team.cheating.repo.api.model.RespExam;
import nobugs.team.cheating.repo.api.retrofit.RestClient;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by xiayong on 2015/9/13.
 */
public class ExamPresenterImpl extends BasePresenter<ExamPresenter.View> implements ExamPresenter {
    List<Question> questions;
    private Exam exam;

    public ExamPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this); //千万别忘了，否则activity泄露
    }

    public void onEventMainThread(CourseChooseEvent event){
        int examId = event.getCourseChoose().getId();
        String courseName = event.getCourseChoose().getName();
        String examTime = event.getCourseChoose().getTime();

//        initMockData();
        getQuestionList(examId, courseName, examTime);

        EventBus.getDefault().removeStickyEvent(event);
    }

    private void getQuestionList(int examId, final String courseName, final String examTime) {

        getView().showLoadingDlg(null, "获取数据中，请稍后...", true);
        String token = SpHelper.get(SpKeys.TOKEN, "");
        Call<RespExam> call = RestClient.getApiService().getExam(token, examId);
        call.enqueue(new Callback<RespExam>() {
            @Override
            public void onResponse(Response<RespExam> response) {
                getView().dismissAllDlg();
                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getMessage() == 200) {
                        exam = new ExamMapper().toModel(response.body().getResult());
                        exam.setCourseName(courseName);
                        exam.setTime(examTime);
                        
                        getView().showExamInfo(exam);

                        getView().showData(exam.getQuestions());
                    } else {
                        getView().showNetworkError();
                    }
                } else {
                    getView().showNetworkError();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissAllDlg();
                getView().showNetworkError();
            }
        });
    }

    private void initMockData() {
        questions = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Question question = new Question();
            question.setAnswer("answer");
            question.setSection(i / 5);
            question.setTitle("title");
            question.setType("type");
            question.setSn(i);
            questions.add(question);
        }
        getView().showData(questions);
    }

    @Override
    public void navigateToExamDetails(int position) {

        EventBus.getDefault().postSticky(new QuestionChooseEvent(position, exam));

        getView().startExamDetailsActivity();
    }
}
