package app.com.zolospace.ui.component.splash;

import android.content.Intent;
import android.os.Handler;

import javax.inject.Inject;

import app.com.zolospace.App;
import app.com.zolospace.R;
import app.com.zolospace.ui.base.BaseActivity;
import app.com.zolospace.ui.component.Login.LoginActivity;
import app.com.zolospace.ui.component.profile.ProfileActivity;
import app.com.zolospace.utils.ZoloPreference;

import static app.com.zolospace.utils.Constants.SPLASH_DELAY;


public class SplashActivity extends BaseActivity implements SplashContract.View {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(SplashActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }


    @Override
    public void NavigateToMainScreen() {
        try {
            new Handler().postDelayed(() -> {
                if (ZoloPreference.getInstance().hasValueForKey(ZoloPreference.USERENUMMBER)) {
                    Intent intent = new Intent(SplashActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, SPLASH_DELAY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
