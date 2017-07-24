package app.com.zolospace.ui.component.register;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class VerifyPresenter extends Presenter<VerifyContract.View> implements VerifyContract.Presenter {

    @Inject
    public VerifyPresenter() {
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
