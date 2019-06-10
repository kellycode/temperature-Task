package com.mostafa.task;

import com.mostafa.task.home.HomePresenter;
import com.mostafa.task.home.HomeView;
import com.mostafa.task.models.CityTemperatureResponse;
import com.mostafa.task.networking.GetTemperatureService;
import com.mostafa.task.networking.NetworkService;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import rx.Observable;

import static org.mockito.Mockito.when;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private NetworkService networkService;

    @Mock
    private HomeView homeView;

    @Mock
    private CityTemperatureResponse cityTemperatureResponse;

    private GetTemperatureService getTemperatureService;
    private HomePresenter homePresenter;
    private String cityName = "Melbourne";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getTemperatureService = new GetTemperatureService(networkService);
        homePresenter = new HomePresenter(getTemperatureService,cityName, homeView);
    }

    @After
    public void teardown() {
        homePresenter.onStop();
    }

    @Test
    public void loadCitiesFromAPIAndLoadIntoView() {

        Observable<CityTemperatureResponse> responseObservable = Observable.just(cityTemperatureResponse);
        when(networkService.getCityTemperature(BaseApp.API_KEY,cityName)).thenReturn(responseObservable);

        homePresenter.getCityTemperature();

        InOrder inOrder = Mockito.inOrder(homeView);
        inOrder.verify(homeView).showWait();
        inOrder.verify(homeView).removeWait();
        inOrder.verify(homeView).getCityTemperatureSuccess(cityTemperatureResponse);
    }
}
