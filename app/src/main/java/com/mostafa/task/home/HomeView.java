package com.mostafa.task.home;

import android.content.SharedPreferences;

import com.mostafa.task.models.CityTemperatureResponse;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

public interface HomeView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getCityTemperatureSuccess(CityTemperatureResponse cityTemperatureResponse);

    String getSpinnerCityName();

    SharedPreferences getPref();

}
