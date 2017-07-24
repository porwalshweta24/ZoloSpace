package app.com.zolospace.data.remote;

import android.accounts.NetworkErrorException;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import app.com.zolospace.App;
import app.com.zolospace.data.remote.dto.LoginItem;
import app.com.zolospace.data.remote.service.LoginService;
import app.com.zolospace.utils.Constants;
import app.com.zolospace.utils.L;
import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;

import static app.com.zolospace.data.remote.ServiceError.NETWORK_ERROR;
import static app.com.zolospace.data.remote.ServiceError.SUCCESS_CODE;
import static app.com.zolospace.utils.Constants.ERROR_UNDEFINED;
import static app.com.zolospace.utils.NetworkUtils.isConnected;
import static app.com.zolospace.utils.ObjectUtil.isNull;


public class RemoteRepository implements RemoteSource {
    private final String UNDELIVERABLE_EXCEPTION_TAG = "Undeliverable exception received, not sure what to do";
    private ServiceGenerator serviceGenerator;

    @Inject
    public RemoteRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    /* @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
     @Override
     public Single getNews() {
         RxJavaPlugins.setErrorHandler(throwable -> {
             Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
             return;
         });
         Single<NewsModel> newsModelSingle = Single.create(singleOnSubscribe -> {
                     if (!isConnected(App.getContext())) {
                         Exception exception = new NetworkErrorException();
                         singleOnSubscribe.onError(exception);
                     } else {
                         try {
                             NewsService newsService = serviceGenerator.createService(NewsService.class,
                                     Constants.BASE_URL);
                             ServiceResponse serviceResponse = processCall(newsService.fetchNews(), false);
                             if (serviceResponse.getCode() == SUCCESS_CODE) {
                                 NewsModel newsModel = (NewsModel) serviceResponse.getData();
                                 singleOnSubscribe.onSuccess(newsModel);
                             } else {
                                 Throwable throwable = new NetworkErrorException();
                                 singleOnSubscribe.onError(throwable);
                             }
                         } catch (Exception e) {
                             singleOnSubscribe.onError(e);
                         }
                     }
                 }
         );
         return newsModelSingle;
     }*/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Single getLoginDetails() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i(UNDELIVERABLE_EXCEPTION_TAG, throwable.getMessage());
            return;
        });
        Single<LoginItem> newsModelSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(App.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            LoginService newsService = serviceGenerator.createService(LoginService.class,
                                    Constants.BASE_URL);
                            ServiceResponse serviceResponse = processCall(newsService.fetchLoginDetails(), false);
                            if (serviceResponse.getCode() == SUCCESS_CODE) {
                                LoginItem newsModel = (LoginItem) serviceResponse.getData();
                                singleOnSubscribe.onSuccess(newsModel);
                            } else {
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
                }
        );
        return newsModelSingle;
    }

    @NonNull
    private ServiceResponse processCall(Call call, boolean isVoid) {
        if (!isConnected(App.getContext())) {
            return new ServiceResponse(new ServiceError());
        }
        try {
            retrofit2.Response response = call.execute();
            Gson gson = new Gson();
            L.json(LoginItem.class.getName(), gson.toJson(response.body()));
            if (isNull(response)) {
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }
}
