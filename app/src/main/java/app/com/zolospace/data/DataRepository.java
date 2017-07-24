package app.com.zolospace.data;

import android.os.Build;

import javax.inject.Inject;

import app.com.zolospace.data.local.LocalRepository;
import app.com.zolospace.data.remote.RemoteRepository;
import app.com.zolospace.data.remote.dto.LoginItem;
import io.reactivex.Single;


public class DataRepository implements DataSource {
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Single<LoginItem> requestLoginDetails() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return remoteRepository.getLoginDetails();
        } else
            return null;
    }
}
