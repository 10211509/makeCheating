package nobugs.team.cheating.repo.api.model;

import com.google.gson.annotations.Expose;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class RespBaseObj<T> extends RespBase{
    @Expose
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
