package com.example.mvvmtest;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mvvmtest.api.Api;
import com.example.mvvmtest.databinding.ActivityMainBinding;
import com.example.mvvmtest.model.Time;
import com.example.mvvmtest.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // create ViewModel for specific model and pass observable
        ViewModel<Time> timeViewModel = new ViewModel<>(Api.getInstance().getTime());
        // bind to existing binding
        timeViewModel.bind(binding, BR.time);
    }

    public void onTextClick(View view) {
        startActivity(new Intent(this, AdvancedActivity.class));
    }
}
