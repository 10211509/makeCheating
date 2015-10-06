package nobugs.team.cheating.presenter.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.lang.ref.WeakReference;


/**
 * Autor: wangyf on 2015/8/15 0015 20:45
 * Email: zgtjwyftc@gmail.com
 * Description:
 */
public class BasePresenter<T extends IPresenter.IView> implements IPresenter {

    private WeakReference<T> mViewRef;

    public void setView(T view) {
        this.mViewRef = new WeakReference<>(view);
    }

    public T getView() {
        return mViewRef.get();
    }

    public BasePresenter(T view) {
        setView(view);
    }

    @Override
    public Context getContext() {
        if (getView() instanceof FragmentActivity) {
            return (FragmentActivity) getView();
        } else if (getView() instanceof Fragment) {
            return ((Fragment) getView()).getActivity();
        }
        throw new IllegalArgumentException("mViewRef must be FragmentActivity or Fragment");
    }

    @Override
    public FragmentActivity getActivity() {
        return (FragmentActivity) getContext();
    }

    @Override
    public Fragment getFragment() {
        if (getView() instanceof Fragment) {
            return (Fragment) getView();
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
