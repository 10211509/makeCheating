package nobugs.team.cheating.repo.api.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class RespBaseList<T> extends RespBase{
    @Expose
    private List<T> result;

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

}
