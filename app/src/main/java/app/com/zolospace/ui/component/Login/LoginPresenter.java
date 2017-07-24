package app.com.zolospace.ui.component.Login;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class LoginPresenter extends Presenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
//        getView().initializeView();
    }

    @Override
    public String getLoginDetails() {
        return null;
    }
}
