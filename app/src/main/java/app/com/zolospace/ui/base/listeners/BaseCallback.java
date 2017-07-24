package app.com.zolospace.ui.base.listeners;


import app.com.zolospace.data.remote.dto.LoginItem;

public interface BaseCallback {
    void onSuccess(LoginItem newsModel);

    void onFail();
}
