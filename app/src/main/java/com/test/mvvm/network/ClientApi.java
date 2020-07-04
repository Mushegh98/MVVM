package com.test.mvvm.network;


import io.reactivex.Single;
import retrofit2.http.GET;

/**
  Interface for API Calls
 **/
public interface ClientApi {

    @GET("TEST_PATH")
    Single<Object> testApiCall();

}
