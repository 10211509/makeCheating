package nobugs.team.cheating.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.OnClick;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.presenter.AuthPresenter;
import nobugs.team.cheating.presenter.impl.AuthPresenterImpl;

public class AuthActivity extends BaseActivity<AuthPresenter> implements AuthPresenter.View {

    public static final String TAG = "AuthActivity";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.edt_auth_code)
    EditText edtAuthCode;
    @Bind(R.id.btn_auth)
    Button btnAuth;
    @Bind(R.id.til_auth_code)
    TextInputLayout tilAuthCode;

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

        edtAuthCode.setText("4201509252C6EA2ABB840");

        edtAuthCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, String.format("beforeTextChanged, s=%s, start=%d, count=%d, after=%d", s, start, count, after));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, String.format("onTextChanged, s=%s, start=%d, count=%d, before=%d", s, start, count, before));
                if (s.length() < 21 && (tilAuthCode.getError() == null || TextUtils.isEmpty(tilAuthCode.getError().toString()))) {
                    tilAuthCode.setError("授权码长度不得小于21位");
                    tilAuthCode.setErrorEnabled(true);
                } else if (s.length() >= 21) {
                    tilAuthCode.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, String.format("afterTextChanged, s=%s", s));
                if (s.length() <= 0) {
                    dismissHints();
                    tilAuthCode.setErrorEnabled(false);
                }
            }
        });
    }

    private void dismissHints() {
        try {
            Method method = tilAuthCode.getClass().getDeclaredMethod("expandHint", boolean.class);
            method.setAccessible(true);
            method.invoke(tilAuthCode, true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_auth)
    void onAuthClick() {
        String authCode = edtAuthCode.getText().toString().trim();
        if (!TextUtils.isEmpty(authCode)) {
            showLoadingDlg(null, "正在授权中, 请稍后...", false);

            getPresenter().submitAuthCode(authCode);
        }
    }

    @Override
    public void showAuthResult(boolean success, int errType, String errMsg) {
        dismissAllDlg();
        if (success) {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AuthActivity.this, CourseActivity.class));
            finish();
        } else if (!TextUtils.isEmpty(errMsg)) {
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "授权失败！错误码：" + errType, Toast.LENGTH_SHORT).show();
        }
    }
}
