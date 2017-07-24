package app.com.zolospace.data;

import app.com.zolospace.data.remote.dto.LoginItem;
import io.reactivex.Single;


interface DataSource {
    Single<LoginItem> requestLoginDetails();
}
