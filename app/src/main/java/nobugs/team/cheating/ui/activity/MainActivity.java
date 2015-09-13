package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;

import java.util.List;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.mvp.model.Subject;
import nobugs.team.cheating.mvp.presenter.SubjectPresenter;
import nobugs.team.cheating.mvp.presenter.impl.SubjectPresenterImpl;
import nobugs.team.cheating.ui.adapter.MainSubjectAdapter;

public class MainActivity extends BaseActivity<SubjectPresenter> implements
        SubjectPresenter.View,
        SwipeRefreshLayout.OnRefreshListener,
        MainSubjectAdapter.OnItemClickListener {

    @Bind(R.id.tool_bar_main)
    Toolbar toolBarMain;
    @Bind(R.id.rv_main_subjects)
    RecyclerView rvMainSubjects;
    @Bind(R.id.srl_main_subjects)
    SwipeRefreshLayout srlMainSubjects;


    private MainSubjectAdapter mSubjectAdapter;

    @Override
    protected SubjectPresenter initPresenter() {
        return new SubjectPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolBarMain);

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        srlMainSubjects.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        // RecyclerView
        GridLayoutManager mgr = new GridLayoutManager(this, 2);
        rvMainSubjects.setLayoutManager(mgr);

        mSubjectAdapter = new MainSubjectAdapter();
        mSubjectAdapter.setListener(this);
        rvMainSubjects.setAdapter(mSubjectAdapter);
    }

    @Override
    protected void initEvent() {
        srlMainSubjects.setOnRefreshListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getPresenter().onRecyclerRefresh();
    }

    @Override
    public void onRefresh() {
        getPresenter().onRecyclerRefresh();
    }

    @Override
    public void showData(List<Subject> data) {
        srlMainSubjects.setRefreshing(false);

        mSubjectAdapter.setSubjects(data);
        mSubjectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void onItemClick(int index, Subject subject) {
        getPresenter().onChooseSubject(subject);
    }


    @Override
    public void goExamPaperView() {
        startActivity(new Intent(this, ExamPaperActivity.class));
    }
}
