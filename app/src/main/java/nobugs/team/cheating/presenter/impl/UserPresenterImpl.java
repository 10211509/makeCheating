package nobugs.team.cheating.presenter.impl;

import nobugs.team.cheating.presenter.UserPresenter;
import nobugs.team.cheating.presenter.base.BasePresenter;
import nobugs.team.cheating.repo.api.mapper.UserMapper;
import nobugs.team.cheating.repo.api.model.RespUser;
import nobugs.team.cheating.repo.api.retrofit.RestClient;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public class UserPresenterImpl extends BasePresenter<UserPresenter.View> implements UserPresenter {
    public UserPresenterImpl(View mView) {
        super(mView);
    }

    @Override
    public void onCreate() {
        String token = SpHelper.get(SpKeys.TOKEN, "");
        Call<RespUser> call = RestClient.getApiService().getUser(token);
        call.enqueue(new Callback<RespUser>() {

            @Override
            public void onResponse(Response<RespUser> response) {
                getView().showUserInfo(new UserMapper().toModel(response.body().getResult()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
