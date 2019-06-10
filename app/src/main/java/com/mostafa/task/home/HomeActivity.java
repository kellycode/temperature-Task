package com.mostafa.task.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.mostafa.task.BaseApp;
import com.mostafa.task.R;
import com.mostafa.task.models.CityTemperatureResponse;
import com.mostafa.task.networking.GetTemperatureService;
import com.mostafa.task.util.Utility;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

public class HomeActivity extends BaseApp implements HomeView {

    private Button submitButton;
    private LinearLayout successLayout;
    private TextView cityTextView,tempTexView, timeTextView, weatherTextView, windTextView, errorTextView;
    private Spinner cityEditText;
    HomePresenter presenter;

    @Inject
    public GetTemperatureService getTemperatureService;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);

        renderView();
        init();
    }

    public void renderView() {
        setContentView(R.layout.activity_home);
        progressBar = findViewById(R.id.progress);
        submitButton = findViewById(R.id.submit_button);
        successLayout = findViewById(R.id.success_layout);
        tempTexView = findViewById(R.id.temp_text_view);
        errorTextView = findViewById(R.id.error_text_view);
        cityEditText = findViewById(R.id.edit_city_text_view);
        windTextView = findViewById(R.id.wind_text_view);
        weatherTextView = findViewById(R.id.weather_text_view);
        timeTextView = findViewById(R.id.time_text_view);
        cityTextView = findViewById(R.id.city_text_view);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.str_arr_cities, android.R.layout.simple_spinner_dropdown_item);
        cityEditText.setAdapter(adapter);

        //handle button click function
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                if (cityEditText.getSelectedItemPosition() == 0) {
                    return;
                } else {
                    presenter = new HomePresenter(getTemperatureService,
                            cityEditText.getSelectedItem().toString().trim(),
                            HomeActivity.this);
                    presenter.getCityTemperature();
                }
            }
        });

    }




    public void init() {
        submitButton.setEnabled(true);
        successLayout.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
    }


    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onFailure(String appErrorMessage) {
        successLayout.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(appErrorMessage);
    }

    @Override
    public void getCityTemperatureSuccess(CityTemperatureResponse cityTemperatureResponse) {
        successLayout.setVisibility(View.VISIBLE);
        errorTextView.setVisibility(View.GONE);
        fillTableFromResponse(cityTemperatureResponse);
    }


    private void fillTableFromResponse(CityTemperatureResponse cityTemperatureResponse){
        cityTextView.setText(cityTemperatureResponse.getName());
        timeTextView.setText(cityTemperatureResponse.getTime());
        tempTexView.setText(Utility.convertFromKelvinToCelisius(cityTemperatureResponse.getData().getTemp()) +
                getResources().getString(R.string.degree_char));
        weatherTextView.setText(cityTemperatureResponse.getWeather().get(0).getDescription());
        windTextView.setText(cityTemperatureResponse.getWind().getSpeed() + getResources().getString(R.string.speed));

        //save response in shared pref to get when offline
        presenter.updateSharedPrefCity(getPref(),cityTemperatureResponse);
    }

    public String getSpinnerCityName(){
        return cityEditText.getSelectedItem().toString().trim();
    }


    @Singleton
    public SharedPreferences getPref(){
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        return mPrefs;
    }
}
