package app.com.zolospace.ui.component.splash;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class SplashPresenter extends Presenter<SplashContract.View> implements SplashContract.Presenter {

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().NavigateToMainScreen();
    }
}
