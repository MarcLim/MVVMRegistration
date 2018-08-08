package com.example.marcivanlim.mvvmregistration.Module;



import com.example.marcivanlim.mvvmregistration.Utils.Utils;
import com.example.marcivanlim.mvvmregistration.Utils.UtilsInterface;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    static UtilsInterface provideUtilsInterface() {
        return new Utils();
    }


}
