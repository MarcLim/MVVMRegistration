package com.example.marcivanlim.mvvmregistration.Module;

import com.example.marcivanlim.mvvmregistration.API.APIClient;
import com.example.marcivanlim.mvvmregistration.API.APIClientInterface;
import com.example.marcivanlim.mvvmregistration.Registration.ViewModel.RegistrationInterface;
import com.example.marcivanlim.mvvmregistration.Registration.ViewModel.RegistrationViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ServiceModule {

    @Provides
    @Singleton
    static APIClientInterface provideAPIInterface() {
        return new APIClient();
    }
}
