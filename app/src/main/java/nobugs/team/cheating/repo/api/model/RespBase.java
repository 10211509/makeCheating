package nobugs.team.cheating.repo.api.model;

import com.google.gson.annotations.Expose;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class RespBase {
    @Expose
    private int message;

    public void setMessage(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }

}
