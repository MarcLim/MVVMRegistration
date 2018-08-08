package com.example.marcivanlim.mvvmregistration.DI;



import com.example.marcivanlim.mvvmregistration.Module.ActivityModule;
import com.example.marcivanlim.mvvmregistration.Module.ServiceModule;
import com.example.marcivanlim.mvvmregistration.Module.UtilsModule;
import com.example.marcivanlim.mvvmregistration.Registration.View.RegistrationView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ActivityModule.class, UtilsModule.class, ServiceModule.class})

public interface AppComponent {


    void inject(RegistrationView registrationView);


}
