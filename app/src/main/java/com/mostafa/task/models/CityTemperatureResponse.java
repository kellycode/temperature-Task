package com.mostafa.task.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */


public class CityTemperatureResponse {

    @SerializedName("main")
    @Expose
    private CityMainData cityMainDatadata;

    @SerializedName("weather")
    @Expose
    private List<Weather> weather = null;

    @SerializedName("wind")
    @Expose
    private Wind wind;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("cod")
    @Expose
    private int cod;

    private String time;

    /**
     * @return The data
     */
    public CityMainData getData() {
        return cityMainDatadata;
    }

    /**
     * @param data The data
     */
    public void setData(CityMainData data) {
        this.cityMainDatadata = data;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The cod
     */
    public int getCod() {
        return cod;
    }

    /**
     * @param cod The cod
     */
    public void setCod(int cod) {
        this.cod = cod;
    }

    public class CityMainData {

        @SerializedName("temp")
        @Expose
        private double temp;

        @SerializedName("temp_max")
        @Expose
        private double tempMax;

        @SerializedName("temp_min")
        @Expose
        private double tempMin;

        @SerializedName("humidity")
        @Expose
        private double humidity;


        public double getTemp() {
            return temp;
        }


        public void setTemp(double temp) {
            this.temp = temp;
        }


        public double getTempMax() {
            return tempMax;
        }

        public void setTempMax(double tempMax) {
            this.tempMax = tempMax;
        }

        public double getTempMin() {
            return tempMin;
        }

        public void setTempMin(double tempMin) {
            this.tempMin = tempMin;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }
    }

    public class Weather {

        private Integer id;
        private String main;
        private String description;
        private String icon;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

    }

    public class Wind {
        private float speed;
        private float deg;


        // Getter Methods

        public float getSpeed() {
            return speed;
        }

        public float getDeg() {
            return deg;
        }

        // Setter Methods

        public void setSpeed(float speed) {
            this.speed = speed;
        }

        public void setDeg(float deg) {
            this.deg = deg;
        }
    }



}