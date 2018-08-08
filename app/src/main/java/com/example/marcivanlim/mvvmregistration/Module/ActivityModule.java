package com.example.marcivanlim.mvvmregistration.Module;


import com.example.marcivanlim.mvvmregistration.Registration.ViewModel.RegistrationInterface;
import com.example.marcivanlim.mvvmregistration.Registration.ViewModel.RegistrationViewModel;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {



    @Provides
    @Singleton
    static RegistrationInterface provideRegistrationPresenter() {
        return new RegistrationViewModel();
    }



}
