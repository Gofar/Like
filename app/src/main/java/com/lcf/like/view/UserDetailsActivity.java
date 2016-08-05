package com.lcf.like.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;

import com.lcf.like.databinding.ActivityUserDetailsBinding;
import com.lcf.like.viewmodel.UserViewModel;

/**
 *
 */
public class UserDetailsActivity extends BaseCompat1Activity {
    private String login;
    private ActivityUserDetailsBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void initVariables() {
        Intent intent = getIntent();
        if (intent != null) {
            login = intent.getStringExtra("login");
        }
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        //binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details);
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.toolbar.setTitle(login);
    }

    @Override
    protected void loadData() {
        if (!TextUtils.isEmpty(login)) {
            userViewModel = new UserViewModel(this, login);
        }
    }
}
