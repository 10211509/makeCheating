package nobugs.team.cheating.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.User;
import nobugs.team.cheating.presenter.UserPresenter;
import nobugs.team.cheating.presenter.impl.UserPresenterImpl;

public class UserActivity extends BaseActivity<UserPresenter> implements UserPresenter.View {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_auth_flag)
    TextView tvAuthFlag;
    @Bind(R.id.tv_auth_time)
    TextView tvAuthTime;
    @Bind(R.id.tv_major)
    TextView tvMajor;
    @Bind(R.id.tv_course)
    TextView tvCourse;
    @Bind(R.id.tv_class)
    TextView tvClass;

    @Override
    protected UserPresenter initPresenter() {
        return new UserPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user_info;
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
    public void showUserInfo(User user) {
        if (!TextUtils.isEmpty(user.getAvatarUrl())) {
            Picasso.with(this).load(user.getAvatarUrl()).into(ivAvatar);
        }
        if (user.isAuth()) {
            tvAuthFlag.setText("已授权");
        } else {
            tvAuthFlag.setText("未授权");
        }
        if (!TextUtils.isEmpty(user.getAuthTime())) {
            tvAuthTime.setText(user.getAuthTime());
        }
        if (!TextUtils.isEmpty(user.getSubject())) {
            tvMajor.setText(user.getSubject());
        }
        if (!TextUtils.isEmpty(user.getClassName())) {
            tvClass.setText(user.getClassName());
        }
//        if (!TextUtils.isEmpty(user.getCourses())) {
//            tvCourse.setText(user.getCourses());
//        }
        int csize = user.getCourses().size();
        if (user.getCourses() != null && csize > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < csize; i++) {
                sb.append(user.getCourses().get(i));
                if (i < csize - 1) {
                    sb.append(",\n");
                }
            }
            tvCourse.setText(sb.toString());
        }

    }

}
