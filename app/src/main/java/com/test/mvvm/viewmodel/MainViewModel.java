package com.test.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.mvvm.repository.DataRepository;
import com.test.mvvm.resource.StatusResource;

public class MainViewModel extends ViewModel {

    public MutableLiveData<StatusResource<Object>> getTestAPI(){
        return DataRepository.getRepositoryInstance().getTestAPI();
    }
}
