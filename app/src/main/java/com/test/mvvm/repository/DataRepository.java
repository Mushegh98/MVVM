package com.test.mvvm.repository;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.test.mvvm.network.ClientApi;
import com.test.mvvm.network.ClientService;
import com.test.mvvm.network.NetworkBoundStatusResource;
import com.test.mvvm.resource.StatusResource;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DataRepository {

    private static volatile DataRepository instance;
    private ClientApi clientApi;
    private CompositeDisposable compositeDisposable;

    private DataRepository(Context context) {
        clientApi = ClientService.getInstance().createClientApi();
        compositeDisposable = new CompositeDisposable();
    }

    public static void buildRepositoryInstance(Context context) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    instance = new DataRepository(context);
                }
            }
        }
    }

    public static DataRepository getRepositoryInstance() {
        return instance;
    }

    public MutableLiveData<StatusResource<Object>> getTestAPI(){
        return new NetworkBoundStatusResource<Object>(){

            @Override
            protected void createCall() {
               Disposable disposable = clientApi.testApiCall()
                       .subscribeOn(Schedulers.newThread())
                       .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(test->{
                            postMutableLiveData(StatusResource.success(test));
                        },throwable -> {
                            postMutableLiveData(StatusResource.error("Failed"));
                        });
               compositeDisposable.add(disposable);
            }
        }.getMutableLiveData();
    }

    public void clearDisposable(){
        compositeDisposable.clear();
    }
}
