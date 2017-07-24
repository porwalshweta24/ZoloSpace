package app.com.zolospace.di;


import javax.inject.Singleton;

import app.com.zolospace.ui.component.Login.LoginActivity;
import app.com.zolospace.ui.component.forgot.ForgotActivity;
import app.com.zolospace.ui.component.profile.ProfileActivity;
import app.com.zolospace.ui.component.register.RegisterActivity;
import app.com.zolospace.ui.component.register.VerifyActivity;
import app.com.zolospace.ui.component.reset.ResetActivity;
import app.com.zolospace.ui.component.splash.SplashActivity;
import dagger.Component;


@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(ForgotActivity activity);

    void inject(LoginActivity activity);

    void inject(ResetActivity activity);

    void inject(RegisterActivity activity);

    void inject(VerifyActivity activity);

    void inject(ProfileActivity activity);
}
