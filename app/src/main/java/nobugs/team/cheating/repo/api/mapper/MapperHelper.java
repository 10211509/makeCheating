package nobugs.team.cheating.repo.api.mapper;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyf on 2015/9/29 0029.
 */
public class MapperHelper {

    public static <Po, Model> List<Model> toModelList(List<Po> pos, @NonNull IModelMapper<Model, Po> mapper){
        List<Model> models = new ArrayList<>();
        for (Po po: pos){
            models.add(mapper.toModel(po));
        }
        return models;
    }
}
