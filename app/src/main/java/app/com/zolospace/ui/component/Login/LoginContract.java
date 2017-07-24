package app.com.zolospace.ui.component.Login;


import app.com.zolospace.data.remote.dto.LoginItem;
import app.com.zolospace.ui.base.listeners.BaseView;


public interface LoginContract {
    public interface View extends BaseView {
        void initializeView(LoginItem newsItem);
    }

    public interface Presenter {
        String getLoginDetails();

    }
}
