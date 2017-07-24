package app.com.zolospace.ui.component.reset;

import android.os.Bundle;

import javax.inject.Inject;

import app.com.zolospace.ui.base.Presenter;


public class ResetPresenter extends Presenter<ResetContract.View> implements ResetContract.Presenter {

    @Inject
    public ResetPresenter() {
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
