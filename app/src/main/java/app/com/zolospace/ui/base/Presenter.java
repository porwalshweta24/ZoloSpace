package app.com.zolospace.ui.base;


import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

import app.com.zolospace.ui.base.listeners.BaseView;


public abstract class Presenter<T extends BaseView> {

    protected AtomicBoolean isViewAlive = new AtomicBoolean();
    private WeakReference<T> view;

    public T getView() {
        return view.get();
    }

    public void setView(T view) {
        this.view = new WeakReference<>(view);
    }

    public void initialize(Bundle extras) {
    }

    public void start() {
        isViewAlive.set(true);
    }

    public void finalizeView() {
        isViewAlive.set(false);
    }

}
