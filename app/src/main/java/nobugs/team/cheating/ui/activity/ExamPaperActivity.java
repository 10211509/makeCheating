package nobugs.team.cheating.ui.activity;

import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.mvp.presenter.IPresenter;

public class ExamPaperActivity extends BaseActivity {


    @Override
    protected IPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_exam_paper;
    }

}
