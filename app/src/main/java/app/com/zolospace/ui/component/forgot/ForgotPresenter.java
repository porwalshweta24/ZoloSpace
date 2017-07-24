package app.com.zolospace.ui.component.forgot;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class ForgotPresenter extends Presenter<ForgotContract.View> implements ForgotContract.Presenter {

    @Inject
    public ForgotPresenter() {
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
