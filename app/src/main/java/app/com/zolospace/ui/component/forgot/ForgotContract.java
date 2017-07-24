package app.com.zolospace.ui.component.forgot;


import app.com.zolospace.data.remote.dto.LoginItem;
import app.com.zolospace.ui.base.listeners.BaseView;


public interface ForgotContract {
    interface View extends BaseView {
        void initializeView(LoginItem newsItem);
    }

    interface Presenter {
        String getLoginDetails();

    }
}
