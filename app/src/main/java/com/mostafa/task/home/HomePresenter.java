package com.mostafa.task.home;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.mostafa.task.models.CityTemperatureResponse;
import com.mostafa.task.networking.GetTemperatureService;
import com.mostafa.task.networking.NetworkError;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

public class HomePresenter {
    private final GetTemperatureService getTemperatureService;
    private final HomeView view;
    private CompositeSubscription subscriptions;
    private String cityName;

    public HomePresenter(GetTemperatureService getTemperatureService, String cityName, HomeView view) {
        this.getTemperatureService = getTemperatureService;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
        this.cityName = cityName;
    }

    public void getCityTemperature() {
        view.showWait();

        Subscription subscription = getTemperatureService.getCityTemperature(new GetTemperatureService.GetCityTemperatureCallback() {
            @Override
            public void onSuccess(CityTemperatureResponse cityTemperatureResponse) {
                view.removeWait();
                cityTemperatureResponse.setTime(new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime()));
                view.getCityTemperatureSuccess(cityTemperatureResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                CityTemperatureResponse cityTemperatureResponse = getSharedPrefCityIfAny(view.getSpinnerCityName());
                if (cityTemperatureResponse == null)
                    view.onFailure(networkError.getAppErrorMessage());
                else
                    view.getCityTemperatureSuccess(cityTemperatureResponse);
            }
        }, cityName);

        subscriptions.add(subscription);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void updateSharedPrefCity(SharedPreferences mPrefs, CityTemperatureResponse cityTemperatureResponse) {
        Gson gson = new Gson();
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        String json = gson.toJson(cityTemperatureResponse);
        prefsEditor.putString(cityTemperatureResponse.getName(), json);
        prefsEditor.commit();
    }


    private CityTemperatureResponse getSharedPrefCityIfAny(String cityName) {
        Gson gson = new Gson();
        String json = view.getPref().getString(cityName, "");
        CityTemperatureResponse cityTemperatureResponse = gson.fromJson(json, CityTemperatureResponse.class);
        if (cityTemperatureResponse == null || cityTemperatureResponse.toString() == "" || cityTemperatureResponse.equals(""))
            return null;
        else
            return cityTemperatureResponse;
    }
}


