package nobugs.team.cheating.model;

import java.util.List;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public class User {
    private boolean isAuth;
    private String authTime;
    private String name;
    private String avatarUrl;
    private String subject;
    private String className;
    private List<String> courses;

    public User() {
    }

    public User(boolean isAuth, String authTime, String name, String avatarUrl, String subject, List<String> course) {
        this.isAuth = isAuth;
        this.authTime = authTime;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.subject = subject;
        this.courses = course;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public String getAuthTime() {
        return authTime;
    }

    public void setAuthTime(String authTime) {
        this.authTime = authTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
