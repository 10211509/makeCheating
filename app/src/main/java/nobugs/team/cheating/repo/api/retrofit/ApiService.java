package nobugs.team.cheating.repo.api.retrofit;

import nobugs.team.cheating.consts.AppConfig;
import nobugs.team.cheating.repo.api.model.RespAbout;
import nobugs.team.cheating.repo.api.model.RespApp;
import nobugs.team.cheating.repo.api.model.RespBase;
import nobugs.team.cheating.repo.api.model.RespCourse;
import nobugs.team.cheating.repo.api.model.RespExam;
import nobugs.team.cheating.repo.api.model.RespQuestion;
import nobugs.team.cheating.repo.api.model.RespToken;
import nobugs.team.cheating.repo.api.model.RespUser;
import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST(AppConfig.URL.BIND)
    Call<RespToken> auth(@Field("device") String imei, @Field("auth_code") String auth_code);

    @DELETE(AppConfig.URL.BIND)
    Call<RespBase> unAuth(@Header("token") String token);

    @FormUrlEncoded
    @POST(AppConfig.URL.TOKEN)
    Call<RespToken> login(@Field("device") String imei, @Field("auth_code") String auth_code);

    @GET(AppConfig.URL.COURSE)
    Call<RespCourse> getCourse(@Header("token") String token);

    @GET(AppConfig.URL.EXAM + "/{exam_id}")
    Call<RespExam> getExam(@Header("token") String token, @Path("exam_id") int exam_id);

    @GET(AppConfig.URL.QUESTION + "/{exam_id}/{question_no}")
    Call<RespQuestion> getQuestion(@Header("token") String token, @Path("exam_id") int exam_id, @Path("question_no") int question_no);

    @GET(AppConfig.URL.USER)
    Call<RespUser> getUser(@Header("token") String token);

    @GET(AppConfig.URL.APP)
    Call<RespApp> getAppUpdate();

    @GET(AppConfig.URL.ABOUT)
    Call<RespAbout> getAboutInfo();
}
