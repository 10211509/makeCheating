package nobugs.team.cheating.app.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import nobugs.team.cheating.R;
import nobugs.team.cheating.mvp.presenter.IPresenter;

/**
 * Created by xiayong on 2015/8/7.
 */

public abstract class BaseActivity<PresenterType extends IPresenter> extends AppCompatActivity {

    private PresenterType mPresenter;

    public PresenterType getPresenter() {
        return mPresenter;
    }

    public void setPresenter(PresenterType mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(initPresenter());

        setContentView(getLayoutResId());

        ButterKnife.bind(this);

        initView();
        initEvent();
        initData();

        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        updateData();

        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_app_exit:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract PresenterType initPresenter();

    protected abstract int getLayoutResId();

    protected void initView() {
    }


    protected void initEvent() {
    }


    protected void initData() {
    }


    protected void updateData() {
    }

}
