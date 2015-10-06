package nobugs.team.cheating.repo.api.mapper;

import android.text.TextUtils;

import java.util.Arrays;

import nobugs.team.cheating.model.User;
import nobugs.team.cheating.repo.model.UserPo;

/**
 * Created by wangyf on 2015/9/29 0029.
 */
public class UserMapper implements IModelMapper<User, UserPo>{
    @Override
    public UserPo fromModel(User user) {
        return null;
    }

    @Override
    public User toModel(UserPo userPo) {
        User user = new User();
        user.setIsAuth(userPo.getStatus() == 1);
        user.setSubject(userPo.getSubject_name());
        user.setClassName(userPo.getClasses_name());
        if (!TextUtils.isEmpty(userPo.getCourse())) {
            user.setCourses(Arrays.asList(userPo.getCourse().split(",")));
        }
        return user;
    }
}
