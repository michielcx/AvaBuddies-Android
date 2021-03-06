package com.projectsoa.avabuddies;


import com.projectsoa.avabuddies.core.dagger.components.DaggerAppComponent;
import com.projectsoa.avabuddies.data.repositories.LoginRepository;
import com.projectsoa.avabuddies.data.repositories.UserRepository;
import com.projectsoa.avabuddies.data.services.AuthService;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {
    private static App instance;

    @Inject
    protected AuthService authService;

    @Inject
    protected LoginRepository loginRepository;

    @Inject
    protected UserRepository userRepository;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        loginRepository.setAuthService(authService);
        loginRepository.setUserRepository(userRepository);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
