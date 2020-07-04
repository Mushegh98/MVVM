package com.test.mvvm;

import com.test.mvvm.repository.DataRepository;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DataRepository.buildRepositoryInstance(this);
    }
}
