package nobugs.team.cheating.model;

/**
 * Created by wangyf on 2015/9/21 0021.
 */
public class Company {
    private String name;
    private String telServ;
    private String telTech;
    private String site;

    public Company(String name, String telServ, String telTech, String site) {
        this.name = name;
        this.telServ = telServ;
        this.telTech = telTech;
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelServ() {
        return telServ;
    }

    public void setTelServ(String telServ) {
        this.telServ = telServ;
    }

    public String getTelTech() {
        return telTech;
    }

    public void setTelTech(String telTech) {
        this.telTech = telTech;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
