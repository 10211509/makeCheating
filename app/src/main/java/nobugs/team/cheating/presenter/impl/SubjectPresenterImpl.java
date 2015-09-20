package nobugs.team.cheating.presenter.impl;

import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import nobugs.team.cheating.event.SubjectChooseEvent;
import nobugs.team.cheating.model.Subject;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.presenter.SubjectPresenter;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class SubjectPresenterImpl extends BasePresenter<SubjectPresenter.View> implements SubjectPresenter {

    List<Subject> subjects;

    public SubjectPresenterImpl(SubjectPresenter.View mView) {
        super(mView);
        initMockData();
    }


    @Override
    public void onRecyclerRefresh() {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                getView().showData(subjects);
            }
        }, 1000);
    }

    @Override
    public void onRecyclerMore() {

    }

    private void initMockData(){
        subjects = new ArrayList<>();
        subjects.add(new Subject("会计", "9月考试"));
        subjects.add(new Subject("数学","10月考试"));
        subjects.add(new Subject("政治","11月考试"));
        subjects.add(new Subject("电算化","9月考试"));
        subjects.add(new Subject("人文","10月考试"));
        subjects.add(new Subject("历史","8月考试"));
        subjects.add(new Subject("音乐","4月考试"));
        subjects.add(new Subject("哲学","6月考试"));
        subjects.add(new Subject("会计", "9月考试"));
        subjects.add(new Subject("数学","10月考试"));
        subjects.add(new Subject("政治","11月考试"));
        subjects.add(new Subject("电算化","9月考试"));
        subjects.add(new Subject("人文","10月考试"));
        subjects.add(new Subject("历史","8月考试"));
        subjects.add(new Subject("音乐","4月考试"));
        subjects.add(new Subject("哲学","6月考试"));
    }

    @Override
    public void onChooseSubject(Subject subject) {
        EventBus.getDefault().postSticky(new SubjectChooseEvent(subject));

        getView().goExamPaperView();
    }

}
