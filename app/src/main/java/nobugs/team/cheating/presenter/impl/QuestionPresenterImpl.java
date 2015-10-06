package nobugs.team.cheating.presenter.impl;

import android.widget.Toast;

import java.util.List;

import de.greenrobot.event.EventBus;
import nobugs.team.cheating.event.QuestionChooseEvent;
import nobugs.team.cheating.model.Exam;
import nobugs.team.cheating.model.Question;
import nobugs.team.cheating.presenter.QuestionPresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.repo.api.mapper.QuestionMapper;
import nobugs.team.cheating.repo.api.model.RespQuestion;
import nobugs.team.cheating.repo.api.retrofit.RestClient;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by xiayong on 2015/9/15.
 */
public class QuestionPresenterImpl extends BasePresenter<QuestionPresenter.View> implements QuestionPresenter {
    private List<Question> mQuestions;
    private int mCurIndex = -1;
    private Exam mExam;
    private Question mCurQuestion;

    @Override
    public void onCreate() {
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(QuestionChooseEvent event) {
        mQuestions = event.getExam().getQuestions();
        mCurIndex = event.getSelectedIndex();

        mExam = event.getExam();
        getView().showExamInfo(mExam);

        if (mCurIndex >= 0 && mCurIndex < mQuestions.size()) {
            getQuestion(mCurIndex);
//            getView().showQuestionDetails(mQuestions.get(mCurIndex));
        }
    }

    public QuestionPresenterImpl(View mView) {
        super(mView);
    }

    private void getQuestion(final int index) {
        String token = SpHelper.get(SpKeys.TOKEN, "");
        mCurQuestion = mQuestions.get(index);

        Call<RespQuestion> call = RestClient.getApiService().getQuestion(token, mExam.getId(), mCurQuestion.getSn());
        call.enqueue(new Callback<RespQuestion>() {
            @Override
            public void onResponse(Response<RespQuestion> response) {
                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getMessage() == 200) {
                        Question question = new QuestionMapper().toModel(response.body().getResult().get(0));
                        try {
                            mCurQuestion.setAnswer(question.getAnswer());
                            if (index == mCurIndex) {
                                getView().showQuestionDetails(mCurQuestion);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        getView().showNetworkError();
                    }
                } else {
                    getView().showNetworkError();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().showNetworkError();
            }
        });
    }

    @Override
    public void handlePreBtnClick() {
        if (mCurIndex == 0) {
            Toast.makeText(getContext(), "已经是第一题了", Toast.LENGTH_SHORT).show();
            return;
        }
        getQuestion(--mCurIndex);
    }

    @Override
    public void handleNextBtnClick() {
        if (mCurIndex >= mQuestions.size() -1) {
            Toast.makeText(getContext(), "已经是最后一题了", Toast.LENGTH_SHORT).show();
            return;
        }
        getQuestion(++mCurIndex);
    }

    @Override
    public void handleContentsClick() {
        getView().navigateToExamPaperActivity();
    }
}
