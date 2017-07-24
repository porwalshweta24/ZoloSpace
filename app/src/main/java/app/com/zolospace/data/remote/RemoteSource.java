package app.com.zolospace.data.remote;

import io.reactivex.Single;


interface RemoteSource {
    Single getLoginDetails();
}
