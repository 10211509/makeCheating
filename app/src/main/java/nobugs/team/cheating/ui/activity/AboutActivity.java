package nobugs.team.cheating.ui.activity;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import nobugs.team.cheating.R;
import nobugs.team.cheating.app.base.BaseActivity;
import nobugs.team.cheating.model.App;
import nobugs.team.cheating.model.Company;
import nobugs.team.cheating.presenter.AboutPresenter;
import nobugs.team.cheating.presenter.impl.AboutPresenterImpl;

public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutPresenter.View {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.iv_logo)
    ImageView ivLogo;
    @Bind(R.id.tv_app_name)
    TextView tvAppName;
    @Bind(R.id.tv_app_ver)
    TextView tvAppVer;
    @Bind(R.id.tv_tel_serv)
    TextView tvTelServ;
    @Bind(R.id.tv_tel_tech)
    TextView tvTelTech;
    @Bind(R.id.tv_company_web)
    TextView tvCompanyWeb;
    @Bind(R.id.tv_company_name)
    TextView tvCompanyName;

    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenterImpl(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showAppInfo(App app) {
        if (!TextUtils.isEmpty(app.getLogo())) {
            Picasso.with(this).load(app.getLogo()).into(ivLogo);
        }
        if (!TextUtils.isEmpty(app.getName())) {
            tvAppName.setText(app.getName());
        }
        if (!TextUtils.isEmpty(app.getVersion())) {
            tvAppVer.setText(app.getVersion());
        }
    }

    @Override
    public void showCompanyInfo(Company company) {
        if (!TextUtils.isEmpty(company.getName())) {
            tvCompanyName.setText(company.getName());
        }
        if (!TextUtils.isEmpty(company.getSite())) {
            tvCompanyWeb.setText(company.getSite());
        }
        if (!TextUtils.isEmpty(company.getTelServ())) {
            tvTelServ.setText(company.getTelServ());
        }
        if (!TextUtils.isEmpty(company.getTelTech())) {
            tvTelTech.setText(company.getTelTech());
        }
    }

}
