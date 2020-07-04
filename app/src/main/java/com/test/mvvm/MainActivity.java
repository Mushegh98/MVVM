package com.test.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.test.mvvm.resource.StatusResource;
import com.test.mvvm.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.getTestAPI().observe(MainActivity.this, new Observer<StatusResource<Object>>() {
                    @Override
                    public void onChanged(StatusResource<Object> objectStatusResource) {
                        switch (objectStatusResource.status){
                            case SUCCESS:
                                //DO Success case
                                break;
                            case LOADING:
                                break;
                            case ERROR:
                                //DO Error case
                                break;
                        }
                    }
                });
            }
        });
    }
}