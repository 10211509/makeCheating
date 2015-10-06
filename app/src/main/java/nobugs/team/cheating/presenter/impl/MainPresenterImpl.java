package nobugs.team.cheating.presenter.impl;

import java.util.List;

import de.greenrobot.event.EventBus;
import nobugs.team.cheating.event.CourseChooseEvent;
import nobugs.team.cheating.model.Course;
import nobugs.team.cheating.presenter.MainPresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.repo.api.CourseApi;
import nobugs.team.cheating.repo.api.impl.CourseApiImpl;
import nobugs.team.cheating.repo.api.model.RespBase;
import nobugs.team.cheating.repo.api.retrofit.RestClient;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by wangyf on 2015/9/13 0013.
 */
public class MainPresenterImpl extends BasePresenter<MainPresenter.View> implements MainPresenter {

    public MainPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void onRecyclerRefresh() {
        CourseApi api = new CourseApiImpl();
        api.getCourses(new CourseApi.Callback() {
            @Override
            public void onFinish(List<Course> courses) {
                getView().showData(courses);
            }

            @Override
            public void onError(int errType, String errMsg) {
                getView().showNetworkError();
            }
        });
    }

    @Override
    public void onRecyclerMore() {

    }

    @Override
    public void onChooseSubject(Course subject) {
        EventBus.getDefault().postSticky(new CourseChooseEvent(subject));

        getView().goExamPaperView();
    }

    @Override
    public void unbind() {
        getView().showLoadingDlg(null, "解绑中，请稍后...", true);
        final Call<RespBase> call = RestClient.getApiService().unAuth(SpHelper.get(SpKeys.TOKEN, ""));
        call.enqueue(new retrofit.Callback<RespBase>() {
            @Override
            public void onResponse(Response<RespBase> response) {
                getView().dismissAllDlg();
                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getMessage() == 200) {
                        getView().showToast("解绑成功");

                        //清空token 和 code
                        SpHelper.clear();

                        getView().navigateToAuthView();
                    } else {
                        getView().showToast("解绑失败，请重试");
                    }
                } else {
                    getView().showToast("解绑失败，请重试");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                getView().dismissAllDlg();
                getView().showToast("解绑失败，请重试");
            }
        });
    }

}
