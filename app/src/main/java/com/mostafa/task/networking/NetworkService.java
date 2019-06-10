package com.mostafa.task.networking;


import com.mostafa.task.models.CityTemperatureResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

public interface NetworkService {

    @GET("weather")
    Observable<CityTemperatureResponse> getCityTemperature(@Query("APPID") String appId,
                                                           @Query("q") String cityName);

}
