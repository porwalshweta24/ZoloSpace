package app.com.zolospace.data.remote.service;

import app.com.zolospace.data.remote.dto.LoginItem;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 7/22/2017.
 */

public interface LoginService {
    @GET("/login")
    Call<LoginItem> fetchLoginDetails();
}
