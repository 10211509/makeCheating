package nobugs.team.cheating.presenter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import nobugs.team.cheating.app.base.LifeCycleCallback;


/**
 * Created by Administrator on 2015/8/16 0016.
 */
public interface IPresenter extends LifeCycleCallback {
    Context getContext();

    Fragment getFragment();

    FragmentActivity getActivity();


    interface IView {
        void showToast(String content);

        void showLoadingDlg(String title, String content, boolean cancelled);

        void dismissAllDlg();

        void showDlg(String title, String content);

        void showErrorDlg(String title, String error);
    }
}
