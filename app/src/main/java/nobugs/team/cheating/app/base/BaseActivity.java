package nobugs.team.cheating.app.base;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.avast.android.dialogs.fragment.ProgressDialogFragment;
import com.avast.android.dialogs.fragment.SimpleDialogFragment;

import butterknife.ButterKnife;
import nobugs.team.cheating.presenter.base.IPresenter;

/**
 * Created by xiayong on 2015/8/7.
 */

public abstract class BaseActivity<PresenterType extends IPresenter> extends AppCompatActivity implements IPresenter.IView{

    private static final String TAG_DLG = "dialog";

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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        switch (id){
//            case R.id.action_app_exit:
//                finish();
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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

    @Override
    public void showLoadingDlg(String title, String content, boolean cancelled) {
        ProgressDialogFragment.createBuilder(this, getSupportFragmentManager())
                .setMessage(content)
                .setTitle(title)
                .setCancelable(cancelled)
                .setCancelableOnTouchOutside(cancelled)
                .show();
    }

    @Override
    public void showDlg(String title, String content) {
        SimpleDialogFragment.createBuilder(this, getSupportFragmentManager())
                .setTitle(title)
                .setMessage(content)
                .show();

    }

    @Override
    public void showErrorDlg(String title, String error) {
        SimpleDialogFragment.createBuilder(this, getSupportFragmentManager())
                .setTitle(title)
                .setMessage(error)
                .show();
    }

    @Override
    public void dismissAllDlg(){
        for(Fragment frag : getSupportFragmentManager().getFragments()){
            if (frag instanceof DialogFragment){
                ((DialogFragment)frag).dismissAllowingStateLoss();
            }
        }
    }
}
