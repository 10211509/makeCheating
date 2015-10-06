package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.Course;
import nobugs.team.cheating.presenter.CoursePresenter;
import nobugs.team.cheating.presenter.impl.CoursePresenterImpl;
import nobugs.team.cheating.ui.adapter.CourseAdapter;

public class CourseActivity extends BaseActivity<CoursePresenter> implements
        CoursePresenter.View,
        SwipeRefreshLayout.OnRefreshListener,
        CourseAdapter.OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar toolBar;
    @Bind(R.id.rv_main_subjects)
    RecyclerView rvMainSubjects;
    @Bind(R.id.srl_main_subjects)
    SwipeRefreshLayout srlMainSubjects;
    @Bind(R.id.nv_main_navigation)
    NavigationView nvMainNavigation;
    @Bind(R.id.dl_main_drawer)
    DrawerLayout dlMainDrawer;

    private CourseAdapter mSubjectAdapter;
    private long exitTime = 0;

    @Override
    protected CoursePresenter initPresenter() {
        return new CoursePresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initRecycler();

        initToolbar();

        initDrawer();
    }

    private void initRecycler() {
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        srlMainSubjects.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        // RecyclerView
        GridLayoutManager mgr = new GridLayoutManager(this, 2);
        rvMainSubjects.setLayoutManager(mgr);

        mSubjectAdapter = new CourseAdapter();
        mSubjectAdapter.setListener(this);
        rvMainSubjects.setAdapter(mSubjectAdapter);
    }

    private void initToolbar() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void initDrawer() {
        nvMainNavigation.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        switch (menuItem.getItemId()){
                            case R.id.action_user_center:
                                startActivity(new Intent(CourseActivity.this, UserActivity.class));
                                return true;
                            case R.id.action_app_update:
                                Snackbar.make(srlMainSubjects, "您的应用已经是最新版", Snackbar.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_about_us:
                                startActivity(new Intent(CourseActivity.this, AboutActivity.class));
                                return true;
                            case R.id.action_app_exit:
                                dlMainDrawer.closeDrawers();
                                finish();
                                return true;
                        }
                        return true;
                    }
                });
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dlMainDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (dlMainDrawer.isDrawerOpen(GravityCompat.START)){
            dlMainDrawer.closeDrawer(GravityCompat.START);
        } else if ((System.currentTimeMillis() - exitTime) > 2000){
            Snackbar.make(srlMainSubjects, "再按一次退出程序", Snackbar.LENGTH_SHORT).setAction("退出", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CourseActivity.super.onBackPressed();
                }
            }).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void showData(List<Course> data) {
        srlMainSubjects.setRefreshing(false);

        mSubjectAdapter.setCourses(data);
        mSubjectAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void onItemClick(int index, Course subject) {
        getPresenter().onChooseSubject(subject);
    }


    @Override
    public void goExamPaperView() {
        startActivity(new Intent(this, ExamActivity.class));
    }



}
