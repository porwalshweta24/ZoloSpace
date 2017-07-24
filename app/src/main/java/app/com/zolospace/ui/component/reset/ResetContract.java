package app.com.zolospace.ui.component.reset;


import app.com.zolospace.data.remote.dto.LoginItem;
import app.com.zolospace.ui.base.listeners.BaseView;


public interface ResetContract {
    interface View extends BaseView {
        void initializeView(LoginItem newsItem);
    }

    interface Presenter {
        String getLoginDetails();

    }
}
