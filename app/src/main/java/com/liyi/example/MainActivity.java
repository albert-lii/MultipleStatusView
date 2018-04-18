package com.liyi.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.liyi.multiple.MultipleStatusView;

public class MainActivity extends Activity implements View.OnClickListener {
    private MultipleStatusView defaultView, customView;
    private StatusViewUtil util;

    private Button default_loading, default_networkPoor, default_empty, default_error, default_dismiss;
    private Button custom_loading, custom_networkPoor, custom_empty, custom_error,
            custom_specified, custom_10, custom_20, custom_dismiss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defaultView = findViewById(R.id.multipleStatusView_default);
        customView = findViewById(R.id.multipleStatusView_custom);

        default_loading = findViewById(R.id.btn_default_loading);
        default_networkPoor = findViewById(R.id.btn_default_networkPoor);
        default_empty = findViewById(R.id.btn_default_empty);
        default_error = findViewById(R.id.btn_default_error);
        default_dismiss = findViewById(R.id.btn_default_dismiss);

        custom_loading = findViewById(R.id.btn_custom_loading);
        custom_networkPoor = findViewById(R.id.btn_custom_networkPoor);
        custom_empty = findViewById(R.id.btn_custom_empty);
        custom_error = findViewById(R.id.btn_custom_error);
        custom_specified = findViewById(R.id.btn_custom_specified);
        custom_10 = findViewById(R.id.btn_custom_10);
        custom_20 = findViewById(R.id.btn_custom_20);
        custom_dismiss = findViewById(R.id.btn_custom_dismiss);

        util = new StatusViewUtil(defaultView, customView);
        addListeners();
    }

    private void addListeners() {
        default_loading.setOnClickListener(this);
        default_networkPoor.setOnClickListener(this);
        default_empty.setOnClickListener(this);
        default_error.setOnClickListener(this);
        default_dismiss.setOnClickListener(this);
        custom_loading.setOnClickListener(this);
        custom_networkPoor.setOnClickListener(this);
        custom_empty.setOnClickListener(this);
        custom_error.setOnClickListener(this);
        custom_specified.setOnClickListener(this);
        custom_10.setOnClickListener(this);
        custom_20.setOnClickListener(this);
        custom_dismiss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_default_loading:
                util.showDefaultView(MultipleStatusView.ViewType.TYPE_LOADING);
                break;
            case R.id.btn_default_networkPoor:
                util.showDefaultView(MultipleStatusView.ViewType.TYPE_NETWORK_POOR);
                break;
            case R.id.btn_default_empty:
                util.showDefaultView(MultipleStatusView.ViewType.TYPE_EMPTY);
                break;
            case R.id.btn_default_error:
                util.showDefaultView(MultipleStatusView.ViewType.TYPE_ERROR);
                break;
            case R.id.btn_default_dismiss:
                defaultView.dimiss();
                break;

            case R.id.btn_custom_loading:
                util.showCustomView(MultipleStatusView.ViewType.TYPE_LOADING);
                break;
            case R.id.btn_custom_networkPoor:
                util.showCustomView(MultipleStatusView.ViewType.TYPE_NETWORK_POOR);
                break;
            case R.id.btn_custom_empty:
                util.showCustomView(MultipleStatusView.ViewType.TYPE_EMPTY);
                break;
            case R.id.btn_custom_error:
                util.showCustomView(MultipleStatusView.ViewType.TYPE_ERROR);
                break;
            case R.id.btn_custom_specified:
                util.showCustomView(MultipleStatusView.ViewType.TYPE_SPECIFIED);
                break;
            case R.id.btn_custom_10:
                util.showCustomView(10);
                break;
            case R.id.btn_custom_20:
                util.showCustomView(20);
                break;
            case R.id.btn_custom_dismiss:
                customView.dimiss();
                break;
        }
    }
}
