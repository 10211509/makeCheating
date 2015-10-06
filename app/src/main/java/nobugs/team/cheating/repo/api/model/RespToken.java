package nobugs.team.cheating.repo.api.model;

import com.google.gson.annotations.Expose;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class RespToken extends RespBase {

    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
