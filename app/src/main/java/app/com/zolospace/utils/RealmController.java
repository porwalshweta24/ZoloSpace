package app.com.zolospace.utils;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import app.com.zolospace.data.remote.dto.LoginData;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from Book.class
    public void clearAll() {

        realm.beginTransaction();
        realm.clear(LoginData.class);
        realm.commitTransaction();
    }

    //find all objects in the Book.class
    public RealmResults<LoginData> getDetails() {

        return realm.where(LoginData.class).findAll();
    }

    //query a single item with the given id
    public LoginData getDetails(String id) {

        return realm.where(LoginData.class).equalTo("id", id).findFirst();
    }

    //query a single item with the given id
    public LoginData getDetailsPhone(String phone) {
        return realm.where(LoginData.class).equalTo("phone", phone).findFirst();
    }

    //check if Book.class is empty
    public boolean hasDetails() {

        return !realm.allObjects(LoginData.class).isEmpty();
    }

    //query example
    public RealmResults<LoginData> queryedDetails() {

        return realm.where(LoginData.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();

    }
}
