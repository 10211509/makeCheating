package nobugs.team.cheating.presenter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


/**
 * Autor: wangyf on 2015/8/15 0015 20:45
 * Email: zgtjwyftc@gmail.com
 * Description:
 */
public class BasePresenter<T extends IPresenter.IView> implements IPresenter {

    private T mView;

    public void setView(T view) {
        this.mView = view;
    }

    public T getView() {
        return mView;
    }

    public BasePresenter(T mView) {
        this.mView = mView;
    }

    @Override
    public Context getContext() {
        if (mView instanceof FragmentActivity) {
            return (FragmentActivity) mView;
        } else if (mView instanceof Fragment) {
            return ((Fragment) mView).getActivity();
        }
        throw new IllegalArgumentException("mView must be FragmentActivity or Fragment");
    }

    @Override
    public FragmentActivity getActivity() {
        return (FragmentActivity) getContext();
    }

    @Override
    public Fragment getFragment() {
        if (mView instanceof Fragment) {
            return (Fragment) mView;
        }
        return null;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void  onCreateView(){

    }
    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }
}
