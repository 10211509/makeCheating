package nobugs.team.cheating.repo.model;

/**
 * Created by wangyf on 2015/9/27 0027.
 */
public class AboutPo {

    /**
     * logo : 第二题
     * tel : 010-12458796
     * info : 这真是个了不起的app
     */

    private String logo;
    private String tel;
    private String info;

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getLogo() {
        return logo;
    }

    public String getTel() {
        return tel;
    }

    public String getInfo() {
        return info;
    }
}
