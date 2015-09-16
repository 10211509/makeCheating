package nobugs.team.cheating.ui.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;

import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.mvp.presenter.QuestionDetailPresenter;

public class ExamDetailsActivity extends BaseActivity<QuestionDetailPresenter> implements QuestionDetailPresenter.View{


    @Override
    protected QuestionDetailPresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_exam_details;
    }
}
