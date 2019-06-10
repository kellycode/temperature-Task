package com.mostafa.task.networking;


import com.mostafa.task.BaseApp;
import com.mostafa.task.models.CityTemperatureResponse;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

public class GetTemperatureService {
    private final NetworkService networkService;

    public GetTemperatureService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getCityTemperature(final GetCityTemperatureCallback callback, String cityName) {

        return networkService.getCityTemperature(BaseApp.API_KEY, cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends CityTemperatureResponse>>() {
                    @Override
                    public Observable<? extends CityTemperatureResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<CityTemperatureResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(CityTemperatureResponse cityTemperatureResponse) {
                        callback.onSuccess(cityTemperatureResponse);

                    }
                });
    }

    public interface GetCityTemperatureCallback {
        void onSuccess(CityTemperatureResponse cityTemperatureResponse);

        void onError(NetworkError networkError);
    }
}
