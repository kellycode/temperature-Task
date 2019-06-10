package com.mostafa.task;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mostafa.task.deps.DaggerDeps;
import com.mostafa.task.deps.Deps;
import com.mostafa.task.networking.NetworkModule;

import java.io.File;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

public class BaseApp extends AppCompatActivity {
    Deps deps;
    public static final String API_KEY = "ea458b9a2d24d2b7d452f9b522c9780d";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }
}
