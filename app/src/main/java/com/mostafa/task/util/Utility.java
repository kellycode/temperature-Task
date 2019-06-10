package com.mostafa.task.util;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */
public class Utility {

    private static final double KELVIN_FACTOR = 273.15;

    public static int convertFromKelvinToCelisius(double tempInKelvin) {
        //return city degree and convert it to cilisius dgree
          return (int) (tempInKelvin - KELVIN_FACTOR);
    }


}
