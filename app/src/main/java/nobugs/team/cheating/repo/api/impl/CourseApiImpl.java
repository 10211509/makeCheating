package nobugs.team.cheating.repo.api.impl;

import nobugs.team.cheating.repo.api.CourseApi;
import nobugs.team.cheating.repo.api.mapper.CourseMapper;
import nobugs.team.cheating.repo.api.mapper.MapperHelper;
import nobugs.team.cheating.repo.api.model.RespCourse;
import nobugs.team.cheating.repo.api.retrofit.RestClient;
import nobugs.team.cheating.repo.sp.SpHelper;
import nobugs.team.cheating.repo.sp.SpKeys;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by wangyf on 2015/10/1 0001.
 */
public class CourseApiImpl implements CourseApi{

    @Override
    public void getCourses(final Callback callback) {
        String token = SpHelper.get(SpKeys.TOKEN, "");
        Call<RespCourse> call = RestClient.getApiService().getCourse(token);
        call.enqueue(new retrofit.Callback<RespCourse>() {
            @Override
            public void onResponse(Response<RespCourse> response) {
                if (response.code() == 200 && response.body() != null) {
                    if (response.body().getMessage() == 200) {
                        callback.onFinish(MapperHelper.toModelList(response.body().getResult(), new CourseMapper()));
                    } else {
                        callback.onError(response.body().getMessage(), "");
                    }
                } else {
                    callback.onError(response.body().getMessage(), "");
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onError(0, t.getMessage());
            }
        });
    }
}
