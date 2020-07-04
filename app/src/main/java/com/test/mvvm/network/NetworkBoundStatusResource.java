package com.test.mvvm.network;

import androidx.annotation.MainThread;
import androidx.lifecycle.MutableLiveData;
import com.test.mvvm.resource.StatusResource;

public abstract class NetworkBoundStatusResource<T> {
    private final MutableLiveData<StatusResource<T>> mutableLiveData = new MutableLiveData<>();

    @MainThread
    public NetworkBoundStatusResource() {
        mutableLiveData.setValue(StatusResource.<T>loading());
        createCall();
    }

    @MainThread
    protected abstract void createCall();

    public void setMutableLiveData(StatusResource<T> value) {
        mutableLiveData.setValue(value);
    }

    public void postMutableLiveData(StatusResource<T> value) {
        mutableLiveData.postValue(value);
    }

    public final MutableLiveData<StatusResource<T>> getMutableLiveData() {
        return mutableLiveData;
    }
}
