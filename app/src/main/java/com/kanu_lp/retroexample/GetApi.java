package com.kanu_lp.retroexample;

import com.kanu_lp.retroexample.NameData.Names;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Kanu on 8/16/2017.
 */

public interface GetApi {

    @GET("/apidemo/api.php")
    Call<Names> getNames();

    @FormUrlEncoded
    @POST("/apidemo/register.php")
    Call<ResponseBody> register_user(@Field("name") String name,@Field("role") String role);

}
