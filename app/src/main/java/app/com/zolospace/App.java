package app.com.zolospace;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.multidex.MultiDexApplication;

import app.com.zolospace.di.DaggerMainComponent;
import app.com.zolospace.di.MainComponent;
import app.com.zolospace.utils.ZoloPreference;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends MultiDexApplication {
    private static Context context;
    private MainComponent mainComponent;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
        ZoloPreference.init(getApplicationContext());

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @VisibleForTesting
    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}
