package nobugs.team.cheating.model;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public class App {

    private String logo;
    private String name;
    private String version;

    public App(String logo, String name, String version) {
        this.logo = logo;
        this.name = name;
        this.version = version;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
