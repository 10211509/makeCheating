package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.OnClick;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.presenter.AuthPresenter;
import nobugs.team.cheating.presenter.impl.AuthPresenterImpl;

public class AuthActivity extends BaseActivity<AuthPresenter> implements AuthPresenter.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.edt_auth_code)
    EditText edtAuthCode;
    @Bind(R.id.btn_auth)
    Button btnAuth;

    @Override
    protected AuthPresenter initPresenter() {
        return new AuthPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btn_auth)
    void onAuthClick() {
        String authCode = edtAuthCode.getText().toString().trim();
        if (!TextUtils.isEmpty(authCode)) {
            getPresenter().submitAuthCode(authCode);
        }
    }

    @Override
    public void showAuthResult(boolean success, int errType) {
        if (success) {
            Snackbar.make(btnAuth, "授权成功！正在跳转...", Snackbar.LENGTH_SHORT).setAction("跳转首页", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                    finish();
                }
            }).setCallback(new Snackbar.Callback() {
                @Override
                public void onDismissed(Snackbar snackbar, int event) {
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                    finish();
                }
            }).show();
        } else {
            Snackbar.make(btnAuth, "授权失败！错误码：" + errType, Snackbar.LENGTH_SHORT).show();
        }
    }
}
