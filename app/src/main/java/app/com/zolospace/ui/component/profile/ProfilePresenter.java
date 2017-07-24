package app.com.zolospace.ui.component.profile;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class ProfilePresenter extends Presenter<ProfileContract.View> implements ProfileContract.Presenter {

    @Inject
    public ProfilePresenter() {
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
