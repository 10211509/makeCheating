package nobugs.team.cheating.app.base;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;


public class MyApplication extends Application {

    private static MyApplication instance;//keep one instance of the Application
    @Override
    public void onCreate() {
        super.onCreate();
//        OkVolleyUtils.init(this);
        CrashReport.initCrashReport(this, "900008519", false);
//        CrashReport.testJavaCrash();
        instance = this;
    }
    public static MyApplication getInstance() {
        return instance;
    }

}
