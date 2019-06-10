package com.mostafa.task.deps;


import com.mostafa.task.home.HomeActivity;
import com.mostafa.task.networking.NetworkModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by Mostafa Gaballah on 06/09/2019.
 */

@Singleton
@Component(modules = {NetworkModule.class,})
public interface Deps {
    void inject(HomeActivity homeActivity);
}
