package app.com.zolospace.ui.component.register;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class RegisterPresenter extends Presenter<RegisterContract.View> implements RegisterContract.Presenter {

    @Inject
    public RegisterPresenter() {
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
